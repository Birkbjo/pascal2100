package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class VarDeclPart extends PascalSyntax {
	ArrayList<VarDecl> varDeclList = new ArrayList<VarDecl>();
	public VarDeclPart(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<var-decl-part> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrintLn("var ");
		for(VarDecl vd : varDeclList) {
			vd.prettyPrint();
		}
		Main.log.prettyPrintLn();
	}

	public static VarDeclPart parse(Scanner s) {
		enterParser("var decl part");
		s.skip(TokenKind.varToken);
		VarDeclPart vdp = new VarDeclPart(s.curLineNum());
		while(s.curToken.kind == TokenKind.nameToken) {
			vdp.varDeclList.add(VarDecl.parse(s));
		}
		leaveParser("var decl part");
		return vdp;
	}

	public void check(Block curScope, Library lib) {
		for(VarDecl vd: varDeclList) {
			vd.check(curScope,lib);
		}
		
	}

	@Override
	public void genCode(CodeFile f) {
		for(VarDecl vd1 : varDeclList){
			vd1.genCode(f);
		}
		
	}

}
