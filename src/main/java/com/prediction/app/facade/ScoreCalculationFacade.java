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
					
					updateScoreForDailyPredictions(matchResult,
							matchToBeUpdated);
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

	/**
	 * @param matchResult
	 * @param matchToBeUpdated
	 */
	private void updateScoreForDailyPredictions(
			Map.Entry<String, String> matchResult, Game matchToBeUpdated) {
		//Updating the Score table for Dailypredictions starts
		if(!(matchResult.getValue().equals("NORESULT"))){
			for(Dailyprediction dp:matchToBeUpdated.getDailypredictions()){
				System.out.println(" "+dp.getUser().getUsername()+" "+dp.getPrediction());
				Scoretable scoretable=scoreService.findScoreByUser(dp.getUser());
				if(null == scoretable){
					scoretable=new Scoretable();
					scoretable.setUser(dp.getUser());
				}
				scoretable.setHistoryScore(scoretable.getTotalScore());
				if(matchResult.getValue().equals(dp.getPrediction())){
					scoretable.setTotalScore(scoretable.getTotalScore()+DAILY_PREDICTION_SUCCESS_SCORE);
				}else{
					scoretable.setTotalScore(scoretable.getTotalScore()+DAILY_PREDICTION_FAILURE_SCORE);
				}
				scoreService.saveScore(scoretable);
			}
		}
	}
}
