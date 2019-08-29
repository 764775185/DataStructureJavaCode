package list;

import java.util.Arrays;

public class SequenceList<T> {

	private int DEFAULT_SIZE = 16;
	private int capacity;
	private Object[] elementData; //此处Object不能换成T，因为泛型<T>不是一个真正的类/类型，不能实例化，只能用来声明
	private int size = 0;
	public  SequenceList() {
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	public  SequenceList(T element) {
		this();
		elementData[0] = element;
		size++;
	}
	//以指定的长度来创建顺序线性表
	public  SequenceList(T element, int initSize) {
		capacity = 1;
		while(capacity < initSize) {
			capacity <<= 1;
		}
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	
	public int getlength() {
		return size;
	}
	
	@SuppressWarnings("unchecked") //配合后面的强制转换，否则会警告Type safety: Unchecked cast from Object to T
	public T getElementByIndex(int i) {
		if( i<0 || i>=size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		return (T)elementData[i];  //此处必须进行强制转换，因为elementData[]的元素全是Object类型
	}
	
	public int locate(T element) {
		for(int i=0; i<size; i++) {
			if(elementData[i].equals(element)) return i; //注意此处比较两个元素是否相等要用equals()函数，因为两者在类型上和实际对象上都不相等，即hashCode不同
		}
		return -1;
	}
	
	public void insert(T element, int index) {
		if( index<0 || index>size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = element;
		size++;
	}
	
	private void ensureCapacity(int minCapacity) {
		while(capacity < minCapacity) {
			capacity<<=1;
		}
		elementData = Arrays.copyOf(elementData, capacity);//了解copyOf(arr,int newlength)与copyOfRange(arr,int fromIndex,int toIndex)前闭后开
	}
	
	public void add(T element) {
		insert(element, size);
	}
	
	@SuppressWarnings("unchecked")
	public T delete(int index) {
		if( index<0 || index>size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		T value = (T)elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;  //对无用内存制空，便于JVM垃圾回收
		return value;
	}
	
	public T remove(){
		return delete(size-1);
	}
	
	public void clear() {
		Arrays.fill(elementData, null);
		size = 0;
	}
	
	public boolean empty() {
		return size ==0 ;
	}
	
	//打印一个类对象时会自动调用toString方法，如果是自己写的类要记得覆盖这个方法
	public String toString() {
		if(size==0) { return "[]"; }
		else {
			StringBuilder stringBuilder= new StringBuilder("[");
			for(int i=0; i<size; i++) {
				stringBuilder.append(elementData[i].toString() + ",");
			}
			return stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]").toString();//StringBuilder是字符串缓存区对象要用toString()转String对象！！！
		}
	}
	
	public static void main(String[] args) {
		SequenceList<String> strList = new SequenceList<String>();
		strList.add("aaa");
		strList.add("bbb");
		strList.insert("ccc", 1);
		System.out.println(strList);
		strList.delete(1);
		System.out.println(strList);
		System.out.println("bbb在顺序线性表中的索引值：" + strList.locate("bbb")  );
		strList.clear();
		System.out.println(strList);
	}
}
