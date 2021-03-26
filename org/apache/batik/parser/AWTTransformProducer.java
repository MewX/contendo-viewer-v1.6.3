/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ public class AWTTransformProducer
/*     */   implements TransformListHandler
/*     */ {
/*     */   protected AffineTransform affineTransform;
/*     */   
/*     */   public static AffineTransform createAffineTransform(Reader r) throws ParseException {
/*  43 */     TransformListParser p = new TransformListParser();
/*  44 */     AWTTransformProducer th = new AWTTransformProducer();
/*     */     
/*  46 */     p.setTransformListHandler(th);
/*  47 */     p.parse(r);
/*     */     
/*  49 */     return th.getAffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AffineTransform createAffineTransform(String s) throws ParseException {
/*  58 */     TransformListParser p = new TransformListParser();
/*  59 */     AWTTransformProducer th = new AWTTransformProducer();
/*     */     
/*  61 */     p.setTransformListHandler(th);
/*  62 */     p.parse(s);
/*     */     
/*  64 */     return th.getAffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getAffineTransform() {
/*  73 */     return this.affineTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startTransformList() throws ParseException {
/*  80 */     this.affineTransform = new AffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void matrix(float a, float b, float c, float d, float e, float f) throws ParseException {
/*  89 */     this.affineTransform.concatenate(new AffineTransform(a, b, c, d, e, f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(float theta) throws ParseException {
/*  96 */     this.affineTransform.concatenate(AffineTransform.getRotateInstance(Math.toRadians(theta)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(float theta, float cx, float cy) throws ParseException {
/* 104 */     AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(theta), cx, cy);
/*     */     
/* 106 */     this.affineTransform.concatenate(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(float tx) throws ParseException {
/* 113 */     AffineTransform at = AffineTransform.getTranslateInstance(tx, 0.0D);
/* 114 */     this.affineTransform.concatenate(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(float tx, float ty) throws ParseException {
/* 121 */     AffineTransform at = AffineTransform.getTranslateInstance(tx, ty);
/* 122 */     this.affineTransform.concatenate(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(float sx) throws ParseException {
/* 129 */     this.affineTransform.concatenate(AffineTransform.getScaleInstance(sx, sx));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(float sx, float sy) throws ParseException {
/* 136 */     this.affineTransform.concatenate(AffineTransform.getScaleInstance(sx, sy));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skewX(float skx) throws ParseException {
/* 143 */     this.affineTransform.concatenate(AffineTransform.getShearInstance(Math.tan(Math.toRadians(skx)), 0.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skewY(float sky) throws ParseException {
/* 151 */     this.affineTransform.concatenate(AffineTransform.getShearInstance(0.0D, Math.tan(Math.toRadians(sky))));
/*     */   }
/*     */   
/*     */   public void endTransformList() throws ParseException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AWTTransformProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */