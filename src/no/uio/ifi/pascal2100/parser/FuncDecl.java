package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class FuncDecl extends ProcDecl {

	FuncDecl(String id, int lNum) {
		super(id, lNum);
	}
	
	@Override
	public String identify() {
		return "<func-decl> " + name + " on line " + lineNum;
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static FuncDecl parse(Scanner s) {
		
		enterParser("func-decl");
		s.skip(TokenKind.functionToken);
		s.test(TokenKind.nameToken);
		FuncDecl fd = new FuncDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		if(s.curToken.kind == TokenKind.leftParToken) {
			fd.paramList = ParamDeclList.parse(s);
		}
		s.skip(TokenKind.colonToken);
		fd.typeName = TypeName.parse(s);
		s.skip(TokenKind.semicolonToken);
		fd.block = Block.parse(s);
		s.skip(TokenKind.semicolonToken);
		
		leaveParser("func-decl");
		return fd;
	}
}