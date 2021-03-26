/*     */ package jp.cssj.sakae.sac.i18n;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 114 */   protected LocaleGroup localeGroup = LocaleGroup.DEFAULT;
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
/* 145 */     this(s, null);
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
/*     */   public LocalizableSupport(String s, ClassLoader cl) {
/* 160 */     this.bundleName = s;
/* 161 */     this.classLoader = cl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/* 168 */     if (this.locale != l) {
/* 169 */       this.locale = l;
/* 170 */       this.resourceBundle = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 178 */     return this.locale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocaleGroup(LocaleGroup lg) {
/* 187 */     this.localeGroup = lg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocaleGroup getLocaleGroup() {
/* 195 */     return this.localeGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultLocale(Locale l) {
/* 205 */     this.localeGroup.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getDefaultLocale() {
/* 213 */     return this.localeGroup.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) {
/* 221 */     getResourceBundle();
/* 222 */     return MessageFormat.format(this.resourceBundle.getString(key), args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceBundle getResourceBundle() {
/* 232 */     if (this.resourceBundle == null) {
/* 233 */       if (this.locale == null) {
/* 234 */         Locale l; if ((l = this.localeGroup.getLocale()) == null) {
/* 235 */           this.usedLocale = Locale.getDefault();
/*     */         } else {
/* 237 */           this.usedLocale = l;
/*     */         } 
/*     */       } else {
/* 240 */         this.usedLocale = this.locale;
/*     */       } 
/* 242 */       if (this.classLoader == null) {
/* 243 */         this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */       } else {
/* 245 */         this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */       } 
/* 247 */     } else if (this.locale == null) {
/*     */       Locale l;
/* 249 */       if ((l = this.localeGroup.getLocale()) == null) {
/* 250 */         if (this.usedLocale != (l = Locale.getDefault())) {
/* 251 */           this.usedLocale = l;
/* 252 */           if (this.classLoader == null) {
/* 253 */             this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */           } else {
/* 255 */             this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */           } 
/*     */         } 
/* 258 */       } else if (this.usedLocale != l) {
/* 259 */         this.usedLocale = l;
/* 260 */         if (this.classLoader == null) {
/* 261 */           this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale);
/*     */         } else {
/* 263 */           this.resourceBundle = ResourceBundle.getBundle(this.bundleName, this.usedLocale, this.classLoader);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 268 */     return this.resourceBundle;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/i18n/LocalizableSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */