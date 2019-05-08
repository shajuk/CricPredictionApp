/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

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
	void updateAllSemiFinalPredictionPoints(String team1,String team2,String team3,String team4,int successPoints,int failurePoints);
}
