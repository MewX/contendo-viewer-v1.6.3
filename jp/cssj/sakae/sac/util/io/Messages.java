/*     */ package jp.cssj.sakae.sac.util.io;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import jp.cssj.sakae.sac.i18n.LocalizableSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Messages
/*     */ {
/*  75 */   protected static final String RESOURCES = Messages.class.getPackage().getName() + ".Messages";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport(RESOURCES, Messages.class
/*  81 */       .getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setLocale(Locale l) {
/*  87 */     localizableSupport.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Locale getLocale() {
/*  94 */     return localizableSupport.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 102 */     return localizableSupport.formatMessage(key, args);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/util/io/Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */