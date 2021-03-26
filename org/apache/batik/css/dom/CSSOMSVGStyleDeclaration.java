/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.css.CSSRule;
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
/*     */ public class CSSOMSVGStyleDeclaration
/*     */   extends CSSOMStyleDeclaration
/*     */ {
/*     */   protected CSSEngine cssEngine;
/*     */   
/*     */   public CSSOMSVGStyleDeclaration(CSSOMStyleDeclaration.ValueProvider vp, CSSRule parent, CSSEngine eng) {
/*  49 */     super(vp, parent);
/*  50 */     this.cssEngine = eng;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSValue createCSSValue(String name) {
/*  57 */     int idx = this.cssEngine.getPropertyIndex(name);
/*  58 */     if (idx > 59) {
/*  59 */       if (this.cssEngine.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGPaintManager) {
/*  60 */         return (CSSValue)new StyleDeclarationPaintValue(name);
/*     */       }
/*  62 */       if (this.cssEngine.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGColorManager) {
/*  63 */         return (CSSValue)new StyleDeclarationColorValue(name);
/*     */       }
/*     */     } else {
/*  66 */       switch (idx) {
/*     */         case 15:
/*     */         case 45:
/*  69 */           return (CSSValue)new StyleDeclarationPaintValue(name);
/*     */         
/*     */         case 19:
/*     */         case 33:
/*     */         case 43:
/*  74 */           return (CSSValue)new StyleDeclarationColorValue(name);
/*     */       } 
/*     */     } 
/*  77 */     return super.createCSSValue(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class StyleDeclarationColorValue
/*     */     extends CSSOMSVGColor
/*     */     implements CSSOMSVGColor.ValueProvider
/*     */   {
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StyleDeclarationColorValue(String prop) {
/*  96 */       super(null);
/*  97 */       this.valueProvider = this;
/*  98 */       setModificationHandler(new CSSOMSVGColor.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 100 */               return CSSOMSVGStyleDeclaration.StyleDeclarationColorValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 103 */               if (CSSOMSVGStyleDeclaration.StyleDeclarationColorValue.this.handler == null) {
/* 104 */                 throw new DOMException((short)7, "");
/*     */               }
/*     */               
/* 107 */               String prio = CSSOMSVGStyleDeclaration.this.getPropertyPriority(CSSOMSVGStyleDeclaration.StyleDeclarationColorValue.this.property);
/* 108 */               CSSOMSVGStyleDeclaration.this.handler.propertyChanged(CSSOMSVGStyleDeclaration.StyleDeclarationColorValue.this.property, text, prio);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 113 */       this.property = prop;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 122 */       return CSSOMSVGStyleDeclaration.this.valueProvider.getValue(this.property);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class StyleDeclarationPaintValue
/*     */     extends CSSOMSVGPaint
/*     */     implements CSSOMSVGColor.ValueProvider
/*     */   {
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StyleDeclarationPaintValue(String prop) {
/* 144 */       super((CSSOMSVGColor.ValueProvider)null);
/* 145 */       this.valueProvider = this;
/* 146 */       setModificationHandler(new CSSOMSVGPaint.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 148 */               return CSSOMSVGStyleDeclaration.StyleDeclarationPaintValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 151 */               if (CSSOMSVGStyleDeclaration.StyleDeclarationPaintValue.this.handler == null) {
/* 152 */                 throw new DOMException((short)7, "");
/*     */               }
/*     */               
/* 155 */               String prio = CSSOMSVGStyleDeclaration.this.getPropertyPriority(CSSOMSVGStyleDeclaration.StyleDeclarationPaintValue.this.property);
/* 156 */               CSSOMSVGStyleDeclaration.this.handler.propertyChanged(CSSOMSVGStyleDeclaration.StyleDeclarationPaintValue.this.property, text, prio);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 161 */       this.property = prop;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 170 */       return CSSOMSVGStyleDeclaration.this.valueProvider.getValue(this.property);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMSVGStyleDeclaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */