/*     */ package org.apache.batik.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UTF8Decoder
/*     */   extends AbstractCharDecoder
/*     */ {
/*  38 */   protected static final byte[] UTF8_BYTES = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected int nextChar = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UTF8Decoder(InputStream is) {
/*  58 */     super(is);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readChar() throws IOException {
/*  66 */     if (this.nextChar != -1) {
/*  67 */       int result = this.nextChar;
/*  68 */       this.nextChar = -1;
/*  69 */       return result;
/*     */     } 
/*  71 */     if (this.position == this.count) {
/*  72 */       fillBuffer();
/*     */     }
/*  74 */     if (this.count == -1) {
/*  75 */       return -1;
/*     */     }
/*  77 */     int b1 = this.buffer[this.position++] & 0xFF;
/*  78 */     switch (UTF8_BYTES[b1]) {
/*     */       default:
/*  80 */         charError("UTF-8");
/*     */       
/*     */       case 1:
/*  83 */         return b1;
/*     */       
/*     */       case 2:
/*  86 */         if (this.position == this.count) {
/*  87 */           fillBuffer();
/*     */         }
/*  89 */         if (this.count == -1) {
/*  90 */           endOfStreamError("UTF-8");
/*     */         }
/*  92 */         return (b1 & 0x1F) << 6 | this.buffer[this.position++] & 0x3F;
/*     */       
/*     */       case 3:
/*  95 */         if (this.position == this.count) {
/*  96 */           fillBuffer();
/*     */         }
/*  98 */         if (this.count == -1) {
/*  99 */           endOfStreamError("UTF-8");
/*     */         }
/* 101 */         b2 = this.buffer[this.position++];
/* 102 */         if (this.position == this.count) {
/* 103 */           fillBuffer();
/*     */         }
/* 105 */         if (this.count == -1) {
/* 106 */           endOfStreamError("UTF-8");
/*     */         }
/* 108 */         b3 = this.buffer[this.position++];
/* 109 */         if ((b2 & 0xC0) != 128 || (b3 & 0xC0) != 128) {
/* 110 */           charError("UTF-8");
/*     */         }
/* 112 */         return (b1 & 0x1F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x1F;
/*     */       case 4:
/*     */         break;
/* 115 */     }  if (this.position == this.count) {
/* 116 */       fillBuffer();
/*     */     }
/* 118 */     if (this.count == -1) {
/* 119 */       endOfStreamError("UTF-8");
/*     */     }
/* 121 */     int b2 = this.buffer[this.position++];
/* 122 */     if (this.position == this.count) {
/* 123 */       fillBuffer();
/*     */     }
/* 125 */     if (this.count == -1) {
/* 126 */       endOfStreamError("UTF-8");
/*     */     }
/* 128 */     int b3 = this.buffer[this.position++];
/* 129 */     if (this.position == this.count) {
/* 130 */       fillBuffer();
/*     */     }
/* 132 */     if (this.count == -1) {
/* 133 */       endOfStreamError("UTF-8");
/*     */     }
/* 135 */     int b4 = this.buffer[this.position++];
/* 136 */     if ((b2 & 0xC0) != 128 || (b3 & 0xC0) != 128 || (b4 & 0xC0) != 128)
/*     */     {
/*     */       
/* 139 */       charError("UTF-8");
/*     */     }
/* 141 */     int c = (b1 & 0x1F) << 18 | (b2 & 0x3F) << 12 | (b3 & 0x1F) << 6 | b4 & 0x1F;
/*     */ 
/*     */ 
/*     */     
/* 145 */     this.nextChar = (c - 65536) % 1024 + 56320;
/* 146 */     return (c - 65536) / 1024 + 55296;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/UTF8Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */