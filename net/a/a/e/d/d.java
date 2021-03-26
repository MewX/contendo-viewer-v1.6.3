/*    */ package net.a.a.e.d;
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
/*    */ public final class d
/*    */ {
/*    */   public static final float a = 1.0F;
/*    */   
/*    */   public static float a(c paramc) {
/* 47 */     float f1 = (float)Math.pow(((Float)paramc
/* 48 */         .a(net.a.a.c.d.d)).floatValue(), ((Integer)paramc
/* 49 */         .a(net.a.a.c.d.e)).intValue());
/* 50 */     float f2 = ((Float)paramc
/* 51 */       .a(net.a.a.c.d.b)).floatValue();
/* 52 */     float f3 = ((Float)paramc
/* 53 */       .a(net.a.a.c.d.c)).floatValue();
/*    */     
/* 55 */     float f4 = f2 * f1;
/*    */     
/* 57 */     return Math.max(Math.min(f3, f2), f4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static float b(c paramc) {
/* 68 */     float f = a(paramc) / 12.0F;
/*    */     
/* 70 */     if (f < 1.0F) {
/* 71 */       f = 1.0F;
/*    */     }
/* 73 */     return f;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */