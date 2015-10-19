package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class FuncDecl extends ProcDecl {

	FuncDecl(String id, int lNum) {
		super(id, lNum);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
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