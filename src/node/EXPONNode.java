package node;

public class EXPONNode extends Node{
	private Node left;
	private Node right;
	 
	public EXPONNode(EXPONNode n) {
		super(Type.op);
		this.left = n.left;
		this.right = n.right;
	}
	
	public EXPONNode(Node left, Node right) {
		super(Type.op);
		this.left = left;
		this.right = right;
	}
	
	
	public double getValue(double x)
	{  double a=left.getValue(x);
	   double b=right.getValue(x);
	   double c;
	   if (a>0) return Math.exp(b*Math.log(a));
	   if ((int)b==b)
	   {  c=1;
	      if (b>=0)
	      {   for (int i=1; i<=b; i++)
	             c*=a;
	          return c;
	      }
	      if (b<0)
	      {   for (int i=1; i<=-b; i++)
	             c*=a;
	          if (c!=0) return 1/c;
	          //else throw MathException(ILLEGAL_EXP);
	      }
	   }
	  // else throw MathException(ILLEGAL_EXP);
	   return -1;
	}
	@Override
	public Node compose(Node f) {
		left.compose(f);
		right.compose(f);
		return this;
	}
}
