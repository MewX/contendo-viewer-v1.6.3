/*     */ package org.apache.batik.css.engine.sac;
/*     */ 
/*     */ import org.w3c.css.sac.CSSException;
/*     */ import org.w3c.css.sac.CharacterDataSelector;
/*     */ import org.w3c.css.sac.Condition;
/*     */ import org.w3c.css.sac.ConditionalSelector;
/*     */ import org.w3c.css.sac.DescendantSelector;
/*     */ import org.w3c.css.sac.ElementSelector;
/*     */ import org.w3c.css.sac.NegativeSelector;
/*     */ import org.w3c.css.sac.ProcessingInstructionSelector;
/*     */ import org.w3c.css.sac.Selector;
/*     */ import org.w3c.css.sac.SelectorFactory;
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
/*     */ public class CSSSelectorFactory
/*     */   implements SelectorFactory
/*     */ {
/*  45 */   public static final SelectorFactory INSTANCE = new CSSSelectorFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionalSelector createConditionalSelector(SimpleSelector selector, Condition condition) throws CSSException {
/*  61 */     return new CSSConditionalSelector(selector, condition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createAnyNodeSelector() throws CSSException {
/*  69 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createRootNodeSelector() throws CSSException {
/*  77 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeSelector createNegativeSelector(SimpleSelector selector) throws CSSException {
/*  86 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createElementSelector(String namespaceURI, String tagName) throws CSSException {
/*  96 */     return new CSSElementSelector(namespaceURI, tagName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createTextNodeSelector(String data) throws CSSException {
/* 105 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCDataSectionSelector(String data) throws CSSException {
/* 114 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProcessingInstructionSelector createProcessingInstructionSelector(String target, String data) throws CSSException {
/* 124 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCommentSelector(String data) throws CSSException {
/* 133 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createPseudoElementSelector(String namespaceURI, String pseudoName) throws CSSException {
/* 143 */     return new CSSPseudoElementSelector(namespaceURI, pseudoName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendantSelector createDescendantSelector(Selector parent, SimpleSelector descendant) throws CSSException {
/* 154 */     return new CSSDescendantSelector(parent, descendant);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendantSelector createChildSelector(Selector parent, SimpleSelector child) throws CSSException {
/* 164 */     return new CSSChildSelector(parent, child);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SiblingSelector createDirectAdjacentSelector(short nodeType, Selector child, SimpleSelector directAdjacent) throws CSSException {
/* 176 */     return new CSSDirectAdjacentSelector(nodeType, child, directAdjacent);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSSelectorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */