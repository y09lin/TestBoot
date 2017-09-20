package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.UserInfo;

public interface UserinfoRepository extends JpaRepository<UserInfo, Long>{
	public UserInfo findByName(String name);
}
