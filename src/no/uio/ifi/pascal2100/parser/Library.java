package no.uio.ifi.pascal2100.parser;

public class Library extends Block {
	
	public Library() {
		TypeDecl integer = new TypeDecl("integer",-1);
		ProcDecl write = new ProcDecl("write",-1);
		ConstDecl eol = new ConstDecl("eol",-1);
		this.addDecl("integer", integer);
		this.addDecl("write", write);
		this.addDecl("eol", eol);
	}

}
