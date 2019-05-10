/**
 * 
 */
package com.prediction.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Shaju K
 *
 */
public class Prediction implements Serializable {


	private static final long serialVersionUID = 1L;
	private int matchNo;
	private String team1;
	private String team2;
	private Date matchDate;
	private String venue;
	private String choice;
	
	public int getMatchNo() {
		return matchNo;
	}
	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	

}
