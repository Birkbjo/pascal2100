package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.CodeFile;
import no.uio.ifi.pascal2100.parser.Type;
public class Library extends Block {
	
	public Library() {
		TypeDecl integer = new TypeDecl("integer",-1);
		TypeName intType = new TypeName(-1);
		intType.name = "integer";
		integer.type = intType;
		
		TypeDecl charD = new TypeDecl("char",-1);
		TypeName charT = new TypeName(-1);
		charT.name = "char";
		charD.type = intType;
		
		ProcDecl write = new ProcDecl("write",-1);
		
		ConstDecl eol = new ConstDecl("eol",-1);

		StringLiteral con = new StringLiteral(-1);
		con.slit = "\n";
		eol.con = con;
		
		
		TypeDecl bool = new TypeDecl("boolean",-1);
		EnumType etype = new EnumType(-1);
		EnumLiteral falseE = new EnumLiteral("enum value false (=0)",-1);
		EnumLiteral trueE = new EnumLiteral("enum value true (=1)",-1);
		etype.eLiteral.add(falseE);
		etype.eLiteral.add(trueE);
		bool.type = etype;
		this.addDecl("integer", integer);
		this.addDecl("write", write);
		this.addDecl("eol", eol);
		this.addDecl("boolean", bool);
		this.addDecl("char",charD);
		this.addDecl("false",falseE);
		this.addDecl("true",trueE);
	}
	
	@Override
	public void genCode(CodeFile f) {
		f.genInstr("", ".extern write_char", "", "");
		f.genInstr("", ".extern write_int", "", "");
		f.genInstr("", ".extern write_string", "", "");
		f.genInstr("", ".globl  _main", "", "");
		f.genInstr("", ".globl  main", "", "");
	}

}
