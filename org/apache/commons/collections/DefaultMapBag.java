/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public abstract class DefaultMapBag
/*     */   implements Bag
/*     */ {
/*  48 */   private Map _map = null;
/*  49 */   private int _total = 0;
/*  50 */   private int _mods = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DefaultMapBag(Map map) {
/*  67 */     setMap(map);
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
/*  78 */     return add(object, 1);
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
/*  89 */     this._mods++;
/*  90 */     if (nCopies > 0) {
/*  91 */       int count = nCopies + getCount(object);
/*  92 */       this._map.put(object, new Integer(count));
/*  93 */       this._total += nCopies;
/*  94 */       return (count == nCopies);
/*     */     } 
/*  96 */     return false;
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
/* 107 */     boolean changed = false;
/* 108 */     Iterator i = coll.iterator();
/* 109 */     while (i.hasNext()) {
/* 110 */       boolean added = add(i.next());
/* 111 */       changed = (changed || added);
/*     */     } 
/* 113 */     return changed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 120 */     this._mods++;
/* 121 */     this._map.clear();
/* 122 */     this._total = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object object) {
/* 133 */     return this._map.containsKey(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection coll) {
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
/*     */   public boolean containsAll(Bag other) {
/* 154 */     boolean result = true;
/* 155 */     Iterator i = other.uniqueSet().iterator();
/* 156 */     while (i.hasNext()) {
/* 157 */       Object current = i.next();
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
/*     */   
/*     */   public boolean equals(Object object) {
/* 173 */     if (object == this) {
/* 174 */       return true;
/*     */     }
/* 176 */     if (!(object instanceof Bag)) {
/* 177 */       return false;
/*     */     }
/* 179 */     Bag other = (Bag)object;
/* 180 */     if (other.size() != size()) {
/* 181 */       return false;
/*     */     }
/* 183 */     for (Iterator it = this._map.keySet().iterator(); it.hasNext(); ) {
/* 184 */       Object element = it.next();
/* 185 */       if (other.getCount(element) != getCount(element)) {
/* 186 */         return false;
/*     */       }
/*     */     } 
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 198 */     return this._map.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 207 */     return this._map.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 211 */     return new BagIterator(this, extractList().iterator());
/*     */   }
/*     */   
/*     */   static class BagIterator implements Iterator {
/* 215 */     private DefaultMapBag _parent = null;
/* 216 */     private Iterator _support = null;
/* 217 */     private Object _current = null;
/* 218 */     private int _mods = 0;
/*     */     
/*     */     public BagIterator(DefaultMapBag parent, Iterator support) {
/* 221 */       this._parent = parent;
/* 222 */       this._support = support;
/* 223 */       this._current = null;
/* 224 */       this._mods = parent.modCount();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 228 */       return this._support.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 232 */       if (this._parent.modCount() != this._mods) {
/* 233 */         throw new ConcurrentModificationException();
/*     */       }
/* 235 */       this._current = this._support.next();
/* 236 */       return this._current;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 240 */       if (this._parent.modCount() != this._mods) {
/* 241 */         throw new ConcurrentModificationException();
/*     */       }
/* 243 */       this._support.remove();
/* 244 */       this._parent.remove(this._current, 1);
/* 245 */       this._mods++;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 250 */     return remove(object, getCount(object));
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/* 254 */     this._mods++;
/* 255 */     boolean result = false;
/* 256 */     int count = getCount(object);
/* 257 */     if (nCopies <= 0) {
/* 258 */       result = false;
/* 259 */     } else if (count > nCopies) {
/* 260 */       this._map.put(object, new Integer(count - nCopies));
/* 261 */       result = true;
/* 262 */       this._total -= nCopies;
/*     */     } else {
/*     */       
/* 265 */       result = (this._map.remove(object) != null);
/* 266 */       this._total -= count;
/*     */     } 
/* 268 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 272 */     boolean result = false;
/* 273 */     if (coll != null) {
/* 274 */       Iterator i = coll.iterator();
/* 275 */       while (i.hasNext()) {
/* 276 */         boolean changed = remove(i.next(), 1);
/* 277 */         result = (result || changed);
/*     */       } 
/*     */     } 
/* 280 */     return result;
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
/* 291 */     return retainAll(new HashBag(coll));
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
/*     */   public boolean retainAll(Bag other) {
/* 303 */     boolean result = false;
/* 304 */     Bag excess = new HashBag();
/* 305 */     Iterator i = uniqueSet().iterator();
/* 306 */     while (i.hasNext()) {
/* 307 */       Object current = i.next();
/* 308 */       int myCount = getCount(current);
/* 309 */       int otherCount = other.getCount(current);
/* 310 */       if (1 <= otherCount && otherCount <= myCount) {
/* 311 */         excess.add(current, myCount - otherCount); continue;
/*     */       } 
/* 313 */       excess.add(current, myCount);
/*     */     } 
/*     */     
/* 316 */     if (!excess.isEmpty()) {
/* 317 */       result = removeAll(excess);
/*     */     }
/* 319 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 328 */     return extractList().toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 338 */     return extractList().toArray(array);
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
/* 349 */     int result = 0;
/* 350 */     Integer count = MapUtils.getInteger(this._map, object);
/* 351 */     if (count != null) {
/* 352 */       result = count.intValue();
/*     */     }
/* 354 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set uniqueSet() {
/* 363 */     return UnmodifiableSet.decorate(this._map.keySet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 372 */     return this._total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int calcTotalSize() {
/* 382 */     this._total = extractList().size();
/* 383 */     return this._total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setMap(Map map) {
/* 392 */     if (map == null || !map.isEmpty()) {
/* 393 */       throw new IllegalArgumentException("The map must be non-null and empty");
/*     */     }
/* 395 */     this._map = map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getMap() {
/* 404 */     return this._map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List extractList() {
/* 411 */     List result = new ArrayList();
/* 412 */     Iterator i = uniqueSet().iterator();
/* 413 */     while (i.hasNext()) {
/* 414 */       Object current = i.next();
/* 415 */       for (int index = getCount(current); index > 0; index--) {
/* 416 */         result.add(current);
/*     */       }
/*     */     } 
/* 419 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int modCount() {
/* 428 */     return this._mods;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 437 */     StringBuffer buf = new StringBuffer();
/* 438 */     buf.append("[");
/* 439 */     Iterator i = uniqueSet().iterator();
/* 440 */     while (i.hasNext()) {
/* 441 */       Object current = i.next();
/* 442 */       int count = getCount(current);
/* 443 */       buf.append(count);
/* 444 */       buf.append(":");
/* 445 */       buf.append(current);
/* 446 */       if (i.hasNext()) {
/* 447 */         buf.append(",");
/*     */       }
/*     */     } 
/* 450 */     buf.append("]");
/* 451 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public DefaultMapBag() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/DefaultMapBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */