package node;

public class SINFunction extends Function {

	public SINFunction(Node n) {
		super(FUNType.sin);
		arg = n;
		System.out.println(this.toString());
	}

	public double getValue(double d) {
		return Math.sin(arg.getValue(d));
	}
	
	@Override
	public String toString() {
		return "sin ( " + arg.toString() + " )";
	}
}
