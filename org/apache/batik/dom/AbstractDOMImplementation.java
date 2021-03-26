/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import org.apache.batik.dom.events.DocumentEventSupport;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.i18n.Localizable;
/*     */ import org.apache.batik.i18n.LocalizableSupport;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDOMImplementation
/*     */   implements Serializable, Localizable, DOMImplementation
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.dom.resources.Messages";
/*  55 */   protected LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.dom.resources.Messages", getClass().getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected final HashMap<String, Object> features = new HashMap<String, Object>();
/*     */   
/*     */   protected AbstractDOMImplementation() {
/*  64 */     registerFeature("Core", new String[] { "2.0", "3.0" });
/*  65 */     registerFeature("XML", new String[] { "1.0", "2.0", "3.0" });
/*     */     
/*  67 */     registerFeature("Events", new String[] { "2.0", "3.0" });
/*  68 */     registerFeature("UIEvents", new String[] { "2.0", "3.0" });
/*  69 */     registerFeature("MouseEvents", new String[] { "2.0", "3.0" });
/*  70 */     registerFeature("TextEvents", "3.0");
/*  71 */     registerFeature("KeyboardEvents", "3.0");
/*  72 */     registerFeature("MutationEvents", new String[] { "2.0", "3.0" });
/*  73 */     registerFeature("MutationNameEvents", "3.0");
/*  74 */     registerFeature("Traversal", "2.0");
/*  75 */     registerFeature("XPath", "3.0");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerFeature(String name, Object value) {
/*  82 */     this.features.put(name.toLowerCase(), value);
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
/*     */   public boolean hasFeature(String feature, String version) {
/*  96 */     if (feature == null || feature.length() == 0) {
/*  97 */       return false;
/*     */     }
/*  99 */     if (feature.charAt(0) == '+')
/*     */     {
/* 101 */       feature = feature.substring(1);
/*     */     }
/* 103 */     Object v = this.features.get(feature.toLowerCase());
/* 104 */     if (v == null) {
/* 105 */       return false;
/*     */     }
/* 107 */     if (version == null || version.length() == 0) {
/* 108 */       return true;
/*     */     }
/* 110 */     if (v instanceof String) {
/* 111 */       return version.equals(v);
/*     */     }
/* 113 */     String[] va = (String[])v;
/* 114 */     for (String aVa : va) {
/* 115 */       if (version.equals(aVa)) {
/* 116 */         return true;
/*     */       }
/*     */     } 
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFeature(String feature, String version) {
/* 130 */     if (hasFeature(feature, version)) {
/* 131 */       return this;
/*     */     }
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentEventSupport createDocumentEventSupport() {
/* 140 */     return new DocumentEventSupport();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventSupport createEventSupport(AbstractNode n) {
/* 147 */     return new EventSupport(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/* 156 */     this.localizableSupport.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 163 */     return this.localizableSupport.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initLocalizable() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/* 174 */     return this.localizableSupport.formatMessage(key, args);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractDOMImplementation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */