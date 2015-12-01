package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.Token;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class NumericLiteral extends Constant {
	Token t;
	public NumericLiteral(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<numeric-literal> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(Integer.toString(t.intVal));		
	}
	
	public static NumericLiteral parse(Scanner s) {
		enterParser("number literal");
		s.test(TokenKind.intValToken);
		NumericLiteral nl = new NumericLiteral(s.curLineNum());
		nl.t = s.curToken;
		s.readNextToken();
		
		leaveParser("number literal");
		return nl;
	}

	@Override
	void check(Block curScope, Library lib) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genCode(CodeFile f) {
	//	System.out.println(t.intVal);
		f.genInstr("", "movl", "$"+t.intVal+",%eax", "  " + t.intVal);
		
	}

}
