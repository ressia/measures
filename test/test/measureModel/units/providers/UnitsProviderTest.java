package test.measureModel.units.providers;

import com.measureModel.units.providers.UnitsProvider;

import junit.framework.TestCase;

public class UnitsProviderTest extends TestCase {
	
	public void testCreation() {
		UnitsProvider aProvider = UnitsProvider.newInstance();
		assertTrue(aProvider == UnitsProvider.newInstance());
	}
	

}
