package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class FuncDecl extends ProcDecl {

	FuncDecl(String id, int lNum) {
		super(id, lNum);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * TODO funcdecl implementation.
	 * @param s
	 * @return
	 */
	public static FuncDecl parse(Scanner s) {
		
		enterParser("func-decl");
		
		s.test(TokenKind.nameToken);
		FuncDecl fd = new FuncDecl(s.curToken.id,s.curLineNum());
		
		leaveParser("func-decl");
		return null;
	}
}