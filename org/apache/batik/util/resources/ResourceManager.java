/*     */ package org.apache.batik.util.resources;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class ResourceManager
/*     */ {
/*     */   protected ResourceBundle bundle;
/*     */   
/*     */   public ResourceManager(ResourceBundle rb) {
/*  46 */     this.bundle = rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key) throws MissingResourceException {
/*  56 */     return this.bundle.getString(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStringList(String key) throws MissingResourceException {
/*  67 */     return getStringList(key, " \t\n\r\f", false);
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
/*     */   public List getStringList(String key, String delim) throws MissingResourceException {
/*  79 */     return getStringList(key, delim, false);
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
/*     */   public List getStringList(String key, String delim, boolean returnDelims) throws MissingResourceException {
/*  92 */     List<String> result = new ArrayList();
/*  93 */     StringTokenizer st = new StringTokenizer(getString(key), delim, returnDelims);
/*     */ 
/*     */     
/*  96 */     while (st.hasMoreTokens()) {
/*  97 */       result.add(st.nextToken());
/*     */     }
/*  99 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) throws MissingResourceException, ResourceFormatException {
/* 110 */     String b = getString(key);
/*     */     
/* 112 */     if (b.equals("true"))
/* 113 */       return true; 
/* 114 */     if (b.equals("false")) {
/* 115 */       return false;
/*     */     }
/* 117 */     throw new ResourceFormatException("Malformed boolean", this.bundle.getClass().getName(), key);
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
/*     */   public int getInteger(String key) throws MissingResourceException, ResourceFormatException {
/* 131 */     String i = getString(key);
/*     */     
/*     */     try {
/* 134 */       return Integer.parseInt(i);
/* 135 */     } catch (NumberFormatException e) {
/* 136 */       throw new ResourceFormatException("Malformed integer", this.bundle.getClass().getName(), key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacter(String key) throws MissingResourceException, ResourceFormatException {
/* 144 */     String s = getString(key);
/*     */     
/* 146 */     if (s == null || s.length() == 0) {
/* 147 */       throw new ResourceFormatException("Malformed character", this.bundle.getClass().getName(), key);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 152 */     return s.charAt(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/resources/ResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */