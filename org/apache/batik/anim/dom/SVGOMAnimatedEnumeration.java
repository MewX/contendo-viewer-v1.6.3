/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableStringValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedEnumeration
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedEnumeration
/*     */ {
/*     */   protected String[] values;
/*     */   protected short defaultValue;
/*     */   protected boolean valid;
/*     */   protected short baseVal;
/*     */   protected short animVal;
/*     */   protected boolean changing;
/*     */   
/*     */   public SVGOMAnimatedEnumeration(AbstractElement elt, String ns, String ln, String[] val, short def) {
/*  82 */     super(elt, ns, ln);
/*  83 */     this.values = val;
/*  84 */     this.defaultValue = def;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getBaseVal() {
/*  91 */     if (!this.valid) {
/*  92 */       update();
/*     */     }
/*  94 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseValAsString() {
/* 101 */     if (!this.valid) {
/* 102 */       update();
/*     */     }
/* 104 */     return this.values[this.baseVal];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void update() {
/* 111 */     String val = this.element.getAttributeNS(this.namespaceURI, this.localName);
/* 112 */     if (val.length() == 0) {
/* 113 */       this.baseVal = this.defaultValue;
/*     */     } else {
/* 115 */       this.baseVal = getEnumerationNumber(val);
/*     */     } 
/* 117 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short getEnumerationNumber(String s) {
/* 124 */     for (short i = 0; i < this.values.length; i = (short)(i + 1)) {
/* 125 */       if (s.equals(this.values[i])) {
/* 126 */         return i;
/*     */       }
/*     */     } 
/* 129 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseVal(short baseVal) throws DOMException {
/* 137 */     if (baseVal >= 0 && baseVal < this.values.length) {
/*     */       try {
/* 139 */         this.baseVal = baseVal;
/* 140 */         this.valid = true;
/* 141 */         this.changing = true;
/* 142 */         this.element.setAttributeNS(this.namespaceURI, this.localName, this.values[baseVal]);
/*     */       } finally {
/*     */         
/* 145 */         this.changing = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAnimVal() {
/* 154 */     if (this.hasAnimVal) {
/* 155 */       return this.animVal;
/*     */     }
/* 157 */     if (!this.valid) {
/* 158 */       update();
/*     */     }
/* 160 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCheckedVal() {
/* 168 */     if (this.hasAnimVal) {
/* 169 */       return this.animVal;
/*     */     }
/* 171 */     if (!this.valid) {
/* 172 */       update();
/*     */     }
/* 174 */     if (this.baseVal == 0) {
/* 175 */       throw new LiveAttributeException(this.element, this.localName, (short)1, getBaseValAsString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 180 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 187 */     return (AnimatableValue)new AnimatableStringValue(target, getBaseValAsString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
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
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 207 */     if (val == null) {
/* 208 */       this.hasAnimVal = false;
/*     */     } else {
/* 210 */       this.hasAnimVal = true;
/* 211 */       this.animVal = getEnumerationNumber(((AnimatableStringValue)val).getString());
/*     */       
/* 213 */       fireAnimatedAttributeListeners();
/*     */     } 
/* 215 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 222 */     if (!this.changing) {
/* 223 */       this.valid = false;
/*     */     }
/* 225 */     fireBaseAttributeListeners();
/* 226 */     if (!this.hasAnimVal) {
/* 227 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 235 */     if (!this.changing) {
/* 236 */       this.valid = false;
/*     */     }
/* 238 */     fireBaseAttributeListeners();
/* 239 */     if (!this.hasAnimVal)
/* 240 */       fireAnimatedAttributeListeners(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedEnumeration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */