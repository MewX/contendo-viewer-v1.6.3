/*    */ package org.apache.batik.parser;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ public class AWTPolygonProducer
/*    */   extends AWTPolylineProducer
/*    */ {
/*    */   public static Shape createShape(Reader r, int wr) throws IOException, ParseException {
/* 40 */     PointsParser p = new PointsParser();
/* 41 */     AWTPolygonProducer ph = new AWTPolygonProducer();
/*    */     
/* 43 */     ph.setWindingRule(wr);
/* 44 */     p.setPointsHandler(ph);
/* 45 */     p.parse(r);
/*    */     
/* 47 */     return ph.getShape();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endPoints() throws ParseException {
/* 54 */     this.path.closePath();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AWTPolygonProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */