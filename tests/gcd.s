# Code file created by Pascal2100 compiler 2015-11-30 14:05:55
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl _main                         
        .globl main                         
_main:                                  
main:   call    prog$gcd_1              # 	Start program
        movl    $0,%eax                 # 	set status 0 and 
        ret                             # 	terminate program
prog$gcd_2:
        enter   $33,$1                  
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        leave                           
        ret                             
