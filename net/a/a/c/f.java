/*    */ package net.a.a.c;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.a.a.c;
/*    */ import net.a.a.e.d.a.a;
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
/*    */ public class f
/*    */   implements c
/*    */ {
/*    */   private final c a;
/*    */   private final String b;
/*    */   private final Color c;
/*    */   
/*    */   public f(c paramc, String paramString, Color paramColor) {
/* 49 */     this.a = paramc;
/* 50 */     this.b = paramString;
/* 51 */     this.c = paramColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object a(d paramd) {
/*    */     Object object;
/* 57 */     if (d.b.equals(paramd) && this.b != null) {
/* 58 */       object = Float.valueOf(a.a(this.b, this.a, "pt"));
/*    */     }
/* 60 */     else if (d.i.equals(paramd) && this.c != null) {
/*    */       
/* 62 */       object = this.c;
/*    */     } else {
/* 64 */       object = this.a.a(paramd);
/*    */     } 
/* 66 */     return object;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */