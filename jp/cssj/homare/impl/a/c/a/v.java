/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.e.e.d;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.a.i;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class v
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new v();
/*    */   
/*    */   public static URI[] c(c style) {
/* 28 */     ad value = style.a(a);
/* 29 */     if (value.a() == 1007) {
/* 30 */       return null;
/*    */     }
/* 32 */     i srcValue = (i)value;
/* 33 */     return srcValue.b();
/*    */   }
/*    */   
/*    */   protected v() {
/* 37 */     super("src");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 41 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 49 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 53 */     List<URI> list = new ArrayList<>();
/*    */     while (true) {
/* 55 */       switch (lu.getLexicalUnitType()) {
/*    */         case 24:
/*    */           try {
/* 58 */             URI uriv = d.a(ua.c().c(), uri, lu.getStringValue());
/* 59 */             list.add(uriv);
/* 60 */           } catch (URISyntaxException e) {
/* 61 */             ua.a((short)10252, lu.getStringValue());
/*    */           } 
/*    */           break;
/*    */         case 41:
/* 65 */           if (lu.getFunctionName().equalsIgnoreCase("local")) {
/* 66 */             LexicalUnit param = lu.getParameters();
/* 67 */             while (param != null) {
/* 68 */               String name = param.getStringValue();
/*    */               try {
/* 70 */                 list.add(d.a("UTF-8", "local-font:" + name));
/* 71 */               } catch (URISyntaxException e) {
/* 72 */                 throw new l();
/*    */               } 
/* 74 */               param = param.getNextLexicalUnit();
/*    */             } 
/*    */           } 
/*    */           break;
/*    */       } 
/*    */ 
/*    */       
/* 81 */       lu = lu.getNextLexicalUnit();
/* 82 */       if (lu == null)
/* 83 */         return (ad)new i(list.<URI>toArray(new URI[list.size()])); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */