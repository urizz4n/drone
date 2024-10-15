package equoExerciseTest;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import equoExercise.Main;

class MainTest {
	private final PrintStream originalOut = System.out;
	private ByteArrayOutputStream testOut;

	void provideInput(String data) {
		java.io.InputStream testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}

	// One drone, follows input first drone from exercise
	@Test
	public void exerciseFirstDrone() {
		provideInput("1\n5 5\n1 2 N\nLMLMLMLMM");
		Main.main(null);
		assertTrue(testOut.toString().endsWith("1 3 N" + System.lineSeparator()));
	}

	// One drone, follows input first drone from exercise
	@Test
	public void exerciseSecondDrone() {
		provideInput("1\n5 5\n3 3 E\nMMRMMRMRRM");
		Main.main(null);
		assertTrue(testOut.toString().endsWith("5 1 E" + System.lineSeparator()));
	}

	// Two drones, follows input both drones from exercise
	@Test
	public void exerciseBothDrones() {
		provideInput("2\n5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM");
		Main.main(null);
		assertTrue(testOut.toString().endsWith("1 3 N 5 1 E" + System.lineSeparator()));
	}

	// Try to deploy zero drones
	@Test
	public void zeroDrones() {
		provideInput("0");
		try {
			Main.main(null);
		} catch (Exception e) {
			assertTrue(testOut.toString().split(System.lineSeparator())[2].equals("#Error: wrong input. Use one positive integer#"));
		}
	}
	
	// Try to input no amount of drones
	@Test
	public void emptyAmountOfDrones() {
		provideInput("\n");
		try {
			Main.main(null);
		} catch (Exception e) {
			assertTrue(testOut.toString().split(System.lineSeparator())[2].equals("#Error: wrong input. Use one positive integer#"));
		}
	}
	
	// Try to create a 0 x 0 plateau
	@Test
	public void noPlateau() {
		provideInput("1\n0 0");
		try {
			Main.main(null);
		} catch (Exception e) {
			assertTrue(testOut.toString().split(System.lineSeparator())[3].equals("#Error: wrong input. Use two positive integers separated by one space, without symbols#"));
		}
	}
	
	// Try to deploy a drone out of bounds
	@Test
	public void deployOutOfBounds() {
		provideInput("1\n5 5\n 6 6 N");
		try {
			Main.main(null);
		} catch (Exception e) {
			assertTrue(testOut.toString().split(System.lineSeparator())[4].equals("#Error: wrong input. The initial position should be within the plateau field (5 5)#"));
		}
	}
	
	
	// Try to go out of bounds, even if you are supposed to return afterwards
	@Test
	public void moveOutOfBounds() {
		provideInput("1\n5 5\n1 1 N\nMMMMMMMMRRMMMMMMMM");
		try {
			Main.main(null);
		} catch (Exception e) {
			assertTrue(testOut.toString().split(System.lineSeparator())[5].equals("#Error: Drone out of bounds. Please try another input#"));
		}
	}
	
	// Three drones, extra drone with custom input, starting from 0 0 moving up to 5 5. 
	@Test
	public void customThreeDrones() {
		provideInput("3\n5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n0 0 N\nMMMMMRMMMMM");
		Main.main(null);
		assertTrue(testOut.toString().endsWith("1 3 N 5 1 E 5 5 E" + System.lineSeparator()));
	}
	
	
	// One drone, ends moving to 0 0
		@Test
		public void customToOppositeCorner() {
			provideInput("1\n5 5\n5 5 S\nMMMMMRMMMMM");
			Main.main(null);
			assertTrue(testOut.toString().endsWith("0 0 W" + System.lineSeparator()));
		}

	//
	@BeforeEach
	public void setUp() {
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
	}
	@BeforeEach
	public void tearDown() {
		System.setOut(originalOut);
	}
}
