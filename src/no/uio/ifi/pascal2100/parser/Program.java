package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Program extends PascalDecl {
	Block block = null;
	public Program(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<program> " + name + " on line " + lineNum;
	}

	@Override
	public void prettyPrint() {
		Main.log.prettyPrintLn("program " + name + ";");
		block.prettyPrint();
		Main.log.prettyPrint(".");
	}
	public void check(Block curScope, Library lib) {
		block.check(curScope,lib);
	}
	public static Program parse(Scanner s) {
		enterParser("program");
		s.skip(TokenKind.programToken);
		//Name only contains letters (handled by scanner), so we can skip this.
		s.test(TokenKind.nameToken);
		Program p = new Program(s.curToken.id,s.curLineNum());
		s.readNextToken();
		s.skip(TokenKind.semicolonToken);
		p.block = Block.parse(s);
		p.block.context = p;
		s.skip(TokenKind.dotToken);
		
		leaveParser("program");
		return p;
	}

	@Override
	public void genCode(CodeFile f) {
		String progLab = f.getLabel(name);
		f.genInstr("_main", "", "", "");
		f.genInstr("main","call","prog$"+progLab,"");
		f.genInstr("", "movl", "$0,%eax", "set status 0 and ");
		f.genInstr("", "ret", "", "terminate program");
		label = "prog$" + progLab;
		block.genCode(f);
		
	
	}

}
