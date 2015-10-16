package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class VarDeclPart extends PascalSyntax {
	ArrayList<VarDecl> varDeclList = new ArrayList<VarDecl>();
	public VarDeclPart(int n) {
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

	public static VarDeclPart parse(Scanner s) {
		enterParser("var-decl-part");
		s.skip(TokenKind.varToken);
		VarDeclPart vdp = new VarDeclPart(s.curLineNum());
		while(s.curToken.kind == TokenKind.nameToken) {
			vdp.varDeclList.add(VarDecl.parse(s));
		}
		leaveParser("var-decl-part");
		return vdp;
	}

}
