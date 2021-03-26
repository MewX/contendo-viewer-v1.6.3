/*    */ package jp.cssj.homare.css.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.f.ad;
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
/*    */ public class i
/*    */   implements k
/*    */ {
/*    */   private final j a;
/*    */   private final ad b;
/*    */   private final URI c;
/*    */   private final boolean d;
/*    */   
/*    */   protected i(j info, ad value, URI uri, boolean important) {
/* 25 */     this.a = info;
/* 26 */     this.b = value;
/* 27 */     this.c = uri;
/* 28 */     this.d = important;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 32 */     return this.d;
/*    */   }
/*    */   
/*    */   public String a() {
/* 36 */     return this.a.b();
/*    */   }
/*    */   
/*    */   public URI b() {
/* 40 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(c style) {
/* 47 */     byte important = this.d ? 1 : 0;
/* 48 */     j info = this.a.a(style);
/* 49 */     style.a(info, this.b, important);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     return this.a.b() + ": " + this.b + (this.d ? " !" : "") + " [uri=" + this.c + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */