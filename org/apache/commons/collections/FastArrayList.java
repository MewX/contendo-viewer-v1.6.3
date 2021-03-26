/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FastArrayList
/*      */   extends ArrayList
/*      */ {
/*      */   protected ArrayList list;
/*      */   protected boolean fast;
/*      */   
/*      */   public FastArrayList() {
/*  115 */     this.list = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  124 */     this.fast = false; this.list = new ArrayList(); } public FastArrayList(int capacity) { this.list = null; this.fast = false; this.list = new ArrayList(capacity); } public FastArrayList(Collection collection) { this.list = null; this.fast = false;
/*      */     this.list = new ArrayList(collection); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFast() {
/*  133 */     return this.fast;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFast(boolean fast) {
/*  142 */     this.fast = fast;
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
/*      */   public boolean add(Object element) {
/*  156 */     if (this.fast) {
/*  157 */       synchronized (this) {
/*  158 */         ArrayList temp = (ArrayList)this.list.clone();
/*  159 */         boolean result = temp.add(element);
/*  160 */         this.list = temp;
/*  161 */         return result;
/*      */       } 
/*      */     }
/*  164 */     synchronized (this.list) {
/*  165 */       return this.list.add(element);
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
/*      */   public void add(int index, Object element) {
/*  183 */     if (this.fast) {
/*  184 */       synchronized (this) {
/*  185 */         ArrayList temp = (ArrayList)this.list.clone();
/*  186 */         temp.add(index, element);
/*  187 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  190 */       synchronized (this.list) {
/*  191 */         this.list.add(index, element);
/*      */       } 
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
/*      */   public boolean addAll(Collection collection) {
/*  207 */     if (this.fast) {
/*  208 */       synchronized (this) {
/*  209 */         ArrayList temp = (ArrayList)this.list.clone();
/*  210 */         boolean result = temp.addAll(collection);
/*  211 */         this.list = temp;
/*  212 */         return result;
/*      */       } 
/*      */     }
/*  215 */     synchronized (this.list) {
/*  216 */       return this.list.addAll(collection);
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
/*      */   public boolean addAll(int index, Collection collection) {
/*  235 */     if (this.fast) {
/*  236 */       synchronized (this) {
/*  237 */         ArrayList temp = (ArrayList)this.list.clone();
/*  238 */         boolean result = temp.addAll(index, collection);
/*  239 */         this.list = temp;
/*  240 */         return result;
/*      */       } 
/*      */     }
/*  243 */     synchronized (this.list) {
/*  244 */       return this.list.addAll(index, collection);
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
/*      */   public void clear() {
/*  260 */     if (this.fast) {
/*  261 */       synchronized (this) {
/*  262 */         ArrayList temp = (ArrayList)this.list.clone();
/*  263 */         temp.clear();
/*  264 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  267 */       synchronized (this.list) {
/*  268 */         this.list.clear();
/*      */       } 
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
/*      */   public Object clone() {
/*  281 */     FastArrayList results = null;
/*  282 */     if (this.fast) {
/*  283 */       results = new FastArrayList(this.list);
/*      */     } else {
/*  285 */       synchronized (this.list) {
/*  286 */         results = new FastArrayList(this.list);
/*      */       } 
/*      */     } 
/*  289 */     results.setFast(getFast());
/*  290 */     return results;
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
/*      */   public boolean contains(Object element) {
/*  302 */     if (this.fast) {
/*  303 */       return this.list.contains(element);
/*      */     }
/*  305 */     synchronized (this.list) {
/*  306 */       return this.list.contains(element);
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
/*      */   public boolean containsAll(Collection collection) {
/*  321 */     if (this.fast) {
/*  322 */       return this.list.containsAll(collection);
/*      */     }
/*  324 */     synchronized (this.list) {
/*  325 */       return this.list.containsAll(collection);
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
/*      */   public void ensureCapacity(int capacity) {
/*  341 */     if (this.fast) {
/*  342 */       synchronized (this) {
/*  343 */         ArrayList temp = (ArrayList)this.list.clone();
/*  344 */         temp.ensureCapacity(capacity);
/*  345 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  348 */       synchronized (this.list) {
/*  349 */         this.list.ensureCapacity(capacity);
/*      */       } 
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
/*      */   public boolean equals(Object o) {
/*  367 */     if (o == this)
/*  368 */       return true; 
/*  369 */     if (!(o instanceof List))
/*  370 */       return false; 
/*  371 */     List lo = (List)o;
/*      */ 
/*      */     
/*  374 */     if (this.fast) {
/*  375 */       ListIterator li1 = this.list.listIterator();
/*  376 */       ListIterator li2 = lo.listIterator();
/*  377 */       while (li1.hasNext() && li2.hasNext()) {
/*  378 */         Object o1 = li1.next();
/*  379 */         Object o2 = li2.next();
/*  380 */         if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/*  381 */           continue;  return false;
/*      */       } 
/*  383 */       return (!li1.hasNext() && !li2.hasNext());
/*      */     } 
/*  385 */     synchronized (this.list) {
/*  386 */       ListIterator li1 = this.list.listIterator();
/*  387 */       ListIterator li2 = lo.listIterator();
/*  388 */       while (li1.hasNext() && li2.hasNext()) {
/*  389 */         Object o1 = li1.next();
/*  390 */         Object o2 = li2.next();
/*  391 */         if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/*  392 */           continue;  return false;
/*      */       } 
/*  394 */       return (!li1.hasNext() && !li2.hasNext());
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
/*      */   public Object get(int index) {
/*  410 */     if (this.fast) {
/*  411 */       return this.list.get(index);
/*      */     }
/*  413 */     synchronized (this.list) {
/*  414 */       return this.list.get(index);
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
/*      */   public int hashCode() {
/*  428 */     if (this.fast) {
/*  429 */       int hashCode = 1;
/*  430 */       Iterator i = this.list.iterator();
/*  431 */       while (i.hasNext()) {
/*  432 */         Object o = i.next();
/*  433 */         hashCode = 31 * hashCode + ((o == null) ? 0 : o.hashCode());
/*      */       } 
/*  435 */       return hashCode;
/*      */     } 
/*  437 */     synchronized (this.list) {
/*  438 */       int hashCode = 1;
/*  439 */       Iterator i = this.list.iterator();
/*  440 */       while (i.hasNext()) {
/*  441 */         Object o = i.next();
/*  442 */         hashCode = 31 * hashCode + ((o == null) ? 0 : o.hashCode());
/*      */       } 
/*  444 */       return hashCode;
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
/*      */   public int indexOf(Object element) {
/*  460 */     if (this.fast) {
/*  461 */       return this.list.indexOf(element);
/*      */     }
/*  463 */     synchronized (this.list) {
/*  464 */       return this.list.indexOf(element);
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
/*      */   public boolean isEmpty() {
/*  476 */     if (this.fast) {
/*  477 */       return this.list.isEmpty();
/*      */     }
/*  479 */     synchronized (this.list) {
/*  480 */       return this.list.isEmpty();
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
/*      */   public Iterator iterator() {
/*  497 */     if (this.fast) {
/*  498 */       return new ListIter(this, 0);
/*      */     }
/*  500 */     return this.list.iterator();
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
/*      */   public int lastIndexOf(Object element) {
/*  514 */     if (this.fast) {
/*  515 */       return this.list.lastIndexOf(element);
/*      */     }
/*  517 */     synchronized (this.list) {
/*  518 */       return this.list.lastIndexOf(element);
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
/*      */   public ListIterator listIterator() {
/*  530 */     if (this.fast) {
/*  531 */       return new ListIter(this, 0);
/*      */     }
/*  533 */     return this.list.listIterator();
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
/*      */   public ListIterator listIterator(int index) {
/*  548 */     if (this.fast) {
/*  549 */       return new ListIter(this, index);
/*      */     }
/*  551 */     return this.list.listIterator(index);
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
/*      */   public Object remove(int index) {
/*  566 */     if (this.fast) {
/*  567 */       synchronized (this) {
/*  568 */         ArrayList temp = (ArrayList)this.list.clone();
/*  569 */         Object result = temp.remove(index);
/*  570 */         this.list = temp;
/*  571 */         return result;
/*      */       } 
/*      */     }
/*  574 */     synchronized (this.list) {
/*  575 */       return this.list.remove(index);
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
/*      */   public boolean remove(Object element) {
/*  590 */     if (this.fast) {
/*  591 */       synchronized (this) {
/*  592 */         ArrayList temp = (ArrayList)this.list.clone();
/*  593 */         boolean result = temp.remove(element);
/*  594 */         this.list = temp;
/*  595 */         return result;
/*      */       } 
/*      */     }
/*  598 */     synchronized (this.list) {
/*  599 */       return this.list.remove(element);
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
/*      */   public boolean removeAll(Collection collection) {
/*  617 */     if (this.fast) {
/*  618 */       synchronized (this) {
/*  619 */         ArrayList temp = (ArrayList)this.list.clone();
/*  620 */         boolean result = temp.removeAll(collection);
/*  621 */         this.list = temp;
/*  622 */         return result;
/*      */       } 
/*      */     }
/*  625 */     synchronized (this.list) {
/*  626 */       return this.list.removeAll(collection);
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
/*      */   public boolean retainAll(Collection collection) {
/*  644 */     if (this.fast) {
/*  645 */       synchronized (this) {
/*  646 */         ArrayList temp = (ArrayList)this.list.clone();
/*  647 */         boolean result = temp.retainAll(collection);
/*  648 */         this.list = temp;
/*  649 */         return result;
/*      */       } 
/*      */     }
/*  652 */     synchronized (this.list) {
/*  653 */       return this.list.retainAll(collection);
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
/*      */   public Object set(int index, Object element) {
/*  675 */     if (this.fast) {
/*  676 */       return this.list.set(index, element);
/*      */     }
/*  678 */     synchronized (this.list) {
/*  679 */       return this.list.set(index, element);
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
/*      */   public int size() {
/*  691 */     if (this.fast) {
/*  692 */       return this.list.size();
/*      */     }
/*  694 */     synchronized (this.list) {
/*  695 */       return this.list.size();
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
/*      */   public List subList(int fromIndex, int toIndex) {
/*  715 */     if (this.fast) {
/*  716 */       return new SubList(this, fromIndex, toIndex);
/*      */     }
/*  718 */     return this.list.subList(fromIndex, toIndex);
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
/*  729 */     if (this.fast) {
/*  730 */       return this.list.toArray();
/*      */     }
/*  732 */     synchronized (this.list) {
/*  733 */       return this.list.toArray();
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
/*      */   public Object[] toArray(Object[] array) {
/*  754 */     if (this.fast) {
/*  755 */       return this.list.toArray(array);
/*      */     }
/*  757 */     synchronized (this.list) {
/*  758 */       return this.list.toArray(array);
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
/*      */   public String toString() {
/*  770 */     StringBuffer sb = new StringBuffer("FastArrayList[");
/*  771 */     sb.append(this.list.toString());
/*  772 */     sb.append("]");
/*  773 */     return sb.toString();
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
/*      */   public void trimToSize() {
/*  785 */     if (this.fast) {
/*  786 */       synchronized (this) {
/*  787 */         ArrayList temp = (ArrayList)this.list.clone();
/*  788 */         temp.trimToSize();
/*  789 */         this.list = temp;
/*      */       } 
/*      */     } else {
/*  792 */       synchronized (this.list) {
/*  793 */         this.list.trimToSize();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private class SubList
/*      */     implements List
/*      */   {
/*      */     private int first;
/*      */     private int last;
/*      */     private List expected;
/*      */     private final FastArrayList this$0;
/*      */     
/*      */     public SubList(FastArrayList this$0, int first, int last) {
/*  808 */       this.this$0 = this$0;
/*  809 */       this.first = first;
/*  810 */       this.last = last;
/*  811 */       this.expected = this$0.list;
/*      */     }
/*      */     
/*      */     private List get(List l) {
/*  815 */       if (this.this$0.list != this.expected) {
/*  816 */         throw new ConcurrentModificationException();
/*      */       }
/*  818 */       return l.subList(this.first, this.last);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  822 */       if (this.this$0.fast) {
/*  823 */         synchronized (this.this$0) {
/*  824 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  825 */           get(temp).clear();
/*  826 */           this.last = this.first;
/*  827 */           this.this$0.list = temp;
/*  828 */           this.expected = temp;
/*      */         } 
/*      */       } else {
/*  831 */         synchronized (this.this$0.list) {
/*  832 */           get(this.expected).clear();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*  838 */       if (this.this$0.fast) {
/*  839 */         synchronized (this.this$0) {
/*  840 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  841 */           boolean r = get(temp).remove(o);
/*  842 */           if (r) this.last--; 
/*  843 */           this.this$0.list = temp;
/*  844 */           this.expected = temp;
/*  845 */           return r;
/*      */         } 
/*      */       }
/*  848 */       synchronized (this.this$0.list) {
/*  849 */         return get(this.expected).remove(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean removeAll(Collection o) {
/*  855 */       if (this.this$0.fast) {
/*  856 */         synchronized (this.this$0) {
/*  857 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  858 */           List sub = get(temp);
/*  859 */           boolean r = sub.removeAll(o);
/*  860 */           if (r) this.last = this.first + sub.size(); 
/*  861 */           this.this$0.list = temp;
/*  862 */           this.expected = temp;
/*  863 */           return r;
/*      */         } 
/*      */       }
/*  866 */       synchronized (this.this$0.list) {
/*  867 */         return get(this.expected).removeAll(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean retainAll(Collection o) {
/*  873 */       if (this.this$0.fast) {
/*  874 */         synchronized (this.this$0) {
/*  875 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  876 */           List sub = get(temp);
/*  877 */           boolean r = sub.retainAll(o);
/*  878 */           if (r) this.last = this.first + sub.size(); 
/*  879 */           this.this$0.list = temp;
/*  880 */           this.expected = temp;
/*  881 */           return r;
/*      */         } 
/*      */       }
/*  884 */       synchronized (this.this$0.list) {
/*  885 */         return get(this.expected).retainAll(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public int size() {
/*  891 */       if (this.this$0.fast) {
/*  892 */         return get(this.expected).size();
/*      */       }
/*  894 */       synchronized (this.this$0.list) {
/*  895 */         return get(this.expected).size();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isEmpty() {
/*  902 */       if (this.this$0.fast) {
/*  903 */         return get(this.expected).isEmpty();
/*      */       }
/*  905 */       synchronized (this.this$0.list) {
/*  906 */         return get(this.expected).isEmpty();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean contains(Object o) {
/*  912 */       if (this.this$0.fast) {
/*  913 */         return get(this.expected).contains(o);
/*      */       }
/*  915 */       synchronized (this.this$0.list) {
/*  916 */         return get(this.expected).contains(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean containsAll(Collection o) {
/*  922 */       if (this.this$0.fast) {
/*  923 */         return get(this.expected).containsAll(o);
/*      */       }
/*  925 */       synchronized (this.this$0.list) {
/*  926 */         return get(this.expected).containsAll(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] toArray(Object[] o) {
/*  932 */       if (this.this$0.fast) {
/*  933 */         return get(this.expected).toArray(o);
/*      */       }
/*  935 */       synchronized (this.this$0.list) {
/*  936 */         return get(this.expected).toArray(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] toArray() {
/*  942 */       if (this.this$0.fast) {
/*  943 */         return get(this.expected).toArray();
/*      */       }
/*  945 */       synchronized (this.this$0.list) {
/*  946 */         return get(this.expected).toArray();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object o) {
/*  953 */       if (o == this) return true; 
/*  954 */       if (this.this$0.fast) {
/*  955 */         return get(this.expected).equals(o);
/*      */       }
/*  957 */       synchronized (this.this$0.list) {
/*  958 */         return get(this.expected).equals(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  964 */       if (this.this$0.fast) {
/*  965 */         return get(this.expected).hashCode();
/*      */       }
/*  967 */       synchronized (this.this$0.list) {
/*  968 */         return get(this.expected).hashCode();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean add(Object o) {
/*  974 */       if (this.this$0.fast) {
/*  975 */         synchronized (this.this$0) {
/*  976 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  977 */           boolean r = get(temp).add(o);
/*  978 */           if (r) this.last++; 
/*  979 */           this.this$0.list = temp;
/*  980 */           this.expected = temp;
/*  981 */           return r;
/*      */         } 
/*      */       }
/*  984 */       synchronized (this.this$0.list) {
/*  985 */         return get(this.expected).add(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection o) {
/*  991 */       if (this.this$0.fast) {
/*  992 */         synchronized (this.this$0) {
/*  993 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/*  994 */           boolean r = get(temp).addAll(o);
/*  995 */           if (r) this.last += o.size(); 
/*  996 */           this.this$0.list = temp;
/*  997 */           this.expected = temp;
/*  998 */           return r;
/*      */         } 
/*      */       }
/* 1001 */       synchronized (this.this$0.list) {
/* 1002 */         return get(this.expected).addAll(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void add(int i, Object o) {
/* 1008 */       if (this.this$0.fast) {
/* 1009 */         synchronized (this.this$0) {
/* 1010 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1011 */           get(temp).add(i, o);
/* 1012 */           this.last++;
/* 1013 */           this.this$0.list = temp;
/* 1014 */           this.expected = temp;
/*      */         } 
/*      */       } else {
/* 1017 */         synchronized (this.this$0.list) {
/* 1018 */           get(this.expected).add(i, o);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean addAll(int i, Collection o) {
/* 1024 */       if (this.this$0.fast) {
/* 1025 */         synchronized (this.this$0) {
/* 1026 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1027 */           boolean r = get(temp).addAll(i, o);
/* 1028 */           this.this$0.list = temp;
/* 1029 */           if (r) this.last += o.size(); 
/* 1030 */           this.expected = temp;
/* 1031 */           return r;
/*      */         } 
/*      */       }
/* 1034 */       synchronized (this.this$0.list) {
/* 1035 */         return get(this.expected).addAll(i, o);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Object remove(int i) {
/* 1041 */       if (this.this$0.fast) {
/* 1042 */         synchronized (this.this$0) {
/* 1043 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1044 */           Object o = get(temp).remove(i);
/* 1045 */           this.last--;
/* 1046 */           this.this$0.list = temp;
/* 1047 */           this.expected = temp;
/* 1048 */           return o;
/*      */         } 
/*      */       }
/* 1051 */       synchronized (this.this$0.list) {
/* 1052 */         return get(this.expected).remove(i);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Object set(int i, Object a) {
/* 1058 */       if (this.this$0.fast) {
/* 1059 */         synchronized (this.this$0) {
/* 1060 */           ArrayList temp = (ArrayList)this.this$0.list.clone();
/* 1061 */           Object o = get(temp).set(i, a);
/* 1062 */           this.this$0.list = temp;
/* 1063 */           this.expected = temp;
/* 1064 */           return o;
/*      */         } 
/*      */       }
/* 1067 */       synchronized (this.this$0.list) {
/* 1068 */         return get(this.expected).set(i, a);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator iterator() {
/* 1075 */       return new SubListIter(this, 0);
/*      */     }
/*      */     
/*      */     public ListIterator listIterator() {
/* 1079 */       return new SubListIter(this, 0);
/*      */     }
/*      */     
/*      */     public ListIterator listIterator(int i) {
/* 1083 */       return new SubListIter(this, i);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(int i) {
/* 1088 */       if (this.this$0.fast) {
/* 1089 */         return get(this.expected).get(i);
/*      */       }
/* 1091 */       synchronized (this.this$0.list) {
/* 1092 */         return get(this.expected).get(i);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public int indexOf(Object o) {
/* 1098 */       if (this.this$0.fast) {
/* 1099 */         return get(this.expected).indexOf(o);
/*      */       }
/* 1101 */       synchronized (this.this$0.list) {
/* 1102 */         return get(this.expected).indexOf(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int lastIndexOf(Object o) {
/* 1109 */       if (this.this$0.fast) {
/* 1110 */         return get(this.expected).lastIndexOf(o);
/*      */       }
/* 1112 */       synchronized (this.this$0.list) {
/* 1113 */         return get(this.expected).lastIndexOf(o);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public List subList(int f, int l) {
/* 1120 */       if (this.this$0.list != this.expected) {
/* 1121 */         throw new ConcurrentModificationException();
/*      */       }
/* 1123 */       return new SubList(this.this$0, this.first + f, f + l);
/*      */     }
/*      */     
/*      */     private class SubListIter
/*      */       implements ListIterator {
/*      */       private List expected;
/*      */       private ListIterator iter;
/*      */       private int lastReturnedIndex;
/*      */       private final FastArrayList.SubList this$1;
/*      */       
/*      */       public SubListIter(FastArrayList.SubList this$1, int i) {
/* 1134 */         this.this$1 = this$1; this.lastReturnedIndex = -1;
/* 1135 */         this.expected = this$1.this$0.list;
/* 1136 */         this.iter = this$1.get(this.expected).listIterator(i);
/*      */       }
/*      */       
/*      */       private void checkMod() {
/* 1140 */         if (this.this$1.this$0.list != this.expected) {
/* 1141 */           throw new ConcurrentModificationException();
/*      */         }
/*      */       }
/*      */       
/*      */       List get() {
/* 1146 */         return this.this$1.get(this.expected);
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/* 1150 */         checkMod();
/* 1151 */         return this.iter.hasNext();
/*      */       }
/*      */       
/*      */       public Object next() {
/* 1155 */         checkMod();
/* 1156 */         this.lastReturnedIndex = this.iter.nextIndex();
/* 1157 */         return this.iter.next();
/*      */       }
/*      */       
/*      */       public boolean hasPrevious() {
/* 1161 */         checkMod();
/* 1162 */         return this.iter.hasPrevious();
/*      */       }
/*      */       
/*      */       public Object previous() {
/* 1166 */         checkMod();
/* 1167 */         this.lastReturnedIndex = this.iter.previousIndex();
/* 1168 */         return this.iter.previous();
/*      */       }
/*      */       
/*      */       public int previousIndex() {
/* 1172 */         checkMod();
/* 1173 */         return this.iter.previousIndex();
/*      */       }
/*      */       
/*      */       public int nextIndex() {
/* 1177 */         checkMod();
/* 1178 */         return this.iter.nextIndex();
/*      */       }
/*      */       
/*      */       public void remove() {
/* 1182 */         checkMod();
/* 1183 */         if (this.lastReturnedIndex < 0) {
/* 1184 */           throw new IllegalStateException();
/*      */         }
/* 1186 */         get().remove(this.lastReturnedIndex);
/* 1187 */         this.this$1.last--;
/* 1188 */         this.expected = this.this$1.this$0.list;
/* 1189 */         this.iter = get().listIterator(previousIndex());
/* 1190 */         this.lastReturnedIndex = -1;
/*      */       }
/*      */       
/*      */       public void set(Object o) {
/* 1194 */         checkMod();
/* 1195 */         if (this.lastReturnedIndex < 0) {
/* 1196 */           throw new IllegalStateException();
/*      */         }
/* 1198 */         get().set(this.lastReturnedIndex, o);
/* 1199 */         this.expected = this.this$1.this$0.list;
/* 1200 */         this.iter = get().listIterator(previousIndex() + 1);
/*      */       }
/*      */       
/*      */       public void add(Object o) {
/* 1204 */         checkMod();
/* 1205 */         int i = nextIndex();
/* 1206 */         get().add(i, o);
/* 1207 */         this.this$1.last++;
/* 1208 */         this.iter = get().listIterator(i + 1);
/* 1209 */         this.lastReturnedIndex = 1;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class ListIter
/*      */     implements ListIterator
/*      */   {
/*      */     private List expected;
/*      */     
/*      */     private ListIterator iter;
/*      */     
/*      */     private int lastReturnedIndex;
/*      */     private final FastArrayList this$0;
/*      */     
/*      */     public ListIter(FastArrayList this$0, int i) {
/* 1226 */       this.this$0 = this$0; this.lastReturnedIndex = -1;
/* 1227 */       this.expected = this$0.list;
/* 1228 */       this.iter = get().listIterator(i);
/*      */     }
/*      */     
/*      */     private void checkMod() {
/* 1232 */       if (this.this$0.list != this.expected) {
/* 1233 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     List get() {
/* 1238 */       return this.expected;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1242 */       checkMod();
/* 1243 */       return this.iter.hasNext();
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1247 */       checkMod();
/* 1248 */       this.lastReturnedIndex = this.iter.nextIndex();
/* 1249 */       return this.iter.next();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1253 */       checkMod();
/* 1254 */       return this.iter.hasPrevious();
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1258 */       checkMod();
/* 1259 */       this.lastReturnedIndex = this.iter.previousIndex();
/* 1260 */       return this.iter.previous();
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1264 */       checkMod();
/* 1265 */       return this.iter.previousIndex();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1269 */       checkMod();
/* 1270 */       return this.iter.nextIndex();
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1274 */       checkMod();
/* 1275 */       if (this.lastReturnedIndex < 0) {
/* 1276 */         throw new IllegalStateException();
/*      */       }
/* 1278 */       get().remove(this.lastReturnedIndex);
/* 1279 */       this.expected = this.this$0.list;
/* 1280 */       this.iter = get().listIterator(previousIndex());
/* 1281 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1285 */       checkMod();
/* 1286 */       if (this.lastReturnedIndex < 0) {
/* 1287 */         throw new IllegalStateException();
/*      */       }
/* 1289 */       get().set(this.lastReturnedIndex, o);
/* 1290 */       this.expected = this.this$0.list;
/* 1291 */       this.iter = get().listIterator(previousIndex() + 1);
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1295 */       checkMod();
/* 1296 */       int i = nextIndex();
/* 1297 */       get().add(i, o);
/* 1298 */       this.iter = get().listIterator(i + 1);
/* 1299 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/FastArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */