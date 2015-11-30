# Code file created by Pascal2100 compiler 2015-11-30 16:59:47
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl _main                         
        .globl main                         
_main:                                  
main:   call    prog$tenstars_1         
        movl    $0,%eax                 # # set status 0 and 
        ret                             # # terminate program
prog$tenstars_2:
        enter   $33,$1                  
.L0003:                                 # Start while-statement
        pushl   %eax                    
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setl    %al                     # Test <
        cmpl    $0,%eax                 
        je      .L0004                  
        pushl   %eax                    
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setl    %al                     # Test <
        jmp     .L0003                  
.L0004:                                 # End while-statement
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        leave                           
        ret                             
