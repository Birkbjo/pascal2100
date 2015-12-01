package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ArrayType extends Type {
	Type t1;
	Type t2;
	
	
	public ArrayType(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<array-type> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("array");
		Main.log.prettyPrint(" [");
		t1.prettyPrint();
		Main.log.prettyPrint("]");
		Main.log.prettyPrint(" of ");
		t2.prettyPrint();
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

	@Override
	void check(Block curScope, Library lib) {
		t1.check(curScope, lib);
		t2.check(curScope, lib);
	}

	@Override
	public void genCode(CodeFile f) {
		//not implemented
		
	}

}
