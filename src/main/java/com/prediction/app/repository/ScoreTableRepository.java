/**
 * 
 */
package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Scoretable;

/**
 * @author Shaju K
 *
 */
@Repository("scoreTableRepository")
public interface ScoreTableRepository extends JpaRepository<Scoretable, Integer> {

}
