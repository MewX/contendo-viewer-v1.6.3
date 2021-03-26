/*    */ package com.levigo.jbig2.decoder.huffman;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.stream.ImageInputStream;
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
/*    */ class ValueNode
/*    */   extends Node
/*    */ {
/*    */   private int rangeLen;
/*    */   private int rangeLow;
/*    */   private boolean isLowerRange;
/*    */   
/*    */   protected ValueNode(HuffmanTable.Code paramCode) {
/* 35 */     this.rangeLen = paramCode.rangeLength;
/* 36 */     this.rangeLow = paramCode.rangeLow;
/* 37 */     this.isLowerRange = paramCode.isLowerRange;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected long decode(ImageInputStream paramImageInputStream) throws IOException {
/* 43 */     if (this.isLowerRange)
/*    */     {
/* 45 */       return this.rangeLow - paramImageInputStream.readBits(this.rangeLen);
/*    */     }
/*    */     
/* 48 */     return this.rangeLow + paramImageInputStream.readBits(this.rangeLen);
/*    */   }
/*    */ 
/*    */   
/*    */   static String bitPattern(int paramInt1, int paramInt2) {
/* 53 */     char[] arrayOfChar = new char[paramInt2];
/* 54 */     for (byte b = 1; b <= paramInt2; b++) {
/* 55 */       arrayOfChar[b - 1] = ((paramInt1 >> paramInt2 - b & 0x1) != 0) ? '1' : '0';
/*    */     }
/* 57 */     return new String(arrayOfChar);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/ValueNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */