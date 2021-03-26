/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractNode;
/*    */ import org.apache.batik.util.SVGConstants;
/*    */ import org.w3c.dom.DOMException;
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
/*    */ 
/*    */ public class SVGZoomAndPanSupport
/*    */   implements SVGConstants
/*    */ {
/*    */   public static void setZoomAndPan(Element elt, short val) throws DOMException {
/* 46 */     switch (val) {
/*    */       case 1:
/* 48 */         elt.setAttributeNS(null, "zoomAndPan", "disable");
/*    */         return;
/*    */       
/*    */       case 2:
/* 52 */         elt.setAttributeNS(null, "zoomAndPan", "magnify");
/*    */         return;
/*    */     } 
/*    */     
/* 56 */     throw ((AbstractNode)elt).createDOMException((short)13, "zoom.and.pan", new Object[] { Integer.valueOf(val) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static short getZoomAndPan(Element elt) {
/* 67 */     String s = elt.getAttributeNS(null, "zoomAndPan");
/* 68 */     if (s.equals("magnify")) {
/* 69 */       return 2;
/*    */     }
/* 71 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGZoomAndPanSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */