package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class ParamDecl extends PascalDecl {
	TypeName typeName;
	ParamDecl(String id, int lNum) {
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

	public static ParamDecl parse(Scanner s) {
		enterParser("param-decl");
		
		s.test(TokenKind.nameToken);
		ParamDecl pd = new ParamDecl(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.colonToken);
		pd.typeName = TypeName.parse(s);
		
		leaveParser("param-decl");
		return pd;
	}

}