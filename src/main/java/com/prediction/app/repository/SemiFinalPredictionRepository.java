/**
 * 
 */
package com.prediction.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Semifinalprediction;

/**
 * @author Shaju K
 *
 */

@Repository("semiFinalPredictionRepository")
public interface SemiFinalPredictionRepository extends JpaRepository<Semifinalprediction, Integer> {
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "call updateSemiFinalPrediction(:team1In,:team2In,:team3In,:team4In,:successPointsIn)")   // call store procedure with arguments
	void updateAllSemiFinalPredictionPoints(@Param("team1In") String team1,@Param("team2In") String team2, @Param("team3In") String team3,@Param("team4In") String team4, @Param("successPointsIn") int successPoints);

}
