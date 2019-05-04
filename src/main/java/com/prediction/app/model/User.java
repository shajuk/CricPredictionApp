package com.prediction.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * @author Shaju K
 *
 */

@Entity
@Table(name = "user",  uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="password")
	private String password;
	
	@Column(name="active")
	private int active;
	
	@Column(name="location")
	private String location;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Scoretable scoretable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Dailyprediction> dailypredictions = new HashSet<Dailyprediction>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Championprediction championprediction;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Semifinalprediction semifinalprediction;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Finalprediction finalprediction;
	
	public Scoretable getScoretable() {
		return scoretable;
	}

	public void setScoretable(Scoretable scoretable) {
		this.scoretable = scoretable;
	}

	public Set<Dailyprediction> getDailypredictions() {
		return dailypredictions;
	}

	public void setDailypredictions(Set<Dailyprediction> dailypredictions) {
		this.dailypredictions = dailypredictions;
	}

	public Championprediction getChampionprediction() {
		return championprediction;
	}

	public void setChampionprediction(Championprediction championprediction) {
		this.championprediction = championprediction;
	}

	public Semifinalprediction getSemifinalprediction() {
		return semifinalprediction;
	}

	public void setSemifinalprediction(Semifinalprediction semifinalprediction) {
		this.semifinalprediction = semifinalprediction;
	}

	public Finalprediction getFinalprediction() {
		return finalprediction;
	}

	public void setFinalprediction(Finalprediction finalprediction) {
		this.finalprediction = finalprediction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
