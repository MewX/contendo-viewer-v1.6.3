/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.collection.PredicatedCollection;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedList
/*     */   extends PredicatedCollection
/*     */   implements List
/*     */ {
/*     */   private static final long serialVersionUID = -5722039223898659102L;
/*     */   
/*     */   public static List decorate(List list, Predicate predicate) {
/*  63 */     return new PredicatedList(list, predicate);
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
/*     */   protected PredicatedList(List list, Predicate predicate) {
/*  79 */     super(list, predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getList() {
/*  88 */     return (List)getCollection();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/*  93 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  97 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 101 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 105 */     return getList().remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object object) {
/* 110 */     validate(object);
/* 111 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 115 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 116 */       validate(it.next());
/*     */     }
/* 118 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 122 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int i) {
/* 126 */     return (ListIterator)new PredicatedListIterator(this, getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 130 */     validate(object);
/* 131 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 135 */     List sub = getList().subList(fromIndex, toIndex);
/* 136 */     return new PredicatedList(sub, this.predicate);
/*     */   }
/*     */   
/*     */   protected class PredicatedListIterator
/*     */     extends AbstractListIteratorDecorator
/*     */   {
/*     */     private final PredicatedList this$0;
/*     */     
/*     */     protected PredicatedListIterator(PredicatedList this$0, ListIterator iterator) {
/* 145 */       super(iterator);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     public void add(Object object) {
/* 149 */       this.this$0.validate(object);
/* 150 */       this.iterator.add(object);
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 154 */       this.this$0.validate(object);
/* 155 */       this.iterator.set(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/PredicatedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */