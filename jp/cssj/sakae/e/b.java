/*    */ package jp.cssj.sakae.e;
/*    */ 
/*    */ import jp.cssj.sakae.c.c.a;
/*    */ import jp.cssj.sakae.c.c.c;
/*    */ import jp.cssj.sakae.c.c.g;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class b
/*    */ {
/*    */   public static jp.cssj.sakae.c.c.b a(jp.cssj.sakae.c.c.b color) {
/*    */     float gray;
/* 16 */     switch (color.c()) {
/*    */       case 1:
/* 18 */         gray = a(color.a(0), color.a(1), color
/* 19 */             .a(2));
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
/* 35 */         return (jp.cssj.sakae.c.c.b)c.a(gray);case 2: gray = a(color.a(0), color.a(1), color.a(2), color.a(3)); return (jp.cssj.sakae.c.c.b)c.a(gray);
/*    */       case 3:
/*    */         return color;
/*    */       case 4:
/*    */         gray = a(color.a(0), color.a(1), color.a(2)); return (jp.cssj.sakae.c.c.b)g.b(gray, gray, gray, color.a(3));
/* 40 */     }  throw new IllegalStateException(); } public static float a(float r, float g, float f1) { return (float)(0.3D * r + 0.59D * g + 0.11D * f1); }
/*    */ 
/*    */ 
/*    */   
/*    */   public static float a(float c, float m, float y, float k) {
/* 45 */     return (float)(1.0D - Math.min(1.0D, 0.3D * c + 0.59D * m + 0.11D * y + k)); } public static a b(jp.cssj.sakae.c.c.b color) {
/*    */     float c;
/*    */     float m;
/*    */     float y;
/*    */     float k;
/* 50 */     switch (color.c()) {
/*    */       
/*    */       case 1:
/*    */       case 4:
/* 54 */         c = 1.0F - color.a(0);
/* 55 */         m = 1.0F - color.a(1);
/* 56 */         y = 1.0F - color.a(2);
/* 57 */         k = Math.min(Math.min(c, m), y);
/* 58 */         c = Math.max(0.0F, c - k);
/* 59 */         m = Math.max(0.0F, m - k);
/* 60 */         y = Math.max(0.0F, y - k);
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
/* 72 */         return a.a(c, m, y, k);case 2: return (a)color;case 3: c = m = y = 0.0F; k = 1.0F - color.a(0); return a.a(c, m, y, k);
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */