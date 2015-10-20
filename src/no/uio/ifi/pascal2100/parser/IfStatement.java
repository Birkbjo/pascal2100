package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class IfStatement extends Statement {
	Expression expr;
	Statement ifstatm;
	Statement elsestatm;
	
	IfStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<if-statement> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("if ");
		expr.prettyPrint();
		Main.log.prettyPrintLn(" then");
		ifstatm.prettyPrint();
		if(elsestatm != null) {
			Main.log.prettyPrintLn("else");
			elsestatm.prettyPrint();
		}
	}
	
	public static IfStatement parse(Scanner s) {
		enterParser("if-statement");
		
		IfStatement ifs = new IfStatement(s.curLineNum());
		s.skip(TokenKind.ifToken);
		ifs.expr = Expression.parse(s);
		s.skip(TokenKind.thenToken);
		ifs.ifstatm = Statement.parse(s);
		
		if(s.curToken.kind == TokenKind.elseToken) {
			s.skip(TokenKind.elseToken);
			ifs.elsestatm = Statement.parse(s);
		}
		
		leaveParser("if-statement");
		return ifs;
		
	}

}