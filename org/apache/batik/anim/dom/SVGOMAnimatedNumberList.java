/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.anim.values.AnimatableNumberListValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGList;
/*     */ import org.apache.batik.dom.svg.AbstractSVGNumberList;
/*     */ import org.apache.batik.dom.svg.ListBuilder;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.dom.svg.SVGNumberItem;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumberList;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGNumber;
/*     */ import org.w3c.dom.svg.SVGNumberList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedNumberList
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedNumberList
/*     */ {
/*     */   protected BaseSVGNumberList baseVal;
/*     */   protected AnimSVGNumberList animVal;
/*     */   protected boolean changing;
/*     */   protected String defaultValue;
/*     */   protected boolean emptyAllowed;
/*     */   
/*     */   public SVGOMAnimatedNumberList(AbstractElement elt, String ns, String ln, String defaultValue, boolean emptyAllowed) {
/*  91 */     super(elt, ns, ln);
/*  92 */     this.defaultValue = defaultValue;
/*  93 */     this.emptyAllowed = emptyAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumberList getBaseVal() {
/* 100 */     if (this.baseVal == null) {
/* 101 */       this.baseVal = new BaseSVGNumberList();
/*     */     }
/* 103 */     return (SVGNumberList)this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumberList getAnimVal() {
/* 110 */     if (this.animVal == null) {
/* 111 */       this.animVal = new AnimSVGNumberList();
/*     */     }
/* 113 */     return (SVGNumberList)this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 120 */     if (!this.hasAnimVal) {
/* 121 */       if (this.baseVal == null) {
/* 122 */         this.baseVal = new BaseSVGNumberList();
/*     */       }
/* 124 */       this.baseVal.revalidate();
/* 125 */       if (this.baseVal.missing) {
/* 126 */         throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */       }
/*     */ 
/*     */       
/* 130 */       if (this.baseVal.malformed) {
/* 131 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
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
/* 143 */     SVGNumberList nl = getBaseVal();
/* 144 */     int n = nl.getNumberOfItems();
/* 145 */     float[] numbers = new float[n];
/* 146 */     for (int i = 0; i < n; i++) {
/* 147 */       numbers[i] = nl.getItem(n).getValue();
/*     */     }
/* 149 */     return (AnimatableValue)new AnimatableNumberListValue(target, numbers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 156 */     if (val == null) {
/* 157 */       this.hasAnimVal = false;
/*     */     } else {
/* 159 */       this.hasAnimVal = true;
/* 160 */       AnimatableNumberListValue animNumList = (AnimatableNumberListValue)val;
/*     */       
/* 162 */       if (this.animVal == null) {
/* 163 */         this.animVal = new AnimSVGNumberList();
/*     */       }
/* 165 */       this.animVal.setAnimatedValue(animNumList.getNumbers());
/*     */     } 
/* 167 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 174 */     if (!this.changing && this.baseVal != null) {
/* 175 */       this.baseVal.invalidate();
/*     */     }
/* 177 */     fireBaseAttributeListeners();
/* 178 */     if (!this.hasAnimVal) {
/* 179 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 187 */     if (!this.changing && this.baseVal != null) {
/* 188 */       this.baseVal.invalidate();
/*     */     }
/* 190 */     fireBaseAttributeListeners();
/* 191 */     if (!this.hasAnimVal) {
/* 192 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 200 */     if (!this.changing && this.baseVal != null) {
/* 201 */       this.baseVal.invalidate();
/*     */     }
/* 203 */     fireBaseAttributeListeners();
/* 204 */     if (!this.hasAnimVal) {
/* 205 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class BaseSVGNumberList
/*     */     extends AbstractSVGNumberList
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
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 229 */       return SVGOMAnimatedNumberList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 238 */       return ((SVGOMElement)SVGOMAnimatedNumberList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getElement() {
/* 246 */       return (Element)SVGOMAnimatedNumberList.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 253 */       Attr attr = SVGOMAnimatedNumberList.this.element.getAttributeNodeNS(SVGOMAnimatedNumberList.this.namespaceURI, SVGOMAnimatedNumberList.this.localName);
/* 254 */       if (attr == null) {
/* 255 */         return SVGOMAnimatedNumberList.this.defaultValue;
/*     */       }
/* 257 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 265 */         SVGOMAnimatedNumberList.this.changing = true;
/* 266 */         SVGOMAnimatedNumberList.this.element.setAttributeNS(SVGOMAnimatedNumberList.this.namespaceURI, SVGOMAnimatedNumberList.this.localName, value);
/*     */       } finally {
/* 268 */         SVGOMAnimatedNumberList.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 276 */       super.resetAttribute();
/* 277 */       this.missing = false;
/* 278 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {
/* 287 */       super.resetAttribute(item);
/* 288 */       this.missing = false;
/* 289 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 296 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 300 */       this.valid = true;
/* 301 */       this.missing = false;
/* 302 */       this.malformed = false;
/*     */       
/* 304 */       String s = getValueAsString();
/* 305 */       boolean isEmpty = (s != null && s.length() == 0);
/* 306 */       if (s == null || (isEmpty && !SVGOMAnimatedNumberList.this.emptyAllowed)) {
/* 307 */         this.missing = true;
/*     */         return;
/*     */       } 
/* 310 */       if (isEmpty) {
/* 311 */         this.itemList = new ArrayList(1);
/*     */       } else {
/*     */         try {
/* 314 */           ListBuilder builder = new ListBuilder((AbstractSVGList)this);
/*     */           
/* 316 */           doParse(s, (ListHandler)builder);
/*     */           
/* 318 */           if (builder.getList() != null) {
/* 319 */             clear(this.itemList);
/*     */           }
/* 321 */           this.itemList = builder.getList();
/* 322 */         } catch (ParseException e) {
/* 323 */           this.itemList = new ArrayList(1);
/* 324 */           this.valid = true;
/* 325 */           this.malformed = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class AnimSVGNumberList
/*     */     extends AbstractSVGNumberList
/*     */   {
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 348 */       return SVGOMAnimatedNumberList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 357 */       return ((SVGOMElement)SVGOMAnimatedNumberList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Element getElement() {
/* 364 */       return (Element)SVGOMAnimatedNumberList.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumberOfItems() {
/* 371 */       if (SVGOMAnimatedNumberList.this.hasAnimVal) {
/* 372 */         return super.getNumberOfItems();
/*     */       }
/* 374 */       return SVGOMAnimatedNumberList.this.getBaseVal().getNumberOfItems();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber getItem(int index) throws DOMException {
/* 381 */       if (SVGOMAnimatedNumberList.this.hasAnimVal) {
/* 382 */         return super.getItem(index);
/*     */       }
/* 384 */       return SVGOMAnimatedNumberList.this.getBaseVal().getItem(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 391 */       if (this.itemList.size() == 0) {
/* 392 */         return "";
/*     */       }
/* 394 */       StringBuffer sb = new StringBuffer(this.itemList.size() * 8);
/* 395 */       Iterator<SVGItem> i = this.itemList.iterator();
/* 396 */       if (i.hasNext()) {
/* 397 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       }
/* 399 */       while (i.hasNext()) {
/* 400 */         sb.append(getItemSeparator());
/* 401 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       } 
/* 403 */       return sb.toString();
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
/* 416 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber initialize(SVGNumber newItem) throws DOMException, SVGException {
/* 426 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber insertItemBefore(SVGNumber newItem, int index) throws DOMException, SVGException {
/* 437 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber replaceItem(SVGNumber newItem, int index) throws DOMException, SVGException {
/* 448 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber removeItem(int index) throws DOMException {
/* 457 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGNumber appendItem(SVGNumber newItem) throws DOMException {
/* 466 */       throw SVGOMAnimatedNumberList.this.element.createDOMException((short)7, "readonly.number.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(float[] values) {
/* 475 */       int size = this.itemList.size();
/* 476 */       int i = 0;
/* 477 */       while (i < size && i < values.length) {
/* 478 */         SVGNumberItem n = this.itemList.get(i);
/* 479 */         n.setValue(values[i]);
/* 480 */         i++;
/*     */       } 
/* 482 */       while (i < values.length) {
/* 483 */         appendItemImpl(new SVGNumberItem(values[i]));
/* 484 */         i++;
/*     */       } 
/* 486 */       while (size > values.length) {
/* 487 */         removeItemImpl(--size);
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
/* 510 */       this.valid = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedNumberList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */