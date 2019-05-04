package com.prediction.app.model;



import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Shaju K
 *
 */

@Entity
@Table(name = "persistent_logins", catalog = "cricapp")
public class PersistentLogins implements java.io.Serializable {

	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	public PersistentLogins() {
	}

	public PersistentLogins(String series, String username, String token,
			Date lastUsed) {
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	@Id
	@Column(name = "series", unique = true, nullable = false)
	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "token", nullable = false)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used", nullable = false, length = 0)
	public Date getLastUsed() {
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
