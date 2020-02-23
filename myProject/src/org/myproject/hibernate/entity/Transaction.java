package org.myproject.hibernate.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "transaction")
@Table(name = "transaction")
public class Transaction {
	@Id
	int tID;

	int cID;
	Date tdate;
	String price;

	public Transaction() {
		
	}

	public Transaction(int tID, int cID, Date tdate, String price)
	{
		this.tID = tID;
		this.cID = cID;
		this.tdate = tdate;
		this.price = price;
	}

	public int gettID() 
	{
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Transaction [tID=" + tID + ", cID=" + cID + ", tdate=" + tdate + ", price=" + price + "]";
	}

}
