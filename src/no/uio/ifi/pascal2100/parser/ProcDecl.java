package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ProcDecl extends PascalDecl {

	ProcDecl(String id, int lNum) {
		super(id, lNum);
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

	
	public static ProcDecl parse(Scanner s) {
		enterParser("proc-decl");
		
		ProcDecl pc = null;
		if(s.curToken.kind == TokenKind.functionToken) {
			pc = FuncDecl.parse(s);
		} else {
			s.skip(TokenKind.procedureToken);
			s.test(TokenKind.nameToken);
			pc = new ProcDecl(s.curToken.id,s.curLineNum());
			//TODO: procdecl implementation.
		}
		
		leaveParser("proc-decl");
		return pc;
	}

}
