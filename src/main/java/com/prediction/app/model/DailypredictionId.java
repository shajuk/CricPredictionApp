package com.prediction.app.model;



import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Shaju K
 *
 */

@Embeddable
public class DailypredictionId implements java.io.Serializable {

	private int userid;
	private int matchNo;

	public DailypredictionId() {
	}

	public DailypredictionId(int userid, int matchNo) {
		this.userid = userid;
		this.matchNo = matchNo;
	}

	@Column(name = "userid", nullable = false)
	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Column(name = "match_no", nullable = false)
	public int getMatchNo() {
		return this.matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DailypredictionId))
			return false;
		DailypredictionId castOther = (DailypredictionId) other;

		return (this.getUserid() == castOther.getUserid())
				&& (this.getMatchNo() == castOther.getMatchNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserid();
		result = 37 * result + this.getMatchNo();
		return result;
	}

}
