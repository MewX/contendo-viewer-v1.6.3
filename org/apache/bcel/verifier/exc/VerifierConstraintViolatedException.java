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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class VerifierConstraintViolatedException
/*     */   extends RuntimeException
/*     */ {
/*     */   private String detailMessage;
/*     */   
/*     */   VerifierConstraintViolatedException() {}
/*     */   
/*     */   VerifierConstraintViolatedException(String message) {
/*  84 */     super(message);
/*  85 */     this.detailMessage = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extendMessage(String pre, String post) {
/*  95 */     if (pre == null) pre = ""; 
/*  96 */     if (this.detailMessage == null) this.detailMessage = ""; 
/*  97 */     if (post == null) post = ""; 
/*  98 */     this.detailMessage = pre + this.detailMessage + post;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 105 */     return this.detailMessage;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/exc/VerifierConstraintViolatedException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */