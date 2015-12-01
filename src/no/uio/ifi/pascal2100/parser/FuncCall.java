package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;
import java.util.ListIterator;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class FuncCall extends Factor {
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	String name;
	ProcDecl ref;
	
	public FuncCall(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<func-call>" + (isInLibrary() ? " in the library" : " on line " + lineNum);
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
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
	
	void check(Block curScope, Library lib) {
		PascalDecl d = curScope.findDecl(name, this);
		ref = (ProcDecl)d;
		for(Expression e: exprList) {
			e.check(curScope, lib);
		}
	}

	public static FuncCall parse(Scanner s) {
		enterParser("func call");
		
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
		
		leaveParser("func call");
		return fc;
	}

	@Override
	public void genCode(CodeFile f) {
		int count = 0;
		for (ListIterator<Expression> iterator = exprList.listIterator(exprList.size());
				iterator.hasPrevious();) {
			Expression e = iterator.previous();
			count++;
			e.genCode(f);
			f.genInstr("","pushl","%eax","Push param #" + count + ".");
		}
		f.genInstr("", "call", ref.label, "");
		f.genInstr("", "addl", "$"+exprList.size()*4+",%esp", "Pop parameters");
	}
}
