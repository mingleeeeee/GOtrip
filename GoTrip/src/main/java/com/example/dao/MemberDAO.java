package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Account;


public interface MemberDAO extends CrudRepository<Account, String>{

}