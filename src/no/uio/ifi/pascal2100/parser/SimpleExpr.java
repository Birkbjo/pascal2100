package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class SimpleExpr extends PascalSyntax {
	PrefixOperator preOpr;
	ArrayList<Term> termList = new ArrayList<Term>();
	ArrayList<TermOperator> termOprList = new ArrayList<TermOperator>();
	public SimpleExpr(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<simple-expr> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static SimpleExpr parse(Scanner s) {
		enterParser("simple-expr");
		
		SimpleExpr se = new SimpleExpr(s.curLineNum());
		
		if(s.curToken.kind.isPrefixOpr()) {
			se.preOpr = PrefixOperator.parse(s);
		}

		se.termList.add(Term.parse(s));

		while(s.curToken.kind.isTermOpr()) {
			se.termOprList.add(TermOperator.parse(s));
			se.termList.add(Term.parse(s));
		}
		
		leaveParser("simple-expr");
		return se;
	}

}
