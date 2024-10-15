package equoExercise;

public class Drone {
	
	public Drone (int xPosition, int yPosition, char direction) {
		this.direction = Direction.fromChar(direction);
		this.xPosition = xPosition;
		this.yPosition = yPosition;	
		
		this.initialX = xPosition;
		this.initialY = yPosition;
		this.initialDirection = Direction.fromChar(direction);		
	}
	
	// x and y are positions in a grid
	private int xPosition;
	private int yPosition;
	private int initialX;
	private int initialY;
	
	// where the drone is facing
	private Direction direction;
	private Direction initialDirection;
	
	
	public void resetDrone() {
		xPosition = initialX;
		yPosition = initialY;
		direction = initialDirection;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public String getPosition() {
		return (xPosition + " " + yPosition);
	}
	public Direction getDirection() {
		return direction;
	}
	
	public void moveForward() {
		xPosition += (int) Math.sin(Math.PI * 2 * direction.getAngle() / 360);
		yPosition += (int) Math.cos(Math.PI * 2 * direction.getAngle() / 360);
	}
	
	public void rotate(char rotation) {
		Direction rotationDirection = Direction.fromChar(rotation);
		direction = Direction.fromAngle(direction.getAngle() + rotationDirection.getAngle());
	}
	
}
