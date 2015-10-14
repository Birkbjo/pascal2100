package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class EnumType extends Type {
	ArrayList<EnumLiteral> eLiteral = new ArrayList<EnumLiteral>();
	public EnumType(int n) {
		super(n);
		// TODO Auto-generated constructor stub
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
	
	public static EnumType parse(Scanner s) {
		enterParser("enum-type");
		s.skip(TokenKind.leftParToken);
		EnumType et = new EnumType(s.curLineNum());
		while(s.curToken.kind == TokenKind.nameToken) {
			et.eLiteral.add(EnumLiteral.parse(s));
			s.skip(TokenKind.commaToken);
		}
		s.skip(TokenKind.rightParToken);
		leaveParser("enum-type");
		return et;
	}
}
