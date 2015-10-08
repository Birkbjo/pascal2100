package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

abstract class Statement extends PascalSyntax {
	
	Statement(int n) {
		super(n);
		// TODO Auto-generated constructor stub
		
	}

	
	
	public static Statement parse(Scanner s) {
		// TODO Auto-generated method stub
		enterParser("statement");
		
		Statement st = null;
		switch (s.curToken.kind) {
		case beginToken:
			st = CompoundStatement.parse(s); break;
		case ifToken:
			st = IfStatement.parse(s); break;
		case nameToken:
			switch (s.nextToken.kind) {
			case assignToken:
			case leftBracketToken:
				st = AssignStatement.parse(s); break;
			default:
				st = EmptyStatement.parse(s); break;
			} break;
		case whileToken:
			st = WhileStatement.parse(s); break;
		default:
			st = EmptyStatement.parse(s); break;
		}
		
		leaveParser("statement");
		return st;
	}

}