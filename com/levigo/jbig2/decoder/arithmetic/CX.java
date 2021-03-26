/*    */ package com.levigo.jbig2.decoder.arithmetic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CX
/*    */ {
/*    */   private int index;
/*    */   private final byte[] cx;
/*    */   private final byte[] mps;
/*    */   
/*    */   public CX(int paramInt1, int paramInt2) {
/* 35 */     this.index = paramInt2;
/* 36 */     this.cx = new byte[paramInt1];
/* 37 */     this.mps = new byte[paramInt1];
/*    */   }
/*    */   
/*    */   protected int cx() {
/* 41 */     return this.cx[this.index] & Byte.MAX_VALUE;
/*    */   }
/*    */   
/*    */   protected void setCx(int paramInt) {
/* 45 */     this.cx[this.index] = (byte)(paramInt & 0x7F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected byte mps() {
/* 52 */     return this.mps[this.index];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toggleMps() {
/* 59 */     this.mps[this.index] = (byte)(this.mps[this.index] ^ 0x1);
/*    */   }
/*    */   
/*    */   protected int getIndex() {
/* 63 */     return this.index;
/*    */   }
/*    */   
/*    */   public void setIndex(int paramInt) {
/* 67 */     this.index = paramInt;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/arithmetic/CX.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */