package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

class ParamDecl extends PascalDecl {
	Token t;
	TypeName typeName;
	ParamDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<param-decl> " + name + " on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(t.id);
		Main.log.prettyPrint(": ");
		typeName.prettyPrint();
	}

	public static ParamDecl parse(Scanner s) {
		enterParser("param-decl");
		ParamDecl pd = new ParamDecl(s.curToken.id,s.curLineNum());
		pd.t = s.curToken;
		s.test(TokenKind.nameToken);
		s.readNextToken();
		s.skip(TokenKind.colonToken);
		pd.typeName = TypeName.parse(s);
		
		leaveParser("param-decl");
		return pd;
	}

}