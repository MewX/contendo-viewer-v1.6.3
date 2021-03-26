/*     */ package com.github.jaiimageio.impl.common;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class BitFile
/*     */ {
/*     */   ImageOutputStream output_;
/*     */   byte[] buffer_;
/*     */   int index_;
/*     */   int bitsLeft_;
/*     */   boolean blocks_ = false;
/*     */   
/*     */   public BitFile(ImageOutputStream output, boolean blocks) {
/*  72 */     this.output_ = output;
/*  73 */     this.blocks_ = blocks;
/*  74 */     this.buffer_ = new byte[256];
/*  75 */     this.index_ = 0;
/*  76 */     this.bitsLeft_ = 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  81 */     int numBytes = this.index_ + ((this.bitsLeft_ == 8) ? 0 : 1);
/*  82 */     if (numBytes > 0) {
/*     */       
/*  84 */       if (this.blocks_)
/*  85 */         this.output_.write(numBytes); 
/*  86 */       this.output_.write(this.buffer_, 0, numBytes);
/*  87 */       this.buffer_[0] = 0;
/*  88 */       this.index_ = 0;
/*  89 */       this.bitsLeft_ = 8;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeBits(int bits, int numbits) throws IOException {
/*  95 */     int bitsWritten = 0;
/*  96 */     int numBytes = 255;
/*     */ 
/*     */     
/*     */     do {
/* 100 */       if ((this.index_ == 254 && this.bitsLeft_ == 0) || this.index_ > 254) {
/*     */         
/* 102 */         if (this.blocks_) {
/* 103 */           this.output_.write(numBytes);
/*     */         }
/* 105 */         this.output_.write(this.buffer_, 0, numBytes);
/*     */         
/* 107 */         this.buffer_[0] = 0;
/* 108 */         this.index_ = 0;
/* 109 */         this.bitsLeft_ = 8;
/*     */       } 
/*     */       
/* 112 */       if (numbits <= this.bitsLeft_)
/*     */       {
/* 114 */         if (this.blocks_)
/*     */         {
/* 116 */           this.buffer_[this.index_] = (byte)(this.buffer_[this.index_] | (bits & (1 << numbits) - 1) << 8 - this.bitsLeft_);
/* 117 */           bitsWritten += numbits;
/* 118 */           this.bitsLeft_ -= numbits;
/* 119 */           numbits = 0;
/*     */         }
/*     */         else
/*     */         {
/* 123 */           this.buffer_[this.index_] = (byte)(this.buffer_[this.index_] | (bits & (1 << numbits) - 1) << this.bitsLeft_ - numbits);
/* 124 */           bitsWritten += numbits;
/* 125 */           this.bitsLeft_ -= numbits;
/* 126 */           numbits = 0;
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 132 */       else if (this.blocks_)
/*     */       {
/*     */ 
/*     */         
/* 136 */         this.buffer_[this.index_] = (byte)(this.buffer_[this.index_] | (bits & (1 << this.bitsLeft_) - 1) << 8 - this.bitsLeft_);
/* 137 */         bitsWritten += this.bitsLeft_;
/* 138 */         bits >>= this.bitsLeft_;
/* 139 */         numbits -= this.bitsLeft_;
/* 140 */         this.buffer_[++this.index_] = 0;
/* 141 */         this.bitsLeft_ = 8;
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */         
/* 148 */         int topbits = bits >>> numbits - this.bitsLeft_ & (1 << this.bitsLeft_) - 1;
/* 149 */         this.buffer_[this.index_] = (byte)(this.buffer_[this.index_] | topbits);
/* 150 */         numbits -= this.bitsLeft_;
/* 151 */         bitsWritten += this.bitsLeft_;
/* 152 */         this.buffer_[++this.index_] = 0;
/* 153 */         this.bitsLeft_ = 8;
/*     */       }
/*     */     
/*     */     }
/* 157 */     while (numbits != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/common/BitFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */