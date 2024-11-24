package Labs.Lab8;

public class FanTest {
	public static void main(String[] args) {
		Fan fan1 = new Fan(Fan.FAST, true, 10, "yellow");
		Fan fan2 = new Fan(Fan.MEDIUM, false, 20, "blue");
		
		System.out.println("Fan 1: " + fan1.toString());
		System.out.println("Fan 2: " + fan2.toString());
	}
}
