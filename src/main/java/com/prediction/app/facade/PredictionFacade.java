/**
 * 
 */
package com.prediction.app.facade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import com.prediction.app.model.Championprediction;
import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Finalprediction;
import com.prediction.app.model.Game;
import com.prediction.app.model.Prediction;
import com.prediction.app.model.PredictionForm;
import com.prediction.app.model.Semifinalprediction;
import com.prediction.app.model.User;
import com.prediction.app.service.ChampionPredictionService;
import com.prediction.app.service.DailyPredictionService;
import com.prediction.app.service.FinalPredictionService;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.SemiFinalPredictionService;
import com.prediction.app.service.UserService;
import com.prediction.app.utils.PredictionAppUtils;

/**
 * @author Shaju K
 *
 */

@Component("predictionFacade")
public class PredictionFacade {

	private static String MATCH_BEGIN_DATE = "13/05/2019 00:00:00";
	private static String MATCH_END_DATE = "14/07/2019 23:59:59";	
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	DailyPredictionService dailyPredictionService;
	
	@Autowired
	PredictionAppUtils predictionAppUtils;
	
	@Autowired
	SemiFinalPredictionService semiFinalPredictionService;
	
	@Autowired
	FinalPredictionService finalPredictionService;
	
	@Autowired
	ChampionPredictionService championPredictionService;
	
	public void prepareAndGetPredictionForm(PredictionForm form) {
		form.getPredictions().clear();
		List<Game> latestMatches=matchService.findMatchByMatchDateBetween(predictionAppUtils.getBeginDateTimeToday(), predictionAppUtils.getEndDateTimeToday());
		for(Game game:latestMatches){
			Prediction prediction=new Prediction();
			Dailyprediction dp=dailyPredictionService.findDailyPredictionByDailyPrediction(form.getUser().getId(),game.getMatchNo());
			if(null!=dp){
				prediction.setChoice(dp.getPrediction());
			}
			prediction.setMatchDate(game.getMatchDate());
			prediction.setMatchNo(game.getMatchNo());
			prediction.setTeam1(game.getTeam1());
			prediction.setTeam2(game.getTeam2());
			prediction.setVenue(game.getVenue());
			form.getPredictions().add(prediction);
			form.setMainPredictionFreezed(isMainPredictionFreezed(game.getMatchNo()));
		}
		
		List<String> teams=matchService.getAllTeams();
		form.getTeams().addAll(teams);
	}


	/**
	 * @param predictionForm
	 * @param bindingResult
	 */
	public void validateForm(PredictionForm predictionForm,
			BindingResult bindingResult) {
		boolean isEmptyChoice=false;
		boolean isPredictionFrozen=false;
		for(Prediction prediction:predictionForm.getPredictions()){
			if(predictionAppUtils.isNullOrEmpty(prediction.getChoice())){
				isEmptyChoice=true;
			}
			if(predictionAppUtils.isDailyPredictionFreezed(prediction.getMatchDate())){
				isPredictionFrozen=true;
			}
		}
		if(isEmptyChoice){
			bindingResult.reject("error.choice","Please select your desired option for all the below matches.");
			return;
		}
		if(isPredictionFrozen){
			bindingResult.reject("error.predictionfreezed","Sorry for the inconvenience, predictions are freezed now.");
		}
		
	}


	/**
	 * @param predictionForm
	 */
	public void saveDailyPredictions(@Valid PredictionForm predictionForm) {
		for(Prediction prediction:predictionForm.getPredictions()){
			Dailyprediction dailyPrediction=dailyPredictionService.findDailyPredictionByDailyPrediction(predictionForm.getUser().getId(), prediction.getMatchNo());
			if(null == dailyPrediction){
				DailypredictionId dpId=new DailypredictionId(predictionForm.getUser().getId(), prediction.getMatchNo());
				Game game=matchService.findMatchByMatchNo(prediction.getMatchNo());
				dailyPrediction=new Dailyprediction(dpId, game, predictionForm.getUser(), prediction.getChoice());
			}else{
				dailyPrediction.setPrediction(prediction.getChoice());
			}
			dailyPredictionService.saveDailyPrediction(dailyPrediction);
		}
	}

	private boolean isMainPredictionFreezed(int matchNo) {
		return matchNo > 22 ? true: false;
	}
	
	public void validateSemiFinalists(@Valid PredictionForm predictionForm, BindingResult bindingResult) {
		if(predictionForm.getSelectedSemiFinalists().size() < 4) {
			bindingResult.reject("error.semiunderselection","Please select 4 teams!");
		}
		
		if(predictionForm.getSelectedSemiFinalists().size() > 4) {
			bindingResult.reject("error.semioverselection","Please select only 4 teams!");
		}
	}
	
	public void validateFinalists(@Valid PredictionForm predictionForm, BindingResult bindingResult) {
		if(predictionForm.getSelectedFinalists().size() < 2) {
			bindingResult.reject("error.finalunderselection","Please select 2 teams!");
		}
		
		if(predictionForm.getSelectedFinalists().size() > 2) {
			bindingResult.reject("error.finaloverselection","Please select only 2 teams!");
		}
	}

	public void validateChampion(@Valid PredictionForm predictionForm, BindingResult bindingResult) {
		if(predictionAppUtils.isNullOrEmpty(predictionForm.getSelectedChampion())) {
			bindingResult.reject("error.emptychampselection","Please select your favourite team!");
		}
	}

	public void saveSemiFinalPredictions(@Valid PredictionForm predictionForm) {
		Semifinalprediction semifinalprediction=semiFinalPredictionService.findSemiFinalPredictionByUser(predictionForm.getUser());
		if(null==semifinalprediction) {
			semiFinalPredictionService.saveSemiFinalPrediction(new Semifinalprediction(predictionForm.getUser(),predictionForm.getSelectedSemiFinalists().get(0),predictionForm.getSelectedSemiFinalists().get(1),predictionForm.getSelectedSemiFinalists().get(2),predictionForm.getSelectedSemiFinalists().get(3)));
		}else {
			semifinalprediction.setTeam1(predictionForm.getSelectedSemiFinalists().get(0));
			semifinalprediction.setTeam2(predictionForm.getSelectedSemiFinalists().get(1));
			semifinalprediction.setTeam3(predictionForm.getSelectedSemiFinalists().get(2));
			semifinalprediction.setTeam4(predictionForm.getSelectedSemiFinalists().get(3));
			semiFinalPredictionService.saveSemiFinalPrediction(semifinalprediction);
		}
		
	}
	
	public void saveFinalPredictions(@Valid PredictionForm predictionForm) {
		Finalprediction finalprediction=finalPredictionService.findFinalPredictionByUser(predictionForm.getUser());
		if(null==finalprediction) {
			finalPredictionService.saveFinalPrediction(new Finalprediction(predictionForm.getUser(),predictionForm.getSelectedFinalists().get(0),predictionForm.getSelectedFinalists().get(1)));
		}else {
			finalprediction.setTeam1(predictionForm.getSelectedFinalists().get(0));
			finalprediction.setTeam2(predictionForm.getSelectedFinalists().get(1));
			finalPredictionService.saveFinalPrediction(finalprediction);
		}
		
	}
	
	public void getMatchFixture(PredictionForm scheduleForm) {
		scheduleForm.getGames().clear();
		List<Game> matches=matchService.findMatchByMatchDateBetween(predictionAppUtils.getMatchBeginDate(MATCH_BEGIN_DATE), predictionAppUtils.getMatchEndDate(MATCH_END_DATE));
		if(!CollectionUtils.isEmpty(matches)) {
			scheduleForm.getGames().addAll(matches);
		}
	}


	public void saveChampionPredictions(@Valid PredictionForm predictionForm) {
		Championprediction championprediction=championPredictionService.findChampionPredictionByUser(predictionForm.getUser());
		if(null==championprediction) {
			championPredictionService.saveChampionPrediction(new Championprediction(predictionForm.getUser(),predictionForm.getSelectedChampion()));
		}else {
			championprediction.setPrediction(predictionForm.getSelectedChampion());
			championPredictionService.saveChampionPrediction(championprediction);
		}
	}
	
}
