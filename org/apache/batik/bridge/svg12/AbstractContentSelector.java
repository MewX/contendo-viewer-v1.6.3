/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractContentSelector
/*     */ {
/*     */   protected ContentManager contentManager;
/*     */   protected XBLOMContentElement contentElement;
/*     */   protected Element boundElement;
/*     */   
/*     */   public AbstractContentSelector(ContentManager cm, XBLOMContentElement content, Element bound) {
/*  59 */     this.contentManager = cm;
/*  60 */     this.contentElement = content;
/*  61 */     this.boundElement = bound;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(Node n) {
/*  83 */     return (this.contentManager.getContentElement(n) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected static HashMap selectorFactories = new HashMap<Object, Object>();
/*     */   static {
/*  91 */     ContentSelectorFactory f1 = new XPathPatternContentSelectorFactory();
/*  92 */     ContentSelectorFactory f2 = new XPathSubsetContentSelectorFactory();
/*  93 */     selectorFactories.put(null, f1);
/*  94 */     selectorFactories.put("XPathPattern", f1);
/*  95 */     selectorFactories.put("XPathSubset", f2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractContentSelector createSelector(String selectorLanguage, ContentManager cm, XBLOMContentElement content, Element bound, String selector) {
/* 111 */     ContentSelectorFactory f = (ContentSelectorFactory)selectorFactories.get(selectorLanguage);
/*     */     
/* 113 */     if (f == null) {
/* 114 */       throw new RuntimeException("Invalid XBL content selector language '" + selectorLanguage + "'");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 119 */     return f.createSelector(cm, content, bound, selector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NodeList getSelectedContent();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean update();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static interface ContentSelectorFactory
/*     */   {
/*     */     AbstractContentSelector createSelector(ContentManager param1ContentManager, XBLOMContentElement param1XBLOMContentElement, Element param1Element, String param1String);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XPathSubsetContentSelectorFactory
/*     */     implements ContentSelectorFactory
/*     */   {
/*     */     public AbstractContentSelector createSelector(ContentManager cm, XBLOMContentElement content, Element bound, String selector) {
/* 149 */       return new XPathSubsetContentSelector(cm, content, bound, selector);
/*     */     }
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
/*     */   
/*     */   protected static class XPathPatternContentSelectorFactory
/*     */     implements ContentSelectorFactory
/*     */   {
/*     */     public AbstractContentSelector createSelector(ContentManager cm, XBLOMContentElement content, Element bound, String selector) {
/* 166 */       return new XPathPatternContentSelector(cm, content, bound, selector);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/AbstractContentSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */