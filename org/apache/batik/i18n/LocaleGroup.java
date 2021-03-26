/*    */ package org.apache.batik.i18n;
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
/*    */ public class LocaleGroup
/*    */ {
/* 34 */   public static final LocaleGroup DEFAULT = new LocaleGroup();
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
/* 46 */     this.locale = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Locale getLocale() {
/* 53 */     return this.locale;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/i18n/LocaleGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */