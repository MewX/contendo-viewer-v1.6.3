/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.batik.gvt.font.GVTFontFamily;
/*    */ import org.w3c.dom.Element;
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
/*    */ public class SVGFontFace
/*    */   extends FontFace
/*    */ {
/*    */   Element fontFaceElement;
/* 35 */   GVTFontFamily fontFamily = null;
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
/*    */   public SVGFontFace(Element fontFaceElement, List srcs, String familyName, float unitsPerEm, String fontWeight, String fontStyle, String fontVariant, String fontStretch, float slope, String panose1, float ascent, float descent, float strikethroughPosition, float strikethroughThickness, float underlinePosition, float underlineThickness, float overlinePosition, float overlineThickness) {
/* 47 */     super(srcs, familyName, unitsPerEm, fontWeight, fontStyle, fontVariant, fontStretch, slope, panose1, ascent, descent, strikethroughPosition, strikethroughThickness, underlinePosition, underlineThickness, overlinePosition, overlineThickness);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     this.fontFaceElement = fontFaceElement;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GVTFontFamily getFontFamily(BridgeContext ctx) {
/* 61 */     if (this.fontFamily != null) {
/* 62 */       return this.fontFamily;
/*    */     }
/* 64 */     Element fontElt = SVGUtilities.getParentElement(this.fontFaceElement);
/* 65 */     if (fontElt.getNamespaceURI().equals("http://www.w3.org/2000/svg") && fontElt.getLocalName().equals("font"))
/*    */     {
/* 67 */       return new SVGFontFamily(this, fontElt, ctx);
/*    */     }
/*    */     
/* 70 */     this.fontFamily = super.getFontFamily(ctx);
/* 71 */     return this.fontFamily;
/*    */   }
/*    */   
/*    */   public Element getFontFaceElement() {
/* 75 */     return this.fontFaceElement;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Element getBaseElement(BridgeContext ctx) {
/* 83 */     if (this.fontFaceElement != null)
/* 84 */       return this.fontFaceElement; 
/* 85 */     return super.getBaseElement(ctx);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFontFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */