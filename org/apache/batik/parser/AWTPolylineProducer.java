/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
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
/*     */ public class AWTPolylineProducer
/*     */   implements PointsHandler, ShapeProducer
/*     */ {
/*     */   protected GeneralPath path;
/*     */   protected boolean newPath;
/*     */   protected int windingRule;
/*     */   
/*     */   public static Shape createShape(Reader r, int wr) throws IOException, ParseException {
/*  56 */     PointsParser p = new PointsParser();
/*  57 */     AWTPolylineProducer ph = new AWTPolylineProducer();
/*     */     
/*  59 */     ph.setWindingRule(wr);
/*  60 */     p.setPointsHandler(ph);
/*  61 */     p.parse(r);
/*     */     
/*  63 */     return ph.getShape();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWindingRule(int i) {
/*  70 */     this.windingRule = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWindingRule() {
/*  77 */     return this.windingRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/*  86 */     return this.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPoints() throws ParseException {
/*  93 */     this.path = new GeneralPath(this.windingRule);
/*  94 */     this.newPath = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void point(float x, float y) throws ParseException {
/* 101 */     if (this.newPath) {
/* 102 */       this.newPath = false;
/* 103 */       this.path.moveTo(x, y);
/*     */     } else {
/* 105 */       this.path.lineTo(x, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endPoints() throws ParseException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AWTPolylineProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */