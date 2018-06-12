package stack;

public class StackItem {
	private Object item;
	private StackItem next;
	
	public StackItem(Object item){
		this.item = item;
	}

	public StackItem getNext() {
		return next;
	}

	public void setNext(StackItem next) {
		this.next = next;
	}

	public Object getItem() {
		return item;
	}

	
	
}
