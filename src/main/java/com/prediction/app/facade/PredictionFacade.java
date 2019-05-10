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
import org.springframework.validation.BindingResult;

import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Game;
import com.prediction.app.model.Prediction;
import com.prediction.app.model.PredictionForm;
import com.prediction.app.model.User;
import com.prediction.app.service.DailyPredictionService;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.UserService;
import com.prediction.app.utils.PredictionAppUtils;

/**
 * @author Shaju K
 *
 */

@Component("predictionFacade")
public class PredictionFacade {

	@Autowired
	MatchService matchService;
	
	@Autowired
	DailyPredictionService dailyPredictionService;
	
	@Autowired
	PredictionAppUtils predictionAppUtils;
	
	
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
		}
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
			if(predictionAppUtils.isPredictionFreezed(prediction.getMatchDate())){
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
	
}
