package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ArrayType extends Type {
	Type t1;
	Type t2;
	public ArrayType(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static ArrayType parse(Scanner s) {
		enterParser("array-type");
		
		s.skip(TokenKind.arrayToken);
		s.skip(TokenKind.leftBracketToken);
		ArrayType at = new ArrayType(s.curLineNum());
		at.t1 = Type.parse(s);
		s.skip(TokenKind.rightBracketToken);
		s.skip(TokenKind.ofToken);
		at.t2 = Type.parse(s);
		
		leaveParser("array-type");
		return at;
	}

}
