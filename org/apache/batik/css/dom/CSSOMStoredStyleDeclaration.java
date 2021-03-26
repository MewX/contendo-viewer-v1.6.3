/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.StyleDeclaration;
/*     */ import org.apache.batik.css.engine.StyleDeclarationProvider;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.w3c.dom.css.CSSRule;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CSSOMStoredStyleDeclaration
/*     */   extends CSSOMSVGStyleDeclaration
/*     */   implements CSSOMStyleDeclaration.ModificationHandler, CSSOMStyleDeclaration.ValueProvider, StyleDeclarationProvider
/*     */ {
/*     */   protected StyleDeclaration declaration;
/*     */   
/*     */   public CSSOMStoredStyleDeclaration(CSSEngine eng) {
/*  48 */     super((CSSOMStyleDeclaration.ValueProvider)null, (CSSRule)null, eng);
/*  49 */     this.valueProvider = this;
/*  50 */     setModificationHandler(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleDeclaration getStyleDeclaration() {
/*  57 */     return this.declaration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStyleDeclaration(StyleDeclaration sd) {
/*  64 */     this.declaration = sd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getValue(String name) {
/*  73 */     int idx = this.cssEngine.getPropertyIndex(name);
/*  74 */     for (int i = 0; i < this.declaration.size(); i++) {
/*  75 */       if (idx == this.declaration.getIndex(i)) {
/*  76 */         return this.declaration.getValue(i);
/*     */       }
/*     */     } 
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImportant(String name) {
/*  86 */     int idx = this.cssEngine.getPropertyIndex(name);
/*  87 */     for (int i = 0; i < this.declaration.size(); i++) {
/*  88 */       if (idx == this.declaration.getIndex(i)) {
/*  89 */         return this.declaration.getPriority(i);
/*     */       }
/*     */     } 
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/*  99 */     return this.declaration.toString(this.cssEngine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 106 */     return this.declaration.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String item(int idx) {
/* 113 */     return this.cssEngine.getPropertyName(this.declaration.getIndex(idx));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMStoredStyleDeclaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */