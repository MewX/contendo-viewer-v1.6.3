/*     */ package org.apache.xml.utils.res;
/*     */ 
/*     */ import java.util.ListResourceBundle;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
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
/*     */ public class XResourceBundle
/*     */   extends ListResourceBundle
/*     */ {
/*     */   public static final String ERROR_RESOURCES = "org.apache.xalan.res.XSLTErrorResources";
/*     */   public static final String XSLT_RESOURCE = "org.apache.xml.utils.res.XResourceBundle";
/*     */   public static final String LANG_BUNDLE_NAME = "org.apache.xml.utils.res.XResources";
/*     */   public static final String MULT_ORDER = "multiplierOrder";
/*     */   public static final String MULT_PRECEDES = "precedes";
/*     */   public static final String MULT_FOLLOWS = "follows";
/*     */   public static final String LANG_ORIENTATION = "orientation";
/*     */   public static final String LANG_RIGHTTOLEFT = "rightToLeft";
/*     */   public static final String LANG_LEFTTORIGHT = "leftToRight";
/*     */   public static final String LANG_NUMBERING = "numbering";
/*     */   public static final String LANG_ADDITIVE = "additive";
/*     */   public static final String LANG_MULT_ADD = "multiplicative-additive";
/*     */   public static final String LANG_MULTIPLIER = "multiplier";
/*     */   public static final String LANG_MULTIPLIER_CHAR = "multiplierChar";
/*     */   public static final String LANG_NUMBERGROUPS = "numberGroups";
/*     */   public static final String LANG_NUM_TABLES = "tables";
/*     */   public static final String LANG_ALPHABET = "alphabet";
/*     */   public static final String LANG_TRAD_ALPHABET = "tradAlphabet";
/*     */   
/*     */   public static final XResourceBundle loadResourceBundle(String className, Locale locale) throws MissingResourceException {
/*  58 */     String suffix = getResourceSuffix(locale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     try { String resourceName = className + suffix;
/*  66 */       return (XResourceBundle)ResourceBundle.getBundle(resourceName, locale); } catch (MissingResourceException e)
/*     */     
/*     */     { 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  75 */         return (XResourceBundle)ResourceBundle.getBundle("org.apache.xml.utils.res.XResourceBundle", new Locale("en", "US")); } catch (MissingResourceException e2)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  83 */         throw new MissingResourceException("Could not load any resource bundles.", className, ""); }
/*     */        }
/*     */   
/*     */   }
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
/*     */   private static final String getResourceSuffix(Locale locale) {
/* 100 */     String lang = locale.getLanguage();
/* 101 */     String country = locale.getCountry();
/* 102 */     String variant = locale.getVariant();
/* 103 */     String suffix = "_" + locale.getLanguage();
/*     */     
/* 105 */     if (lang.equals("zh")) {
/* 106 */       suffix = suffix + "_" + country;
/*     */     }
/* 108 */     if (country.equals("JP")) {
/* 109 */       suffix = suffix + "_" + country + "_" + variant;
/*     */     }
/* 111 */     return suffix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[][] getContents() {
/* 121 */     return contents;
/*     */   }
/*     */ 
/*     */   
/* 125 */   static final Object[][] contents = new Object[][] { { "ui_language", "en" }, { "help_language", "en" }, { "language", "en" }, { "alphabet", { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' } }, { "tradAlphabet", { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' } }, { "orientation", "LeftToRight" }, { "numbering", "additive" } };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/res/XResourceBundle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */