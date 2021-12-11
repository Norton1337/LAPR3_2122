.section .data
	.global num
	.global matrix
.section .text
	
	.global countContainers
	countContainers:
		pushq %rbx
		movq $0, %rbx
		movq $0, %rcx
		movq $0, %r9
		movq $0, %r10
		movl num(%rip), %ebx
		leaq matrix(%rip), %rdi 
		
		movl $1, %eax
		mull %ebx
		mull %ebx
		mull %ebx
		addl %eax, %r9d
		movl (%rdi), %eax
		
	loop:
		cmpl $0, %eax
		je skip
		addl $1, %r10d
	skip:
		addq $4, %rdi
		addl $1, %ecx
		movl (%rdi), %eax
		cmpl %ecx, %r9d
		jg loop
		jl loop
		
	end:
		subl %r10d, %r9d
		movslq %r9d, %rax
		SHL $32, %rax
		movq %r10, %rbx
		OR %rbx, %rax
		popq %rbx
		ret
