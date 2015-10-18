package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ProcCallStatement extends Statement {
	Name name;
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	
	public ProcCallStatement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<proc-call> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub

	}

	public static ProcCallStatement parse(Scanner s) {
		enterParser("proc-call");
		s.skip(TokenKind.nameToken);
		ProcCallStatement ps = new ProcCallStatement(s.curLineNum());
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
