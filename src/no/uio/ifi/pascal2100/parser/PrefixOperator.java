package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class PrefixOperator extends Operator {
	Token t; 
	public PrefixOperator(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<prefix-opr> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(t.kind.toString());
	}

	public static PrefixOperator parse(Scanner s) {
		enterParser("prefix operator");
		PrefixOperator preopr = new PrefixOperator(s.curLineNum());
		if(s.curToken.kind == TokenKind.addToken) {
			preopr.t = s.curToken;
			s.skip(TokenKind.addToken);
		} else {
			preopr.t = s.curToken;
			s.skip(TokenKind.subtractToken);
		}
		
		
		leaveParser("prefix operator");
		return preopr;
	}

	@Override
	public void genCode(CodeFile f) {

		if(t.kind == TokenKind.addToken) {
			f.genInstr("", "addl", "%ecx,%eax", "  +");
		} else {
			f.genInstr("","negl","%eax","  - (prefix)");
		}		
	}

}
