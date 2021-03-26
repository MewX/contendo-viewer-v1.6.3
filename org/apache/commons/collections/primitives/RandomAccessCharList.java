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
/*     */ public abstract class RandomAccessCharList
/*     */   extends AbstractCharCollection
/*     */   implements CharList
/*     */ {
/*     */   public abstract char get(int paramInt);
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public char removeElementAt(int index) {
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char set(int index, char element) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, char element) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(char element) {
/*  86 */     add(size(), element);
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, CharCollection collection) {
/*  91 */     boolean modified = false;
/*  92 */     for (CharIterator iter = collection.iterator(); iter.hasNext(); ) {
/*  93 */       add(index++, iter.next());
/*  94 */       modified = true;
/*     */     } 
/*  96 */     return modified;
/*     */   }
/*     */   
/*     */   public int indexOf(char element) {
/* 100 */     int i = 0;
/* 101 */     for (CharIterator iter = iterator(); iter.hasNext(); ) {
/* 102 */       if (iter.next() == element) {
/* 103 */         return i;
/*     */       }
/* 105 */       i++;
/*     */     } 
/*     */     
/* 108 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(char element) {
/* 112 */     for (CharListIterator iter = listIterator(size()); iter.hasPrevious();) {
/* 113 */       if (iter.previous() == element) {
/* 114 */         return iter.nextIndex();
/*     */       }
/*     */     } 
/* 117 */     return -1;
/*     */   }
/*     */   
/*     */   public CharIterator iterator() {
/* 121 */     return listIterator();
/*     */   }
/*     */   
/*     */   public CharListIterator listIterator() {
/* 125 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public CharListIterator listIterator(int index) {
/* 129 */     return new RandomAccessCharListIterator(this, index);
/*     */   }
/*     */   
/*     */   public CharList subList(int fromIndex, int toIndex) {
/* 133 */     return new RandomAccessCharSubList(this, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 137 */     if (this == that)
/* 138 */       return true; 
/* 139 */     if (that instanceof CharList) {
/* 140 */       CharList thatList = (CharList)that;
/* 141 */       if (size() != thatList.size()) {
/* 142 */         return false;
/*     */       }
/* 144 */       for (CharIterator thatIter = thatList.iterator(), thisIter = iterator(); thisIter.hasNext();) {
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
/* 157 */     for (CharIterator iter = iterator(); iter.hasNext();) {
/* 158 */       hash = 31 * hash + iter.next();
/*     */     }
/* 160 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 165 */     return new String(toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getModCount() {
/* 173 */     return this._modCount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void incrModCount() {
/* 178 */     this._modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   private int _modCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ComodChecker
/*     */   {
/*     */     private RandomAccessCharList _source;
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
/*     */     ComodChecker(RandomAccessCharList source) {
/* 209 */       this._source = null;
/* 210 */       this._expectedModCount = -1; this._source = source; resyncModCount();
/*     */     }
/*     */     protected RandomAccessCharList getList() { return this._source; } protected void assertNotComodified() throws ConcurrentModificationException { if (this._expectedModCount != getList().getModCount()) throw new ConcurrentModificationException();  } protected void resyncModCount() { this._expectedModCount = getList().getModCount(); }
/*     */   } protected static class RandomAccessCharListIterator extends ComodChecker implements CharListIterator
/*     */   {
/* 215 */     private int _nextIndex; private int _lastReturnedIndex; public boolean hasNext() { assertNotComodified(); return (this._nextIndex < getList().size()); } public boolean hasPrevious() { assertNotComodified(); return (this._nextIndex > 0); } public int nextIndex() { assertNotComodified(); return this._nextIndex; } public int previousIndex() { assertNotComodified(); return this._nextIndex - 1; } public char next() { assertNotComodified(); if (!hasNext()) throw new NoSuchElementException();  char val = getList().get(this._nextIndex); this._lastReturnedIndex = this._nextIndex; this._nextIndex++; return val; } RandomAccessCharListIterator(RandomAccessCharList list, int index) { super(list);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       this._nextIndex = 0;
/* 299 */       this._lastReturnedIndex = -1; if (index < 0 || index > getList().size()) throw new IndexOutOfBoundsException("Index " + index + " not in [0," + getList().size() + ")");  this._nextIndex = index; resyncModCount(); }
/*     */     public char previous() { assertNotComodified(); if (!hasPrevious()) throw new NoSuchElementException();  char val = getList().get(this._nextIndex - 1); this._lastReturnedIndex = this._nextIndex - 1; this._nextIndex--; return val; } public void add(char value) { assertNotComodified(); getList().add(this._nextIndex, value); this._nextIndex++; this._lastReturnedIndex = -1; resyncModCount(); } public void remove() { assertNotComodified(); if (-1 == this._lastReturnedIndex) throw new IllegalStateException();  getList().removeElementAt(this._lastReturnedIndex); this._lastReturnedIndex = -1; this._nextIndex--; resyncModCount(); } public void set(char value) { assertNotComodified(); if (-1 == this._lastReturnedIndex)
/*     */         throw new IllegalStateException();  getList().set(this._lastReturnedIndex, value); resyncModCount(); }
/*     */   } protected static class RandomAccessCharSubList extends RandomAccessCharList implements CharList
/*     */   {
/* 304 */     RandomAccessCharSubList(RandomAccessCharList list, int fromIndex, int toIndex) { if (fromIndex < 0 || toIndex > list.size())
/* 305 */         throw new IndexOutOfBoundsException(); 
/* 306 */       if (fromIndex > toIndex) {
/* 307 */         throw new IllegalArgumentException();
/*     */       }
/* 309 */       this._list = list;
/* 310 */       this._offset = fromIndex;
/* 311 */       this._limit = toIndex - fromIndex;
/* 312 */       this._comod = new RandomAccessCharList.ComodChecker(list);
/* 313 */       this._comod.resyncModCount(); }
/*     */ 
/*     */ 
/*     */     
/*     */     public char get(int index) {
/* 318 */       checkRange(index);
/* 319 */       this._comod.assertNotComodified();
/* 320 */       return this._list.get(toUnderlyingIndex(index));
/*     */     }
/*     */     
/*     */     public char removeElementAt(int index) {
/* 324 */       checkRange(index);
/* 325 */       this._comod.assertNotComodified();
/* 326 */       char val = this._list.removeElementAt(toUnderlyingIndex(index));
/* 327 */       this._limit--;
/* 328 */       this._comod.resyncModCount();
/* 329 */       incrModCount();
/* 330 */       return val;
/*     */     }
/*     */     
/*     */     public char set(int index, char element) {
/* 334 */       checkRange(index);
/* 335 */       this._comod.assertNotComodified();
/* 336 */       char val = this._list.set(toUnderlyingIndex(index), element);
/* 337 */       incrModCount();
/* 338 */       this._comod.resyncModCount();
/* 339 */       return val;
/*     */     }
/*     */     
/*     */     public void add(int index, char element) {
/* 343 */       checkRangeIncludingEndpoint(index);
/* 344 */       this._comod.assertNotComodified();
/* 345 */       this._list.add(toUnderlyingIndex(index), element);
/* 346 */       this._limit++;
/* 347 */       this._comod.resyncModCount();
/* 348 */       incrModCount();
/*     */     }
/*     */     
/*     */     public int size() {
/* 352 */       this._comod.assertNotComodified();
/* 353 */       return this._limit;
/*     */     }
/*     */     
/*     */     private void checkRange(int index) {
/* 357 */       if (index < 0 || index >= size()) {
/* 358 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + ")");
/*     */       }
/*     */     }
/*     */     
/*     */     private void checkRangeIncludingEndpoint(int index) {
/* 363 */       if (index < 0 || index > size()) {
/* 364 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + "]");
/*     */       }
/*     */     }
/*     */     
/*     */     private int toUnderlyingIndex(int index) {
/* 369 */       return index + this._offset;
/*     */     }
/*     */     
/* 372 */     private int _offset = 0;
/* 373 */     private int _limit = 0;
/* 374 */     private RandomAccessCharList _list = null;
/* 375 */     private RandomAccessCharList.ComodChecker _comod = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/RandomAccessCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */