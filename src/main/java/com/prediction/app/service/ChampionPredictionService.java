/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Championprediction;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */
public interface ChampionPredictionService {
	Championprediction findChampionPredictionByUser(User user);
	void saveChampionPrediction(Championprediction championprediction);
	List<Championprediction> findAllChampionPredictions();
}
