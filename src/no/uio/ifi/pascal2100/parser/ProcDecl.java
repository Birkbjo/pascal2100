package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ProcDecl extends PascalDecl {
	ParamDeclList paramList;
	TypeName typeName;
	Block block;
	ProcDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<proc-decl> " + name + " on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("procedure ");
		Main.log.prettyPrint(name);
		if(paramList != null) {
			paramList.prettyPrint();
		}
		Main.log.prettyPrintLn(";");
		Main.log.prettyIndent();
		block.prettyPrint();
		Main.log.prettyOutdent();
		Main.log.prettyPrintLn(";");
		
	}

	
	public static ProcDecl parse(Scanner s) {
		
		ProcDecl pc = null;
		if(s.curToken.kind == TokenKind.functionToken) {
			pc = FuncDecl.parse(s);
		} else { //Its a procedure, parse this.
			enterParser("proc-decl");
			s.skip(TokenKind.procedureToken);
			s.test(TokenKind.nameToken);
			pc = new ProcDecl(s.curToken.id,s.curLineNum());
			s.readNextToken();
			if(s.curToken.kind == TokenKind.leftParToken) {
				pc.paramList = ParamDeclList.parse(s);
			}
			s.skip(TokenKind.semicolonToken);
			pc.block = Block.parse(s);
			s.skip(TokenKind.semicolonToken);
			leaveParser("proc-decl");
		}
		
		return pc;
	}

}
