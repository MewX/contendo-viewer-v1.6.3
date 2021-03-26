/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntrySetMapIterator
/*     */   implements MapIterator, ResettableIterator
/*     */ {
/*     */   private final Map map;
/*     */   private Iterator iterator;
/*     */   private Map.Entry last;
/*     */   private boolean canRemove = false;
/*     */   
/*     */   public EntrySetMapIterator(Map map) {
/*  55 */     this.map = map;
/*  56 */     this.iterator = map.entrySet().iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/*  66 */     return this.iterator.hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/*  76 */     this.last = this.iterator.next();
/*  77 */     this.canRemove = true;
/*  78 */     return this.last.getKey();
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
/*     */   public void remove() {
/*  93 */     if (!this.canRemove) {
/*  94 */       throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */     }
/*  96 */     this.iterator.remove();
/*  97 */     this.last = null;
/*  98 */     this.canRemove = false;
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
/*     */   public Object getKey() {
/* 110 */     if (this.last == null) {
/* 111 */       throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */     }
/* 113 */     return this.last.getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 124 */     if (this.last == null) {
/* 125 */       throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */     }
/* 127 */     return this.last.getValue();
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
/*     */   public Object setValue(Object value) {
/* 141 */     if (this.last == null) {
/* 142 */       throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */     }
/* 144 */     return this.last.setValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 152 */     this.iterator = this.map.entrySet().iterator();
/* 153 */     this.last = null;
/* 154 */     this.canRemove = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 163 */     if (this.last != null) {
/* 164 */       return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */     }
/* 166 */     return "MapIterator[]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EntrySetMapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */