/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.anim.values.AnimatableLengthListValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.ListBuilder;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAnimatedLengthList;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGLength;
/*     */ import org.w3c.dom.svg.SVGLengthList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedLengthList
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedLengthList
/*     */ {
/*     */   protected BaseSVGLengthList baseVal;
/*     */   protected AnimSVGLengthList animVal;
/*     */   protected boolean changing;
/*     */   protected String defaultValue;
/*     */   protected boolean emptyAllowed;
/*     */   protected short direction;
/*     */   
/*     */   public SVGOMAnimatedLengthList(AbstractElement elt, String ns, String ln, String defaultValue, boolean emptyAllowed, short direction) {
/*  95 */     super(elt, ns, ln);
/*  96 */     this.defaultValue = defaultValue;
/*  97 */     this.emptyAllowed = emptyAllowed;
/*  98 */     this.direction = direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLengthList getBaseVal() {
/* 105 */     if (this.baseVal == null) {
/* 106 */       this.baseVal = new BaseSVGLengthList();
/*     */     }
/* 108 */     return this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLengthList getAnimVal() {
/* 115 */     if (this.animVal == null) {
/* 116 */       this.animVal = new AnimSVGLengthList();
/*     */     }
/* 118 */     return this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 125 */     if (!this.hasAnimVal) {
/* 126 */       if (this.baseVal == null) {
/* 127 */         this.baseVal = new BaseSVGLengthList();
/*     */       }
/* 129 */       this.baseVal.revalidate();
/* 130 */       if (this.baseVal.missing) {
/* 131 */         throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */       }
/*     */ 
/*     */       
/* 135 */       if (this.baseVal.malformed) {
/* 136 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
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
/* 148 */     SVGLengthList ll = getBaseVal();
/* 149 */     int n = ll.getNumberOfItems();
/* 150 */     short[] types = new short[n];
/* 151 */     float[] values = new float[n];
/* 152 */     for (int i = 0; i < n; i++) {
/* 153 */       SVGLength l = ll.getItem(i);
/* 154 */       types[i] = l.getUnitType();
/* 155 */       values[i] = l.getValueInSpecifiedUnits();
/*     */     } 
/* 157 */     return (AnimatableValue)new AnimatableLengthListValue(target, types, values, target.getPercentageInterpretation(getNamespaceURI(), getLocalName(), false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 167 */     if (val == null) {
/* 168 */       this.hasAnimVal = false;
/*     */     } else {
/* 170 */       this.hasAnimVal = true;
/* 171 */       AnimatableLengthListValue animLengths = (AnimatableLengthListValue)val;
/*     */       
/* 173 */       if (this.animVal == null) {
/* 174 */         this.animVal = new AnimSVGLengthList();
/*     */       }
/* 176 */       this.animVal.setAnimatedValue(animLengths.getLengthTypes(), animLengths.getLengthValues());
/*     */     } 
/*     */     
/* 179 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 186 */     if (!this.changing && this.baseVal != null) {
/* 187 */       this.baseVal.invalidate();
/*     */     }
/* 189 */     fireBaseAttributeListeners();
/* 190 */     if (!this.hasAnimVal) {
/* 191 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 199 */     if (!this.changing && this.baseVal != null) {
/* 200 */       this.baseVal.invalidate();
/*     */     }
/* 202 */     fireBaseAttributeListeners();
/* 203 */     if (!this.hasAnimVal) {
/* 204 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 212 */     if (!this.changing && this.baseVal != null) {
/* 213 */       this.baseVal.invalidate();
/*     */     }
/* 215 */     fireBaseAttributeListeners();
/* 216 */     if (!this.hasAnimVal) {
/* 217 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class BaseSVGLengthList
/*     */     extends AbstractSVGLengthList
/*     */   {
/*     */     protected boolean missing;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean malformed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BaseSVGLengthList() {
/* 240 */       super(SVGOMAnimatedLengthList.this.direction);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 248 */       return SVGOMAnimatedLengthList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 257 */       return ((SVGOMElement)SVGOMAnimatedLengthList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getElement() {
/* 265 */       return (Element)SVGOMAnimatedLengthList.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 272 */       Attr attr = SVGOMAnimatedLengthList.this.element.getAttributeNodeNS(SVGOMAnimatedLengthList.this.namespaceURI, SVGOMAnimatedLengthList.this.localName);
/* 273 */       if (attr == null) {
/* 274 */         return SVGOMAnimatedLengthList.this.defaultValue;
/*     */       }
/* 276 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 284 */         SVGOMAnimatedLengthList.this.changing = true;
/* 285 */         SVGOMAnimatedLengthList.this.element.setAttributeNS(SVGOMAnimatedLengthList.this.namespaceURI, SVGOMAnimatedLengthList.this.localName, value);
/*     */       } finally {
/* 287 */         SVGOMAnimatedLengthList.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 295 */       super.resetAttribute();
/* 296 */       this.missing = false;
/* 297 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {
/* 306 */       super.resetAttribute(item);
/* 307 */       this.missing = false;
/* 308 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 315 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 319 */       this.valid = true;
/* 320 */       this.missing = false;
/* 321 */       this.malformed = false;
/*     */       
/* 323 */       String s = getValueAsString();
/* 324 */       boolean isEmpty = (s != null && s.length() == 0);
/* 325 */       if (s == null || (isEmpty && !SVGOMAnimatedLengthList.this.emptyAllowed)) {
/* 326 */         this.missing = true;
/*     */         return;
/*     */       } 
/* 329 */       if (isEmpty) {
/* 330 */         this.itemList = new ArrayList(1);
/*     */       } else {
/*     */         try {
/* 333 */           ListBuilder builder = new ListBuilder(this);
/*     */           
/* 335 */           doParse(s, (ListHandler)builder);
/*     */           
/* 337 */           if (builder.getList() != null) {
/* 338 */             clear(this.itemList);
/*     */           }
/* 340 */           this.itemList = builder.getList();
/* 341 */         } catch (ParseException e) {
/* 342 */           this.itemList = new ArrayList(1);
/* 343 */           this.valid = true;
/* 344 */           this.malformed = true;
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
/*     */   protected class AnimSVGLengthList
/*     */     extends AbstractSVGLengthList
/*     */   {
/*     */     public AnimSVGLengthList() {
/* 359 */       super(SVGOMAnimatedLengthList.this.direction);
/* 360 */       this.itemList = new ArrayList(1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 368 */       return SVGOMAnimatedLengthList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 377 */       return ((SVGOMElement)SVGOMAnimatedLengthList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getElement() {
/* 384 */       return (Element)SVGOMAnimatedLengthList.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumberOfItems() {
/* 391 */       if (SVGOMAnimatedLengthList.this.hasAnimVal) {
/* 392 */         return super.getNumberOfItems();
/*     */       }
/* 394 */       return SVGOMAnimatedLengthList.this.getBaseVal().getNumberOfItems();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength getItem(int index) throws DOMException {
/* 401 */       if (SVGOMAnimatedLengthList.this.hasAnimVal) {
/* 402 */         return super.getItem(index);
/*     */       }
/* 404 */       return SVGOMAnimatedLengthList.this.getBaseVal().getItem(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 411 */       if (this.itemList.size() == 0) {
/* 412 */         return "";
/*     */       }
/* 414 */       StringBuffer sb = new StringBuffer(this.itemList.size() * 8);
/* 415 */       Iterator<SVGItem> i = this.itemList.iterator();
/* 416 */       if (i.hasNext()) {
/* 417 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       }
/* 419 */       while (i.hasNext()) {
/* 420 */         sb.append(getItemSeparator());
/* 421 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       } 
/* 423 */       return sb.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() throws DOMException {
/* 436 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength initialize(SVGLength newItem) throws DOMException, SVGException {
/* 446 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength insertItemBefore(SVGLength newItem, int index) throws DOMException, SVGException {
/* 457 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength replaceItem(SVGLength newItem, int index) throws DOMException, SVGException {
/* 468 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength removeItem(int index) throws DOMException {
/* 477 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGLength appendItem(SVGLength newItem) throws DOMException {
/* 486 */       throw SVGOMAnimatedLengthList.this.element.createDOMException((short)7, "readonly.length.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(short[] types, float[] values) {
/* 495 */       int size = this.itemList.size();
/* 496 */       int i = 0;
/* 497 */       while (i < size && i < types.length) {
/* 498 */         AbstractSVGLengthList.SVGLengthItem l = this.itemList.get(i);
/* 499 */         l.unitType = types[i];
/* 500 */         l.value = values[i];
/* 501 */         l.direction = this.direction;
/* 502 */         i++;
/*     */       } 
/* 504 */       while (i < types.length) {
/* 505 */         appendItemImpl(new AbstractSVGLengthList.SVGLengthItem(this, types[i], values[i], this.direction));
/*     */         
/* 507 */         i++;
/*     */       } 
/* 509 */       while (size > types.length) {
/* 510 */         removeItemImpl(--size);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 533 */       this.valid = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedLengthList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */