/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import jp.cssj.sakae.sac.css.CombinatorCondition;
/*     */ import jp.cssj.sakae.sac.css.Condition;
/*     */ import jp.cssj.sakae.sac.css.ConditionalSelector;
/*     */ import jp.cssj.sakae.sac.css.DescendantSelector;
/*     */ import jp.cssj.sakae.sac.css.ElementSelector;
/*     */ import jp.cssj.sakae.sac.css.NegativeCondition;
/*     */ import jp.cssj.sakae.sac.css.NegativeSelector;
/*     */ import jp.cssj.sakae.sac.css.Selector;
/*     */ import jp.cssj.sakae.sac.css.SiblingSelector;
/*     */ import jp.cssj.sakae.sac.css.SimpleSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class i
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long a = 0L;
/*     */   private final Selector b;
/*     */   private final f c;
/*  29 */   private int d = -1;
/*     */   
/*     */   public i(Selector selector, f declaration) {
/*  32 */     this.b = selector;
/*  33 */     this.c = declaration;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  37 */     return new i(this.b, (f)this.c.clone());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector a() {
/*  46 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public f b() {
/*  55 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c() {
/*  64 */     if (this.d == -1) {
/*  65 */       int[] triplet = new int[3];
/*  66 */       Selector selector = this.b;
/*  67 */       while (selector != null) {
/*  68 */         DescendantSelector descendantSelector; SiblingSelector siblingSelector; SimpleSelector simpleSelector = null;
/*  69 */         switch (selector.getSelectorType()) {
/*     */           case 10:
/*     */           case 11:
/*  72 */             descendantSelector = (DescendantSelector)selector;
/*  73 */             selector = descendantSelector.getAncestorSelector();
/*  74 */             simpleSelector = descendantSelector.getSimpleSelector();
/*     */             break;
/*     */           
/*     */           case 12:
/*  78 */             siblingSelector = (SiblingSelector)selector;
/*  79 */             selector = siblingSelector.getSelector();
/*  80 */             simpleSelector = siblingSelector.getSiblingSelector();
/*     */             break;
/*     */           
/*     */           default:
/*  84 */             simpleSelector = (SimpleSelector)selector;
/*  85 */             selector = null;
/*     */             break;
/*     */         } 
/*  88 */         while (simpleSelector != null) {
/*  89 */           NegativeSelector negativeSelector; ElementSelector elementSelector; String localName; ConditionalSelector conditionalSelector; Condition condition; switch (simpleSelector.getSelectorType()) {
/*     */             case 3:
/*  91 */               negativeSelector = (NegativeSelector)simpleSelector;
/*  92 */               simpleSelector = negativeSelector.getSimpleSelector();
/*     */               continue;
/*     */             
/*     */             case 1:
/*     */             case 2:
/*  97 */               simpleSelector = null;
/*     */               continue;
/*     */             
/*     */             case 4:
/* 101 */               elementSelector = (ElementSelector)simpleSelector;
/* 102 */               localName = elementSelector.getLocalName();
/* 103 */               if (localName != null) {
/* 104 */                 triplet[2] = triplet[2] + 1;
/*     */               }
/* 106 */               simpleSelector = null;
/*     */               continue;
/*     */             
/*     */             case 0:
/* 110 */               conditionalSelector = (ConditionalSelector)simpleSelector;
/* 111 */               condition = conditionalSelector.getCondition();
/* 112 */               a(condition, triplet);
/* 113 */               simpleSelector = conditionalSelector.getSimpleSelector();
/*     */               continue;
/*     */             
/*     */             case 5:
/*     */             case 6:
/*     */             case 8:
/*     */             case 9:
/* 120 */               triplet[1] = triplet[1] + 1;
/* 121 */               simpleSelector = null;
/*     */               continue;
/*     */           } 
/*     */           
/* 125 */           throw new IllegalStateException();
/*     */         } 
/*     */       } 
/*     */       
/* 129 */       this.d = triplet[0] * 65536 + triplet[1] * 256 + triplet[2];
/*     */     } 
/* 131 */     return this.d;
/*     */   } private static void a(Condition condition, int[] triplet) {
/*     */     CombinatorCondition combinatorCondition;
/*     */     NegativeCondition negativeCondition;
/* 135 */     switch (condition.getConditionType()) {
/*     */       case 5:
/* 137 */         triplet[0] = triplet[0] + 1;
/*     */         return;
/*     */       
/*     */       case 0:
/*     */       case 1:
/* 142 */         combinatorCondition = (CombinatorCondition)condition;
/* 143 */         a(combinatorCondition.getFirstCondition(), triplet);
/* 144 */         a(combinatorCondition.getSecondCondition(), triplet);
/*     */         return;
/*     */       
/*     */       case 2:
/* 148 */         negativeCondition = (NegativeCondition)condition;
/* 149 */         a(negativeCondition.getCondition(), triplet);
/*     */         return;
/*     */     } 
/*     */     
/* 153 */     triplet[1] = triplet[1] + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     return this.b + " { \n" + this.c + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */