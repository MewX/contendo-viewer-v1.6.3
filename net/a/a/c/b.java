/*    */ package net.a.a.c;
/*    */ 
/*    */ import net.a.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements c
/*    */ {
/*    */   private final c a;
/*    */   private final boolean b;
/*    */   
/*    */   public b(c paramc) {
/* 39 */     this.a = paramc;
/* 40 */     this.b = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public b(c paramc, boolean paramBoolean) {
/* 53 */     this.a = paramc;
/* 54 */     this.b = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object a(d paramd) {
/*    */     Object object;
/* 60 */     if (d.a.equals(paramd)) {
/* 61 */       object = a.b;
/* 62 */     } else if (this.b && d.e
/* 63 */       .equals(paramd) && a.b
/* 64 */       .equals(this.a
/* 65 */         .a(d.a))) {
/* 66 */       object = Integer.valueOf(((Integer)this.a
/* 67 */           .a(d.e)).intValue() + 1);
/*    */     } else {
/* 69 */       object = this.a.a(paramd);
/*    */     } 
/* 71 */     return object;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */