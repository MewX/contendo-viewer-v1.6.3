/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGList
/*     */ {
/*     */   protected boolean valid;
/*     */   protected List itemList;
/*     */   
/*     */   protected abstract String getItemSeparator();
/*     */   
/*     */   protected abstract SVGItem createSVGItem(Object paramObject);
/*     */   
/*     */   protected abstract void doParse(String paramString, ListHandler paramListHandler) throws ParseException;
/*     */   
/*     */   protected abstract void checkItemType(Object paramObject) throws SVGException;
/*     */   
/*     */   protected abstract String getValueAsString();
/*     */   
/*     */   protected abstract void setAttributeValue(String paramString);
/*     */   
/*     */   protected abstract DOMException createDOMException(short paramShort, String paramString, Object[] paramArrayOfObject);
/*     */   
/*     */   public int getNumberOfItems() {
/* 116 */     revalidate();
/* 117 */     if (this.itemList != null) {
/* 118 */       return this.itemList.size();
/*     */     }
/* 120 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() throws DOMException {
/* 129 */     revalidate();
/* 130 */     if (this.itemList != null) {
/*     */       
/* 132 */       clear(this.itemList);
/*     */       
/* 134 */       resetAttribute();
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
/*     */   protected SVGItem initializeImpl(Object newItem) throws DOMException, SVGException {
/* 153 */     checkItemType(newItem);
/*     */ 
/*     */     
/* 156 */     if (this.itemList == null) {
/* 157 */       this.itemList = new ArrayList(1);
/*     */     } else {
/* 159 */       clear(this.itemList);
/*     */     } 
/*     */     
/* 162 */     SVGItem item = removeIfNeeded(newItem);
/*     */ 
/*     */     
/* 165 */     this.itemList.add(item);
/*     */ 
/*     */     
/* 168 */     item.setParent(this);
/*     */ 
/*     */     
/* 171 */     resetAttribute();
/*     */     
/* 173 */     return item;
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
/*     */   protected SVGItem getItemImpl(int index) throws DOMException {
/* 187 */     revalidate();
/*     */     
/* 189 */     if (index < 0 || this.itemList == null || index >= this.itemList.size()) {
/* 190 */       throw createDOMException((short)1, "index.out.of.bounds", new Object[] { Integer.valueOf(index) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 195 */     return this.itemList.get(index);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem insertItemBeforeImpl(Object newItem, int index) throws DOMException, SVGException {
/* 225 */     checkItemType(newItem);
/*     */     
/* 227 */     revalidate();
/* 228 */     if (index < 0) {
/* 229 */       throw createDOMException((short)1, "index.out.of.bounds", new Object[] { Integer.valueOf(index) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 234 */     if (index > this.itemList.size()) {
/* 235 */       index = this.itemList.size();
/*     */     }
/*     */     
/* 238 */     SVGItem item = removeIfNeeded(newItem);
/*     */ 
/*     */     
/* 241 */     this.itemList.add(index, item);
/*     */ 
/*     */     
/* 244 */     item.setParent(this);
/*     */ 
/*     */     
/* 247 */     resetAttribute();
/*     */     
/* 249 */     return item;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem replaceItemImpl(Object newItem, int index) throws DOMException, SVGException {
/* 276 */     checkItemType(newItem);
/*     */     
/* 278 */     revalidate();
/* 279 */     if (index < 0 || index >= this.itemList.size()) {
/* 280 */       throw createDOMException((short)1, "index.out.of.bounds", new Object[] { Integer.valueOf(index) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 285 */     SVGItem item = removeIfNeeded(newItem);
/*     */ 
/*     */     
/* 288 */     this.itemList.set(index, item);
/*     */ 
/*     */     
/* 291 */     item.setParent(this);
/*     */ 
/*     */     
/* 294 */     resetAttribute();
/*     */     
/* 296 */     return item;
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
/*     */   protected SVGItem removeItemImpl(int index) throws DOMException {
/* 312 */     revalidate();
/*     */     
/* 314 */     if (index < 0 || index >= this.itemList.size()) {
/* 315 */       throw createDOMException((short)1, "index.out.of.bounds", new Object[] { Integer.valueOf(index) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 320 */     SVGItem item = this.itemList.remove(index);
/*     */ 
/*     */     
/* 323 */     item.setParent(null);
/*     */ 
/*     */     
/* 326 */     resetAttribute();
/*     */     
/* 328 */     return item;
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
/*     */ 
/*     */   
/*     */   protected SVGItem appendItemImpl(Object newItem) throws DOMException, SVGException {
/* 348 */     checkItemType(newItem);
/*     */     
/* 350 */     revalidate();
/*     */     
/* 352 */     SVGItem item = removeIfNeeded(newItem);
/*     */     
/* 354 */     this.itemList.add(item);
/*     */ 
/*     */     
/* 357 */     item.setParent(this);
/*     */     
/* 359 */     if (this.itemList.size() <= 1) {
/* 360 */       resetAttribute();
/*     */     } else {
/* 362 */       resetAttribute(item);
/*     */     } 
/*     */     
/* 365 */     return item;
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
/*     */   protected SVGItem removeIfNeeded(Object newItem) {
/*     */     SVGItem item;
/* 380 */     if (newItem instanceof SVGItem) {
/*     */       
/* 382 */       item = (SVGItem)newItem;
/* 383 */       if (item.getParent() != null) {
/* 384 */         item.getParent().removeItem(item);
/*     */       }
/*     */     } else {
/*     */       
/* 388 */       item = createSVGItem(newItem);
/*     */     } 
/* 390 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void revalidate() {
/* 397 */     if (this.valid) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 402 */       ListBuilder builder = new ListBuilder(this);
/*     */       
/* 404 */       doParse(getValueAsString(), builder);
/*     */       
/* 406 */       List parsedList = builder.getList();
/* 407 */       if (parsedList != null) {
/* 408 */         clear(this.itemList);
/*     */       }
/* 410 */       this.itemList = parsedList;
/* 411 */     } catch (ParseException e) {
/* 412 */       this.itemList = null;
/*     */     } 
/* 414 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setValueAsString(List value) throws DOMException {
/* 422 */     String finalValue = null;
/* 423 */     Iterator<SVGItem> it = value.iterator();
/* 424 */     if (it.hasNext()) {
/* 425 */       SVGItem item = it.next();
/* 426 */       StringBuffer buf = new StringBuffer(value.size() * 8);
/* 427 */       buf.append(item.getValueAsString());
/* 428 */       while (it.hasNext()) {
/* 429 */         item = it.next();
/* 430 */         buf.append(getItemSeparator());
/* 431 */         buf.append(item.getValueAsString());
/*     */       } 
/* 433 */       finalValue = buf.toString();
/*     */     } 
/* 435 */     setAttributeValue(finalValue);
/* 436 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemChanged() {
/* 444 */     resetAttribute();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void resetAttribute() {
/* 451 */     setValueAsString(this.itemList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void resetAttribute(SVGItem item) {
/* 460 */     String newValue = getValueAsString() + getItemSeparator() + item.getValueAsString();
/* 461 */     setAttributeValue(newValue);
/* 462 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 469 */     this.valid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeItem(SVGItem item) {
/* 480 */     if (this.itemList.contains(item)) {
/* 481 */       this.itemList.remove(item);
/* 482 */       item.setParent(null);
/* 483 */       resetAttribute();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clear(List list) {
/* 491 */     if (list == null) {
/*     */       return;
/*     */     }
/* 494 */     for (Object aList : list) {
/* 495 */       SVGItem item = (SVGItem)aList;
/* 496 */       item.setParent(null);
/*     */     } 
/* 498 */     list.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */