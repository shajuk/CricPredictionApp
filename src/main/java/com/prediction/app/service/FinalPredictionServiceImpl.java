/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Finalprediction;
import com.prediction.app.model.User;
import com.prediction.app.repository.FinalPredictionRepository;

/**
 * @author Shaju K
 *
 */

@Service("finalPredictionService")
public class FinalPredictionServiceImpl implements FinalPredictionService {
	
	@Autowired
	FinalPredictionRepository finalPredictionRepository;
	
	@Override
	public Finalprediction findFinalPredictionByUser(User user) {
		return finalPredictionRepository.findById(user.getId()).orElse(null);
	}

	@Override
	public void saveFinalPrediction(Finalprediction finalprediction) {
		finalPredictionRepository.saveAndFlush(finalprediction);
	}

	@Override
	public List<Finalprediction> findAllFinalPredictions() {
		return finalPredictionRepository.findAll();
	}

}
