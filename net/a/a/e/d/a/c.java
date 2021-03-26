/*    */ package net.a.a.e.d.a;
/*    */ 
/*    */ import net.a.a.g.d;
/*    */ import net.a.a.g.f;
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
/*    */ public enum c
/*    */ {
/*    */   a, b, c;
/*    */   public static final String d = "center";
/*    */   public static final String e = "right";
/*    */   
/*    */   public static c[] a() {
/* 27 */     return (c[])g.clone(); } public static c a(String paramString) { return Enum.<c>valueOf(c.class, paramString); }
/*    */    static {
/* 29 */     a = new c("LEFT", 0);
/*    */     
/* 31 */     b = new c("CENTER", 1);
/*    */     
/* 33 */     c = new c("RIGHT", 2);
/*    */     g = new c[] { a, b, c };
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static c a(String paramString, c paramc) {
/*    */     c c1;
/* 52 */     if ("center".equalsIgnoreCase(paramString)) {
/* 53 */       c1 = b;
/* 54 */     } else if ("left".equalsIgnoreCase(paramString)) {
/* 55 */       c1 = a;
/* 56 */     } else if ("right".equalsIgnoreCase(paramString)) {
/* 57 */       c1 = c;
/*    */     } else {
/* 59 */       c1 = paramc;
/*    */     } 
/* 61 */     return c1;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float a(f paramf, d paramd, float paramFloat) {
/* 78 */     switch (null.a[ordinal()])
/*    */     { case 1:
/* 80 */         f1 = 0.0F;
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
/* 92 */         return f1;case 2: f1 = paramFloat - paramd.d(paramf); return f1;case 3: f1 = paramFloat / 2.0F - paramd.e(paramf); return f1; }  if (!f) throw new AssertionError();  float f1 = 0.0F; return f1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/a/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */