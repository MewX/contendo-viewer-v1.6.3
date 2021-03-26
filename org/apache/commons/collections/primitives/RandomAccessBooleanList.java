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
/*     */ public abstract class RandomAccessBooleanList
/*     */   extends AbstractBooleanCollection
/*     */   implements BooleanList
/*     */ {
/*     */   public abstract boolean get(int paramInt);
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public boolean removeElementAt(int index) {
/*  59 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean set(int index, boolean element) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, boolean element) {
/*  75 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(boolean element) {
/*  83 */     add(size(), element);
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, BooleanCollection collection) {
/*  88 */     boolean modified = false;
/*  89 */     for (BooleanIterator iter = collection.iterator(); iter.hasNext(); ) {
/*  90 */       add(index++, iter.next());
/*  91 */       modified = true;
/*     */     } 
/*  93 */     return modified;
/*     */   }
/*     */   
/*     */   public int indexOf(boolean element) {
/*  97 */     int i = 0;
/*  98 */     for (BooleanIterator iter = iterator(); iter.hasNext(); ) {
/*  99 */       if (iter.next() == element) {
/* 100 */         return i;
/*     */       }
/* 102 */       i++;
/*     */     } 
/*     */     
/* 105 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(boolean element) {
/* 109 */     for (BooleanListIterator iter = listIterator(size()); iter.hasPrevious();) {
/* 110 */       if (iter.previous() == element) {
/* 111 */         return iter.nextIndex();
/*     */       }
/*     */     } 
/* 114 */     return -1;
/*     */   }
/*     */   
/*     */   public BooleanIterator iterator() {
/* 118 */     return listIterator();
/*     */   }
/*     */   
/*     */   public BooleanListIterator listIterator() {
/* 122 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public BooleanListIterator listIterator(int index) {
/* 126 */     return new RandomAccessBooleanListIterator(this, index);
/*     */   }
/*     */   
/*     */   public BooleanList subList(int fromIndex, int toIndex) {
/* 130 */     return new RandomAccessBooleanSubList(this, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 134 */     if (this == that)
/* 135 */       return true; 
/* 136 */     if (that instanceof BooleanList) {
/* 137 */       BooleanList thatList = (BooleanList)that;
/* 138 */       if (size() != thatList.size()) {
/* 139 */         return false;
/*     */       }
/* 141 */       BooleanIterator thatIter = thatList.iterator();
/* 142 */       for (BooleanIterator thisIter = iterator(); thisIter.hasNext();) {
/* 143 */         if (thisIter.next() != thatIter.next()) {
/* 144 */           return false;
/*     */         }
/*     */       } 
/* 147 */       return true;
/*     */     } 
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 154 */     int hash = 1;
/* 155 */     for (BooleanIterator iter = iterator(); iter.hasNext();) {
/* 156 */       hash = 31 * hash + (iter.next() ? 1231 : 1237);
/*     */     }
/* 158 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 162 */     StringBuffer buf = new StringBuffer();
/* 163 */     buf.append("[");
/* 164 */     for (BooleanIterator iter = iterator(); iter.hasNext(); ) {
/* 165 */       buf.append(iter.next());
/* 166 */       if (iter.hasNext()) {
/* 167 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 170 */     buf.append("]");
/* 171 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getModCount() {
/* 179 */     return this._modCount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void incrModCount() {
/* 184 */     this._modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   private int _modCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ComodChecker
/*     */   {
/*     */     private RandomAccessBooleanList _source;
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
/*     */     ComodChecker(RandomAccessBooleanList source) {
/* 215 */       this._source = null;
/* 216 */       this._expectedModCount = -1; this._source = source; resyncModCount();
/*     */     }
/*     */     protected RandomAccessBooleanList getList() { return this._source; }
/*     */     protected void assertNotComodified() throws ConcurrentModificationException { if (this._expectedModCount != getList().getModCount()) throw new ConcurrentModificationException();  } protected void resyncModCount() { this._expectedModCount = getList().getModCount(); }
/*     */   } protected static class RandomAccessBooleanListIterator extends ComodChecker implements BooleanListIterator
/*     */   {
/* 222 */     private int _nextIndex; private int _lastReturnedIndex; public boolean hasNext() { assertNotComodified(); return (this._nextIndex < getList().size()); } public boolean hasPrevious() { assertNotComodified(); return (this._nextIndex > 0); } public int nextIndex() { assertNotComodified(); return this._nextIndex; } public int previousIndex() { assertNotComodified(); return this._nextIndex - 1; } public boolean next() { assertNotComodified(); if (!hasNext()) throw new NoSuchElementException();  boolean val = getList().get(this._nextIndex); this._lastReturnedIndex = this._nextIndex; this._nextIndex++; return val; } RandomAccessBooleanListIterator(RandomAccessBooleanList list, int index) { super(list);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 306 */       this._nextIndex = 0;
/* 307 */       this._lastReturnedIndex = -1; if (index < 0 || index > getList().size()) throw new IndexOutOfBoundsException("Index " + index + " not in [0," + getList().size() + ")");  this._nextIndex = index; resyncModCount(); }
/*     */     public boolean previous() { assertNotComodified(); if (!hasPrevious())
/*     */         throw new NoSuchElementException();  boolean val = getList().get(this._nextIndex - 1); this._lastReturnedIndex = this._nextIndex - 1; this._nextIndex--; return val; } public void add(boolean value) { assertNotComodified(); getList().add(this._nextIndex, value); this._nextIndex++; this._lastReturnedIndex = -1; resyncModCount(); } public void remove() { assertNotComodified(); if (-1 == this._lastReturnedIndex)
/*     */         throw new IllegalStateException();  getList().removeElementAt(this._lastReturnedIndex); this._lastReturnedIndex = -1; this._nextIndex--; resyncModCount(); } public void set(boolean value) { assertNotComodified(); if (-1 == this._lastReturnedIndex)
/*     */         throw new IllegalStateException();  getList().set(this._lastReturnedIndex, value); resyncModCount(); }
/*     */   } protected static class RandomAccessBooleanSubList extends RandomAccessBooleanList implements BooleanList
/*     */   {
/* 314 */     RandomAccessBooleanSubList(RandomAccessBooleanList list, int fromIndex, int toIndex) { if (fromIndex < 0 || toIndex > list.size())
/* 315 */         throw new IndexOutOfBoundsException(); 
/* 316 */       if (fromIndex > toIndex) {
/* 317 */         throw new IllegalArgumentException();
/*     */       }
/* 319 */       this._list = list;
/* 320 */       this._offset = fromIndex;
/* 321 */       this._limit = toIndex - fromIndex;
/* 322 */       this._comod = new RandomAccessBooleanList.ComodChecker(list);
/* 323 */       this._comod.resyncModCount(); }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean get(int index) {
/* 328 */       checkRange(index);
/* 329 */       this._comod.assertNotComodified();
/* 330 */       return this._list.get(toUnderlyingIndex(index));
/*     */     }
/*     */     
/*     */     public boolean removeElementAt(int index) {
/* 334 */       checkRange(index);
/* 335 */       this._comod.assertNotComodified();
/* 336 */       boolean val = this._list.removeElementAt(toUnderlyingIndex(index));
/* 337 */       this._limit--;
/* 338 */       this._comod.resyncModCount();
/* 339 */       incrModCount();
/* 340 */       return val;
/*     */     }
/*     */     
/*     */     public boolean set(int index, boolean element) {
/* 344 */       checkRange(index);
/* 345 */       this._comod.assertNotComodified();
/* 346 */       boolean val = this._list.set(toUnderlyingIndex(index), element);
/* 347 */       incrModCount();
/* 348 */       this._comod.resyncModCount();
/* 349 */       return val;
/*     */     }
/*     */     
/*     */     public void add(int index, boolean element) {
/* 353 */       checkRangeIncludingEndpoint(index);
/* 354 */       this._comod.assertNotComodified();
/* 355 */       this._list.add(toUnderlyingIndex(index), element);
/* 356 */       this._limit++;
/* 357 */       this._comod.resyncModCount();
/* 358 */       incrModCount();
/*     */     }
/*     */     
/*     */     public int size() {
/* 362 */       this._comod.assertNotComodified();
/* 363 */       return this._limit;
/*     */     }
/*     */     
/*     */     private void checkRange(int index) {
/* 367 */       if (index < 0 || index >= size()) {
/* 368 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void checkRangeIncludingEndpoint(int index) {
/* 374 */       if (index < 0 || index > size()) {
/* 375 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + "]");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private int toUnderlyingIndex(int index) {
/* 381 */       return index + this._offset;
/*     */     }
/*     */     
/* 384 */     private int _offset = 0;
/* 385 */     private int _limit = 0;
/* 386 */     private RandomAccessBooleanList _list = null;
/* 387 */     private RandomAccessBooleanList.ComodChecker _comod = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/RandomAccessBooleanList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */