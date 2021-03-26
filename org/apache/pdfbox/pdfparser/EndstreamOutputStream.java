/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
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
/*     */ class EndstreamOutputStream
/*     */   extends BufferedOutputStream
/*     */ {
/*     */   private boolean hasCR = false;
/*     */   private boolean hasLF = false;
/*  39 */   private int pos = 0;
/*     */   
/*     */   private boolean mustFilter = true;
/*     */   
/*     */   EndstreamOutputStream(OutputStream out) {
/*  44 */     super(out);
/*     */   }
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
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  60 */     if (this.pos == 0 && len > 10) {
/*     */ 
/*     */       
/*  63 */       this.mustFilter = false;
/*  64 */       for (int i = 0; i < 10; i++) {
/*     */ 
/*     */         
/*  67 */         if (b[i] < 9 || (b[i] > 10 && b[i] < 32 && b[i] != 13)) {
/*     */ 
/*     */           
/*  70 */           this.mustFilter = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     if (this.mustFilter) {
/*     */ 
/*     */       
/*  78 */       if (this.hasCR) {
/*     */ 
/*     */         
/*  81 */         this.hasCR = false;
/*  82 */         if (!this.hasLF && len == 1 && b[off] == 10) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         write(13);
/*     */       } 
/*  91 */       if (this.hasLF) {
/*     */         
/*  93 */         write(10);
/*  94 */         this.hasLF = false;
/*     */       } 
/*     */       
/*  97 */       if (len > 0)
/*     */       {
/*  99 */         if (b[off + len - 1] == 13) {
/*     */           
/* 101 */           this.hasCR = true;
/* 102 */           len--;
/*     */         }
/* 104 */         else if (b[off + len - 1] == 10) {
/*     */           
/* 106 */           this.hasLF = true;
/* 107 */           len--;
/* 108 */           if (len > 0 && b[off + len - 1] == 13) {
/*     */             
/* 110 */             this.hasCR = true;
/* 111 */             len--;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 116 */     super.write(b, off, len);
/* 117 */     this.pos += len;
/*     */   }
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
/*     */   public void flush() throws IOException {
/* 130 */     if (this.hasCR && !this.hasLF) {
/*     */       
/* 132 */       write(13);
/* 133 */       this.pos++;
/*     */     } 
/* 135 */     this.hasCR = false;
/* 136 */     this.hasLF = false;
/* 137 */     super.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/EndstreamOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */