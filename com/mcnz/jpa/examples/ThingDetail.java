package com.mcnz.jpa.examples;
import javax.persistence.Embeddable;

@Embeddable
public class ThingDetail {

	private String alias;
	private int count;

	public String getAlias() {return alias;}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getCount() {return count;}
	public void setCount(int count) {
		this.count = count;
	}
}
