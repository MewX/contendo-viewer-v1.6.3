/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Paint;
/*    */ import org.apache.batik.bridge.AbstractSVGBridge;
/*    */ import org.apache.batik.bridge.Bridge;
/*    */ import org.apache.batik.bridge.BridgeContext;
/*    */ import org.apache.batik.bridge.PaintBridge;
/*    */ import org.apache.batik.bridge.SVGUtilities;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
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
/*    */ public class ColorSwitchBridge
/*    */   extends AbstractSVGBridge
/*    */   implements PaintBridge, BatikExtConstants
/*    */ {
/*    */   public String getNamespaceURI() {
/* 52 */     return "http://xml.apache.org/batik/ext";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 59 */     return "colorSwitch";
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
/*    */ 
/*    */ 
/*    */   
/*    */   public Paint createPaint(BridgeContext ctx, Element paintElement, Element paintedElement, GraphicsNode paintedNode, float opacity) {
/* 76 */     Element clrDef = null;
/* 77 */     Node n = paintElement.getFirstChild();
/* 78 */     for (; n != null; 
/* 79 */       n = n.getNextSibling()) {
/* 80 */       if (n.getNodeType() == 1) {
/*    */         
/* 82 */         Element ref = (Element)n;
/* 83 */         if (SVGUtilities.matchUserAgent(ref, ctx.getUserAgent())) {
/*    */           
/* 85 */           clrDef = ref;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 90 */     if (clrDef == null) {
/* 91 */       return Color.black;
/*    */     }
/* 93 */     Bridge bridge = ctx.getBridge(clrDef);
/* 94 */     if (bridge == null || !(bridge instanceof PaintBridge)) {
/* 95 */       return Color.black;
/*    */     }
/* 97 */     return ((PaintBridge)bridge).createPaint(ctx, clrDef, paintedElement, paintedNode, opacity);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/ColorSwitchBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */