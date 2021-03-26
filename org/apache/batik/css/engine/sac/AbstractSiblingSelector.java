/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import org.w3c.css.sac.Selector;
/*     */ import org.w3c.css.sac.SiblingSelector;
/*     */ import org.w3c.css.sac.SimpleSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSiblingSelector
/*     */   implements ExtendedSelector, SiblingSelector
/*     */ {
/*     */   protected short nodeType;
/*     */   protected Selector selector;
/*     */   protected SimpleSelector simpleSelector;
/*     */   
/*     */   protected AbstractSiblingSelector(short type, Selector sel, SimpleSelector simple) {
/*  57 */     this.nodeType = type;
/*  58 */     this.selector = sel;
/*  59 */     this.simpleSelector = simple;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNodeType() {
/*  66 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  74 */     if (obj == null || obj.getClass() != getClass()) {
/*  75 */       return false;
/*     */     }
/*  77 */     AbstractSiblingSelector s = (AbstractSiblingSelector)obj;
/*  78 */     return s.simpleSelector.equals(this.simpleSelector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecificity() {
/*  85 */     return ((ExtendedSelector)this.selector).getSpecificity() + ((ExtendedSelector)this.simpleSelector).getSpecificity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector getSelector() {
/*  94 */     return this.selector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector getSiblingSelector() {
/* 102 */     return this.simpleSelector;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/AbstractSiblingSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */