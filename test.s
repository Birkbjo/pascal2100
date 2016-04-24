# Code file created by Pascal2100 compiler 2015-08-18 15:19:47
.extern write_char
.extern write_int
.extern write_string
.globl _main
.globl main
_main:
main:
	call prog$Mini_1 # Start program
	movl $0,%eax # Set status 0 and
	ret # terminate the program
prog$Mini_1:
	enter $32,$1 # Start of Mini
	movl $120,%eax # char 120
	pushl %eax # Push param #1.
	call write_char
	addl $4,%esp # Pop parameter.
	leave # End of Mini
	ret