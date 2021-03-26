/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.MutationEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMMutationEvent
/*     */   extends AbstractEvent
/*     */   implements MutationEvent
/*     */ {
/*     */   private Node relatedNode;
/*     */   private String prevValue;
/*     */   private String newValue;
/*     */   private String attrName;
/*     */   private short attrChange;
/*     */   
/*     */   public Node getRelatedNode() {
/*  50 */     return this.relatedNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrevValue() {
/*  59 */     return this.prevValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNewValue() {
/*  68 */     return this.newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttrName() {
/*  76 */     return this.attrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getAttrChange() {
/*  83 */     return this.attrChange;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initMutationEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, Node relatedNodeArg, String prevValueArg, String newValueArg, String attrNameArg, short attrChangeArg) {
/* 116 */     initEvent(typeArg, canBubbleArg, cancelableArg);
/* 117 */     this.relatedNode = relatedNodeArg;
/* 118 */     this.prevValue = prevValueArg;
/* 119 */     this.newValue = newValueArg;
/* 120 */     this.attrName = attrNameArg;
/* 121 */     this.attrChange = attrChangeArg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initMutationEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, Node relatedNodeArg, String prevValueArg, String newValueArg, String attrNameArg, short attrChangeArg) {
/* 136 */     initEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg);
/* 137 */     this.relatedNode = relatedNodeArg;
/* 138 */     this.prevValue = prevValueArg;
/* 139 */     this.newValue = newValueArg;
/* 140 */     this.attrName = attrNameArg;
/* 141 */     this.attrChange = attrChangeArg;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMMutationEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */