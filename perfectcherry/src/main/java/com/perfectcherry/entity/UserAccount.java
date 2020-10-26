package com.perfectcherry.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "useraccount")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user" })
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 6929818202477730595L;

	@Id
	@Column(name = "useraccountid")
	private Long userAccountId;

	@Column(name = "pcid")
	private String pcId;

	@Column(name = "phone")
	private Long phone;

	@Column(name = "emailaddress")
	private String emailAddress;

	@Column(name = "username")
	private String userName;

	@Column(name = "aboutme")
	private String aboutMe;

	@Column(name = "jobtitle")
	private String jobTitle;

	@Column(name = "company")
	private String company;

	@Column(name = "education")
	private String education;

	@Column(name = "livingin")
	private String livingIn;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "longitude")
	private BigDecimal longitude;

	@Column(name = "gender")
	private String gender;

	@Column(name = "sexualorientation")
	private String sexualOrientation;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "status")
	private char status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccount", orphanRemoval = true)
	private List<Image> image;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "interestedin")
	private String interestedIn;

	@Column(name = "peoplewithinkm")
	private int peopleWithinKm;

	@Column(name = "isprofileupdated")
	private boolean isProfileUpdated;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public boolean isProfileUpdated() {
		return isProfileUpdated;
	}

	public void setProfileUpdated(boolean isProfileUpdated) {
		this.isProfileUpdated = isProfileUpdated;
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
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((interestedIn == null) ? 0 : interestedIn.hashCode());
		result = prime * result + (isProfileUpdated ? 1231 : 1237);
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((livingIn == null) ? 0 : livingIn.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((pcId == null) ? 0 : pcId.hashCode());
		result = prime * result + peopleWithinKm;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sexualOrientation == null) ? 0 : sexualOrientation.hashCode());
		result = prime * result + status;
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userAccountId == null) ? 0 : userAccountId.hashCode());
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
		UserAccount other = (UserAccount) obj;
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
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
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
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (interestedIn == null) {
			if (other.interestedIn != null)
				return false;
		} else if (!interestedIn.equals(other.interestedIn))
			return false;
		if (isProfileUpdated != other.isProfileUpdated)
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
		if (pcId == null) {
			if (other.pcId != null)
				return false;
		} else if (!pcId.equals(other.pcId))
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
		if (status != other.status)
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userAccountId == null) {
			if (other.userAccountId != null)
				return false;
		} else if (!userAccountId.equals(other.userAccountId))
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
		return "UserAccount [userAccountId=" + userAccountId + ", pcId=" + pcId + ", phone=" + phone + ", emailAddress="
				+ emailAddress + ", userName=" + userName + ", aboutMe=" + aboutMe + ", jobTitle=" + jobTitle
				+ ", company=" + company + ", education=" + education + ", livingIn=" + livingIn + ", latitude="
				+ latitude + ", longitude=" + longitude + ", gender=" + gender + ", sexualOrientation="
				+ sexualOrientation + ", dob=" + dob + ", status=" + status + ", image=" + image + ", user=" + user
				+ ", interestedIn=" + interestedIn + ", peopleWithinKm=" + peopleWithinKm + ", isProfileUpdated="
				+ isProfileUpdated + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
