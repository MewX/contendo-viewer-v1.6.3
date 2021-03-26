/*    */ package org.apache.batik.gvt.font;
/*    */ 
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import java.util.Map;
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
/*    */ public class UnresolvedFontFamily
/*    */   implements GVTFontFamily
/*    */ {
/*    */   protected GVTFontFace fontFace;
/*    */   
/*    */   public UnresolvedFontFamily(GVTFontFace fontFace) {
/* 41 */     this.fontFace = fontFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnresolvedFontFamily(String familyName) {
/* 50 */     this(new GVTFontFace(familyName));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GVTFontFace getFontFace() {
/* 57 */     return this.fontFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFamilyName() {
/* 66 */     return this.fontFace.getFamilyName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GVTFont deriveFont(float size, AttributedCharacterIterator aci) {
/* 78 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GVTFont deriveFont(float size, Map attrs) {
/* 87 */     return null;
/*    */   }
/*    */   public boolean isComplex() {
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/UnresolvedFontFamily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */