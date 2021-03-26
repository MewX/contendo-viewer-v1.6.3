/*     */ package org.apache.xpath.res;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ListResourceBundle;
/*     */ import org.apache.xml.res.XMLMessages;
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
/*     */ public class XPATHMessages
/*     */   extends XMLMessages
/*     */ {
/*  32 */   private static ListResourceBundle XPATHBundle = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String XPATH_ERROR_RESOURCES = "org.apache.xpath.res.XPATHErrorResources";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String createXPATHMessage(String msgKey, Object[] args) {
/*  50 */     if (XPATHBundle == null) {
/*  51 */       XPATHBundle = XMLMessages.loadResourceBundle("org.apache.xpath.res.XPATHErrorResources");
/*     */     }
/*  53 */     if (XPATHBundle != null)
/*     */     {
/*  55 */       return createXPATHMsg(XPATHBundle, msgKey, args);
/*     */     }
/*     */     
/*  58 */     return "Could not load any resource bundles.";
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
/*     */   public static final String createXPATHWarning(String msgKey, Object[] args) {
/*  73 */     if (XPATHBundle == null) {
/*  74 */       XPATHBundle = XMLMessages.loadResourceBundle("org.apache.xpath.res.XPATHErrorResources");
/*     */     }
/*  76 */     if (XPATHBundle != null)
/*     */     {
/*  78 */       return createXPATHMsg(XPATHBundle, msgKey, args);
/*     */     }
/*     */     
/*  81 */     return "Could not load any resource bundles.";
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
/*     */   public static final String createXPATHMsg(ListResourceBundle fResourceBundle, String msgKey, Object[] args) {
/* 101 */     String fmsg = null;
/* 102 */     boolean throwex = false;
/* 103 */     String msg = null;
/*     */     
/* 105 */     if (msgKey != null) {
/* 106 */       msg = fResourceBundle.getString(msgKey);
/*     */     }
/* 108 */     if (msg == null) {
/*     */       
/* 110 */       msg = fResourceBundle.getString("BAD_CODE");
/* 111 */       throwex = true;
/*     */     } 
/*     */     
/* 114 */     if (args != null) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */         
/* 122 */         int n = args.length;
/*     */         
/* 124 */         for (int i = 0; i < n; i++) {
/*     */           
/* 126 */           if (null == args[i]) {
/* 127 */             args[i] = "";
/*     */           }
/*     */         } 
/* 130 */         fmsg = MessageFormat.format(msg, args); } catch (Exception e)
/*     */       
/*     */       { 
/*     */         
/* 134 */         fmsg = fResourceBundle.getString("FORMAT_FAILED");
/* 135 */         fmsg = fmsg + " " + msg; }
/*     */     
/*     */     } else {
/*     */       
/* 139 */       fmsg = msg;
/*     */     } 
/* 141 */     if (throwex)
/*     */     {
/* 143 */       throw new RuntimeException(fmsg);
/*     */     }
/*     */     
/* 146 */     return fmsg;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/res/XPATHMessages.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */