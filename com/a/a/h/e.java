/*    */ package com.a.a.h;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class e<T>
/*    */   implements Enumeration<T>
/*    */ {
/*    */   Iterator<T> a;
/*    */   
/*    */   public e(Iterator<T> it) {
/* 13 */     this.a = it;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMoreElements() {
/* 21 */     return this.a.hasNext();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T nextElement() {
/* 29 */     return this.a.next();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */