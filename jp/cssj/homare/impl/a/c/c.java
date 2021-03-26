/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.ab;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   extends b
/*    */ {
/* 27 */   public static final j a = (j)new c();
/* 28 */   private static final Logger b = Logger.getLogger(c.class.getName());
/*    */   
/*    */   public static b c(jp.cssj.homare.css.c style) {
/* 31 */     ad value = style.a(a);
/* 32 */     if (value.a() == 1007) {
/* 33 */       return null;
/*    */     }
/* 35 */     m ua = style.b();
/* 36 */     ab uriValue = (ab)value;
/* 37 */     URI uri = uriValue.b();
/*    */     try {
/* 39 */       b source = ua.b(uri);
/*    */       try {
/* 41 */         return ua.c(source);
/*    */       } finally {
/* 43 */         ua.a(source);
/*    */       } 
/* 45 */     } catch (Exception e) {
/* 46 */       b.log(Level.FINE, "Missing image", e);
/* 47 */       ua.a((short)10257, uri.toString());
/* 48 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected c() {
/* 53 */     super("background-image");
/*    */   }
/*    */   
/*    */   public ad b(jp.cssj.homare.css.c style) {
/* 57 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, jp.cssj.homare.css.c style) {
/* 65 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 69 */     if (l.b(lu)) {
/* 70 */       return (ad)H.a;
/*    */     }
/*    */     try {
/* 73 */       ab value = l.a(ua, uri, lu);
/* 74 */       if (value != null) {
/* 75 */         return (ad)value;
/*    */       }
/* 77 */     } catch (URISyntaxException e) {
/* 78 */       ua.a((short)10252, lu.getStringValue());
/*    */     } 
/* 80 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */