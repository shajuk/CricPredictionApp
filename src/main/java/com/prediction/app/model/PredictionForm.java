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
	
	
}
