package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ParamDeclList extends PascalSyntax {
	ArrayList<ParamDecl> paramDeclList = new ArrayList<ParamDecl>();
	public ParamDeclList(int n) {
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

	public static ParamDeclList parse(Scanner s) {
		enterParser("param-decl-list");
		
		s.skip(TokenKind.leftParToken);
		ParamDeclList pdl = new ParamDeclList(s.curLineNum());
		while(s.curToken.kind == TokenKind.nameToken) {
			pdl.paramDeclList.add(ParamDecl.parse(s));
			s.skip(TokenKind.semicolonToken);
		}
		s.skip(TokenKind.rightParToken);
		leaveParser("param-decl-list");
		return pdl;
	}

}
