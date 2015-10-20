package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class StatementList extends PascalSyntax {
	ArrayList<Statement> statmList = new ArrayList<Statement>();
	StatementList(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<statm-list> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		for(int i = 0;i<statmList.size();i++) {
			statmList.get(i).prettyPrint();
			if(i != statmList.size()-1)
				Main.log.prettyPrintLn("; ");
			else Main.log.prettyPrintLn("");
		}
		
	}

	public static StatementList parse(Scanner s) {
		enterParser("statement-list");
		StatementList sl = new StatementList(s.curLineNum());
		sl.statmList.add(Statement.parse(s));
		while(s.curToken.kind == TokenKind.semicolonToken) {	
			s.skip(TokenKind.semicolonToken);
			sl.statmList.add(Statement.parse(s));
		}
		
		leaveParser("statement-list");
		
		return sl;
	}

}