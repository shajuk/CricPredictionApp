/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.Game;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */
public interface DailyPredictionService {
	List<Dailyprediction> findDailyPredictionByUser(User user);
	List<Dailyprediction> findDailyPredictionByMatches(Game game);
	Dailyprediction findDailyPredictionByDailyPrediction(int userid,int matchNo);
	void saveDailyPrediction(Dailyprediction dailyPrediction);
	List<Dailyprediction> findAllDailyPredictions();
	void updateDailyPredictionPoints(int matchNo, int successPoints, int failurePoints, String matchResult);
}
