package com.prediction.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

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
	@Query(nativeQuery = true,value = "call getMatchByVenue(:venueIn,:teamNo2)")   // call store procedure with arguments
    List<Game> getMatchByVenue(@Param("venueIn")String venue,@Param("teamNo2")String team2);
	
	@Query(nativeQuery = true,value = "SELECT distinct team1 FROM game where team1 not in ('1ST','2ND','TBC') order by team1")   // call store procedure with arguments
    List<String> getAllTeams();
}
