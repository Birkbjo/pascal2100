# Code file created by Pascal2100 compiler 2015-12-01 14:47:55
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl _main                         
        .globl main                         
_main:                                  
main:   call    prog$gcd_1              
        movl    $0,%eax                 # set status 0 and 
        ret                             # terminate program
func$gcd_2:
        enter   $32,$2                  # Start block
        movl    -8(%ebp),%edx           # paramdecl n
        movl    12(%edx),%eax           # paramdecl
        pushl   %eax                    # Expression
        movl    $0,%eax                 # 0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        sete    %al                     # Test =
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    -8(%ebp),%edx           # paramdecl m
        movl    8(%edx),%eax            # paramdecl
        movl    eax,-32(%edp)           # func decl i assignStatm
        jmp     .L0004                  
.L0003:                                 # # else-label
        movl    -8(%ebp),%edx           # paramdecl m
        movl    8(%edx),%eax            # paramdecl
        pushl   %eax                    # term
        movl    -8(%ebp),%edx           # paramdecl n
        movl    12(%edx),%eax           # paramdecl
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %edx                    
        movl    %edx,%eax               # mod in class factor Opr
        pushl   %eax                    # FuncCall
        movl    -8(%ebp),%edx           # paramdecl n
        movl    12(%edx),%eax           # paramdecl
        pushl   %eax                    # FuncCall
        call    func$gcd_2              
        movl    eax,-32(%edp)           # func decl i assignStatm
.L0004:                                 # End if-statement
        movl    -32(%ebp),%eax          
        leave                           
        ret                             
prog$gcd_1:
        enter   $36,$1                  # Start block
        movl    $462,%eax               # 462
        pushl   %eax                    # FuncCall
        movl    $1071,%eax              # 1071
        pushl   %eax                    # FuncCall
        call    func$gcd_2              
        .data                  
.L0005: .asciz   "gcd("
        .align  2              
        .text                  
        leal    .L0005,%eax             # Addr("gcd(")
        pushl   %eax                    
        call    write_string            
        addl    $4,%esp                 
        addl    $4,%esp                 
        movl    $44,%eax                # char 44
        pushl   %eax                    
        call    write_char              
        addl    $4,%esp                 
        addl    $4,%esp                 
        .data                  
.L0006: .asciz   ") = "
        .align  2              
        .text                  
        leal    .L0006,%eax             # Addr(") = ")
        pushl   %eax                    
        call    write_string            
        addl    $4,%esp                 
        addl    $4,%esp                 
        addl    $4,%esp                 
        leave                           
        ret                             
