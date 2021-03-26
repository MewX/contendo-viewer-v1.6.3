/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
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
/*     */ 
/*     */ 
/*     */ public class SetUniqueList
/*     */   extends AbstractSerializableListDecorator
/*     */ {
/*     */   private static final long serialVersionUID = 7196982186153478694L;
/*     */   protected final Set set;
/*     */   
/*     */   public static SetUniqueList decorate(List list) {
/*  71 */     if (list == null) {
/*  72 */       throw new IllegalArgumentException("List must not be null");
/*     */     }
/*  74 */     if (list.isEmpty()) {
/*  75 */       return new SetUniqueList(list, new HashSet());
/*     */     }
/*  77 */     List temp = new ArrayList(list);
/*  78 */     list.clear();
/*  79 */     SetUniqueList sl = new SetUniqueList(list, new HashSet());
/*  80 */     sl.addAll(temp);
/*  81 */     return sl;
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
/*     */   protected SetUniqueList(List list, Set set) {
/*  96 */     super(list);
/*  97 */     if (set == null) {
/*  98 */       throw new IllegalArgumentException("Set must not be null");
/*     */     }
/* 100 */     this.set = set;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set asSet() {
/* 110 */     return UnmodifiableSet.decorate(this.set);
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
/*     */   public boolean add(Object object) {
/* 127 */     int sizeBefore = size();
/*     */ 
/*     */     
/* 130 */     add(size(), object);
/*     */ 
/*     */     
/* 133 */     return (sizeBefore != size());
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
/*     */   public void add(int index, Object object) {
/* 148 */     if (!this.set.contains(object)) {
/* 149 */       super.add(index, object);
/* 150 */       this.set.add(object);
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
/*     */   public boolean addAll(Collection coll) {
/* 164 */     return addAll(size(), coll);
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
/*     */   public boolean addAll(int index, Collection coll) {
/* 183 */     int sizeBefore = size();
/*     */ 
/*     */     
/* 186 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 187 */       add(it.next());
/*     */     }
/*     */ 
/*     */     
/* 191 */     return (sizeBefore != size());
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
/*     */   public Object set(int index, Object object) {
/* 208 */     int pos = indexOf(object);
/* 209 */     Object result = super.set(index, object);
/* 210 */     if (pos == -1 || pos == index) {
/* 211 */       return result;
/*     */     }
/* 213 */     return remove(pos);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 217 */     boolean result = super.remove(object);
/* 218 */     this.set.remove(object);
/* 219 */     return result;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 223 */     Object result = super.remove(index);
/* 224 */     this.set.remove(result);
/* 225 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 229 */     boolean result = super.removeAll(coll);
/* 230 */     this.set.removeAll(coll);
/* 231 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 235 */     boolean result = super.retainAll(coll);
/* 236 */     this.set.retainAll(coll);
/* 237 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 241 */     super.clear();
/* 242 */     this.set.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 246 */     return this.set.contains(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 250 */     return this.set.containsAll(coll);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 254 */     return (Iterator)new SetListIterator(super.iterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 258 */     return (ListIterator)new SetListListIterator(super.listIterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 262 */     return (ListIterator)new SetListListIterator(super.listIterator(index), this.set);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 266 */     return new SetUniqueList(super.subList(fromIndex, toIndex), this.set);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class SetListIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     protected final Set set;
/*     */     
/* 276 */     protected Object last = null;
/*     */     
/*     */     protected SetListIterator(Iterator it, Set set) {
/* 279 */       super(it);
/* 280 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 284 */       this.last = super.next();
/* 285 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 289 */       super.remove();
/* 290 */       this.set.remove(this.last);
/* 291 */       this.last = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class SetListListIterator
/*     */     extends AbstractListIteratorDecorator
/*     */   {
/*     */     protected final Set set;
/*     */     
/* 301 */     protected Object last = null;
/*     */     
/*     */     protected SetListListIterator(ListIterator it, Set set) {
/* 304 */       super(it);
/* 305 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 309 */       this.last = super.next();
/* 310 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 314 */       this.last = super.previous();
/* 315 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 319 */       super.remove();
/* 320 */       this.set.remove(this.last);
/* 321 */       this.last = null;
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 325 */       if (!this.set.contains(object)) {
/* 326 */         super.add(object);
/* 327 */         this.set.add(object);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 332 */       throw new UnsupportedOperationException("ListIterator does not support set");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/SetUniqueList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */