/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunLengthEncodeOutputStream
/*     */   extends FilterOutputStream
/*     */   implements Finalizable
/*     */ {
/*     */   private static final int MAX_SEQUENCE_COUNT = 127;
/*     */   private static final int END_OF_DATA = 128;
/*     */   private static final int BYTE_MAX = 256;
/*     */   private static final int NOT_IDENTIFY_SEQUENCE = 0;
/*     */   private static final int START_SEQUENCE = 1;
/*     */   private static final int IN_SEQUENCE = 2;
/*     */   private static final int NOT_IN_SEQUENCE = 3;
/*     */   private int runCount;
/*  47 */   private int isSequence = 0;
/*  48 */   private byte[] runBuffer = new byte[128];
/*     */ 
/*     */ 
/*     */   
/*     */   public RunLengthEncodeOutputStream(OutputStream out) {
/*  53 */     super(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte b) throws IOException {
/*  60 */     this.runBuffer[this.runCount] = b;
/*     */     
/*  62 */     switch (this.runCount) {
/*     */       case 0:
/*  64 */         this.runCount = 0;
/*  65 */         this.isSequence = 0;
/*  66 */         this.runCount++;
/*     */         return;
/*     */       case 1:
/*  69 */         if (this.runBuffer[this.runCount] != this.runBuffer[this.runCount - 1]) {
/*  70 */           this.isSequence = 3;
/*     */         }
/*  72 */         this.runCount++;
/*     */         return;
/*     */       case 2:
/*  75 */         if (this.runBuffer[this.runCount] != this.runBuffer[this.runCount - 1]) {
/*  76 */           this.isSequence = 3;
/*     */         }
/*  78 */         else if (this.isSequence == 3) {
/*  79 */           this.isSequence = 1;
/*     */         } else {
/*  81 */           this.isSequence = 2;
/*     */         } 
/*     */         
/*  84 */         this.runCount++;
/*     */         return;
/*     */       case 127:
/*  87 */         if (this.isSequence == 2) {
/*  88 */           this.out.write(130);
/*  89 */           this.out.write(this.runBuffer[this.runCount - 1]);
/*  90 */           this.runBuffer[0] = this.runBuffer[this.runCount];
/*  91 */           this.runCount = 1;
/*     */         } else {
/*  93 */           this.out.write(127);
/*  94 */           this.out.write(this.runBuffer, 0, this.runCount + 1);
/*  95 */           this.runCount = 0;
/*     */         } 
/*  97 */         this.isSequence = 0;
/*     */         return;
/*     */     } 
/* 100 */     switch (this.isSequence) {
/*     */       case 3:
/* 102 */         if (this.runBuffer[this.runCount] == this.runBuffer[this.runCount - 1]) {
/* 103 */           this.isSequence = 1;
/*     */         }
/* 105 */         this.runCount++;
/*     */         return;
/*     */       case 1:
/* 108 */         if (this.runBuffer[this.runCount] == this.runBuffer[this.runCount - 1]) {
/* 109 */           this.out.write(this.runCount - 3);
/* 110 */           this.out.write(this.runBuffer, 0, this.runCount - 2);
/* 111 */           this.runBuffer[0] = this.runBuffer[this.runCount];
/* 112 */           this.runBuffer[1] = this.runBuffer[this.runCount];
/* 113 */           this.runBuffer[2] = this.runBuffer[this.runCount];
/* 114 */           this.runCount = 3;
/* 115 */           this.isSequence = 2;
/*     */         } else {
/*     */           
/* 118 */           this.isSequence = 3;
/* 119 */           this.runCount++;
/*     */         } 
/*     */         return;
/*     */     } 
/*     */     
/* 124 */     if (this.runBuffer[this.runCount] != this.runBuffer[this.runCount - 1]) {
/* 125 */       this.out.write(256 - this.runCount - 1);
/* 126 */       this.out.write(this.runBuffer[this.runCount - 1]);
/* 127 */       this.runBuffer[0] = this.runBuffer[this.runCount];
/* 128 */       this.runCount = 1;
/* 129 */       this.isSequence = 0;
/*     */     } else {
/*     */       
/* 132 */       this.runCount++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 143 */     for (int i = 0; i < b.length; i++) {
/* 144 */       write(b[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 153 */     for (int i = 0; i < len; i++) {
/* 154 */       write(b[off + i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalizeStream() throws IOException {
/* 162 */     switch (this.isSequence) {
/*     */       case 2:
/* 164 */         this.out.write(256 - this.runCount - 1);
/* 165 */         this.out.write(this.runBuffer[this.runCount - 1]);
/*     */         break;
/*     */       default:
/* 168 */         this.out.write(this.runCount - 1);
/* 169 */         this.out.write(this.runBuffer, 0, this.runCount);
/*     */         break;
/*     */     } 
/* 172 */     this.out.write(128);
/*     */     
/* 174 */     flush();
/* 175 */     if (this.out instanceof Finalizable) {
/* 176 */       ((Finalizable)this.out).finalizeStream();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 184 */     finalizeStream();
/* 185 */     super.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/RunLengthEncodeOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */