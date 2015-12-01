program OperatorTest;

type bool = Boolean;  int = integer;

procedure TestUnaryBoolean;

   procedure Test (x: bool);
   begin
      write('not ', x, ' = ', not x, eol);
   end; { Test }

begin
   Test(false);  Test(true);
end; { TestUnaryBoolean }
begin
   TestUnaryBoolean; 
end.
