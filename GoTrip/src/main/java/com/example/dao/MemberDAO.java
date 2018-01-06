package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.Account;


public interface MemberDAO extends CrudRepository<Account, String>{

	@Query("select a FROM Authority a1 JOIN a1.accountAutho a where authority = 'ROLE_USER'")
	public Iterable<Account> findUsers();
	
	@Query("select a FROM Authority a1 JOIN a1.accountAutho a where authority = 'ROLE_ADMIN'")
	public Iterable<Account> findAdmins();
}