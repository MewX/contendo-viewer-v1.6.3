/*    */ package org.apache.pdfbox.rendering;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import org.apache.pdfbox.pdmodel.PDPage;
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
/*    */ public final class PageDrawerParameters
/*    */ {
/*    */   private final PDFRenderer renderer;
/*    */   private final PDPage page;
/*    */   private final boolean subsamplingAllowed;
/*    */   private final RenderDestination destination;
/*    */   private final RenderingHints renderingHints;
/*    */   
/*    */   PageDrawerParameters(PDFRenderer renderer, PDPage page, boolean subsamplingAllowed, RenderDestination destination, RenderingHints renderingHints) {
/* 45 */     this.renderer = renderer;
/* 46 */     this.page = page;
/* 47 */     this.subsamplingAllowed = subsamplingAllowed;
/* 48 */     this.destination = destination;
/* 49 */     this.renderingHints = renderingHints;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDPage getPage() {
/* 57 */     return this.page;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   PDFRenderer getRenderer() {
/* 65 */     return this.renderer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSubsamplingAllowed() {
/* 73 */     return this.subsamplingAllowed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderDestination getDestination() {
/* 81 */     return this.destination;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderingHints getRenderingHints() {
/* 89 */     return this.renderingHints;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/PageDrawerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */