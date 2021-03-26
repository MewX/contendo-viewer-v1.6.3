/*     */ package com.levigo.jbig2.decoder.huffman;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class HuffmanTable
/*     */ {
/*     */   public static class Code
/*     */   {
/*     */     final int prefixLength;
/*     */     final int rangeLength;
/*     */     final int rangeLow;
/*     */     final boolean isLowerRange;
/*  40 */     int code = -1;
/*     */     
/*     */     public Code(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) {
/*  43 */       this.prefixLength = param1Int1;
/*  44 */       this.rangeLength = param1Int2;
/*  45 */       this.rangeLow = param1Int3;
/*  46 */       this.isLowerRange = param1Boolean;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  51 */       return ((this.code != -1) ? ValueNode.bitPattern(this.code, this.prefixLength) : "?") + "/" + this.prefixLength + "/" + this.rangeLength + "/" + this.rangeLow;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  56 */   private InternalNode rootNode = new InternalNode();
/*     */   
/*     */   public void initTree(List<Code> paramList) {
/*  59 */     preprocessCodes(paramList);
/*     */     
/*  61 */     for (Code code : paramList) {
/*  62 */       this.rootNode.append(code);
/*     */     }
/*  64 */     System.out.println("");
/*     */   }
/*     */   
/*     */   public long decode(ImageInputStream paramImageInputStream) throws IOException {
/*  68 */     return this.rootNode.decode(paramImageInputStream);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  73 */     return this.rootNode + "\n";
/*     */   }
/*     */   
/*     */   public static String codeTableToString(List<Code> paramList) {
/*  77 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*  79 */     for (Code code : paramList) {
/*  80 */       stringBuilder.append(code.toString()).append("\n");
/*     */     }
/*     */     
/*  83 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private void preprocessCodes(List<Code> paramList) {
/*  88 */     int i = 0;
/*     */     
/*  90 */     for (Code code : paramList) {
/*  91 */       i = Math.max(i, code.prefixLength);
/*     */     }
/*     */     
/*  94 */     int[] arrayOfInt1 = new int[i + 1];
/*  95 */     for (Code code : paramList) {
/*  96 */       arrayOfInt1[code.prefixLength] = arrayOfInt1[code.prefixLength] + 1;
/*     */     }
/*     */ 
/*     */     
/* 100 */     int[] arrayOfInt2 = new int[arrayOfInt1.length + 1];
/* 101 */     arrayOfInt1[0] = 0;
/*     */ 
/*     */     
/* 104 */     for (byte b = 1; b <= arrayOfInt1.length; b++) {
/* 105 */       arrayOfInt2[b] = arrayOfInt2[b - 1] + arrayOfInt1[b - 1] << 1;
/* 106 */       int j = arrayOfInt2[b];
/* 107 */       for (Code code : paramList) {
/* 108 */         if (code.prefixLength == b) {
/* 109 */           code.code = j;
/* 110 */           j++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/HuffmanTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */