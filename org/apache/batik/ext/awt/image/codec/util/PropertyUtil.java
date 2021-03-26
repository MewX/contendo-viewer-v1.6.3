/*    */ package org.apache.batik.ext.awt.image.codec.util;
/*    */ 
/*    */ import java.util.MissingResourceException;
/*    */ import org.apache.batik.i18n.LocalizableSupport;
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
/*    */ public class PropertyUtil
/*    */ {
/*    */   protected static final String RESOURCES = "org.apache.batik.bridge.resources.properties";
/* 34 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.bridge.resources.properties", PropertyUtil.class.getClassLoader());
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getString(String key) {
/*    */     try {
/* 40 */       return localizableSupport.formatMessage(key, null);
/* 41 */     } catch (MissingResourceException e) {
/* 42 */       return key;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/PropertyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */