public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T> {
	public Iterator <T> getIterator();
}
