package no.uio.ifi.pascal2100.parser;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
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
}
