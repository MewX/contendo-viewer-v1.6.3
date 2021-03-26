/*     */ package org.apache.fontbox.afm;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.fontbox.util.BoundingBox;
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
/*     */ public class CharMetric
/*     */ {
/*     */   private int characterCode;
/*     */   private float wx;
/*     */   private float w0x;
/*     */   private float w1x;
/*     */   private float wy;
/*     */   private float w0y;
/*     */   private float w1y;
/*     */   private float[] w;
/*     */   private float[] w0;
/*     */   private float[] w1;
/*     */   private float[] vv;
/*     */   private String name;
/*     */   private BoundingBox boundingBox;
/*  48 */   private List<Ligature> ligatures = new ArrayList<Ligature>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() {
/*  55 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoundingBox(BoundingBox bBox) {
/*  63 */     this.boundingBox = bBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacterCode() {
/*  71 */     return this.characterCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacterCode(int cCode) {
/*  79 */     this.characterCode = cCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLigature(Ligature ligature) {
/*  89 */     this.ligatures.add(ligature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Ligature> getLigatures() {
/*  97 */     return this.ligatures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLigatures(List<Ligature> lig) {
/* 105 */     this.ligatures = lig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String n) {
/* 121 */     this.name = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getVv() {
/* 129 */     return this.vv;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVv(float[] vvValue) {
/* 137 */     this.vv = vvValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getW() {
/* 145 */     return this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW(float[] wValue) {
/* 153 */     this.w = wValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getW0() {
/* 161 */     return this.w0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW0(float[] w0Value) {
/* 169 */     this.w0 = w0Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getW0x() {
/* 177 */     return this.w0x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW0x(float w0xValue) {
/* 185 */     this.w0x = w0xValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getW0y() {
/* 193 */     return this.w0y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW0y(float w0yValue) {
/* 201 */     this.w0y = w0yValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getW1() {
/* 209 */     return this.w1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW1(float[] w1Value) {
/* 217 */     this.w1 = w1Value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getW1x() {
/* 225 */     return this.w1x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW1x(float w1xValue) {
/* 233 */     this.w1x = w1xValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getW1y() {
/* 241 */     return this.w1y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setW1y(float w1yValue) {
/* 249 */     this.w1y = w1yValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWx() {
/* 257 */     return this.wx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWx(float wxValue) {
/* 265 */     this.wx = wxValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWy() {
/* 273 */     return this.wy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWy(float wyValue) {
/* 281 */     this.wy = wyValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/CharMetric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */