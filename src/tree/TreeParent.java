package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeParent<E> {  //泛型标记符<E>，Element在集合中使用

	public static class Node<T>{  //泛型标记符<T>，Type在集合中使用

		T data;
		int parent;
		public Node() {}
		public Node(T data) {
			this.data = data; 
		}
		public Node(T data, int parent) {
			this.data = data; 
			this.parent = parent;
		}
		public String toString() {
			return "TreeParent$Node[data="+data+", parent=" +parent+"]";
		}
	}
	private final int DEFAULT_SIZE = 100;
	private int treeSize = 0;  //数组存储需提前预知空间大小
	private Node<E>[] nodes;
	private int nodeNum = 0;
	
	@SuppressWarnings("unchecked")
	public TreeParent(E data) {
		treeSize = DEFAULT_SIZE;
		nodes = new Node[treeSize]; //Type safety: The expression of type TreeParent.Node[] needs unchecked conversion to conform to TreeParent.Node<E>[]
		nodes[0] = new Node<E>(data, -1);
		nodeNum++;
	}
	
	@SuppressWarnings("unchecked")
	public TreeParent(E data, int initSize) {
		treeSize = initSize;
		nodes = new Node[treeSize];  
		nodes[0] = new Node<E>(data, -1);
		nodeNum++;
	}
	
	public void addNode(E data, Node parent) { //替换成Node<E> parent将不会警告
		for(int i=0; i<treeSize; i++) {
			if(nodes[i]==null) {
				nodes[i] = new Node<E>(data, pos(parent) );
				nodeNum++;
				return;
			}
		}
		throw new RuntimeException("该树已满，无法添加新节点！");
	}
	public int pos(Node<E> node) {
		for(int i=0; i<treeSize; i++) {
			if(nodes[i]==node) return i;
		}
		return -1;
	}	
	public boolean empty() {
		return nodes[0]==null;
	}
	
	public Node<E> root(){
		return nodes[0];
	}
	//返回父节点
	public Node<E> parent(Node<E> node){
		return nodes[node.parent];
	}
	
	//返回子节点
	public List<Node<E>> children(Node<E> node){
		List<Node<E>> list = new ArrayList<Node<E>>();
		for(int i=0; i<treeSize; i++) {
			if(nodes[i]!=null && nodes[i].parent==pos(node))  list.add(nodes[i]);
		}
		return list;
	}
	
	public int deep() {
		int max = 0;
		for(int i=0; i<treeSize && nodes[i]!=null; i++) {
			int def = 1;
			int par = nodes[i].parent;
			while(par!=-1 && nodes[par]!=null ) {
				par = nodes[par].parent;
				def++;
			}
			if(max < def) {
				max = def;
			}
		}
		return max; 
	}
	
	public static void main(String[] args) {
		TreeParent<String> tp = new TreeParent<String>("root");
		TreeParent.Node root = tp.root(); //替换成TreeParent.Node<String>将不会警告
		System.out.println(root);
		
		tp.addNode("node1", root);
		System.out.println("此树的深度："+ tp.deep());
		tp.addNode("node2", root);
		List<TreeParent.Node<String>> nodeChild = tp.children(root);
		System.out.println("根节点的第一个子节点为："+ nodeChild.get(0));
		
		tp.addNode("node3", nodeChild.get(0));
		System.out.println("此树的深度："+ tp.deep());
	}
}
