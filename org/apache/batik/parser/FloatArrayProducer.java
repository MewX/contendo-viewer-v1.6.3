/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.util.LinkedList;
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
/*     */ public class FloatArrayProducer
/*     */   extends DefaultNumberListHandler
/*     */   implements PointsHandler
/*     */ {
/*     */   protected LinkedList as;
/*     */   protected float[] a;
/*     */   protected int index;
/*     */   protected int count;
/*     */   
/*     */   public float[] getFloatArray() {
/*  58 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startNumberList() throws ParseException {
/*  69 */     this.as = new LinkedList();
/*  70 */     this.a = new float[11];
/*  71 */     this.count = 0;
/*  72 */     this.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void numberValue(float v) throws ParseException {
/*  81 */     if (this.index == this.a.length) {
/*  82 */       this.as.add(this.a);
/*  83 */       this.a = new float[this.a.length * 2 + 1];
/*  84 */       this.index = 0;
/*     */     } 
/*  86 */     this.a[this.index++] = v;
/*  87 */     this.count++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endNumberList() throws ParseException {
/*  96 */     float[] all = new float[this.count];
/*  97 */     int pos = 0;
/*  98 */     for (Object a1 : this.as) {
/*  99 */       float[] b = (float[])a1;
/* 100 */       System.arraycopy(b, 0, all, pos, b.length);
/* 101 */       pos += b.length;
/*     */     } 
/* 103 */     System.arraycopy(this.a, 0, all, pos, this.index);
/* 104 */     this.as.clear();
/* 105 */     this.a = all;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPoints() throws ParseException {
/* 114 */     startNumberList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void point(float x, float y) throws ParseException {
/* 121 */     numberValue(x);
/* 122 */     numberValue(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPoints() throws ParseException {
/* 129 */     endNumberList();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/FloatArrayProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */