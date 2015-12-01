package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;
import java.util.HashMap;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.scanner.TokenKind;

public class Block extends PascalSyntax {
	HashMap<String,PascalDecl> decls = new HashMap<String,PascalDecl>();
	Block outerScope = null;
	ConstDeclPart constDeclPart;
	TypeDeclPart typeDeclPart;
	VarDeclPart varDeclPart;
	ArrayList<ProcDecl> procOrFunc = new ArrayList<ProcDecl>();
	StatementList statmList;
	PascalDecl context = null;
	
	public Block(int n) {
		super(n);
	}
	public Block(){}
	@Override
	public String identify() {
		return "<block> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		if(constDeclPart != null) {
			constDeclPart.prettyPrint();
		}
		if(typeDeclPart != null) {
			typeDeclPart.prettyPrint();
		}
		if(varDeclPart != null) {
			varDeclPart.prettyPrint();
		}
		for(ProcDecl p: procOrFunc) p.prettyPrint();
		Main.log.prettyPrintLn("begin"); Main.log.prettyIndent();
		statmList.prettyPrint();
		Main.log.prettyOutdent();
		Main.log.prettyPrint("end");

	}

	static Block parse(Scanner s) {
		enterParser("block");

		Block b = new Block(s.curLineNum());
		if (s.curToken.kind == TokenKind.constToken) {
			b.constDeclPart = ConstDeclPart.parse(s);
		}
		if (s.curToken.kind == TokenKind.typeToken) {
			b.typeDeclPart = TypeDeclPart.parse(s);
		}
		if (s.curToken.kind == TokenKind.varToken) {
			b.varDeclPart = VarDeclPart.parse(s);
		}

		// Handle funcDecl and procDecl
		while (s.curToken.kind == TokenKind.functionToken
				|| s.curToken.kind == TokenKind.procedureToken) {
			b.procOrFunc.add(ProcDecl.parse(s));
		}

		s.skip(TokenKind.beginToken);
		b.statmList = StatementList.parse(s);
		s.skip(TokenKind.endToken);
		leaveParser("block");
		return b;
	}

	void check(Block curScope, Library lib) {
		outerScope = curScope;
		if(constDeclPart != null) {
			constDeclPart.check(this,lib);
			for(ConstDecl cd:  constDeclPart.constDecl) {
				addDecl(cd.name,cd);
			}
		}
		if(typeDeclPart != null) {
			typeDeclPart.check(this,lib);
			for(TypeDecl td: typeDeclPart.typeDeclList) {
				addDecl(td.name,td);
			}
		}
		
		if(varDeclPart != null) {
			varDeclPart.check(this,lib);
			for(VarDecl vd: varDeclPart.varDeclList) {
				addDecl(vd.name,vd);
			}
		}
		
		for(ProcDecl pd: procOrFunc) {
			addDecl(pd.name, pd);
			pd.check(this,lib);
		}
		statmList.check(this,lib);
	}
	
	void addDecl(String id, PascalDecl d) {
		if(decls.containsKey(id)) {
			d.error(id + " declared twice in same block!");
		}
		decls.put(id, d);
	}
	
	PascalDecl findDecl(String id, PascalSyntax where) {
		PascalDecl d = decls.get(id);
		if(d != null) {
			Main.log.noteBinding(id, where, d);
			return d;
		}
		if(outerScope != null) {
			return outerScope.findDecl(id,where);
		}
		where.error("Name " + id + " is unknown!");
		return null;
	}
	@Override
	public void genCode(CodeFile f) {
		
		for(ProcDecl pd: procOrFunc) {
			pd.declLevel = context.declLevel + 1;
			pd.genCode(f);
		}
		int start = 32;
		
		if(varDeclPart != null) {
			start += (varDeclPart.varDeclList.size()*4);
			int varoff = -36;
			for(VarDecl v: varDeclPart.varDeclList) {
				v.declLevel = context.declLevel;
				v.declOffset = varoff;
				varoff -= 4;
			}
		}
	
		
		f.genInstr(context.label, "enter","$"+start+",$"+context.declLevel, "Start of " + context.name);
		statmList.genCode(f);
		/*if(context instanceof FuncDecl) {
			f.genInstr("","movl","-32(%ebp),%eax","");
		}*/
		f.genInstr("","leave","","End of " + context.name);
		f.genInstr("", "ret", "", "");
	}

}
