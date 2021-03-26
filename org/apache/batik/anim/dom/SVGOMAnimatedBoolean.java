/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableBooleanValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedBoolean
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedBoolean
/*     */ {
/*     */   protected boolean defaultValue;
/*     */   protected boolean valid;
/*     */   protected boolean baseVal;
/*     */   protected boolean animVal;
/*     */   protected boolean changing;
/*     */   
/*     */   public SVGOMAnimatedBoolean(AbstractElement elt, String ns, String ln, boolean val) {
/*  74 */     super(elt, ns, ln);
/*  75 */     this.defaultValue = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBaseVal() {
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
/*  96 */       this.baseVal = attr.getValue().equals("true");
/*     */     } 
/*  98 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseVal(boolean baseVal) throws DOMException {
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
/*     */   public boolean getAnimVal() {
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
/*     */   public void setAnimatedValue(boolean animVal) {
/* 133 */     this.hasAnimVal = true;
/* 134 */     this.animVal = animVal;
/* 135 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 142 */     if (val == null) {
/* 143 */       this.hasAnimVal = false;
/*     */     } else {
/* 145 */       this.hasAnimVal = true;
/* 146 */       this.animVal = ((AnimatableBooleanValue)val).getValue();
/*     */     } 
/* 148 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 155 */     return (AnimatableValue)new AnimatableBooleanValue(target, getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 162 */     if (!this.changing) {
/* 163 */       this.valid = false;
/*     */     }
/* 165 */     fireBaseAttributeListeners();
/* 166 */     if (!this.hasAnimVal) {
/* 167 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 175 */     if (!this.changing) {
/* 176 */       this.valid = false;
/*     */     }
/* 178 */     fireBaseAttributeListeners();
/* 179 */     if (!this.hasAnimVal) {
/* 180 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 188 */     if (!this.changing) {
/* 189 */       this.valid = false;
/*     */     }
/* 191 */     fireBaseAttributeListeners();
/* 192 */     if (!this.hasAnimVal)
/* 193 */       fireAnimatedAttributeListeners(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */