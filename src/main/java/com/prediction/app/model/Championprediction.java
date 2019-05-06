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
@Table(name = "championprediction", catalog = "cricapp")
public class Championprediction implements java.io.Serializable {

	private int userid;
	private User user;
	private String prediction;
	private int points;

	public Championprediction() {
	}

	public Championprediction(User user, String prediction) {
		this.user = user;
		this.prediction = prediction;
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
