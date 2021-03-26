/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedCollection
/*     */   extends AbstractSerializableCollectionDecorator
/*     */ {
/*     */   private static final long serialVersionUID = -5259182142076705162L;
/*     */   protected final Predicate predicate;
/*     */   
/*     */   public static Collection decorate(Collection coll, Predicate predicate) {
/*  63 */     return new PredicatedCollection(coll, predicate);
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
/*     */   protected PredicatedCollection(Collection coll, Predicate predicate) {
/*  79 */     super(coll);
/*  80 */     if (predicate == null) {
/*  81 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  83 */     this.predicate = predicate;
/*  84 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/*  85 */       validate(it.next());
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
/*     */   protected void validate(Object object) {
/*  99 */     if (!this.predicate.evaluate(object)) {
/* 100 */       throw new IllegalArgumentException("Cannot add Object '" + object + "' - Predicate rejected it");
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
/*     */   public boolean add(Object object) {
/* 114 */     validate(object);
/* 115 */     return getCollection().add(object);
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
/* 128 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 129 */       validate(it.next());
/*     */     }
/* 131 */     return getCollection().addAll(coll);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/PredicatedCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */