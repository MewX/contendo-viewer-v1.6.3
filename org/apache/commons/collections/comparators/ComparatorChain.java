/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComparatorChain
/*     */   implements Serializable, Comparator
/*     */ {
/*     */   private static final long serialVersionUID = -721644942746081630L;
/*  62 */   protected List comparatorChain = null;
/*     */   
/*  64 */   protected BitSet orderingBits = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isLocked = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComparatorChain() {
/*  76 */     this(new ArrayList(), new BitSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComparatorChain(Comparator comparator) {
/*  86 */     this(comparator, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComparatorChain(Comparator comparator, boolean reverse) {
/*  97 */     this.comparatorChain = new ArrayList();
/*  98 */     this.comparatorChain.add(comparator);
/*  99 */     this.orderingBits = new BitSet(1);
/* 100 */     if (reverse == true) {
/* 101 */       this.orderingBits.set(0);
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
/*     */   public ComparatorChain(List list) {
/* 114 */     this(list, new BitSet(list.size()));
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
/*     */ 
/*     */   
/*     */   public ComparatorChain(List list, BitSet bits) {
/* 133 */     this.comparatorChain = list;
/* 134 */     this.orderingBits = bits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComparator(Comparator comparator) {
/* 145 */     addComparator(comparator, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addComparator(Comparator comparator, boolean reverse) {
/* 156 */     checkLocked();
/*     */     
/* 158 */     this.comparatorChain.add(comparator);
/* 159 */     if (reverse == true) {
/* 160 */       this.orderingBits.set(this.comparatorChain.size() - 1);
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
/*     */   
/*     */   public void setComparator(int index, Comparator comparator) throws IndexOutOfBoundsException {
/* 175 */     setComparator(index, comparator, false);
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
/*     */   public void setComparator(int index, Comparator comparator, boolean reverse) {
/* 187 */     checkLocked();
/*     */     
/* 189 */     this.comparatorChain.set(index, comparator);
/* 190 */     if (reverse == true) {
/* 191 */       this.orderingBits.set(index);
/*     */     } else {
/* 193 */       this.orderingBits.clear(index);
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
/*     */   public void setForwardSort(int index) {
/* 205 */     checkLocked();
/* 206 */     this.orderingBits.clear(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReverseSort(int index) {
/* 216 */     checkLocked();
/* 217 */     this.orderingBits.set(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 226 */     return this.comparatorChain.size();
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
/*     */   public boolean isLocked() {
/* 238 */     return this.isLocked;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkLocked() {
/* 243 */     if (this.isLocked == true) {
/* 244 */       throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkChainIntegrity() {
/* 249 */     if (this.comparatorChain.size() == 0) {
/* 250 */       throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int compare(Object o1, Object o2) throws UnsupportedOperationException {
/* 267 */     if (!this.isLocked) {
/* 268 */       checkChainIntegrity();
/* 269 */       this.isLocked = true;
/*     */     } 
/*     */ 
/*     */     
/* 273 */     Iterator comparators = this.comparatorChain.iterator();
/* 274 */     for (int comparatorIndex = 0; comparators.hasNext(); comparatorIndex++) {
/*     */       
/* 276 */       Comparator comparator = comparators.next();
/* 277 */       int retval = comparator.compare(o1, o2);
/* 278 */       if (retval != 0) {
/*     */         
/* 280 */         if (this.orderingBits.get(comparatorIndex) == true) {
/* 281 */           if (Integer.MIN_VALUE == retval) {
/* 282 */             retval = Integer.MAX_VALUE;
/*     */           } else {
/* 284 */             retval *= -1;
/*     */           } 
/*     */         }
/*     */         
/* 288 */         return retval;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 294 */     return 0;
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
/*     */   public int hashCode() {
/* 306 */     int hash = 0;
/* 307 */     if (null != this.comparatorChain) {
/* 308 */       hash ^= this.comparatorChain.hashCode();
/*     */     }
/* 310 */     if (null != this.orderingBits) {
/* 311 */       hash ^= this.orderingBits.hashCode();
/*     */     }
/* 313 */     return hash;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 333 */     if (this == object)
/* 334 */       return true; 
/* 335 */     if (null == object)
/* 336 */       return false; 
/* 337 */     if (object.getClass().equals(getClass())) {
/* 338 */       ComparatorChain chain = (ComparatorChain)object;
/* 339 */       return (((null == this.orderingBits) ? (null == chain.orderingBits) : this.orderingBits.equals(chain.orderingBits)) && ((null == this.comparatorChain) ? (null == chain.comparatorChain) : this.comparatorChain.equals(chain.comparatorChain)));
/*     */     } 
/*     */     
/* 342 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/comparators/ComparatorChain.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */