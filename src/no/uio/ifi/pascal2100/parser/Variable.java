package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Variable extends Factor {
	Expression expr;
	String name;
	PascalDecl ref;
	public Variable(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<variable> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		if(expr != null) {
			Main.log.prettyPrint("[");
			expr.prettyPrint();
			Main.log.prettyPrint("]");
		}
	}
	
	public static Variable parse(Scanner s) {
		enterParser("variable");
		
		Variable v = new Variable(s.curLineNum());
		s.test(TokenKind.nameToken);
		v.name = s.curToken.id;
		s.readNextToken();
		
		if(s.curToken.kind == TokenKind.leftBracketToken) {
			s.readNextToken();
			v.expr = Expression.parse(s);
			s.skip(TokenKind.rightBracketToken);
		}

		leaveParser("variable");
		return v;
	}

	public void check(Block curScope, Library lib) {
		PascalDecl d = curScope.findDecl(name, this);
		ref = d;
		if(expr != null) {
			expr.check(curScope,lib);
		}
	}

	@Override
	public void genCode(CodeFile f) {
		if(expr != null){
			expr.genCode(f);
		}
		ref.genCode(f);
	
	/*	int off1 = -4*ref.declLevel;
		int off2 = -ref.declOffset;
		f.genInstr("", "movl", off1+"(%ebp),%edx", "");
		f.genInstr("", "movl", off2+"(%edx),%eax", ""); */
	}
	
	
}
