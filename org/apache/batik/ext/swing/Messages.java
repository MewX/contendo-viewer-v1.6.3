/*    */ package org.apache.batik.ext.swing;
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
/*    */   protected static final String RESOURCES = "org.apache.batik.ext.swing.resources.Messages";
/* 48 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.ext.swing.resources.Messages", Messages.class.getClassLoader());
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
/*    */   public static String getString(String key) throws MissingResourceException {
/* 76 */     return formatMessage(key, null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/swing/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */