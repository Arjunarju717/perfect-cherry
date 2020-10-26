package com.perfectcherry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perfectcherry.entity.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long>{
	
	@Query("SELECT interestedOn FROM Interest WHERE userId= ?1 AND status = 'Pending' ")
	public List<Long> interestSent(Long userId);
	
	@Query("SELECT userId FROM Interest WHERE interestedOn= ?1 AND status = 'Pending' ")
	public List<Long> interestReceived(Long userId);
	
	@Query("SELECT userId FROM Interest WHERE interestedOn= ?1 AND status = 'Accepted' ")
	public List<Long> interestAcceptedByMe(Long userId);
	
	@Query("SELECT interestedOn FROM Interest WHERE userId= ?1 AND status = 'Accepted' ")
	public List<Long> interestAcceptedByThem(Long userId);
	
	@Query("SELECT userId FROM Interest WHERE interestedOn= ?1 AND status = 'Declined' ")
	public List<Long> interestDeclinedByMe(Long userId);
	
	@Query("SELECT interestedOn FROM Interest WHERE userId= ?1 AND status = 'Declined' ")
	public List<Long> interestDeclinedByThem(Long userId);
	
	@Query("FROM Interest WHERE userId= ?1 AND interestedOn = ?2 ")
	public Optional<Interest> isInterestAlreadySent(Long userId, Long interestedOn);
	
	

}
