package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ProcCallStatement extends Statement {
	String name;
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	
	public ProcCallStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<proc-call> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		if(exprList.size() > 0) {
			Main.log.prettyPrint("(");
			for(int i = 0;i<exprList.size();i++) {
				exprList.get(i).prettyPrint();
				if(i < exprList.size()-1)
					Main.log.prettyPrint(", ");
			}
			Main.log.prettyPrint(")");
		}
	}

	public static ProcCallStatement parse(Scanner s) {
		enterParser("proc-call");
		s.test(TokenKind.nameToken);
		ProcCallStatement ps = new ProcCallStatement(s.curLineNum());
		ps.name = s.curToken.id;
		s.readNextToken();
		if(s.curToken.kind == TokenKind.leftParToken) {
			s.skip(TokenKind.leftParToken);
			ps.exprList.add(Expression.parse(s));
			while(s.curToken.kind == TokenKind.commaToken) {
				s.skip(TokenKind.commaToken);
				ps.exprList.add(Expression.parse(s));
				
			}
			s.skip(TokenKind.rightParToken);
		}
		
		leaveParser("proc-call");
		return ps;
	}
}
