/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ import java.net.URI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ab
/*    */   implements ad
/*    */ {
/*    */   private final URI a;
/*    */   
/*    */   public static ab a(URI uri) {
/* 14 */     return new ab(uri);
/*    */   }
/*    */   
/*    */   private ab(URI uri) {
/* 18 */     this.a = uri;
/*    */   }
/*    */   
/*    */   public URI b() {
/* 22 */     return this.a;
/*    */   }
/*    */   
/*    */   public short a() {
/* 26 */     return 1020;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     return "url(" + this.a + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/ab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */