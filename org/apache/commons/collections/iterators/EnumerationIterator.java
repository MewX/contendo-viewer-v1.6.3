/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnumerationIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Collection collection;
/*     */   private Enumeration enumeration;
/*     */   private Object last;
/*     */   
/*     */   public EnumerationIterator() {
/*  48 */     this(null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumerationIterator(Enumeration enumeration) {
/*  58 */     this(enumeration, null);
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
/*     */   public EnumerationIterator(Enumeration enumeration, Collection collection) {
/*  70 */     this.enumeration = enumeration;
/*  71 */     this.collection = collection;
/*  72 */     this.last = null;
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
/*     */   public boolean hasNext() {
/*  84 */     return this.enumeration.hasMoreElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/*  94 */     this.last = this.enumeration.nextElement();
/*  95 */     return this.last;
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
/*     */   public void remove() {
/* 109 */     if (this.collection != null) {
/* 110 */       if (this.last != null) {
/* 111 */         this.collection.remove(this.last);
/*     */       } else {
/* 113 */         throw new IllegalStateException("next() must have been called for remove() to function");
/*     */       } 
/*     */     } else {
/* 116 */       throw new UnsupportedOperationException("No Collection associated with this Iterator");
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
/*     */   public Enumeration getEnumeration() {
/* 128 */     return this.enumeration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnumeration(Enumeration enumeration) {
/* 137 */     this.enumeration = enumeration;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EnumerationIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */