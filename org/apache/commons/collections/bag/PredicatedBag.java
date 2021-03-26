/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.collection.PredicatedCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedBag
/*     */   extends PredicatedCollection
/*     */   implements Bag
/*     */ {
/*     */   private static final long serialVersionUID = -2575833140344736876L;
/*     */   
/*     */   public static Bag decorate(Bag bag, Predicate predicate) {
/*  62 */     return new PredicatedBag(bag, predicate);
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
/*     */   protected PredicatedBag(Bag bag, Predicate predicate) {
/*  78 */     super((Collection)bag, predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Bag getBag() {
/*  87 */     return (Bag)getCollection();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object object, int count) {
/*  92 */     validate(object);
/*  93 */     return getBag().add(object, count);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int count) {
/*  97 */     return getBag().remove(object, count);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 101 */     return getBag().uniqueSet();
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 105 */     return getBag().getCount(object);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/PredicatedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */