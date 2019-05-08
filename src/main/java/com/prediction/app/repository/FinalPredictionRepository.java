/**
 * 
 */
package com.prediction.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Finalprediction;

/**
 * @author Shaju K
 *
 */
@Repository("finalPredictionRepository")
public interface FinalPredictionRepository extends JpaRepository<Finalprediction, Integer> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "call updateAllFinalPredictions(:team1In,:team2In,:successPointsIn,:failurePointsIn)")   // call store procedure with arguments
	void updateAllFinalPredictionPoints(@Param("team1In") String team1,@Param("team2In") String team2, @Param("successPointsIn") int successPoints, @Param("failurePointsIn") int failurePoints);

}
