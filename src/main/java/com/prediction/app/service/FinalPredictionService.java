/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prediction.app.model.Finalprediction;
import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */

public interface FinalPredictionService {
	Finalprediction findFinalPredictionByUser(User user);
	void saveFinalPrediction(Finalprediction finalprediction);
	List<Finalprediction> findAllFinalPredictions();
}
