package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeChild<E> {

	private static class SonNode{
		private int pos;
		private SonNode next;
		public SonNode(int pos, SonNode next) {
			this.pos = pos;
			this.next = next;
		}
	}
	private static class Node<T>{
		T data;
		SonNode first;
		public Node(T data) {
			this.data = data;
			this.first = null;
		}
		public String toString() {
			if(first != null) {
				return "TreeChild$Node[data="+data+", first="+first.pos+"]";
			}
			else {
				return "TreeChild$Node[data="+data+", first=-1";
			}
		}
	}
	private final int DEFAULT_TREE_SIZE = 100;
	private int treeSize = 0;
	private Node<E>[] nodes;
	private int nodeNum;
	
	@SuppressWarnings("unchecked")
	public TreeChild(E data){
		treeSize = DEFAULT_TREE_SIZE;
		nodes = new Node[treeSize];
		nodes[0] = new Node<E>(data);
		nodeNum++;
	}
	
	@SuppressWarnings("unchecked")
	public TreeChild(E data, int treeSize){
		treeSize = treeSize;
		nodes = new Node[treeSize];
		nodes[0] = new Node<E>(data);
		nodeNum++;
	}
	
	public void addNode(E data, Node parent) {
		for(int i= 0; i<treeSize; i++) {
			if(nodes[i]==null) {
				nodes[i] = new Node<E>(data);
				if(parent.first==null) {
					parent.first = new SonNode(i, null);
				}
				else {
					SonNode next = parent.first;
					while(next.next!=null) {
						next = next.next;
					}
					next.next = new SonNode(i, null);
				}
				nodeNum++;
				return;
			}
		}
		throw new RuntimeException("该树已满，无法添加新节点！");
	}
	
	public boolean empty() {
		return nodes[0]==null;
	}
	
	public Node<E> root(){
		return nodes[0];
	}
	
	//返回all子节点
	public List<Node<E>> children(Node<E> node){
		List<Node<E>> list = new ArrayList<Node<E>>();
		SonNode next = node.first;
		while(next!=null) {
			list.add(nodes[next.pos]);
			next = next.next;
		}
		return list;
	}
	
	//返回index子节点
	public Node<E> children(Node<E> node, int index){
		SonNode next = node.first;
		int i=0;
		while(next!=null && i<index) {
			next = next.next;
		}
		return nodes[next.pos];
	}
	
	public int deep() {
		return deep(root());
	}
	
	private int deep(Node node) {
		if(node.first == null) return 1;
		else {
			int max = 0;
			SonNode next = node.first;
			while(next!=null) {
				int tmp = deep(nodes[next.pos]);
				if(tmp > max) max = tmp;
				next = next.next;
			}
			return max+1;
		}
	}
	
	public int pos(Node node) {
		for(int i=0; i<treeSize; i++) {
			if(nodes[i]==node)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		TreeChild<String> tc = new TreeChild<String>("root");
		TreeChild.Node root = tc.root(); //替换成TreeChild.Node<String>将不会警告
		System.out.println(root);
		
		tc.addNode("node1", root);
		System.out.println("此树的深度："+ tc.deep());
		tc.addNode("node2", root);
		List<TreeChild.Node<String>> nodeChild = tc.children(root);
		System.out.println("根节点的第一个子节点为："+ nodeChild.get(0));
		
		tc.addNode("node3", nodeChild.get(0));
		System.out.println("此树的深度："+ tc.deep());
	}
}
