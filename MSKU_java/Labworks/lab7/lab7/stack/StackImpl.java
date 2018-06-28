package stack;

import java.util.ArrayList;
import java.util.List;

public class StackImpl<T> extends AbstractStack<T> {

	StackItem<T> top;
	
	@Override
	public void push(T item) {
		StackItem<T> newTop = new StackItem<T>(item);
		newTop.setNext(top);
		top = newTop;
	}

	@Override
	public T pop() {
		T item = top.getItem();
		top = top.getNext();
		return item;
	}

	@Override
	public boolean empty() {
		return (top == null);
	}

	@Override
	public List<T> toList() {
		List<T> lst = new ArrayList<T>();
		StackItem<T> current = top;
		while(current != null){
			lst.add(current.getItem());
			current = current.getNext();
		}
		return lst;
	}


}
