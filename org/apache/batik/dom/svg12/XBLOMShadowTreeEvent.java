/*    */ package org.apache.batik.dom.svg12;
/*    */ 
/*    */ import org.apache.batik.dom.events.AbstractEvent;
/*    */ import org.apache.batik.dom.xbl.ShadowTreeEvent;
/*    */ import org.apache.batik.dom.xbl.XBLShadowTreeElement;
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
/*    */ public class XBLOMShadowTreeEvent
/*    */   extends AbstractEvent
/*    */   implements ShadowTreeEvent
/*    */ {
/*    */   protected XBLShadowTreeElement xblShadowTree;
/*    */   
/*    */   public XBLShadowTreeElement getXblShadowTree() {
/* 43 */     return this.xblShadowTree;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initShadowTreeEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, XBLShadowTreeElement xblShadowTreeArg) {
/* 53 */     initEvent(typeArg, canBubbleArg, cancelableArg);
/* 54 */     this.xblShadowTree = xblShadowTreeArg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initShadowTreeEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, XBLShadowTreeElement xblShadowTreeArg) {
/* 65 */     initEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg);
/* 66 */     this.xblShadowTree = xblShadowTreeArg;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg12/XBLOMShadowTreeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */