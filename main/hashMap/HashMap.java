package main.hashMap;

import main.hashMap.exceptions.*;


public class HashMap<Key, Value> {
	private static int DEFAULT_SIZE = 13;
	private static int MAX_SIZE = (int) (Math.pow(2, 10)*DEFAULT_SIZE);
	
	private int size;
	
	public int getSize() {
		return size;
	}
		
	private class Node<Key, Value> {
		
		Key key;
		Value value;
		
		Node<Key, Value> next;
		
		public Node(Key key, Value value) {
			// TODO Auto-generated constructor stub
			this.key = key;
			this.value =value;
		}
	}
	
	Node<Key, Value> elements[];
	
	@SuppressWarnings("unchecked")
	public HashMap() {
		elements = new Node[DEFAULT_SIZE];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() throws MaxSizeException {
		
		size = 0; //Reset size
		
		Node<Key, Value>[] tempElements = elements;
		
		elements = new Node[elements.length << 1];
		
		for (Node<Key, Value> node : tempElements) {
			while (node != null) {
				this.put(node.key, node.value);
				node = node.next;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void put(Key key, Value value) throws MaxSizeException  {
		if (size == MAX_SIZE) {
			throw new MaxSizeException("Max size has reached");
		}
		
		if (size == elements.length) {
			resize();
		}
		
		if (elements[( (key.toString().hashCode() >>> 1 )% elements.length)] == null) {
			
			elements[( (key.toString().hashCode() >>> 1 )% elements.length)] = new Node<Key, Value>(key, value);

			size++;

			return;
		}

		Node<Key, Value> curr = elements[( (key.toString().hashCode() >>> 1 )% elements.length)];
		
		while ( (curr != null) && (curr.next) != null ) {
			curr = curr.next;
		}
		
		curr.next = new Node<Key, Value>(key, value);
		
		Node<Key, Value> temp = curr;
		
		size++;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Value get(Key key) throws EmptyMapException, NoKeyFoundException {
		
		if (size == 0) {
			throw new EmptyMapException("Hash map is empty");
		}
		
		if (elements[( (key.toString().hashCode() >>> 1 )% elements.length)] == null) {
			throw new NoKeyFoundException("There is no entry for key " + key.toString());
		}
		
		Node<Key, Value> curr = elements[( (key.toString().hashCode() >>> 1 )% elements.length)];
		
		if (curr == null) {
			throw new NoKeyFoundException("There is no entry for key " + key.toString());
		}
		
		while (!(curr.key ).equals(key)) {
			curr = curr.next;
			
			if (curr == null) {
				throw new NoKeyFoundException("There is no entry for key " + key.toString());
			}
		}
		
		return curr.value;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void remove(Key key) throws EmptyMapException, NoKeyFoundException {
		
		if(size == 0) {
			throw new EmptyMapException("Hash map is empty");
		}
		
		Node<Key, Value> curr = elements[( (key.toString().hashCode() >>> 1 )% elements.length)];
		
		if (curr == null) {
			throw new NoKeyFoundException("There is no entry for key " + key.toString());
		}
		
		if( (curr.key).equals(key))  {
			elements[( (key.toString().hashCode() >>> 1 )% elements.length)] = curr.next;
            size--;
            return;
        }
		
		Node<Key, Value> prev = curr;
		
		while ( !(curr.key).equals(key) ) {
            prev = curr;
            curr = curr.next;
            if (curr == null) {
    			throw new NoKeyFoundException("There is no entry for key " + key.toString());
    		}
         }
         
         prev.next = curr.next;
         size--;
		
	}
}
