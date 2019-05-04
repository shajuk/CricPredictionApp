/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.User;
import java.util.List;
import com.prediction.app.model.Matches;

/**
 * @author Shaju K
 *
 */
public interface DailyPredictionRepository extends JpaRepository<Dailyprediction, DailypredictionId>{
	
}
