package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.scanner.Scanner;

public class Block extends PascalSyntax {
	/*
	 * constdeclpart
	 * typedeclpart
	 * vardeclpart
	 * funcdelc
	 * procdecl
	 * statmlist
	 * 
	 */
	public PascalSyntax context = null;
	
	public Block(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String identify() {
		return null;
	}

	@Override
	void prettyPrint() {
		// TODO Auto-generated method stub
		
	}
	
	static Block parse(Scanner s) {
		return null;
	}

}
