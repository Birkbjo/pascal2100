package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;
import no.uio.ifi.pascal2100.main.CodeFile;

class ConstDecl extends PascalDecl {
	Constant con;
	
	ConstDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<const-decl>" + (isInLibrary() ? " in the library" : " on line " + lineNum);
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
		Main.log.prettyPrint(" = ");
		con.prettyPrint();
		Main.log.prettyPrint("; ");
	}

	public static ConstDecl parse(Scanner s) {
		enterParser("const decl");
		
		s.test(TokenKind.nameToken);
		ConstDecl cd = new ConstDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.equalToken);
		cd.con = Constant.parse(s);
		s.skip(TokenKind.semicolonToken);
		leaveParser("const decl");
		return cd;
	}

	public void check(Block curScope, Library lib) {
		con.check(curScope,lib);
		
	}
	
	public void genCode(CodeFile f) {
		if(con != null){
			con.genCode(f);
		}
	}

}