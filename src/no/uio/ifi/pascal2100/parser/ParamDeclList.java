package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ParamDeclList extends PascalSyntax {
	ArrayList<ParamDecl> paramDeclList = new ArrayList<ParamDecl>();
	public ParamDeclList(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<param-decl-list> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub

	}

	public static ParamDeclList parse(Scanner s) {
		enterParser("param-decl-list");
		
		s.skip(TokenKind.leftParToken);
		ParamDeclList pdl = new ParamDeclList(s.curLineNum());
		pdl.paramDeclList.add(ParamDecl.parse(s));
		while(s.curToken.kind == TokenKind.semicolonToken) {
			s.skip(TokenKind.semicolonToken);
			pdl.paramDeclList.add(ParamDecl.parse(s));
		}
		
		s.skip(TokenKind.rightParToken);
		leaveParser("param-decl-list");
		return pdl;
	}

}
