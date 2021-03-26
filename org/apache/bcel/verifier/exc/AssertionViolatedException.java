/*     */ package org.apache.bcel.verifier.exc;
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
/*     */ public final class AssertionViolatedException
/*     */   extends RuntimeException
/*     */ {
/*     */   private String detailMessage;
/*     */   
/*     */   public AssertionViolatedException() {}
/*     */   
/*     */   public AssertionViolatedException(String message) {
/*  76 */     super(message = "INTERNAL ERROR: " + message);
/*  77 */     this.detailMessage = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extendMessage(String pre, String post) {
/*  85 */     if (pre == null) pre = ""; 
/*  86 */     if (this.detailMessage == null) this.detailMessage = ""; 
/*  87 */     if (post == null) post = ""; 
/*  88 */     this.detailMessage = pre + this.detailMessage + post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  95 */     return this.detailMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 102 */     AssertionViolatedException ave = new AssertionViolatedException("Oops!");
/* 103 */     ave.extendMessage("\nFOUND:\n\t", "\nExiting!!\n");
/* 104 */     throw ave;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStackTrace() {
/* 112 */     return Utility.getStackTrace(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/exc/AssertionViolatedException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */