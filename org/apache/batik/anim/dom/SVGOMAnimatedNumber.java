/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableNumberValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedNumber
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedNumber
/*     */ {
/*     */   protected float defaultValue;
/*     */   protected boolean allowPercentage;
/*     */   protected boolean valid;
/*     */   protected float baseVal;
/*     */   protected float animVal;
/*     */   protected boolean changing;
/*     */   
/*     */   public SVGOMAnimatedNumber(AbstractElement elt, String ns, String ln, float val) {
/*  79 */     this(elt, ns, ln, val, false);
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
/*     */   public SVGOMAnimatedNumber(AbstractElement elt, String ns, String ln, float val, boolean allowPercentage) {
/*  95 */     super(elt, ns, ln);
/*  96 */     this.defaultValue = val;
/*  97 */     this.allowPercentage = allowPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBaseVal() {
/* 104 */     if (!this.valid) {
/* 105 */       update();
/*     */     }
/* 107 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void update() {
/* 114 */     Attr attr = this.element.getAttributeNodeNS(this.namespaceURI, this.localName);
/* 115 */     if (attr == null) {
/* 116 */       this.baseVal = this.defaultValue;
/*     */     } else {
/* 118 */       String v = attr.getValue();
/* 119 */       int len = v.length();
/* 120 */       if (this.allowPercentage && len > 1 && v.charAt(len - 1) == '%') {
/* 121 */         this.baseVal = 0.01F * Float.parseFloat(v.substring(0, len - 1));
/*     */       } else {
/* 123 */         this.baseVal = Float.parseFloat(v);
/*     */       } 
/*     */     } 
/* 126 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseVal(float baseVal) throws DOMException {
/*     */     try {
/* 134 */       this.baseVal = baseVal;
/* 135 */       this.valid = true;
/* 136 */       this.changing = true;
/* 137 */       this.element.setAttributeNS(this.namespaceURI, this.localName, String.valueOf(baseVal));
/*     */     } finally {
/*     */       
/* 140 */       this.changing = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAnimVal() {
/* 148 */     if (this.hasAnimVal) {
/* 149 */       return this.animVal;
/*     */     }
/* 151 */     if (!this.valid) {
/* 152 */       update();
/*     */     }
/* 154 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 161 */     return (AnimatableValue)new AnimatableNumberValue(target, getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 168 */     if (val == null) {
/* 169 */       this.hasAnimVal = false;
/*     */     } else {
/* 171 */       this.hasAnimVal = true;
/* 172 */       this.animVal = ((AnimatableNumberValue)val).getValue();
/*     */     } 
/* 174 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 181 */     if (!this.changing) {
/* 182 */       this.valid = false;
/*     */     }
/* 184 */     fireBaseAttributeListeners();
/* 185 */     if (!this.hasAnimVal) {
/* 186 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 194 */     if (!this.changing) {
/* 195 */       this.valid = false;
/*     */     }
/* 197 */     fireBaseAttributeListeners();
/* 198 */     if (!this.hasAnimVal) {
/* 199 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 207 */     if (!this.changing) {
/* 208 */       this.valid = false;
/*     */     }
/* 210 */     fireBaseAttributeListeners();
/* 211 */     if (!this.hasAnimVal)
/* 212 */       fireAnimatedAttributeListeners(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */