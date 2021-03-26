/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.classfile.CodeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CodeExceptionGen
/*     */   implements Cloneable, InstructionTargeter
/*     */ {
/*     */   private InstructionHandle start_pc;
/*     */   private InstructionHandle end_pc;
/*     */   private InstructionHandle handler_pc;
/*     */   private ObjectType catch_type;
/*     */   
/*     */   public CodeExceptionGen(InstructionHandle start_pc, InstructionHandle end_pc, InstructionHandle handler_pc, ObjectType catch_type) {
/*  92 */     setStartPC(start_pc);
/*  93 */     setEndPC(end_pc);
/*  94 */     setHandlerPC(handler_pc);
/*  95 */     this.catch_type = catch_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CodeException getCodeException(ConstantPoolGen cp) {
/* 108 */     return new CodeException(this.start_pc.getPosition(), this.end_pc.getPosition() + this.end_pc.getInstruction().getLength(), this.handler_pc.getPosition(), (this.catch_type == null) ? 0 : cp.addClass(this.catch_type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPC(InstructionHandle start_pc) {
/* 118 */     BranchInstruction.notifyTarget(this.start_pc, start_pc, this);
/* 119 */     this.start_pc = start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPC(InstructionHandle end_pc) {
/* 126 */     BranchInstruction.notifyTarget(this.end_pc, end_pc, this);
/* 127 */     this.end_pc = end_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandlerPC(InstructionHandle handler_pc) {
/* 134 */     BranchInstruction.notifyTarget(this.handler_pc, handler_pc, this);
/* 135 */     this.handler_pc = handler_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/* 143 */     boolean targeted = false;
/*     */     
/* 145 */     if (this.start_pc == old_ih) {
/* 146 */       targeted = true;
/* 147 */       setStartPC(new_ih);
/*     */     } 
/*     */     
/* 150 */     if (this.end_pc == old_ih) {
/* 151 */       targeted = true;
/* 152 */       setEndPC(new_ih);
/*     */     } 
/*     */     
/* 155 */     if (this.handler_pc == old_ih) {
/* 156 */       targeted = true;
/* 157 */       setHandlerPC(new_ih);
/*     */     } 
/*     */     
/* 160 */     if (!targeted) {
/* 161 */       throw new ClassGenException("Not targeting " + old_ih + ", but {" + this.start_pc + ", " + this.end_pc + ", " + this.handler_pc + "}");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTarget(InstructionHandle ih) {
/* 169 */     return (this.start_pc == ih || this.end_pc == ih || this.handler_pc == ih);
/*     */   }
/*     */   
/*     */   public void setCatchType(ObjectType catch_type) {
/* 173 */     this.catch_type = catch_type;
/*     */   } public ObjectType getCatchType() {
/* 175 */     return this.catch_type;
/*     */   }
/*     */   
/*     */   public InstructionHandle getStartPC() {
/* 179 */     return this.start_pc;
/*     */   }
/*     */   
/*     */   public InstructionHandle getEndPC() {
/* 183 */     return this.end_pc;
/*     */   }
/*     */   
/*     */   public InstructionHandle getHandlerPC() {
/* 187 */     return this.handler_pc;
/*     */   }
/*     */   public String toString() {
/* 190 */     return "CodeExceptionGen(" + this.start_pc + ", " + this.end_pc + ", " + this.handler_pc + ")";
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 195 */       return super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 197 */       System.err.println(e);
/* 198 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/CodeExceptionGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */