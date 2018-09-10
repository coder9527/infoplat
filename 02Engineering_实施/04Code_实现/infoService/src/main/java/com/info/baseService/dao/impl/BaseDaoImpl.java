package com.info.baseService.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.baseService.dao.BaseDao;
import com.info.baseService.util.ObjectUtil;




@SuppressWarnings("serial")
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> ,Serializable {

	@Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public T selectById(String tableName, String pkName, String id, Class<T> type) {
        Map<String, Object> obj = jdbcTemplate.queryForMap("SELECT * FROM "+tableName+" WHERE "+pkName+" = ?", id);
        return ObjectUtil.mapToObject(obj, type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> selectByCriteria(String tableName, BaseDao.Criteria criteria, Class<T> type) {
        StringBuilder sqlStr = new StringBuilder("");
        sqlStr.append("SELECT * FROM ")
                .append(tableName)
                .append(criteria.getCriteriaSQL());
        System.out.println(sqlStr.toString());
        Object[] params = criteria.getParam().toArray(new Object[criteria.getParam().size()]);
        List<Map<String, Object>> objs = jdbcTemplate.queryForList(sqlStr.toString(), params);
        List<T> results = new ArrayList<T>();
        for(Map<String, Object> o: objs){
            results.add(ObjectUtil.mapToObject(o, type));
        }
        return results;
    }

    @Override
    public long countByCriteria(String tableName, BaseDao.Criteria criteria) {
        String sql = "SELECT COUNT(*) AS num FROM "+tableName + criteria.getCriteriaSQL();
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, criteria.getParam().toArray());
        return (Long)map.get("num");
    }

    @Override
    public int removeById(String tableName, String pkName, String id) {
        String sql = "DELETE FROM " +
                tableName +
                " WHERE " +
                pkName +
                " = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int save(String tableName, String pkName, T entity) {
        Map<String, Object> obj = ObjectUtil.objectToMap(entity);
        StringBuffer sql1 = new StringBuffer("INSERT INTO ")
                .append(tableName)
                .append("(");
        StringBuffer sql2 = new StringBuffer(" VALUES(");
        List<Object> args = new ArrayList<Object>();
        int count = 0;
        for(String key: obj.keySet()){
            Object arg = obj.get(key);
            if (arg==null){
                continue;
            }
            sql1.append(key).append(",");
            sql2.append("?,");
            args.add(arg);
        }
        sql1.deleteCharAt(sql1.length() - 1);
        sql1.append(") ");
        sql2.deleteCharAt(sql2.length() - 1);
        sql2.append(") ");
        String sql = sql1.append(sql2).toString();
        System.out.println(sql);
        try {
            count += jdbcTemplate.update(sql, args.toArray());
        }catch (DuplicateKeyException e){
            sql1 = new StringBuffer("UPDATE ")
                    .append(tableName)
                    .append(" SET ");
            sql2 = new StringBuffer(" WHERE "+pkName+"=?");
            args = new ArrayList<Object>();
            for (String key: obj.keySet()){
                if (key.equals(pkName)){
                    continue;
                }
                Object arg = obj.get(key);
                if (arg==null){
                    continue;
                }
                sql1.append(key).append("=?,");
                args.add(arg);
            }

            sql1.deleteCharAt(sql1.length() - 1);
            args.add(obj.get(pkName));
            sql = sql1.append(sql2).toString();
            System.out.println(sql);
            count+=jdbcTemplate.update(sql, args.toArray());
        }
        return count;
    }

    @Override
    public BaseDao.Criteria createCriteria() {
        return new Criteria();
    }


    /**
     * 查询条件的实现
     */
    class Criteria implements BaseDao.Criteria{
        private boolean not; //是否标记了非
        private boolean begin; //是否正在拼接第一个条件
        private boolean or;//是否修改连接词为OR
        StringBuilder criteriaSQL; //从where开始的条件sql
        List<Object> param; //参数列表
        String limitStr; //限制条数

        public Criteria(){
            criteriaSQL = new StringBuilder("");
            param = new LinkedList<Object>();
            not = false;
            begin = true;
            limitStr = "";
        }

        public Criteria not(){
            not = true;
            return this;
        }

        @Override
        public BaseDao.Criteria or() {
            or = true;
            return this;
        }

        private void link(){
            //判断是否是第一个条件
            // ，如果是就加WHERE不加连接词
            // ，不是就直接加连接词
            if(begin){
                criteriaSQL.append(" WHERE ");
            }else{
                if(or){
                    criteriaSQL.append(" OR ");
                }else{
                    criteriaSQL.append(" AND ");
                }
            }
            or = false;
        }

        public Criteria eq(String field, Object val) {
            link();
            if (not) {
                criteriaSQL.append(field)
                        .append(" != ?");
            } else {
                criteriaSQL.append(field)
                        .append(" = ?");
            }
            not = false;
            begin = false;
            param.add(val);
            return this;
        }

        public Criteria like(String field, Object val){
            link();
            if(not){
                criteriaSQL.append(field)
                        .append(" NOT LIKE ?");
            }else{
                criteriaSQL.append(field)
                        .append(" LIKE ?");
            }
            not = false;
            begin = false;
            param.add("%"+val+"%");
            return this;
        }
        public Criteria between(String field, Object val1, Object val2){
            link();
            if(not){
                criteriaSQL.append(field)
                        .append(" NOT BETWEEN ? AND ? ");
            }else{
                criteriaSQL.append(field)
                        .append(" BETWEEN ? AND ? ");
            }
            not = false;
            begin = false;
            param.add(val1);
            param.add(val2);
            return this;
        }

        @Override
        public BaseDao.Criteria limit(int start, int row) {
            limitStr = " limit " + start + "," + row;
            return this;
        }

        public List<Object> getParam(){
            return this.param;
        }
        public StringBuilder getCriteriaSQL(){
            return new StringBuilder(criteriaSQL.toString()+limitStr);
        }
    }
}
