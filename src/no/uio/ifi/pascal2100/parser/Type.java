package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

abstract public class Type extends PascalSyntax {

	public Type(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public abstract String identify();

	public static Type parse(Scanner s) {
		enterParser("type");
		Type t = null;
		switch(s.curToken.kind) {
		case leftParToken:
			t = EnumType.parse(s);
			break;
		case arrayToken:
			t = ArrayType.parse(s);
			break;
		case nameToken:
			t = TypeName.parse(s);
			break;
		default:
			t = RangeType.parse(s);
			break;
		}
		leaveParser("type");
		return t;
	}

}
