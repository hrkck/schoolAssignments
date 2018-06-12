package stack;

import java.util.ArrayList;
import java.util.List;

public class StackArrayListImpl<T> implements Stack<T> {
        List<T> stack = new ArrayList<T>();

        @Override
        public void push(T item) {
                stack.add(0, item);
        }

        @Override
        public T pop() {
                return stack.remove(0);
        }

        @Override
        public boolean empty() {
                return stack.isEmpty();
        }

        @Override
        public List<T> toList() {
                return stack;
        }
}
