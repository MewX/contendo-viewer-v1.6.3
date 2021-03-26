/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableLengthValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.LiveAttributeValue;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGAnimatedLength
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements LiveAttributeValue, SVGAnimatedLength
/*     */ {
/*     */   public static final short HORIZONTAL_LENGTH = 2;
/*     */   public static final short VERTICAL_LENGTH = 1;
/*     */   public static final short OTHER_LENGTH = 0;
/*     */   protected short direction;
/*     */   protected BaseSVGLength baseVal;
/*     */   protected AnimSVGLength animVal;
/*     */   protected boolean changing;
/*     */   protected boolean nonNegative;
/*     */   
/*     */   public AbstractSVGAnimatedLength(AbstractElement elt, String ns, String ln, short dir, boolean nonneg) {
/* 100 */     super(elt, ns, ln);
/* 101 */     this.direction = dir;
/* 102 */     this.nonNegative = nonneg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getDefaultValue();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength getBaseVal() {
/* 115 */     if (this.baseVal == null) {
/* 116 */       this.baseVal = new BaseSVGLength(this.direction);
/*     */     }
/* 118 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength getAnimVal() {
/* 125 */     if (this.animVal == null) {
/* 126 */       this.animVal = new AnimSVGLength(this.direction);
/*     */     }
/* 128 */     return this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCheckedValue() {
/* 136 */     if (this.hasAnimVal) {
/* 137 */       if (this.animVal == null) {
/* 138 */         this.animVal = new AnimSVGLength(this.direction);
/*     */       }
/* 140 */       if (this.nonNegative && this.animVal.value < 0.0F) {
/* 141 */         throw new LiveAttributeException(this.element, this.localName, (short)2, this.animVal.getValueAsString());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 146 */       return this.animVal.getValue();
/*     */     } 
/* 148 */     if (this.baseVal == null) {
/* 149 */       this.baseVal = new BaseSVGLength(this.direction);
/*     */     }
/* 151 */     this.baseVal.revalidate();
/* 152 */     if (this.baseVal.missing) {
/* 153 */       throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */     }
/*     */     
/* 156 */     if (this.baseVal.unitType == 0)
/*     */     {
/* 158 */       throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (this.nonNegative && this.baseVal.value < 0.0F) {
/* 164 */       throw new LiveAttributeException(this.element, this.localName, (short)2, this.baseVal.getValueAsString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 169 */     return this.baseVal.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 177 */     if (val == null) {
/* 178 */       this.hasAnimVal = false;
/*     */     } else {
/* 180 */       this.hasAnimVal = true;
/* 181 */       AnimatableLengthValue animLength = (AnimatableLengthValue)val;
/* 182 */       if (this.animVal == null) {
/* 183 */         this.animVal = new AnimSVGLength(this.direction);
/*     */       }
/* 185 */       this.animVal.setAnimatedValue(animLength.getLengthType(), animLength.getLengthValue());
/*     */     } 
/*     */     
/* 188 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 195 */     SVGLength base = getBaseVal();
/* 196 */     return (AnimatableValue)new AnimatableLengthValue(target, base.getUnitType(), base.getValueInSpecifiedUnits(), target.getPercentageInterpretation(getNamespaceURI(), getLocalName(), false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 206 */     attrChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 213 */     attrChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 220 */     attrChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attrChanged() {
/* 227 */     if (!this.changing && this.baseVal != null) {
/* 228 */       this.baseVal.invalidate();
/*     */     }
/* 230 */     fireBaseAttributeListeners();
/* 231 */     if (!this.hasAnimVal) {
/* 232 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class BaseSVGLength
/*     */     extends AbstractSVGLength
/*     */   {
/*     */     protected boolean valid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean missing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BaseSVGLength(short direction) {
/* 256 */       super(direction);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void invalidate() {
/* 263 */       this.valid = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void reset() {
/*     */       try {
/* 271 */         AbstractSVGAnimatedLength.this.changing = true;
/* 272 */         this.valid = true;
/* 273 */         String value = getValueAsString();
/* 274 */         AbstractSVGAnimatedLength.this.element.setAttributeNS(AbstractSVGAnimatedLength.this.namespaceURI, AbstractSVGAnimatedLength.this.localName, value);
/*     */       } finally {
/* 276 */         AbstractSVGAnimatedLength.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/*     */       String s;
/* 284 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 288 */       this.missing = false;
/* 289 */       this.valid = true;
/*     */       
/* 291 */       Attr attr = AbstractSVGAnimatedLength.this.element.getAttributeNodeNS(AbstractSVGAnimatedLength.this.namespaceURI, AbstractSVGAnimatedLength.this.localName);
/*     */       
/* 293 */       if (attr == null) {
/* 294 */         s = AbstractSVGAnimatedLength.this.getDefaultValue();
/* 295 */         if (s == null) {
/* 296 */           this.missing = true;
/*     */           return;
/*     */         } 
/*     */       } else {
/* 300 */         s = attr.getValue();
/*     */       } 
/*     */       
/* 303 */       parse(s);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGOMElement getAssociatedElement() {
/* 310 */       return (SVGOMElement)AbstractSVGAnimatedLength.this.element;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimSVGLength
/*     */     extends AbstractSVGLength
/*     */   {
/*     */     public AnimSVGLength(short direction) {
/* 324 */       super(direction);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public short getUnitType() {
/* 331 */       if (AbstractSVGAnimatedLength.this.hasAnimVal) {
/* 332 */         return super.getUnitType();
/*     */       }
/* 334 */       return AbstractSVGAnimatedLength.this.getBaseVal().getUnitType();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getValue() {
/* 341 */       if (AbstractSVGAnimatedLength.this.hasAnimVal) {
/* 342 */         return super.getValue();
/*     */       }
/* 344 */       return AbstractSVGAnimatedLength.this.getBaseVal().getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getValueInSpecifiedUnits() {
/* 351 */       if (AbstractSVGAnimatedLength.this.hasAnimVal) {
/* 352 */         return super.getValueInSpecifiedUnits();
/*     */       }
/* 354 */       return AbstractSVGAnimatedLength.this.getBaseVal().getValueInSpecifiedUnits();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getValueAsString() {
/* 361 */       if (AbstractSVGAnimatedLength.this.hasAnimVal) {
/* 362 */         return super.getValueAsString();
/*     */       }
/* 364 */       return AbstractSVGAnimatedLength.this.getBaseVal().getValueAsString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValue(float value) throws DOMException {
/* 371 */       throw AbstractSVGAnimatedLength.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValueInSpecifiedUnits(float value) throws DOMException {
/* 381 */       throw AbstractSVGAnimatedLength.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValueAsString(String value) throws DOMException {
/* 390 */       throw AbstractSVGAnimatedLength.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void newValueSpecifiedUnits(short unit, float value) {
/* 400 */       throw AbstractSVGAnimatedLength.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void convertToSpecifiedUnits(short unit) {
/* 410 */       throw AbstractSVGAnimatedLength.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGOMElement getAssociatedElement() {
/* 419 */       return (SVGOMElement)AbstractSVGAnimatedLength.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(int type, float val) {
/* 428 */       super.newValueSpecifiedUnits((short)type, val);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AbstractSVGAnimatedLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */