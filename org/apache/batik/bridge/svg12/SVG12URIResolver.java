/*    */ package org.apache.batik.bridge.svg12;
/*    */ 
/*    */ import org.apache.batik.bridge.DocumentLoader;
/*    */ import org.apache.batik.bridge.URIResolver;
/*    */ import org.apache.batik.dom.AbstractNode;
/*    */ import org.apache.batik.dom.xbl.NodeXBL;
/*    */ import org.apache.batik.dom.xbl.XBLShadowTreeElement;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
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
/*    */ public class SVG12URIResolver
/*    */   extends URIResolver
/*    */ {
/*    */   public SVG12URIResolver(SVGDocument doc, DocumentLoader dl) {
/* 45 */     super(doc, dl);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getRefererBaseURI(Element ref) {
/* 52 */     AbstractNode aref = (AbstractNode)ref;
/* 53 */     if (aref.getXblBoundElement() != null) {
/* 54 */       return null;
/*    */     }
/* 56 */     return aref.getBaseURI();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node getNodeByFragment(String frag, Element ref) {
/* 67 */     NodeXBL refx = (NodeXBL)ref;
/* 68 */     NodeXBL boundElt = (NodeXBL)refx.getXblBoundElement();
/* 69 */     if (boundElt != null) {
/* 70 */       XBLShadowTreeElement shadow = (XBLShadowTreeElement)boundElt.getXblShadowTree();
/*    */       
/* 72 */       Node n = shadow.getElementById(frag);
/* 73 */       if (n != null) {
/* 74 */         return n;
/*    */       }
/* 76 */       NodeList nl = refx.getXblDefinitions();
/* 77 */       for (int i = 0; i < nl.getLength(); i++) {
/* 78 */         n = nl.item(i).getOwnerDocument().getElementById(frag);
/* 79 */         if (n != null) {
/* 80 */           return n;
/*    */         }
/*    */       } 
/*    */     } 
/* 84 */     return super.getNodeByFragment(frag, ref);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12URIResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */