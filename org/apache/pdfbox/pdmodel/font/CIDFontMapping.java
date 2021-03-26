/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import org.apache.fontbox.FontBoxFont;
/*    */ import org.apache.fontbox.ttf.OpenTypeFont;
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
/*    */ public final class CIDFontMapping
/*    */   extends FontMapping<OpenTypeFont>
/*    */ {
/*    */   private final FontBoxFont ttf;
/*    */   
/*    */   public CIDFontMapping(OpenTypeFont font, FontBoxFont fontBoxFont, boolean isFallback) {
/* 35 */     super(font, isFallback);
/* 36 */     this.ttf = fontBoxFont;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FontBoxFont getTrueTypeFont() {
/* 44 */     return this.ttf;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCIDFont() {
/* 52 */     return (getFont() != null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/CIDFontMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */