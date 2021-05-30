package com.skilldistillery.jets.entity;

public class CargoJet extends Jet implements Loadable {

	public CargoJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void putOnboard() {
		System.out.println("The " + this.getModel() +  " is loading lots of stuff in the hold!");
	}

}
