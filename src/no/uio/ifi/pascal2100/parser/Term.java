package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;

public class Term extends PascalSyntax {
	ArrayList<Factor> factorList = new ArrayList<Factor>();
	ArrayList<FactorOperator> facOprList = new ArrayList<FactorOperator>();
	public Term(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<term> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}

	public static Term parse(Scanner s) {
		enterParser("term");
		
		Term t = new Term(s.curLineNum());
		t.factorList.add(Factor.parse(s));
		while(s.curToken.kind.isFactorOpr()){
			t.facOprList.add(FactorOperator.parse(s));
			t.factorList.add(Factor.parse(s));
		}
		leaveParser("term");
		return t;
	}

}
