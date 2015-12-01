program OperatorTest;

type bool = Boolean;  int = integer;


procedure TestUnaryNumeric;

   procedure Test (x: int);
   begin
      write('- ', x, ' = ', -x, eol);
      write('+ ', x, ' = ', +x, eol);
   end; { Test }

begin
   Test(17);  Test(-11);  Test(0);
end; { TestUnaryNumeric }


procedure TestBinaryNumeric;

   procedure Test (x: int;  y: int);
   begin
      write(x, ' + ', y, ' = ', x + y, eol);
      write(x, ' - ', y, ' = ', x - y, eol);
      write(x, ' * ', y, ' = ', x * y, eol);
      if y <> 0 then begin
	 write(x, ' div ', y, ' = ', x div y, eol);
	 write(x, ' mod ', y, ' = ', x mod y, eol);
      end
   end; { Test }

begin
   Test(17, 17);  Test(17, -11);  Test(17, 0);
   Test(-11, 17);  Test(-11, -11);  Test(17, 0);
   Test(0, 17);  Test(0, -11);  Test(0, 0);
end; { TestBinaryNumeric }


begin
	TestUnaryNumeric; TestBinaryNumeric;
end.
