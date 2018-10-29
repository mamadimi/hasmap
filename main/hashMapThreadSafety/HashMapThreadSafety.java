package main.hashMapThreadSafety;

import main.hashMap.HashMap;
import main.hashMap.exceptions.*;


public class HashMapThreadSafety<Key, Value> extends HashMap<Key, Value> {
	
	HashMap<Key, Value> hMap ;
	
	public HashMapThreadSafety() {
		// TODO Auto-generated constructor stub
		hMap = new HashMap<Key, Value>();
	}

	public synchronized void put(Key key, Value value) throws MaxSizeException {
		// TODO Auto-generated method stub
		hMap.put(key, value);
	}
	
	@Override
	public synchronized Value get(Key key) throws EmptyMapException, NoKeyFoundException {
		// TODO Auto-generated method stub
		return hMap.get(key);
	}
	
	@Override
	public synchronized void remove(Key key) throws EmptyMapException, NoKeyFoundException {
		// TODO Auto-generated method stub
		hMap.remove(key);
	}
	
}
