package node;

public class NUMNode extends Node{

	private double value;
	
	public NUMNode(NUMNode n) {
		super(Type.num);
		this.value = n.value;
		System.out.println(this.toString());
		
	}
	
	public NUMNode(double value) {
		super(Type.num);
		this.value = value;
		System.out.println(this.toString());
	}	
	
	public double getValue(double d){
		return value;
	}
	@Override
	public String toString() {
		Double d = new Double(value);
		return d.toString();
	}

	@Override
	public Node compose(Node f) {
		return this;
	}
}
