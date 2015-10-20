package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class CompoundStatement extends Statement {
	StatementList statmList;
	CompoundStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<compound-statement on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("begin"); Main.log.prettyIndent();
		statmList.prettyPrint(); Main.log.prettyOutdent();
		Main.log.prettyPrint("end");
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