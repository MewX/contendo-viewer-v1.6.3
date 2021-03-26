/*    */ package org.apache.batik.parser;
/*    */ 
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
/*    */ public class PointsParser
/*    */   extends NumberParser
/*    */ {
/* 46 */   protected PointsHandler pointsHandler = DefaultPointsHandler.INSTANCE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean eRead;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPointsHandler(PointsHandler handler) {
/* 61 */     this.pointsHandler = handler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PointsHandler getPointsHandler() {
/* 68 */     return this.pointsHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doParse() throws ParseException, IOException {
/* 75 */     this.pointsHandler.startPoints();
/*    */     
/* 77 */     this.current = this.reader.read();
/* 78 */     skipSpaces();
/*    */ 
/*    */     
/* 81 */     while (this.current != -1) {
/*    */ 
/*    */       
/* 84 */       float x = parseFloat();
/* 85 */       skipCommaSpaces();
/* 86 */       float y = parseFloat();
/*    */       
/* 88 */       this.pointsHandler.point(x, y);
/* 89 */       skipCommaSpaces();
/*    */     } 
/*    */     
/* 92 */     this.pointsHandler.endPoints();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PointsParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */