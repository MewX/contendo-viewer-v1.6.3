/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableIntegerValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedInteger
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedInteger
/*     */ {
/*     */   protected int defaultValue;
/*     */   protected boolean valid;
/*     */   protected int baseVal;
/*     */   protected int animVal;
/*     */   protected boolean changing;
/*     */   
/*     */   public SVGOMAnimatedInteger(AbstractElement elt, String ns, String ln, int val) {
/*  74 */     super(elt, ns, ln);
/*  75 */     this.defaultValue = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBaseVal() {
/*  82 */     if (!this.valid) {
/*  83 */       update();
/*     */     }
/*  85 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void update() {
/*  92 */     Attr attr = this.element.getAttributeNodeNS(this.namespaceURI, this.localName);
/*  93 */     if (attr == null) {
/*  94 */       this.baseVal = this.defaultValue;
/*     */     } else {
/*  96 */       this.baseVal = Integer.parseInt(attr.getValue());
/*     */     } 
/*  98 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseVal(int baseVal) throws DOMException {
/*     */     try {
/* 106 */       this.baseVal = baseVal;
/* 107 */       this.valid = true;
/* 108 */       this.changing = true;
/* 109 */       this.element.setAttributeNS(this.namespaceURI, this.localName, String.valueOf(baseVal));
/*     */     } finally {
/*     */       
/* 112 */       this.changing = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimVal() {
/* 120 */     if (this.hasAnimVal) {
/* 121 */       return this.animVal;
/*     */     }
/* 123 */     if (!this.valid) {
/* 124 */       update();
/*     */     }
/* 126 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 133 */     return (AnimatableValue)new AnimatableIntegerValue(target, getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 140 */     if (val == null) {
/* 141 */       this.hasAnimVal = false;
/*     */     } else {
/* 143 */       this.hasAnimVal = true;
/* 144 */       this.animVal = ((AnimatableIntegerValue)val).getValue();
/*     */     } 
/* 146 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 153 */     if (!this.changing) {
/* 154 */       this.valid = false;
/*     */     }
/* 156 */     fireBaseAttributeListeners();
/* 157 */     if (!this.hasAnimVal) {
/* 158 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 166 */     if (!this.changing) {
/* 167 */       this.valid = false;
/*     */     }
/* 169 */     fireBaseAttributeListeners();
/* 170 */     if (!this.hasAnimVal) {
/* 171 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 179 */     if (!this.changing) {
/* 180 */       this.valid = false;
/*     */     }
/* 182 */     fireBaseAttributeListeners();
/* 183 */     if (!this.hasAnimVal)
/* 184 */       fireAnimatedAttributeListeners(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedInteger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */