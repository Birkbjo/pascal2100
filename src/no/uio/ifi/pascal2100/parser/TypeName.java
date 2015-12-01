package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class TypeName extends Type {
	String name;
	TypeDecl ref;
	
	public TypeName(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<type-name on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name);
	}
	
	void check(Block curScope, Library lib) {
		PascalDecl d =curScope.findDecl(name, this);
		ref = (TypeDecl)d;
	}

	public static TypeName parse(Scanner s) {
		enterParser("type name");
		s.test(TokenKind.nameToken);
		TypeName tn = new TypeName(s.curLineNum());
		tn.name = s.curToken.id;
		s.readNextToken();
		leaveParser("type name");
		return tn;
	}

	@Override
	public void genCode(CodeFile f) {
		
	}

}
