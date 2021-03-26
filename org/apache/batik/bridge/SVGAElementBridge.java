/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.util.List;
/*     */ import org.apache.batik.anim.dom.SVGOMAElement;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimationElement;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.dom.events.AbstractEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.svg.SVGAElement;
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
/*     */ 
/*     */ public class SVGAElementBridge
/*     */   extends SVGGElementBridge
/*     */ {
/*     */   protected AnchorListener al;
/*     */   protected CursorMouseOverListener bl;
/*     */   protected CursorMouseOutListener cl;
/*     */   
/*     */   public String getLocalName() {
/*  60 */     return "a";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  67 */     return new SVGAElementBridge();
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/*  82 */     super.buildGraphicsNode(ctx, e, node);
/*     */     
/*  84 */     if (ctx.isInteractive()) {
/*  85 */       NodeEventTarget target = (NodeEventTarget)e;
/*  86 */       CursorHolder ch = new CursorHolder(CursorManager.DEFAULT_CURSOR);
/*     */       
/*  88 */       this.al = new AnchorListener(ctx.getUserAgent(), ch);
/*  89 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.al, false, null);
/*     */ 
/*     */       
/*  92 */       ctx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "click", this.al, false);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  97 */       this.bl = new CursorMouseOverListener(ctx.getUserAgent(), ch);
/*  98 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.bl, false, null);
/*     */ 
/*     */       
/* 101 */       ctx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "mouseover", this.bl, false);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       this.cl = new CursorMouseOutListener(ctx.getUserAgent(), ch);
/* 107 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.cl, false, null);
/*     */ 
/*     */       
/* 110 */       ctx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "mouseout", this.cl, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 118 */     NodeEventTarget target = (NodeEventTarget)this.e;
/* 119 */     if (this.al != null) {
/* 120 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.al, false);
/*     */ 
/*     */       
/* 123 */       this.al = null;
/*     */     } 
/* 125 */     if (this.bl != null) {
/* 126 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.bl, false);
/*     */ 
/*     */       
/* 129 */       this.bl = null;
/*     */     } 
/* 131 */     if (this.cl != null) {
/* 132 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.cl, false);
/*     */ 
/*     */       
/* 135 */       this.cl = null;
/*     */     } 
/* 137 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 144 */     return true;
/*     */   }
/*     */   
/*     */   public static class CursorHolder {
/* 148 */     Cursor cursor = null;
/*     */     
/*     */     public CursorHolder(Cursor c) {
/* 151 */       this.cursor = c;
/*     */     }
/*     */     
/*     */     public void holdCursor(Cursor c) {
/* 155 */       this.cursor = c;
/*     */     }
/*     */     public Cursor getCursor() {
/* 158 */       return this.cursor;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AnchorListener
/*     */     implements EventListener
/*     */   {
/*     */     protected UserAgent userAgent;
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public AnchorListener(UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 170 */       this.userAgent = ua;
/* 171 */       this.holder = ch;
/*     */     }
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 175 */       if (!(evt instanceof AbstractEvent))
/* 176 */         return;  AbstractEvent ae = (AbstractEvent)evt;
/*     */       
/* 178 */       List l = ae.getDefaultActions();
/* 179 */       if (l != null) {
/* 180 */         for (Object o : l) {
/* 181 */           if (o instanceof SVGAElementBridge.AnchorDefaultActionable) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 187 */       SVGAElement elt = (SVGAElement)evt.getCurrentTarget();
/* 188 */       ae.addDefaultAction(new SVGAElementBridge.AnchorDefaultActionable(elt, this.userAgent, this.holder));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AnchorDefaultActionable
/*     */     implements Runnable
/*     */   {
/*     */     protected SVGOMAElement elt;
/*     */     
/*     */     protected UserAgent userAgent;
/*     */     
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public AnchorDefaultActionable(SVGAElement e, UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 203 */       this.elt = (SVGOMAElement)e;
/* 204 */       this.userAgent = ua;
/* 205 */       this.holder = ch;
/*     */     }
/*     */     
/*     */     public void run() {
/* 209 */       this.userAgent.setSVGCursor(this.holder.getCursor());
/* 210 */       String href = this.elt.getHref().getAnimVal();
/* 211 */       ParsedURL purl = new ParsedURL(this.elt.getBaseURI(), href);
/* 212 */       SVGOMDocument doc = (SVGOMDocument)this.elt.getOwnerDocument();
/* 213 */       ParsedURL durl = doc.getParsedURL();
/* 214 */       if (purl.sameFile(durl)) {
/* 215 */         String frag = purl.getRef();
/* 216 */         if (frag != null && frag.length() != 0) {
/* 217 */           Element refElt = doc.getElementById(frag);
/* 218 */           if (refElt instanceof SVGOMAnimationElement) {
/* 219 */             SVGOMAnimationElement aelt = (SVGOMAnimationElement)refElt;
/*     */             
/* 221 */             float t = aelt.getHyperlinkBeginTime();
/* 222 */             if (Float.isNaN(t)) {
/* 223 */               aelt.beginElement();
/*     */             } else {
/* 225 */               doc.getRootElement().setCurrentTime(t);
/*     */             } 
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 231 */       this.userAgent.openLink((SVGAElement)this.elt);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CursorMouseOverListener
/*     */     implements EventListener
/*     */   {
/*     */     protected UserAgent userAgent;
/*     */     
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public CursorMouseOverListener(UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 244 */       this.userAgent = ua;
/* 245 */       this.holder = ch;
/*     */     }
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 249 */       if (!(evt instanceof AbstractEvent))
/* 250 */         return;  AbstractEvent ae = (AbstractEvent)evt;
/*     */       
/* 252 */       List l = ae.getDefaultActions();
/* 253 */       if (l != null) {
/* 254 */         for (Object o : l) {
/* 255 */           if (o instanceof SVGAElementBridge.MouseOverDefaultActionable) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       }
/* 260 */       Element target = (Element)ae.getTarget();
/* 261 */       SVGAElement elt = (SVGAElement)ae.getCurrentTarget();
/*     */       
/* 263 */       ae.addDefaultAction(new SVGAElementBridge.MouseOverDefaultActionable(target, elt, this.userAgent, this.holder));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class MouseOverDefaultActionable
/*     */     implements Runnable
/*     */   {
/*     */     protected Element target;
/*     */     
/*     */     protected SVGAElement elt;
/*     */     
/*     */     protected UserAgent userAgent;
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public MouseOverDefaultActionable(Element t, SVGAElement e, UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 279 */       this.target = t;
/* 280 */       this.elt = e;
/* 281 */       this.userAgent = ua;
/* 282 */       this.holder = ch;
/*     */     }
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
/*     */     public void run() {
/* 303 */       if (CSSUtilities.isAutoCursor(this.target)) {
/* 304 */         this.holder.holdCursor(CursorManager.DEFAULT_CURSOR);
/*     */         
/* 306 */         this.userAgent.setSVGCursor(CursorManager.ANCHOR_CURSOR);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 312 */       if (this.elt != null) {
/* 313 */         String href = this.elt.getHref().getAnimVal();
/* 314 */         this.userAgent.displayMessage(href);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CursorMouseOutListener
/*     */     implements EventListener
/*     */   {
/*     */     protected UserAgent userAgent;
/*     */     
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public CursorMouseOutListener(UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 328 */       this.userAgent = ua;
/* 329 */       this.holder = ch;
/*     */     }
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 333 */       if (!(evt instanceof AbstractEvent))
/* 334 */         return;  AbstractEvent ae = (AbstractEvent)evt;
/*     */       
/* 336 */       List l = ae.getDefaultActions();
/* 337 */       if (l != null) {
/* 338 */         for (Object o : l) {
/* 339 */           if (o instanceof SVGAElementBridge.MouseOutDefaultActionable) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       }
/* 344 */       SVGAElement elt = (SVGAElement)evt.getCurrentTarget();
/* 345 */       ae.addDefaultAction(new SVGAElementBridge.MouseOutDefaultActionable(elt, this.userAgent, this.holder));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class MouseOutDefaultActionable
/*     */     implements Runnable
/*     */   {
/*     */     protected SVGAElement elt;
/*     */     
/*     */     protected UserAgent userAgent;
/*     */     protected SVGAElementBridge.CursorHolder holder;
/*     */     
/*     */     public MouseOutDefaultActionable(SVGAElement e, UserAgent ua, SVGAElementBridge.CursorHolder ch) {
/* 359 */       this.elt = e;
/* 360 */       this.userAgent = ua;
/* 361 */       this.holder = ch;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 369 */       if (this.elt != null)
/* 370 */         this.userAgent.displayMessage(""); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */