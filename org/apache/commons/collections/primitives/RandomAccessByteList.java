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
/*     */ public abstract class RandomAccessByteList
/*     */   extends AbstractByteCollection
/*     */   implements ByteList
/*     */ {
/*     */   public abstract byte get(int paramInt);
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public byte removeElementAt(int index) {
/*  62 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte set(int index, byte element) {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, byte element) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(byte element) {
/*  86 */     add(size(), element);
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, ByteCollection collection) {
/*  91 */     boolean modified = false;
/*  92 */     for (ByteIterator iter = collection.iterator(); iter.hasNext(); ) {
/*  93 */       add(index++, iter.next());
/*  94 */       modified = true;
/*     */     } 
/*  96 */     return modified;
/*     */   }
/*     */   
/*     */   public int indexOf(byte element) {
/* 100 */     int i = 0;
/* 101 */     for (ByteIterator iter = iterator(); iter.hasNext(); ) {
/* 102 */       if (iter.next() == element) {
/* 103 */         return i;
/*     */       }
/* 105 */       i++;
/*     */     } 
/*     */     
/* 108 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(byte element) {
/* 112 */     for (ByteListIterator iter = listIterator(size()); iter.hasPrevious();) {
/* 113 */       if (iter.previous() == element) {
/* 114 */         return iter.nextIndex();
/*     */       }
/*     */     } 
/* 117 */     return -1;
/*     */   }
/*     */   
/*     */   public ByteIterator iterator() {
/* 121 */     return listIterator();
/*     */   }
/*     */   
/*     */   public ByteListIterator listIterator() {
/* 125 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ByteListIterator listIterator(int index) {
/* 129 */     return new RandomAccessByteListIterator(this, index);
/*     */   }
/*     */   
/*     */   public ByteList subList(int fromIndex, int toIndex) {
/* 133 */     return new RandomAccessByteSubList(this, fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 137 */     if (this == that)
/* 138 */       return true; 
/* 139 */     if (that instanceof ByteList) {
/* 140 */       ByteList thatList = (ByteList)that;
/* 141 */       if (size() != thatList.size()) {
/* 142 */         return false;
/*     */       }
/* 144 */       for (ByteIterator thatIter = thatList.iterator(), thisIter = iterator(); thisIter.hasNext();) {
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
/* 157 */     for (ByteIterator iter = iterator(); iter.hasNext();) {
/* 158 */       hash = 31 * hash + iter.next();
/*     */     }
/* 160 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 164 */     StringBuffer buf = new StringBuffer();
/* 165 */     buf.append("[");
/* 166 */     for (ByteIterator iter = iterator(); iter.hasNext(); ) {
/* 167 */       buf.append(iter.next());
/* 168 */       if (iter.hasNext()) {
/* 169 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 172 */     buf.append("]");
/* 173 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getModCount() {
/* 181 */     return this._modCount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void incrModCount() {
/* 186 */     this._modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   private int _modCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ComodChecker
/*     */   {
/*     */     private RandomAccessByteList _source;
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
/*     */     ComodChecker(RandomAccessByteList source) {
/* 217 */       this._source = null;
/* 218 */       this._expectedModCount = -1; this._source = source; resyncModCount();
/*     */     }
/*     */     protected RandomAccessByteList getList() { return this._source; } protected void assertNotComodified() throws ConcurrentModificationException { if (this._expectedModCount != getList().getModCount()) throw new ConcurrentModificationException();  } protected void resyncModCount() { this._expectedModCount = getList().getModCount(); }
/*     */   } protected static class RandomAccessByteListIterator extends ComodChecker implements ByteListIterator
/*     */   {
/* 223 */     private int _nextIndex; private int _lastReturnedIndex; public boolean hasNext() { assertNotComodified(); return (this._nextIndex < getList().size()); } public boolean hasPrevious() { assertNotComodified(); return (this._nextIndex > 0); } public int nextIndex() { assertNotComodified(); return this._nextIndex; } public int previousIndex() { assertNotComodified(); return this._nextIndex - 1; } public byte next() { assertNotComodified(); if (!hasNext()) throw new NoSuchElementException();  byte val = getList().get(this._nextIndex); this._lastReturnedIndex = this._nextIndex; this._nextIndex++; return val; } RandomAccessByteListIterator(RandomAccessByteList list, int index) { super(list);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */     public byte previous() { assertNotComodified(); if (!hasPrevious()) throw new NoSuchElementException();  byte val = getList().get(this._nextIndex - 1); this._lastReturnedIndex = this._nextIndex - 1; this._nextIndex--; return val; } public void add(byte value) { assertNotComodified(); getList().add(this._nextIndex, value); this._nextIndex++; this._lastReturnedIndex = -1; resyncModCount(); } public void remove() { assertNotComodified(); if (-1 == this._lastReturnedIndex) throw new IllegalStateException();  getList().removeElementAt(this._lastReturnedIndex); this._lastReturnedIndex = -1; this._nextIndex--; resyncModCount(); } public void set(byte value) { assertNotComodified(); if (-1 == this._lastReturnedIndex)
/*     */         throw new IllegalStateException();  getList().set(this._lastReturnedIndex, value); resyncModCount(); }
/*     */   } protected static class RandomAccessByteSubList extends RandomAccessByteList implements ByteList
/*     */   {
/* 312 */     RandomAccessByteSubList(RandomAccessByteList list, int fromIndex, int toIndex) { if (fromIndex < 0 || toIndex > list.size())
/* 313 */         throw new IndexOutOfBoundsException(); 
/* 314 */       if (fromIndex > toIndex) {
/* 315 */         throw new IllegalArgumentException();
/*     */       }
/* 317 */       this._list = list;
/* 318 */       this._offset = fromIndex;
/* 319 */       this._limit = toIndex - fromIndex;
/* 320 */       this._comod = new RandomAccessByteList.ComodChecker(list);
/* 321 */       this._comod.resyncModCount(); }
/*     */ 
/*     */ 
/*     */     
/*     */     public byte get(int index) {
/* 326 */       checkRange(index);
/* 327 */       this._comod.assertNotComodified();
/* 328 */       return this._list.get(toUnderlyingIndex(index));
/*     */     }
/*     */     
/*     */     public byte removeElementAt(int index) {
/* 332 */       checkRange(index);
/* 333 */       this._comod.assertNotComodified();
/* 334 */       byte val = this._list.removeElementAt(toUnderlyingIndex(index));
/* 335 */       this._limit--;
/* 336 */       this._comod.resyncModCount();
/* 337 */       incrModCount();
/* 338 */       return val;
/*     */     }
/*     */     
/*     */     public byte set(int index, byte element) {
/* 342 */       checkRange(index);
/* 343 */       this._comod.assertNotComodified();
/* 344 */       byte val = this._list.set(toUnderlyingIndex(index), element);
/* 345 */       incrModCount();
/* 346 */       this._comod.resyncModCount();
/* 347 */       return val;
/*     */     }
/*     */     
/*     */     public void add(int index, byte element) {
/* 351 */       checkRangeIncludingEndpoint(index);
/* 352 */       this._comod.assertNotComodified();
/* 353 */       this._list.add(toUnderlyingIndex(index), element);
/* 354 */       this._limit++;
/* 355 */       this._comod.resyncModCount();
/* 356 */       incrModCount();
/*     */     }
/*     */     
/*     */     public int size() {
/* 360 */       this._comod.assertNotComodified();
/* 361 */       return this._limit;
/*     */     }
/*     */     
/*     */     private void checkRange(int index) {
/* 365 */       if (index < 0 || index >= size()) {
/* 366 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + ")");
/*     */       }
/*     */     }
/*     */     
/*     */     private void checkRangeIncludingEndpoint(int index) {
/* 371 */       if (index < 0 || index > size()) {
/* 372 */         throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + "]");
/*     */       }
/*     */     }
/*     */     
/*     */     private int toUnderlyingIndex(int index) {
/* 377 */       return index + this._offset;
/*     */     }
/*     */     
/* 380 */     private int _offset = 0;
/* 381 */     private int _limit = 0;
/* 382 */     private RandomAccessByteList _list = null;
/* 383 */     private RandomAccessByteList.ComodChecker _comod = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/RandomAccessByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */