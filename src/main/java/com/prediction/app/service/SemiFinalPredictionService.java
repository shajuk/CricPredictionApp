/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import com.prediction.app.model.Semifinalprediction;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */
public interface SemiFinalPredictionService {
	Semifinalprediction findSemiFinalPredictionByUser(User user);
	void saveSemiFinalPrediction(Semifinalprediction semifinalprediction);
	List<Semifinalprediction> findAllSemiFinalPredictions();
}
