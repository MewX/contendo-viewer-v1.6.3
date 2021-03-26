/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.anim.values.AnimatableTransformListValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGList;
/*     */ import org.apache.batik.dom.svg.AbstractSVGTransformList;
/*     */ import org.apache.batik.dom.svg.ListBuilder;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedTransformList;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGTransform;
/*     */ import org.w3c.dom.svg.SVGTransformList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedTransformList
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedTransformList
/*     */ {
/*     */   protected BaseSVGTransformList baseVal;
/*     */   protected AnimSVGTransformList animVal;
/*     */   protected boolean changing;
/*     */   protected String defaultValue;
/*     */   
/*     */   public SVGOMAnimatedTransformList(AbstractElement elt, String ns, String ln, String defaultValue) {
/*  81 */     super(elt, ns, ln);
/*  82 */     this.defaultValue = defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransformList getBaseVal() {
/*  89 */     if (this.baseVal == null) {
/*  90 */       this.baseVal = new BaseSVGTransformList();
/*     */     }
/*  92 */     return (SVGTransformList)this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransformList getAnimVal() {
/*  99 */     if (this.animVal == null) {
/* 100 */       this.animVal = new AnimSVGTransformList();
/*     */     }
/* 102 */     return (SVGTransformList)this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 109 */     if (!this.hasAnimVal) {
/* 110 */       if (this.baseVal == null) {
/* 111 */         this.baseVal = new BaseSVGTransformList();
/*     */       }
/* 113 */       this.baseVal.revalidate();
/* 114 */       if (this.baseVal.missing) {
/* 115 */         throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */       }
/*     */ 
/*     */       
/* 119 */       if (this.baseVal.malformed) {
/* 120 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
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
/* 132 */     SVGTransformList tl = getBaseVal();
/* 133 */     int n = tl.getNumberOfItems();
/* 134 */     List<SVGTransform> v = new ArrayList(n);
/* 135 */     for (int i = 0; i < n; i++) {
/* 136 */       v.add(tl.getItem(i));
/*     */     }
/* 138 */     return (AnimatableValue)new AnimatableTransformListValue(target, v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 145 */     if (val == null) {
/* 146 */       this.hasAnimVal = false;
/*     */     } else {
/* 148 */       this.hasAnimVal = true;
/* 149 */       AnimatableTransformListValue aval = (AnimatableTransformListValue)val;
/*     */       
/* 151 */       if (this.animVal == null) {
/* 152 */         this.animVal = new AnimSVGTransformList();
/*     */       }
/* 154 */       this.animVal.setAnimatedValue(aval.getTransforms());
/*     */     } 
/* 156 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 163 */     if (!this.changing && this.baseVal != null) {
/* 164 */       this.baseVal.invalidate();
/*     */     }
/* 166 */     fireBaseAttributeListeners();
/* 167 */     if (!this.hasAnimVal) {
/* 168 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 176 */     if (!this.changing && this.baseVal != null) {
/* 177 */       this.baseVal.invalidate();
/*     */     }
/* 179 */     fireBaseAttributeListeners();
/* 180 */     if (!this.hasAnimVal) {
/* 181 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 189 */     if (!this.changing && this.baseVal != null) {
/* 190 */       this.baseVal.invalidate();
/*     */     }
/* 192 */     fireBaseAttributeListeners();
/* 193 */     if (!this.hasAnimVal) {
/* 194 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class BaseSVGTransformList
/*     */     extends AbstractSVGTransformList
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
/* 218 */       return SVGOMAnimatedTransformList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 227 */       return ((SVGOMElement)SVGOMAnimatedTransformList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 234 */       Attr attr = SVGOMAnimatedTransformList.this.element.getAttributeNodeNS(SVGOMAnimatedTransformList.this.namespaceURI, SVGOMAnimatedTransformList.this.localName);
/* 235 */       if (attr == null) {
/* 236 */         return SVGOMAnimatedTransformList.this.defaultValue;
/*     */       }
/* 238 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 246 */         SVGOMAnimatedTransformList.this.changing = true;
/* 247 */         SVGOMAnimatedTransformList.this.element.setAttributeNS(SVGOMAnimatedTransformList.this.namespaceURI, SVGOMAnimatedTransformList.this.localName, value);
/*     */       } finally {
/* 249 */         SVGOMAnimatedTransformList.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 257 */       super.resetAttribute();
/* 258 */       this.missing = false;
/* 259 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {
/* 268 */       super.resetAttribute(item);
/* 269 */       this.missing = false;
/* 270 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 277 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 281 */       this.valid = true;
/* 282 */       this.missing = false;
/* 283 */       this.malformed = false;
/*     */       
/* 285 */       String s = getValueAsString();
/* 286 */       if (s == null) {
/* 287 */         this.missing = true;
/*     */         return;
/*     */       } 
/*     */       try {
/* 291 */         ListBuilder builder = new ListBuilder((AbstractSVGList)this);
/*     */         
/* 293 */         doParse(s, (ListHandler)builder);
/*     */         
/* 295 */         if (builder.getList() != null) {
/* 296 */           clear(this.itemList);
/*     */         }
/* 298 */         this.itemList = builder.getList();
/* 299 */       } catch (ParseException e) {
/* 300 */         this.itemList = new ArrayList(1);
/* 301 */         this.malformed = true;
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
/*     */   
/*     */   protected class AnimSVGTransformList
/*     */     extends AbstractSVGTransformList
/*     */   {
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 324 */       return SVGOMAnimatedTransformList.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 333 */       return ((SVGOMElement)SVGOMAnimatedTransformList.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumberOfItems() {
/* 340 */       if (SVGOMAnimatedTransformList.this.hasAnimVal) {
/* 341 */         return super.getNumberOfItems();
/*     */       }
/* 343 */       return SVGOMAnimatedTransformList.this.getBaseVal().getNumberOfItems();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform getItem(int index) throws DOMException {
/* 350 */       if (SVGOMAnimatedTransformList.this.hasAnimVal) {
/* 351 */         return super.getItem(index);
/*     */       }
/* 353 */       return SVGOMAnimatedTransformList.this.getBaseVal().getItem(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 360 */       if (this.itemList.size() == 0) {
/* 361 */         return "";
/*     */       }
/* 363 */       StringBuffer sb = new StringBuffer(this.itemList.size() * 8);
/* 364 */       Iterator<SVGItem> i = this.itemList.iterator();
/* 365 */       if (i.hasNext()) {
/* 366 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       }
/* 368 */       while (i.hasNext()) {
/* 369 */         sb.append(getItemSeparator());
/* 370 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       } 
/* 372 */       return sb.toString();
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
/* 385 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform initialize(SVGTransform newItem) throws DOMException, SVGException {
/* 395 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform insertItemBefore(SVGTransform newItem, int index) throws DOMException, SVGException {
/* 406 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform replaceItem(SVGTransform newItem, int index) throws DOMException, SVGException {
/* 417 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform removeItem(int index) throws DOMException {
/* 426 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform appendItem(SVGTransform newItem) throws DOMException {
/* 435 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransform consolidate() {
/* 444 */       throw SVGOMAnimatedTransformList.this.element.createDOMException((short)7, "readonly.transform.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(Iterator<SVGTransform> it) {
/* 453 */       int size = this.itemList.size();
/* 454 */       int i = 0;
/* 455 */       while (i < size && it.hasNext()) {
/* 456 */         AbstractSVGTransformList.SVGTransformItem t = this.itemList.get(i);
/* 457 */         t.assign(it.next());
/* 458 */         i++;
/*     */       } 
/* 460 */       while (it.hasNext()) {
/* 461 */         appendItemImpl(new AbstractSVGTransformList.SVGTransformItem(this, it.next()));
/* 462 */         i++;
/*     */       } 
/* 464 */       while (size > i) {
/* 465 */         removeItemImpl(--size);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(SVGTransform transform) {
/* 473 */       int size = this.itemList.size();
/* 474 */       while (size > 1) {
/* 475 */         removeItemImpl(--size);
/*     */       }
/* 477 */       if (size == 0) {
/* 478 */         appendItemImpl(new AbstractSVGTransformList.SVGTransformItem(this, transform));
/*     */       } else {
/* 480 */         AbstractSVGTransformList.SVGTransformItem t = this.itemList.get(0);
/* 481 */         t.assign(transform);
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
/* 504 */       this.valid = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedTransformList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */