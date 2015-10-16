package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class ConstDecl extends PascalDecl {
	Constant con;
	ConstDecl(String id, int lNum) {
		super(id, lNum);
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

	public static ConstDecl parse(Scanner s) {
		enterParser("const-decl");
		
		s.test(TokenKind.nameToken);
		ConstDecl cd = new ConstDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.equalToken);
		cd.con = Constant.parse(s);
		
		leaveParser("const-decl");
		return cd;
	}

}