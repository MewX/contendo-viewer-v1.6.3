/*    */ package org.apache.xmlgraphics.image.codec.util;
/*    */ 
/*    */ import java.util.MissingResourceException;
/*    */ import org.apache.xmlgraphics.util.i18n.LocalizableSupport;
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
/*    */ public final class PropertyUtil
/*    */ {
/*    */   private static final String RESOURCES = "org.apache.xmlgraphics.image.codec.Messages";
/* 35 */   private static final LocalizableSupport LOCALIZABLESUPPORT = new LocalizableSupport("org.apache.xmlgraphics.image.codec.Messages", PropertyUtil.class.getClassLoader());
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getString(String key) {
/*    */     try {
/* 41 */       return LOCALIZABLESUPPORT.formatMessage(key, null);
/* 42 */     } catch (MissingResourceException e) {
/* 43 */       return key;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/PropertyUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */