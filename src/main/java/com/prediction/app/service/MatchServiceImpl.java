package com.prediction.app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.prediction.app.model.Matches;
import com.prediction.app.repository.MatchRepository;

/**
 * @author Shaju K
 *
 */

public class MatchServiceImpl implements MatchService{

	@Autowired
	private MatchRepository matchRepository;
	
	@Override
	public Matches findMatchByMatchNo(Integer matchNo) {
		return matchRepository.findById(matchNo).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void saveMatch(Matches match) {
		matchRepository.saveAndFlush(match);		
	}

	@Override
	public void updateMatch(Matches match) {
		matchRepository.saveAndFlush(match);
	}

	@Override
	public List<Matches> findAllMatches() {
		return matchRepository.findAll(Sort.by(Sort.Direction.ASC, "matchNo"));
	}

	@Override
	public void saveAllMatches(List<Matches> matches) {
		matchRepository.saveAll(matches);
		matchRepository.flush();
	}

}
