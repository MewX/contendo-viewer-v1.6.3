/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import org.apache.batik.anim.dom.SVGOMAnimationElement;
/*    */ import org.apache.batik.anim.timing.TimedElement;
/*    */ import org.apache.batik.dom.events.DOMTimeEvent;
/*    */ import org.apache.batik.dom.svg.IdContainer;
/*    */ import org.apache.batik.dom.svg.SVGOMUseShadowRoot;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.events.DocumentEvent;
/*    */ import org.w3c.dom.events.Event;
/*    */ import org.w3c.dom.events.EventTarget;
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
/*    */ public abstract class AnimationSupport
/*    */ {
/*    */   public static void fireTimeEvent(EventTarget target, String eventType, Calendar time, int detail) {
/* 49 */     DocumentEvent de = (DocumentEvent)((Node)target).getOwnerDocument();
/* 50 */     DOMTimeEvent evt = (DOMTimeEvent)de.createEvent("TimeEvent");
/* 51 */     evt.initTimeEventNS("http://www.w3.org/2001/xml-events", eventType, null, detail);
/*    */ 
/*    */     
/* 54 */     evt.setTimestamp(time.getTime().getTime());
/* 55 */     target.dispatchEvent((Event)evt);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TimedElement getTimedElementById(String id, Node n) {
/* 63 */     Element e = getElementById(id, n);
/* 64 */     if (e instanceof SVGOMAnimationElement) {
/* 65 */       SVGAnimationElementBridge b = (SVGAnimationElementBridge)((SVGOMAnimationElement)e).getSVGContext();
/*    */       
/* 67 */       return b.getTimedElement();
/*    */     } 
/* 69 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EventTarget getEventTargetById(String id, Node n) {
/* 77 */     return (EventTarget)getElementById(id, n);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Element getElementById(String id, Node n) {
/* 85 */     Node p = n.getParentNode();
/* 86 */     while (p != null) {
/* 87 */       n = p;
/* 88 */       if (n instanceof SVGOMUseShadowRoot) {
/* 89 */         p = ((SVGOMUseShadowRoot)n).getCSSParentNode(); continue;
/*    */       } 
/* 91 */       p = n.getParentNode();
/*    */     } 
/*    */     
/* 94 */     if (n instanceof IdContainer) {
/* 95 */       return ((IdContainer)n).getElementById(id);
/*    */     }
/* 97 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AnimationSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */