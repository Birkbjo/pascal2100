package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class TypeName extends Type {
	Name name;
	public TypeName(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<type-name on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	//TODO: may not need the Name class.
	public static TypeName parse(Scanner s) {
		enterParser("type-name");
		s.test(TokenKind.nameToken);
		TypeName tn = new TypeName(s.curLineNum());
		//tn.name = Name.parse(s);
		s.readNextToken();
		leaveParser("type-name");
		return tn;
	}

}
