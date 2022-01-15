.section .data

	.equ DATA_SIZE, 56


.section .text

	.global checkRefrigerated
	checkRefrigerated:
		movl $0, %eax
		movq $0, %r9
	
	loop:
		cmpl (%rdi), %edx
		jne skip
		cmpl 4(%rdi), %ecx
		jne skip
		cmpl 8(%rdi), %r8d
		jne skip
		cmpl $1, 32(%rdi)
		jne skip
		
		movl $1, %eax
		jmp end
	skip:
	
		addq $DATA_SIZE, %rdi
		subl $1, %esi
		cmpl $0, %esi
		jne loop

	
	end:	
		
		ret
