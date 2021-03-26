/*    */ package org.apache.batik.ext.swing;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.MissingResourceException;
/*    */ import org.apache.batik.i18n.LocalizableSupport;
/*    */ import org.apache.batik.util.resources.ResourceManager;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Resources
/*    */ {
/*    */   protected static final String RESOURCES = "org.apache.batik.ext.swing.resources.Messages";
/* 50 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.ext.swing.resources.Messages", Resources.class.getClassLoader());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   protected static ResourceManager resourceManager = new ResourceManager(localizableSupport.getResourceBundle());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setLocale(Locale l) {
/* 63 */     localizableSupport.setLocale(l);
/* 64 */     resourceManager = new ResourceManager(localizableSupport.getResourceBundle());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Locale getLocale() {
/* 71 */     return localizableSupport.getLocale();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 80 */     return localizableSupport.formatMessage(key, args);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getString(String key) throws MissingResourceException {
/* 85 */     return resourceManager.getString(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getInteger(String key) throws MissingResourceException {
/* 90 */     return resourceManager.getInteger(key);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/swing/Resources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */