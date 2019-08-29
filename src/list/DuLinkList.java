package list;

public class DuLinkList<T> {

	private class Node{
		private T data;
		private Node prev;
		private Node next;
	//	public Node() {}
		public Node(T data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	private int size = 0;
	
	public DuLinkList() {
		head = null;
		tail = null;
	}
	public DuLinkList(T element) {
		head = new Node(element,null,null);
		tail = head;
		size++;
	}
	
	public int length() {
		return size;
	}
	
	public T getElement(int index) {
		return getElementByIndex(index).data;
	}
	
	public Node getElementByIndex(int index) {
		if( index<0 || index>=size ) throw new IndexOutOfBoundsException("线性表索引越界！");
		if(index <= size/2) {
			Node current = head;
		    for(int i=0; i<index; i++) {
		    	current = current.next;
		    }
		    return current;
		}else {
			Node current = tail;
		    for(int i=size-1; i>index; i--) {
		    	current = current.prev;
		    }
		    return current;
		}
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
				Node nextNode = preNode.next;
				Node newNode = new Node(element, preNode, nextNode);
				preNode.next = newNode;
				if(nextNode!=null) {   //先判断再操作???
					nextNode.prev = newNode;
				}
				size++;
			}
		}
	}
	//尾插法
	public void add(T element) {
		if(head==null) {
			head = new Node(element, null, null);
			tail = head;
		}
		else {
			Node newNode = new Node(element, tail, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	//头插法
	public void addAtHead(T element) {
		head = new Node(element, null, head);
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
			head.prev = null;
		}
		else {
			Node preNode = getElementByIndex(index - 1);
			del = preNode.next;
			preNode.next = del.next;
			if(del.next!=null) {  //先判断再操作，判断不能少！！
				del.next.prev = preNode;
			}
		}
		del.prev = null;
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
	
	public String reverseToString() {
		if(empty()) {
			return "[]";
		}
		else {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("[");
			for(Node current=tail; current!=null; current=current.prev) {
				stringBuilder.append(current.data.toString() + ",");
			}
			return stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]").toString();
		}
	}
	
	public static void main(String[] args) {
		DuLinkList<String> strList = new DuLinkList<String>();
		strList.insert("aaa", 0);
		strList.add("bbb");
		strList.insert("ccc", 0);
		strList.insert("ddd", 1);
		System.out.println(strList);
		System.out.println("bbb在双向链式线性表中的索引值：" + strList.locate("bbb") );
		strList.delete(2);
		System.out.println(strList);
		System.out.println(strList.reverseToString());
		System.out.println("双向链表索引1处的元素：" + strList.getElement(1)  );
		strList.remove();
		System.out.println("调用remove方法后的链表："+ strList);
	}
	
}
