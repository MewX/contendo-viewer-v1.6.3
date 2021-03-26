/*    */ package com.a.a.h;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ 
/*    */ 
/*    */ public class a<E>
/*    */   implements Enumeration<E>
/*    */ {
/*    */   private E[] a;
/*    */   private int b;
/*    */   
/*    */   public a(Object[] array) {
/* 13 */     this.a = (E[])array;
/* 14 */     this.b = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMoreElements() {
/* 22 */     return (this.a == null && this.a.length > this.b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public E nextElement() {
/* 30 */     return this.a[this.b++];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */