/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGRect;
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
/*     */ public class SVGOMRect
/*     */   implements SVGRect
/*     */ {
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float w;
/*     */   protected float h;
/*     */   
/*     */   public SVGOMRect() {}
/*     */   
/*     */   public SVGOMRect(float x, float y, float w, float h) {
/*  63 */     this.x = x;
/*  64 */     this.y = y;
/*  65 */     this.w = w;
/*  66 */     this.h = h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/*  73 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(float x) throws DOMException {
/*  80 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/*  87 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(float y) throws DOMException {
/*  94 */     this.y = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 101 */     return this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(float width) throws DOMException {
/* 108 */     this.w = width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 115 */     return this.h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(float height) throws DOMException {
/* 122 */     this.h = height;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMRect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */