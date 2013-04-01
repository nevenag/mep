package node;

public abstract class OPNode extends Node {
	
	public enum OPType {
		plus, minus, times, divide;
	}
	
	public OPNode(OPType type) {
		super(Type.op);
		this.opType = type;
	}

	protected OPType opType = null;
	
	public static OPNode createOPNode(OPType opType, Node n1, Node n2) {
		OPNode node = null;
		switch (opType) {
		case plus:
			node = new PLUSNode(n1, n2);
			break;
			
		case minus:
			node = new MINUSNode(n1, n2);
			break;
			
		case times:
			node = new TIMESNode(n1, n2);
			break;
			
		case divide:
			node = new DIVIDENode(n1, n2);
			break;			
		
		default:
				// exception	
		}		
		return node;		
	}
}
