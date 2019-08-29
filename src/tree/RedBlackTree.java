package tree;

import javax.swing.tree.FixedHeightLayoutCache;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.ietf.jgss.Oid;

import tree.SortedBinTree.Node;

public class RedBlackTree<T extends Comparable> {

	private static final boolean RED = false;
	private static final boolean BLACK= true;
	static class Node{
		Object data;
		Node parent;
		Node left;
		Node right;
		boolean color = BLACK;
		public Node(Object data,Node parent, Node left,Node right) {
			this.data = data;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		public String toString() {
			return "[data="+data+", color="+color+"]";
		}
		
		public boolean equals(Object obj) {
			if(this==obj) {return true;}
			else {
				if(obj.getClass()==Node.class) {
					Node target = (Node) obj;
					return target.data.equals(this.data)
							&& target.parent == this.parent
							&& target.left == this.left
							&& target.right == this.right;
				}
				return false;
			}
		}
		
		public int hashCode() {
			return data==null? null : data.hashCode();
		} 
	}
	
	private Node root;
	public RedBlackTree() {
		root = null;
	}
	public RedBlackTree(T data) {
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
			fixAfterInsertion(newNode);
		} 
	}
	
	private void fixAfterInsertion(Node node) {
		node.color = RED;
		while(node!=null && node!=root && node.parent.color==RED ) { //P是红色节点
			//P是G的左子节点
			if(parentOf(node)==leftOf(parentOf(parentOf(node)))) {
				Node rNode = rightOf(parentOf(parentOf(node)));//获取G的右子节点，即P的兄弟节点
				//P的兄弟节点是红色
				if(rNode.color==RED) {
					setColor(parentOf(node),BLACK);//P
				    setColor(rNode, BLACK);//P的兄弟节点
				    setColor(parentOf(parentOf(node)), RED);//G
				    node = parentOf(parentOf(node));//对G进行递归
				}
				else {//P的兄弟节点是黑色
					if(node == parentOf(node).right) { //N是P的右子节点
						node = parentOf(node);
						rotateLeft(node);  //左旋操作
					}
					setColor(parentOf(node), BLACK);
					setColor(parentOf(parentOf(node)), RED);
					rotateRight(parentOf(parentOf(node)));		
				}
			}
			//P是G的右子节点
			else {
				Node lNode = leftOf(parentOf(node));//获取G的左子节点，即P的兄弟节点
				if(colorOf(lNode)==RED) {
					setColor(parentOf(node), BLACK);//P
					setColor(lNode, BLACK);
					setColor(parentOf(parentOf(node)), RED);
					node = parentOf(parentOf(node));
				}
				else {
					if(node == parentOf(node).left) { //N是P的右子节点
						node = parentOf(node);
						rotateRight(node);  //左旋操作
					}
					setColor(parentOf(node), BLACK);
					setColor(parentOf(parentOf(node)), RED);
					rotateLeft(parentOf(parentOf(node)));	
				}
			}
		}
		root.color = BLACK;
	}
	
	public void remove(T ele) {
		Node target = getNode(ele);
		if(target.left!=null && target.right!=null) {
			Node sNode = target.left;
			while(sNode.right!=null) sNode = sNode.right;
			target.data = sNode.data;
			target = sNode;
		}
		
		Node replacement = target.left!=null? target.left : target.right;
		if(replacement!=null) {
			replacement.parent = target.parent;
			if(target.parent==null) root = replacement;
			else if(target.parent.left == target) target.parent.left = replacement;
			else  target.parent.right = replacement;
			target.parent = target.left = target.right =null;
			if(target.color==BLACK) {
				fixAfterDeletion(replacement);
			}
		}
		else if(target.parent==null) root=null;
		else {
			if(target.color == BLACK ) {fixAfterDeletion(target);}
			if(target.parent!=null) {
				if(target.parent.left == target) target.parent.left = null;
				else target.parent.right = null;
				target.parent = null;
			}
		}
	}
	
	private void fixAfterDeletion(Node node) {
		//直到node不是根节点且颜色为黑
		while(node!=root && colorOf(node)==BLACK ) { 
			//node是其父节点的左子节点
			if(node==leftOf(parentOf(node))) {
				Node sib = rightOf(parentOf(node));//获取兄弟节点
				//兄弟节点是红色
				if(sib.color==RED) {
				    setColor(sib, BLACK);//P的兄弟节点
				    setColor(parentOf(node),RED);
				    rotateLeft(parentOf(node));
				    sib = rightOf(parentOf(node));
				}
				if(colorOf(leftOf(sib))==BLACK && colorOf(rightOf(sib))== BLACK)
				{
					setColor(sib, RED);
					node = parentOf(node);
				}
				else {
					if(colorOf(rightOf(sib))== BLACK) { 
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib); 
						sib = rightOf(parentOf(node));
					}
					setColor(sib, colorOf(parentOf(node)));
					setColor(parentOf(node), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(node));
					node = root;
				}
			}
		}	
	}
	
	private Node parentOf(Node p) {
		return p==null? null : p.parent;
	}
	private Node leftOf(Node p) {
		return p==null? null : p.left;
	}
	private Node rightOf(Node p) {
		return p==null? null : p.right;
	}
	private boolean colorOf(Node p) {
		return p==null? BLACK : p.color;
	}
	private void setColor(Node p, boolean c) {
		if(p!=null) p.color = c;
	}
	//左旋操作
	private void rotateLeft(Node p) {
		if(p!=null) {
			Node rNode = p.right;
			Node q = rNode.left;
			p.right = q;
			if(q!=null) {
				q.parent = p;
			}
			rNode.parent = p.parent;
			if(p.parent==null) {
				root = rNode;
			}else if(p.parent.left ==p) {
				p.parent.left = rNode;
			}else {
				p.parent.right = rNode;
			}
			rNode.left = p;
			p.parent = rNode;
		}
	}
	//右旋操作
	private void rotateRight(Node p) {
		if(p!=null) {
			Node lNode = p.left;
			Node q = lNode.right;
			p.left = q;
			if(q!=null) {
				q.parent = p;
			}
			lNode.parent = p.parent;
			if(p.parent==null) {
				root = lNode;
			}else if(p.parent.left ==p) {
				p.parent.left = lNode;
			}else {
				p.parent.right = lNode;
			}
			lNode.right = p;
			p.parent = lNode;
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
	
}
