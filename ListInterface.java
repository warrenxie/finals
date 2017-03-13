

public interface ListInterface<T> {

	public void add(T newItem);

	public void add(int newPosition, T newItem);
	

	public T remove(int position);
	

	public void clear();
	

	public T replace(int position, T newItem);
	
	

	public T getItem(int positon);

	public T[] toArray();
	

	public boolean contains(T anItem);
	

	public int getLength();
	

	public boolean isEmpty();
}

