/*     */ package org.apache.batik.i18n;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class LocalizableSupport
/*     */   implements Localizable
/*     */ {
/*  85 */   protected LocaleGroup localeGroup = LocaleGroup.DEFAULT;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String bundleName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader classLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Locale locale;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Locale usedLocale;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   List resourceBundles = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   Class lastResourceClass;
/*     */ 
/*     */ 
/*     */   
/*     */   Class cls;
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalizableSupport(String s, Class cls) {
/* 123 */     this(s, cls, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalizableSupport(String s, Class cls, ClassLoader cl) {
/* 130 */     this.bundleName = s;
/* 131 */     this.cls = cls;
/* 132 */     this.classLoader = cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalizableSupport(String s) {
/* 139 */     this(s, (ClassLoader)null);
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
/*     */   public LocalizableSupport(String s, ClassLoader cl) {
/* 153 */     this.bundleName = s;
/* 154 */     this.classLoader = cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/* 161 */     if (this.locale != l) {
/* 162 */       this.locale = l;
/* 163 */       this.resourceBundles.clear();
/* 164 */       this.lastResourceClass = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 172 */     return this.locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocaleGroup(LocaleGroup lg) {
/* 180 */     this.localeGroup = lg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocaleGroup getLocaleGroup() {
/* 188 */     return this.localeGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultLocale(Locale l) {
/* 198 */     this.localeGroup.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getDefaultLocale() {
/* 206 */     return this.localeGroup.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) {
/* 214 */     return MessageFormat.format(getString(key), args);
/*     */   }
/*     */   
/*     */   protected Locale getCurrentLocale() {
/* 218 */     if (this.locale != null) return this.locale; 
/* 219 */     Locale l = this.localeGroup.getLocale();
/* 220 */     if (l != null) return l; 
/* 221 */     return Locale.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean setUsedLocale() {
/* 229 */     Locale l = getCurrentLocale();
/* 230 */     if (this.usedLocale == l) return false; 
/* 231 */     this.usedLocale = l;
/* 232 */     this.resourceBundles.clear();
/* 233 */     this.lastResourceClass = null;
/* 234 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceBundle getResourceBundle() {
/* 241 */     return getResourceBundle(0);
/*     */   }
/*     */   
/*     */   protected boolean hasNextResourceBundle(int i) {
/* 245 */     if (i == 0) return true; 
/* 246 */     if (i < this.resourceBundles.size()) return true;
/*     */     
/* 248 */     if (this.lastResourceClass == null) return false; 
/* 249 */     if (this.lastResourceClass == Object.class) return false; 
/* 250 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceBundle lookupResourceBundle(String bundle, Class theClass) {
/* 255 */     ClassLoader cl = this.classLoader;
/* 256 */     ResourceBundle rb = null;
/* 257 */     if (cl != null) {
/*     */       try {
/* 259 */         rb = ResourceBundle.getBundle(bundle, this.usedLocale, cl);
/* 260 */       } catch (MissingResourceException missingResourceException) {}
/*     */       
/* 262 */       if (rb != null) {
/* 263 */         return rb;
/*     */       }
/*     */     } 
/* 266 */     if (theClass != null) {
/*     */       try {
/* 268 */         cl = theClass.getClassLoader();
/* 269 */       } catch (SecurityException securityException) {}
/*     */     }
/*     */     
/* 272 */     if (cl == null)
/* 273 */       cl = getClass().getClassLoader(); 
/*     */     try {
/* 275 */       rb = ResourceBundle.getBundle(bundle, this.usedLocale, cl);
/* 276 */     } catch (MissingResourceException missingResourceException) {}
/*     */     
/* 278 */     return rb;
/*     */   }
/*     */   
/*     */   protected ResourceBundle getResourceBundle(int i) {
/* 282 */     setUsedLocale();
/* 283 */     ResourceBundle rb = null;
/* 284 */     if (this.cls == null) {
/*     */       
/* 286 */       if (this.resourceBundles.size() == 0) {
/* 287 */         rb = lookupResourceBundle(this.bundleName, null);
/* 288 */         this.resourceBundles.add(rb);
/*     */       } 
/* 290 */       return this.resourceBundles.get(0);
/*     */     } 
/*     */     
/* 293 */     while (i >= this.resourceBundles.size()) {
/* 294 */       if (this.lastResourceClass == Object.class)
/* 295 */         return null; 
/* 296 */       if (this.lastResourceClass == null) {
/* 297 */         this.lastResourceClass = this.cls;
/*     */       } else {
/* 299 */         this.lastResourceClass = this.lastResourceClass.getSuperclass();
/* 300 */       }  Class cl = this.lastResourceClass;
/* 301 */       String bundle = cl.getPackage().getName() + "." + this.bundleName;
/* 302 */       this.resourceBundles.add(lookupResourceBundle(bundle, cl));
/*     */     } 
/* 304 */     return this.resourceBundles.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key) throws MissingResourceException {
/* 310 */     setUsedLocale();
/* 311 */     for (int i = 0; hasNextResourceBundle(i); i++) {
/* 312 */       ResourceBundle rb = getResourceBundle(i);
/* 313 */       if (rb != null) {
/*     */         try {
/* 315 */           String ret = rb.getString(key);
/* 316 */           if (ret != null) return ret; 
/* 317 */         } catch (MissingResourceException missingResourceException) {}
/*     */       }
/*     */     } 
/* 320 */     String classStr = (this.cls != null) ? this.cls.toString() : this.bundleName;
/* 321 */     throw new MissingResourceException("Unable to find resource: " + key, classStr, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInteger(String key) throws MissingResourceException {
/* 332 */     String i = getString(key);
/*     */     
/*     */     try {
/* 335 */       return Integer.parseInt(i);
/* 336 */     } catch (NumberFormatException e) {
/* 337 */       throw new MissingResourceException("Malformed integer", this.bundleName, key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacter(String key) throws MissingResourceException {
/* 344 */     String s = getString(key);
/*     */     
/* 346 */     if (s == null || s.length() == 0) {
/* 347 */       throw new MissingResourceException("Malformed character", this.bundleName, key);
/*     */     }
/*     */ 
/*     */     
/* 351 */     return s.charAt(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/i18n/LocalizableSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */