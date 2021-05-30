package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entity.Airfield;
import com.skilldistillery.jets.entity.Alertable;
import com.skilldistillery.jets.entity.CargoJet;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.Loadable;
import com.skilldistillery.jets.entity.MilitaryJet;
import com.skilldistillery.jets.entity.PassengerJet;

public class JetsApplication {

	private Airfield airfield = new Airfield();
	private Scanner input = new Scanner(System.in); 

	public JetsApplication() {
	}

	public static void main(String[] args) {
		String fileName = "jets.csv";
		List<Jet> jetFleet;
		
		JetsApplication jetTycoonSim = new JetsApplication();

		jetFleet = jetTycoonSim.takeOff(fileName);
		jetTycoonSim.addFleetToAirField(jetFleet);
		jetTycoonSim.mainMenu();
	}

	// Get things up and running
	private List<Jet> takeOff(String jetFile) {
		// Initialize the List that will be returned, full of jets from txt file
		List<Jet> fleet = new ArrayList<Jet>();
		// Read and separate jets from file based on type, then add to List
		try (BufferedReader bufIn = new BufferedReader(new FileReader(jetFile))) {
			String jet;
			// Get required parameters to instantiate new Jet objects
			while ((jet = bufIn.readLine()) != null) {
				String[] jetSpecs = jet.split(",");
				String model = jetSpecs[0];
				double speed = Double.parseDouble(jetSpecs[1]);
				int range = Integer.parseInt(jetSpecs[2]);
				long price = Long.parseLong(jetSpecs[3]);
				String type = jetSpecs[4];
				// Use type to determine which sub-class to instantiate
				switch (type) {
				case "cargo":
					Jet addCargoJet = new CargoJet(model, speed, range, price);
					fleet.add(addCargoJet);
					break;
				case "military":
					Jet addMilJet = new MilitaryJet(model, speed, range, price);
					fleet.add(addMilJet);
					break;
				case "passenger":
					Jet addPaxJet = new PassengerJet(model, speed, range, price);
					fleet.add(addPaxJet);
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		return fleet;
	}

	// Add new Jet objects to Airfield object fleet
	private void addFleetToAirField(List<Jet> newFleet) {
		airfield.setFleet(newFleet);
	}
	
	// User interface
	private void mainMenu() {
		boolean continueApp = true;
		
		while (continueApp) {
			int userChoice = getMenuChoice();
			
			switch (userChoice) {
			case 1:
				displayFullFleet();
				System.out.println();
				break;
			case 2:
				// flyFleet method
				flyFleet();
				System.out.println();
				break;
			case 3:
				// fastestJet method
				displayFastestJet();
				System.out.println();
				break;
			case 4:
				// farthestJet method
				displayFarthestJet();
				System.out.println();
				break;
			case 5:
				// loadJets method
				loadJets();
				System.out.println();
				break;
			case 6:
				// alertJets method
				alertJets();
				System.out.println();
				break;
			case 7:
				// addJet method
				addJet();
				System.out.println();
				break;
			case 8:
				// removeJet method
				removeJet();
				System.out.println();
				break;
			case 9:
				displayOutroMsg();
				continueApp = false;
				break;

			default:
				displayErrorMsg();
				break;
			}
		}
	}
	
//	private Jet iterateThruFleet(List<Jet> fleet) {
//		Jet jet;
//		
//		return jet;
//	}
	
	private int getMenuChoice() {
		System.out.println("Press 1 to LIST all jets in the fleet: \n"
				+ "Press 2 to FLY all jets in the fleet: \n"
				+ "Press 3 to see the FASTEST jet: \n"
				+ "Press 4 to see the jet that can fly the FARTHEST: \n"
				+ "Press 5 to LOAD passenger and cargo jets: \n"
				+ "Press 6 to ALERT military jets: \n"
				+ "Press 7 to ADD a jet to the fleet: \n"
				+ "Press 8 to REMOVE a jet from the fleet: \n"
				+ "Press 9 to QUIT: \n"
				+ "\n"
				);
		return input.nextInt();
	}
	
	// Choice 1 method
	private void displayFullFleet() {
		System.out.println(airfield.toString());
		
	}
	
	// Choice 2 method
	private void flyFleet() {
		System.out.println("Flying all jets in fleet!");
		// foreach on fleet, calling fly() method
		for (Jet jet : airfield.getFleet()) {
			jet.fly();
		}
	}
	
	// Choice 3 method
	private void displayFastestJet() {
		Jet fastest = airfield.getJetAtPosition(0);
		// do a foreach search with a fastestJetFound local var, print out 
		for (Jet jet : airfield.getFleet()) {
			if (jet.getSpeed() > fastest.getSpeed()) {
				fastest = jet;
			}
		}
		System.out.println("The fastest jet in your fleet is the " + fastest.getModel());
	}
	
	// Choice 4 method
	private void displayFarthestJet() {
		Jet farthest = airfield.getJetAtPosition(0);
		
		for (Jet jet : airfield.getFleet()) {
			if (jet.getRange() > farthest.getRange()) {
				farthest = jet;
			}
		}
		System.out.println("The jet that can fly the furthest is the " + farthest.getModel());
		// same as above but for longest range
	}
	// Choice 5 method
	private void loadJets() {
		System.out.println("Loading the following jets: ");
		for (Jet jet : airfield.getFleet()) {
			if (jet instanceof Loadable) {
				((Loadable)jet).putOnboard(); // cast to interface type
			}
		}
	}
	// Choice 6 method
	private void alertJets() {
		System.out.println("The following jets are now on alert: ");
		// same as above but for MilitaryJet class objects
		for (Jet jet : airfield.getFleet()) {
			if (jet instanceof Alertable) {
				((Alertable)jet).scramble(); // cast to interface type
			}
		}
	}
	// Choice 7 method
	private void addJet() {
		int choice;
		String model;
		double speed;
		int range;
		long price;
		boolean continueToAddJets = true;
		
		while (continueToAddJets) {
		
		System.out.println("What type of jet would you like to add?\n"
				+ "---------------------------------------------\n"
				+ "1) Passenger\n"
				+ "2) Cargo\n"
				+ "3) Military\n"
				);
		choice = input.nextInt();
		if (choice < 1 || choice > 3) {
			System.out.println("ERROR: Invalid choice.\n"
					+ "Please select a number from 1-3\n"
					);
			break;
		}
		System.out.println("What is the model designation?");
		model = input.next();
		System.out.println("What is the top speed?");
		speed = input.nextDouble();
		System.out.println("What is the range?");
		range = input.nextInt();
		System.out.println("What is the price?");
		price = input.nextLong();

		
		switch (choice) {
		case 1:
			Jet newPaxJet = new PassengerJet(model, speed, range, price);
			airfield.addJetToFleet(newPaxJet);
			System.out.println("New " + model + " successfully added to your fleet!");
			break;
		case 2:
			Jet newCargoJet = new CargoJet(model, speed, range, price);
			airfield.addJetToFleet(newCargoJet);
			System.out.println("New " + model + " successfully added to your fleet!");
			break;
		case 3:
			Jet newMilJet = new MilitaryJet(model, speed, range, price);
			airfield.addJetToFleet(newMilJet);
			System.out.println("New " + model + " successfully added to your fleet!");
			break;

		default:
			System.out.println("ERROR: Invalid choice.\n"
					+ "Please select a number from 1-3\n"
					);
			break;
		}
		System.out.println("Would you like to add another jet to the fleet?");
		String in = input.next();
		if (in.contains("y") || in.contains("Y")) {
			continue;
		} else {
			continueToAddJets = false;
		}
		}
	}
	// Choice 8 method
	private void removeJet() {
		boolean continueRemovingJets = true;

		while (continueRemovingJets) {
			int counter = 1;
			int toRemove = 0;
			if (airfield.getSize() == 0) {
				System.out.println("You have no jets left in your fleet to remove!");
				break;
			}
			System.out.println("What jet would you like to remove?");
			for (Jet jet : airfield.getFleet()) {
				System.out.println("" + counter + ") " + jet.getModel());
				counter++;
			}
			toRemove = (input.nextInt() - 1);
			System.err.println("Please confirm you would like to permanently remove "
					+ airfield.getJetAtPosition(toRemove) + "!!!");
			String in = input.next();
			if (in.contains("y") || in.contains("Y")) {
				// call remove on airfield
				System.out.println("Removing " + airfield.getJetAtPosition(toRemove).getModel() 
									+ " from the fleet...\n");
				airfield.removeJetFromFleet(toRemove);
				System.out.println("Jet succesfully removed. Would you like to remove another?");
				in = input.next();
				if (in.contains("y") || in.contains("Y")) {
					continue;
				} else {
					break;
				}
			} else {
				System.out.println("Remove jet cancelled. No further action required.");
				break;
			}
		}
	}
	// Choice 9 method
	private void displayOutroMsg() {
		System.out.println("Thank you for using JetFleet Manager 2000!\n"
				+ "\n"
				+ "Produced by the makers of the famous FoodTruck App\n"
				+ "MickCorp ©2000 All Rights Reserved"
				);
	}
	
	private void displayErrorMsg() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n"
				+ "           ERROR - INVALID INPUT\n"
				+ "      Please choose a number from 1-9\n"
				+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
				);
		System.out.println();
	}

}


























































