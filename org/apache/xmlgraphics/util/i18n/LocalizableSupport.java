/*     */ package org.apache.xmlgraphics.util.i18n;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalizableSupport
/*     */   implements Localizable
/*     */ {
/*  86 */   protected LocaleGroup localeGroup = LocaleGroup.DEFAULT;
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
/*     */   protected ResourceBundle resourceBundle;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalizableSupport(String s) {
/* 117 */     this(s, null);
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
/* 131 */     this.bundleName = s;
/* 132 */     this.classLoader = cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/* 139 */     if (this.locale != l) {
/* 140 */       this.locale = l;
/* 141 */       this.resourceBundle = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 149 */     return this.locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocaleGroup(LocaleGroup lg) {
/* 157 */     this.localeGroup = lg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocaleGroup getLocaleGroup() {
/* 165 */     return this.localeGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultLocale(Locale l) {
/* 175 */     this.localeGroup.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getDefaultLocale() {
/* 183 */     return this.localeGroup.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) {
/* 191 */     getResourceBundle();
/* 192 */     return MessageFormat.format(this.resourceBundle.getString(key), args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceBundle getResourceBundle() {
/* 202 */     if (this.resourceBundle == null) {
/* 203 */       if (this.locale == null) {
/* 204 */         Locale l; if ((l = this.localeGroup.getLocale()) == null) {
/* 205 */           this.usedLocale = Locale.getDefault();
/*     */         } else {
/* 207 */           this.usedLocale = l;
/*     */         } 
/*     */       } else {
/* 210 */         this.usedLocale = this.locale;
/*     */       } 
/* 212 */       if (this.classLoader == null) {
/* 213 */         this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */       } else {
/*     */         
/* 216 */         this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */       }
/*     */     
/*     */     }
/* 220 */     else if (this.locale == null) {
/*     */       Locale l;
/* 222 */       if ((l = this.localeGroup.getLocale()) == null) {
/* 223 */         if (this.usedLocale != (l = Locale.getDefault())) {
/* 224 */           this.usedLocale = l;
/* 225 */           if (this.classLoader == null) {
/* 226 */             this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */           } else {
/*     */             
/* 229 */             this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 234 */       } else if (this.usedLocale != l) {
/* 235 */         this.usedLocale = l;
/* 236 */         if (this.classLoader == null) {
/* 237 */           this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */         } else {
/*     */           
/* 240 */           this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 247 */     return this.resourceBundle;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/i18n/LocalizableSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */