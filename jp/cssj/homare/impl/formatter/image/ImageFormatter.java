/*    */ package jp.cssj.homare.impl.formatter.image;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.b.d;
/*    */ import jp.cssj.c.b;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.a.a;
/*    */ import jp.cssj.homare.b.d.b;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ import jp.cssj.homare.formatter.Formatter;
/*    */ import jp.cssj.homare.impl.ua.e;
/*    */ import jp.cssj.homare.impl.ua.image.RasterImageLoader;
/*    */ import jp.cssj.homare.ua.ImageLoader;
/*    */ import jp.cssj.homare.ua.a;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ 
/*    */ public class ImageFormatter
/*    */   implements Formatter
/*    */ {
/* 24 */   private static final Logger a = Logger.getLogger(ImageFormatter.class.getName());
/*    */   
/*    */   public boolean match(b source) {
/*    */     try {
/* 28 */       String mimeType = source.c();
/* 29 */       if (mimeType != null && !mimeType.startsWith("image/")) {
/* 30 */         return false;
/*    */       }
/* 32 */       ImageLoader loader = (ImageLoader)b.a().a(ImageLoader.class, source);
/* 33 */       if (loader instanceof RasterImageLoader) {
/* 34 */         return ((RasterImageLoader)loader).available(source);
/*    */       }
/* 36 */       return (loader != null);
/* 37 */     } catch (IOException e) {
/* 38 */       a.log(Level.WARNING, "変換元文書のMIME型を取得できませんでした", e);
/*    */       
/* 40 */       return false;
/*    */     } 
/*    */   }
/*    */   public void format(b source, m ua) throws a, d {
/*    */     try {
/* 45 */       b image = ua.c(source);
/* 46 */       double iw = image.a();
/* 47 */       double ih = image.b();
/*    */       
/* 49 */       e e = new e(ua);
/* 50 */       e.c(iw);
/* 51 */       e.d(ih);
/* 52 */       e.a(ua, (b)e);
/*    */       
/* 54 */       b gc = e.a();
/* 55 */       if (gc != null) {
/* 56 */         gc.a(image);
/* 57 */         e.b();
/*    */       } 
/* 59 */       e.z();
/* 60 */     } catch (IOException e) {
/* 61 */       short code = 12290;
/* 62 */       String[] args = { e.getMessage() };
/* 63 */       String mes = a.b(code, args);
/* 64 */       ua.a(code, args);
/* 65 */       a.log(Level.WARNING, mes, e);
/* 66 */       throw new d(code, args, mes);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/formatter/image/ImageFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */