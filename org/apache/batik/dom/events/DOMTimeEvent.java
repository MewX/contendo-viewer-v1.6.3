/*    */ package org.apache.batik.dom.events;
/*    */ 
/*    */ import org.w3c.dom.smil.TimeEvent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DOMTimeEvent
/*    */   extends AbstractEvent
/*    */   implements TimeEvent
/*    */ {
/*    */   protected AbstractView view;
/*    */   protected int detail;
/*    */   
/*    */   public AbstractView getView() {
/* 47 */     return this.view;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDetail() {
/* 54 */     return this.detail;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initTimeEvent(String typeArg, AbstractView viewArg, int detailArg) {
/* 63 */     initEvent(typeArg, false, false);
/* 64 */     this.view = viewArg;
/* 65 */     this.detail = detailArg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initTimeEventNS(String namespaceURIArg, String typeArg, AbstractView viewArg, int detailArg) {
/* 75 */     initEventNS(namespaceURIArg, typeArg, false, false);
/* 76 */     this.view = viewArg;
/* 77 */     this.detail = detailArg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTimestamp(long timeStamp) {
/* 85 */     this.timeStamp = timeStamp;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMTimeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */