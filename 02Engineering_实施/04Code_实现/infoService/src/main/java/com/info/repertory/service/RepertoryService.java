package com.info.repertory.service;

import java.util.List;

import com.info.repertory.model.Repertory;

public interface RepertoryService {
	
	public List<Repertory> getRepertory();
	
	public void updateRepertory(Repertory repertory);
	
	public Repertory getRepertoryById(String id);

}
