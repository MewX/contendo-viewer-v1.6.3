/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.css.dom.CSSOMSVGColor;
/*     */ import org.apache.batik.css.dom.CSSOMSVGPaint;
/*     */ import org.apache.batik.css.dom.CSSOMStoredStyleDeclaration;
/*     */ import org.apache.batik.css.dom.CSSOMValue;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleDeclarationProvider;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.LiveAttributeValue;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.css.CSSValue;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGStylableElement
/*     */   extends SVGOMElement
/*     */   implements CSSStylableElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected StyleMap computedStyleMap;
/*     */   protected OverrideStyleDeclaration overrideStyleDeclaration;
/*     */   protected SVGOMAnimatedString className;
/*     */   protected StyleDeclaration style;
/*     */   
/*     */   static {
/*  63 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMElement.xmlTraitInformation);
/*     */     
/*  65 */     t.put(null, "class", new TraitInformation(true, 16));
/*     */     
/*  67 */     xmlTraitInformation = t;
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
/*     */   protected SVGStylableElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGStylableElement(String prefix, AbstractDocument owner) {
/* 102 */     super(prefix, owner);
/* 103 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 110 */     super.initializeAllLiveAttributes();
/* 111 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 118 */     this.className = createLiveAnimatedString((String)null, "class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getOverrideStyle() {
/* 125 */     if (this.overrideStyleDeclaration == null) {
/* 126 */       CSSEngine eng = ((SVGOMDocument)getOwnerDocument()).getCSSEngine();
/* 127 */       this.overrideStyleDeclaration = new OverrideStyleDeclaration(eng);
/*     */     } 
/* 129 */     return (CSSStyleDeclaration)this.overrideStyleDeclaration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleMap getComputedStyleMap(String pseudoElement) {
/* 138 */     return this.computedStyleMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComputedStyleMap(String pseudoElement, StyleMap sm) {
/* 145 */     this.computedStyleMap = sm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLId() {
/* 152 */     return getAttributeNS(null, "id");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSSClass() {
/* 159 */     return getAttributeNS(null, "class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL getCSSBase() {
/* 168 */     if (getXblBoundElement() != null) {
/* 169 */       return null;
/*     */     }
/* 171 */     String bu = getBaseURI();
/* 172 */     return (bu == null) ? null : new ParsedURL(bu);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPseudoInstanceOf(String pseudoClass) {
/* 180 */     if (pseudoClass.equals("first-child")) {
/* 181 */       Node n = getPreviousSibling();
/* 182 */       while (n != null && n.getNodeType() != 1) {
/* 183 */         n = n.getPreviousSibling();
/*     */       }
/* 185 */       return (n == null);
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleDeclarationProvider getOverrideStyleDeclarationProvider() {
/* 196 */     return (StyleDeclarationProvider)getOverrideStyle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePropertyValue(String pn, AnimatableValue val) {
/* 205 */     CSSStyleDeclaration over = getOverrideStyle();
/* 206 */     if (val == null) {
/* 207 */       over.removeProperty(pn);
/*     */     } else {
/* 209 */       over.setProperty(pn, val.getCssText(), "");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useLinearRGBColorInterpolation() {
/* 218 */     CSSEngine eng = ((SVGOMDocument)getOwnerDocument()).getCSSEngine();
/* 219 */     Value v = eng.getComputedStyle(this, null, 6);
/*     */     
/* 221 */     return (v.getStringValue().charAt(0) == 'l');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetListener(String ns, String an, boolean isCSS, AnimationTargetListener l) {
/* 229 */     if (isCSS) {
/* 230 */       if (this.svgContext != null) {
/* 231 */         SVGAnimationTargetContext actx = (SVGAnimationTargetContext)this.svgContext;
/*     */         
/* 233 */         actx.addTargetListener(an, l);
/*     */       } 
/*     */     } else {
/* 236 */       super.addTargetListener(ns, an, isCSS, l);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTargetListener(String ns, String an, boolean isCSS, AnimationTargetListener l) {
/* 245 */     if (isCSS) {
/* 246 */       if (this.svgContext != null) {
/* 247 */         SVGAnimationTargetContext actx = (SVGAnimationTargetContext)this.svgContext;
/*     */         
/* 249 */         actx.removeTargetListener(an, l);
/*     */       } 
/*     */     } else {
/* 252 */       super.removeTargetListener(ns, an, isCSS, l);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getStyle() {
/* 262 */     if (this.style == null) {
/* 263 */       CSSEngine eng = ((SVGOMDocument)getOwnerDocument()).getCSSEngine();
/* 264 */       this.style = new StyleDeclaration(eng);
/* 265 */       putLiveAttributeValue((String)null, "style", this.style);
/*     */     } 
/* 267 */     return (CSSStyleDeclaration)this.style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSValue getPresentationAttribute(String name) {
/*     */     PresentationAttributeValue presentationAttributeValue;
/* 275 */     CSSValue result = (CSSValue)getLiveAttributeValue((String)null, name);
/* 276 */     if (result != null) {
/* 277 */       return result;
/*     */     }
/* 279 */     CSSEngine eng = ((SVGOMDocument)getOwnerDocument()).getCSSEngine();
/* 280 */     int idx = eng.getPropertyIndex(name);
/* 281 */     if (idx == -1) {
/* 282 */       return null;
/*     */     }
/* 284 */     if (idx > 59) {
/* 285 */       if (eng.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGPaintManager) {
/* 286 */         PresentationAttributePaintValue presentationAttributePaintValue = new PresentationAttributePaintValue(eng, name);
/*     */       }
/* 288 */       if (eng.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGColorManager)
/* 289 */         PresentationAttributeColorValue presentationAttributeColorValue = new PresentationAttributeColorValue(eng, name); 
/*     */     } else {
/*     */       PresentationAttributePaintValue presentationAttributePaintValue; PresentationAttributeColorValue presentationAttributeColorValue;
/* 292 */       switch (idx) {
/*     */         case 15:
/*     */         case 45:
/* 295 */           presentationAttributePaintValue = new PresentationAttributePaintValue(eng, name);
/*     */           break;
/*     */         
/*     */         case 19:
/*     */         case 33:
/*     */         case 43:
/* 301 */           presentationAttributeColorValue = new PresentationAttributeColorValue(eng, name);
/*     */           break;
/*     */         
/*     */         default:
/* 305 */           presentationAttributeValue = new PresentationAttributeValue(eng, name); break;
/*     */       } 
/*     */     } 
/* 308 */     putLiveAttributeValue((String)null, name, presentationAttributeValue);
/* 309 */     if (getAttributeNS(null, name).length() == 0) {
/* 310 */       return null;
/*     */     }
/* 312 */     return (CSSValue)presentationAttributeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getClassName() {
/* 320 */     return this.className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 327 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class PresentationAttributeValue
/*     */     extends CSSOMValue
/*     */     implements CSSOMValue.ValueProvider, LiveAttributeValue
/*     */   {
/*     */     protected CSSEngine cssEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Value value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean mutate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PresentationAttributeValue(CSSEngine eng, String prop) {
/* 362 */       super(null);
/* 363 */       this.valueProvider = this;
/* 364 */       setModificationHandler((CSSOMValue.ModificationHandler)new CSSOMValue.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 366 */               return SVGStylableElement.PresentationAttributeValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 369 */               SVGStylableElement.PresentationAttributeValue.this.value = SVGStylableElement.PresentationAttributeValue.this.cssEngine.parsePropertyValue(SVGStylableElement.this, SVGStylableElement.PresentationAttributeValue.this.property, text);
/*     */               
/* 371 */               SVGStylableElement.PresentationAttributeValue.this.mutate = true;
/* 372 */               SVGStylableElement.this.setAttributeNS(null, SVGStylableElement.PresentationAttributeValue.this.property, text);
/* 373 */               SVGStylableElement.PresentationAttributeValue.this.mutate = false;
/*     */             }
/*     */           });
/*     */       
/* 377 */       this.cssEngine = eng;
/* 378 */       this.property = prop;
/*     */       
/* 380 */       Attr attr = SVGStylableElement.this.getAttributeNodeNS(null, prop);
/* 381 */       if (attr != null) {
/* 382 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, prop, attr.getValue());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 393 */       if (this.value == null) {
/* 394 */         throw new DOMException((short)11, "");
/*     */       }
/* 396 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrAdded(Attr node, String newv) {
/* 405 */       if (!this.mutate) {
/* 406 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrModified(Attr node, String oldv, String newv) {
/* 415 */       if (!this.mutate) {
/* 416 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrRemoved(Attr node, String oldv) {
/* 425 */       if (!this.mutate) {
/* 426 */         this.value = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class PresentationAttributeColorValue
/*     */     extends CSSOMSVGColor
/*     */     implements CSSOMSVGColor.ValueProvider, LiveAttributeValue
/*     */   {
/*     */     protected CSSEngine cssEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Value value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean mutate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PresentationAttributeColorValue(CSSEngine eng, String prop) {
/* 463 */       super(null);
/* 464 */       this.valueProvider = this;
/* 465 */       setModificationHandler((CSSOMSVGColor.ModificationHandler)new CSSOMSVGColor.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 467 */               return SVGStylableElement.PresentationAttributeColorValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 470 */               SVGStylableElement.PresentationAttributeColorValue.this.value = SVGStylableElement.PresentationAttributeColorValue.this.cssEngine.parsePropertyValue(SVGStylableElement.this, SVGStylableElement.PresentationAttributeColorValue.this.property, text);
/*     */               
/* 472 */               SVGStylableElement.PresentationAttributeColorValue.this.mutate = true;
/* 473 */               SVGStylableElement.this.setAttributeNS(null, SVGStylableElement.PresentationAttributeColorValue.this.property, text);
/* 474 */               SVGStylableElement.PresentationAttributeColorValue.this.mutate = false;
/*     */             }
/*     */           });
/*     */       
/* 478 */       this.cssEngine = eng;
/* 479 */       this.property = prop;
/*     */       
/* 481 */       Attr attr = SVGStylableElement.this.getAttributeNodeNS(null, prop);
/* 482 */       if (attr != null) {
/* 483 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, prop, attr.getValue());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 494 */       if (this.value == null) {
/* 495 */         throw new DOMException((short)11, "");
/*     */       }
/* 497 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrAdded(Attr node, String newv) {
/* 506 */       if (!this.mutate) {
/* 507 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrModified(Attr node, String oldv, String newv) {
/* 516 */       if (!this.mutate) {
/* 517 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrRemoved(Attr node, String oldv) {
/* 526 */       if (!this.mutate) {
/* 527 */         this.value = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class PresentationAttributePaintValue
/*     */     extends CSSOMSVGPaint
/*     */     implements CSSOMSVGColor.ValueProvider, LiveAttributeValue
/*     */   {
/*     */     protected CSSEngine cssEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String property;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Value value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean mutate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PresentationAttributePaintValue(CSSEngine eng, String prop) {
/* 564 */       super(null);
/* 565 */       this.valueProvider = this;
/* 566 */       setModificationHandler((CSSOMSVGColor.ModificationHandler)new CSSOMSVGPaint.AbstractModificationHandler() {
/*     */             protected Value getValue() {
/* 568 */               return SVGStylableElement.PresentationAttributePaintValue.this.getValue();
/*     */             }
/*     */             public void textChanged(String text) throws DOMException {
/* 571 */               SVGStylableElement.PresentationAttributePaintValue.this.value = SVGStylableElement.PresentationAttributePaintValue.this.cssEngine.parsePropertyValue(SVGStylableElement.this, SVGStylableElement.PresentationAttributePaintValue.this.property, text);
/*     */               
/* 573 */               SVGStylableElement.PresentationAttributePaintValue.this.mutate = true;
/* 574 */               SVGStylableElement.this.setAttributeNS(null, SVGStylableElement.PresentationAttributePaintValue.this.property, text);
/* 575 */               SVGStylableElement.PresentationAttributePaintValue.this.mutate = false;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 580 */       this.cssEngine = eng;
/* 581 */       this.property = prop;
/*     */       
/* 583 */       Attr attr = SVGStylableElement.this.getAttributeNodeNS(null, prop);
/* 584 */       if (attr != null) {
/* 585 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, prop, attr.getValue());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 596 */       if (this.value == null) {
/* 597 */         throw new DOMException((short)11, "");
/*     */       }
/* 599 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrAdded(Attr node, String newv) {
/* 608 */       if (!this.mutate) {
/* 609 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrModified(Attr node, String oldv, String newv) {
/* 618 */       if (!this.mutate) {
/* 619 */         this.value = this.cssEngine.parsePropertyValue(SVGStylableElement.this, this.property, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrRemoved(Attr node, String oldv) {
/* 628 */       if (!this.mutate) {
/* 629 */         this.value = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class StyleDeclaration
/*     */     extends CSSOMStoredStyleDeclaration
/*     */     implements CSSEngine.MainPropertyReceiver, LiveAttributeValue
/*     */   {
/*     */     protected boolean mutate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StyleDeclaration(CSSEngine eng) {
/* 651 */       super(eng);
/*     */       
/* 653 */       this.declaration = this.cssEngine.parseStyleDeclaration(SVGStylableElement.this, SVGStylableElement.this.getAttributeNS(null, "style"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrAdded(Attr node, String newv) {
/* 664 */       if (!this.mutate) {
/* 665 */         this.declaration = this.cssEngine.parseStyleDeclaration(SVGStylableElement.this, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrModified(Attr node, String oldv, String newv) {
/* 674 */       if (!this.mutate) {
/* 675 */         this.declaration = this.cssEngine.parseStyleDeclaration(SVGStylableElement.this, newv);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void attrRemoved(Attr node, String oldv) {
/* 684 */       if (!this.mutate) {
/* 685 */         this.declaration = new org.apache.batik.css.engine.StyleDeclaration();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void textChanged(String text) throws DOMException {
/* 696 */       this.declaration = this.cssEngine.parseStyleDeclaration(SVGStylableElement.this, text);
/*     */       
/* 698 */       this.mutate = true;
/* 699 */       SVGStylableElement.this.setAttributeNS(null, "style", text);
/* 700 */       this.mutate = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyRemoved(String name) throws DOMException {
/* 707 */       int idx = this.cssEngine.getPropertyIndex(name);
/* 708 */       for (int i = 0; i < this.declaration.size(); i++) {
/* 709 */         if (idx == this.declaration.getIndex(i)) {
/* 710 */           this.declaration.remove(i);
/* 711 */           this.mutate = true;
/* 712 */           SVGStylableElement.this.setAttributeNS(null, "style", this.declaration.toString(this.cssEngine));
/*     */           
/* 714 */           this.mutate = false;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyChanged(String name, String value, String prio) throws DOMException {
/* 725 */       boolean important = (prio != null && prio.length() > 0);
/* 726 */       this.cssEngine.setMainProperties(SVGStylableElement.this, this, name, value, important);
/*     */       
/* 728 */       this.mutate = true;
/* 729 */       SVGStylableElement.this.setAttributeNS(null, "style", this.declaration.toString(this.cssEngine));
/*     */       
/* 731 */       this.mutate = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMainProperty(String name, Value v, boolean important) {
/* 741 */       int idx = this.cssEngine.getPropertyIndex(name);
/* 742 */       if (idx == -1) {
/*     */         return;
/*     */       }
/*     */       int i;
/* 746 */       for (i = 0; i < this.declaration.size() && 
/* 747 */         idx != this.declaration.getIndex(i); i++);
/*     */ 
/*     */       
/* 750 */       if (i < this.declaration.size()) {
/* 751 */         this.declaration.put(i, v, idx, important);
/*     */       } else {
/* 753 */         this.declaration.append(v, idx, important);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class OverrideStyleDeclaration
/*     */     extends CSSOMStoredStyleDeclaration
/*     */   {
/*     */     protected OverrideStyleDeclaration(CSSEngine eng) {
/* 768 */       super(eng);
/* 769 */       this.declaration = new org.apache.batik.css.engine.StyleDeclaration();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void textChanged(String text) throws DOMException {
/* 778 */       ((SVGOMDocument)SVGStylableElement.this.ownerDocument).overrideStyleTextChanged(SVGStylableElement.this, text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyRemoved(String name) throws DOMException {
/* 786 */       ((SVGOMDocument)SVGStylableElement.this.ownerDocument).overrideStylePropertyRemoved(SVGStylableElement.this, name);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyChanged(String name, String value, String prio) throws DOMException {
/* 795 */       ((SVGOMDocument)SVGStylableElement.this.ownerDocument).overrideStylePropertyChanged(SVGStylableElement.this, name, value, prio);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGStylableElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */