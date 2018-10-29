package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import main.hashMap.HashMap;
import main.hashMap.exceptions.*;

class HashMapTest {
	
	HashMap<String, String> hMap = null;

	@Test
	void putRandomHundrendElementsGetARandom() {
		hMap = new HashMap<String, String>();
		
		int iterator = 500;
		
		int randPos = (int) (iterator*Math.random());
		String randStringGetTest = null;
		
		for (int i = 0; i < iterator; i++) {
			String randString = new Double(Math.random()).toString();
			
			if (i == randPos) {
				randStringGetTest = randString;
			}
			
			try {
				hMap.put(randString, new Integer(i).toString());
			} catch (MaxSizeException e) {
				e.printStackTrace();
			}
		}
		
		try {
			assertEquals("Get a random string correctly", new Integer(randPos).toString(), hMap.get(randStringGetTest));
		} catch (EmptyMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoKeyFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void getEmptyMapException() {
		hMap = new HashMap<String, String>();
		
		Assertions.assertThrows(EmptyMapException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				
				hMap.get("Test");
				
			}
		});
	}

}
