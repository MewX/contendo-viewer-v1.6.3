/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedOrderComparator
/*     */   implements Comparator
/*     */ {
/*     */   public static final int UNKNOWN_BEFORE = 0;
/*     */   public static final int UNKNOWN_AFTER = 1;
/*     */   public static final int UNKNOWN_THROW_EXCEPTION = 2;
/*  72 */   private final Map map = new HashMap();
/*     */   
/*  74 */   private int counter = 0;
/*     */   
/*     */   private boolean isLocked = false;
/*     */   
/*  78 */   private int unknownObjectBehavior = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FixedOrderComparator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FixedOrderComparator(Object[] items) {
/* 100 */     if (items == null) {
/* 101 */       throw new IllegalArgumentException("The list of items must not be null");
/*     */     }
/* 103 */     for (int i = 0; i < items.length; i++) {
/* 104 */       add(items[i]);
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
/*     */   public FixedOrderComparator(List items) {
/* 119 */     if (items == null) {
/* 120 */       throw new IllegalArgumentException("The list of items must not be null");
/*     */     }
/* 122 */     for (Iterator it = items.iterator(); it.hasNext();) {
/* 123 */       add(it.next());
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
/*     */   public boolean isLocked() {
/* 137 */     return this.isLocked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkLocked() {
/* 146 */     if (isLocked()) {
/* 147 */       throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnknownObjectBehavior() {
/* 158 */     return this.unknownObjectBehavior;
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
/*     */   public void setUnknownObjectBehavior(int unknownObjectBehavior) {
/* 170 */     checkLocked();
/* 171 */     if (unknownObjectBehavior != 1 && unknownObjectBehavior != 0 && unknownObjectBehavior != 2)
/*     */     {
/*     */       
/* 174 */       throw new IllegalArgumentException("Unrecognised value for unknown behaviour flag");
/*     */     }
/* 176 */     this.unknownObjectBehavior = unknownObjectBehavior;
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
/*     */   public boolean add(Object obj) {
/* 192 */     checkLocked();
/* 193 */     Object position = this.map.put(obj, new Integer(this.counter++));
/* 194 */     return (position == null);
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
/*     */   public boolean addAsEqual(Object existingObj, Object newObj) {
/* 211 */     checkLocked();
/* 212 */     Integer position = (Integer)this.map.get(existingObj);
/* 213 */     if (position == null) {
/* 214 */       throw new IllegalArgumentException(existingObj + " not known to " + this);
/*     */     }
/* 216 */     Object result = this.map.put(newObj, position);
/* 217 */     return (result == null);
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
/*     */   public int compare(Object obj1, Object obj2) {
/* 237 */     this.isLocked = true;
/* 238 */     Integer position1 = (Integer)this.map.get(obj1);
/* 239 */     Integer position2 = (Integer)this.map.get(obj2);
/* 240 */     if (position1 == null || position2 == null) {
/* 241 */       Object unknownObj; switch (this.unknownObjectBehavior) {
/*     */         case 0:
/* 243 */           if (position1 == null) {
/* 244 */             return (position2 == null) ? 0 : -1;
/*     */           }
/* 246 */           return 1;
/*     */         
/*     */         case 1:
/* 249 */           if (position1 == null) {
/* 250 */             return (position2 == null) ? 0 : 1;
/*     */           }
/* 252 */           return -1;
/*     */         
/*     */         case 2:
/* 255 */           unknownObj = (position1 == null) ? obj1 : obj2;
/* 256 */           throw new IllegalArgumentException("Attempting to compare unknown object " + unknownObj);
/*     */       } 
/* 258 */       throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + this.unknownObjectBehavior);
/*     */     } 
/*     */     
/* 261 */     return position1.compareTo(position2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/comparators/FixedOrderComparator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */