/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CursorableLinkedList
/*      */   implements Serializable, List
/*      */ {
/*      */   private static final long serialVersionUID = 8836393098519411393L;
/*      */   
/*      */   public boolean add(Object o) {
/*   67 */     insertListable(this._head.prev(), null, o);
/*   68 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, Object element) {
/*   87 */     if (index == this._size) {
/*   88 */       add(element);
/*      */     } else {
/*   90 */       if (index < 0 || index > this._size) {
/*   91 */         throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " > " + this._size);
/*      */       }
/*   93 */       Listable succ = isEmpty() ? null : getListableAt(index);
/*   94 */       Listable pred = (null == succ) ? null : succ.prev();
/*   95 */       insertListable(pred, succ, element);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(Collection c) {
/*  117 */     if (c.isEmpty()) {
/*  118 */       return false;
/*      */     }
/*  120 */     Iterator it = c.iterator();
/*  121 */     while (it.hasNext()) {
/*  122 */       insertListable(this._head.prev(), null, it.next());
/*      */     }
/*  124 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/*  153 */     if (c.isEmpty())
/*  154 */       return false; 
/*  155 */     if (this._size == index || this._size == 0) {
/*  156 */       return addAll(c);
/*      */     }
/*  158 */     Listable succ = getListableAt(index);
/*  159 */     Listable pred = (null == succ) ? null : succ.prev();
/*  160 */     Iterator it = c.iterator();
/*  161 */     while (it.hasNext()) {
/*  162 */       pred = insertListable(pred, succ, it.next());
/*      */     }
/*  164 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addFirst(Object o) {
/*  176 */     insertListable(null, this._head.next(), o);
/*  177 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addLast(Object o) {
/*  188 */     insertListable(this._head.prev(), null, o);
/*  189 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  206 */     Iterator it = iterator();
/*  207 */     while (it.hasNext()) {
/*  208 */       it.next();
/*  209 */       it.remove();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(Object o) {
/*  223 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  224 */       if ((null == o && null == elt.value()) || (o != null && o.equals(elt.value())))
/*      */       {
/*  226 */         return true;
/*      */       }
/*      */     } 
/*  229 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAll(Collection c) {
/*  241 */     Iterator it = c.iterator();
/*  242 */     while (it.hasNext()) {
/*  243 */       if (!contains(it.next())) {
/*  244 */         return false;
/*      */       }
/*      */     } 
/*  247 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cursor cursor() {
/*  276 */     return new Cursor(this, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cursor cursor(int i) {
/*  296 */     return new Cursor(this, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/*  314 */     if (o == this)
/*  315 */       return true; 
/*  316 */     if (!(o instanceof List)) {
/*  317 */       return false;
/*      */     }
/*  319 */     Iterator it = ((List)o).listIterator();
/*  320 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  321 */       if (!it.hasNext() || ((null == elt.value()) ? (null != it.next()) : !elt.value().equals(it.next()))) {
/*  322 */         return false;
/*      */       }
/*      */     } 
/*  325 */     return !it.hasNext();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(int index) {
/*  338 */     return getListableAt(index).value();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getFirst() {
/*      */     try {
/*  346 */       return this._head.next().value();
/*  347 */     } catch (NullPointerException e) {
/*  348 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getLast() {
/*      */     try {
/*  357 */       return this._head.prev().value();
/*  358 */     } catch (NullPointerException e) {
/*  359 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  385 */     int hash = 1;
/*  386 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  387 */       hash = 31 * hash + ((null == elt.value()) ? 0 : elt.value().hashCode());
/*      */     }
/*  389 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOf(Object o) {
/*  404 */     int ndx = 0;
/*      */ 
/*      */ 
/*      */     
/*  408 */     if (null == o) {
/*  409 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  410 */         if (null == elt.value()) {
/*  411 */           return ndx;
/*      */         }
/*  413 */         ndx++;
/*      */       } 
/*      */     } else {
/*      */       
/*  417 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  418 */         if (o.equals(elt.value())) {
/*  419 */           return ndx;
/*      */         }
/*  421 */         ndx++;
/*      */       } 
/*      */     } 
/*  424 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  432 */     return (0 == this._size);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator iterator() {
/*  440 */     return listIterator(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int lastIndexOf(Object o) {
/*  455 */     int ndx = this._size - 1;
/*      */ 
/*      */ 
/*      */     
/*  459 */     if (null == o) {
/*  460 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  461 */         if (null == elt.value()) {
/*  462 */           return ndx;
/*      */         }
/*  464 */         ndx--;
/*      */       } 
/*      */     } else {
/*  467 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  468 */         if (o.equals(elt.value())) {
/*  469 */           return ndx;
/*      */         }
/*  471 */         ndx--;
/*      */       } 
/*      */     } 
/*  474 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator listIterator() {
/*  482 */     return listIterator(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator listIterator(int index) {
/*  490 */     if (index < 0 || index > this._size) {
/*  491 */       throw new IndexOutOfBoundsException(index + " < 0 or > " + this._size);
/*      */     }
/*  493 */     return new ListIter(this, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object o) {
/*  507 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  508 */       if (null == o && null == elt.value()) {
/*  509 */         removeListable(elt);
/*  510 */         return true;
/*  511 */       }  if (o != null && o.equals(elt.value())) {
/*  512 */         removeListable(elt);
/*  513 */         return true;
/*      */       } 
/*      */     } 
/*  516 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(int index) {
/*  532 */     Listable elt = getListableAt(index);
/*  533 */     Object ret = elt.value();
/*  534 */     removeListable(elt);
/*  535 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeAll(Collection c) {
/*  547 */     if (0 == c.size() || 0 == this._size) {
/*  548 */       return false;
/*      */     }
/*  550 */     boolean changed = false;
/*  551 */     Iterator it = iterator();
/*  552 */     while (it.hasNext()) {
/*  553 */       if (c.contains(it.next())) {
/*  554 */         it.remove();
/*  555 */         changed = true;
/*      */       } 
/*      */     } 
/*  558 */     return changed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object removeFirst() {
/*  566 */     if (this._head.next() != null) {
/*  567 */       Object val = this._head.next().value();
/*  568 */       removeListable(this._head.next());
/*  569 */       return val;
/*      */     } 
/*  571 */     throw new NoSuchElementException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object removeLast() {
/*  579 */     if (this._head.prev() != null) {
/*  580 */       Object val = this._head.prev().value();
/*  581 */       removeListable(this._head.prev());
/*  582 */       return val;
/*      */     } 
/*  584 */     throw new NoSuchElementException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean retainAll(Collection c) {
/*  599 */     boolean changed = false;
/*  600 */     Iterator it = iterator();
/*  601 */     while (it.hasNext()) {
/*  602 */       if (!c.contains(it.next())) {
/*  603 */         it.remove();
/*  604 */         changed = true;
/*      */       } 
/*      */     } 
/*  607 */     return changed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object set(int index, Object element) {
/*  626 */     Listable elt = getListableAt(index);
/*  627 */     Object val = elt.setValue(element);
/*  628 */     broadcastListableChanged(elt);
/*  629 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  637 */     return this._size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray() {
/*  648 */     Object[] array = new Object[this._size];
/*  649 */     int i = 0;
/*  650 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  651 */       array[i++] = elt.value();
/*      */     }
/*  653 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/*  672 */     if (a.length < this._size) {
/*  673 */       a = (Object[])Array.newInstance(a.getClass().getComponentType(), this._size);
/*      */     }
/*  675 */     int i = 0;
/*  676 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  677 */       a[i++] = elt.value();
/*      */     }
/*  679 */     if (a.length > this._size) {
/*  680 */       a[this._size] = null;
/*      */     }
/*  682 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  690 */     StringBuffer buf = new StringBuffer();
/*  691 */     buf.append("[");
/*  692 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  693 */       if (this._head.next() != elt) {
/*  694 */         buf.append(", ");
/*      */       }
/*  696 */       buf.append(elt.value());
/*      */     } 
/*  698 */     buf.append("]");
/*  699 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List subList(int i, int j) {
/*  707 */     if (i < 0 || j > this._size || i > j)
/*  708 */       throw new IndexOutOfBoundsException(); 
/*  709 */     if (i == 0 && j == this._size) {
/*  710 */       return this;
/*      */     }
/*  712 */     return new CursorableSubList(this, i, j);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Listable insertListable(Listable before, Listable after, Object value) {
/*  727 */     this._modCount++;
/*  728 */     this._size++;
/*  729 */     Listable elt = new Listable(before, after, value);
/*  730 */     if (null != before) {
/*  731 */       before.setNext(elt);
/*      */     } else {
/*  733 */       this._head.setNext(elt);
/*      */     } 
/*      */     
/*  736 */     if (null != after) {
/*  737 */       after.setPrev(elt);
/*      */     } else {
/*  739 */       this._head.setPrev(elt);
/*      */     } 
/*  741 */     broadcastListableInserted(elt);
/*  742 */     return elt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeListable(Listable elt) {
/*  751 */     this._modCount++;
/*  752 */     this._size--;
/*  753 */     if (this._head.next() == elt) {
/*  754 */       this._head.setNext(elt.next());
/*      */     }
/*  756 */     if (null != elt.next()) {
/*  757 */       elt.next().setPrev(elt.prev());
/*      */     }
/*  759 */     if (this._head.prev() == elt) {
/*  760 */       this._head.setPrev(elt.prev());
/*      */     }
/*  762 */     if (null != elt.prev()) {
/*  763 */       elt.prev().setNext(elt.next());
/*      */     }
/*  765 */     broadcastListableRemoved(elt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Listable getListableAt(int index) {
/*  777 */     if (index < 0 || index >= this._size) {
/*  778 */       throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " >= " + this._size);
/*      */     }
/*  780 */     if (index <= this._size / 2) {
/*  781 */       Listable listable = this._head.next();
/*  782 */       for (int j = 0; j < index; j++) {
/*  783 */         listable = listable.next();
/*      */       }
/*  785 */       return listable;
/*      */     } 
/*  787 */     Listable elt = this._head.prev();
/*  788 */     for (int i = this._size - 1; i > index; i--) {
/*  789 */       elt = elt.prev();
/*      */     }
/*  791 */     return elt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void registerCursor(Cursor cur) {
/*  802 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  803 */       WeakReference ref = it.next();
/*  804 */       if (ref.get() == null) {
/*  805 */         it.remove();
/*      */       }
/*      */     } 
/*      */     
/*  809 */     this._cursors.add(new WeakReference(cur));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void unregisterCursor(Cursor cur) {
/*  817 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  818 */       WeakReference ref = it.next();
/*  819 */       Cursor cursor = ref.get();
/*  820 */       if (cursor == null) {
/*      */ 
/*      */ 
/*      */         
/*  824 */         it.remove(); continue;
/*      */       } 
/*  826 */       if (cursor == cur) {
/*  827 */         ref.clear();
/*  828 */         it.remove();
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void invalidateCursors() {
/*  839 */     Iterator it = this._cursors.iterator();
/*  840 */     while (it.hasNext()) {
/*  841 */       WeakReference ref = it.next();
/*  842 */       Cursor cursor = ref.get();
/*  843 */       if (cursor != null) {
/*      */         
/*  845 */         cursor.invalidate();
/*  846 */         ref.clear();
/*      */       } 
/*  848 */       it.remove();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void broadcastListableChanged(Listable elt) {
/*  858 */     Iterator it = this._cursors.iterator();
/*  859 */     while (it.hasNext()) {
/*  860 */       WeakReference ref = it.next();
/*  861 */       Cursor cursor = ref.get();
/*  862 */       if (cursor == null) {
/*  863 */         it.remove(); continue;
/*      */       } 
/*  865 */       cursor.listableChanged(elt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void broadcastListableRemoved(Listable elt) {
/*  875 */     Iterator it = this._cursors.iterator();
/*  876 */     while (it.hasNext()) {
/*  877 */       WeakReference ref = it.next();
/*  878 */       Cursor cursor = ref.get();
/*  879 */       if (cursor == null) {
/*  880 */         it.remove(); continue;
/*      */       } 
/*  882 */       cursor.listableRemoved(elt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void broadcastListableInserted(Listable elt) {
/*  892 */     Iterator it = this._cursors.iterator();
/*  893 */     while (it.hasNext()) {
/*  894 */       WeakReference ref = it.next();
/*  895 */       Cursor cursor = ref.get();
/*  896 */       if (cursor == null) {
/*  897 */         it.remove(); continue;
/*      */       } 
/*  899 */       cursor.listableInserted(elt);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  905 */     out.defaultWriteObject();
/*  906 */     out.writeInt(this._size);
/*  907 */     Listable cur = this._head.next();
/*  908 */     while (cur != null) {
/*  909 */       out.writeObject(cur.value());
/*  910 */       cur = cur.next();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  915 */     in.defaultReadObject();
/*  916 */     this._size = 0;
/*  917 */     this._modCount = 0;
/*  918 */     this._cursors = new ArrayList();
/*  919 */     this._head = new Listable(null, null, null);
/*  920 */     int size = in.readInt();
/*  921 */     for (int i = 0; i < size; i++) {
/*  922 */       add(in.readObject());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  929 */   protected transient int _size = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   protected transient Listable _head = new Listable(null, null, null);
/*      */ 
/*      */   
/*  946 */   protected transient int _modCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   protected transient List _cursors = new ArrayList();
/*      */   
/*      */   static class Listable
/*      */     implements Serializable
/*      */   {
/*  957 */     private Listable _prev = null;
/*  958 */     private Listable _next = null;
/*  959 */     private Object _val = null;
/*      */     
/*      */     Listable(Listable prev, Listable next, Object val) {
/*  962 */       this._prev = prev;
/*  963 */       this._next = next;
/*  964 */       this._val = val;
/*      */     }
/*      */     
/*      */     Listable next() {
/*  968 */       return this._next;
/*      */     }
/*      */     
/*      */     Listable prev() {
/*  972 */       return this._prev;
/*      */     }
/*      */     
/*      */     Object value() {
/*  976 */       return this._val;
/*      */     }
/*      */     
/*      */     void setNext(Listable next) {
/*  980 */       this._next = next;
/*      */     }
/*      */     
/*      */     void setPrev(Listable prev) {
/*  984 */       this._prev = prev;
/*      */     }
/*      */     
/*      */     Object setValue(Object val) {
/*  988 */       Object temp = this._val;
/*  989 */       this._val = val;
/*  990 */       return temp;
/*      */     } }
/*      */   
/*      */   class ListIter implements ListIterator { CursorableLinkedList.Listable _cur;
/*      */     CursorableLinkedList.Listable _lastReturned;
/*      */     int _expectedModCount;
/*      */     int _nextIndex;
/*      */     private final CursorableLinkedList this$0;
/*      */     
/*      */     ListIter(CursorableLinkedList this$0, int index) {
/* 1000 */       this.this$0 = this$0; this._cur = null; this._lastReturned = null; this._expectedModCount = this.this$0._modCount; this._nextIndex = 0;
/* 1001 */       if (index == 0) {
/* 1002 */         this._cur = new CursorableLinkedList.Listable(null, this$0._head.next(), null);
/* 1003 */         this._nextIndex = 0;
/* 1004 */       } else if (index == this$0._size) {
/* 1005 */         this._cur = new CursorableLinkedList.Listable(this$0._head.prev(), null, null);
/* 1006 */         this._nextIndex = this$0._size;
/*      */       } else {
/* 1008 */         CursorableLinkedList.Listable temp = this$0.getListableAt(index);
/* 1009 */         this._cur = new CursorableLinkedList.Listable(temp.prev(), temp, null);
/* 1010 */         this._nextIndex = index;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1015 */       checkForComod();
/* 1016 */       if (!hasPrevious()) {
/* 1017 */         throw new NoSuchElementException();
/*      */       }
/* 1019 */       Object ret = this._cur.prev().value();
/* 1020 */       this._lastReturned = this._cur.prev();
/* 1021 */       this._cur.setNext(this._cur.prev());
/* 1022 */       this._cur.setPrev(this._cur.prev().prev());
/* 1023 */       this._nextIndex--;
/* 1024 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 1029 */       checkForComod();
/* 1030 */       return (null != this._cur.next() && this._cur.prev() != this.this$0._head.prev());
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1034 */       checkForComod();
/* 1035 */       if (!hasNext()) {
/* 1036 */         throw new NoSuchElementException();
/*      */       }
/* 1038 */       Object ret = this._cur.next().value();
/* 1039 */       this._lastReturned = this._cur.next();
/* 1040 */       this._cur.setPrev(this._cur.next());
/* 1041 */       this._cur.setNext(this._cur.next().next());
/* 1042 */       this._nextIndex++;
/* 1043 */       return ret;
/*      */     }
/*      */ 
/*      */     
/*      */     public int previousIndex() {
/* 1048 */       checkForComod();
/* 1049 */       if (!hasPrevious()) {
/* 1050 */         return -1;
/*      */       }
/* 1052 */       return this._nextIndex - 1;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1056 */       checkForComod();
/* 1057 */       return (null != this._cur.prev() && this._cur.next() != this.this$0._head.next());
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1061 */       checkForComod();
/*      */       try {
/* 1063 */         this._lastReturned.setValue(o);
/* 1064 */       } catch (NullPointerException e) {
/* 1065 */         throw new IllegalStateException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1070 */       checkForComod();
/* 1071 */       if (!hasNext()) {
/* 1072 */         return this.this$0.size();
/*      */       }
/* 1074 */       return this._nextIndex;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1078 */       checkForComod();
/* 1079 */       if (null == this._lastReturned) {
/* 1080 */         throw new IllegalStateException();
/*      */       }
/* 1082 */       this._cur.setNext((this._lastReturned == this.this$0._head.prev()) ? null : this._lastReturned.next());
/* 1083 */       this._cur.setPrev((this._lastReturned == this.this$0._head.next()) ? null : this._lastReturned.prev());
/* 1084 */       this.this$0.removeListable(this._lastReturned);
/* 1085 */       this._lastReturned = null;
/* 1086 */       this._nextIndex--;
/* 1087 */       this._expectedModCount++;
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(Object o) {
/* 1092 */       checkForComod();
/* 1093 */       this._cur.setPrev(this.this$0.insertListable(this._cur.prev(), this._cur.next(), o));
/* 1094 */       this._lastReturned = null;
/* 1095 */       this._nextIndex++;
/* 1096 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1100 */       if (this._expectedModCount != this.this$0._modCount)
/* 1101 */         throw new ConcurrentModificationException(); 
/*      */     } }
/*      */ 
/*      */   
/*      */   public class Cursor extends ListIter implements ListIterator {
/*      */     boolean _valid;
/*      */     private final CursorableLinkedList this$0;
/*      */     
/*      */     Cursor(CursorableLinkedList this$0, int index) {
/* 1110 */       super(this$0, index); this.this$0 = this$0; this._valid = false;
/* 1111 */       this._valid = true;
/* 1112 */       this$0.registerCursor(this);
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1116 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1120 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1124 */       checkForComod();
/* 1125 */       CursorableLinkedList.Listable elt = this.this$0.insertListable(this._cur.prev(), this._cur.next(), o);
/* 1126 */       this._cur.setPrev(elt);
/* 1127 */       this._cur.setNext(elt.next());
/* 1128 */       this._lastReturned = null;
/* 1129 */       this._nextIndex++;
/* 1130 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void listableRemoved(CursorableLinkedList.Listable elt) {
/* 1134 */       if (null == this.this$0._head.prev()) {
/* 1135 */         this._cur.setNext(null);
/* 1136 */       } else if (this._cur.next() == elt) {
/* 1137 */         this._cur.setNext(elt.next());
/*      */       } 
/* 1139 */       if (null == this.this$0._head.next()) {
/* 1140 */         this._cur.setPrev(null);
/* 1141 */       } else if (this._cur.prev() == elt) {
/* 1142 */         this._cur.setPrev(elt.prev());
/*      */       } 
/* 1144 */       if (this._lastReturned == elt) {
/* 1145 */         this._lastReturned = null;
/*      */       }
/*      */     }
/*      */     
/*      */     protected void listableInserted(CursorableLinkedList.Listable elt) {
/* 1150 */       if (null == this._cur.next() && null == this._cur.prev()) {
/* 1151 */         this._cur.setNext(elt);
/* 1152 */       } else if (this._cur.prev() == elt.prev()) {
/* 1153 */         this._cur.setNext(elt);
/*      */       } 
/* 1155 */       if (this._cur.next() == elt.next()) {
/* 1156 */         this._cur.setPrev(elt);
/*      */       }
/* 1158 */       if (this._lastReturned == elt) {
/* 1159 */         this._lastReturned = null;
/*      */       }
/*      */     }
/*      */     
/*      */     protected void listableChanged(CursorableLinkedList.Listable elt) {
/* 1164 */       if (this._lastReturned == elt) {
/* 1165 */         this._lastReturned = null;
/*      */       }
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1170 */       if (!this._valid) {
/* 1171 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     protected void invalidate() {
/* 1176 */       this._valid = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() {
/* 1188 */       if (this._valid) {
/* 1189 */         this._valid = false;
/* 1190 */         this.this$0.unregisterCursor(this);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/CursorableLinkedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */