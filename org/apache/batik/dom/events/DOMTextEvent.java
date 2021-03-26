/*    */ package org.apache.batik.dom.events;
/*    */ 
/*    */ import org.apache.batik.w3c.dom.events.TextEvent;
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
/*    */ public class DOMTextEvent
/*    */   extends DOMUIEvent
/*    */   implements TextEvent
/*    */ {
/*    */   protected String data;
/*    */   
/*    */   public String getData() {
/* 41 */     return this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initTextEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, String dataArg) {
/* 52 */     initUIEvent(typeArg, canBubbleArg, cancelableArg, viewArg, 0);
/* 53 */     this.data = dataArg;
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
/*    */   public void initTextEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, String dataArg) {
/* 65 */     initUIEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg, viewArg, 0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 71 */     this.data = dataArg;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMTextEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */