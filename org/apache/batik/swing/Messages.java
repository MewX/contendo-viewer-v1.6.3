/*    */ package org.apache.batik.swing;
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
/*    */ 
/*    */ public class Messages
/*    */ {
/*    */   protected static final String RESOURCES = "org.apache.batik.swing.resources.Messages";
/* 49 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.swing.resources.Messages", Messages.class.getClassLoader());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setLocale(Locale l) {
/* 56 */     localizableSupport.setLocale(l);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Locale getLocale() {
/* 63 */     return localizableSupport.getLocale();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 72 */     return localizableSupport.formatMessage(key, args);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */