package no.uio.ifi.pascal2100.parser;

import java.util.ArrayList;
import java.util.ListIterator;

import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.main.Main;
import no.uio.ifi.pascal2100.scanner.*;

public class ProcCallStatement extends Statement {
	Token name;
	ArrayList<Expression> exprList = new ArrayList<Expression>();
	ProcDecl procRef;

	public ProcCallStatement(int n) {
		super(n);
	}

	@Override
	public String identify() {
		return "<proc-call> on line " + lineNum;
	}

	@Override
	void prettyPrint() {
		Main.log.prettyPrint(name.id);
		if (exprList.size() > 0) {
			Main.log.prettyPrint("(");
			for (int i = 0; i < exprList.size(); i++) {
				exprList.get(i).prettyPrint();
				if (i < exprList.size() - 1)
					Main.log.prettyPrint(", ");
			}
			Main.log.prettyPrint(")");
		}
	}

	public static ProcCallStatement parse(Scanner s) {
		enterParser("proc call");
		s.test(TokenKind.nameToken);
		ProcCallStatement ps = new ProcCallStatement(s.curLineNum());
		ps.name = s.curToken;
		s.readNextToken();
		if (s.curToken.kind == TokenKind.leftParToken) {
			s.skip(TokenKind.leftParToken);
			ps.exprList.add(Expression.parse(s));
			while (s.curToken.kind == TokenKind.commaToken) {
				s.skip(TokenKind.commaToken);
				ps.exprList.add(Expression.parse(s));

			}
			s.skip(TokenKind.rightParToken);
		}

		leaveParser("proc call");
		return ps;
	}

	@Override
	void check(Block curScope, Library lib) {
		PascalDecl d = curScope.findDecl(name.id, this);
		procRef = (ProcDecl) d;
		for (Expression e : exprList) {
			e.check(curScope, lib);
		}

	}

	@Override
	public void genCode(CodeFile f) {
		count = 0;
		if (procRef.isInLibrary()) { // write 
			for (Expression e : exprList) {
				count++;
				genWriteType(e, f, count);
			}
		} else {
			for (ListIterator<Expression> iterator = exprList.listIterator(exprList.size());
					iterator.hasPrevious();) {
				Expression e = iterator.previous();
				e.genCode(f);
				count++;
				f.genInstr("","pushl","%eax","Push param #" + count + ".");
			}
			f.genInstr("", "call", procRef.label, "");
			
			if(!exprList.isEmpty()) {
				f.genInstr("", "addl", "$"+exprList.size()*4+",%esp", "Pop parameters.");
				if(procRef != null) {
					
				} else {
					f.genInstr("", "movl", "%eax,32(%ebp)", "");
				}
				
			}
		}
	}

	/**
	 * Ad-hoc solution for type-checking as we did not implement it in part 3.
	 * Generates the needed code for write, per parameter given to write.
	 * 
	 * @param e
	 * @return
	 */
	private void genWriteType(Expression e, CodeFile f, int count) {
		Factor fac = e.expr1.termList.get(0).factorList.get(0);
		if (fac instanceof NumericLiteral) {
			e.genCode(f);
			f.genInstr("", "pushl", "%eax", "Push param #"+ count + ".");
			f.genInstr("", "call", "write_int", "");
		} else if (fac instanceof Variable) {
			Variable v = (Variable) fac;
			v.genCode(f);
			f.genInstr("", "pushl", "%eax", "");
			if(v.ref instanceof ConstDecl) {
				ConstDecl cd = (ConstDecl) v.ref;
				if(cd.con instanceof NumericLiteral) {
					f.genInstr("", "call", "write_int", "");
				} else if(cd.con instanceof StringLiteral) {
					
					f.genInstr("", "call", "write_char", "");
				} else {
					f.genInstr("", "", "", "Proccall nono");
				}
			} else {
				f.genInstr("", "call", "write_int", "");
			}
		} else if (fac instanceof StringLiteral) {
			if (((StringLiteral) fac).slit.length() == 1) {
				e.genCode(f);
				f.genInstr("", "pushl", "%eax", "Push param #"+ count+".");
				f.genInstr("", "call", "write_char", "");
			} else { // string
				e.genCode(f);
				f.genInstr("", "pushl", "%eax", "Push param #"+ count+".");
				f.genInstr("", "call", "write_string", "");
			}
		} else if (fac instanceof Negation) {
			e.genCode(f);
			f.genInstr("", "pushl", "%eax", "Push param #"+ count+".");
			f.genInstr("", "call", "write_int", "");
		}
		f.genInstr("", "addl", "$4,%esp", "Pop parameter.");
	}
}
