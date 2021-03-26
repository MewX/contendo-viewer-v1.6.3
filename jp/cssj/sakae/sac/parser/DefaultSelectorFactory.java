/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.CharacterDataSelector;
/*     */ import jp.cssj.sakae.sac.css.Condition;
/*     */ import jp.cssj.sakae.sac.css.ConditionalSelector;
/*     */ import jp.cssj.sakae.sac.css.DescendantSelector;
/*     */ import jp.cssj.sakae.sac.css.ElementSelector;
/*     */ import jp.cssj.sakae.sac.css.NegativeSelector;
/*     */ import jp.cssj.sakae.sac.css.ProcessingInstructionSelector;
/*     */ import jp.cssj.sakae.sac.css.Selector;
/*     */ import jp.cssj.sakae.sac.css.SelectorFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  80 */   public static final SelectorFactory INSTANCE = new DefaultSelectorFactory();
/*     */ 
/*     */ 
/*     */ 
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
/*  94 */     return new DefaultConditionalSelector(selector, condition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createAnyNodeSelector() throws CSSException {
/* 102 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleSelector createRootNodeSelector() throws CSSException {
/* 110 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NegativeSelector createNegativeSelector(SimpleSelector selector) throws CSSException {
/* 119 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createElementSelector(String namespaceURI, String tagName) throws CSSException {
/* 128 */     return new DefaultElementSelector(namespaceURI, tagName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createTextNodeSelector(String data) throws CSSException {
/* 136 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCDataSectionSelector(String data) throws CSSException {
/* 145 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProcessingInstructionSelector createProcessingInstructionSelector(String target, String data) throws CSSException {
/* 154 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterDataSelector createCommentSelector(String data) throws CSSException {
/* 162 */     throw new CSSException("Not implemented in CSS2");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementSelector createPseudoElementSelector(String namespaceURI, String pseudoName) throws CSSException {
/* 170 */     return new DefaultPseudoElementSelector(namespaceURI, pseudoName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendantSelector createDescendantSelector(Selector parent, SimpleSelector descendant) throws CSSException {
/* 178 */     return new DefaultDescendantSelector(parent, descendant);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendantSelector createChildSelector(Selector parent, SimpleSelector child) throws CSSException {
/* 186 */     return new DefaultChildSelector(parent, child);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SiblingSelector createDirectAdjacentSelector(short nodeType, Selector child, SimpleSelector directAdjacent) throws CSSException {
/* 196 */     return new DefaultDirectAdjacentSelector(nodeType, child, directAdjacent);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultSelectorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */