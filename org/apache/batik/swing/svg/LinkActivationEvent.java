/*    */ package org.apache.batik.swing.svg;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.w3c.dom.svg.SVGAElement;
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
/*    */ 
/*    */ public class LinkActivationEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected String referencedURI;
/*    */   
/*    */   public LinkActivationEvent(Object source, SVGAElement link, String uri) {
/* 47 */     super(source);
/* 48 */     this.referencedURI = uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getReferencedURI() {
/* 55 */     return this.referencedURI;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/LinkActivationEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */