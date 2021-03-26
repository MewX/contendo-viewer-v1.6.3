/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.collections.collection.CompositeCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeSet
/*     */   extends CompositeCollection
/*     */   implements Set
/*     */ {
/*     */   public CompositeSet() {}
/*     */   
/*     */   public CompositeSet(Set set) {
/*  50 */     super(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeSet(Set[] sets) {
/*  57 */     super((Collection[])sets);
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
/*     */   public synchronized void addComposited(Collection c) {
/*  72 */     if (!(c instanceof Set)) {
/*  73 */       throw new IllegalArgumentException("Collections added must implement java.util.Set");
/*     */     }
/*     */     
/*  76 */     for (Iterator i = getCollections().iterator(); i.hasNext(); ) {
/*  77 */       Set set = i.next();
/*  78 */       Collection intersects = CollectionUtils.intersection(set, c);
/*  79 */       if (intersects.size() > 0) {
/*  80 */         if (this.mutator == null) {
/*  81 */           throw new UnsupportedOperationException("Collision adding composited collection with no SetMutator set");
/*     */         }
/*     */         
/*  84 */         if (!(this.mutator instanceof SetMutator)) {
/*  85 */           throw new UnsupportedOperationException("Collision adding composited collection to a CompositeSet with a CollectionMutator instead of a SetMutator");
/*     */         }
/*     */         
/*  88 */         ((SetMutator)this.mutator).resolveCollision(this, set, (Set)c, intersects);
/*  89 */         if (CollectionUtils.intersection(set, c).size() > 0) {
/*  90 */           throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     super.addComposited(new Collection[] { c });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addComposited(Collection c, Collection d) {
/* 104 */     if (!(c instanceof Set)) throw new IllegalArgumentException("Argument must implement java.util.Set"); 
/* 105 */     if (!(d instanceof Set)) throw new IllegalArgumentException("Argument must implement java.util.Set"); 
/* 106 */     addComposited((Collection[])new Set[] { (Set)c, (Set)d });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addComposited(Collection[] comps) {
/* 115 */     for (int i = comps.length - 1; i >= 0; i--) {
/* 116 */       addComposited(comps[i]);
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
/*     */   public void setMutator(CompositeCollection.CollectionMutator mutator) {
/* 128 */     super.setMutator(mutator);
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
/*     */   public boolean remove(Object obj) {
/* 141 */     for (Iterator i = getCollections().iterator(); i.hasNext(); ) {
/* 142 */       Set set = i.next();
/* 143 */       if (set.contains(obj)) return set.remove(obj); 
/*     */     } 
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 153 */     if (obj instanceof Set) {
/* 154 */       Set set = (Set)obj;
/* 155 */       if (set.containsAll(this) && set.size() == size()) {
/* 156 */         return true;
/*     */       }
/*     */     } 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 166 */     int code = 0;
/* 167 */     for (Iterator i = iterator(); i.hasNext(); ) {
/* 168 */       Object next = i.next();
/* 169 */       code += (next != null) ? next.hashCode() : 0;
/*     */     } 
/* 171 */     return code;
/*     */   }
/*     */   
/*     */   public static interface SetMutator extends CompositeCollection.CollectionMutator {
/*     */     void resolveCollision(CompositeSet param1CompositeSet, Set param1Set1, Set param1Set2, Collection param1Collection);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/CompositeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */