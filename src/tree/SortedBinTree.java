package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//Comparable指的是一个接口而不是一个类，这点应该注重注意下，传入的T类型必须已经是实现了Comparable接口中compareTo()这个方法
public class SortedBinTree <T extends Comparable> {

	static class Node{
		Object data;
		Node parent;
		Node left;
		Node right;
		public Node() {}
		public Node(Object data, Node parent, Node left, Node right) {
			this.data = data ;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		public String toString() {
			return "[data="+data+"]";
		}
		public boolean equals(Object obj) {
			if(this==obj) {return true;}
			else {
				if(obj.getClass()==Node.class) {
					Node target = (Node) obj;
					return target.data.equals(data) 
							&& target.parent == parent
							&& target.left == left
							&& target.right == right;
				}
				return false;
			}
		}
		
		public int hashCode() {
			return data==null? null : data.hashCode();
		} 
	}
	
	private Node root;
	public SortedBinTree() {
		root = null;
	}
	public SortedBinTree(T data) {
		root = new Node(data, null, null, null);
	}
	
	@SuppressWarnings("unchecked")
	public void addNode(T ele) {
		if(root==null) {
			root = new Node(ele, null, null, null);
		}
		else {
			Node cur = root;
			Node parent = null;
			int cmp = 0;
			do {
				parent = cur;
				cmp = ele.compareTo(cur.data);
				if(cmp > 0) {
					cur = cur.right;
				}
				else {
					cur = cur.left;
				}
			}while(cur != null);//cur已经到达叶子节点的空子节点，即当前插入的位置
			
			Node newNode= new Node(ele, parent, null, null);
			if(cmp > 0) parent.right = newNode;
			else parent.left = newNode;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Node getNode(T ele) {
		Node pNode = root;
		int cmp = 0;
		while(pNode != null) {
			cmp = ele.compareTo(pNode.data);
			if(cmp > 0) pNode = pNode.right;
			else if(cmp < 0)pNode = pNode.left;
			else return pNode;
		}
		return null;
	}
	
	public void remove(T ele) {
		Node target = getNode(ele);
		//被删节点是根节点
		if(target == null) return;
		//被删节点是叶子节点
		if(target.left==null && target.right==null) {
			if(target==root) root=null;
			else {
				if(target == target.parent.left) target.parent.left=null;
				else target.parent.right=null;
			//	为了让删除的节点能垃圾回收，应该让对该节点的引用和该节点对其他节点的引用都置null
			//  target = null; 这行语句只让target这个临时变量制null，没有意义
				target.parent = null;
			}
		}
		//被删节点只有左子树
		else if(target.left!=null && target.right==null) {
			if(target==root) {
				root = target.left;
				
				target.left.parent = target.left = null;//便于垃圾回收
			}
			else {
				if(target == target.parent.left) {
					target.parent.left = target.left;	
				}
				else {
					target.parent.right = target.left;
				}
				target.left.parent = target.parent;
			
				target.parent = target.left = null;//便于垃圾回收
			}
		}
		//被删节点只有右子树
		else if(target.left==null && target.right!=null) {
			if(target==root) {
				root = target.right;
				
				target.right.parent = target.right =null;//便于垃圾回收
			}
			else {
				if(target == target.parent.left) {
					target.parent.left = target.right;	
				}
				else {
					target.parent.right = target.right;
				}
				target.right.parent = target.parent;
					
				target.parent = target.right =null;//便于垃圾回收
			}
		}
		//被删节点左右子树都存在
		else {
			Node leftMaxNode = target.left;
			while(leftMaxNode.right!=null) leftMaxNode = leftMaxNode.right;
			leftMaxNode.parent.right = null;
			leftMaxNode.parent = target.parent;
			if(target == target.parent.left) {
				target.parent.left = leftMaxNode;
			}
			else {
				target.parent.right = leftMaxNode;
			}
			leftMaxNode.left = target.left;
			leftMaxNode.right = target.right;
			target.parent = target.left = target.right =null;  // 便于垃圾回收
		}		
	}
	public  List<Node> breadthFirst(){
		Queue<Node> queue  = new ArrayDeque<>();
		List<Node> list = new ArrayList<>();
		if( root != null) {
		queue.offer(root);
		        }
		        while(!queue.isEmpty()){
		            list.add( queue.peek() );
		            Node p = queue.poll();
		            if(p.left!=null) queue.offer(p.left);
		            if(p.right!=null) queue.offer(p.right);
		        }
		       return list;
		}
	
	public static void main(String[] args) {
		SortedBinTree<Integer> tree = new SortedBinTree<>();
		tree.addNode(5);
		tree.addNode(20);
		tree.addNode(10);
		tree.addNode(3);
		tree.addNode(8);
		tree.addNode(15);
		tree.addNode(30);
		System.out.println(tree.breadthFirst());
		tree.remove(20);
		System.out.println(tree.breadthFirst());
	}
}
