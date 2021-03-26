/*    */ package org.apache.batik.dom.events;
/*    */ 
/*    */ import org.apache.batik.w3c.dom.events.MutationNameEvent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DOMMutationNameEvent
/*    */   extends DOMMutationEvent
/*    */   implements MutationNameEvent
/*    */ {
/*    */   protected String prevNamespaceURI;
/*    */   protected String prevNodeName;
/*    */   
/*    */   public void initMutationNameEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, Node relatedNodeArg, String prevNamespaceURIArg, String prevNodeNameArg) {
/* 53 */     initMutationEvent(typeArg, canBubbleArg, cancelableArg, relatedNodeArg, null, null, null, (short)0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     this.prevNamespaceURI = prevNamespaceURIArg;
/* 62 */     this.prevNodeName = prevNodeNameArg;
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
/*    */   public void initMutationNameEventNS(String namespaceURI, String typeArg, boolean canBubbleArg, boolean cancelableArg, Node relatedNodeArg, String prevNamespaceURIArg, String prevNodeNameArg) {
/* 75 */     initMutationEventNS(namespaceURI, typeArg, canBubbleArg, cancelableArg, relatedNodeArg, null, null, null, (short)0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     this.prevNamespaceURI = prevNamespaceURIArg;
/* 85 */     this.prevNodeName = prevNodeNameArg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPrevNamespaceURI() {
/* 92 */     return this.prevNamespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPrevNodeName() {
/* 99 */     return this.prevNodeName;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMMutationNameEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */