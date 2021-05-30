package com.skilldistillery.jets.entity;

public class PassengerJet extends Jet implements Loadable {

	public PassengerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void putOnboard() {
		System.out.println("The " + this.getModel() + " is boarding all passengers (aka self-loading cargo)!");
	}
}
