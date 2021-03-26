/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.collection.TransformedCollection;
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
/*     */ public class TransformedList
/*     */   extends TransformedCollection
/*     */   implements List
/*     */ {
/*     */   private static final long serialVersionUID = 1077193035000013141L;
/*     */   
/*     */   public static List decorate(List list, Transformer transformer) {
/*  57 */     return new TransformedList(list, transformer);
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
/*     */   protected TransformedList(List list, Transformer transformer) {
/*  72 */     super(list, transformer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getList() {
/*  81 */     return (List)((AbstractCollectionDecorator)this).collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/*  86 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  90 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  94 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  98 */     return getList().remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object object) {
/* 103 */     object = transform(object);
/* 104 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 108 */     coll = transform(coll);
/* 109 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 113 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int i) {
/* 117 */     return (ListIterator)new TransformedListIterator(this, getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 121 */     object = transform(object);
/* 122 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 126 */     List sub = getList().subList(fromIndex, toIndex);
/* 127 */     return new TransformedList(sub, this.transformer);
/*     */   }
/*     */   
/*     */   protected class TransformedListIterator
/*     */     extends AbstractListIteratorDecorator
/*     */   {
/*     */     private final TransformedList this$0;
/*     */     
/*     */     protected TransformedListIterator(TransformedList this$0, ListIterator iterator) {
/* 136 */       super(iterator);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     public void add(Object object) {
/* 140 */       object = this.this$0.transform(object);
/* 141 */       this.iterator.add(object);
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 145 */       object = this.this$0.transform(object);
/* 146 */       this.iterator.set(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/TransformedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */