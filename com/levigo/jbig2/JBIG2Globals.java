/*    */ package com.levigo.jbig2;
/*    */ 
/*    */ import com.levigo.jbig2.util.log.Logger;
/*    */ import com.levigo.jbig2.util.log.LoggerFactory;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class JBIG2Globals
/*    */ {
/* 37 */   private static final Logger log = LoggerFactory.getLogger(JBIG2Globals.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   private Map<Integer, SegmentHeader> globalSegments = new HashMap<>();
/*    */   
/*    */   protected SegmentHeader getSegment(int paramInt) {
/* 46 */     if (this.globalSegments.size() == 0 && 
/* 47 */       log.isErrorEnabled()) {
/* 48 */       log.error("No global segment added so far. Use JBIG2ImageReader.setGlobals().");
/*    */     }
/*    */ 
/*    */     
/* 52 */     return this.globalSegments.get(Integer.valueOf(paramInt));
/*    */   }
/*    */   
/*    */   protected void addSegment(Integer paramInteger, SegmentHeader paramSegmentHeader) {
/* 56 */     this.globalSegments.put(paramInteger, paramSegmentHeader);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2Globals.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */