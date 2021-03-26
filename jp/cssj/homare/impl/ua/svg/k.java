/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.geom.Dimension2D;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.d.h;
/*    */ import org.apache.batik.bridge.NoLoadScriptSecurity;
/*    */ import org.apache.batik.bridge.ScriptSecurity;
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ 
/*    */ 
/*    */ class k
/*    */   extends h
/*    */ {
/*    */   protected final String a;
/*    */   protected final m b;
/*    */   
/*    */   public k(String docURI, m ua, Dimension2D viewport) {
/* 18 */     super(viewport);
/* 19 */     this.a = docURI;
/* 20 */     this.b = ua;
/* 21 */     addStdFeatures();
/*    */   }
/*    */   
/*    */   public Dimension2D getViewportSize() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public void displayError(String message) {
/* 29 */     this.b.a((short)10253, this.a, message);
/*    */   }
/*    */   
/*    */   public void displayError(Exception e) {
/* 33 */     this.b.a((short)10253, this.a, e.getMessage());
/*    */   }
/*    */   
/*    */   public void displayMessage(String message) {
/* 37 */     this.b.a((short)10253, this.a, message);
/*    */   }
/*    */   
/*    */   public float getPixelUnitToMillimeter() {
/* 41 */     return (float)(25.4D / this.b.n());
/*    */   }
/*    */   
/*    */   public String getLanguages() {
/* 45 */     return this.b.d().getLanguage();
/*    */   }
/*    */   
/*    */   public String getMedia() {
/* 49 */     return "print";
/*    */   }
/*    */   
/*    */   public String getDefaultFontFamily() {
/* 53 */     return this.b.i().a(0).c();
/*    */   }
/*    */   
/*    */   public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptPURL, ParsedURL docPURL) {
/* 57 */     return (ScriptSecurity)new NoLoadScriptSecurity(scriptType);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */