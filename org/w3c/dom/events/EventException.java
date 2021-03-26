/*    */ package org.w3c.dom.events;
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
/*    */ public class EventException
/*    */   extends RuntimeException
/*    */ {
/*    */   public short code;
/*    */   public static final short UNSPECIFIED_EVENT_TYPE_ERR = 0;
/*    */   public static final short DISPATCH_REQUEST_ERR = 1;
/*    */   
/*    */   public EventException(short code, String message) {
/* 25 */     super(message);
/* 26 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/EventException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */