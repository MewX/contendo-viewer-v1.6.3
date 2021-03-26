/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImageOp;
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
/*    */ public class SVGCustomBufferedImageOp
/*    */   extends AbstractSVGFilterConverter
/*    */ {
/*    */   private static final String ERROR_EXTENSION = "SVGCustomBufferedImageOp:: ExtensionHandler could not convert filter";
/*    */   
/*    */   public SVGCustomBufferedImageOp(SVGGeneratorContext generatorContext) {
/* 43 */     super(generatorContext);
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
/*    */   public SVGFilterDescriptor toSVG(BufferedImageOp filter, Rectangle filterRect) {
/* 56 */     SVGFilterDescriptor filterDesc = (SVGFilterDescriptor)this.descMap.get(filter);
/*    */ 
/*    */     
/* 59 */     if (filterDesc == null) {
/*    */ 
/*    */       
/* 62 */       filterDesc = this.generatorContext.extensionHandler.handleFilter(filter, filterRect, this.generatorContext);
/*    */ 
/*    */ 
/*    */       
/* 66 */       if (filterDesc != null) {
/* 67 */         Element def = filterDesc.getDef();
/* 68 */         if (def != null)
/* 69 */           this.defSet.add(def); 
/* 70 */         this.descMap.put(filter, filterDesc);
/*    */       } else {
/* 72 */         System.err.println("SVGCustomBufferedImageOp:: ExtensionHandler could not convert filter");
/*    */       } 
/*    */     } 
/*    */     
/* 76 */     return filterDesc;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGCustomBufferedImageOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */