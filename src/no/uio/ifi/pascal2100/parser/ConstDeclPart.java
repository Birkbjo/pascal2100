package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ConstDeclPart extends PascalSyntax{
	ArrayList<ConstDecl> constDecl = new ArrayList<ConstDecl>();
	ConstDeclPart(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<const-decl-part> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("const ");
		
		for(ConstDecl cd : constDecl) {
			cd.prettyPrint();
			
		}
		Main.log.prettyPrintLn();
		
	}

	public static ConstDeclPart parse(Scanner s) {
		enterParser("const-decl-part");
		
		s.skip(TokenKind.constToken);
		ConstDeclPart c = new ConstDeclPart(s.curLineNum());
		while(s.curToken.kind == TokenKind.nameToken) {
			c.constDecl.add(ConstDecl.parse(s));	
		}
		
		leaveParser("const-decl-part");
		return c;
	}
	
	
}
