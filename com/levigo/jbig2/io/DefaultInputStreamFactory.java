/*    */ package com.levigo.jbig2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.stream.FileCacheImageInputStream;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.imageio.stream.MemoryCacheImageInputStream;
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
/*    */ public class DefaultInputStreamFactory
/*    */   implements InputStreamFactory
/*    */ {
/*    */   public ImageInputStream getInputStream(InputStream paramInputStream) {
/*    */     try {
/* 31 */       return new FileCacheImageInputStream(paramInputStream, null);
/* 32 */     } catch (IOException iOException) {
/* 33 */       return new MemoryCacheImageInputStream(paramInputStream);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/io/DefaultInputStreamFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */