/*    */ package org.apache.pdfbox.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import org.apache.pdfbox.io.IOUtils;
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
/*    */ 
/*    */ public final class Version
/*    */ {
/*    */   private static final String PDFBOX_VERSION_PROPERTIES = "/org/apache/pdfbox/resources/version.properties";
/*    */   
/*    */   public static String getVersion() {
/* 44 */     InputStream is = null;
/*    */     
/*    */     try {
/* 47 */       is = Version.class.getResourceAsStream("/org/apache/pdfbox/resources/version.properties");
/* 48 */       if (is == null)
/*    */       {
/* 50 */         return null;
/*    */       }
/* 52 */       Properties properties = new Properties();
/* 53 */       properties.load(is);
/* 54 */       return properties.getProperty("pdfbox.version", null);
/*    */     }
/* 56 */     catch (IOException io) {
/*    */       
/* 58 */       return null;
/*    */     }
/*    */     finally {
/*    */       
/* 62 */       IOUtils.closeQuietly(is);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */