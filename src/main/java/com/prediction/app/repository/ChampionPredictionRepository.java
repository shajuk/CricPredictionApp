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

import com.prediction.app.model.Championprediction;

/**
 * @author Shaju K
 *
 */
@Repository("championPredictionRepository")
public interface ChampionPredictionRepository extends JpaRepository<Championprediction, Integer> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "call updateAllChampionPredictions(:championIn,:successPointsIn,:failurePointsIn)")
	void updateAllChampionPredictionPoints(@Param("championIn") String champion, @Param("successPointsIn") int successPoints, @Param("failurePointsIn") int failurePoints);

}
