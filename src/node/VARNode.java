package node;
// TODO kako ovo definisem??
public class VARNode extends Node{
	
	String identifier;

	public VARNode() {
		super(Type.var);
		System.out.println(this.toString());
	}
	
	public VARNode(String i){
		super(Type.var);
		identifier = i;
		System.out.println(this.toString());
	}
	
	public VARNode(Node f) {
		super(Type.var);
	}	
	
	@Override
	public double getValue(double value) {
		return value;
	}
	@Override
	public String toString() {
		return "( x )";
	}

	@Override
	public Node compose(Node f) {
		return f;
	}
}
