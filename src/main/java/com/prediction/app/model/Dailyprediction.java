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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DailypredictionId id;
	private Game game;
	private User user;
	private String prediction;
	private int points;
	
	public Dailyprediction() {
	}

	public Dailyprediction(DailypredictionId id, Game game, User user,
			String prediction) {
		this.id = id;
		this.game = game;
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
	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
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

	@Column(name = "points", nullable = false)
	public int getPoints() {
		return this.points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

}
