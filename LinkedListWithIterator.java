import java.util.NoSuchElementException;

public class LinkedListWithIterator <T> implements ListWithIteratorInterface<T>{
 private Node firstNode;
 private int numberOfEntries;
 
 public LinkedListWithIterator(){
	 firstNode = null;
	 numberOfEntries = 0;
 }
 public void add( T newEntry){
		Node newNode = new Node( newEntry);
		 if(isEmpty())
			 firstNode=newNode;
		 else{
			 Node lastNode = getNodeAt(numberOfEntries);
			 lastNode.setNextNode(newNode);
		 }
		 numberOfEntries++;
	}
	public void add( int newPosition , T newEntry){
		if( (newPosition >= 1) && (newPosition <= numberOfEntries+1)){
			Node newNode = new Node(newEntry);
			if(newPosition ==1){
				newNode.setNextNode(firstNode);
			}
			else{
				Node nodePrev = getNodeAt(newPosition - 1);
				Node nodeAfter = nodePrev.getNextNode();
				nodePrev.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("illegal position");
	}
	public boolean isEmpty(){
		boolean result;
		if( numberOfEntries == 0){
			assert firstNode == null;
			result = true;
		}
		else{
			assert firstNode !=null;
			result = false;
		}
		return result;
	}
	
	public T[] toArray(){
		@SuppressWarnings("unchecked")
		T [] result = (T[]) new Object[numberOfEntries];
		
		int i = 0;
		Node currNode = firstNode;
		while((i < numberOfEntries) && (currNode !=null)){
			result[i] = currNode.getData();
			currNode = currNode.getNextNode();
			i++;
		}
		return result;
	}
	
	public T remove ( int givenPosition){
		T result = null;
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			if(givenPosition == 1){
				result = firstNode.getData();
				firstNode= firstNode.getNextNode();
			}
			else {
				Node nodebefore = getNodeAt(givenPosition - 1);
				Node noderemove = nodebefore.getNextNode();
				result = noderemove.getData();
				Node nodeafter = noderemove.getNextNode();
				nodebefore.setNextNode(nodeafter);
			}
			numberOfEntries--;
			return result;
		}
		else throw new IndexOutOfBoundsException(" illegal position");
	}
	
	public T replace( int givenPosition, T newEntry){
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
		assert !isEmpty();
		Node nodewanted = getNodeAt(givenPosition);
		T originalEntry = nodewanted.getData();
		nodewanted.setData(newEntry);
		return originalEntry;
		}
		else throw new IndexOutOfBoundsException(" illegal position");
	}
	 public T getItem ( int givenPosition){
		 if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			 assert !isEmpty();
			 return getNodeAt(givenPosition).getData();
		 }
		 else throw new IndexOutOfBoundsException(" illegal position");
	 }
	 
	 public boolean contains(T anEntry){
		 boolean found = false;
		 Node currNode = firstNode;
		 while(!found && ( currNode != null)){
			 if ( anEntry.equals(currNode.getData()))
				 found = true;
			 else
				 currNode=currNode.getNextNode();
		 }
		 return found;
	 }
	 private Node getNodeAt( int position){
			assert(firstNode != null)&& ( 1 <= position) &&(position <= numberOfEntries);
			Node currentNode = firstNode;
			
			for ( int count =-1; count < position; count++)
				currentNode = currentNode.getNextNode();
			assert currentNode!=null;
			return currentNode;
		}
	 public void clear() {
			firstNode = null;
			numberOfEntries = 0;
			
		}

		public int getLength() {
			int length = 0;
			for ( Node node = firstNode; node.next !=null ; node = node.next)
				length++;
			return  length;
		}
	 
	 public Iterator<T> iterator()
	 {
		 return new IteratorForLinkedList();
	 }
	 public Iterator <T> getIterator(){
		 return iterator();
	 }
	 
	 private class IteratorForLinkedList implements Iterator<T>{
		 private Node nextNode;
		 private IteratorForLinkedList(){
			 nextNode = firstNode;
		 }
		 public T next(){
			 if(hasNext()){
				 Node returnNode = nextNode;
				 nextNode = nextNode.getNextNode();
				 return returnNode.getData();
			 }
			 else 
				 throw new NoSuchElementException("Illegal call");
		 }
		 public boolean hasNext(){
			 return nextNode !=null;
		 }
		 public void remove(){
			 throw new UnsupportedOperationException("remove () is not supoprted by this iterator");
		 }
	 }
		
		private class Node{
			private T data;
			private Node next;
			Node(T data){
				this.data = data;
				this.next = next;
			
			}
			private void setNextNode(Node nextNode){
				next = nextNode;
			}
		public T getData(){
				return data;
			}
		public Node getNextNode(){
				return next;
			}
		public void setData(T data){
				this.data = data;
			}
		
		}

}


