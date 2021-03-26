/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RandomAccessLongList
/*     */   extends AbstractLongCollection
/*     */   implements LongList
/*     */ {
/*     */   public abstract long get(int paramInt);
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public long removeElementAt(int index) {
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long set(int index, long element) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, long element) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(long element) {
/*  86 */     add(size(), element);
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, LongCollection collection) {
/*  91 */     boolean modified = false;
/*  92 */     for (LongIterator iter = collection.iterator(); iter.hasNext(); ) {
/*  93 */       add(index++, iter.next());
/*  94 */       modified = true;
/*     */     } 
/*  96 */     return modified;
/*     */   }
/*     */   
/*     */   public int indexOf(long element) {
/* 100 */     int i = 0;
/* 101 */     for (LongIterator iter = iterator(); iter.hasNext(); ) {
/* 102 */       if (iter.next() == element) {
/* 103 */         return i;
/*     */       }
/* 105 */       i++;
/*     */     } 
/*     */     
/* 108 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(long element) {
/* 112 */     for (LongListIterator iter = listIterator(size()); iter.hasPrevious();) {
/* 113 */       if (iter.previous() == element) {
/* 114 */         return iter.nextIndex();
/*     */       }
/*     */     } 
/* 117 */     return -1;
/*     */   }
/*     */   
/*     */   public LongIterator iterator() {
/* 121 */     return listIterator();
/*     */   }
/*     */   
/*     */   public LongListIterator listIterator() {
/* 125 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public LongListIterator listIterator(int index) {
/* 129 */     return new RandomAccessLongListIterator(this, index);
/*     */   }
/*     */   
/*     */   public LongList subList(int fromIndex, int toIndex) {
/* 133 */     return new RandomAccessLongSubList(this, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 137 */     if (this == that)
/* 138 */       return true; 
/* 139 */     if (that instanceof LongList) {
/* 140 */       LongList thatList = (LongList)that;
/* 141 */       if (size() != thatList.size()) {
/* 142 */         return false;
/*     */       }
/* 144 */       for (LongIterator thatIter = thatList.iterator(), thisIter = iterator(); thisIter.hasNext();) {
/* 145 */         if (thisIter.next() != thatIter.next()) {
/* 146 */           return false;
/*     */         }
/*     */       } 
/* 149 */       return true;
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 156 */     int hash = 1;
/* 157 */     for (LongIterator iter = iterator(); iter.hasNext(); ) {
/* 158 */       long val = iter.next();
/* 159 */       hash = 31 * hash + (int)(val ^ val >>> 32L);
/*     */     } 
/* 161 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 165 */     StringBuffer buf = new StringBuffer();
/* 166 */     buf.append("[");
/* 167 */     for (LongIterator iter = iterator(); iter.hasNext(); ) {
/* 168 */       buf.append(iter.next());
/* 169 */       if (iter.hasNext()) {
/* 170 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 173 */     buf.append("]");
/* 174 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getModCount() {
/* 182 */     return this._modCount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void incrModCount() {
/* 187 */     this._modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   private int _modCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ComodChecker
/*     */   {
/*     */     private RandomAccessLongList _source;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int _expectedModCount;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ComodChecker(RandomAccessLongList source) {
/* 218 */       this._source = null;
/* 219 */       this._expectedModCount = -1; this._source = source; resyncModCount();
/*     */     }
/*     */     protected RandomAccessLongList getList() { return this._source; } protected void assertNotComodified() throws ConcurrentModificationException { if (this._expectedModCount != getList().getModCount()) throw new ConcurrentModificationException();  } protected void resyncModCount() { this._expectedModCount = getList().getModCount(); }
/*     */   } protected static class RandomAccessLongListIterator extends ComodChecker implements LongListIterator
/*     */   {
/* 224 */     private int _nextIndex; private int _lastReturnedIndex; public boolean hasNext() { assertNotComodified(); return (this._nextIndex < getList().size()); } public boolean hasPrevious() { assertNotComodified(); return (this._nextIndex > 0); } public int nextIndex() { assertNotComodified(); return this._nextIndex; } public int previousIndex() { assertNotComodified(); return this._nextIndex - 1; } public long next() { assertNotComodified(); if (!hasNext()) throw new NoSuchElementException();  long val = getList().get(this._nextIndex); this._lastReturnedIndex = this._nextIndex; this._nextIndex++; return val; } RandomAccessLongListIterator(RandomAccessLongList list, int index) { super(list);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 307 */       this._nextIndex = 0;
/* 308 */       this._lastReturnedIndex = -1; if (index < 0 || index > getList().size()) throw new IndexOutOfBoundsException("Index " + index + " not in [0," + getList().size() + ")");  this._nextIndex = index; resyncModCount(); }
/*     */     public long previous() { assertNotComodified(); if (!hasPrevious()) throw new NoSuchElementException();  long val = getList().get(this._nextIndex - 1); this._lastReturnedIndex = this._nextIndex - 1; this._nextIndex--; return val; } public void add(long value) { assertNotComodified(); getList().add(this._nextIndex, value); this._nextIndex++; this._lastReturnedIndex = -1; resyncModCount(); } public void remove() { assertNotComodified(); if (-1 == this._lastReturnedIndex) throw new IllegalStateException();  getList().removeElementAt(this._lastReturnedIndex); this._lastReturnedIndex = -1; this._nextIndex--; resyncModCount(); } public void set(long value) { assertNotComodified(); if (-1 == this._lastReturnedIndex)
/*     */         throw new IllegalStateException();  getList().set(this._lastReturnedIndex, value); resyncModCount(); }
/*     */   } protected static class RandomAccessLongSubList extends RandomAccessLongList implements LongList
/*     */   {
/* 313 */     RandomAccessLongSubList(RandomAccessLongList list, int fromIndex, int toIndex) { if (fromIndex < 0 || toIndex > list.size())
/* 314 */         throw new IndexOutOfBoundsException(); 
/* 315 */       if (fromIndex > toIndex) {
/* 316 */         throw new IllegalArgumentException();
/*     */       }
/* 318 */       this._list = list;
/* 319 */       this._offset = fromIndex;
/* 320 */       this._limit = toIndex - fromIndex;
/* 321 */       this._comod = new RandomAccessLongList.ComodChecker(list);
/* 322 */       this._comod.resyncModCount(); }
/*     */ 
/*     */ 
/*     */     
/*     */     public long get(int index) {
/* 327 */       checkRange(index);
/* 328 */       this._comod.assertNotComodified();
/* 329 */       return this._list.get(toUnderlyingIndex(index));
/*     */     }
/*     */     
/*     */     public long removeElementAt(int index) {
/* 333 */       checkRange(index);
/* 334 */       this._comod.assertNotComodified();
/* 335 */       long val = this._list.removeElementAt(toUnderlyingIndex(index));
/* 336 */       this._limit--;
/* 337 */       this._comod.resyncModCount();
/* 338 */       incrModCount();
/* 339 */       return val;
/*     */     }
/*     */     
/*     */     public long set(int index, long element) {
/* 343 */       checkRange(index);
/* 344 */       this._comod.assertNotComodified();
/* 345 */       long val = this._list.set(toUnderlyingIndex(index), element);
/* 346 */       incrModCount();
/* 347 */       this._comod.resyncModCount();
/* 348 */       return val;
/*     */     }
/*     */     
/*     */     public void add(int index, long element) {
/* 352 */       checkRangeIncludingEndpoint(index);
/* 353 */       this._comod.assertNotComodified();
/* 354 */       this._list.add(toUnderlyingIndex(index), element);
/* 355 */       this._limit++;
/* 356 */       this._comod.resyncModCount();
/* 357 */       incrModCount();
/*     */     }
/*     */     
/*     */     public int size() {
/* 361 */       this._comod.assertNotComodified();
/* 362 */       return this._limit;
/*     */     }
/*     */     
/*     */     private void checkRange(int index) {
/* 366 */       if (index < 0 || index >= size()) {
/* 367 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + ")");
/*     */       }
/*     */     }
/*     */     
/*     */     private void checkRangeIncludingEndpoint(int index) {
/* 372 */       if (index < 0 || index > size()) {
/* 373 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + "]");
/*     */       }
/*     */     }
/*     */     
/*     */     private int toUnderlyingIndex(int index) {
/* 378 */       return index + this._offset;
/*     */     }
/*     */     
/* 381 */     private int _offset = 0;
/* 382 */     private int _limit = 0;
/* 383 */     private RandomAccessLongList _list = null;
/* 384 */     private RandomAccessLongList.ComodChecker _comod = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/RandomAccessLongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */