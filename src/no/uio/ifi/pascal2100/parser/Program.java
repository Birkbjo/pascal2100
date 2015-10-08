package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Program extends PascalDecl {
	Block block = null;
	public Program(String id, int lNum) {
		super(id, lNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return "<program> " + name + " on line " + lineNum;
	}

	@Override
	public void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	public static Program parse(Scanner s) {
		enterParser("program");
		s.skip(TokenKind.programToken);
		//Name only contains letters (handled by scanner), so we can skip this.
		s.test(TokenKind.nameToken);
		Program p = new Program(s.curToken.id,s.curLineNum());
		p.block.context = p;
		s.readNextToken();
		s.skip(TokenKind.semicolonToken);
		p.block = Block.parse(s);
		s.skip(TokenKind.dotToken);
		
		leaveParser("program");
		return p;
	}

}
