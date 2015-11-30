package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class StringLiteral extends Constant {
	String slit;
	public StringLiteral(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<char-literal> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("'" + slit + "'");
		
	}
	
	public static StringLiteral parse(Scanner s) {
		enterParser("char literal");
		s.test(TokenKind.stringValToken);
		StringLiteral sl = new StringLiteral(s.curLineNum());
		sl.slit = s.curToken.strVal;
		s.readNextToken();
		leaveParser("char literal");
		return sl;
	}

	@Override
	void check(Block curScope, Library lib) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genCode(CodeFile f) {
		System.out.println("StringLiteral");
		f.genInstr("", "movl", "$120,%eax", "char 120");
		f.genString("", slit, "char");
		
	}

}
