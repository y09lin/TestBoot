package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.PlayInfo;

public interface PlayinfoRepository extends JpaRepository<PlayInfo, Long>{

//	@Query("select p from playInfo where p.nameA=:name or p.nameB=:name")
//	public List<PlayInfo> getPlayByUsername(@Param("name") String name);
}
