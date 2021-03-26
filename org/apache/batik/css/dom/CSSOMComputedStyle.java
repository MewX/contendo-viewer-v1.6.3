/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.css.CSSRule;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.css.CSSValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSOMComputedStyle
/*     */   implements CSSStyleDeclaration
/*     */ {
/*     */   protected CSSEngine cssEngine;
/*     */   protected CSSStylableElement element;
/*     */   protected String pseudoElement;
/*  58 */   protected Map values = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSOMComputedStyle(CSSEngine e, CSSStylableElement elt, String pseudoElt) {
/*  66 */     this.cssEngine = e;
/*  67 */     this.element = elt;
/*  68 */     this.pseudoElement = pseudoElt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  76 */     StringBuffer sb = new StringBuffer();
/*  77 */     for (int i = 0; i < this.cssEngine.getNumberOfProperties(); i++) {
/*  78 */       sb.append(this.cssEngine.getPropertyName(i));
/*  79 */       sb.append(": ");
/*  80 */       sb.append(this.cssEngine.getComputedStyle(this.element, this.pseudoElement, i).getCssText());
/*     */       
/*  82 */       sb.append(";\n");
/*     */     } 
/*  84 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCssText(String cssText) throws DOMException {
/*  93 */     throw new DOMException((short)7, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyValue(String propertyName) {
/* 101 */     int idx = this.cssEngine.getPropertyIndex(propertyName);
/* 102 */     if (idx == -1) {
/* 103 */       return "";
/*     */     }
/* 105 */     Value v = this.cssEngine.getComputedStyle(this.element, this.pseudoElement, idx);
/* 106 */     return v.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSValue getPropertyCSSValue(String propertyName) {
/* 114 */     CSSValue result = (CSSValue)this.values.get(propertyName);
/* 115 */     if (result == null) {
/* 116 */       int idx = this.cssEngine.getPropertyIndex(propertyName);
/* 117 */       if (idx != -1) {
/* 118 */         result = createCSSValue(idx);
/* 119 */         this.values.put(propertyName, result);
/*     */       } 
/*     */     } 
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeProperty(String propertyName) throws DOMException {
/* 130 */     throw new DOMException((short)7, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyPriority(String propertyName) {
/* 138 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(String propertyName, String value, String prio) throws DOMException {
/* 147 */     throw new DOMException((short)7, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 155 */     return this.cssEngine.getNumberOfProperties();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String item(int index) {
/* 163 */     if (index < 0 || index >= this.cssEngine.getNumberOfProperties()) {
/* 164 */       return "";
/*     */     }
/* 166 */     return this.cssEngine.getPropertyName(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSRule getParentRule() {
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSValue createCSSValue(int idx) {
/* 182 */     return new ComputedCSSValue(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class ComputedCSSValue
/*     */     extends CSSOMValue
/*     */     implements CSSOMValue.ValueProvider
/*     */   {
/*     */     protected int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ComputedCSSValue(int idx) {
/* 201 */       super(null);
/* 202 */       this.valueProvider = this;
/* 203 */       this.index = idx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 210 */       return CSSOMComputedStyle.this.cssEngine.getComputedStyle(CSSOMComputedStyle.this.element, CSSOMComputedStyle.this.pseudoElement, this.index);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMComputedStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */