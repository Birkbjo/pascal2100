package no.uio.ifi.pascal2100.parser;

public abstract class Operator extends PascalSyntax {

	public Operator(int n) {
		super(n);
	}

	abstract public String identify();
	
}
