package node;

// TODO shouldn't this be abstract?

public class Function extends Node{

	protected FUNType funType = null;

	// argument of the function
	protected Node arg;
	// the function would be:
	// name(argument)
	// Node ...

	
	public Function(Type type) {
		super(Type.fun);
	}

	public Function(FUNType type) {
		super(Type.fun);
		this.funType = type;
	}
	
	public Function(String s, Node n) {
		super(Type.fun);
		if(s.equalsIgnoreCase("sin")){
			funType = FUNType.sin;
		}else if(s.equalsIgnoreCase("cos")){
			funType = FUNType.cos;
		}
		else funType = FUNType.ln;
		arg = n;
		System.out.println(this.toString());
	}


	public Function(String string) {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Node createFunction(String s, Node n1) {
		Node n = null;
		if(s.equalsIgnoreCase("sin")){
			return new SINFunction(n1);
		}else if(s.equalsIgnoreCase("cos")){
			return new COSFunction(n1);
		}
		return n;
	}

	public enum FUNType {
		sin, cos, ln;
	}
	
			
	public static Function createFunction(FUNType type, Node n) {
		Function f = null;
		switch (type) {
		case sin:
			f = new SINFunction(n);
			break;
			
		case cos:
			f = new COSFunction(n);
			break;
		case ln:
			f = new LNFunction(n);
			break;			
		default:
			// exception
			break;
		}
			
		return f;
	}

	@Override
	public String toString() {
		return  this.funType + " ( " + arg.toString() + " ) ";
	}

	@Override
	public double getValue(double x) {
		//TODO throw an exception
		return 0;
	}

	@Override
	public Node compose(Node f) {
		this.arg = arg.compose(f);
		return this;
	}
}