/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractListDecorator
/*     */   extends AbstractCollectionDecorator
/*     */   implements List
/*     */ {
/*     */   protected AbstractListDecorator() {}
/*     */   
/*     */   protected AbstractListDecorator(List list) {
/*  51 */     super(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getList() {
/*  60 */     return (List)getCollection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object object) {
/*  65 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  69 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  73 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  77 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  81 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/*  85 */     return getList().listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/*  89 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  93 */     return getList().remove(index);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/*  97 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 101 */     return getList().subList(fromIndex, toIndex);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/AbstractListDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */