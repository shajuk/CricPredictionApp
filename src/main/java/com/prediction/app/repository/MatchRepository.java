package com.prediction.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.Game;


/**
 * @author Shaju K
 *
 */

@Repository("matchRepository")
public interface MatchRepository extends JpaRepository<Game, Integer> {

}
