/**
 * 
 */
package com.prediction.app.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.Game;
import com.prediction.app.model.Scoretable;
import com.prediction.app.service.ChampionPredictionService;
import com.prediction.app.service.DailyPredictionService;
import com.prediction.app.service.FinalPredictionService;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.ScoreTableService;
import com.prediction.app.service.SemiFinalPredictionService;
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
	SemiFinalPredictionService semiFinalPredictionService;
	
	@Autowired
	FinalPredictionService finalPredictionService;
	
	@Autowired
	ChampionPredictionService championPredictionService;
	
	@Autowired
	DailyPredictionService dailyPredictionService;
	
	@Autowired
	ScoreTableService scoreService;
	
	private static int DAILY_PREDICTION_SUCCESS_POINTS=10;
	private static int DAILY_PREDICTION_FAILURE_POINTS=-3;
	private static int MATCH_NO_PRIOR_TO_SEMI_FINALS=45;
	private static int MATCH_NO_FOR_SEMI_FINAL_ONE=46;
	private static int MATCH_NO_FOR_SEMI_FINAL_TWO=47;
	private static int MATCH_NO_FOR_FINALS=48;
	private static String SEMI_FINAL_TEAMONE_PLACEHOLDER="1ST";
	private static String SEMI_FINAL_TEAMTWO_PLACEHOLDER="2ND";
	private static String FINAL_TEAM_NAME_PLACEHOLDER="TBC";
	private static int SEMI_FINAL_PREDICTION_SUCCESS_POINTS=25;
	private static int SEMI_FINAL_PREDICTION_FAILURE_POINTS=-5;
	private static int FINAL_PREDICTION_SUCCESS_POINTS=50;
	private static int FINAL_PREDICTION_FAILURE_POINTS=-10;
	private static int CHAMPION_PREDICTION_SUCCESS_POINTS=100;
	private static int CHAMPION_PREDICTION_FAILURE_POINTS=-20;
	
	public void updateAllScores(){
		//call some api and get match result into Map
		Map<String,String> matchResults=new HashMap<>();
		//putting match no and its result
		matchResults.put("44", "INDIA");
		matchResults.put("45", "SOUTH AFRICA");
		matchResults.put("46", "INDIA");
		matchResults.put("47", "SOUTH AFRICA");
		matchResults.put("48", "INDIA");
		
		Map<String,Map<String,String>> semiFinalTeams=new HashMap<>();
		Map<String,String> semiFina1One=new HashMap<>();
		semiFina1One.put("semiFinalTeam1", "INDIA");
		semiFina1One.put("semiFinalTeam2", "PAKISTAN");
		semiFinalTeams.put("semiFina1One", semiFina1One);
		Map<String,String> semiFina1Two=new HashMap<>();
		semiFina1Two.put("semiFinalTeam3", "ENGLAND");
		semiFina1Two.put("semiFinalTeam4", "SOUTH AFRICA");
		semiFinalTeams.put("semiFina1Two", semiFina1Two);
		
		Map<String,String> finalTeams=new HashMap<>();
		finalTeams.put("finalTeam1", "INDIA");
		finalTeams.put("finalTeam2", "SOUTH AFRICA");
		
		//Find latest matches
		List<Game> latestMatches=matchService.findMatchByMatchDateBetween(predictionAppUtils.getYesterdayBeginDateTime(), predictionAppUtils.getYesterdayEndDateTime());
		for (Map.Entry<String, String> matchResult : matchResults.entrySet()) {
			for(Game match:latestMatches){
				if(match.getMatchNo() == Integer.valueOf(matchResult.getKey())){
					
					//Update db for Semi final Predictions
					if((match.getMatchNo() == MATCH_NO_PRIOR_TO_SEMI_FINALS)){
						updateTeamDetailsForSemiFinals(semiFinalTeams);
						updateScoresForSemiFinalPredictions(semiFinalTeams);
					}
					
					//Update db for final Predictions
					if((match.getMatchNo() == MATCH_NO_FOR_SEMI_FINAL_TWO)){
						updateTeamDetailsForFinals(finalTeams);
						updateScoresForFinalPredictions(finalTeams);
					}
					
					//Update db for Champion Predictions
					if((match.getMatchNo() == MATCH_NO_FOR_FINALS)){
						updateScoresForChampionPrediction(matchResult.getValue());
					}
					
					//Update db for Daily Predictions
					Game matchToBeUpdated=matchService.findMatchByMatchNo(match.getMatchNo());
					updateMatchResultForLatestMatch(matchResult,matchToBeUpdated);
					updateScoreForDailyPredictions(matchToBeUpdated.getMatchNo(),matchResult.getValue());
					updateAllTotalScores();
				}
			}
		}
	}

	/**
	 * 
	 */
	private void updateAllTotalScores() {
		scoreService.updateAllScores();
	}

	/**
	 * @param value
	 */
	private void updateScoresForChampionPrediction(String winner) {
		championPredictionService.updateAllChampionPredictionPoints(winner, CHAMPION_PREDICTION_SUCCESS_POINTS, CHAMPION_PREDICTION_FAILURE_POINTS);
	}

	/**
	 * @param finalTeams
	 */
	public void updateTeamDetailsForFinals(Map<String, String> finalTeams) {
		Game finals=matchService.findMatchByMatchNo(MATCH_NO_FOR_FINALS);
		if(teamsNotExistsForFinals(finals)){
			String team1=finalTeams.get("finalTeam1");
			String team2=finalTeams.get("finalTeam2");
			// update final teams
			finals.setTeam1(team1);
			finals.setTeam2(team2);
			matchService.updateMatch(finals);
		}
	}

	/**
	 * @param finals
	 * @return
	 */
	private boolean teamsNotExistsForFinals(Game finals) {
		// TODO Auto-generated method stub
		return finals.getTeam1().equals(FINAL_TEAM_NAME_PLACEHOLDER) 
				&& finals.getTeam2().equals(FINAL_TEAM_NAME_PLACEHOLDER);
	}

	/**
	 * @param finalTeams
	 */
	public void updateScoresForFinalPredictions(Map<String, String> finalTeams) {
		String team1=finalTeams.get("finalTeam1");
		String team2=finalTeams.get("finalTeam2");
		finalPredictionService.updateAllFinalPredictionPoints(team1, team2, FINAL_PREDICTION_SUCCESS_POINTS, FINAL_PREDICTION_FAILURE_POINTS);
	}

	/**
	 * @param semiFinalTeams
	 */
	private void updateScoresForSemiFinalPredictions(
			Map<String, Map<String, String>> semiFinalTeams) {
		Map<String, String> semiFina1MachOne=(Map<String, String>)semiFinalTeams.get("semiFina1One");
		String team1=semiFina1MachOne.get("semiFinalTeam1");
		String team2=semiFina1MachOne.get("semiFinalTeam2");
		Map<String, String> semiFina1MachTwo=(Map<String, String>)semiFinalTeams.get("semiFina1Two");
		String team3=semiFina1MachTwo.get("semiFinalTeam3");
		String team4=semiFina1MachTwo.get("semiFinalTeam4");
		semiFinalPredictionService.updateAllSemiFinalPredictionPoints(team1, team2, team3, team4, SEMI_FINAL_PREDICTION_SUCCESS_POINTS, SEMI_FINAL_PREDICTION_FAILURE_POINTS);
	}

	/**
	 * @param semiFinalTeams
	 * This method checks if the semi final teams names are already updated if not updates the team details
	 */
	public void updateTeamDetailsForSemiFinals(Map<String, Map<String, String>> semiFinalTeams) {
		
		Game firstSemiFinal=matchService.findMatchByMatchNo(MATCH_NO_FOR_SEMI_FINAL_ONE);
		Game secondSemiFinal=matchService.findMatchByMatchNo(MATCH_NO_FOR_SEMI_FINAL_TWO);
		if(semiFinalTeamsNotExists(firstSemiFinal, secondSemiFinal)){
			Map<String, String> semiFina1MachOne=(Map<String, String>)semiFinalTeams.get("semiFina1One");
			String team1=semiFina1MachOne.get("semiFinalTeam1");
			String team2=semiFina1MachOne.get("semiFinalTeam2");
			Map<String, String> semiFina1MachTwo=(Map<String, String>)semiFinalTeams.get("semiFina1Two");
			String team3=semiFina1MachTwo.get("semiFinalTeam3");
			String team4=semiFina1MachTwo.get("semiFinalTeam4");
			
			// update semi final1 teams
			firstSemiFinal.setTeam1(team1);
			firstSemiFinal.setTeam2(team2);
			//update semi final2 teams
			secondSemiFinal.setTeam1(team3);
			secondSemiFinal.setTeam2(team4);
			List<Game> semiFinalists=new ArrayList<Game>();
			semiFinalists.add(firstSemiFinal);
			semiFinalists.add(secondSemiFinal);
			matchService.saveAllMatches(semiFinalists);
		}
		
		
	}

	/**
	 * @param firstSemiFinal
	 * @param secondSemiFinal
	 * @return
	 */
	private boolean semiFinalTeamsNotExists(Game firstSemiFinal,
			Game secondSemiFinal) {
		return firstSemiFinal.getTeam1().equals(SEMI_FINAL_TEAMONE_PLACEHOLDER) 
				&& secondSemiFinal.getTeam1().equals(SEMI_FINAL_TEAMTWO_PLACEHOLDER);
	}

	/**
	 * @param team1
	 * @return
	 */
	

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
			dailyPredictionService.updateDailyPredictionPoints(matchNo,DAILY_PREDICTION_SUCCESS_POINTS,DAILY_PREDICTION_FAILURE_POINTS,matchResult);
			
		}
	}
}
