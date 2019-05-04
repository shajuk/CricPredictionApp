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
@Table(name = "finalprediction", catalog = "cricapp")
public class Finalprediction implements java.io.Serializable {

	private int userid;
	private User user;
	private String team1;
	private String team2;

	public Finalprediction() {
	}

	public Finalprediction(User user, String team1, String team2) {
		this.user = user;
		this.team1 = team1;
		this.team2 = team2;
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

}
