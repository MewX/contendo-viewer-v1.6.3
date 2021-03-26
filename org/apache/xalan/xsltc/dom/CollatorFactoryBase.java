/*    */ package org.apache.xalan.xsltc.dom;
/*    */ 
/*    */ import java.text.Collator;
/*    */ import java.util.Locale;
/*    */ import org.apache.xalan.xsltc.CollatorFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollatorFactoryBase
/*    */   implements CollatorFactory
/*    */ {
/* 32 */   public static final Locale DEFAULT_LOCALE = Locale.getDefault();
/* 33 */   public static final Collator DEFAULT_COLLATOR = Collator.getInstance();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Collator getCollator(String lang, String country) {
/* 39 */     return Collator.getInstance(new Locale(lang, country));
/*    */   }
/*    */   
/*    */   public Collator getCollator(Locale locale) {
/* 43 */     if (locale == DEFAULT_LOCALE) {
/* 44 */       return DEFAULT_COLLATOR;
/*    */     }
/* 46 */     return Collator.getInstance(locale);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/CollatorFactoryBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */