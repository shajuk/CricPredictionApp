/**
 * 
 */
package com.prediction.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Scoretable;
import com.prediction.app.model.User;
import com.prediction.app.repository.ScoreTableRepository;

/**
 * @author Shaju K
 *
 */
@Service("scoreService")
public class ScoreTableServiceImpl implements ScoreTableService {

	@Autowired
	ScoreTableRepository scoreTableRepository;
	
	@Override
	public Scoretable findScoreByUser(User user) {
		return scoreTableRepository.findById(user.getId()).orElse(null);
	}

	@Override
	public void saveScore(Scoretable scoreTable) {
		scoreTableRepository.saveAndFlush(scoreTable);
	}
	
	@Override
	public List<Scoretable> findAllScores() {
		return scoreTableRepository.findAll();
	}

}
