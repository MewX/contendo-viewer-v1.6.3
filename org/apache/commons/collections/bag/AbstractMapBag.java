/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMapBag
/*     */   implements Bag
/*     */ {
/*     */   private transient Map map;
/*     */   private int size;
/*     */   private transient int modCount;
/*     */   private transient Set uniqueSet;
/*     */   
/*     */   protected AbstractMapBag() {}
/*     */   
/*     */   protected AbstractMapBag(Map map) {
/*  74 */     this.map = map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getMap() {
/*  84 */     return this.map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  94 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 103 */     return this.map.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount(Object object) {
/* 114 */     MutableInteger count = (MutableInteger)this.map.get(object);
/* 115 */     if (count != null) {
/* 116 */       return count.value;
/*     */     }
/* 118 */     return 0;
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
/*     */   public boolean contains(Object object) {
/* 130 */     return this.map.containsKey(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 140 */     if (coll instanceof Bag) {
/* 141 */       return containsAll((Bag)coll);
/*     */     }
/* 143 */     return containsAll(new HashBag(coll));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean containsAll(Bag other) {
/* 154 */     boolean result = true;
/* 155 */     Iterator it = other.uniqueSet().iterator();
/* 156 */     while (it.hasNext()) {
/* 157 */       Object current = it.next();
/* 158 */       boolean contains = (getCount(current) >= other.getCount(current));
/* 159 */       result = (result && contains);
/*     */     } 
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 172 */     return new BagIterator(this);
/*     */   }
/*     */ 
/*     */   
/*     */   static class BagIterator
/*     */     implements Iterator
/*     */   {
/*     */     private AbstractMapBag parent;
/*     */     
/*     */     private Iterator entryIterator;
/*     */     
/*     */     private Map.Entry current;
/*     */     
/*     */     private int itemCount;
/*     */     
/*     */     private final int mods;
/*     */     
/*     */     private boolean canRemove;
/*     */     
/*     */     public BagIterator(AbstractMapBag parent) {
/* 192 */       this.parent = parent;
/* 193 */       this.entryIterator = parent.map.entrySet().iterator();
/* 194 */       this.current = null;
/* 195 */       this.mods = parent.modCount;
/* 196 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 200 */       return (this.itemCount > 0 || this.entryIterator.hasNext());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 204 */       if (this.parent.modCount != this.mods) {
/* 205 */         throw new ConcurrentModificationException();
/*     */       }
/* 207 */       if (this.itemCount == 0) {
/* 208 */         this.current = this.entryIterator.next();
/* 209 */         this.itemCount = ((AbstractMapBag.MutableInteger)this.current.getValue()).value;
/*     */       } 
/* 211 */       this.canRemove = true;
/* 212 */       this.itemCount--;
/* 213 */       return this.current.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 217 */       if (this.parent.modCount != this.mods) {
/* 218 */         throw new ConcurrentModificationException();
/*     */       }
/* 220 */       if (!this.canRemove) {
/* 221 */         throw new IllegalStateException();
/*     */       }
/* 223 */       AbstractMapBag.MutableInteger mut = (AbstractMapBag.MutableInteger)this.current.getValue();
/* 224 */       if (mut.value > 0) {
/* 225 */         mut.value--;
/* 226 */         this.parent.size--;
/*     */       } else {
/* 228 */         this.entryIterator.remove();
/*     */       } 
/* 230 */       this.canRemove = false;
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
/*     */   public boolean add(Object object) {
/* 242 */     return add(object, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(Object object, int nCopies) {
/* 253 */     this.modCount++;
/* 254 */     if (nCopies > 0) {
/* 255 */       MutableInteger mut = (MutableInteger)this.map.get(object);
/* 256 */       this.size += nCopies;
/* 257 */       if (mut == null) {
/* 258 */         this.map.put(object, new MutableInteger(nCopies));
/* 259 */         return true;
/*     */       } 
/* 261 */       mut.value += nCopies;
/* 262 */       return false;
/*     */     } 
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 276 */     boolean changed = false;
/* 277 */     Iterator i = coll.iterator();
/* 278 */     while (i.hasNext()) {
/* 279 */       boolean added = add(i.next());
/* 280 */       changed = (changed || added);
/*     */     } 
/* 282 */     return changed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 290 */     this.modCount++;
/* 291 */     this.map.clear();
/* 292 */     this.size = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object object) {
/* 302 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 303 */     if (mut == null) {
/* 304 */       return false;
/*     */     }
/* 306 */     this.modCount++;
/* 307 */     this.map.remove(object);
/* 308 */     this.size -= mut.value;
/* 309 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/* 320 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 321 */     if (mut == null) {
/* 322 */       return false;
/*     */     }
/* 324 */     if (nCopies <= 0) {
/* 325 */       return false;
/*     */     }
/* 327 */     this.modCount++;
/* 328 */     if (nCopies < mut.value) {
/* 329 */       mut.value -= nCopies;
/* 330 */       this.size -= nCopies;
/*     */     } else {
/* 332 */       this.map.remove(object);
/* 333 */       this.size -= mut.value;
/*     */     } 
/* 335 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 345 */     boolean result = false;
/* 346 */     if (coll != null) {
/* 347 */       Iterator i = coll.iterator();
/* 348 */       while (i.hasNext()) {
/* 349 */         boolean changed = remove(i.next(), 1);
/* 350 */         result = (result || changed);
/*     */       } 
/*     */     } 
/* 353 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 364 */     if (coll instanceof Bag) {
/* 365 */       return retainAll((Bag)coll);
/*     */     }
/* 367 */     return retainAll(new HashBag(coll));
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
/*     */   boolean retainAll(Bag other) {
/* 379 */     boolean result = false;
/* 380 */     Bag excess = new HashBag();
/* 381 */     Iterator i = uniqueSet().iterator();
/* 382 */     while (i.hasNext()) {
/* 383 */       Object current = i.next();
/* 384 */       int myCount = getCount(current);
/* 385 */       int otherCount = other.getCount(current);
/* 386 */       if (1 <= otherCount && otherCount <= myCount) {
/* 387 */         excess.add(current, myCount - otherCount); continue;
/*     */       } 
/* 389 */       excess.add(current, myCount);
/*     */     } 
/*     */     
/* 392 */     if (!excess.isEmpty()) {
/* 393 */       result = removeAll((Collection)excess);
/*     */     }
/* 395 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MutableInteger
/*     */   {
/*     */     protected int value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MutableInteger(int value) {
/* 411 */       this.value = value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 415 */       if (!(obj instanceof MutableInteger)) {
/* 416 */         return false;
/*     */       }
/* 418 */       return (((MutableInteger)obj).value == this.value);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 422 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 433 */     Object[] result = new Object[size()];
/* 434 */     int i = 0;
/* 435 */     Iterator it = this.map.keySet().iterator();
/* 436 */     while (it.hasNext()) {
/* 437 */       Object current = it.next();
/* 438 */       for (int index = getCount(current); index > 0; index--) {
/* 439 */         result[i++] = current;
/*     */       }
/*     */     } 
/* 442 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 452 */     int size = size();
/* 453 */     if (array.length < size) {
/* 454 */       array = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*     */     }
/*     */     
/* 457 */     int i = 0;
/* 458 */     Iterator it = this.map.keySet().iterator();
/* 459 */     while (it.hasNext()) {
/* 460 */       Object current = it.next();
/* 461 */       for (int index = getCount(current); index > 0; index--) {
/* 462 */         array[i++] = current;
/*     */       }
/*     */     } 
/* 465 */     if (array.length > size) {
/* 466 */       array[size] = null;
/*     */     }
/* 468 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set uniqueSet() {
/* 477 */     if (this.uniqueSet == null) {
/* 478 */       this.uniqueSet = UnmodifiableSet.decorate(this.map.keySet());
/*     */     }
/* 480 */     return this.uniqueSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 490 */     out.writeInt(this.map.size());
/* 491 */     for (Iterator it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 492 */       Map.Entry entry = it.next();
/* 493 */       out.writeObject(entry.getKey());
/* 494 */       out.writeInt(((MutableInteger)entry.getValue()).value);
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
/*     */   protected void doReadObject(Map map, ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 506 */     this.map = map;
/* 507 */     int entrySize = in.readInt();
/* 508 */     for (int i = 0; i < entrySize; i++) {
/* 509 */       Object obj = in.readObject();
/* 510 */       int count = in.readInt();
/* 511 */       map.put(obj, new MutableInteger(count));
/* 512 */       this.size += count;
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
/*     */   public boolean equals(Object object) {
/* 526 */     if (object == this) {
/* 527 */       return true;
/*     */     }
/* 529 */     if (!(object instanceof Bag)) {
/* 530 */       return false;
/*     */     }
/* 532 */     Bag other = (Bag)object;
/* 533 */     if (other.size() != size()) {
/* 534 */       return false;
/*     */     }
/* 536 */     for (Iterator it = this.map.keySet().iterator(); it.hasNext(); ) {
/* 537 */       Object element = it.next();
/* 538 */       if (other.getCount(element) != getCount(element)) {
/* 539 */         return false;
/*     */       }
/*     */     } 
/* 542 */     return true;
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
/*     */   public int hashCode() {
/* 555 */     int total = 0;
/* 556 */     for (Iterator it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 557 */       Map.Entry entry = it.next();
/* 558 */       Object element = entry.getKey();
/* 559 */       MutableInteger count = (MutableInteger)entry.getValue();
/* 560 */       total += ((element == null) ? 0 : element.hashCode()) ^ count.value;
/*     */     } 
/* 562 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 571 */     if (size() == 0) {
/* 572 */       return "[]";
/*     */     }
/* 574 */     StringBuffer buf = new StringBuffer();
/* 575 */     buf.append('[');
/* 576 */     Iterator it = uniqueSet().iterator();
/* 577 */     while (it.hasNext()) {
/* 578 */       Object current = it.next();
/* 579 */       int count = getCount(current);
/* 580 */       buf.append(count);
/* 581 */       buf.append(':');
/* 582 */       buf.append(current);
/* 583 */       if (it.hasNext()) {
/* 584 */         buf.append(',');
/*     */       }
/*     */     } 
/* 587 */     buf.append(']');
/* 588 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/AbstractMapBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */