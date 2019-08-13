package com.atan.registration.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atan.registration.dto.User;


@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	
	
	public User findByUserId(long id);

	public User findByEmail(String email);

	public User findByMobileNumber(String mobileNumber);
	
	public Page<User> findAll(Pageable pageable);

//	@Query("SELECT o FROM Registration o WHERE DATE(o.dateOfBirth) >= ?1 AND DATE(o.dateOfBirth) <= ?2 ")
//	public List<Registration> findByDateOfBirth(@Temporal(TemporalType.DATE) Date startDate, @Temporal(TemporalType.DATE) Date endDate);

}
