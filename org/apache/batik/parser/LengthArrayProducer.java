/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.util.Iterator;
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
/*     */ public class LengthArrayProducer
/*     */   extends DefaultLengthListHandler
/*     */ {
/*     */   protected LinkedList vs;
/*     */   protected float[] v;
/*     */   protected LinkedList us;
/*     */   protected short[] u;
/*     */   protected int index;
/*     */   protected int count;
/*     */   protected short currentUnit;
/*     */   
/*     */   public short[] getLengthTypeArray() {
/*  74 */     return this.u;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getLengthValueArray() {
/*  81 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startLengthList() throws ParseException {
/*  92 */     this.us = new LinkedList();
/*  93 */     this.u = new short[11];
/*  94 */     this.vs = new LinkedList();
/*  95 */     this.v = new float[11];
/*  96 */     this.count = 0;
/*  97 */     this.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void numberValue(float v) throws ParseException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lengthValue(float val) throws ParseException {
/* 112 */     if (this.index == this.v.length) {
/* 113 */       this.vs.add(this.v);
/* 114 */       this.v = new float[this.v.length * 2 + 1];
/* 115 */       this.us.add(this.u);
/* 116 */       this.u = new short[this.u.length * 2 + 1];
/* 117 */       this.index = 0;
/*     */     } 
/* 119 */     this.v[this.index] = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startLength() throws ParseException {
/* 126 */     this.currentUnit = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endLength() throws ParseException {
/* 133 */     this.u[this.index++] = this.currentUnit;
/* 134 */     this.count++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void em() throws ParseException {
/* 141 */     this.currentUnit = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ex() throws ParseException {
/* 148 */     this.currentUnit = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void in() throws ParseException {
/* 155 */     this.currentUnit = 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cm() throws ParseException {
/* 162 */     this.currentUnit = 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mm() throws ParseException {
/* 169 */     this.currentUnit = 7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pc() throws ParseException {
/* 176 */     this.currentUnit = 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pt() throws ParseException {
/* 183 */     this.currentUnit = 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void px() throws ParseException {
/* 190 */     this.currentUnit = 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void percentage() throws ParseException {
/* 197 */     this.currentUnit = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endLengthList() throws ParseException {
/* 206 */     float[] allValues = new float[this.count];
/* 207 */     int pos = 0;
/* 208 */     Iterator<float[]> it = this.vs.iterator();
/* 209 */     while (it.hasNext()) {
/* 210 */       float[] a = it.next();
/* 211 */       System.arraycopy(a, 0, allValues, pos, a.length);
/* 212 */       pos += a.length;
/*     */     } 
/* 214 */     System.arraycopy(this.v, 0, allValues, pos, this.index);
/* 215 */     this.vs.clear();
/* 216 */     this.v = allValues;
/*     */     
/* 218 */     short[] allUnits = new short[this.count];
/* 219 */     pos = 0;
/* 220 */     it = (Iterator)this.us.iterator();
/* 221 */     while (it.hasNext()) {
/* 222 */       short[] a = (short[])it.next();
/* 223 */       System.arraycopy(a, 0, allUnits, pos, a.length);
/* 224 */       pos += a.length;
/*     */     } 
/* 226 */     System.arraycopy(this.u, 0, allUnits, pos, this.index);
/* 227 */     this.us.clear();
/* 228 */     this.u = allUnits;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/LengthArrayProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */