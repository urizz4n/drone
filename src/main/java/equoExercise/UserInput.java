package equoExercise;

import java.util.Scanner;

public class UserInput {

	Scanner scanner = new Scanner(System.in);

	// Drone quantity
	public int droneQuantityInput() {
		String droneQuantityInput;
		while (true) {
			System.out.println("*Please insert how many drones to deploy*");
			droneQuantityInput = scanner.nextLine().trim();
			if (droneQuantityInput.matches("^\\b[1-9]\\d*\\b$"))
				break; // Regex, checks if it's one integer
			System.out.println("#Error: wrong input. Use one positive integer#");
		}
		return Integer.parseInt(droneQuantityInput);
	}

	// Receives input from the user until it's correct. (plateau)
	public int[] plateauInput() {
		int[] plateauInputArray;
		// Retries until input format is correct
		while (true) {
			System.out.println("*Please insert Plateau size with the following format: <X> <Y>*");
			String plateauInput = scanner.nextLine().trim();
			// Regex, checks if it's two positive integers separated by a space
			if (plateauInput.matches("^\\b[1-9]\\d*\\b\\s\\b[1-9]\\d*\\b$")) {
				String[] splitPlateauInput = plateauInput.split(" ");
				int length = Integer.valueOf(splitPlateauInput[0]);
				int height = Integer.valueOf(splitPlateauInput[1]);
				plateauInputArray = new int[] {length, height};
				break; 
			}
			System.out
					.println("#Error: wrong input. Use two positive integers separated by one space, without symbols#");
		}

		return plateauInputArray;
	}

	// Receives input from the user until it's correct. (drone)
	public Object[] droneInput(Plateau plateau) {
		Object[] returnDroneInput;

		// Retries until input format is correct
		while (true) {
			System.out.println(
					"*Please insert Drone initial position with the following format: <X> <Y> <Facing direction>*");
			String droneInput = scanner.nextLine().trim();
			if (droneInput.matches("^\\d+\\s\\d+\\s[NSWEnswe]$")) {
				// Split to make it easier to read
				String[] droneInputArray = droneInput.split(" ");
				int startingXPosition = Integer.valueOf(droneInputArray[0]);
				int startingYPosition = Integer.valueOf(droneInputArray[1]);

				// Check if starting position exceeds plateau size
				if (startingXPosition <= plateau.getXSize() && startingYPosition <= plateau.getYSize()) {
					char startingDirection = Character.toUpperCase(droneInputArray[2].charAt(0));
					returnDroneInput = new Object[] { startingXPosition, startingYPosition, startingDirection };
					break; // Regex, checks if it's two positive integers and a char from NSWE separated by
							// spaces
				} else {
					System.out.println("#Error: wrong input. The initial position should be within the plateau field ("
							+ plateau.getXSize() + " " + plateau.getYSize() + ")#");
				}
			} else {
				System.out.println(
						"#Error: wrong input. Use two positive integers and one character for direction (from NSWE) separated by spaces, without symbols#");
			}

		}
		return returnDroneInput;
	}

	// Receives input from the user until it's correct. (commands)
	public String commandInput() {
		String commandInput;
		// Retries until input format is correct
		while (true) {
			System.out.println(
					"*Please insert your movement commands. Type L or R to rotate 90 degrees, or M to move forward*");
			commandInput = scanner.nextLine().trim();
			if (commandInput.matches("[MLRmlr]+"))
				break; // Regex, checks if it's a String made just by a combination of M, L, or R.
			System.out.println("#Error: wrong input. Please write all characters without spaces#");
		}
		return commandInput;
	}

	public void closeInput() {
		scanner.close();
	}

}
