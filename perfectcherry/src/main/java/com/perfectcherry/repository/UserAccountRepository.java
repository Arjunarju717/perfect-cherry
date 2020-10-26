package com.perfectcherry.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perfectcherry.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	@Query("FROM UserAccount WHERE userAccountId= ?1 AND status = 'A' ")
	public Optional<UserAccount> getActiveUser(Long userID);

	@Query("FROM UserAccount WHERE userAccountId= ?1 AND status = 'O' ")
	public Optional<UserAccount> getObsoleteUser(Long userID);

	@Query(value = "CALL FindPeopleNearMe(:userid,:latitude,:longitude,:withinRange);", nativeQuery = true)
	public List<UserAccount> findPeopleNearMe(@Param("userid") Long userID, @Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude, @Param("withinRange") int km);

}
