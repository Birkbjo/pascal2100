package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Block extends PascalSyntax {

	ConstDeclPart constDeclPart;
	TypeDeclPart typeDeclPart;
	VarDeclPart varDeclPart;
	ArrayList<ProcDecl> procOrFunc = new ArrayList<ProcDecl>();
	StatementList statmList;
	PascalSyntax context = null;

	public Block(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<block> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub

	}

	static Block parse(Scanner s) {
		enterParser("block");

		Block b = new Block(s.curLineNum());
		if(s.curToken.kind == TokenKind.constToken) {
			b.constDeclPart = ConstDeclPart.parse(s);
		}
		if(s.curToken.kind == TokenKind.typeToken) {
			b.typeDeclPart = TypeDeclPart.parse(s);
		}
		if(s.curToken.kind == TokenKind.varToken) {
			b.varDeclPart = VarDeclPart.parse(s);
		}

		// Handle funcDecl and procDecl
		while (s.curToken.kind == TokenKind.functionToken
				|| s.curToken.kind == TokenKind.procedureToken) {
			b.procOrFunc.add(ProcDecl.parse(s));
		}
		
		s.skip(TokenKind.beginToken);
		b.statmList = StatementList.parse(s);
		s.skip(TokenKind.endToken);
		leaveParser("block");
		return b;
	}

}
