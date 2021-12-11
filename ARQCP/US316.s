.section .data
	.global num
	.global matrix
	.global structArraySize
.section .text
	.global totalOccupied
	totalOccupied:
		pushq %rbx
		movl structArraySize(%rip), %ecx
		movl $0, %r8d
	loop:
		cmpl $0, %ecx
		je end
		movl (%rdi), %eax
		addq $4, %rdi
		movl (%rdi), %ebx
		addq $4, %rdi
		movl (%rdi), %edx
		pushq %rdi
		pushq %rcx
		movl %eax, %edi
		movl %ebx, %esi
		call containerExists
		popq %rcx
		popq %rdi
		cmpl $0, %eax
		je skip
		addl $1, %r8d
	skip:
		subl $1, %ecx
		addq $4, %rdi
		jmp loop
	end:
		movl %r8d, %eax
		popq %rbx
		ret
