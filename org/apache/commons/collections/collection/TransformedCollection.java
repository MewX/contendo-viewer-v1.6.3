/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedCollection
/*     */   extends AbstractSerializableCollectionDecorator
/*     */ {
/*     */   private static final long serialVersionUID = 8692300188161871514L;
/*     */   protected final Transformer transformer;
/*     */   
/*     */   public static Collection decorate(Collection coll, Transformer transformer) {
/*  60 */     return new TransformedCollection(coll, transformer);
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
/*     */   protected TransformedCollection(Collection coll, Transformer transformer) {
/*  75 */     super(coll);
/*  76 */     if (transformer == null) {
/*  77 */       throw new IllegalArgumentException("Transformer must not be null");
/*     */     }
/*  79 */     this.transformer = transformer;
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
/*     */   protected Object transform(Object object) {
/*  91 */     return this.transformer.transform(object);
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
/*     */   protected Collection transform(Collection coll) {
/* 103 */     List list = new ArrayList(coll.size());
/* 104 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 105 */       list.add(transform(it.next()));
/*     */     }
/* 107 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object) {
/* 112 */     object = transform(object);
/* 113 */     return getCollection().add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 117 */     coll = transform(coll);
/* 118 */     return getCollection().addAll(coll);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/TransformedCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */