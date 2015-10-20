package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class WhileStatement extends Statement {
	Expression expr;
	Statement statm;
	
	public WhileStatement(int lineNr) {
		super(lineNr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<while-statement> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("while ");
		expr.prettyPrint();
		Main.log.prettyPrintLn(" do ");
		Main.log.prettyIndent();
		statm.prettyPrint();
		Main.log.prettyOutdent();
	}
	
	public static WhileStatement parse(Scanner s) {
		enterParser("while-statm");
		
		WhileStatement ws = new WhileStatement(s.curLineNum());
		s.skip(TokenKind.whileToken);
		ws.expr = Expression.parse(s);
		s.skip(TokenKind.doToken);
		ws.statm = Statement.parse(s);
		
		leaveParser("while-statm");
		return ws;
	}

}
