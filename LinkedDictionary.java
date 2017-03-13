import java.util.NoSuchElementException;

import javax.xml.soap.Node;

public class LinkedDictionary <K extends Comparable<? super K>,V> implements DictionaryInterface<K,V>
{
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedDictionary(){
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public V add( K key, V value){
		V result = null;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while(( currentNode != null) && !(key.compareTo(currentNode.getKey()) > 0)){
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		if( (currentNode !=null) && key.equals(currentNode.getKey())) {
			result = currentNode.getValue();
			currentNode.setValue(value);
		}
		else
		{
			Node newNode = new Node( key, value);
			if(nodeBefore == null){
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else{
				newNode.setNextNode(currentNode);
				nodeBefore.setNextNode(newNode);
			}
				numberOfEntries++;
		}
		return result;
	}
	public V remove ( K key){
		V result = null;
		
		if(!isEmpty()){
			Node currentNode = firstNode;
			Node nodeBefore = null;
			
			while (( currentNode != null) && !key.equals(currentNode.getKey()) ){
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			}
			if( currentNode != null){
				Node nodeAfter = currentNode.getNextNode();
				if(nodeBefore == null)
					firstNode = nodeAfter;
				else
					nodeBefore.setNextNode(nodeAfter);
				
				result = currentNode.getValue();
				numberOfEntries--;
			}// end if
		}//end if
		return result;
	}
	public V getValue( K key){
		V result = null;
		
		Node currentNode = firstNode;
		while (( currentNode != null) && !key.equals(currentNode.getKey()) ){
			currentNode = currentNode.getNextNode();
		}
		if( currentNode != null){
			result = currentNode.getValue();
		}
		return result;
	}
	public boolean contains(K key){
		return getValue(key) != null;
	}
	
	public boolean isEmpty(){
		return numberOfEntries == 0;
	}
	
	public boolean isFull(){
		return false;
	}
	
	public int getSize(){
		return numberOfEntries;
	}
	
	public final void clear(){
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public Iterator<K> getKeyIterator(){
		return new KeyIterator();
	}
	
	public Iterator<V> getValueIterator(){
		return new ValueIterator();
	}
	
	private class KeyIterator implements Iterator<K>{
		private Node nextNode;
		
		private KeyIterator()
		{
			nextNode = firstNode;
		} 
		
		public boolean hasNext() 
		{
			return nextNode != null;
		} 
		
		public K next()
		{
			K result;
			
			if (hasNext())
			{
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			}
			else
			{
				throw new NoSuchElementException();
			} 
		
			return result;
		} 
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		} 
	} private class ValueIterator implements Iterator<V>
	{
		private Node nextNode;
		
		private ValueIterator()
		{
			nextNode = firstNode;
		} 
		
		public boolean hasNext() 
		{
			return nextNode != null;
		} // end hasNext
		
		public V next()
		{
			V result;
			
			if (hasNext())
			{
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			}
			else
			{
				throw new NoSuchElementException();
			} 
		
			return result;
		} 
		
		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		} 
	}
	private class Node  
	{
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue)
		{
			key = searchKey;
			value = dataValue;
			next = null;	
		} 
		
		private Node(K searchKey, V dataValue, Node nextNode)
		{
			key = searchKey;
			value = dataValue;
			next = nextNode;	
		} 
		
		private K getKey()
		{
			return key;
		} 
		
		private V getValue()
		{
			return value;
		} 

		private void setValue(V newValue)
		{
			value = newValue;
		} 

		private Node getNextNode()
		{
			return next;
		} 
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} 
	}
	
}
