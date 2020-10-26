package com.perfectcherry.dto;

import java.io.Serializable;

public class ImageDTO implements Serializable{

	private static final long serialVersionUID = 2504630031173429578L;

	private Long userID;

	private char isProfilePhoto;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public char getIsProfilePhoto() {
		return isProfilePhoto;
	}

	public void setIsProfilePhoto(char isProfilePhoto) {
		this.isProfilePhoto = isProfilePhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isProfilePhoto;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		ImageDTO other = (ImageDTO) obj;
		if (isProfilePhoto != other.isProfilePhoto)
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageDTO [userID=" + userID + ", isProfilePhoto=" + isProfilePhoto + "]";
	}

}
