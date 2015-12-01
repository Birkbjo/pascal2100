package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class RangeType extends Type {
	Constant c1;
	Constant c2;
	
	public RangeType(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<range-type> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		c1.prettyPrint();
		Main.log.prettyPrint("..");
		c2.prettyPrint();
	}

	public static RangeType parse(Scanner s) {
		enterParser("range-type");
		RangeType rt = new RangeType(s.curLineNum());
		rt.c1 = Constant.parse(s);
		s.skip(TokenKind.rangeToken);
		rt.c2 = Constant.parse(s);
		leaveParser("range-type");
		return rt;
	}

	@Override
	void check(Block curScope, Library lib) {
		c1.check(curScope,lib);
		c2.check(curScope,lib);
	}

	@Override
	public void genCode(CodeFile f) {
		//shouldnt reach this
		
	}
}
