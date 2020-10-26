package com.perfectcherry.dto;

import java.io.Serializable;

public class InterestDTO implements Serializable {

	private static final long serialVersionUID = 1627281797104661716L;

	private Long interestId;

	private Long userId;

	private Long interestedOn;

	private String status;

	private String errorMessage;

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((interestId == null) ? 0 : interestId.hashCode());
		result = prime * result + ((interestedOn == null) ? 0 : interestedOn.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestDTO other = (InterestDTO) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (interestId == null) {
			if (other.interestId != null)
				return false;
		} else if (!interestId.equals(other.interestId))
			return false;
		if (interestedOn == null) {
			if (other.interestedOn != null)
				return false;
		} else if (!interestedOn.equals(other.interestedOn))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterestDTO [interestId=" + interestId + ", userId=" + userId + ", interestedOn=" + interestedOn
				+ ", status=" + status + ", errorMessage=" + errorMessage + "]";
	}

}
