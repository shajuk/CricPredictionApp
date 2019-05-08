package com.prediction.app.model;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Shaju K
 *
 */

@Entity
@Table(name = "game", catalog = "cricapp")
public class Game implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int matchNo;
	private String team1;
	private String team2;
	private Date matchDate;
	private String venue;
	private String matchResult;
	private Set<Dailyprediction> dailypredictions = new HashSet<Dailyprediction>(
			0);

	public Game() {
	}

	public Game(int matchNo, String team1, String team2,Date matchDate, String venue,
			String matchResult) {
		this.matchNo = matchNo;
		this.team1 = team1;
		this.team2 = team2;
		this.matchDate = matchDate;
		this.venue = venue;
		this.matchResult = matchResult;
	}

	public Game(int matchNo, String team1, String team2, Date matchDate,
			String venue, String matchResult, Set<Dailyprediction> dailypredictions) {
		this.matchNo = matchNo;
		this.team1 = team1;
		this.team2 = team2;
		this.matchDate = matchDate;
		this.venue = venue;
		this.matchResult = matchResult;
		this.dailypredictions = dailypredictions;
	}

	@Id
	@Column(name = "match_no", unique = true, nullable = false)
	public int getMatchNo() {
		return this.matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	@Column(name = "team1", nullable = false, length = 40)
	public String getTeam1() {
		return this.team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	@Column(name = "team2", nullable = false, length = 40)
	public String getTeam2() {
		return this.team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "match_date", length = 0)
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	@Column(name = "venue", nullable = false)
	public String getVenue() {
		return this.venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Column(name = "match_result", nullable = false, length = 40)
	public String getMatchResult() {
		return matchResult;
	}
	
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
	public Set<Dailyprediction> getDailypredictions() {
		return this.dailypredictions;
	}

	public void setDailypredictions(Set<Dailyprediction> dailypredictions) {
		this.dailypredictions = dailypredictions;
	}

}
