/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Finalprediction;

/**
 * @author Shaju K
 *
 */
@Repository("finalPredictionRepository")
public interface FinalPredictionRepository extends JpaRepository<Finalprediction, Integer> {

}
