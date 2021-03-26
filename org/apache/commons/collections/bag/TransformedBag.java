/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections.collection.TransformedCollection;
/*     */ import org.apache.commons.collections.set.TransformedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedBag
/*     */   extends TransformedCollection
/*     */   implements Bag
/*     */ {
/*     */   private static final long serialVersionUID = 5421170911299074185L;
/*     */   
/*     */   public static Bag decorate(Bag bag, Transformer transformer) {
/*  58 */     return new TransformedBag(bag, transformer);
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
/*     */   protected TransformedBag(Bag bag, Transformer transformer) {
/*  73 */     super((Collection)bag, transformer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Bag getBag() {
/*  82 */     return (Bag)((AbstractCollectionDecorator)this).collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCount(Object object) {
/*  87 */     return getBag().getCount(object);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/*  91 */     return getBag().remove(object, nCopies);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object, int nCopies) {
/*  96 */     object = transform(object);
/*  97 */     return getBag().add(object, nCopies);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 101 */     Set set = getBag().uniqueSet();
/* 102 */     return TransformedSet.decorate(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/TransformedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */