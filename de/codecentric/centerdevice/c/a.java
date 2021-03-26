/*    */ package de.codecentric.centerdevice.c;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.Locale;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public static final String a = ".properties";
/*    */   public static final String b = "menu_labels_";
/*    */   private final Properties c;
/*    */   private final Locale d;
/*    */   
/*    */   public a(Locale locale) {
/* 17 */     this.d = locale;
/* 18 */     this.c = new Properties();
/*    */     
/* 20 */     a(locale);
/*    */   }
/*    */   
/*    */   private void a(Locale locale) {
/* 24 */     InputStream resource = b(locale);
/* 25 */     if (resource != null) {
/*    */       try {
/* 27 */         a(resource);
/* 28 */       } catch (IOException e) {
/* 29 */         System.err.println("Unable to load properties: " + e.getMessage());
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void a(InputStream resource) throws IOException {
/* 35 */     this.c.load(new InputStreamReader(resource, "UTF-8"));
/*    */   }
/*    */   
/*    */   private InputStream b(Locale locale) {
/* 39 */     InputStream resource = a.class.getClassLoader().getResourceAsStream(d(locale));
/* 40 */     if (resource != null) {
/* 41 */       return resource;
/*    */     }
/*    */     
/* 44 */     resource = a.class.getClassLoader().getResourceAsStream(c(locale));
/* 45 */     if (resource != null) {
/* 46 */       return resource;
/*    */     }
/*    */     
/* 49 */     return a.class.getClassLoader().getResourceAsStream(c(Locale.ENGLISH));
/*    */   }
/*    */   
/*    */   private String c(Locale locale) {
/* 53 */     return a(locale.getLanguage());
/*    */   }
/*    */   
/*    */   private String d(Locale locale) {
/* 57 */     return a(locale.toLanguageTag().replace('-', '_'));
/*    */   }
/*    */   
/*    */   public String a(String label) {
/* 61 */     return "menu_labels_" + label + ".properties";
/*    */   }
/*    */   
/*    */   public String a(b menuItemName, Object... args) {
/* 65 */     String property = this.c.getProperty(menuItemName.b());
/* 66 */     if (property == null) {
/* 67 */       return a(menuItemName);
/*    */     }
/*    */     
/* 70 */     return String.format(this.d, property, args);
/*    */   }
/*    */   
/*    */   private String a(b name) {
/* 74 */     return "§" + name.b() + "§";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */