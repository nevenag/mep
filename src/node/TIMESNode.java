package node;

public class TIMESNode extends OPNode{
	    
	private Node left;
	private Node right;
	
	public TIMESNode(Node l, Node r){
		super(OPType.times);
		this.left = l;
		this.right = r;
		System.out.println(this.toString());
	} 
	public TIMESNode(TIMESNode n) {
		super(OPType.times);
		this.left = n.left;
		this.right = n.right;
		System.out.println(this.toString());
	}

	public TIMESNode(Object obj, Object obj2) {
		super(OPType.times);
		this.left = (Node) obj;
		this.right = (Node) obj2;
		System.out.println(this.toString());
	}
	@Override
	public double getValue(double d) {
		return left.getValue(d) * right.getValue(d);
	};
	@Override
	public String toString() {
		return "( " + left.toString() + " ) * ( " + right.toString() + " )";
	}
	@Override
	public Node compose(Node f) {
		this.left = left.compose(f);
		this.right = right.compose(f);
		return this;
	}
}
