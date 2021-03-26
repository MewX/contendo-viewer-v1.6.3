/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerificationResult
/*     */ {
/*     */   public static final int VERIFIED_NOTYET = 0;
/*     */   public static final int VERIFIED_OK = 1;
/*     */   public static final int VERIFIED_REJECTED = 2;
/*     */   private static final String VERIFIED_NOTYET_MSG = "Not yet verified.";
/*     */   private static final String VERIFIED_OK_MSG = "Passed verification.";
/*  89 */   public static final VerificationResult VR_NOTYET = new VerificationResult(0, "Not yet verified.");
/*     */   
/*  91 */   public static final VerificationResult VR_OK = new VerificationResult(1, "Passed verification.");
/*     */ 
/*     */   
/*     */   private int numeric;
/*     */ 
/*     */   
/*     */   private String detailMessage;
/*     */ 
/*     */   
/*     */   private VerificationResult() {}
/*     */ 
/*     */   
/*     */   public VerificationResult(int status, String message) {
/* 104 */     this.numeric = status;
/* 105 */     this.detailMessage = message;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 110 */     return this.numeric;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 115 */     return this.detailMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 122 */     if (!(o instanceof VerificationResult)) return false; 
/* 123 */     VerificationResult other = (VerificationResult)o;
/* 124 */     return (other.numeric == this.numeric && other.detailMessage.equals(this.detailMessage));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 131 */     String ret = "";
/* 132 */     if (this.numeric == 0) ret = "VERIFIED_NOTYET"; 
/* 133 */     if (this.numeric == 1) ret = "VERIFIED_OK"; 
/* 134 */     if (this.numeric == 2) ret = "VERIFIED_REJECTED"; 
/* 135 */     ret = ret + "\n" + this.detailMessage + "\n";
/* 136 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/VerificationResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */