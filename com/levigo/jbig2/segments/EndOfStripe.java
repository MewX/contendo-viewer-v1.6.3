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
/*    */ public class EndOfStripe
/*    */   implements SegmentData
/*    */ {
/*    */   private SubInputStream subInputStream;
/*    */   private int lineNumber;
/*    */   
/*    */   private void parseHeader() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 37 */     this.lineNumber = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws IntegerMaxValueException, InvalidHeaderValueException, IOException {
/* 42 */     this.subInputStream = paramSubInputStream;
/* 43 */     parseHeader();
/*    */   }
/*    */   
/*    */   public int getLineNumber() {
/* 47 */     return this.lineNumber;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/EndOfStripe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */