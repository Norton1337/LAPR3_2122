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
		
		# Get amount of slots in the 2d matrix
		movl $1, %eax
		mull %ebx
		mull %ebx
		mull %ebx
		addl %eax, %r9d
		movl (%rdi), %eax
		
	loop:
		cmpl $0, %eax  #If position has a value of 0 then skip
		je skip
		addl $1, %r10d #Count amount of existing containers
	skip:
		addq $4, %rdi #Go to next position
		addl $1, %ecx #Count up to the amount of slots in the matrix
		movl (%rdi), %eax
		cmpl %ecx, %r9d  #if %ecx == %eax then we checked every position
		jg loop
		jl loop
		
	end:
		
		subl %r10d, %r9d #Subtracting amount of existing containers from total amount of slots
		#Saving free slots in the 4 most significant bytes and the number of occupied slots in the four least significant bytes.
		movslq %r9d, %rax
		SHL $32, %rax
		movq %r10, %rbx
		OR %rbx, %rax
		popq %rbx
		ret
