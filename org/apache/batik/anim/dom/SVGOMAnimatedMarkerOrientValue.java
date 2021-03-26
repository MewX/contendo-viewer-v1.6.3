/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.SVGOMAngle;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAngle;
/*     */ import org.w3c.dom.svg.SVGAnimatedAngle;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedMarkerOrientValue
/*     */   extends AbstractSVGAnimatedValue
/*     */ {
/*     */   protected boolean valid;
/*  50 */   protected AnimatedAngle animatedAngle = new AnimatedAngle();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected AnimatedEnumeration animatedEnumeration = new AnimatedEnumeration();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseSVGAngle baseAngleVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short baseEnumerationVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimSVGAngle animAngleVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short animEnumerationVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean changing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMAnimatedMarkerOrientValue(AbstractElement elt, String ns, String ln) {
/*  92 */     super(elt, ns, ln);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 100 */     throw new UnsupportedOperationException("Animation of marker orient value is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 109 */     throw new UnsupportedOperationException("Animation of marker orient value is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 117 */     if (!this.changing) {
/* 118 */       this.valid = false;
/*     */     }
/* 120 */     fireBaseAttributeListeners();
/* 121 */     if (!this.hasAnimVal) {
/* 122 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 130 */     if (!this.changing) {
/* 131 */       this.valid = false;
/*     */     }
/* 133 */     fireBaseAttributeListeners();
/* 134 */     if (!this.hasAnimVal) {
/* 135 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 143 */     if (!this.changing) {
/* 144 */       this.valid = false;
/*     */     }
/* 146 */     fireBaseAttributeListeners();
/* 147 */     if (!this.hasAnimVal) {
/* 148 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnimatedValueToAngle(short unitType, float value) {
/* 156 */     this.hasAnimVal = true;
/* 157 */     this.animAngleVal.setAnimatedValue(unitType, value);
/* 158 */     this.animEnumerationVal = 2;
/* 159 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnimatedValueToAuto() {
/* 166 */     this.hasAnimVal = true;
/* 167 */     this.animAngleVal.setAnimatedValue(1, 0.0F);
/* 168 */     this.animEnumerationVal = 1;
/* 169 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetAnimatedValue() {
/* 176 */     this.hasAnimVal = false;
/* 177 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedAngle getAnimatedAngle() {
/* 184 */     return this.animatedAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getAnimatedEnumeration() {
/* 191 */     return this.animatedEnumeration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class BaseSVGAngle
/*     */     extends SVGOMAngle
/*     */   {
/*     */     public void invalidate() {
/* 204 */       SVGOMAnimatedMarkerOrientValue.this.valid = false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void reset() {
/*     */       try {
/*     */         String value;
/* 212 */         SVGOMAnimatedMarkerOrientValue.this.changing = true;
/* 213 */         SVGOMAnimatedMarkerOrientValue.this.valid = true;
/*     */         
/* 215 */         if (SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal == 2) {
/*     */           
/* 217 */           value = getValueAsString();
/* 218 */         } else if (SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal == 1) {
/*     */           
/* 220 */           value = "auto";
/*     */         } else {
/*     */           return;
/*     */         } 
/* 224 */         SVGOMAnimatedMarkerOrientValue.this.element.setAttributeNS(SVGOMAnimatedMarkerOrientValue.this.namespaceURI, SVGOMAnimatedMarkerOrientValue.this.localName, value);
/*     */       } finally {
/* 226 */         SVGOMAnimatedMarkerOrientValue.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 234 */       if (!SVGOMAnimatedMarkerOrientValue.this.valid) {
/* 235 */         Attr attr = SVGOMAnimatedMarkerOrientValue.this.element.getAttributeNodeNS(SVGOMAnimatedMarkerOrientValue.this.namespaceURI, SVGOMAnimatedMarkerOrientValue.this.localName);
/* 236 */         if (attr == null) {
/* 237 */           setUnitType((short)1);
/* 238 */           this.value = 0.0F;
/*     */         } else {
/* 240 */           parse(attr.getValue());
/*     */         } 
/* 242 */         SVGOMAnimatedMarkerOrientValue.this.valid = true;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void parse(String s) {
/* 251 */       if (s.equals("auto")) {
/* 252 */         setUnitType((short)1);
/* 253 */         this.value = 0.0F;
/* 254 */         SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal = 1;
/*     */       } else {
/* 256 */         super.parse(s);
/* 257 */         if (getUnitType() == 0) {
/* 258 */           SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal = 0;
/*     */         } else {
/* 260 */           SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal = 2;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimSVGAngle
/*     */     extends SVGOMAngle
/*     */   {
/*     */     public short getUnitType() {
/* 275 */       if (SVGOMAnimatedMarkerOrientValue.this.hasAnimVal) {
/* 276 */         return super.getUnitType();
/*     */       }
/* 278 */       return SVGOMAnimatedMarkerOrientValue.this.animatedAngle.getBaseVal().getUnitType();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getValue() {
/* 285 */       if (SVGOMAnimatedMarkerOrientValue.this.hasAnimVal) {
/* 286 */         return super.getValue();
/*     */       }
/* 288 */       return SVGOMAnimatedMarkerOrientValue.this.animatedAngle.getBaseVal().getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getValueInSpecifiedUnits() {
/* 295 */       if (SVGOMAnimatedMarkerOrientValue.this.hasAnimVal) {
/* 296 */         return super.getValueInSpecifiedUnits();
/*     */       }
/* 298 */       return SVGOMAnimatedMarkerOrientValue.this.animatedAngle.getBaseVal().getValueInSpecifiedUnits();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getValueAsString() {
/* 305 */       if (SVGOMAnimatedMarkerOrientValue.this.hasAnimVal) {
/* 306 */         return super.getValueAsString();
/*     */       }
/* 308 */       return SVGOMAnimatedMarkerOrientValue.this.animatedAngle.getBaseVal().getValueAsString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValue(float value) throws DOMException {
/* 315 */       throw SVGOMAnimatedMarkerOrientValue.this.element.createDOMException((short)7, "readonly.angle", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValueInSpecifiedUnits(float value) throws DOMException {
/* 325 */       throw SVGOMAnimatedMarkerOrientValue.this.element.createDOMException((short)7, "readonly.angle", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setValueAsString(String value) throws DOMException {
/* 334 */       throw SVGOMAnimatedMarkerOrientValue.this.element.createDOMException((short)7, "readonly.angle", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void newValueSpecifiedUnits(short unit, float value) {
/* 344 */       throw SVGOMAnimatedMarkerOrientValue.this.element.createDOMException((short)7, "readonly.angle", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void convertToSpecifiedUnits(short unit) {
/* 354 */       throw SVGOMAnimatedMarkerOrientValue.this.element.createDOMException((short)7, "readonly.angle", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(int type, float val) {
/* 363 */       super.newValueSpecifiedUnits((short)type, val);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimatedAngle
/*     */     implements SVGAnimatedAngle
/*     */   {
/*     */     public SVGAngle getBaseVal() {
/* 376 */       if (SVGOMAnimatedMarkerOrientValue.this.baseAngleVal == null) {
/* 377 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal = new SVGOMAnimatedMarkerOrientValue.BaseSVGAngle();
/*     */       }
/* 379 */       return (SVGAngle)SVGOMAnimatedMarkerOrientValue.this.baseAngleVal;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGAngle getAnimVal() {
/* 386 */       if (SVGOMAnimatedMarkerOrientValue.this.animAngleVal == null) {
/* 387 */         SVGOMAnimatedMarkerOrientValue.this.animAngleVal = new SVGOMAnimatedMarkerOrientValue.AnimSVGAngle();
/*     */       }
/* 389 */       return (SVGAngle)SVGOMAnimatedMarkerOrientValue.this.animAngleVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimatedEnumeration
/*     */     implements SVGAnimatedEnumeration
/*     */   {
/*     */     public short getBaseVal() {
/* 402 */       if (SVGOMAnimatedMarkerOrientValue.this.baseAngleVal == null) {
/* 403 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal = new SVGOMAnimatedMarkerOrientValue.BaseSVGAngle();
/*     */       }
/* 405 */       SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.revalidate();
/* 406 */       return SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setBaseVal(short baseVal) throws DOMException {
/* 414 */       if (baseVal == 1) {
/* 415 */         SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal = baseVal;
/* 416 */         if (SVGOMAnimatedMarkerOrientValue.this.baseAngleVal == null) {
/* 417 */           SVGOMAnimatedMarkerOrientValue.this.baseAngleVal = new SVGOMAnimatedMarkerOrientValue.BaseSVGAngle();
/*     */         }
/* 419 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.setUnitType((short)1);
/* 420 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.setValue(0.0F);
/* 421 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.reset();
/* 422 */       } else if (baseVal == 2) {
/* 423 */         SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal = baseVal;
/* 424 */         if (SVGOMAnimatedMarkerOrientValue.this.baseAngleVal == null) {
/* 425 */           SVGOMAnimatedMarkerOrientValue.this.baseAngleVal = new SVGOMAnimatedMarkerOrientValue.BaseSVGAngle();
/*     */         }
/* 427 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.reset();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public short getAnimVal() {
/* 435 */       if (SVGOMAnimatedMarkerOrientValue.this.hasAnimVal) {
/* 436 */         return SVGOMAnimatedMarkerOrientValue.this.animEnumerationVal;
/*     */       }
/* 438 */       if (SVGOMAnimatedMarkerOrientValue.this.baseAngleVal == null) {
/* 439 */         SVGOMAnimatedMarkerOrientValue.this.baseAngleVal = new SVGOMAnimatedMarkerOrientValue.BaseSVGAngle();
/*     */       }
/* 441 */       SVGOMAnimatedMarkerOrientValue.this.baseAngleVal.revalidate();
/* 442 */       return SVGOMAnimatedMarkerOrientValue.this.baseEnumerationVal;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedMarkerOrientValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */