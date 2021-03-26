/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import jp.cssj.sakae.sac.css.SACMediaList;
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
/*     */ 
/*     */ 
/*     */ public class CSSSACMediaList
/*     */   implements SACMediaList
/*     */ {
/*  66 */   protected String[] list = new String[3];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int length;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  77 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String item(int index) {
/*  85 */     if (index < 0 || index >= this.length) {
/*  86 */       return null;
/*     */     }
/*  88 */     return this.list[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(String item) {
/*  95 */     if (this.length == this.list.length) {
/*  96 */       String[] tmp = this.list;
/*  97 */       this.list = new String[this.list.length * 3 / 2];
/*  98 */       for (int i = 0; i < tmp.length; i++) {
/*  99 */         this.list[i] = tmp[i];
/*     */       }
/*     */     } 
/* 102 */     this.list[this.length++] = item;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuffer buff = new StringBuffer();
/* 107 */     if (this.length > 0) {
/* 108 */       buff.append(this.list[0]);
/* 109 */       for (int i = 1; i < this.length; i++) {
/* 110 */         buff.append(", ");
/* 111 */         buff.append(this.list[i]);
/*     */       } 
/*     */     } 
/* 114 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/CSSSACMediaList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */