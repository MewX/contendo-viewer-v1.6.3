/*    */ package de.codecentric.centerdevice.a;
/*    */ 
/*    */ import com.sun.javafx.tk.Toolkit;
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   private static a a;
/*    */   private d b;
/*    */   private c c;
/*    */   
/*    */   private a(d systemMenuAdapter, c applicationAdapter) {
/* 13 */     this.b = systemMenuAdapter;
/* 14 */     this.c = applicationAdapter;
/*    */   }
/*    */   
/*    */   public static a a() {
/* 18 */     if (a == null) {
/* 19 */       a = d();
/*    */     }
/*    */     
/* 22 */     return a;
/*    */   }
/*    */   
/*    */   public c b() {
/* 26 */     return this.c;
/*    */   }
/*    */   
/*    */   public d c() {
/* 30 */     return this.b;
/*    */   }
/*    */   
/*    */   private static a d() {
/* 34 */     if (!Toolkit.getToolkit().getSystemMenu().isSupported()) {
/* 35 */       return null;
/*    */     }
/*    */     
/*    */     try {
/* 39 */       return new a(new d(), new c());
/* 40 */     } catch (ReflectiveOperationException e) {
/* 41 */       throw new b(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */