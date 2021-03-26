/*    */ package org.apache.pdfbox.rendering;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.pdmodel.font.PDCIDFontType0;
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
/*    */ final class CIDType0Glyph2D
/*    */   implements Glyph2D
/*    */ {
/* 34 */   private static final Log LOG = LogFactory.getLog(CIDType0Glyph2D.class);
/*    */   
/* 36 */   private final Map<Integer, GeneralPath> cache = new HashMap<Integer, GeneralPath>();
/*    */ 
/*    */   
/*    */   private final PDCIDFontType0 font;
/*    */ 
/*    */   
/*    */   private final String fontName;
/*    */ 
/*    */ 
/*    */   
/*    */   CIDType0Glyph2D(PDCIDFontType0 font) {
/* 47 */     this.font = font;
/* 48 */     this.fontName = font.getBaseFont();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GeneralPath getPathForCharacterCode(int code) {
/* 54 */     GeneralPath path = this.cache.get(Integer.valueOf(code));
/* 55 */     if (path == null) {
/*    */       
/*    */       try {
/*    */         
/* 59 */         if (!this.font.hasGlyph(code)) {
/*    */           
/* 61 */           int cid = this.font.getParent().codeToCID(code);
/* 62 */           String cidHex = String.format("%04x", new Object[] { Integer.valueOf(cid) });
/* 63 */           LOG.warn("No glyph for " + code + " (CID " + cidHex + ") in font " + this.fontName);
/*    */         } 
/*    */         
/* 66 */         path = this.font.getPath(code);
/* 67 */         this.cache.put(Integer.valueOf(code), path);
/* 68 */         return path;
/*    */       }
/* 70 */       catch (IOException e) {
/*    */ 
/*    */         
/* 73 */         LOG.error("Glyph rendering failed", e);
/* 74 */         path = new GeneralPath();
/*    */       } 
/*    */     }
/* 77 */     return path;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {
/* 83 */     this.cache.clear();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/CIDType0Glyph2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */