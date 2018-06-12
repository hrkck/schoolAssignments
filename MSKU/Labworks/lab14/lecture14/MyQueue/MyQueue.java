
public class MyQueue<T> implements Queue<T>{

	Node<T> first;
	int capacity;
	
	public  MyQueue(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public synchronized void add(T obj) throws QueueException {
		if(capacity == size()){
			throw new QueueException("Max capacity is reached");
		}
		Node<T> node = new Node<T>();
		node.setObj(obj);
		if (first == null){
			first = node;
		}else{
			Node current = first; 
			while (current.getNext()!= null){
				current = current.getNext();
			}
			current.setNext(node);
		}
	}

	@Override
	public synchronized T remove() throws QueueException {
		if(first == null){
			throw new QueueException("No element");
		}else{
			T obj = first.getObj();
			first = first.getNext();
			return obj;
		}
		
	}

	@Override
	public synchronized int size() {
		int count = 0;
		Node current = first;
		while (current != null){
			current = current.getNext();
			count++;
		}
		return count;
	}

}
