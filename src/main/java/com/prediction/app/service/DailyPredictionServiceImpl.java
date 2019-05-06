/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Game;
import com.prediction.app.model.User;
import com.prediction.app.repository.DailyPredictionRepository;

/**
 * @author Shaju K
 *
 */

@Service("dailyPredictionService")
public class DailyPredictionServiceImpl implements DailyPredictionService {
	
	@Autowired
	private DailyPredictionRepository dailyPredictionRepository;
	
	@Override
	public List<Dailyprediction> findDailyPredictionByUser(User user) {
		return dailyPredictionRepository.findByUser(user);
	}

	@Override
	public void saveDailyPrediction(Dailyprediction dailyPrediction) {
		dailyPredictionRepository.saveAndFlush(dailyPrediction);
	}

	@Override
	public List<Dailyprediction> findDailyPredictionByMatches(Game game) {
		return dailyPredictionRepository.findByGame(game);
	}

	@Override
	public List<Dailyprediction> findAllDailyPredictions() {
		return dailyPredictionRepository.findAll();
	}

	@Override
	public void updateDailyPredictionPoints(int matchNo, int successPoints,
			int failurePoints, String matchResult) {
		dailyPredictionRepository.updateDailyPredictionByMatch(matchNo,successPoints,failurePoints,matchResult);
	}
}
