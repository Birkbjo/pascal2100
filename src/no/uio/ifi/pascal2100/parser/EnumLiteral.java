package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class EnumLiteral extends PascalDecl {

	Token t;
	
	
	EnumLiteral(String id, int n) {
		super(id, n);
	}

	@Override
	public String identify() {
		return "<enum-literal> " + (isInLibrary() ? " in the library" : " on line " + lineNum);
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(t.id);
	}
	
	void check(Block curScope, Library lib) {
	}

	public static EnumLiteral parse(Scanner s) {
		enterParser("enum-literal");
		
		s.test(TokenKind.nameToken);
		EnumLiteral el = new EnumLiteral(s.curToken.id, s.curLineNum());
		s.readNextToken();

		leaveParser("enum-literal");
		return el;
	}
	
	@Override
	public void genCode(CodeFile f) {
		if(name.equals("enum value false (=0)")){
			f.genInstr("", "movl", "$" + 0 + ",%eax", "  " + name);	
		} else {
			f.genInstr("", "movl", "$" + 1 + ",%eax", "  " + name);
		}
		
		//f.genInstr("", "pushl","%eax", "PascalDecl");
	}

}
