/*     */ package com.github.jaiimageio.impl.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.imageio.stream.ImageOutputStream;
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
/*     */ public class LZWCompressor
/*     */ {
/*     */   int codeSize_;
/*     */   int clearCode_;
/*     */   int endOfInfo_;
/*     */   int numBits_;
/*     */   int limit_;
/*     */   short prefix_;
/*     */   BitFile bf_;
/*     */   LZWStringTable lzss_;
/*     */   boolean tiffFudge_;
/*     */   
/*     */   public LZWCompressor(ImageOutputStream out, int codeSize, boolean TIFF) throws IOException {
/*  93 */     this.bf_ = new BitFile(out, !TIFF);
/*  94 */     this.codeSize_ = codeSize;
/*  95 */     this.tiffFudge_ = TIFF;
/*  96 */     this.clearCode_ = 1 << this.codeSize_;
/*  97 */     this.endOfInfo_ = this.clearCode_ + 1;
/*  98 */     this.numBits_ = this.codeSize_ + 1;
/*     */     
/* 100 */     this.limit_ = (1 << this.numBits_) - 1;
/* 101 */     if (this.tiffFudge_) {
/* 102 */       this.limit_--;
/*     */     }
/* 104 */     this.prefix_ = -1;
/* 105 */     this.lzss_ = new LZWStringTable();
/* 106 */     this.lzss_.ClearTable(this.codeSize_);
/* 107 */     this.bf_.writeBits(this.clearCode_, this.numBits_);
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
/*     */   public void compress(byte[] buf, int offset, int length) throws IOException {
/* 121 */     int maxOffset = offset + length;
/* 122 */     for (int idx = offset; idx < maxOffset; idx++) {
/*     */       
/* 124 */       byte c = buf[idx]; short index;
/* 125 */       if ((index = this.lzss_.FindCharString(this.prefix_, c)) != -1) {
/* 126 */         this.prefix_ = index;
/*     */       } else {
/*     */         
/* 129 */         this.bf_.writeBits(this.prefix_, this.numBits_);
/* 130 */         if (this.lzss_.AddCharString(this.prefix_, c) > this.limit_) {
/*     */           
/* 132 */           if (this.numBits_ == 12) {
/*     */             
/* 134 */             this.bf_.writeBits(this.clearCode_, this.numBits_);
/* 135 */             this.lzss_.ClearTable(this.codeSize_);
/* 136 */             this.numBits_ = this.codeSize_ + 1;
/*     */           } else {
/*     */             
/* 139 */             this.numBits_++;
/*     */           } 
/* 141 */           this.limit_ = (1 << this.numBits_) - 1;
/* 142 */           if (this.tiffFudge_)
/* 143 */             this.limit_--; 
/*     */         } 
/* 145 */         this.prefix_ = (short)((short)c & 0xFF);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 158 */     if (this.prefix_ != -1) {
/* 159 */       this.bf_.writeBits(this.prefix_, this.numBits_);
/*     */     }
/* 161 */     this.bf_.writeBits(this.endOfInfo_, this.numBits_);
/* 162 */     this.bf_.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public void dump(PrintStream out) {
/* 167 */     this.lzss_.dump(out);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/common/LZWCompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */