package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Negation extends Factor {
	Factor factor;
	public Negation(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<negation> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("not ");
		factor.prettyPrint();
		
	}
	
	public static Negation parse(Scanner s) {
		enterParser("negation");
		
		Negation n = new Negation(s.curLineNum());
		s.skip(TokenKind.notToken);
		n.factor = Factor.parse(s);
		
		
		leaveParser("negation");
		return n;
		
	}

	@Override
	void check(Block curScope, Library lib) {
		factor.check(curScope,lib);
		
	}

	@Override
	public void genCode(CodeFile f) {
		factor.genCode(f);
		f.genInstr("", "xorl", "$0x1,%eax","  not");
	}
	
	

}
