/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import jp.cssj.sakae.sac.css.Selector;
/*     */ import jp.cssj.sakae.sac.css.SelectorList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSSelectorList
/*     */   implements SelectorList
/*     */ {
/*  67 */   protected Selector[] list = new Selector[3];
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
/*  78 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector item(int index) {
/*  86 */     if (index < 0 || index >= this.length) {
/*  87 */       return null;
/*     */     }
/*  89 */     return this.list[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Selector item) {
/*  96 */     if (this.length == this.list.length) {
/*  97 */       Selector[] tmp = this.list;
/*  98 */       this.list = new Selector[this.list.length * 3 / 2];
/*  99 */       for (int i = 0; i < tmp.length; i++) {
/* 100 */         this.list[i] = tmp[i];
/*     */       }
/*     */     } 
/* 103 */     this.list[this.length++] = item;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 107 */     StringBuffer buff = new StringBuffer();
/* 108 */     for (int i = 0; i < this.length; i++) {
/* 109 */       if (i > 0) {
/* 110 */         buff.append(", ");
/*     */       }
/* 112 */       buff.append(this.list[i]);
/*     */     } 
/* 114 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/CSSSelectorList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */