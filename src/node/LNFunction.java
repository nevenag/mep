package node;

public class LNFunction extends Function{
	   
	private Node arg;
	
	public LNFunction(Node n) {
		super(FUNType.ln);
		this.arg = n;
		System.out.println(this.toString());
	}
	
	
	@Override
	public double getValue(double x) {

	 double a=arg.getValue(x);
		  if(a>0){
			  return Math.log(a);
		  }
		  else{
			  return 0;
		  }
		  // TODO throw MathException(LOG_0);
	}
	@Override
	public String toString() {
		return "ln ( " + arg.toString() + " )";
	}
	
}
