/*    */ package org.apache.batik.ext.awt.image.spi;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.util.Service;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ImageWriterRegistry
/*    */ {
/*    */   private static ImageWriterRegistry instance;
/* 35 */   private final Map imageWriterMap = new HashMap<Object, Object>();
/*    */   
/*    */   private ImageWriterRegistry() {
/* 38 */     setup();
/*    */   }
/*    */   
/*    */   public static ImageWriterRegistry getInstance() {
/* 42 */     synchronized (ImageWriterRegistry.class) {
/* 43 */       if (instance == null) {
/* 44 */         instance = new ImageWriterRegistry();
/*    */       }
/* 46 */       return instance;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setup() {
/* 51 */     Iterator<ImageWriter> iter = Service.providers(ImageWriter.class);
/* 52 */     while (iter.hasNext()) {
/* 53 */       ImageWriter writer = iter.next();
/*    */       
/* 55 */       register(writer);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void register(ImageWriter writer) {
/* 60 */     this.imageWriterMap.put(writer.getMIMEType(), writer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageWriter getWriterFor(String mime) {
/* 69 */     return (ImageWriter)this.imageWriterMap.get(mime);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/ImageWriterRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */