/*    */ package net.a.a.e.d.b;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */ public enum b
/*    */ {
/*    */   public static b[] a() {
/*    */     return (b[])n.clone();
/*    */   }
/*    */   
/* 34 */   a("infix"),
/*    */   
/* 36 */   b("false"),
/*    */   
/* 38 */   c("false"),
/*    */   
/* 40 */   d("thickmathspace"),
/*    */   
/* 42 */   e("thickmathspace"),
/*    */   
/* 44 */   f("false"),
/*    */   
/* 46 */   g("true"),
/*    */   
/* 48 */   h("infinity"),
/*    */   
/* 50 */   i("1"),
/*    */   
/* 52 */   j("false"),
/*    */   
/* 54 */   k("false"),
/*    */   
/* 56 */   l("false");
/*    */ 
/*    */ 
/*    */   
/*    */   b(String paramString1) {
/* 61 */     this.m = paramString1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String b() {
/* 68 */     return this.m;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static b a(String paramString) {
/*    */     return Enum.<b>valueOf(b.class, paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   private final String m;
/*    */ 
/*    */   
/*    */   public static b b(String paramString) throws h {
/*    */     try {
/* 83 */       return a(paramString.toUpperCase(Locale.US));
/* 84 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 85 */       throw new h(paramString, illegalArgumentException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/b/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */