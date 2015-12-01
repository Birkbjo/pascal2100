package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class SimpleExpr extends PascalSyntax {
	PrefixOperator preOpr;
	ArrayList<Term> termList = new ArrayList<Term>();
	ArrayList<TermOperator> termOprList = new ArrayList<TermOperator>();
	public SimpleExpr(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<simple-expr> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		if(preOpr != null) {
			preOpr.prettyPrint();
		}
		termList.get(0).prettyPrint();
		for(int i = 0;i<termOprList.size();i++) {
			termOprList.get(i).prettyPrint();
			termList.get(i+1).prettyPrint();;
		}
		
	}

	public static SimpleExpr parse(Scanner s) {
		enterParser("simple expr");
		
		SimpleExpr se = new SimpleExpr(s.curLineNum());
		
		if(s.curToken.kind.isPrefixOpr()) {
			se.preOpr = PrefixOperator.parse(s);
		}

		se.termList.add(Term.parse(s));

		while(s.curToken.kind.isTermOpr()) {
			se.termOprList.add(TermOperator.parse(s));
			se.termList.add(Term.parse(s));
		}
		
		leaveParser("simple expr");
		return se;
	}

	public void check(Block curScope, Library lib) {
		for(Term t: termList) {
			t.check(curScope,lib);
		}
		
	}

	@Override
	public void genCode(CodeFile f) {
		Term t = termList.get(0);
		t.genCode(f);
		if(preOpr != null) {
			preOpr.genCode(f);
		}
		if(termOprList.size() > 0) {
			for(int i = 0;i<termOprList.size();i++) {
				f.genInstr("", "pushl", "%eax", "");
				termList.get(i+1).genCode(f);
				termOprList.get(i).genCode(f);
			}
		}
		
	} 

}
