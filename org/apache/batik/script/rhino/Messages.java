/*    */ package org.apache.batik.script.rhino;
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
/*    */   protected static final String RESOURCES = "org.apache.batik.script.rhino.resources.messages";
/* 48 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.script.rhino.resources.messages", Messages.class.getClassLoader());
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
/* 76 */     return localizableSupport.getString(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getInteger(String key) throws MissingResourceException {
/* 81 */     return localizableSupport.getInteger(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getCharacter(String key) throws MissingResourceException {
/* 86 */     return localizableSupport.getCharacter(key);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/rhino/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */