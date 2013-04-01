package node;

import node.OPNode.OPType;

public abstract class Node {
	
	public enum Type {
		num, var, fun, op;
	}

	protected Type type = null;
	
	public Node(Type type) {
		this.type = type;
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public static Node createNode(Type type, String s, double d, Node n1, Node n2, OPType opType) {
		Node node;
		switch (type) {
		case num:
			node = new NUMNode(d);
			break;
			
		case var:
			node = new VARNode();
			break;
			
		case fun:
			node = new  Function(s, n1);
			break;
			
		case op:
			node = OPNode.createOPNode(opType, n1, n2);
			break;
		
		default:
				throw new AssertionError("unknow type: " + type);	
		}		
		return node;		
	}
	
	// g ( f ( x ) )
	// being g node we want to take f as an argument.
	// go over g node and search for varnode
	// replace it with f
	public abstract Node compose(Node f);
	
	//public static Node replaceArgument();
	
//	public Node(ParserVal pv1, ParserVal pv2) {
//		if(pv1 == null || pv2 == null){
//			System.out.println("those pv are null!!");
//		}
//		this.s = (pv1.node == null ? "null" : pv1.node.getString())
//				+ (pv2.node == null ? "null" : pv2.node.getString());
//	}

	public abstract double getValue(double x);
}
