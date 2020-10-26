package com.perfectcherry.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "image")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user" })
public class Image implements Serializable {

	private static final long serialVersionUID = 5764866028189394732L;

	@Id
	@Column(name = "imageid")
	private Long imageId;

	@Column(name = "imagename")
	private String imageName;

	@Column(name = "imagetype")
	private String imageType;

	@Lob
	@Column(name = "imagedata")
	private byte[] imageData;

	@Column(name = "isprofilephoto")
	private char isProfilePhoto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	private UserAccount userAccount;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public char getIsProfilePhoto() {
		return isProfilePhoto;
	}

	public void setIsProfilePhoto(char isProfilePhoto) {
		this.isProfilePhoto = isProfilePhoto;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
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
		result = prime * result + Arrays.hashCode(imageData);
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((imageType == null) ? 0 : imageType.hashCode());
		result = prime * result + isProfilePhoto;
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((userAccount == null) ? 0 : userAccount.hashCode());
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
		Image other = (Image) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (!Arrays.equals(imageData, other.imageData))
			return false;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (imageType == null) {
			if (other.imageType != null)
				return false;
		} else if (!imageType.equals(other.imageType))
			return false;
		if (isProfilePhoto != other.isProfilePhoto)
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (userAccount == null) {
			if (other.userAccount != null)
				return false;
		} else if (!userAccount.equals(other.userAccount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Images [imageId=" + imageId + ", imageName=" + imageName + ", imageType=" + imageType + ", imageData="
				+ Arrays.toString(imageData) + ", isProfilePhoto=" + isProfilePhoto + ", userAccount=" + userAccount
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
