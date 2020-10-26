package com.perfectcherry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interest")
public class Interest implements Serializable {

	private static final long serialVersionUID = 6850823602386980585L;

	@Id
	@Column(name = "interestid")
	private Long interestId;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "interestedon")
	private Long interestedOn;

	@Column(name = "status")
	private String status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getInterestedOn() {
		return interestedOn;
	}

	public void setInterestedOn(Long interestedOn) {
		this.interestedOn = interestedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((interestId == null) ? 0 : interestId.hashCode());
		result = prime * result + ((interestedOn == null) ? 0 : interestedOn.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Interest other = (Interest) obj;
		if (createdDate == null) {
			if (other.createdDate != null) {
				return false;
			}
		} else if (!createdDate.equals(other.createdDate)) {
			return false;
		}
		if (interestId == null) {
			if (other.interestId != null) {
				return false;
			}
		} else if (!interestId.equals(other.interestId)) {
			return false;
		}
		if (interestedOn == null) {
			if (other.interestedOn != null) {
				return false;
			}
		} else if (!interestedOn.equals(other.interestedOn)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (updatedDate == null) {
			if (other.updatedDate != null) {
				return false;
			}
		} else if (!updatedDate.equals(other.updatedDate)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Interest [interestId=" + interestId + ", userId=" + userId + ", interestedOn=" + interestedOn
				+ ", status=" + status + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
