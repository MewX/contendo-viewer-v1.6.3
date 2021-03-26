/*    */ package jp.cssj.sakae.sac.i18n;
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
/* 66 */   public static final LocaleGroup DEFAULT = new LocaleGroup();
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
/* 78 */     this.locale = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Locale getLocale() {
/* 85 */     return this.locale;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/i18n/LocaleGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */