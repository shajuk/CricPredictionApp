package com.prediction.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Game;

import java.util.Date;
import java.util.List;


/**
 * @author Shaju K
 *
 */

@Repository("matchRepository")
public interface MatchRepository extends JpaRepository<Game, Integer> {
	//List<Game> findByMatchDate(@Temporal(TemporalType.DATE) Date date);
	List<Game> findByMatchDateBetween(Date dateBegin, Date dateEnd);
}
