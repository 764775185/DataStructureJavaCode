package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree{

	public static class Node<T>{
		T data;
		double weight;
		Node<T> leftChild;
		Node<T> rightChild;
		public Node(T data, double weight) {
			this.data = data;
			this.weight = weight;
		}
		public String toString() {
			return "Node[data="+data+", weight="+weight+"]";
		}
	}
	
	//此处第一个<T>不可省略,这个是java声明泛型方法的固定格式，在方法的返回值之前的位置声明
	private static <T> Node<T> creatTree(List<Node<T>> nodes){ 
		while(nodes.size()>1) {
			quickSort(nodes);
			Node<T> left = nodes.get(nodes.size()-1);
			Node<T> right = nodes.get(nodes.size()-2);
			Node<T> parent = new Node<T>(null, left.weight+right.weight);
			parent.leftChild = left;
			parent.rightChild = right;
			
			nodes.remove(nodes.size()-1);
			nodes.remove(nodes.size()-1);
			nodes.add(parent);
		}
		return nodes.get(0);
	}
	
	public static <T> void quickSort(List<Node<T>> nodes) {
		subSort(nodes, 0 , nodes.size()-1);
	}
	private static <T> void subSort(List<Node<T>> nodes, int start, int end) {
		if(start<end) {
			Node<T> base = nodes.get(start);
			int i =start+1;
			int j = end;
			while(true) {
				while(i<end && nodes.get(i).weight >= base.weight) i++;
				while(j>start && nodes.get(j).weight < base.weight) j--;
				if(i<j) {
					swap(nodes,i,j);
				}
				else {
					break;
				}
			}
			swap(nodes,start,j);
			subSort(nodes, start, j-1);
			subSort(nodes, j+1, end);
		}	
	}
	
	private static <T> void swap(List<Node<T>> nodes, int i, int j) {
		Node<T> tmp = nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, tmp);
	}
	
	public static List<Node> breadthFirst(Node root){
		Queue<Node> queue  = new ArrayDeque<>();
		List<Node> list = new ArrayList<>();
		if( root != null) {
		queue.offer(root);
		        }
		        while(!queue.isEmpty()){
		            list.add( queue.peek() );
		            Node p = queue.poll();
		            if(p.leftChild!=null) queue.offer(p.leftChild);
		            if(p.rightChild!=null) queue.offer(p.rightChild);
		        }
		       return list;
		}

	public static void main(String[] args) {
		List<Node<String>> nodes = new ArrayList<>();
		nodes.add(new Node<String>("A", 40.0));
		nodes.add(new Node<String>("B", 7.0));
		nodes.add(new Node<String>("C", 10.0));
		nodes.add(new Node<String>("D", 30.0));
		nodes.add(new Node<String>("E", 12.0));
		nodes.add(new Node<String>("F", 2.0));
		Node<String> root = HuffmanTree.creatTree(nodes);
		System.out.println(breadthFirst(root));
	}
}
