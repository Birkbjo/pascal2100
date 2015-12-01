package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

class ParamDecl extends PascalDecl {
	Token t;
	TypeName typeName;
	ParamDecl(String id, int lNum) {
		super(id, lNum);
	}

	@Override
	public String identify() {
		return "<param-decl> " + name + " on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(t.id);
		Main.log.prettyPrint(": ");
		typeName.prettyPrint();
	}

	public static ParamDecl parse(Scanner s) {
		enterParser("param decl");
		ParamDecl pd = new ParamDecl(s.curToken.id,s.curLineNum());
		pd.t = s.curToken;
		s.test(TokenKind.nameToken);
		s.readNextToken();
		s.skip(TokenKind.colonToken);
		pd.typeName = TypeName.parse(s);
		
		leaveParser("param decl");
		return pd;
	}

	public void check(Block curScope, Library lib) {
		typeName.check(curScope,lib);
	}
	
	@Override
	public void genCode(CodeFile f) {
		int off1 = -4*declLevel;
		int off2 = declOffset;
		f.genInstr("", "movl", off1+"(%ebp),%edx", "paramdecl "+t.id);
		f.genInstr("", "movl", off2+"(%edx),%eax", "paramdecl");
		//f.genInstr("", "pushl","%eax", "paramdecl");
	}
}