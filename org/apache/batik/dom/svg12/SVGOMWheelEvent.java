/*    */ package org.apache.batik.dom.svg12;
/*    */ 
/*    */ import org.apache.batik.dom.events.DOMUIEvent;
/*    */ import org.w3c.dom.views.AbstractView;
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
/*    */ public class SVGOMWheelEvent
/*    */   extends DOMUIEvent
/*    */ {
/*    */   protected int wheelDelta;
/*    */   
/*    */   public int getWheelDelta() {
/* 42 */     return this.wheelDelta;
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
/*    */ 
/*    */   
/*    */   public void initWheelEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int wheelDeltaArg) {
/* 61 */     initUIEvent(typeArg, canBubbleArg, cancelableArg, viewArg, 0);
/* 62 */     this.wheelDelta = wheelDeltaArg;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initWheelEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int wheelDeltaArg) {
/* 83 */     initUIEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg, viewArg, 0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 89 */     this.wheelDelta = wheelDeltaArg;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg12/SVGOMWheelEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */