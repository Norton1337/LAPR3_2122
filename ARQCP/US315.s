.section .data
	.global num
	.global matrix
	
.section .text
	.global containerExists
	containerExists:
		pushq %rbx
		movq $0, %rax
		movl num(%rip), %ebx
		subl $1, %ebx
		
		cmpl %ebx, %edi
		jg end
		cmpl %ebx, %esi
		jg end
		cmpl %ebx, %edx
		jg end
		
		cmpl $0, %edi
		jl end
		cmpl $0, %esi
		jl end
		cmpl $0, %edx
		jl end
		
		
		movq $0, %rbx
		movl $4, %eax
		mull %edx
		addl %eax, %ebx

		movl $20, %eax
		mull %esi
		addl %eax, %ebx
		
		movl $100, %eax
		mull %edi
		addl %eax, %ebx
		
		leaq matrix(%rip), %rdi 
		movq $0, %rax
		
		addq %rbx, %rdi
		movl (%rdi), %ebx
		cmpl $0, %ebx
		je end
		movl $1, %eax
		
	end:
		popq %rbx
		ret
