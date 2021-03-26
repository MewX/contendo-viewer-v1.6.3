/*     */ package com.levigo.jbig2.decoder.huffman;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ class InternalNode
/*     */   extends Node
/*     */ {
/*     */   private final int depth;
/*     */   private Node zero;
/*     */   private Node one;
/*     */   
/*     */   protected InternalNode() {
/*  37 */     this.depth = 0;
/*     */   }
/*     */   
/*     */   protected InternalNode(int paramInt) {
/*  41 */     this.depth = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void append(HuffmanTable.Code paramCode) {
/*  49 */     if (paramCode.prefixLength == 0) {
/*     */       return;
/*     */     }
/*  52 */     int i = paramCode.prefixLength - 1 - this.depth;
/*     */     
/*  54 */     if (i < 0) {
/*  55 */       throw new IllegalArgumentException("Negative shifting is not possible.");
/*     */     }
/*  57 */     int j = paramCode.code >> i & 0x1;
/*  58 */     if (i == 0) {
/*  59 */       if (paramCode.rangeLength == -1) {
/*     */         
/*  61 */         if (j == 1) {
/*  62 */           if (this.one != null)
/*  63 */             throw new IllegalStateException("already have a OOB for " + paramCode); 
/*  64 */           this.one = new OutOfBandNode(paramCode);
/*     */         } else {
/*  66 */           if (this.zero != null)
/*  67 */             throw new IllegalStateException("already have a OOB for " + paramCode); 
/*  68 */           this.zero = new OutOfBandNode(paramCode);
/*     */         }
/*     */       
/*     */       }
/*  72 */       else if (j == 1) {
/*  73 */         if (this.one != null)
/*  74 */           throw new IllegalStateException("already have a ValueNode for " + paramCode); 
/*  75 */         this.one = new ValueNode(paramCode);
/*     */       } else {
/*  77 */         if (this.zero != null)
/*  78 */           throw new IllegalStateException("already have a ValueNode for " + paramCode); 
/*  79 */         this.zero = new ValueNode(paramCode);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  84 */     else if (j == 1) {
/*  85 */       if (this.one == null)
/*  86 */         this.one = new InternalNode(this.depth + 1); 
/*  87 */       ((InternalNode)this.one).append(paramCode);
/*     */     } else {
/*  89 */       if (this.zero == null)
/*  90 */         this.zero = new InternalNode(this.depth + 1); 
/*  91 */       ((InternalNode)this.zero).append(paramCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected long decode(ImageInputStream paramImageInputStream) throws IOException {
/*  98 */     int i = paramImageInputStream.readBit();
/*  99 */     Node node = (i == 0) ? this.zero : this.one;
/* 100 */     return node.decode(paramImageInputStream);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 105 */     StringBuilder stringBuilder = new StringBuilder("\n");
/*     */     
/* 107 */     pad(stringBuilder);
/* 108 */     stringBuilder.append("0: ").append(this.zero).append("\n");
/* 109 */     pad(stringBuilder);
/* 110 */     stringBuilder.append("1: ").append(this.one).append("\n");
/*     */     
/* 112 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private void pad(StringBuilder paramStringBuilder) {
/* 116 */     for (byte b = 0; b < this.depth; b++)
/* 117 */       paramStringBuilder.append("   "); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/InternalNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */