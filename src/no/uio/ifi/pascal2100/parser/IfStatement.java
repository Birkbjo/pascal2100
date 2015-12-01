package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

class IfStatement extends Statement {
	Expression expr;
	Statement ifstatm;
	Statement elsestatm;
	
	IfStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<if-statement> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint("if ");
		expr.prettyPrint();
		Main.log.prettyPrintLn(" then "); Main.log.prettyIndent();
		ifstatm.prettyPrint();
		Main.log.prettyPrintLn();
		Main.log.prettyOutdent();
		if(elsestatm != null) {
			Main.log.prettyPrintLn("else "); 
			Main.log.prettyIndent();
			elsestatm.prettyPrint();
			Main.log.prettyOutdent();
		}
	}
	
	public static IfStatement parse(Scanner s) {
		enterParser("if-statm");
		
		IfStatement ifs = new IfStatement(s.curLineNum());
		s.skip(TokenKind.ifToken);
		ifs.expr = Expression.parse(s);
		s.skip(TokenKind.thenToken);
		ifs.ifstatm = Statement.parse(s);
		
		if(s.curToken.kind == TokenKind.elseToken) {
			s.skip(TokenKind.elseToken);
			ifs.elsestatm = Statement.parse(s);
		}
		
		leaveParser("if-statm");
		return ifs;
		
	}

	@Override
	void check(Block curScope, Library lib) {
		expr.check(curScope, lib);
		ifstatm.check(curScope, lib);
		if(elsestatm != null) {
			elsestatm.check(curScope, lib);
		}
		
	}
	
	@Override
	public void genCode(CodeFile f) {
		String endLabel = "";
		f.genInstr("", "", "", "Start if-statement");
		if(elsestatm != null) {
			String elselabel = f.getLocalLabel();
			endLabel = f.getLocalLabel();
			expr.genCode(f);
			f.genInstr("", "cmpl", "$0,%eax", "");
			f.genInstr("", "je", elselabel, "");
			ifstatm.genCode(f);
			f.genInstr("", "jmp", endLabel, "");
			f.genInstr(elselabel, "", "", "");
			elsestatm.genCode(f);
		} else {
			endLabel = f.getLocalLabel();
			expr.genCode(f);
			f.genInstr("", "cmpl", "$0,%eax", "");
			f.genInstr("", "je", endLabel, "");
			ifstatm.genCode(f);
		}
		f.genInstr(endLabel, "", "", "End if-statement");
		
	}

}