package incud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import incud.util.Multiset;

public class MultisetTest {

	@Test
	public void testAdd() {
		Multiset<String> mset = new Multiset<>();
		mset.add("ciao");
		mset.add("addio");
		mset.add("addio");
		assertEquals(mset.get("ciao"), 1);
		assertEquals(mset.get("aaaa"), 0);
		assertEquals(mset.get("addio"), 2);
	}
	
	@Test
	public void testRemove() {
		Multiset<String> mset = new Multiset<>();
		mset.add("ciao", 10);
		
		mset.remove("ciao");
		assertEquals(mset.get("ciao"), 9);
		
		mset.remove("ciao", 9);
		assertEquals(mset.get("ciao"), 0);
		
		try {
			mset.remove("ciao");
			fail("Sarebbe dovuto bloccarsi qui");
		} catch (RuntimeException e) { }
		
		try {
			mset.remove("ciao", 10);
			fail("Sarebbe dovuto bloccarsi qui");
		} catch (RuntimeException e) { }
	}
	
	@Test
	public void testMax() {
		String[] stringhe = new String[]{"ciao", "ciao", "ciao", "orso", "orso"};
		Multiset<String> mset = new Multiset<>();
		
		for(String parola : stringhe) {
			mset.add(parola);
		}
		
		assertEquals(mset.getElementWithMaxOccurrences(), "ciao");
		
		assertEquals((new Multiset<String>()).getElementWithMaxOccurrences(), null);
	}
	
}
