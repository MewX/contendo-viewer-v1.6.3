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
/*    */ public class e
/*    */   implements k
/*    */ {
/*    */   private final String a;
/*    */   private final URI b;
/*    */   private final boolean c;
/*    */   private final a[] d;
/*    */   
/*    */   public static class a
/*    */   {
/*    */     private final j a;
/*    */     private final ad b;
/*    */     
/*    */     public a(j info, ad value) {
/* 29 */       this.a = info;
/* 30 */       this.b = value;
/*    */     }
/*    */     
/*    */     public j a() {
/* 34 */       return this.a;
/*    */     }
/*    */     
/*    */     public ad b() {
/* 38 */       return this.b;
/*    */     }
/*    */     
/*    */     public String toString() {
/* 42 */       return this.a + "=" + this.b;
/*    */     }
/*    */   }
/*    */   
/*    */   protected e(String name, a[] entries, URI uri, boolean important) {
/* 47 */     this.a = name;
/* 48 */     this.d = entries;
/* 49 */     this.b = uri;
/* 50 */     this.c = important;
/*    */   }
/*    */   
/*    */   public String a() {
/* 54 */     return this.a;
/*    */   }
/*    */   
/*    */   public URI b() {
/* 58 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 62 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(c style) {
/* 69 */     for (int i = 0; i < this.d.length; i++) {
/* 70 */       a entry = this.d[i];
/* 71 */       j info = entry.a();
/* 72 */       style.a(info.a(style), entry.b(), this.c ? 1 : 0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     StringBuffer buff = new StringBuffer(this.a + ":");
/* 79 */     for (int i = 0; i < this.d.length; i++) {
/* 80 */       a entry = this.d[i];
/* 81 */       buff.append(' ');
/* 82 */       buff.append(entry.b());
/*    */     } 
/* 84 */     buff.append((this.c ? " !" : "") + " [uri=" + this.b + "]");
/* 85 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */