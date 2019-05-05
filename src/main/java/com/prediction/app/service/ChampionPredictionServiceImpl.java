/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Championprediction;
import com.prediction.app.model.User;
import com.prediction.app.repository.ChampionPredictionRepository;

/**
 * @author Shaju K
 *
 */
@Service("championPredictionService")
public class ChampionPredictionServiceImpl implements ChampionPredictionService {

	@Autowired
	ChampionPredictionRepository championPredictionRepository;  
	@Override
	public Championprediction findChampionPredictionByUser(User user) {
		return championPredictionRepository.findById(user.getId()).orElse(null);
	}

	@Override
	public void saveChampionPrediction(Championprediction championprediction) {
		championPredictionRepository.saveAndFlush(championprediction);
	}

	@Override
	public List<Championprediction> findAllChampionPredictions() {
		return championPredictionRepository.findAll();
	}

}
