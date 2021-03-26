/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.RootGraphicsNode;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTBuilder
/*     */   implements SVGConstants
/*     */ {
/*     */   public GraphicsNode build(BridgeContext ctx, Document document) {
/*  54 */     ctx.setDocument(document);
/*  55 */     ctx.initializeDocument(document);
/*     */ 
/*     */     
/*  58 */     ctx.setGVTBuilder(this);
/*     */ 
/*     */     
/*  61 */     DocumentBridge dBridge = ctx.getDocumentBridge();
/*  62 */     RootGraphicsNode rootNode = null;
/*     */     
/*     */     try {
/*  65 */       rootNode = dBridge.createGraphicsNode(ctx, document);
/*  66 */       Element svgElement = document.getDocumentElement();
/*  67 */       GraphicsNode topNode = null;
/*     */ 
/*     */       
/*  70 */       Bridge bridge = ctx.getBridge(svgElement);
/*  71 */       if (bridge == null || !(bridge instanceof GraphicsNodeBridge)) {
/*  72 */         return null;
/*     */       }
/*     */       
/*  75 */       GraphicsNodeBridge gnBridge = (GraphicsNodeBridge)bridge;
/*  76 */       topNode = gnBridge.createGraphicsNode(ctx, svgElement);
/*  77 */       if (topNode == null) {
/*  78 */         return null;
/*     */       }
/*  80 */       rootNode.getChildren().add(topNode);
/*     */       
/*  82 */       buildComposite(ctx, svgElement, (CompositeGraphicsNode)topNode);
/*  83 */       gnBridge.buildGraphicsNode(ctx, svgElement, topNode);
/*     */ 
/*     */       
/*  86 */       dBridge.buildGraphicsNode(ctx, document, rootNode);
/*  87 */     } catch (BridgeException ex) {
/*     */       
/*  89 */       ex.setGraphicsNode((GraphicsNode)rootNode);
/*     */       
/*  91 */       throw ex;
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (ctx.isInteractive()) {
/*  96 */       ctx.addUIEventListeners(document);
/*     */ 
/*     */       
/*  99 */       ctx.addGVTListener(document);
/*     */     } 
/*     */ 
/*     */     
/* 103 */     if (ctx.isDynamic())
/*     */     {
/* 105 */       ctx.addDOMListeners();
/*     */     }
/* 107 */     return (GraphicsNode)rootNode;
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
/*     */   public GraphicsNode build(BridgeContext ctx, Element e) {
/* 120 */     Bridge bridge = ctx.getBridge(e);
/* 121 */     if (bridge instanceof GenericBridge) {
/*     */ 
/*     */       
/* 124 */       ((GenericBridge)bridge).handleElement(ctx, e);
/* 125 */       handleGenericBridges(ctx, e);
/* 126 */       return null;
/* 127 */     }  if (bridge == null || !(bridge instanceof GraphicsNodeBridge)) {
/* 128 */       handleGenericBridges(ctx, e);
/* 129 */       return null;
/*     */     } 
/*     */     
/* 132 */     GraphicsNodeBridge gnBridge = (GraphicsNodeBridge)bridge;
/*     */     
/* 134 */     if (!gnBridge.getDisplay(e)) {
/* 135 */       handleGenericBridges(ctx, e);
/* 136 */       return null;
/*     */     } 
/* 138 */     GraphicsNode gn = gnBridge.createGraphicsNode(ctx, e);
/* 139 */     if (gn != null) {
/* 140 */       if (gnBridge.isComposite()) {
/* 141 */         buildComposite(ctx, e, (CompositeGraphicsNode)gn);
/*     */       } else {
/* 143 */         handleGenericBridges(ctx, e);
/*     */       } 
/* 145 */       gnBridge.buildGraphicsNode(ctx, e, gn);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (ctx.isDynamic());
/*     */ 
/*     */     
/* 153 */     return gn;
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
/*     */   protected void buildComposite(BridgeContext ctx, Element e, CompositeGraphicsNode parentNode) {
/* 169 */     for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 170 */       if (n.getNodeType() == 1) {
/* 171 */         buildGraphicsNode(ctx, (Element)n, parentNode);
/*     */       }
/*     */     } 
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
/*     */   protected void buildGraphicsNode(BridgeContext ctx, Element e, CompositeGraphicsNode parentNode) {
/* 190 */     if (HaltingThread.hasBeenHalted()) {
/* 191 */       throw new InterruptedBridgeException();
/*     */     }
/*     */     
/* 194 */     Bridge bridge = ctx.getBridge(e);
/* 195 */     if (bridge instanceof GenericBridge) {
/*     */ 
/*     */       
/* 198 */       ((GenericBridge)bridge).handleElement(ctx, e);
/* 199 */       handleGenericBridges(ctx, e); return;
/*     */     } 
/* 201 */     if (bridge == null || !(bridge instanceof GraphicsNodeBridge)) {
/* 202 */       handleGenericBridges(ctx, e);
/*     */       
/*     */       return;
/*     */     } 
/* 206 */     if (!CSSUtilities.convertDisplay(e)) {
/* 207 */       handleGenericBridges(ctx, e);
/*     */       return;
/*     */     } 
/* 210 */     GraphicsNodeBridge gnBridge = (GraphicsNodeBridge)bridge;
/*     */     
/*     */     try {
/* 213 */       GraphicsNode gn = gnBridge.createGraphicsNode(ctx, e);
/* 214 */       if (gn != null) {
/*     */         
/* 216 */         parentNode.getChildren().add(gn);
/*     */         
/* 218 */         if (gnBridge.isComposite()) {
/* 219 */           buildComposite(ctx, e, (CompositeGraphicsNode)gn);
/*     */         } else {
/*     */           
/* 222 */           handleGenericBridges(ctx, e);
/*     */         } 
/* 224 */         gnBridge.buildGraphicsNode(ctx, e, gn);
/*     */       } else {
/* 226 */         handleGenericBridges(ctx, e);
/*     */       } 
/* 228 */     } catch (BridgeException ex) {
/*     */ 
/*     */ 
/*     */       
/* 232 */       GraphicsNode errNode = ex.getGraphicsNode();
/* 233 */       if (errNode != null) {
/* 234 */         parentNode.getChildren().add(errNode);
/* 235 */         gnBridge.buildGraphicsNode(ctx, e, errNode);
/* 236 */         ex.setGraphicsNode(null);
/*     */       } 
/*     */       
/* 239 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleGenericBridges(BridgeContext ctx, Element e) {
/* 250 */     for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 251 */       if (n instanceof Element) {
/* 252 */         Element e2 = (Element)n;
/* 253 */         Bridge b = ctx.getBridge(e2);
/* 254 */         if (b instanceof GenericBridge) {
/* 255 */           ((GenericBridge)b).handleElement(ctx, e2);
/*     */         }
/* 257 */         handleGenericBridges(ctx, e2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/GVTBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */