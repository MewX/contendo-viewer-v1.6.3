/*    */ package org.apache.batik.swing.svg;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.w3c.dom.svg.SVGDocument;
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
/*    */ public class SVGDocumentLoaderEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected SVGDocument svgDocument;
/*    */   
/*    */   public SVGDocumentLoaderEvent(Object source, SVGDocument doc) {
/* 46 */     super(source);
/* 47 */     this.svgDocument = doc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGDocument getSVGDocument() {
/* 55 */     return this.svgDocument;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGDocumentLoaderEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */