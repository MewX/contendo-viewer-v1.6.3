/*     */ package org.apache.batik.css.parser;
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
/*     */ 
/*     */ public class DefaultSelectorFactory
/*     */   implements SelectorFactory
/*     */ {
/*  46 */   public static final SelectorFactory INSTANCE = new DefaultSelectorFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  63 */     return new DefaultConditionalSelector(selector, condition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createAnyNodeSelector() throws CSSException {
/*  71 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createRootNodeSelector() throws CSSException {
/*  79 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeSelector createNegativeSelector(SimpleSelector selector) throws CSSException {
/*  88 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createElementSelector(String namespaceURI, String tagName) throws CSSException {
/*  98 */     return new DefaultElementSelector(namespaceURI, tagName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createTextNodeSelector(String data) throws CSSException {
/* 107 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCDataSectionSelector(String data) throws CSSException {
/* 116 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProcessingInstructionSelector createProcessingInstructionSelector(String target, String data) throws CSSException {
/* 126 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCommentSelector(String data) throws CSSException {
/* 135 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createPseudoElementSelector(String namespaceURI, String pseudoName) throws CSSException {
/* 145 */     return new DefaultPseudoElementSelector(namespaceURI, pseudoName);
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
/* 156 */     return new DefaultDescendantSelector(parent, descendant);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendantSelector createChildSelector(Selector parent, SimpleSelector child) throws CSSException {
/* 166 */     return new DefaultChildSelector(parent, child);
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
/* 178 */     return new DefaultDirectAdjacentSelector(nodeType, child, directAdjacent);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultSelectorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */