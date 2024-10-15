package equoExercise;

public class Main {
	
	private static UserInput userInput;
	private static OperationBase opBase;

	public static void main(String[] args) {

		// Operation Base creation
		opBase = new OperationBase();
		userInput = new UserInput();
		int droneQuantity;
		
		// Start operation base
		opBase.welcome();
		// Get amount of drones to deploy
		droneQuantity = userInput.droneQuantityInput();
		// Plateau Creation
		Plateau plateau = setupPlateau();
		// Create and move the drones
		droneProgram(plateau,droneQuantity);
		
		userInput.closeInput();
		opBase.finish();

	}
	
	public static Plateau setupPlateau() {
		int[] plateauInput = userInput.plateauInput();
		return opBase.plateauCreation(plateauInput);
		
	}
	
	public static Drone createDrone(Plateau plateau) {
		// Drone Creation
		Object[] droneInputArray = userInput.droneInput(plateau);
		return opBase.droneCreation(droneInputArray);
	}

	public static void commandDrone(Drone drone, Plateau plateau) {
		// Command execution
		boolean executed = false;
		// Command might not execute depending if the drone gets out of bounds. If that
		// happens, it'll request another input and try again
		while (!executed) {
			String commandInput = userInput.commandInput();
			executed = opBase.commandExecuter(commandInput, drone, plateau);
		}
	}

	public static void droneProgram(Plateau plateau, int droneQuantity) {
		for (int i = 0; i < droneQuantity; i++) {
			Drone drone = createDrone(plateau);
			commandDrone(drone,plateau);
		}
	}

}
