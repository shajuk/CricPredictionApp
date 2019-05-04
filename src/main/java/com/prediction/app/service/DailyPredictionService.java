/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.Matches;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */
public interface DailyPredictionService {
	List<Dailyprediction> findByUser(User user);
	List<Dailyprediction> findByMatches(Matches match);
	void saveDailyPrediction(Dailyprediction dailyPrediction);
}
