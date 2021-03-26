/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.css.c;
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
/*    */ public class P
/*    */   extends b
/*    */ {
/* 27 */   private static final Logger b = Logger.getLogger(P.class.getName());
/*    */   
/* 29 */   public static final j a = (j)new P();
/*    */   
/*    */   public static b c(c style) {
/* 32 */     ad value = style.a(a);
/* 33 */     if (value.a() == 1007) {
/* 34 */       return null;
/*    */     }
/* 36 */     m ua = style.b();
/* 37 */     ab uriValue = (ab)value;
/* 38 */     URI uri = uriValue.b();
/*    */     try {
/* 40 */       b source = ua.b(uri);
/*    */       try {
/* 42 */         return ua.c(source);
/*    */       } finally {
/* 44 */         ua.a(source);
/*    */       } 
/* 46 */     } catch (Exception e) {
/* 47 */       b.log(Level.FINE, "Missing image", e);
/* 48 */       ua.a((short)10257, uri.toString());
/* 49 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected P() {
/* 54 */     super("list-style-image");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 58 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 62 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 70 */     if (l.b(lu)) {
/* 71 */       return (ad)H.a;
/*    */     }
/*    */     
/*    */     try {
/* 75 */       ab value = l.a(ua, uri, lu);
/* 76 */       if (value != null) {
/* 77 */         return (ad)value;
/*    */       }
/* 79 */     } catch (URISyntaxException e) {
/* 80 */       ua.a((short)10252, lu.getStringValue());
/*    */     } 
/* 82 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */