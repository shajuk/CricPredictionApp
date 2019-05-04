package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Game;


/**
 * @author Shaju K
 *
 */

public interface MatchService {
	
	public Game findMatchByMatchNo(Integer matchNo);
	public void saveMatch(Game match);
	public void saveAllMatches(List<Game> matches);
	public void updateMatch(Game match);
	public List<Game> findAllMatches();
}
