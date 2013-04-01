package node;

public class COSFunction extends Function {
	public COSFunction(Node n) {
		super(FUNType.cos);
		arg = n;
		System.out.println(this.toString());
	}
	
	@Override
	public double getValue(double d) {
		return Math.cos(arg.getValue(d));
	}
	
	@Override
	public String toString() {
		return "cos ( " + arg.toString() + " )";
	}
}
