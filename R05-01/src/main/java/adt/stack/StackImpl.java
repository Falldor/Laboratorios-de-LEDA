package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		this.array = (T[]) new Object[size];
		this.top = -1;
	}

	@Override
	public T top() {
		if (!isEmpty()){
			return this.array[this.top];
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (this.top < 0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.top == this.array.length - 1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void push(T element) throws StackOverflowException{
		if (isFull()) {
			throw new StackOverflowException();
		}
		this.top ++;
		this.array[this.top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException{
		if (isEmpty()){
			throw new StackUnderflowException();
		}
		this.top -= 1;
		return this.array[this.top + 1];
	}

}
