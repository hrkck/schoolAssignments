package stack;

public class StackItem<T> {
	private T item;
	private StackItem<T> next;
	
	public StackItem(T item){
		this.item = item;
	}

	public StackItem<T> getNext() {
		return next;
	}

	public void setNext(StackItem<T> next) {
		this.next = next;
	}

	public T getItem() {
		return item;
	}

	
	
}
