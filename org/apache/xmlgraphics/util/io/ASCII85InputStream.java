/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ASCII85InputStream
/*     */   extends InputStream
/*     */   implements ASCII85Constants
/*     */ {
/*     */   private InputStream in;
/*     */   private boolean eodReached;
/*  42 */   private int[] b = new int[4];
/*     */   
/*     */   private int bSize;
/*     */   
/*     */   private int bIndex;
/*     */   
/*     */   public ASCII85InputStream(InputStream in) {
/*  49 */     this.in = in;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  55 */     if (this.bIndex >= this.bSize) {
/*  56 */       if (this.eodReached) {
/*  57 */         return -1;
/*     */       }
/*  59 */       readNextTuple();
/*  60 */       if (this.bSize == 0) {
/*  61 */         if (!this.eodReached) {
/*  62 */           throw new IllegalStateException("Internal error");
/*     */         }
/*  64 */         return -1;
/*     */       } 
/*     */     } 
/*  67 */     int result = this.b[this.bIndex];
/*  68 */     result = (result < 0) ? (256 + result) : result;
/*  69 */     this.bIndex++;
/*  70 */     return result;
/*     */   }
/*     */   
/*     */   private int filteredRead() throws IOException {
/*     */     int buf;
/*     */     while (true) {
/*  76 */       buf = this.in.read();
/*  77 */       switch (buf) {
/*     */         case 0:
/*     */         case 9:
/*     */         case 10:
/*     */         case 12:
/*     */         case 13:
/*     */         case 32:
/*     */           continue;
/*     */         case 122:
/*     */         case 126:
/*  87 */           return buf;
/*     */       }  break;
/*  89 */     }  if (buf >= 33 && buf <= 117) {
/*  90 */       return buf;
/*     */     }
/*  92 */     throw new IOException("Illegal character detected: " + buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleEOD() throws IOException {
/*  99 */     int buf = this.in.read();
/* 100 */     if (buf != EOD[1]) {
/* 101 */       throw new IOException("'>' expected after '~' (EOD)");
/*     */     }
/* 103 */     this.eodReached = true;
/* 104 */     this.bSize = 0;
/* 105 */     this.bIndex = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private void readNextTuple() throws IOException {
/* 110 */     long tuple = 0L;
/*     */     
/* 112 */     int buf = filteredRead();
/* 113 */     if (buf == 122) {
/* 114 */       Arrays.fill(this.b, 0);
/* 115 */       this.bSize = 4;
/* 116 */       this.bIndex = 0;
/* 117 */     } else if (buf == EOD[0]) {
/* 118 */       handleEOD();
/*     */     } else {
/* 120 */       int cIndex = 0;
/* 121 */       tuple = (buf - 33) * POW85[cIndex];
/* 122 */       cIndex++;
/* 123 */       while (cIndex < 5) {
/* 124 */         buf = filteredRead();
/* 125 */         if (buf == EOD[0]) {
/* 126 */           handleEOD(); break;
/*     */         } 
/* 128 */         if (buf == 122)
/*     */         {
/* 130 */           throw new IOException("Illegal 'z' within tuple");
/*     */         }
/* 132 */         tuple += (buf - 33) * POW85[cIndex];
/* 133 */         cIndex++;
/*     */       } 
/*     */       
/* 136 */       int cSize = cIndex;
/* 137 */       if (cSize == 1)
/*     */       {
/* 139 */         throw new IOException("Only one character in tuple");
/*     */       }
/*     */       
/* 142 */       while (cIndex < 5) {
/* 143 */         tuple += POW85[cIndex - 1];
/* 144 */         cIndex++;
/*     */       } 
/* 146 */       if (tuple > 4294967295L)
/*     */       {
/* 148 */         throw new IOException("Illegal tuple (> 2^32 - 1)");
/*     */       }
/*     */       
/* 151 */       this.b[0] = (byte)(int)(tuple >> 24L & 0xFFL);
/* 152 */       this.b[1] = (byte)(int)(tuple >> 16L & 0xFFL);
/* 153 */       this.b[2] = (byte)(int)(tuple >> 8L & 0xFFL);
/* 154 */       this.b[3] = (byte)(int)(tuple & 0xFFL);
/* 155 */       this.bSize = cSize - 1;
/* 156 */       this.bIndex = 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/ASCII85InputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */