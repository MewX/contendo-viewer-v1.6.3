/*    */ package org.apache.batik.dom.events;
/*    */ 
/*    */ import org.apache.batik.w3c.dom.events.CustomEvent;
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
/*    */ public class DOMCustomEvent
/*    */   extends DOMEvent
/*    */   implements CustomEvent
/*    */ {
/*    */   protected Object detail;
/*    */   
/*    */   public Object getDetail() {
/* 40 */     return this.detail;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initCustomEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, Object detailArg) {
/* 51 */     initEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg);
/* 52 */     this.detail = detailArg;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMCustomEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */