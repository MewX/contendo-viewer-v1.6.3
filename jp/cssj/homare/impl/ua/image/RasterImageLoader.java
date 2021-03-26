/*    */ package jp.cssj.homare.impl.ua.image;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.imageio.ImageReader;
/*    */ import javax.imageio.stream.FileCacheImageInputStream;
/*    */ import javax.imageio.stream.FileImageInputStream;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.ua.ImageLoader;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.b.b.b;
/*    */ import jp.cssj.sakae.b.c.a;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ 
/*    */ public class RasterImageLoader
/*    */   implements ImageLoader
/*    */ {
/*    */   public boolean match(b key) {
/* 22 */     return true;
/*    */   }
/*    */   
/*    */   public boolean available(b source) throws IOException {
/*    */     ImageInputStream imageIn;
/* 27 */     if (source.k()) {
/* 28 */       imageIn = new FileImageInputStream(source.l());
/*    */     } else {
/* 30 */       imageIn = ImageIO.createImageInputStream(new BufferedInputStream(source.h()));
/*    */     } 
/*    */     try {
/* 33 */       Iterator<ImageReader> readers = ImageIO.getImageReaders(imageIn);
/* 34 */       if (readers == null || !readers.hasNext()) {
/* 35 */         return false;
/*    */       }
/* 37 */       return true;
/*    */     } finally {
/* 39 */       imageIn.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public b loadImage(m ua, b source) throws IOException {
/*    */     ImageInputStream imageIn;
/* 45 */     if (source.k()) {
/* 46 */       imageIn = new FileImageInputStream(source.l());
/*    */     } else {
/* 48 */       imageIn = new FileCacheImageInputStream(source.h(), null);
/*    */     } 
/*    */     try {
/* 51 */       Iterator<ImageReader> readers = ImageIO.getImageReaders(imageIn);
/* 52 */       if (readers == null || !readers.hasNext()) {
/* 53 */         throw new IOException("ImageIOがサポートしない画像形式です");
/*    */       }
/* 55 */       ImageReader reader = readers.next();
/* 56 */       imageIn.seek(0L);
/* 57 */       return (b)new b(a.a(reader, imageIn));
/*    */     } finally {
/* 59 */       imageIn.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/image/RasterImageLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */