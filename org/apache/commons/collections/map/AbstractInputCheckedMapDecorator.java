/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ import org.apache.commons.collections.set.AbstractSetDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractInputCheckedMapDecorator
/*     */   extends AbstractMapDecorator
/*     */ {
/*     */   protected AbstractInputCheckedMapDecorator() {}
/*     */   
/*     */   protected AbstractInputCheckedMapDecorator(Map map) {
/*  63 */     super(map);
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
/*     */   protected abstract Object checkSetValue(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSetValueChecking() {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/*  99 */     if (isSetValueChecking()) {
/* 100 */       return (Set)new EntrySet(this.map.entrySet(), this);
/*     */     }
/* 102 */     return this.map.entrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class EntrySet
/*     */     extends AbstractSetDecorator
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */ 
/*     */ 
/*     */     
/*     */     protected EntrySet(Set set, AbstractInputCheckedMapDecorator parent) {
/* 116 */       super(set);
/* 117 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 121 */       return (Iterator)new AbstractInputCheckedMapDecorator.EntrySetIterator(((AbstractCollectionDecorator)this).collection.iterator(), this.parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 125 */       Object[] array = ((AbstractCollectionDecorator)this).collection.toArray();
/* 126 */       for (int i = 0; i < array.length; i++) {
/* 127 */         array[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)array[i], this.parent);
/*     */       }
/* 129 */       return array;
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] array) {
/* 133 */       Object[] result = array;
/* 134 */       if (array.length > 0)
/*     */       {
/*     */         
/* 137 */         result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
/*     */       }
/* 139 */       result = ((AbstractCollectionDecorator)this).collection.toArray(result);
/* 140 */       for (int i = 0; i < result.length; i++) {
/* 141 */         result[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)result[i], this.parent);
/*     */       }
/*     */ 
/*     */       
/* 145 */       if (result.length > array.length) {
/* 146 */         return result;
/*     */       }
/*     */ 
/*     */       
/* 150 */       System.arraycopy(result, 0, array, 0, result.length);
/* 151 */       if (array.length > result.length) {
/* 152 */         array[result.length] = null;
/*     */       }
/* 154 */       return array;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class EntrySetIterator
/*     */     extends AbstractIteratorDecorator
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */ 
/*     */     
/*     */     protected EntrySetIterator(Iterator iterator, AbstractInputCheckedMapDecorator parent) {
/* 167 */       super(iterator);
/* 168 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 172 */       Map.Entry entry = this.iterator.next();
/* 173 */       return new AbstractInputCheckedMapDecorator.MapEntry(entry, this.parent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class MapEntry
/*     */     extends AbstractMapEntryDecorator
/*     */   {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */ 
/*     */     
/*     */     protected MapEntry(Map.Entry entry, AbstractInputCheckedMapDecorator parent) {
/* 186 */       super(entry);
/* 187 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 191 */       value = this.parent.checkSetValue(value);
/* 192 */       return this.entry.setValue(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/map/AbstractInputCheckedMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */