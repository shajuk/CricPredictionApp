/**
 * 
 */
package com.prediction.app.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Scoretable;

/**
 * @author Shaju K
 *
 */
@Repository("scoreTableRepository")
public interface ScoreTableRepository extends JpaRepository<Scoretable, Integer> {
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "call updateAllScores()")   // call store procedure with arguments
    void updateAllScores();
}
