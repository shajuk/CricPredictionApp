package com.prediction.app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prediction.app.model.Game;
import com.prediction.app.repository.MatchRepository;

/**
 * @author Shaju K
 *
 */

@Service("matchService")
public class MatchServiceImpl implements MatchService{

	@Autowired
	private MatchRepository matchRepository;
	
	@Override
	public Game findMatchByMatchNo(Integer matchNo) {
		return matchRepository.findById(matchNo).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void saveMatch(Game match) {
		matchRepository.saveAndFlush(match);		
	}

	@Override
	public void updateMatch(Game match) {
		matchRepository.saveAndFlush(match);
	}

	@Override
	public List<Game> findAllMatches() {
		return matchRepository.findAll(Sort.by(Sort.Direction.ASC, "matchNo"));
	}

	@Override
	public void saveAllMatches(List<Game> matches) {
		matchRepository.saveAll(matches);
		matchRepository.flush();
	}

}
