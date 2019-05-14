/**
 * 
 */
package com.prediction.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaju K
 *
 */
public class PredictionForm {
	private List<Prediction> predictions =new ArrayList<Prediction>();
	private List<String> teams=new ArrayList<String>();
	private List<String> selectedSemiFinalists=new ArrayList<String>();
	private List<String>selectedFinalists=new ArrayList<String>();
	private List<Game> games =new ArrayList<Game>();
	private boolean mainPredictionFreezed;
	private String selectedChampion;
	
	private User user;
	/**
	 * @return the predictions
	 */
	public List<Prediction> getPredictions() {
		return predictions;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<String> getSelectedFinalists() {
		return selectedFinalists;
	}
	public void setSelectedFinalists(List<String> selectedFinalists) {
		this.selectedFinalists = selectedFinalists;
	}
	public String getSelectedChampion() {
		return selectedChampion;
	}
	
	public void setSelectedChampion(String selectedChampion) {
		this.selectedChampion = selectedChampion;
	}
	
	public List<String> getTeams() {
		return teams;
	}
	public List<String> getSelectedSemiFinalists() {
		return selectedSemiFinalists;
	}
	public void setSelectedSemiFinalists(List<String> selectedSemiFinalists) {
		this.selectedSemiFinalists = selectedSemiFinalists;
	}
	public boolean isMainPredictionFreezed() {
		return mainPredictionFreezed;
	}
	public void setMainPredictionFreezed(boolean mainPredictionFreezed) {
		this.mainPredictionFreezed = mainPredictionFreezed;
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public void setGames(List<Game> games) {
		this.games = games;
	}
}
