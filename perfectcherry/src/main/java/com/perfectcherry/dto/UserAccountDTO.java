package com.perfectcherry.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserAccountDTO implements Serializable {

	private static final long serialVersionUID = -1341827384154609085L;

	private Long userId;

	private Long phone;

	private String emailAddress;

	private String userName;

	private String aboutMe;

	private String jobTitle;

	private String company;

	private String education;

	private String livingIn;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String gender;

	private String sexualOrientation;

	private Date dob;

	private String interestedIn;

	private int peopleWithinKm;

	private String errorMessage;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLivingIn() {
		return livingIn;
	}

	public void setLivingIn(String livingIn) {
		this.livingIn = livingIn;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(String sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}

	public int getPeopleWithinKm() {
		return peopleWithinKm;
	}

	public void setPeopleWithinKm(int peopleWithinKm) {
		this.peopleWithinKm = peopleWithinKm;
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
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((interestedIn == null) ? 0 : interestedIn.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((livingIn == null) ? 0 : livingIn.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + peopleWithinKm;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sexualOrientation == null) ? 0 : sexualOrientation.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserAccountDTO other = (UserAccountDTO) obj;
		if (aboutMe == null) {
			if (other.aboutMe != null)
				return false;
		} else if (!aboutMe.equals(other.aboutMe))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (interestedIn == null) {
			if (other.interestedIn != null)
				return false;
		} else if (!interestedIn.equals(other.interestedIn))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (livingIn == null) {
			if (other.livingIn != null)
				return false;
		} else if (!livingIn.equals(other.livingIn))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (peopleWithinKm != other.peopleWithinKm)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sexualOrientation == null) {
			if (other.sexualOrientation != null)
				return false;
		} else if (!sexualOrientation.equals(other.sexualOrientation))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAccountDTO [userId=" + userId + ", phone=" + phone + ", emailAddress=" + emailAddress
				+ ", userName=" + userName + ", aboutMe=" + aboutMe + ", jobTitle=" + jobTitle + ", company=" + company
				+ ", education=" + education + ", livingIn=" + livingIn + ", latitude=" + latitude + ", longitude="
				+ longitude + ", gender=" + gender + ", sexualOrientation=" + sexualOrientation + ", dob=" + dob
				+ ", interestedIn=" + interestedIn + ", peopleWithinKm=" + peopleWithinKm + ", errorMessage="
				+ errorMessage + "]";
	}

}
