/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Scoretable;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */
public interface ScoreTableService {
	Scoretable findScoreByUser(User user);
	void saveScore(Scoretable scoreTable);
	List<Scoretable> findAllScores();
	void updateAllScores();
}
