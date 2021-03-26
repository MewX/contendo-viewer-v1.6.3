/*     */ package org.apache.bcel.verifier.structurals;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Frame
/*     */ {
/*     */   protected static UninitializedObjectType _this;
/*     */   private LocalVariables locals;
/*     */   private OperandStack stack;
/*     */   
/*     */   public Frame(int maxLocals, int maxStack) {
/*  93 */     this.locals = new LocalVariables(maxLocals);
/*  94 */     this.stack = new OperandStack(maxStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Frame(LocalVariables locals, OperandStack stack) {
/* 101 */     this.locals = locals;
/* 102 */     this.stack = stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 109 */     Frame f = new Frame(this.locals.getClone(), this.stack.getClone());
/* 110 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Frame getClone() {
/* 117 */     return (Frame)clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariables getLocals() {
/* 124 */     return this.locals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperandStack getStack() {
/* 131 */     return this.stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 138 */     if (!(o instanceof Frame)) return false; 
/* 139 */     Frame f = (Frame)o;
/* 140 */     return (this.stack.equals(f.stack) && this.locals.equals(f.locals));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 147 */     String s = "Local Variables:\n";
/* 148 */     s = s + this.locals;
/* 149 */     s = s + "OperandStack:\n";
/* 150 */     s = s + this.stack;
/* 151 */     return s;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/Frame.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */