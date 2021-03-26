/*    */ package org.apache.batik.apps.rasterizer;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */   protected static final String RESOURCES = "org.apache.batik.apps.rasterizer.resources.Messages";
/* 48 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.apps.rasterizer.resources.Messages", Messages.class.getClassLoader());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setLocale(Locale l) {
/* 55 */     localizableSupport.setLocale(l);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Locale getLocale() {
/* 62 */     return localizableSupport.getLocale();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 71 */     return localizableSupport.formatMessage(key, args);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String get(String key) throws MissingResourceException {
/* 76 */     return formatMessage(key, null);
/*    */   }
/*    */   
/*    */   public static String get(String key, String def) {
/* 80 */     String value = def;
/*    */     try {
/* 82 */       value = get(key);
/* 83 */     } catch (MissingResourceException missingResourceException) {}
/*    */ 
/*    */     
/* 86 */     return value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */