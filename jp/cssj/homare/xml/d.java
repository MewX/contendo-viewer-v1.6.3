/*    */ package jp.cssj.homare.xml;
/*    */ 
/*    */ import jp.cssj.homare.css.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */ {
/*    */   public final String a;
/*    */   public final String b;
/*    */   public final String c;
/*    */   
/*    */   public d(String uri, String prefix, String lName) {
/* 15 */     this.a = uri;
/* 16 */     this.b = lName;
/* 17 */     this.c = prefix + ":" + lName;
/*    */   }
/*    */   
/*    */   public boolean a(String uri, String lName) {
/* 21 */     if (lName == null) {
/* 22 */       return false;
/*    */     }
/* 24 */     return (this.b.equals(lName) && this.a.equals(uri));
/*    */   }
/*    */   
/*    */   public boolean a(a ce) {
/* 28 */     return a(ce.B, ce.C);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */