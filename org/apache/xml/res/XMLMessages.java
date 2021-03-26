/*     */ package org.apache.xml.res;
/*     */ 
/*     */ import java.text.MessageFormat;
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
/*     */ public class XMLMessages
/*     */ {
/*  34 */   protected Locale fLocale = Locale.getDefault();
/*     */ 
/*     */   
/*  37 */   private static ListResourceBundle XMLBundle = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String XML_ERROR_RESOURCES = "org.apache.xml.res.XMLErrorResources";
/*     */ 
/*     */   
/*  44 */   protected static String BAD_CODE = "BAD_CODE";
/*     */ 
/*     */   
/*  47 */   protected static String FORMAT_FAILED = "FORMAT_FAILED";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale locale) {
/*  56 */     this.fLocale = locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/*  66 */     return this.fLocale;
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
/*     */   public static final String createXMLMessage(String msgKey, Object[] args) {
/*  81 */     if (XMLBundle == null) {
/*  82 */       XMLBundle = loadResourceBundle("org.apache.xml.res.XMLErrorResources");
/*     */     }
/*  84 */     if (XMLBundle != null)
/*     */     {
/*  86 */       return createMsg(XMLBundle, msgKey, args);
/*     */     }
/*     */     
/*  89 */     return "Could not load any resource bundles.";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String createMsg(ListResourceBundle fResourceBundle, String msgKey, Object[] args) {
/* 109 */     String fmsg = null;
/* 110 */     boolean throwex = false;
/* 111 */     String msg = null;
/*     */     
/* 113 */     if (msgKey != null) {
/* 114 */       msg = fResourceBundle.getString(msgKey);
/*     */     }
/* 116 */     if (msg == null) {
/*     */       
/* 118 */       msg = fResourceBundle.getString(BAD_CODE);
/* 119 */       throwex = true;
/*     */     } 
/*     */     
/* 122 */     if (args != null) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */         
/* 130 */         int n = args.length;
/*     */         
/* 132 */         for (int i = 0; i < n; i++) {
/*     */           
/* 134 */           if (null == args[i]) {
/* 135 */             args[i] = "";
/*     */           }
/*     */         } 
/* 138 */         fmsg = MessageFormat.format(msg, args); } catch (Exception e)
/*     */       
/*     */       { 
/*     */         
/* 142 */         fmsg = fResourceBundle.getString(FORMAT_FAILED);
/* 143 */         fmsg = fmsg + " " + msg; }
/*     */     
/*     */     } else {
/*     */       
/* 147 */       fmsg = msg;
/*     */     } 
/* 149 */     if (throwex)
/*     */     {
/* 151 */       throw new RuntimeException(fmsg);
/*     */     }
/*     */     
/* 154 */     return fmsg;
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
/*     */ 
/*     */   
/*     */   public static ListResourceBundle loadResourceBundle(String className) throws MissingResourceException {
/* 171 */     Locale locale = Locale.getDefault();
/*     */ 
/*     */ 
/*     */     
/* 175 */     try { return (ListResourceBundle)ResourceBundle.getBundle(className, locale); } catch (MissingResourceException e)
/*     */     
/*     */     { 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 184 */         return (ListResourceBundle)ResourceBundle.getBundle(className, new Locale("en", "US")); } catch (MissingResourceException e2)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 192 */         throw new MissingResourceException("Could not load any resource bundles." + className, className, ""); }
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
/*     */   protected static String getResourceSuffix(Locale locale) {
/* 209 */     String suffix = "_" + locale.getLanguage();
/* 210 */     String country = locale.getCountry();
/*     */     
/* 212 */     if (country.equals("TW")) {
/* 213 */       suffix = suffix + "_" + country;
/*     */     }
/* 215 */     return suffix;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/res/XMLMessages.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */