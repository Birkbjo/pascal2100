# Code file created by Pascal2100 compiler 2015-11-30 17:56:21
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl _main                         
        .globl main                         
_main:                                  
main:   call    prog$mini_1             
        movl    $0,%eax                 # set status 0 and 
        ret                             # terminate program
prog$mini_2:
        enter   $32,$1                  
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        leave                           
        ret                             
