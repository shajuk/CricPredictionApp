package com.prediction.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prediction.app.model.Matches;

/**
 * @author Shaju K
 *
 */

public interface MatchRepository extends JpaRepository<Matches, Integer> {

}
