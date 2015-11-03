package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;
import java.util.HashMap;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Block extends PascalSyntax {
	HashMap<String,PascalDecl> decls = new HashMap<String,PascalDecl>();
	Block outerScope = null;
	ConstDeclPart constDeclPart;
	TypeDeclPart typeDeclPart;
	VarDeclPart varDeclPart;
	ArrayList<ProcDecl> procOrFunc = new ArrayList<ProcDecl>();
	StatementList statmList;
	PascalSyntax context = null;

	public Block(int n) {
		super(n);
	}
	public Block(){}
	@Override
	public String identify() {
		return "<block> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		if(constDeclPart != null) {
			constDeclPart.prettyPrint();
		}
		if(typeDeclPart != null) {
			typeDeclPart.prettyPrint();
		}
		if(varDeclPart != null) {
			varDeclPart.prettyPrint();
		}
		for(ProcDecl p: procOrFunc) p.prettyPrint();
		Main.log.prettyPrintLn("begin"); Main.log.prettyIndent();
		statmList.prettyPrint();
		Main.log.prettyOutdent();
		Main.log.prettyPrint("end");

	}

	static Block parse(Scanner s) {
		enterParser("block");

		Block b = new Block(s.curLineNum());
		if (s.curToken.kind == TokenKind.constToken) {
			b.constDeclPart = ConstDeclPart.parse(s);
		}
		if (s.curToken.kind == TokenKind.typeToken) {
			b.typeDeclPart = TypeDeclPart.parse(s);
		}
		if (s.curToken.kind == TokenKind.varToken) {
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

	void check(Block curScope, Library lib) {
		outerScope = lib;
		if(constDeclPart != null) {
			
		}
	}
	
	private void addDecl(String id, PascalDecl d) {
		if(decls.containsKey(id)) {
			d.error(id + " declared twice in same block!");
		}
		decls.put(id, d);
	}
	
	PascalDecl findDecl(String id, PascalSyntax where) {
		PascalDecl d = decls.get(id);
		if(d != null) {
			Main.log.noteBinding(id, where, d);
			return d;
		}
		if(outerScope != null) {
			return outerScope.findDecl(id,where);
		}
		where.error("Name " + id + " is unknown!");
		return null;
	}

}
