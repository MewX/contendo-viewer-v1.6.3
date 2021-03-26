/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.SVGTextElementBridge;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.xbl.NodeXBL;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
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
/*     */ public class SVG12TextElementBridge
/*     */   extends SVGTextElementBridge
/*     */   implements SVG12BridgeUpdateHandler
/*     */ {
/*     */   public Bridge getInstance() {
/*  51 */     return (Bridge)new SVG12TextElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTextEventListeners(BridgeContext ctx, NodeEventTarget e) {
/*  58 */     if (this.childNodeRemovedEventListener == null) {
/*  59 */       this.childNodeRemovedEventListener = new DOMChildNodeRemovedEventListener();
/*     */     }
/*     */     
/*  62 */     if (this.subtreeModifiedEventListener == null) {
/*  63 */       this.subtreeModifiedEventListener = new DOMSubtreeModifiedEventListener();
/*     */     }
/*     */ 
/*     */     
/*  67 */     SVG12BridgeContext ctx12 = (SVG12BridgeContext)ctx;
/*  68 */     AbstractNode n = (AbstractNode)e;
/*  69 */     XBLEventSupport evtSupport = (XBLEventSupport)n.initializeEventSupport();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", (EventListener)this.childNodeRemovedEventListener, true);
/*     */ 
/*     */     
/*  77 */     ctx12.storeImplementationEventListenerNS((EventTarget)e, "http://www.w3.org/2001/xml-events", "DOMNodeRemoved", (EventListener)this.childNodeRemovedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", (EventListener)this.subtreeModifiedEventListener, false);
/*     */ 
/*     */     
/*  86 */     ctx12.storeImplementationEventListenerNS((EventTarget)e, "http://www.w3.org/2001/xml-events", "DOMSubtreeModified", (EventListener)this.subtreeModifiedEventListener, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeTextEventListeners(BridgeContext ctx, NodeEventTarget e) {
/*  96 */     AbstractNode n = (AbstractNode)e;
/*  97 */     XBLEventSupport evtSupport = (XBLEventSupport)n.initializeEventSupport();
/*     */ 
/*     */     
/* 100 */     evtSupport.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", (EventListener)this.childNodeRemovedEventListener, true);
/*     */ 
/*     */     
/* 103 */     evtSupport.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", (EventListener)this.subtreeModifiedEventListener, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DOMChildNodeRemovedEventListener
/*     */     extends SVGTextElementBridge.DOMChildNodeRemovedEventListener
/*     */   {
/*     */     protected DOMChildNodeRemovedEventListener() {
/* 111 */       super(SVG12TextElementBridge.this);
/*     */     }
/*     */     public void handleEvent(Event evt) {
/* 114 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */   
/*     */   protected class DOMSubtreeModifiedEventListener
/*     */     extends SVGTextElementBridge.DOMSubtreeModifiedEventListener {
/*     */     protected DOMSubtreeModifiedEventListener() {
/* 121 */       super(SVG12TextElementBridge.this);
/*     */     }
/*     */     public void handleEvent(Event evt) {
/* 124 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getFirstChild(Node n) {
/* 135 */     return ((NodeXBL)n).getXblFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getNextSibling(Node n) {
/* 143 */     return ((NodeXBL)n).getXblNextSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getParentNode(Node n) {
/* 151 */     return ((NodeXBL)n).getXblParentNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMCharacterDataModified(MutationEvent evt) {
/* 161 */     Node childNode = (Node)evt.getTarget();
/*     */     
/* 163 */     if (isParentDisplayed(childNode)) {
/* 164 */       if (getParentNode(childNode) != childNode.getParentNode()) {
/*     */ 
/*     */ 
/*     */         
/* 168 */         computeLaidoutText(this.ctx, this.e, this.node);
/*     */       } else {
/* 170 */         this.laidoutText = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleBindingEvent(Element bindableElement, Element shadowTree) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleContentSelectionChangedEvent(ContentSelectionChangedEvent csce) {
/* 188 */     computeLaidoutText(this.ctx, this.e, this.node);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12TextElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */