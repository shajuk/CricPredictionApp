/**
 * 
 */
package com.prediction.app.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.Game;
import com.prediction.app.model.Scoretable;
import com.prediction.app.service.DailyPredictionService;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.ScoreTableService;
import com.prediction.app.utils.PredictionAppUtils;

/**
 * @author Shaju K
 *
 */

@Component("scoreCalculationFacade")
public class ScoreCalculationFacade {
	
	@Autowired
	PredictionAppUtils predictionAppUtils;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	ScoreTableService scoreService;
	
	@Autowired
	DailyPredictionService dailyPredictionService;
	
	private static int DAILY_PREDICTION_SUCCESS_SCORE=10;
	private static int DAILY_PREDICTION_FAILURE_SCORE=-3;
	
	public void updateAllScores(){
		//call some api and get match result into Map
		Map<String,String> matchResults=new HashMap<>();
		matchResults.put("1", "ENGLAND");
		matchResults.put("2", "WEST INDIES");
		
		//Find latest matches
		List<Game> latestMatches=matchService.findMatchByMatchDateBetween(predictionAppUtils.getYesterdayBeginDateTime(), predictionAppUtils.getYesterdayEndDateTime());
		for (Map.Entry<String, String> matchResult : matchResults.entrySet()) {
			for(Game match:latestMatches){
				if(match.getMatchNo() == Integer.valueOf(matchResult.getKey())){
					
					Game matchToBeUpdated=matchService.findMatchByMatchNo(match.getMatchNo());
					
					updateMatchResultForLatestMatch(matchResult,
							matchToBeUpdated);
					
					updateScoreForDailyPredictions(matchToBeUpdated.getMatchNo(),matchResult.getValue());
				}
			}
		}
	}

	/**
	 * @param matchResult
	 * @param matchToBeUpdated
	 */
	private void updateMatchResultForLatestMatch(
			Map.Entry<String, String> matchResult, Game matchToBeUpdated) {
		matchToBeUpdated.setMatchResult(matchResult.getValue());
		matchService.updateMatch(matchToBeUpdated);
	}


	private void updateScoreForDailyPredictions(
			int matchNo, String matchResult) {
		//Updating the Score table for Dailypredictions starts
		if(!(matchResult.equals("NORESULT"))){
			dailyPredictionService.updateDailyPredictionPoints(matchNo,DAILY_PREDICTION_SUCCESS_SCORE,DAILY_PREDICTION_FAILURE_SCORE,matchResult);
		}
	}
}
