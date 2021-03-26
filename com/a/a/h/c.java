/*    */ package com.a.a.h;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ public class c<T> implements Enumeration<T> {
/*    */   Enumeration<Enumeration<T>> a;
/*  9 */   Enumeration<T> b = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(Enumeration[] enums) {
/* 15 */     this(new a<Enumeration<T>>((Enumeration<T>[])enums));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(Iterator<Enumeration<T>> enums) {
/* 22 */     this(new e<Enumeration<T>>(enums));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c(Enumeration<Enumeration<T>> enums) {
/* 29 */     this.a = enums;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMoreElements() {
/*    */     while (true) {
/* 38 */       a();
/* 39 */       if (this.b == null) return false; 
/* 40 */       if (this.b.hasMoreElements()) {
/* 41 */         return true;
/*    */       }
/* 43 */       this.b = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T nextElement() {
/*    */     while (true) {
/* 53 */       a();
/* 54 */       if (this.b == null) throw new NoSuchElementException(); 
/*    */       try {
/* 56 */         return this.b.nextElement();
/* 57 */       } catch (NoSuchElementException e) {
/* 58 */         this.b = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a() {
/* 67 */     while (this.b == null && 
/* 68 */       this.a.hasMoreElements())
/* 69 */       this.b = this.a.nextElement(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */