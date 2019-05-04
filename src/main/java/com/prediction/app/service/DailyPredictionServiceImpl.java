/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Matches;
import com.prediction.app.model.User;
import com.prediction.app.repository.DailyPredictionRepository;

/**
 * @author Shaju K
 *
 */
public class DailyPredictionServiceImpl implements DailyPredictionService {
	
	@Autowired
	private DailyPredictionRepository dailyPredictionRepository;
	
	@Override
	public List<Dailyprediction> findByUser(User user) {
		Dailyprediction dailyprediction=new Dailyprediction();
		DailypredictionId dailypredictionId=new DailypredictionId();
		dailyprediction.setId(dailypredictionId);
		dailypredictionId.setUserid(user.getId());
		Example<Dailyprediction> dailypredictionExample= Example.of(dailyprediction);
		return dailyPredictionRepository.findAll(dailypredictionExample);
	}

	
	@Override
	public List<Dailyprediction> findByMatches(Matches match) {
		Dailyprediction dailyprediction=new Dailyprediction();
		DailypredictionId dailypredictionId=new DailypredictionId();
		dailyprediction.setId(dailypredictionId);
		dailypredictionId.setMatchNo(match.getMatchNo());
		Example<Dailyprediction> dailypredictionExample=Example.of(dailyprediction);
		return dailyPredictionRepository.findAll(dailypredictionExample);
	}

	
	@Override
	public void saveDailyPrediction(Dailyprediction dailyPrediction) {
		dailyPredictionRepository.saveAndFlush(dailyPrediction);
	}

}
