/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.anim.values.AnimatablePointListValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.svg.AbstractSVGList;
/*     */ import org.apache.batik.dom.svg.AbstractSVGPointList;
/*     */ import org.apache.batik.dom.svg.ListBuilder;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.dom.svg.SVGPointItem;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedPoints;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ import org.w3c.dom.svg.SVGPointList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAnimatedPoints
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedPoints
/*     */ {
/*     */   protected BaseSVGPointList baseVal;
/*     */   protected AnimSVGPointList animVal;
/*     */   protected boolean changing;
/*     */   protected String defaultValue;
/*     */   
/*     */   public SVGOMAnimatedPoints(AbstractElement elt, String ns, String ln, String defaultValue) {
/*  82 */     super(elt, ns, ln);
/*  83 */     this.defaultValue = defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPointList getPoints() {
/*  90 */     if (this.baseVal == null) {
/*  91 */       this.baseVal = new BaseSVGPointList();
/*     */     }
/*  93 */     return (SVGPointList)this.baseVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPointList getAnimatedPoints() {
/* 100 */     if (this.animVal == null) {
/* 101 */       this.animVal = new AnimSVGPointList();
/*     */     }
/* 103 */     return (SVGPointList)this.animVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 110 */     if (!this.hasAnimVal) {
/* 111 */       if (this.baseVal == null) {
/* 112 */         this.baseVal = new BaseSVGPointList();
/*     */       }
/* 114 */       this.baseVal.revalidate();
/* 115 */       if (this.baseVal.missing) {
/* 116 */         throw new LiveAttributeException(this.element, this.localName, (short)0, null);
/*     */       }
/*     */ 
/*     */       
/* 120 */       if (this.baseVal.malformed) {
/* 121 */         throw new LiveAttributeException(this.element, this.localName, (short)1, this.baseVal.getValueAsString());
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
/* 133 */     SVGPointList pl = getPoints();
/* 134 */     int n = pl.getNumberOfItems();
/* 135 */     float[] points = new float[n * 2];
/* 136 */     for (int i = 0; i < n; i++) {
/* 137 */       SVGPoint p = pl.getItem(i);
/* 138 */       points[i * 2] = p.getX();
/* 139 */       points[i * 2 + 1] = p.getY();
/*     */     } 
/* 141 */     return (AnimatableValue)new AnimatablePointListValue(target, points);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/* 148 */     if (val == null) {
/* 149 */       this.hasAnimVal = false;
/*     */     } else {
/* 151 */       this.hasAnimVal = true;
/* 152 */       AnimatablePointListValue animPointList = (AnimatablePointListValue)val;
/*     */       
/* 154 */       if (this.animVal == null) {
/* 155 */         this.animVal = new AnimSVGPointList();
/*     */       }
/* 157 */       this.animVal.setAnimatedValue(animPointList.getNumbers());
/*     */     } 
/* 159 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 166 */     if (!this.changing && this.baseVal != null) {
/* 167 */       this.baseVal.invalidate();
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
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 179 */     if (!this.changing && this.baseVal != null) {
/* 180 */       this.baseVal.invalidate();
/*     */     }
/* 182 */     fireBaseAttributeListeners();
/* 183 */     if (!this.hasAnimVal) {
/* 184 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 192 */     if (!this.changing && this.baseVal != null) {
/* 193 */       this.baseVal.invalidate();
/*     */     }
/* 195 */     fireBaseAttributeListeners();
/* 196 */     if (!this.hasAnimVal) {
/* 197 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class BaseSVGPointList
/*     */     extends AbstractSVGPointList
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
/* 221 */       return SVGOMAnimatedPoints.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 230 */       return ((SVGOMElement)SVGOMAnimatedPoints.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 237 */       Attr attr = SVGOMAnimatedPoints.this.element.getAttributeNodeNS(SVGOMAnimatedPoints.this.namespaceURI, SVGOMAnimatedPoints.this.localName);
/* 238 */       if (attr == null) {
/* 239 */         return SVGOMAnimatedPoints.this.defaultValue;
/*     */       }
/* 241 */       return attr.getValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAttributeValue(String value) {
/*     */       try {
/* 249 */         SVGOMAnimatedPoints.this.changing = true;
/* 250 */         SVGOMAnimatedPoints.this.element.setAttributeNS(SVGOMAnimatedPoints.this.namespaceURI, SVGOMAnimatedPoints.this.localName, value);
/*     */       } finally {
/* 252 */         SVGOMAnimatedPoints.this.changing = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 260 */       super.resetAttribute();
/* 261 */       this.missing = false;
/* 262 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute(SVGItem item) {
/* 271 */       super.resetAttribute(item);
/* 272 */       this.missing = false;
/* 273 */       this.malformed = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void revalidate() {
/* 280 */       if (this.valid) {
/*     */         return;
/*     */       }
/*     */       
/* 284 */       this.valid = true;
/* 285 */       this.missing = false;
/* 286 */       this.malformed = false;
/*     */       
/* 288 */       String s = getValueAsString();
/* 289 */       if (s == null) {
/* 290 */         this.missing = true;
/*     */         return;
/*     */       } 
/*     */       try {
/* 294 */         ListBuilder builder = new ListBuilder((AbstractSVGList)this);
/*     */         
/* 296 */         doParse(s, (ListHandler)builder);
/*     */         
/* 298 */         if (builder.getList() != null) {
/* 299 */           clear(this.itemList);
/*     */         }
/* 301 */         this.itemList = builder.getList();
/* 302 */       } catch (ParseException e) {
/* 303 */         this.itemList = new ArrayList(1);
/* 304 */         this.malformed = true;
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
/*     */   protected class AnimSVGPointList
/*     */     extends AbstractSVGPointList
/*     */   {
/*     */     protected DOMException createDOMException(short type, String key, Object[] args) {
/* 326 */       return SVGOMAnimatedPoints.this.element.createDOMException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGException createSVGException(short type, String key, Object[] args) {
/* 335 */       return ((SVGOMElement)SVGOMAnimatedPoints.this.element).createSVGException(type, key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumberOfItems() {
/* 342 */       if (SVGOMAnimatedPoints.this.hasAnimVal) {
/* 343 */         return super.getNumberOfItems();
/*     */       }
/* 345 */       return SVGOMAnimatedPoints.this.getPoints().getNumberOfItems();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint getItem(int index) throws DOMException {
/* 352 */       if (SVGOMAnimatedPoints.this.hasAnimVal) {
/* 353 */         return super.getItem(index);
/*     */       }
/* 355 */       return SVGOMAnimatedPoints.this.getPoints().getItem(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getValueAsString() {
/* 362 */       if (this.itemList.size() == 0) {
/* 363 */         return "";
/*     */       }
/* 365 */       StringBuffer sb = new StringBuffer(this.itemList.size() * 8);
/* 366 */       Iterator<SVGItem> i = this.itemList.iterator();
/* 367 */       if (i.hasNext()) {
/* 368 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       }
/* 370 */       while (i.hasNext()) {
/* 371 */         sb.append(getItemSeparator());
/* 372 */         sb.append(((SVGItem)i.next()).getValueAsString());
/*     */       } 
/* 374 */       return sb.toString();
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
/* 387 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint initialize(SVGPoint newItem) throws DOMException, SVGException {
/* 397 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint insertItemBefore(SVGPoint newItem, int index) throws DOMException, SVGException {
/* 408 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint replaceItem(SVGPoint newItem, int index) throws DOMException, SVGException {
/* 419 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint removeItem(int index) throws DOMException {
/* 428 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGPoint appendItem(SVGPoint newItem) throws DOMException {
/* 437 */       throw SVGOMAnimatedPoints.this.element.createDOMException((short)7, "readonly.point.list", null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setAnimatedValue(float[] pts) {
/* 446 */       int size = this.itemList.size();
/* 447 */       int i = 0;
/* 448 */       while (i < size && i < pts.length / 2) {
/* 449 */         SVGPointItem p = this.itemList.get(i);
/* 450 */         p.setX(pts[i * 2]);
/* 451 */         p.setY(pts[i * 2 + 1]);
/* 452 */         i++;
/*     */       } 
/* 454 */       while (i < pts.length / 2) {
/* 455 */         appendItemImpl(new SVGPointItem(pts[i * 2], pts[i * 2 + 1]));
/* 456 */         i++;
/*     */       } 
/* 458 */       while (size > pts.length / 2) {
/* 459 */         removeItemImpl(--size);
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
/* 482 */       this.valid = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedPoints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */