package com.prediction.app.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Shaju K
 *
 */

@Entity
@Table(name = "scoretable", catalog = "cricapp")
public class Scoretable implements java.io.Serializable {

	private int userid;
	private User user;
	private int totalScore;
	private int historyScore;

	public Scoretable() {
	}

	public Scoretable(User user, int totalScore, int historyScore) {
		this.user = user;
		this.totalScore = totalScore;
		this.historyScore = historyScore;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userid", unique = true, nullable = false)
	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "total_score", nullable = false)
	public int getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	@Column(name = "history_score", nullable = false)
	public int getHistoryScore() {
		return this.historyScore;
	}

	public void setHistoryScore(int historyScore) {
		this.historyScore = historyScore;
	}

}
