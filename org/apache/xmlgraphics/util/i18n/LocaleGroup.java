/*    */ package org.apache.xmlgraphics.util.i18n;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocaleGroup
/*    */ {
/* 36 */   public static final LocaleGroup DEFAULT = new LocaleGroup();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Locale locale;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLocale(Locale l) {
/* 48 */     this.locale = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Locale getLocale() {
/* 55 */     return this.locale;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/i18n/LocaleGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */