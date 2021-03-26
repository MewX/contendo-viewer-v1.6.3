/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LazyList
/*     */   extends AbstractSerializableListDecorator
/*     */ {
/*     */   private static final long serialVersionUID = -1708388017160694542L;
/*     */   protected final Factory factory;
/*     */   
/*     */   public static List decorate(List list, Factory factory) {
/*  74 */     return new LazyList(list, factory);
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
/*     */   protected LazyList(List list, Factory factory) {
/*  86 */     super(list);
/*  87 */     if (factory == null) {
/*  88 */       throw new IllegalArgumentException("Factory must not be null");
/*     */     }
/*  90 */     this.factory = factory;
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
/*     */   public Object get(int index) {
/* 105 */     int size = getList().size();
/* 106 */     if (index < size) {
/*     */       
/* 108 */       Object object1 = getList().get(index);
/* 109 */       if (object1 == null) {
/*     */         
/* 111 */         object1 = this.factory.create();
/* 112 */         getList().set(index, object1);
/* 113 */         return object1;
/*     */       } 
/*     */       
/* 116 */       return object1;
/*     */     } 
/*     */ 
/*     */     
/* 120 */     for (int i = size; i < index; i++) {
/* 121 */       getList().add(null);
/*     */     }
/*     */     
/* 124 */     Object object = this.factory.create();
/* 125 */     getList().add(object);
/* 126 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 132 */     List sub = getList().subList(fromIndex, toIndex);
/* 133 */     return new LazyList(sub, this.factory);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/LazyList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */