/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Championprediction;

/**
 * @author Shaju K
 *
 */
@Repository("championPredictionRepository")
public interface ChampionPredictionRepository extends JpaRepository<Championprediction, Integer> {

}
