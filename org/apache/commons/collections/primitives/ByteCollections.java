/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableByteIterator;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableByteList;
/*     */ import org.apache.commons.collections.primitives.decorators.UnmodifiableByteListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ByteCollections
/*     */ {
/*     */   public static ByteList singletonByteList(byte value) {
/*  42 */     ByteList list = new ArrayByteList(1);
/*  43 */     list.add(value);
/*  44 */     return UnmodifiableByteList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteIterator singletonByteIterator(byte value) {
/*  53 */     return singletonByteList(value).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteListIterator singletonByteListIterator(byte value) {
/*  62 */     return singletonByteList(value).listIterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteList unmodifiableByteList(ByteList list) throws NullPointerException {
/*  73 */     if (null == list) {
/*  74 */       throw new NullPointerException();
/*     */     }
/*  76 */     return UnmodifiableByteList.wrap(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteIterator unmodifiableByteIterator(ByteIterator iter) {
/*  87 */     if (null == iter) {
/*  88 */       throw new NullPointerException();
/*     */     }
/*  90 */     return UnmodifiableByteIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteListIterator unmodifiableByteListIterator(ByteListIterator iter) {
/* 101 */     if (null == iter) {
/* 102 */       throw new NullPointerException();
/*     */     }
/* 104 */     return UnmodifiableByteListIterator.wrap(iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteList getEmptyByteList() {
/* 113 */     return EMPTY_BYTE_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteIterator getEmptyByteIterator() {
/* 122 */     return EMPTY_BYTE_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteListIterator getEmptyByteListIterator() {
/* 131 */     return EMPTY_BYTE_LIST_ITERATOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final ByteList EMPTY_BYTE_LIST = unmodifiableByteList(new ArrayByteList(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final ByteIterator EMPTY_BYTE_ITERATOR = unmodifiableByteIterator(EMPTY_BYTE_LIST.iterator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ByteListIterator EMPTY_BYTE_LIST_ITERATOR = unmodifiableByteListIterator(EMPTY_BYTE_LIST.listIterator());
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ByteCollections.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */