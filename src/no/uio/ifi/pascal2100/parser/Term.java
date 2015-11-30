package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.scanner.Scanner;

public class Term extends PascalSyntax {
	ArrayList<Factor> factorList = new ArrayList<Factor>();
	ArrayList<FactorOperator> facOprList = new ArrayList<FactorOperator>();
	public Term(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<term> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		
		factorList.get(0).prettyPrint();
		for(int i = 0;i<facOprList.size();i++) {
			facOprList.get(i).prettyPrint();
			factorList.get(i+1).prettyPrint();;
		}
		
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

	public void check(Block curScope, Library lib) {
		for(Factor f: factorList) {
			f.check(curScope,lib);
		}
		
	}

	@Override
	public void genCode(CodeFile f) {
		System.out.println("Term");
		for(Factor fl: factorList){
			System.out.println("Factor test");
			fl.identify();
			fl.genCode(f);

			System.out.println("Factor end");
		}
		
	}

}
