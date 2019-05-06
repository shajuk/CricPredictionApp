/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Game;
import com.prediction.app.model.User;

import java.util.List;

import javax.transaction.Transactional;


/**
 * @author Shaju K
 *
 */

@Repository("dailyPredictionRepository")
public interface DailyPredictionRepository extends JpaRepository<Dailyprediction, DailypredictionId>{
	List<Dailyprediction> findByUser(User user);
	List<Dailyprediction> findByGame(Game game);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "call updateDailyPredictionByMatch(:matchNoIn,:successPointsIn,:failurePointsIn,:matchResultIn)")   // call store procedure with arguments
	void updateDailyPredictionByMatch(@Param("matchNoIn")int matchNo, @Param("successPointsIn") int successPoints, @Param("failurePointsIn") int failurePoints, @Param("matchResultIn") String matchResult);
}
