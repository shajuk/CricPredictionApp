package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Matches;

/**
 * @author Shaju K
 *
 */

public interface MatchService {
	
	public Matches findMatchByMatchNo(Integer matchNo);
	public void saveMatch(Matches matches);
	public void saveAllMatches(List<Matches> matches);
	public void updateMatch(Matches match);
	public List<Matches> findAllMatches();
}
