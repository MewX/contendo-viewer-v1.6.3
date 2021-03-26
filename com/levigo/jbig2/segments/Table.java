/*    */ package com.levigo.jbig2.segments;
/*    */ 
/*    */ import com.levigo.jbig2.SegmentData;
/*    */ import com.levigo.jbig2.SegmentHeader;
/*    */ import com.levigo.jbig2.err.IntegerMaxValueException;
/*    */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*    */ import com.levigo.jbig2.io.SubInputStream;
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Table
/*    */   implements SegmentData
/*    */ {
/*    */   private SubInputStream subInputStream;
/*    */   private int htOutOfBand;
/*    */   private int htPS;
/*    */   private int htRS;
/*    */   private int htLow;
/*    */   private int htHigh;
/*    */   
/*    */   private void parseHeader() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/*    */     int i;
/* 50 */     if ((i = this.subInputStream.readBit()) == 1) {
/* 51 */       throw new InvalidHeaderValueException("B.2.1 Code table flags: Bit 7 must be zero, but was " + i);
/*    */     }
/*    */ 
/*    */     
/* 55 */     this.htRS = (int)(this.subInputStream.readBits(3) + 1L & 0xFL);
/*    */ 
/*    */     
/* 58 */     this.htPS = (int)(this.subInputStream.readBits(3) + 1L & 0xFL);
/*    */ 
/*    */     
/* 61 */     this.htOutOfBand = this.subInputStream.readBit();
/*    */     
/* 63 */     this.htLow = (int)this.subInputStream.readBits(32);
/* 64 */     this.htHigh = (int)this.subInputStream.readBits(32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IOException, IntegerMaxValueException {
/* 69 */     this.subInputStream = paramSubInputStream;
/*    */     
/* 71 */     parseHeader();
/*    */   }
/*    */   
/*    */   public int getHtOOB() {
/* 75 */     return this.htOutOfBand;
/*    */   }
/*    */   
/*    */   public int getHtPS() {
/* 79 */     return this.htPS;
/*    */   }
/*    */   
/*    */   public int getHtRS() {
/* 83 */     return this.htRS;
/*    */   }
/*    */   
/*    */   public int getHtLow() {
/* 87 */     return this.htLow;
/*    */   }
/*    */   
/*    */   public int getHtHigh() {
/* 91 */     return this.htHigh;
/*    */   }
/*    */   
/*    */   public SubInputStream getSubInputStream() {
/* 95 */     return this.subInputStream;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/Table.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */