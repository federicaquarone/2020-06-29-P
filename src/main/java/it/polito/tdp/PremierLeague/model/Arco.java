package it.polito.tdp.PremierLeague.model;

public class Arco {

	Match m1;
	Match m2;
	int count;
	public Arco(Match m1, Match m2, int count) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.count = count;
	}
	public Match getM1() {
		return m1;
	}
	public void setM1(Match m1) {
		this.m1 = m1;
	}
	public Match getM2() {
		return m2;
	}
	public void setM2(Match m2) {
		this.m2 = m2;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Arco [m1=" + m1 + ", m2=" + m2 + ", count=" + count + "]";
	}
	
	
}
