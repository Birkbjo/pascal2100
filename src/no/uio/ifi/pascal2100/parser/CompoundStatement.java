package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class CompoundStatement extends Statement {
	StatementList statmList;
	CompoundStatement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<compound-statement on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static CompoundStatement parse(Scanner s) {
		enterParser("compound-statement");
		s.skip(TokenKind.beginToken);
		CompoundStatement cs = new CompoundStatement(s.curLineNum());
		cs.statmList = StatementList.parse(s);
		s.skip(TokenKind.endToken);
	
		leaveParser("compound-statement");
		return cs;
	}

}