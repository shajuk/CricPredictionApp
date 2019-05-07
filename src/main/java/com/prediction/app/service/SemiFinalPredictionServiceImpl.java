/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Semifinalprediction;
import com.prediction.app.model.User;
import com.prediction.app.repository.SemiFinalPredictionRepository;

/**
 * @author Shaju K
 *
 */
@Service("semiFinalPredictionService")
public class SemiFinalPredictionServiceImpl implements
		SemiFinalPredictionService {

	@Autowired
	private SemiFinalPredictionRepository semiFinalPredictionRepository;
	
	@Override
	public Semifinalprediction findSemiFinalPredictionByUser(User user) {
		return semiFinalPredictionRepository.findById(user.getId()).orElse(null);
	}

	@Override
	public void saveSemiFinalPrediction(Semifinalprediction semifinalprediction) {
		semiFinalPredictionRepository.saveAndFlush(semifinalprediction);
	}
	
	@Override
	public List<Semifinalprediction> findAllSemiFinalPredictions() {
		return semiFinalPredictionRepository.findAll(Sort.by(Sort.Direction.ASC,"userid"));
	}
	
	@Override
	public void updateAllSemiFinalPredictionPoints(String team1, String team2,
			String team3, String team4, int successPoints) {
		 semiFinalPredictionRepository.updateAllSemiFinalPredictionPoints(team1, team2, team3, team4, successPoints);
	}
}
