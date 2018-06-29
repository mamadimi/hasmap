 public class HMap {
     private static int  DEFAULT_SIZE =  13;
     private int size = 0;
     
     Node[] elements;
     
     private class Node{
         
         String key,value;
         
         Node next;
               
         Node(String key, String value){
             this.key = key;
             this.value = value;
         }
     
     }
     
     public HMap() {
        elements = new Node[DEFAULT_SIZE];
     }
     
     public void resize(){
        Node[] before = elements;
        elements = new Node[elements.length << 1];
        
        for (Node node : before) {
            Node curr = node;
            
            do {
            	if (curr == null) {
            		break;
            	}
                this.put(curr.key, curr.value);
                curr = curr.next;
            } while (curr != null);
        }
     
     }

     public void put(String key, String value) {
         if (size == elements.length) {
             resize();
         }
         
         Node currentNode = elements[( key.hashCode() >>> 1 ) % elements.length];
         
         if (currentNode == null) {
             elements[( key.hashCode() >>> 1 ) % elements.length] = new Node(key,value);
             size++;
             return;
         }
         
         while ((currentNode != null) && (currentNode.next) != null) {
             currentNode = currentNode.next;
         }
     
         currentNode.next = new Node(key,value);
         size++;

     }

     public String get(String key) {
         
        Node currentNode = elements[( key.hashCode() >>> 1 ) % elements.length];
        
        if (currentNode == null) {
            return null;
        }
        
        while (!(currentNode.key).equals(key)) {
            currentNode = currentNode.next;
        }
        
        return currentNode == null ? null : currentNode.value;
        
     }
     
     public void remove(String key) {
     
         Node currentNode = elements[( key.hashCode() >>> 1 ) % elements.length];
         Node previousNode = currentNode;
         
         if(currentNode == null ) return ; 

         //remove first node of linked list
         if( (currentNode.key).equals(key))  {
             elements[( key.hashCode() >>> 1 ) % elements.length] = currentNode.next;
             size--;
             return;
         }
       
         while ( !(currentNode.key).equals(key) ) {
            previousNode = currentNode;
            currentNode = currentNode.next;
            if(currentNode == null ) return;
         }
         
         previousNode.next = currentNode.next;
         size--;
        
     }
 }

