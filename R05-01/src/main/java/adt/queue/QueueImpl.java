package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (!isEmpty()) {
			return array[0];
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.tail < 0;
	}

	@Override
	public boolean isFull() {
		return this.tail == array.length;
	}

	private void shiftLeft() {
		for (int i = 0; i < array.length - 1; i++){
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		++this.tail;
		if (isFull()){
			throw new QueueOverflowException();
		}
		this.array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()){
			throw new QueueUnderflowException();
		}
		T resultado = this.array[0];
		shiftLeft();
		tail--;
		return resultado;
	}

}
