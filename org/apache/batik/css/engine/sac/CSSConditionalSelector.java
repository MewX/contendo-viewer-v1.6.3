/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.w3c.css.sac.Condition;
/*     */ import org.w3c.css.sac.ConditionalSelector;
/*     */ import org.w3c.css.sac.SimpleSelector;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSConditionalSelector
/*     */   implements ExtendedSelector, ConditionalSelector
/*     */ {
/*     */   protected SimpleSelector simpleSelector;
/*     */   protected Condition condition;
/*     */   
/*     */   public CSSConditionalSelector(SimpleSelector s, Condition c) {
/*  53 */     this.simpleSelector = s;
/*  54 */     this.condition = c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  62 */     if (obj == null || obj.getClass() != getClass()) {
/*  63 */       return false;
/*     */     }
/*  65 */     CSSConditionalSelector s = (CSSConditionalSelector)obj;
/*  66 */     return (s.simpleSelector.equals(this.simpleSelector) && s.condition.equals(this.condition));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getSelectorType() {
/*  75 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(Element e, String pseudoE) {
/*  82 */     return (((ExtendedSelector)getSimpleSelector()).match(e, pseudoE) && ((ExtendedCondition)getCondition()).match(e, pseudoE));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillAttributeSet(Set attrSet) {
/*  90 */     ((ExtendedSelector)getSimpleSelector()).fillAttributeSet(attrSet);
/*  91 */     ((ExtendedCondition)getCondition()).fillAttributeSet(attrSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecificity() {
/*  98 */     return ((ExtendedSelector)getSimpleSelector()).getSpecificity() + ((ExtendedCondition)getCondition()).getSpecificity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector getSimpleSelector() {
/* 107 */     return this.simpleSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition getCondition() {
/* 115 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return String.valueOf(this.simpleSelector) + this.condition;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSConditionalSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */