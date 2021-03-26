/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableRectValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGOMRect;
/*     */ import org.apache.batik.parser.DefaultNumberListHandler;
/*     */ import org.apache.batik.parser.NumberListHandler;
/*     */ import org.apache.batik.parser.NumberListParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedRect
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedRect
/*     */ {
/*     */   protected BaseSVGRect baseVal;
/*     */   protected AnimSVGRect animVal;
/*     */   protected boolean changing;
/*     */   protected String defaultValue;
/*     */   
/*     */   public SVGOMAnimatedRect(AbstractElement elt, String ns, String ln, String def) {
/*  73 */     super(elt, ns, ln);
/*  74 */     this.defaultValue = def;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getBaseVal() {
/*  81 */     if (this.baseVal == null) {
/*  82 */       this.baseVal = new BaseSVGRect();
/*     */     }
/*  84 */     return (SVGRect)this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getAnimVal() {
/*  91 */     if (this.animVal == null) {
/*  92 */       this.animVal = new AnimSVGRect();
/*     */     }
/*  94 */     return (SVGRect)this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 101 */     if (val == null) {
/* 102 */       this.hasAnimVal = false;
/*     */     } else {
/* 104 */       this.hasAnimVal = true;
/* 105 */       AnimatableRectValue animRect = (AnimatableRectValue)val;
/* 106 */       if (this.animVal == null) {
/* 107 */         this.animVal = new AnimSVGRect();
/*     */       }
/* 109 */       this.animVal.setAnimatedValue(animRect.getX(), animRect.getY(), animRect.getWidth(), animRect.getHeight());
/*     */     } 
/*     */     
/* 112 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/* 119 */     SVGRect r = getBaseVal();
/* 120 */     return (AnimatableValue)new AnimatableRectValue(target, r.getX(), r.getY(), r.getWidth(), r.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 128 */     if (!this.changing && this.baseVal != null) {
/* 129 */       this.baseVal.invalidate();
/*     */     }
/* 131 */     fireBaseAttributeListeners();
/* 132 */     if (!this.hasAnimVal) {
/* 133 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 141 */     if (!this.changing && this.baseVal != null) {
/* 142 */       this.baseVal.invalidate();
/*     */     }
/* 144 */     fireBaseAttributeListeners();
/* 145 */     if (!this.hasAnimVal) {
/* 146 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 154 */     if (!this.changing && this.baseVal != null) {
/* 155 */       this.baseVal.invalidate();
/*     */     }
/* 157 */     fireBaseAttributeListeners();
/* 158 */     if (!this.hasAnimVal) {
/* 159 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class BaseSVGRect
/*     */     extends SVGOMRect
/*     */   {
/*     */     protected boolean valid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void invalidate() {
/* 177 */       this.valid = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void reset() {
/*     */       try {
/* 185 */         SVGOMAnimatedRect.this.changing = true;
/* 186 */         SVGOMAnimatedRect.this.element.setAttributeNS(SVGOMAnimatedRect.this.namespaceURI, SVGOMAnimatedRect.this.localName, Float.toString(this.x) + ' ' + this.y + ' ' + this.w + ' ' + this.h);
/*     */       }
/*     */       finally {
/*     */         
/* 190 */         SVGOMAnimatedRect.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 198 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 202 */       Attr attr = SVGOMAnimatedRect.this.element.getAttributeNodeNS(SVGOMAnimatedRect.this.namespaceURI, SVGOMAnimatedRect.this.localName);
/*     */       
/* 204 */       final String s = (attr == null) ? SVGOMAnimatedRect.this.defaultValue : attr.getValue();
/* 205 */       final float[] numbers = new float[4];
/* 206 */       NumberListParser p = new NumberListParser();
/* 207 */       p.setNumberListHandler((NumberListHandler)new DefaultNumberListHandler()
/*     */           {
/*     */             public void endNumberList() {
/* 210 */               if (this.count != 4) {
/* 211 */                 throw new LiveAttributeException(SVGOMAnimatedRect.this.element, SVGOMAnimatedRect.this.localName, (short)1, s);
/*     */               }
/*     */             }
/*     */             
/*     */             protected int count;
/*     */             
/*     */             public void numberValue(float v) throws ParseException {
/* 218 */               if (this.count < 4) {
/* 219 */                 numbers[this.count] = v;
/*     */               }
/* 221 */               if (v < 0.0F && (this.count == 2 || this.count == 3)) {
/* 222 */                 throw new LiveAttributeException(SVGOMAnimatedRect.this.element, SVGOMAnimatedRect.this.localName, (short)1, s);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 227 */               this.count++;
/*     */             }
/*     */           });
/* 230 */       p.parse(s);
/* 231 */       this.x = numbers[0];
/* 232 */       this.y = numbers[1];
/* 233 */       this.w = numbers[2];
/* 234 */       this.h = numbers[3];
/*     */       
/* 236 */       this.valid = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getX() {
/* 243 */       revalidate();
/* 244 */       return this.x;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setX(float x) throws DOMException {
/* 251 */       this.x = x;
/* 252 */       reset();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getY() {
/* 259 */       revalidate();
/* 260 */       return this.y;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setY(float y) throws DOMException {
/* 267 */       this.y = y;
/* 268 */       reset();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getWidth() {
/* 275 */       revalidate();
/* 276 */       return this.w;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setWidth(float width) throws DOMException {
/* 283 */       this.w = width;
/* 284 */       reset();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getHeight() {
/* 291 */       revalidate();
/* 292 */       return this.h;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHeight(float height) throws DOMException {
/* 299 */       this.h = height;
/* 300 */       reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimSVGRect
/*     */     extends SVGOMRect
/*     */   {
/*     */     public float getX() {
/* 313 */       if (SVGOMAnimatedRect.this.hasAnimVal) {
/* 314 */         return super.getX();
/*     */       }
/* 316 */       return SVGOMAnimatedRect.this.getBaseVal().getX();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getY() {
/* 323 */       if (SVGOMAnimatedRect.this.hasAnimVal) {
/* 324 */         return super.getY();
/*     */       }
/* 326 */       return SVGOMAnimatedRect.this.getBaseVal().getY();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getWidth() {
/* 333 */       if (SVGOMAnimatedRect.this.hasAnimVal) {
/* 334 */         return super.getWidth();
/*     */       }
/* 336 */       return SVGOMAnimatedRect.this.getBaseVal().getWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getHeight() {
/* 343 */       if (SVGOMAnimatedRect.this.hasAnimVal) {
/* 344 */         return super.getHeight();
/*     */       }
/* 346 */       return SVGOMAnimatedRect.this.getBaseVal().getHeight();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setX(float value) throws DOMException {
/* 353 */       throw SVGOMAnimatedRect.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setY(float value) throws DOMException {
/* 362 */       throw SVGOMAnimatedRect.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setWidth(float value) throws DOMException {
/* 371 */       throw SVGOMAnimatedRect.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHeight(float value) throws DOMException {
/* 380 */       throw SVGOMAnimatedRect.this.element.createDOMException((short)7, "readonly.length", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(float x, float y, float w, float h) {
/* 389 */       this.x = x;
/* 390 */       this.y = y;
/* 391 */       this.w = w;
/* 392 */       this.h = h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedRect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */