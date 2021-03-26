/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.css.DocumentCSS;
/*     */ import org.w3c.dom.stylesheets.StyleSheetList;
/*     */ import org.w3c.dom.views.AbstractView;
/*     */ import org.w3c.dom.views.DocumentView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractStylableDocument
/*     */   extends AbstractDocument
/*     */   implements DocumentCSS, DocumentView
/*     */ {
/*     */   protected transient AbstractView defaultView;
/*     */   protected transient CSSEngine cssEngine;
/*     */   
/*     */   protected AbstractStylableDocument() {}
/*     */   
/*     */   protected AbstractStylableDocument(DocumentType dt, DOMImplementation impl) {
/*  62 */     super(dt, impl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCSSEngine(CSSEngine ctx) {
/*  69 */     this.cssEngine = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSEngine getCSSEngine() {
/*  76 */     return this.cssEngine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheetList getStyleSheets() {
/*  86 */     throw new RuntimeException(" !!! Not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractView getDefaultView() {
/*  96 */     if (this.defaultView == null) {
/*     */       
/*  98 */       ExtensibleDOMImplementation impl = (ExtensibleDOMImplementation)this.implementation;
/*  99 */       this.defaultView = impl.createViewCSS(this);
/*     */     } 
/* 101 */     return this.defaultView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearViewCSS() {
/* 108 */     this.defaultView = null;
/* 109 */     if (this.cssEngine != null) {
/* 110 */       this.cssEngine.dispose();
/*     */     }
/* 112 */     this.cssEngine = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getOverrideStyle(Element elt, String pseudoElt) {
/* 123 */     throw new RuntimeException(" !!! Not implemented");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractStylableDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */