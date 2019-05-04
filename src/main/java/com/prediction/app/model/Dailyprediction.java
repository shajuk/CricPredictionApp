package com.prediction.app.model;



import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Shaju K
 *
 */

@Entity
@Table(name = "dailyprediction", catalog = "cricapp")
public class Dailyprediction implements java.io.Serializable {

	private DailypredictionId id;
	private Matches matches;
	private User user;
	private String prediction;

	public Dailyprediction() {
	}

	public Dailyprediction(DailypredictionId id, Matches matches, User user,
			String prediction) {
		this.id = id;
		this.matches = matches;
		this.user = user;
		this.prediction = prediction;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userid", column = @Column(name = "userid", nullable = false)),
			@AttributeOverride(name = "matchNo", column = @Column(name = "match_no", nullable = false)) })
	public DailypredictionId getId() {
		return this.id;
	}

	public void setId(DailypredictionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_no", nullable = false, insertable = false, updatable = false)
	public Matches getMatches() {
		return this.matches;
	}

	public void setMatches(Matches matches) {
		this.matches = matches;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "prediction", nullable = false, length = 40)
	public String getPrediction() {
		return this.prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}

}
