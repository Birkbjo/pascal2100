package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class ConstDecl extends PascalDecl {
	Constant con;
	ConstDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<const-decl> " + name + " on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("constdecl");
		con.prettyPrint();
		
	}

	public static ConstDecl parse(Scanner s) {
		enterParser("const-decl");
		
		s.test(TokenKind.nameToken);
		ConstDecl cd = new ConstDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.equalToken);
		cd.con = Constant.parse(s);
		s.skip(TokenKind.semicolonToken);
		leaveParser("const-decl");
		return cd;
	}

}