package com.perfectcherry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perfectcherry.entity.Image;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {
	
	@Query("FROM Image where userAccount.userAccountId = ?1 ") 
	public List<Image> getAllImagesByUserId(Long userId);
	
	@Query("FROM Image where userAccount.userAccountId = ?1 and isProfilePhoto = 'Y' ") 
	public Image getProfilePhotoByUserId(Long userId);

}
