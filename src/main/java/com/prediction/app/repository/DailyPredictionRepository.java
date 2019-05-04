/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Game;
import com.prediction.app.model.User;

import java.util.List;


/**
 * @author Shaju K
 *
 */

@Repository("dailyPredictionRepository")
public interface DailyPredictionRepository extends JpaRepository<Dailyprediction, DailypredictionId>{
	List<Dailyprediction> findByUser(User user);
	List<Dailyprediction> findByGame(Game game);
}
