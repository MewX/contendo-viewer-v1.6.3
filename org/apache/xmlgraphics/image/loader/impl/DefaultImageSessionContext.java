/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.stream.StreamSource;
/*    */ import org.apache.xmlgraphics.image.loader.ImageContext;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultImageSessionContext
/*    */   extends AbstractImageSessionContext
/*    */ {
/*    */   private ImageContext context;
/*    */   private File baseDir;
/*    */   
/*    */   public DefaultImageSessionContext(ImageContext context, File baseDir) {
/* 48 */     this.context = context;
/* 49 */     this.baseDir = baseDir;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageContext getParentContext() {
/* 54 */     return this.context;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public File getBaseDir() {
/* 62 */     return this.baseDir;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Source resolveURI(String uri) {
/*    */     try {
/* 68 */       URL url = new URL(uri);
/* 69 */       return new StreamSource(url.openStream(), url.toExternalForm());
/* 70 */     } catch (MalformedURLException e) {
/* 71 */       File f = new File(this.baseDir, uri);
/* 72 */       if (f.isFile()) {
/* 73 */         return new StreamSource(f);
/*    */       }
/* 75 */       return null;
/*    */     }
/* 77 */     catch (IOException ioe) {
/* 78 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getTargetResolution() {
/* 84 */     return getParentContext().getSourceResolution();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/DefaultImageSessionContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */