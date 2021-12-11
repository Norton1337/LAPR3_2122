.section .data
	.global num
	.global matrix
	
.section .text
	.global containerExists
	containerExists:
		pushq %rbx
		movq $0, %rax
		movl num(%rip), %ecx
		subl $1, %ecx
		
		# Check if given coordinates are not larger than matrix size
		cmpl %ecx, %edi
		jg end
		cmpl %ecx, %esi
		jg end
		cmpl %ecx, %edx
		jg end
		
		# Check if given coordinates are not smaller than 0
		cmpl $0, %edi
		jl end
		cmpl $0, %esi
		jl end
		cmpl $0, %edx
		jl end
		addl $1, %ecx
		movq $0, %rbx
		
		# Move in z axis
		movl $4, %eax
		mull %edx
		addl %eax, %ebx

		# Move in y axis
		movl $4, %eax
		mull %ecx
		mull %esi
		addl %eax, %ebx
		
		# Move in x axis
		movl $4, %eax
		mull %ecx
		mull %ecx
		mull %edi
		addl %eax, %ebx
		
		leaq matrix(%rip), %rdi 
		movq $0, %rax
		
		# Check if desired position has a container
		addq %rbx, %rdi
		movl (%rdi), %ebx
		cmpl $0, %ebx
		je end
		movl $1, %eax
		
	end:
		popq %rbx
		ret
