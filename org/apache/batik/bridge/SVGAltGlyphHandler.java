/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.awt.font.FontRenderContext;
/*    */ import java.text.AttributedCharacterIterator;
/*    */ import org.apache.batik.gvt.font.AltGlyphHandler;
/*    */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*    */ import org.apache.batik.gvt.font.Glyph;
/*    */ import org.apache.batik.gvt.font.SVGGVTGlyphVector;
/*    */ import org.apache.batik.util.SVGConstants;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGAltGlyphHandler
/*    */   implements AltGlyphHandler, SVGConstants
/*    */ {
/*    */   private BridgeContext ctx;
/*    */   private Element textElement;
/*    */   
/*    */   public SVGAltGlyphHandler(BridgeContext ctx, Element textElement) {
/* 52 */     this.ctx = ctx;
/* 53 */     this.textElement = textElement;
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
/*    */ 
/*    */   
/*    */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, float fontSize, AttributedCharacterIterator aci) {
/*    */     try {
/* 68 */       if ("http://www.w3.org/2000/svg".equals(this.textElement.getNamespaceURI()) && "altGlyph".equals(this.textElement.getLocalName())) {
/*    */         
/* 70 */         SVGAltGlyphElementBridge altGlyphBridge = (SVGAltGlyphElementBridge)this.ctx.getBridge(this.textElement);
/*    */         
/* 72 */         Glyph[] glyphArray = altGlyphBridge.createAltGlyphArray(this.ctx, this.textElement, fontSize, aci);
/*    */         
/* 74 */         if (glyphArray != null) {
/* 75 */           return (GVTGlyphVector)new SVGGVTGlyphVector(null, glyphArray, frc);
/*    */         }
/*    */       } 
/* 78 */     } catch (SecurityException e) {
/* 79 */       this.ctx.getUserAgent().displayError(e);
/*    */ 
/*    */ 
/*    */       
/* 83 */       throw e;
/*    */     } 
/*    */     
/* 86 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAltGlyphHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */