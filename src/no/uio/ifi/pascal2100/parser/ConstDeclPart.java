package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class ConstDeclPart extends PascalSyntax{
	ArrayList<ConstDecl> constDecl = new ArrayList<ConstDecl>();
	ConstDeclPart(int n) {
		super(n);
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
