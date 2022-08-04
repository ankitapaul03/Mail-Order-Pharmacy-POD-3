/**
 * 
 */
package com.cts.refill.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "refill_order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefillOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long refillID;
	@Column
	private long subID;
	@Column
	private String memberID;
	@Column
	private boolean payStatus;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	private Date refillDate;
	@Column
	private int quantity;
	public boolean getPayStatus() {
		return this.payStatus;
	}
	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}
	
}
