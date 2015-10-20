package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class EnumType extends Type {
	ArrayList<EnumLiteral> eLiteral = new ArrayList<EnumLiteral>();
	public EnumType(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<enum-type> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("(");
		
		for(int i = 0;i<eLiteral.size();i++) {
			eLiteral.get(i).prettyPrint();
			if(i < eLiteral.size()-1) {
				Main.log.prettyPrint(", ");
			}
		}
		Main.log.prettyPrint(")");
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
