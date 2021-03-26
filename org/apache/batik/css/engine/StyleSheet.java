/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ import org.w3c.css.sac.SACMediaList;
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
/*     */ public class StyleSheet
/*     */ {
/*  34 */   protected Rule[] rules = new Rule[16];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int size;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StyleSheet parent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean alternate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SACMediaList media;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String title;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMedia(SACMediaList m) {
/*  65 */     this.media = m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SACMediaList getMedia() {
/*  72 */     return this.media;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheet getParent() {
/*  79 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(StyleSheet ss) {
/*  86 */     this.parent = ss;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternate(boolean b) {
/*  93 */     this.alternate = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlternate() {
/* 100 */     return this.alternate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String t) {
/* 107 */     this.title = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 114 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 121 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule(int i) {
/* 128 */     return this.rules[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 135 */     this.size = 0;
/* 136 */     this.rules = new Rule[10];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Rule r) {
/* 143 */     if (this.size == this.rules.length) {
/* 144 */       Rule[] t = new Rule[this.size * 2];
/* 145 */       System.arraycopy(this.rules, 0, t, 0, this.size);
/* 146 */       this.rules = t;
/*     */     } 
/* 148 */     this.rules[this.size++] = r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(CSSEngine eng) {
/* 155 */     StringBuffer sb = new StringBuffer(this.size * 8);
/* 156 */     for (int i = 0; i < this.size; i++) {
/* 157 */       sb.append(this.rules[i].toString(eng));
/*     */     }
/* 159 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/StyleSheet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */