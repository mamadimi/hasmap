
public class HMapThreadSafety {
	
	HMap map ;
	
	public HMapThreadSafety() {
		map = new HMap();
	}
	
	public synchronized void put(String key, String value) {		
		map.put(key, value);
	}
	
	public synchronized String get(String key) {
        return map.get(key);    
    }
    
    public synchronized void remove(String key) {
        map.remove(key);
    }

}
