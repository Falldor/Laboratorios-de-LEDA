package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;


	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		this.tail = (this.tail + 1)% array.length;
		if (isFull()) {
			throw new QueueOverflowException();
		}
		if (isEmpty()) {
			this.head++;
		}
		this.array[this.tail] = element;
		this.elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T elemento = this.array[this.head];
		this.head = (this.head + 1)% array.length;
		this.elements--;
		return elemento;
	}

	@Override
	public T head() {
		if(!isEmpty()){
			return this.array[this.head];
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == 0);
	}

	@Override
	public boolean isFull() {
		return this.elements == array.length;
	}

}
