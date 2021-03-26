/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.SVGBridgeExtension;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class SVG12BridgeExtension
/*     */   extends SVGBridgeExtension
/*     */ {
/*     */   public float getPriority() {
/*  46 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getImplementedExtensions() {
/*  55 */     return Collections.EMPTY_LIST.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/*  63 */     return "The Apache Batik Team.";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContactAddress() {
/*  70 */     return "batik-dev@xmlgraphics.apache.org";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/*  78 */     return "http://xml.apache.org/batik";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  87 */     return "The required SVG 1.2 tags";
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
/*     */   public void registerTags(BridgeContext ctx) {
/* 100 */     super.registerTags(ctx);
/*     */ 
/*     */     
/* 103 */     ctx.putBridge((Bridge)new SVGFlowRootElementBridge());
/* 104 */     ctx.putBridge((Bridge)new SVGMultiImageElementBridge());
/* 105 */     ctx.putBridge((Bridge)new SVGSolidColorElementBridge());
/*     */     
/* 107 */     ctx.putBridge((Bridge)new SVG12TextElementBridge());
/*     */ 
/*     */     
/* 110 */     ctx.putBridge((Bridge)new XBLShadowTreeElementBridge());
/* 111 */     ctx.putBridge((Bridge)new XBLContentElementBridge());
/*     */ 
/*     */     
/* 114 */     ctx.setDefaultBridge((Bridge)new BindableElementBridge());
/*     */ 
/*     */     
/* 117 */     ctx.putReservedNamespaceURI(null);
/* 118 */     ctx.putReservedNamespaceURI("http://www.w3.org/2000/svg");
/* 119 */     ctx.putReservedNamespaceURI("http://www.w3.org/2004/xbl");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamicElement(Element e) {
/* 130 */     String ns = e.getNamespaceURI();
/* 131 */     if ("http://www.w3.org/2004/xbl".equals(ns)) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (!"http://www.w3.org/2000/svg".equals(ns)) {
/* 135 */       return false;
/*     */     }
/* 137 */     String ln = e.getLocalName();
/* 138 */     if (ln.equals("script") || ln.equals("handler") || ln.startsWith("animate") || ln.equals("set"))
/*     */     {
/*     */ 
/*     */       
/* 142 */       return true;
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12BridgeExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */