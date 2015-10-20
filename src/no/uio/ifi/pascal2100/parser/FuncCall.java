package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class FuncCall extends Factor {
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	String name;
	public FuncCall(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<func-call> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("name");
		if(exprList.size() > 0) {
			Main.log.prettyPrint("(");
			for(int i = 0;i<exprList.size();i++) {
				exprList.get(i).prettyPrint();
				if(i < exprList.size()-1)
					Main.log.prettyPrint(",");
			}
			Main.log.prettyPrint(")");
		}
	}

	public static FuncCall parse(Scanner s) {
		enterParser("func-call");
		
		s.test(TokenKind.nameToken);
		FuncCall fc = new FuncCall(s.curLineNum());
		fc.name = s.curToken.id;
		s.readNextToken();
		
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
