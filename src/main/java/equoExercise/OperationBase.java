package equoExercise;

import java.util.ArrayList;
import java.util.Scanner;

public class OperationBase {

	ArrayList<String> drones = new ArrayList<String>();

	// Welcome phrase
	public void welcome() {
		System.out.println("**Shell Inc. Desert oil exploration drone***");
	}


	// Receives the output from plateauInput(), returns the plateau singleton
	// initialized
	public Plateau plateauCreation(int[] plateauInputArray) {
		int length = plateauInputArray[0];
		int height = plateauInputArray[1];
		Plateau plateau = Plateau.getInstance(length, height);
		return plateau;
	}


	// Receives the output from droneInput(), returns the drone initialized
	public Drone droneCreation(Object[] droneInputArray) {
		Drone drone = new Drone((int) droneInputArray[0], (int) droneInputArray[1], (char) droneInputArray[2]);
		return drone;
	}



	// Receives the output from commandInput(), executes commands
	public boolean commandExecuter(String commandInput, Drone drone, Plateau plateau) {
		commandInput = commandInput.toUpperCase();
		// Separates string into char Array and iterates
		for (char command : commandInput.toCharArray()) {
			// Moves forward when M is found
			if (command == 'M') {
				drone.moveForward();
				if (drone.getXPosition() > plateau.getXSize() || drone.getYPosition() > plateau.getYSize()) {
					System.out.println("#Error: Drone out of bounds. Please try another input#");
					drone.resetDrone();
					return false;
				}
				// Rotates if founds L or R
			} else if (command == 'L' || command == 'R') {
				drone.rotate(command);
			}
		}
		// Add drone last position and direction to the final result
		drones.add(drone.getPosition() + " " + drone.getDirection().getAbbreviation());
		return true;
	}

	public String finish() {
		String result = "";
		
		// Sends drones final position and direction through console
		System.out.println("*Drone movement completed successfully*");
		for (String drone : drones)
			result += drone + " ";
		
		result = result.trim();
		System.out.println(result);
		
		return result;
	}

}
