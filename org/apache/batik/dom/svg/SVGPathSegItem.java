/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.w3c.dom.svg.SVGPathSeg;
/*     */ import org.w3c.dom.svg.SVGPathSegClosePath;
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
/*     */ public class SVGPathSegItem
/*     */   extends AbstractSVGItem
/*     */   implements SVGPathSeg, SVGPathSegClosePath
/*     */ {
/*     */   protected short type;
/*     */   protected String letter;
/*     */   private float x;
/*     */   private float y;
/*     */   private float x1;
/*     */   private float y1;
/*     */   private float x2;
/*     */   private float y2;
/*     */   private float r1;
/*     */   private float r2;
/*     */   private float angle;
/*     */   private boolean largeArcFlag;
/*     */   private boolean sweepFlag;
/*     */   
/*     */   protected SVGPathSegItem() {}
/*     */   
/*     */   public SVGPathSegItem(short type, String letter) {
/*  48 */     this.type = type;
/*  49 */     this.letter = letter;
/*     */   }
/*     */   
/*     */   public SVGPathSegItem(SVGPathSeg pathSeg) {
/*  53 */     this.type = pathSeg.getPathSegType();
/*  54 */     switch (this.type) {
/*     */       case 1:
/*  56 */         this.letter = "z";
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getStringValue() {
/*  62 */     return this.letter;
/*     */   }
/*     */   
/*     */   public short getPathSegType() {
/*  66 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPathSegTypeAsLetter() {
/*  71 */     return this.letter;
/*     */   }
/*     */   
/*     */   public float getR1() {
/*  75 */     return this.r1;
/*     */   }
/*     */   
/*     */   public void setR1(float r1) {
/*  79 */     this.r1 = r1;
/*     */   }
/*     */   
/*     */   public float getR2() {
/*  83 */     return this.r2;
/*     */   }
/*     */   
/*     */   public void setR2(float r2) {
/*  87 */     this.r2 = r2;
/*     */   }
/*     */   
/*     */   public float getAngle() {
/*  91 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setAngle(float angle) {
/*  95 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public boolean isLargeArcFlag() {
/*  99 */     return this.largeArcFlag;
/*     */   }
/*     */   
/*     */   public void setLargeArcFlag(boolean largeArcFlag) {
/* 103 */     this.largeArcFlag = largeArcFlag;
/*     */   }
/*     */   
/*     */   public boolean isSweepFlag() {
/* 107 */     return this.sweepFlag;
/*     */   }
/*     */   
/*     */   public void setSweepFlag(boolean sweepFlag) {
/* 111 */     this.sweepFlag = sweepFlag;
/*     */   }
/*     */   
/*     */   public float getX() {
/* 115 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/* 119 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 123 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/* 127 */     this.y = y;
/*     */   }
/*     */   
/*     */   public float getX1() {
/* 131 */     return this.x1;
/*     */   }
/*     */   
/*     */   public void setX1(float x1) {
/* 135 */     this.x1 = x1;
/*     */   }
/*     */   
/*     */   public float getY1() {
/* 139 */     return this.y1;
/*     */   }
/*     */   
/*     */   public void setY1(float y1) {
/* 143 */     this.y1 = y1;
/*     */   }
/*     */   
/*     */   public float getX2() {
/* 147 */     return this.x2;
/*     */   }
/*     */   
/*     */   public void setX2(float x2) {
/* 151 */     this.x2 = x2;
/*     */   }
/*     */   
/*     */   public float getY2() {
/* 155 */     return this.y2;
/*     */   }
/*     */   
/*     */   public void setY2(float y2) {
/* 159 */     this.y2 = y2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGPathSegItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */