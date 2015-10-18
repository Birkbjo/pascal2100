package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

abstract public class Factor extends PascalSyntax {

	public Factor(int n) {
		super(n);
	}

	abstract public String identify();

	public static Factor parse(Scanner s) {
		enterParser("factor");
		
		Factor f = null;
		
		switch (s.curToken.kind) {
		case notToken:
			f = Negation.parse(s);
			break;
		case rightParToken:
			f = InnerExpr.parse(s);
			break;
		case stringValToken: // string OR intval is a constant
		case intValToken:
			f = Constant.parse(s);
			break;
			
		default: // should be a nametoken
			s.test(TokenKind.nameToken);

			switch (s.nextToken.kind) {
			case leftBracketToken:
				f =  Variable.parse(s);
				break;
			case leftParToken:
				f = FuncCall.parse(s);
				break;
			default:
				f = Variable.parse(s);
				break;
			}
		}

		leaveParser("factor");
		return f;
	}

}
