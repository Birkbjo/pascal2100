package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class FuncCall extends Factor {
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	public FuncCall(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<func-call> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static FuncCall parse(Scanner s) {
		enterParser("func-call");
	
		s.skip(TokenKind.nameToken);
		FuncCall fc = new FuncCall(s.curLineNum());

		if(s.curToken.kind == TokenKind.leftParToken) {
			s.skip(TokenKind.leftParToken);
			fc.exprList.add(Expression.parse(s));
			while(s.curToken.kind == TokenKind.commaToken) {
				s.skip(TokenKind.commaToken);
				fc.exprList.add(Expression.parse(s));
			}
			s.skip(TokenKind.rightParToken);
		}
		
		leaveParser("func-call");
		return fc;
	}
}
