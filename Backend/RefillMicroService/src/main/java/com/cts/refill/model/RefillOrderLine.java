/**
 * 
 */
package com.cts.refill.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "refillOrder_line")
@Table(name = "refillOrder_line")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefillOrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private long subID;
	@Column
	private String memberID;
	@Column
	private int refillTime;
	@Column
	private int quantity;
	public RefillOrderLine(long subID, String memberID, int refillTime, int quantity) {
		super();
		this.subID = subID;
		this.memberID = memberID;
		this.refillTime = refillTime;
		this.quantity = quantity;
	}
	
	
}
