package com.yonyou.integral.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Integrallevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "integrallevel", catalog = "innoshare")
public class Integrallevel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer integralDown;
	private Integer integralUp;
	private String level;

	// Constructors

	/** default constructor */
	public Integrallevel() {
	}

	/** full constructor */
	public Integrallevel(Integer integralDown, Integer integralUp, String level) {
		this.integralDown = integralDown;
		this.integralUp = integralUp;
		this.level = level;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "integralDown")
	public Integer getIntegralDown() {
		return this.integralDown;
	}

	public void setIntegralDown(Integer integralDown) {
		this.integralDown = integralDown;
	}

	@Column(name = "IntegralUp")
	public Integer getIntegralUp() {
		return this.integralUp;
	}

	public void setIntegralUp(Integer integralUp) {
		this.integralUp = integralUp;
	}

	@Column(name = "level", length = 32)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}