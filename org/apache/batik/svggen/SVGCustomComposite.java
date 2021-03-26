/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Composite;
/*    */ import org.apache.batik.ext.awt.g2d.GraphicContext;
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
/*    */ public class SVGCustomComposite
/*    */   extends AbstractSVGConverter
/*    */ {
/*    */   public SVGCustomComposite(SVGGeneratorContext generatorContext) {
/* 39 */     super(generatorContext);
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
/*    */   public SVGDescriptor toSVG(GraphicContext gc) {
/* 53 */     return toSVG(gc.getComposite());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGCompositeDescriptor toSVG(Composite composite) {
/* 62 */     if (composite == null)
/* 63 */       throw new NullPointerException(); 
/* 64 */     SVGCompositeDescriptor compositeDesc = (SVGCompositeDescriptor)this.descMap.get(composite);
/*    */ 
/*    */     
/* 67 */     if (compositeDesc == null) {
/*    */ 
/*    */       
/* 70 */       SVGCompositeDescriptor desc = this.generatorContext.extensionHandler.handleComposite(composite, this.generatorContext);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 75 */       if (desc != null) {
/* 76 */         Element def = desc.getDef();
/* 77 */         if (def != null)
/* 78 */           this.defSet.add(def); 
/* 79 */         this.descMap.put(composite, desc);
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return compositeDesc;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGCustomComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */