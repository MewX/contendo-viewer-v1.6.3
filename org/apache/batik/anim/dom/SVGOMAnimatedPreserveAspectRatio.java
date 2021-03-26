/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatablePreserveAspectRatioValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGPreserveAspectRatio;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGPreserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedPreserveAspectRatio
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedPreserveAspectRatio
/*     */ {
/*     */   protected BaseSVGPARValue baseVal;
/*     */   protected AnimSVGPARValue animVal;
/*     */   protected boolean changing;
/*     */   
/*     */   public SVGOMAnimatedPreserveAspectRatio(AbstractElement elt) {
/*  63 */     super(elt, null, "preserveAspectRatio");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPreserveAspectRatio getBaseVal() {
/*  70 */     if (this.baseVal == null) {
/*  71 */       this.baseVal = new BaseSVGPARValue();
/*     */     }
/*  73 */     return (SVGPreserveAspectRatio)this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPreserveAspectRatio getAnimVal() {
/*  80 */     if (this.animVal == null) {
/*  81 */       this.animVal = new AnimSVGPARValue();
/*     */     }
/*  83 */     return (SVGPreserveAspectRatio)this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/*  91 */     if (!this.hasAnimVal) {
/*  92 */       if (this.baseVal == null) {
/*  93 */         this.baseVal = new BaseSVGPARValue();
/*     */       }
/*  95 */       if (this.baseVal.malformed) {
/*  96 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
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
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 108 */     SVGPreserveAspectRatio par = getBaseVal();
/* 109 */     return (AnimatableValue)new AnimatablePreserveAspectRatioValue(target, par.getAlign(), par.getMeetOrSlice());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 117 */     if (val == null) {
/* 118 */       this.hasAnimVal = false;
/*     */     } else {
/* 120 */       this.hasAnimVal = true;
/* 121 */       if (this.animVal == null) {
/* 122 */         this.animVal = new AnimSVGPARValue();
/*     */       }
/* 124 */       AnimatablePreserveAspectRatioValue animPAR = (AnimatablePreserveAspectRatioValue)val;
/*     */       
/* 126 */       this.animVal.setAnimatedValue(animPAR.getAlign(), animPAR.getMeetOrSlice());
/*     */     } 
/*     */     
/* 129 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 136 */     if (!this.changing && this.baseVal != null) {
/* 137 */       this.baseVal.invalidate();
/*     */     }
/* 139 */     fireBaseAttributeListeners();
/* 140 */     if (!this.hasAnimVal) {
/* 141 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 149 */     if (!this.changing && this.baseVal != null) {
/* 150 */       this.baseVal.invalidate();
/*     */     }
/* 152 */     fireBaseAttributeListeners();
/* 153 */     if (!this.hasAnimVal) {
/* 154 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 162 */     if (!this.changing && this.baseVal != null) {
/* 163 */       this.baseVal.invalidate();
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
/*     */ 
/*     */   
/*     */   public class BaseSVGPARValue
/*     */     extends AbstractSVGPreserveAspectRatio
/*     */   {
/*     */     protected boolean malformed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BaseSVGPARValue() {
/* 186 */       invalidate();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 194 */       return SVGOMAnimatedPreserveAspectRatio.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) throws DOMException {
/*     */       try {
/* 202 */         SVGOMAnimatedPreserveAspectRatio.this.changing = true;
/* 203 */         SVGOMAnimatedPreserveAspectRatio.this.element.setAttributeNS(null, "preserveAspectRatio", value);
/*     */ 
/*     */         
/* 206 */         this.malformed = false;
/*     */       } finally {
/* 208 */         SVGOMAnimatedPreserveAspectRatio.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void invalidate() {
/* 216 */       String s = SVGOMAnimatedPreserveAspectRatio.this.element.getAttributeNS(null, "preserveAspectRatio");
/*     */       
/* 218 */       setValueAsString(s);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class AnimSVGPARValue
/*     */     extends AbstractSVGPreserveAspectRatio
/*     */   {
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 233 */       return SVGOMAnimatedPreserveAspectRatio.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) throws DOMException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public short getAlign() {
/* 247 */       if (SVGOMAnimatedPreserveAspectRatio.this.hasAnimVal) {
/* 248 */         return super.getAlign();
/*     */       }
/* 250 */       return SVGOMAnimatedPreserveAspectRatio.this.getBaseVal().getAlign();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public short getMeetOrSlice() {
/* 257 */       if (SVGOMAnimatedPreserveAspectRatio.this.hasAnimVal) {
/* 258 */         return super.getMeetOrSlice();
/*     */       }
/* 260 */       return SVGOMAnimatedPreserveAspectRatio.this.getBaseVal().getMeetOrSlice();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setAlign(short align) {
/* 267 */       throw SVGOMAnimatedPreserveAspectRatio.this.element.createDOMException((short)7, "readonly.preserve.aspect.ratio", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMeetOrSlice(short meetOrSlice) {
/* 276 */       throw SVGOMAnimatedPreserveAspectRatio.this.element.createDOMException((short)7, "readonly.preserve.aspect.ratio", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(short align, short meetOrSlice) {
/* 285 */       this.align = align;
/* 286 */       this.meetOrSlice = meetOrSlice;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedPreserveAspectRatio.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */