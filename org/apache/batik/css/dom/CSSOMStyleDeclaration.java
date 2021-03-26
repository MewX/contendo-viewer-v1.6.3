/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSOMStyleDeclaration
/*     */   implements CSSStyleDeclaration
/*     */ {
/*     */   protected ValueProvider valueProvider;
/*     */   protected ModificationHandler handler;
/*     */   protected CSSRule parentRule;
/*     */   protected Map values;
/*     */   
/*     */   public CSSOMStyleDeclaration(ValueProvider vp, CSSRule parent) {
/*  62 */     this.valueProvider = vp;
/*  63 */     this.parentRule = parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModificationHandler(ModificationHandler h) {
/*  70 */     this.handler = h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssText() {
/*  78 */     return this.valueProvider.getText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCssText(String cssText) throws DOMException {
/*  86 */     if (this.handler == null) {
/*  87 */       throw new DOMException((short)7, "");
/*     */     }
/*     */     
/*  90 */     this.values = null;
/*  91 */     this.handler.textChanged(cssText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyValue(String propertyName) {
/* 100 */     Value value = this.valueProvider.getValue(propertyName);
/* 101 */     if (value == null) {
/* 102 */       return "";
/*     */     }
/* 104 */     return value.getCssText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSValue getPropertyCSSValue(String propertyName) {
/* 112 */     Value value = this.valueProvider.getValue(propertyName);
/* 113 */     if (value == null) {
/* 114 */       return null;
/*     */     }
/* 116 */     return getCSSValue(propertyName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeProperty(String propertyName) throws DOMException {
/* 124 */     String result = getPropertyValue(propertyName);
/* 125 */     if (result.length() > 0) {
/* 126 */       if (this.handler == null) {
/* 127 */         throw new DOMException((short)7, "");
/*     */       }
/*     */       
/* 130 */       if (this.values != null) {
/* 131 */         this.values.remove(propertyName);
/*     */       }
/* 133 */       this.handler.propertyRemoved(propertyName);
/*     */     } 
/*     */     
/* 136 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyPriority(String propertyName) {
/* 144 */     return this.valueProvider.isImportant(propertyName) ? "important" : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(String propertyName, String value, String prio) throws DOMException {
/* 153 */     if (this.handler == null) {
/* 154 */       throw new DOMException((short)7, "");
/*     */     }
/*     */     
/* 157 */     this.handler.propertyChanged(propertyName, value, prio);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 166 */     return this.valueProvider.getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String item(int index) {
/* 174 */     return this.valueProvider.item(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSRule getParentRule() {
/* 182 */     return this.parentRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSValue getCSSValue(String name) {
/* 189 */     CSSValue result = null;
/* 190 */     if (this.values != null) {
/* 191 */       result = (CSSValue)this.values.get(name);
/*     */     }
/* 193 */     if (result == null) {
/* 194 */       result = createCSSValue(name);
/* 195 */       if (this.values == null) {
/* 196 */         this.values = new HashMap<Object, Object>(11);
/*     */       }
/* 198 */       this.values.put(name, result);
/*     */     } 
/* 200 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSValue createCSSValue(String name) {
/* 207 */     return new StyleDeclarationValue(name);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class StyleDeclarationValue
/*     */     extends CSSOMValue
/*     */     implements CSSOMValue.ValueProvider
/*     */   {
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StyleDeclarationValue(String prop) {
/* 279 */       super(null);
/* 280 */       this.valueProvider = this;
/* 281 */       setModificationHandler(new CSSOMValue.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 283 */               return CSSOMStyleDeclaration.StyleDeclarationValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 286 */               if (CSSOMStyleDeclaration.this.values == null || CSSOMStyleDeclaration.this.values.get(this) == null || CSSOMStyleDeclaration.StyleDeclarationValue.this.handler == null)
/*     */               {
/*     */                 
/* 289 */                 throw new DOMException((short)7, "");
/*     */               }
/*     */               
/* 292 */               String prio = CSSOMStyleDeclaration.this.getPropertyPriority(CSSOMStyleDeclaration.StyleDeclarationValue.this.property);
/* 293 */               CSSOMStyleDeclaration.this.handler.propertyChanged(CSSOMStyleDeclaration.StyleDeclarationValue.this.property, text, prio);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 298 */       this.property = prop;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 307 */       return CSSOMStyleDeclaration.this.valueProvider.getValue(this.property);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ModificationHandler {
/*     */     void textChanged(String param1String) throws DOMException;
/*     */     
/*     */     void propertyRemoved(String param1String) throws DOMException;
/*     */     
/*     */     void propertyChanged(String param1String1, String param1String2, String param1String3) throws DOMException;
/*     */   }
/*     */   
/*     */   public static interface ValueProvider {
/*     */     Value getValue(String param1String);
/*     */     
/*     */     boolean isImportant(String param1String);
/*     */     
/*     */     String getText();
/*     */     
/*     */     int getLength();
/*     */     
/*     */     String item(int param1Int);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMStyleDeclaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */