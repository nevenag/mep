package node;

public class DIVIDENode extends OPNode{
	    
	private Node left;
	private Node right;
	
	public DIVIDENode(Node l, Node r){
		super(OPType.divide);
		this.left = l;
		this.right = r;
		System.out.println(this.toString());
	} 
	public DIVIDENode(DIVIDENode n) {
		super(OPType.divide);
		this.left = n.left;
		this.right = n.right;
		System.out.println(this.toString());
	}
		
	public DIVIDENode(Object l, Object r){
		super(OPType.divide);
		this.left = (Node) l;
		this.right = (Node) r;
		System.out.println(this.toString());
	} 
	
	@Override
	public double getValue(double d) {
		double r=right.getValue(d);
		   if (r==0){
			   return 0;
			   // TODO: throw MathException(DIV_0);
		   }
		   else{
			   return (left.getValue(d) / right.getValue(d));
		   }
	}
	
	@Override
	public String toString() {
		return "( " + left.toString() + " ) / ( " + right.toString() + " ) created.";
	}
	@Override
	public Node compose(Node f) {
		this.left = left.compose(f);
		this.right = right.compose(f);
		return this;
	}
}
