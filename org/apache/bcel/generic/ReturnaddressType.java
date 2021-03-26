/*     */ package org.apache.bcel.generic;
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
/*     */ public class ReturnaddressType
/*     */   extends Type
/*     */ {
/*  68 */   public static final ReturnaddressType NO_TARGET = new ReturnaddressType();
/*     */ 
/*     */   
/*     */   private InstructionHandle returnTarget;
/*     */ 
/*     */   
/*     */   private ReturnaddressType() {
/*  75 */     super((byte)16, "<return address>");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReturnaddressType(InstructionHandle returnTarget) {
/*  82 */     super((byte)16, "<return address targeting " + returnTarget + ">");
/*  83 */     this.returnTarget = returnTarget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rat) {
/*  90 */     if (!(rat instanceof ReturnaddressType)) {
/*  91 */       return false;
/*     */     }
/*  93 */     return ((ReturnaddressType)rat).returnTarget.equals(this.returnTarget);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionHandle getTarget() {
/* 100 */     return this.returnTarget;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ReturnaddressType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */