package Projects.Project4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EnumTest {
	
	@Test
	public void OwnershipTest() {
		try {
			Ownership.valueOf("RENTAL");
			Ownership.valueOf("OWNED");
			Ownership.valueOf("FINANCED");
		}
		catch(IllegalArgumentException e) {
			fail(e.getLocalizedMessage());
		}
		assertEquals("When looking at the number of values in the Ownership enum, we",3,Ownership.values().length);
	}
	
	@Test
	public void CoolingTest() {
		try {
			Cooling.valueOf("AIR");
			Cooling.valueOf("LIQUID");
			Cooling.valueOf("HEAT_SINK");
			Cooling.valueOf("HYBRID");
			
		}
		catch(IllegalArgumentException e) {
			fail(e.getLocalizedMessage());
		}
		assertEquals("When looking at the number of values in the Cooling enum, we",4,Cooling.values().length);
	}
	
	@Test
	public void OSTest() {
		try {
			OS.valueOf("WINDOWS");
			OS.valueOf("MAC");
			OS.valueOf("LINUX");
		}
		catch(IllegalArgumentException e) {
			fail(e.getLocalizedMessage());
		}
		assertEquals("When looking at the number of values in the OS enum, we",3,OS.values().length);
	}
	
	
}
