package equoExercise;

public enum Direction {
	
	NORTH(0,'N'), EAST(90,'E'), SOUTH(180,'S'), WEST(270,'W'),LEFT(-90,'L'),RIGHT(90,'R');
	
	private Direction(int angle, char abbreviation) {
		this.angle = angle;
		this.abbreviation = abbreviation;
	}
	
	private int angle;
	private char abbreviation;
	
	public int getAngle() {
		return angle;
	}
	
	public char getAbbreviation() {
		return abbreviation;
	}
	
	// Given a specific angle, return a cardinal direction
	public static Direction fromAngle(int angleInput) {
		int finalAngle = angleInput % 360;
		if (finalAngle < 0) finalAngle = finalAngle + 360;
        for (Direction direction : Direction.values()) {
            if (direction.getAngle() == finalAngle) {
                return direction;
            }
        }
        return null;
	 }
	
	public static Direction fromChar(char charInput) {
        for (Direction direction : Direction.values()) {
            if (direction.getAbbreviation() == charInput) {
                return direction;
            }
        }
        return null;
	 }
	
}
