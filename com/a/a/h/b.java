/*    */ package com.a.a.h;
/*    */ 
/*    */ public class b<T>
/*    */ {
/*  5 */   private b<T> a = null;
/*  6 */   private b<T> b = null;
/*    */   private T c;
/*    */   
/*    */   public b() {
/* 10 */     this.c = null;
/*    */   }
/*    */   
/*    */   private b(T value) {
/* 14 */     this.c = value;
/*    */   }
/*    */   
/*    */   public T a() {
/* 18 */     return this.c;
/*    */   }
/*    */   
/*    */   public b<T> b() {
/* 22 */     return this.b;
/*    */   }
/*    */   
/*    */   public b<T> c() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public void a(T value) {
/* 30 */     b<T> elm = new b(value);
/* 31 */     elm.b = this.b;
/* 32 */     elm.a = this;
/* 33 */     this.b = elm;
/*    */   }
/*    */   
/*    */   public void b(T value) {
/* 37 */     e().a(value);
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 41 */     return (this.b != null);
/*    */   }
/*    */   
/*    */   public b<T> e() {
/* 45 */     b<T> tmp = this;
/* 46 */     for (; tmp.d(); tmp = tmp.b());
/* 47 */     return tmp;
/*    */   }
/*    */   
/*    */   public void c(T value) {
/* 51 */     b<T> elm = new b(value);
/* 52 */     elm.b = this;
/* 53 */     elm.a = this.a;
/* 54 */     this.a = elm;
/*    */   }
/*    */   
/*    */   public void f() {
/* 58 */     if (this.a != null) {
/* 59 */       this.a.b = this.b;
/* 60 */       this.a = null;
/*    */     } 
/* 62 */     if (this.b != null) {
/* 63 */       this.b.a = this.a;
/* 64 */       this.b = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public b<T> g() {
/* 69 */     b<T> tmp = c();
/* 70 */     f();
/* 71 */     return tmp;
/*    */   }
/*    */   
/*    */   public b<T> h() {
/* 75 */     b<T> tmp = b();
/* 76 */     f();
/* 77 */     return tmp;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */