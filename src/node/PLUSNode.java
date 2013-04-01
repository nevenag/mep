package node;


public class PLUSNode extends OPNode{
	    
	private Node left;
	private Node right;	
	
	public PLUSNode(Node l, Node r){
		super(OPType.plus);
		this.left = l;
		this.right = r;		
		System.out.println(this.toString());
	} 
	public PLUSNode(PLUSNode n) {
		super(OPType.plus);
		this.left = n.left;
		this.right = n.right;
		System.out.println(this.toString());
	}
	
//	public PLUSNode(Object node, Object node2) {
//		super(OPType.plus);
//		if(node.getClass().isInstance(Node.class) &&
//				node.getClass().isInstance(Node.class)){
//			left = (Node) node;
//			right = (Node) node2;
//		}
//		else{
//			System.out.println("Object from the ParserVal has to be Node.");
//		}
//	}
		
	@Override
	public double getValue(double d) {
		return left.getValue(d) + right.getValue(d);
	}
	@Override
	public String toString() {
		return "( " + left.toString() + " ) + ( " + right.toString() + " )";
	}
	@Override
	public Node compose(Node f) {
		this.left = left.compose(f);
		this.right = right.compose(f);
		return this;
	}
}
