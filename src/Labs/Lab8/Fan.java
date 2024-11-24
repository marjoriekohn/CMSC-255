package Labs.Lab8;

public class Fan {
	// Constants for the speed of the fan
	public static final int SLOW = 1;
	public static final int MEDIUM = 2;
	public static final int FAST = 3;
	
	// Instance variables
	private int speed;
	private boolean on;
	private double radius;
	private String color;
	
	/**
	 * This is a constructor that sets the speed, on, radius, and color of the fan
	 * @param speed the speed of the fan
	 * @param on true if the fan is on, false otherwise
	 * @param radius the radius of the fan
	 * @param color the color of the fan
	 */
	public Fan(int speed, boolean on, double radius, String color) {
		this.speed = speed;
		this.on = on;
		this.radius = radius;
		this.color = color;
	}
	
	/**
	 * This is a no-arg constructor that sets the speed to SLOW, on to false, radius to 5, and color to blue
	 */
	public Fan () {
		speed = SLOW;
		on = false;
		radius = 5;
		color = "blue";
	}
	
	/**
	 * This method returns the color of the fan
	 * @return the color of the fan
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * This method sets the color of the fan
	 * @param color the color of the fan
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * This method returns the radius of the fan
	 * @return the radius of the fan
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * This method sets the radius of the fan
	 * @param radius the radius of the fan
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 *This method returns the status of whether the fan is on or off
	 * @return true if the fan is on, false otherwise
	 */
	public boolean isOn() {
		return on;
	}
	
	/**
	 * This method sets the status of the fan to on or off
	 * @param on true if the fan is on, false otherwise
	 */
	public void setOn(boolean on) {
		this.on = on;
	}
	
	/**
	 * This method returns the speed of the fan
	 * @return the speed of the fan
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * This method sets the speed of the fan
	 * @param speed the speed of the fan
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * This method returns the string representation of the status of the fan
	 * @return if the fan is on, speed, color, and radius, otherwise, "fan is off"
	 */
	public String toString() {
		if (on) {
			return "fan is " + speed + ", " + color + ", and size " + radius;
		} else {
			return "fan is off";
		}
	}
}
