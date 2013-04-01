package node;

public class MINUSNode extends OPNode{
	    
	private Node left;
	private Node right;
	
	public MINUSNode(Node l, Node r){
		super(OPType.minus);
		this.left = l;
		this.right = r;
		System.out.println(this.toString());
	} 
	public MINUSNode(MINUSNode n) {
		super(OPType.minus);
		this.left = n.left;
		this.right = n.right;
		System.out.println(this.toString());
	}
	
	public MINUSNode(Object obj, Object obj2) {
		super(OPType.minus);
		this.left = (Node) obj;
		this.right = (Node) obj2;
		System.out.println(this.toString());
	}
	@Override
	public double getValue(double x) {
		return left.getValue(x) - right.getValue(x);
	}
	@Override
	public String toString() {
		return "( " + left.toString() + " ) - ( " + right.toString() + " ) created.";
	}
	@Override
	public Node compose(Node f) {
		this.left = left.compose(f);
		this.right = right.compose(f);
		return this;
	}
}