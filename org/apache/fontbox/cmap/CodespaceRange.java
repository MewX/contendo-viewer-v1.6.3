/*     */ package org.apache.fontbox.cmap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CodespaceRange
/*     */ {
/*     */   private byte[] start;
/*     */   private byte[] end;
/*     */   private int startInt;
/*     */   private int endInt;
/*  32 */   private int codeLength = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodeLength() {
/*  48 */     return this.codeLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getEnd() {
/*  57 */     return this.end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setEnd(byte[] endBytes) {
/*  66 */     this.end = endBytes;
/*  67 */     this.endInt = CMap.toInt(endBytes, endBytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getStart() {
/*  76 */     return this.start;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setStart(byte[] startBytes) {
/*  85 */     this.start = startBytes;
/*  86 */     this.codeLength = this.start.length;
/*  87 */     this.startInt = CMap.toInt(startBytes, startBytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(byte[] code) {
/*  95 */     return isFullMatch(code, code.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFullMatch(byte[] code, int codeLen) {
/* 104 */     if (codeLen == this.codeLength) {
/*     */       
/* 106 */       int value = CMap.toInt(code, codeLen);
/* 107 */       if (value >= this.startInt && value <= this.endInt)
/*     */       {
/* 109 */         return true;
/*     */       }
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cmap/CodespaceRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */