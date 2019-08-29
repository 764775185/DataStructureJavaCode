package list;

import java.util.Arrays;

public class SequenceStack<T> {

	private int DEFAULT_SIZE = 10;
	private int capacity;
	private int capacityIncrement = 0;
	private Object[] elementData;
	private int size = 0;
	
	public SequenceStack() {
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	public SequenceStack(T element) {
		this();
		elementData[0] = element;
		size++;
	}
	public SequenceStack(T element, int initSize) {
		this.capacity = initSize;
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	public SequenceStack(T element, int initSize, int capacityInctrment) {
		this.capacity = initSize;
		this.capacityIncrement = capacityInctrment;
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	
	public int length() {
		return size;
	}
	
	public void push(T element) {
		ensureCapacity(size+1);
		elementData[size++] = element;
	}
	private void ensureCapacity(int minCapacity) {
		if(capacity < minCapacity) {
			if(capacityIncrement > 0) {
				while(capacity < minCapacity) {
					capacity += capacityIncrement;
				}
			}
			else {
				while(capacity < minCapacity) {
					capacity <<= 1;
				}
			}
			elementData = Arrays.copyOf(elementData, capacity);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T pop() {
		T value = (T)elementData[size-1];
		elementData[--size] = null;
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T)elementData[size-1];
	}
	
	public boolean empty() {
		return size ==0 ;
	}
	
	public void clear() {
		Arrays.fill(elementData, null);
		size = 0;
	}
	
	public String toString() {
		if(size==0) { return "[]"; }
		else {
			StringBuilder stringBuilder= new StringBuilder("[");
			for(int i=size-1; i>=0; i--) {
				stringBuilder.append(elementData[i].toString() + ",");
			}
			return stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]").toString();//StringBuilder是字符串缓存区对象要用toString()转String对象！！！
		}
	}
	
	public static void main(String[] args) {
		SequenceStack<String> strStack = new SequenceStack<String>();
		strStack.push("aaa");
		strStack.push("bbb");
		strStack.push("ccc");
		System.out.println(strStack);
		System.out.println("访问栈顶元素：" + strStack.peek() );
		strStack.pop();
		System.out.println(strStack);
		strStack.clear();
		System.out.println(strStack);
	}
}
