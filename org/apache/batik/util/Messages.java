/*    */ package org.apache.batik.util;
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
/*    */ public class Messages
/*    */ {
/*    */   protected static final String RESOURCES = "org.apache.batik.util.resources.Messages";
/* 49 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.util.resources.Messages", Messages.class.getClassLoader());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   protected static ResourceManager resourceManager = new ResourceManager(localizableSupport.getResourceBundle());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setLocale(Locale l) {
/* 62 */     localizableSupport.setLocale(l);
/* 63 */     resourceManager = new ResourceManager(localizableSupport.getResourceBundle());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Locale getLocale() {
/* 70 */     return localizableSupport.getLocale();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 79 */     return localizableSupport.formatMessage(key, args);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getString(String key) throws MissingResourceException {
/* 84 */     return resourceManager.getString(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getInteger(String key) throws MissingResourceException {
/* 89 */     return resourceManager.getInteger(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getCharacter(String key) throws MissingResourceException {
/* 94 */     return resourceManager.getCharacter(key);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */