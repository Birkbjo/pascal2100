# Code file created by Pascal2100 compiler 2015-12-01 19:44:08
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl  _main                         
        .globl  main                         
_main:                                  
main:   call    prog$operatortest_1     # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$test_3:
        enter   $32,$3                  # Start of test
        .data                  
.L0004: .asciz   "not "
        .align  2              
        .text                  
        leal    .L0004,%eax             # Addr("not ")
        pushl   %eax                    # Push param #1.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #2.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0005: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0005,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #3.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        xorl    $0x1,%eax               #   not
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of test
        ret                             
proc$testunaryboolean_2:
        enter   $32,$2                  # Start of testunaryboolean
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            #   enum value false (=0)
        pushl   %eax                    # Push param #1
        call    proc$test_3             
        addl    $4,%esp                 
        movl    %eax,32(%ebp)           
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            #   enum value true (=1)
        pushl   %eax                    # Push param #1
        call    proc$test_3             
        addl    $4,%esp                 
        movl    %eax,32(%ebp)           
        leave                           # End of testunaryboolean
        ret                             
prog$operatortest_1:
        enter   $32,$1                  # Start of operatortest
        call    proc$testunaryboolean_2 
        leave                           # End of operatortest
        ret                             
