package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userInfo")
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "score", nullable = false)
	private Long score;
	
	@Column(name = "createTime", nullable = false)
	private String createTime;
	
	@Column(name = "challenge", nullable = false)
	private Long challenge;
	
	@Column(name = "against", nullable = false)
	private Long against;

	public Long getId() {
		return id;
	}

	public Long getChallenge() {
		return challenge;
	}

	public void setChallenge(Long challenge) {
		this.challenge = challenge;
	}

	public Long getAgainst() {
		return against;
	}

	public void setAgainst(Long against) {
		this.against = against;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
