/*    */ package jp.cssj.homare.impl.ua.image;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.imageio.ImageWriter;
/*    */ import javax.imageio.spi.ImageWriterSpi;
/*    */ import jp.cssj.homare.ua.UserAgentFactory;
/*    */ import jp.cssj.homare.ua.m;
/*    */ 
/*    */ public class ImageUserAgentFactory
/*    */   implements UserAgentFactory
/*    */ {
/*    */   public boolean match(String key) {
/* 18 */     String[] mimeTypes = ImageIO.getWriterMIMETypes();
/* 19 */     for (int i = 0; i < mimeTypes.length; i++) {
/* 20 */       if (mimeTypes[i].equals(key)) {
/* 21 */         return true;
/*    */       }
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */   
/*    */   public Iterator<UserAgentFactory.a> types() {
/* 28 */     List<UserAgentFactory.a> list = new ArrayList<>();
/* 29 */     String[] mimeTypes = ImageIO.getWriterMIMETypes();
/* 30 */     Collection<ImageWriterSpi> shown = new HashSet<>();
/* 31 */     for (int i = 0; i < mimeTypes.length; i++) {
/* 32 */       String mimeType = mimeTypes[i];
/* 33 */       ImageWriter w = ImageIO.getImageWritersByMIMEType(mimeType).next();
/* 34 */       ImageWriterSpi spi = w.getOriginatingProvider();
/* 35 */       if (!shown.contains(spi)) {
/*    */ 
/*    */         
/* 38 */         shown.add(spi);
/* 39 */         String name = spi.getFormatNames()[0].toUpperCase();
/* 40 */         String suffix = spi.getFileSuffixes()[0];
/* 41 */         list.add(new UserAgentFactory.a(name, mimeType, suffix));
/*    */       } 
/* 43 */     }  return list.iterator();
/*    */   }
/*    */   
/*    */   public m createUserAgent() {
/* 47 */     return (m)new a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/image/ImageUserAgentFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */