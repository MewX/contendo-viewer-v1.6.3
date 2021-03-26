/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.primitives.ByteCollection;
/*     */ import org.apache.commons.collections.primitives.ByteIterator;
/*     */ import org.apache.commons.collections.primitives.ByteList;
/*     */ import org.apache.commons.collections.primitives.ByteListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractListByteList
/*     */   extends AbstractCollectionByteCollection
/*     */   implements ByteList
/*     */ {
/*     */   public void add(int index, byte element) {
/*  35 */     getList().add(index, new Byte(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, ByteCollection collection) {
/*  39 */     return getList().addAll(index, ByteCollectionCollection.wrap(collection));
/*     */   }
/*     */   
/*     */   public byte get(int index) {
/*  43 */     return ((Number)getList().get(index)).byteValue();
/*     */   }
/*     */   
/*     */   public int indexOf(byte element) {
/*  47 */     return getList().indexOf(new Byte(element));
/*     */   }
/*     */   
/*     */   public int lastIndexOf(byte element) {
/*  51 */     return getList().lastIndexOf(new Byte(element));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteListIterator listIterator() {
/*  62 */     return ListIteratorByteListIterator.wrap(getList().listIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteListIterator listIterator(int index) {
/*  73 */     return ListIteratorByteListIterator.wrap(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public byte removeElementAt(int index) {
/*  77 */     return ((Number)getList().remove(index)).byteValue();
/*     */   }
/*     */   
/*     */   public byte set(int index, byte element) {
/*  81 */     return ((Number)getList().set(index, new Byte(element))).byteValue();
/*     */   }
/*     */   
/*     */   public ByteList subList(int fromIndex, int toIndex) {
/*  85 */     return ListByteList.wrap(getList().subList(fromIndex, toIndex));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (obj instanceof ByteList) {
/*  90 */       ByteList that = (ByteList)obj;
/*  91 */       if (this == that)
/*  92 */         return true; 
/*  93 */       if (size() != that.size()) {
/*  94 */         return false;
/*     */       }
/*  96 */       ByteIterator thisiter = iterator();
/*  97 */       ByteIterator thatiter = that.iterator();
/*  98 */       while (thisiter.hasNext()) {
/*  99 */         if (thisiter.next() != thatiter.next()) {
/* 100 */           return false;
/*     */         }
/*     */       } 
/* 103 */       return true;
/*     */     } 
/*     */     
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 111 */     return getList().hashCode();
/*     */   }
/*     */   
/*     */   protected final Collection getCollection() {
/* 115 */     return getList();
/*     */   }
/*     */   
/*     */   protected abstract List getList();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractListByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */