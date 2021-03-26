/*     */ package com.a.a.h;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class f<K> implements Set<K> {
/*     */   final HashSet<K> a;
/*  11 */   final LinkedList<K> b = new LinkedList<K>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public f() {
/*  17 */     this.a = new HashSet<K>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public f(int nums) {
/*  25 */     this.a = new HashSet<K>(nums);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  33 */     return this.a.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  41 */     return this.a.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  49 */     this.a.clear();
/*  50 */     this.b.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  58 */     return this.a.contains(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<K> iterator() {
/*  66 */     return this.b.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  74 */     return this.b.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(Object[] a) {
/*  82 */     return this.b.toArray((T[])a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(K e) {
/*     */     boolean rtn;
/*  91 */     if (!(rtn = this.a.add(e))) {
/*  92 */       this.b.remove(e);
/*     */     }
/*  94 */     this.b.addFirst(e);
/*  95 */     return rtn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 103 */     return this.a.contains(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends K> c) {
/* 111 */     boolean rtn = false;
/* 112 */     for (K k : c) {
/* 113 */       if (this.a.add(k)) {
/* 114 */         rtn = true;
/*     */       } else {
/* 116 */         this.b.remove(k);
/*     */       } 
/* 118 */       this.b.addFirst(k);
/*     */     } 
/* 120 */     return rtn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 128 */     return this.a.retainAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 136 */     this.b.removeAll(c);
/* 137 */     return this.a.removeAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/* 145 */     this.b.remove(o);
/* 146 */     return this.a.remove(o);
/*     */   }
/*     */   
/*     */   public K a() {
/* 150 */     return this.b.getFirst();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */