package stack;

import java.util.List;

public abstract class AbstractStack<T> implements Stack<T> {

	
	@Override
	public void addAll(Stack<? extends T> aStack) {
		List<? extends T> lst = aStack.toList();
		int i = lst.size()-1;
		while (i >= 0){
			push(lst.get(i));
			i--;
		}
		
	}
}
