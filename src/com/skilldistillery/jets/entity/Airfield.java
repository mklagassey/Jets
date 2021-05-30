package com.skilldistillery.jets.entity;

import java.util.ArrayList;
import java.util.List;

public class Airfield {
	private List<Jet> fleet = new ArrayList<>();
	
	
	
	public Airfield() {
		super();
	}

	public Airfield(List<Jet> jets) {
		fleet = jets;
	}
	
	public int getSize() {
		return fleet.size();
	}

	public List<Jet> getFleet() {
		return fleet;
	}

	public void setFleet(List<Jet> fleet) {
		this.fleet = fleet;
	}
	
	// get a jet at a position method
	public Jet getJetAtPosition(int position) {
		return fleet.get(position);
	}
	
	// addJetToFleet method
	public void addJetToFleet(Jet jetToAdd) {
		this.fleet.add(jetToAdd);
	}
	
	// remvoveJetFromFleet method
	public void removeJetFromFleet(int index) {
		this.fleet.remove(index);
	}

	@Override
	public String toString() {
		return "         ~~~~~~~~~~~~~  AIRFIELD FLEET  ~~~~~~~~~~~~~~~\n"
				+ "                             __|__\n"
				+ "                      --@--@--(_)--@--@-- \n" 
				+ "\n"
				+ fleet;
	}
	
	
}
