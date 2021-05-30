package com.skilldistillery.jets.entity;

public class MilitaryJet extends Jet implements Alertable{

	public MilitaryJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	public void genericMethodForInterface() {

	}

	@Override
	public void scramble() {
		System.out.println("The " + this.getModel() +  " is now on high alert!");
		
	}

}
