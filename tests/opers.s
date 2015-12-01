# Code file created by Pascal2100 compiler 2015-12-01 23:46:34
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
.L0004: .asciz   "- "
        .align  2              
        .text                  
        leal    .L0004,%eax             # Addr("- ")
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
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0006: .asciz   "+ "
        .align  2              
        .text                  
        leal    .L0006,%eax             # Addr("+ ")
        pushl   %eax                    # Push param #1.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #2.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0007: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0007,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #3.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        addl    %ecx,%eax               #   +
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of test
        ret                             
proc$testunarynumeric_2:
        enter   $32,$2                  # Start of testunarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_3             
        addl    $4,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_3             
        addl    $4,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_3             
        addl    $4,%esp                 # Pop parameters.
        leave                           # End of testunarynumeric
        ret                             
proc$test_9:
        enter   $32,$3                  # Start of test
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0010: .asciz   " + "
        .align  2              
        .text                  
        leal    .L0010,%eax             # Addr(" + ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0011: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0011,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0012: .asciz   " - "
        .align  2              
        .text                  
        leal    .L0012,%eax             # Addr(" - ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0013: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0013,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        subl    %ecx,%eax               #   -
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0014: .asciz   " * "
        .align  2              
        .text                  
        leal    .L0014,%eax             # Addr(" * ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0015: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0015,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        imull   %ecx,%eax               #   *
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
                                        # Start if-statement
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setne   %al                     # Test <>
        cmpl    $0,%eax                 
        je      .L0016                  
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0017: .asciz   " div "
        .align  2              
        .text                  
        leal    .L0017,%eax             # Addr(" div ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0018: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0018,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0019: .asciz   " mod "
        .align  2              
        .text                  
        leal    .L0019,%eax             # Addr(" mod ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0020: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0020,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0021: .asciz   " div "
        .align  2              
        .text                  
        leal    .L0021,%eax             # Addr(" div ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0022: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0022,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0023: .asciz   " mod "
        .align  2              
        .text                  
        leal    .L0023,%eax             # Addr(" mod ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0024: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0024,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
.L0016:                                 # End if-statement
        leave                           # End of test
        ret                             
proc$testbinarynumeric_8:
        enter   $32,$2                  # Start of testbinarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_9             
        addl    $8,%esp                 # Pop parameters.
        leave                           # End of testbinarynumeric
        ret                             
prog$operatortest_1:
        enter   $32,$1                  # Start of operatortest
        call    proc$testunarynumeric_2 
        call    proc$testbinarynumeric_8 
        leave                           # End of operatortest
        ret                             
