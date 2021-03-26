/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import javax.xml.transform.TransformerException;
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
/*     */ public class SystemIDResolver
/*     */ {
/*     */   public static String getAbsoluteURIFromRelative(String localPath) {
/*     */     String str1;
/*  55 */     if (localPath == null || localPath.length() == 0) {
/*  56 */       return "";
/*     */     }
/*     */ 
/*     */     
/*  60 */     String absolutePath = localPath;
/*  61 */     if (!isAbsolutePath(localPath)) {
/*     */ 
/*     */       
/*     */       try { 
/*  65 */         absolutePath = getAbsolutePathFromRelativePath(localPath); } catch (SecurityException se)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/*  70 */         return "file:" + localPath; }
/*     */     
/*     */     }
/*     */ 
/*     */     
/*  75 */     if (null != absolutePath) {
/*     */       
/*  77 */       if (absolutePath.startsWith(File.separator)) {
/*  78 */         str1 = "file://" + absolutePath;
/*     */       } else {
/*  80 */         str1 = "file:///" + absolutePath;
/*     */       } 
/*     */     } else {
/*  83 */       str1 = "file:" + localPath;
/*     */     } 
/*  85 */     return replaceChars(str1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getAbsolutePathFromRelativePath(String relativePath) {
/*  96 */     return (new File(relativePath)).getAbsolutePath();
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
/*     */ 
/*     */   
/*     */   public static boolean isAbsoluteURI(String systemId) {
/* 118 */     if (isWindowsAbsolutePath(systemId)) {
/* 119 */       return false;
/*     */     }
/*     */     
/* 122 */     int fragmentIndex = systemId.indexOf('#');
/* 123 */     int queryIndex = systemId.indexOf('?');
/* 124 */     int slashIndex = systemId.indexOf('/');
/* 125 */     int colonIndex = systemId.indexOf(':');
/*     */ 
/*     */     
/* 128 */     int index = systemId.length() - 1;
/* 129 */     if (fragmentIndex > 0)
/* 130 */       index = fragmentIndex; 
/* 131 */     if (queryIndex > 0 && queryIndex < index)
/* 132 */       index = queryIndex; 
/* 133 */     if (slashIndex > 0 && slashIndex < index) {
/* 134 */       index = slashIndex;
/*     */     }
/* 136 */     return (colonIndex > 0 && colonIndex < index);
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
/*     */   public static boolean isAbsolutePath(String systemId) {
/* 148 */     if (systemId == null)
/* 149 */       return false; 
/* 150 */     File file = new File(systemId);
/* 151 */     return file.isAbsolute();
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
/*     */   private static boolean isWindowsAbsolutePath(String systemId) {
/* 163 */     if (!isAbsolutePath(systemId)) {
/* 164 */       return false;
/*     */     }
/* 166 */     if (systemId.length() > 2 && systemId.charAt(1) == ':' && Character.isLetter(systemId.charAt(0)) && (systemId.charAt(2) == '\\' || systemId.charAt(2) == '/'))
/*     */     {
/*     */ 
/*     */       
/* 170 */       return true;
/*     */     }
/* 172 */     return false;
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
/*     */   private static String replaceChars(String str) {
/* 184 */     StringBuffer buf = new StringBuffer(str);
/* 185 */     int length = buf.length();
/* 186 */     for (int i = 0; i < length; i++) {
/*     */       
/* 188 */       char currentChar = buf.charAt(i);
/*     */       
/* 190 */       if (currentChar == ' ') {
/*     */         
/* 192 */         buf.setCharAt(i, '%');
/* 193 */         buf.insert(i + 1, "20");
/* 194 */         length += 2;
/* 195 */         i += 2;
/*     */       
/*     */       }
/* 198 */       else if (currentChar == '\\') {
/*     */         
/* 200 */         buf.setCharAt(i, '/');
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     return buf.toString();
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
/*     */   public static String getAbsoluteURI(String systemId) {
/* 216 */     String absoluteURI = systemId;
/* 217 */     if (isAbsoluteURI(systemId)) {
/*     */ 
/*     */       
/* 220 */       if (systemId.startsWith("file:")) {
/*     */         
/* 222 */         String str = systemId.substring(5);
/*     */ 
/*     */ 
/*     */         
/* 226 */         if (str != null && str.startsWith("/")) {
/*     */           
/* 228 */           if (str.startsWith("///") || !str.startsWith("//")) {
/*     */ 
/*     */ 
/*     */             
/* 232 */             int secondColonIndex = systemId.indexOf(':', 5);
/* 233 */             if (secondColonIndex > 0) {
/*     */               
/* 235 */               String localPath = systemId.substring(secondColonIndex - 1);
/*     */               
/* 237 */               try { if (!isAbsolutePath(localPath))
/* 238 */                   absoluteURI = systemId.substring(0, secondColonIndex - 1) + getAbsolutePathFromRelativePath(localPath);  } catch (SecurityException se)
/*     */               
/*     */               { 
/*     */                 
/* 242 */                 return systemId; }
/*     */ 
/*     */             
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 249 */           return getAbsoluteURIFromRelative(systemId.substring(5));
/*     */         } 
/*     */         
/* 252 */         return replaceChars(absoluteURI);
/*     */       } 
/*     */       
/* 255 */       return systemId;
/*     */     } 
/*     */     
/* 258 */     return getAbsoluteURIFromRelative(systemId);
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
/*     */   public static String getAbsoluteURI(String urlString, String base) throws TransformerException {
/* 275 */     if (base == null) {
/* 276 */       return getAbsoluteURI(urlString);
/*     */     }
/* 278 */     String absoluteBase = getAbsoluteURI(base);
/* 279 */     URI uri = null;
/*     */ 
/*     */     
/* 282 */     try { URI baseURI = new URI(absoluteBase);
/* 283 */       uri = new URI(baseURI, urlString); } catch (MalformedURIException mue)
/*     */     
/*     */     { 
/*     */       
/* 287 */       throw new TransformerException(mue); }
/*     */ 
/*     */     
/* 290 */     return replaceChars(uri.toString());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/SystemIDResolver.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */