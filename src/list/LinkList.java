package list;

public class LinkList<T> {

	private class Node{
		private T data;
		private Node next;
	//	public Node() {}
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	private int size = 0;
	
	public LinkList() {
		head = null;
		tail = null;
	}
	public LinkList(T element) {
		head = new Node(element,null);
		tail = head;
		size++;
	}
	
	public int length() {
		return size;
	}
	
	public Node getElementByIndex(int index) {
		if( index<0 || index>=size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		Node current = head;
		for(int i=0; i<index; i++) {
			current = current.next;
		}
		return current;
	}
	
	public int locate(T element) {
		Node current = head;
		for(int i=0; i<size && current!=null; i++,current = current.next) {
			if(current.data.equals(element)) return i;
		}
		return -1;
	}
	
	public void insert(T element, int index) {
		if( index<0 || index>size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		if(head==null) {
			add(element);
		}
		else {
			if(index==0) {
				addAtHead(element);
			}
			else {
				Node preNode = getElementByIndex(index - 1);
				preNode.next = new Node(element, preNode.next);
				size++;
			}
		}
	}
	//尾插法
	public void add(T element) {
		if(head==null) {
			head = new Node(element, null);
			tail = head;
		}
		else {
			Node newNode = new Node(element, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	//头插法
	public void addAtHead(T element) {
		head = new Node(element, head);
		if(tail==null) {  //判断原来是否为空链
			tail = head;    
		}
		size++;
	}
	
	public T delete(int index) {
		if( index<0 || index>=size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		Node del = null;
		if(index==0) {
			del = head;
			head = head.next;
		}
		else {
			Node preNode = getElementByIndex(index -1 );
			del = preNode.next;
			preNode.next = del.next;
		}
		del.next = null;  
		size--;
		return del.data;
	}
	
	public T remove() {
		return delete(size - 1);
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean empty() {
		return size==0;
	}
	
	public String toString() {
		if(empty()) {
			return "[]";
		}
		else {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("[");
			for(Node current=head; current!=null; current=current.next) {
				stringBuilder.append(current.data.toString() + ",");
			}
			return stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]").toString();
		}
	}
	
	public static void main(String[] args) {
		LinkList<String> strList = new LinkList<String>();
		strList.add("aaa");
		strList.add("bbb");
		strList.insert("ccc", 1);
		System.out.println(strList);
		strList.delete(1);
		System.out.println(strList);
		System.out.println("bbb在链式线性表中的索引值：" + strList.locate("bbb")  );
		strList.clear();
		System.out.println(strList);
	}
}



