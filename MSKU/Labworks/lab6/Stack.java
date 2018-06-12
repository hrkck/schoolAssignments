package stack;

import java.util.List;

public interface Stack<T> {
        public void push(T item);
        public T pop();
        public boolean empty();
        public List<T> toList();
}
