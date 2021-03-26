/*    */ package jp.cssj.sakae.pdf.c.b;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import jp.cssj.sakae.a.b;
/*    */ import jp.cssj.sakae.e.d;
/*    */ 
/*    */ public class a implements Serializable {
/*    */   private static final long n = 0L;
/*    */   public String a;
/*    */   public String b;
/*    */   public String c;
/*    */   
/*    */   public static class a implements Serializable {
/* 16 */     public int a = -1;
/*    */     
/*    */     private static final long f = 0L;
/*    */     public String b;
/* 20 */     public short c = 0;
/*    */     
/* 22 */     public Map<String, String> d = null;
/*    */     
/* 24 */     public Map<String, Short> e = null;
/*    */     
/*    */     public void a(String sname, short kerning) {
/* 27 */       if (this.e == null) {
/* 28 */         this.e = new HashMap<>();
/*    */       }
/* 30 */       this.e.put(sname, d.a(kerning));
/*    */     }
/*    */     
/*    */     public void a(String sname, String lname) {
/* 34 */       if (this.d == null) {
/* 35 */         this.d = new HashMap<>();
/*    */       }
/* 37 */       this.d.put(sname, lname);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public short d = 1000, e = 0, f = 700, g = 0, h = 0, i = 500;
/*    */   
/* 45 */   public short j = 400;
/*    */   public boolean k = false;
/*    */   public b l;
/*    */   public Map<String, a> m;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */