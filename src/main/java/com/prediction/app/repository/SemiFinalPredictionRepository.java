/**
 * 
 */
package com.prediction.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Semifinalprediction;

/**
 * @author Shaju K
 *
 */

@Repository("semiFinalPredictionRepository")
public interface SemiFinalPredictionRepository extends JpaRepository<Semifinalprediction, Integer> {

}
