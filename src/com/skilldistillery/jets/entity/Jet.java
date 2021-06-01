package com.skilldistillery.jets.entity;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	
	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	
	
	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public double getSpeed() {
		return speed;
	}



	public void setSpeed(double speed) {
		this.speed = speed;
	}



	public int getRange() {
		return range;
	}



	public void setRange(int range) {
		this.range = range;
	}



	public long getPrice() {
		return price;
	}



	public void setPrice(long price) {
		this.price = price;
	}



	public void fly() {
		System.out.printf("*** A " + model 
				+ " departing runway 19 for cross-country flight.\n"
				+ "Estimated duration will be: %.2f", getFlightTime()
				);
				System.out.println(" hours. ------------------------------------------------->");
			}
	
	public double getFlightTime() {
		return (double)(range/speed);
	}
	
	@Override
	public String toString() {
		return "Jet [model: " + model + ", speed: " + speed + ", range: " + range + ", price: " + price + "]\n";
	}



	public double convertSpeedToMach() {
		// Code here to get get the speed and convert it to a mach speed
		double mach = 0.0;
		
		return mach;
	}
}











































































