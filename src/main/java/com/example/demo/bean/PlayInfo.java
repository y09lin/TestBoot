package com.example.demo.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="playInfo")
public class PlayInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="nameA", nullable=false)
	private String nameA;
	
	@Column(name="nameB", nullable=false)
	private String nameB;
	
	@Column(name="roundCount", nullable=false)
	private Long roundCount;
	
	@Column(name="roundDetail", nullable=false)
	private String roundDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}

	public long getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(long roundCount) {
		this.roundCount = roundCount;
	}

	public String getRoundDetail() {
		return roundDetail;
	}

	public void setRoundDetail(String roundDetail) {
		this.roundDetail = roundDetail;
	}
	
}
