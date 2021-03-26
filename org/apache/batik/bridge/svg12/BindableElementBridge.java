/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.BindableElement;
/*     */ import org.apache.batik.bridge.AbstractGraphicsNodeBridge;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.ScriptingEnvironment;
/*     */ import org.apache.batik.bridge.UpdateManager;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
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
/*     */ 
/*     */ public class BindableElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */   implements SVG12BridgeUpdateHandler
/*     */ {
/*     */   public String getNamespaceURI() {
/*  56 */     return "*";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  63 */     return "*";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  70 */     return (Bridge)new BindableElementBridge();
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
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  82 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/*  83 */       return null;
/*     */     }
/*     */     
/*  86 */     CompositeGraphicsNode gn = buildCompositeGraphicsNode(ctx, e, (CompositeGraphicsNode)null);
/*     */     
/*  88 */     return (GraphicsNode)gn;
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
/*     */   public CompositeGraphicsNode buildCompositeGraphicsNode(BridgeContext ctx, Element e, CompositeGraphicsNode gn) {
/* 106 */     BindableElement be = (BindableElement)e;
/* 107 */     Element shadowTree = be.getXblShadowTree();
/*     */     
/* 109 */     UpdateManager um = ctx.getUpdateManager();
/* 110 */     ScriptingEnvironment se = (um == null) ? null : um.getScriptingEnvironment();
/*     */ 
/*     */     
/* 113 */     if (se != null && shadowTree != null) {
/* 114 */       se.addScriptingListeners(shadowTree);
/*     */     }
/*     */     
/* 117 */     if (gn == null) {
/* 118 */       gn = new CompositeGraphicsNode();
/* 119 */       associateSVGContext(ctx, e, (GraphicsNode)gn);
/*     */     } else {
/* 121 */       int s = gn.size();
/* 122 */       for (int i = 0; i < s; i++) {
/* 123 */         gn.remove(0);
/*     */       }
/*     */     } 
/*     */     
/* 127 */     GVTBuilder builder = ctx.getGVTBuilder();
/*     */     
/* 129 */     if (shadowTree != null) {
/* 130 */       GraphicsNode shadowNode = builder.build(ctx, shadowTree);
/* 131 */       if (shadowNode != null) {
/* 132 */         gn.add(shadowNode);
/*     */       }
/*     */     } else {
/* 135 */       for (Node m = e.getFirstChild(); m != null; m = m.getNextSibling()) {
/* 136 */         if (m.getNodeType() == 1) {
/* 137 */           GraphicsNode n = builder.build(ctx, (Element)m);
/* 138 */           if (n != null) {
/* 139 */             gn.add(n);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return gn;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 149 */     BindableElement be = (BindableElement)this.e;
/* 150 */     if (be != null && be.getCSSFirstChild() != null) {
/* 151 */       disposeTree(be.getCSSFirstChild());
/*     */     }
/*     */     
/* 154 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 169 */     return false;
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
/* 184 */     initializeDynamicSupport(ctx, e, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 194 */     BindableElement be = (BindableElement)this.e;
/* 195 */     Element shadowTree = be.getXblShadowTree();
/*     */     
/* 197 */     if (shadowTree == null && evt.getTarget() instanceof Element) {
/* 198 */       handleElementAdded((CompositeGraphicsNode)this.node, this.e, (Element)evt.getTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleBindingEvent(Element bindableElement, Element shadowTree) {
/* 209 */     CompositeGraphicsNode gn = this.node.getParent();
/* 210 */     gn.remove(this.node);
/* 211 */     disposeTree(this.e);
/*     */     
/* 213 */     handleElementAdded(gn, this.e.getParentNode(), this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleContentSelectionChangedEvent(ContentSelectionChangedEvent csce) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleElementAdded(CompositeGraphicsNode gn, Node parent, Element childElt) {
/* 231 */     GVTBuilder builder = this.ctx.getGVTBuilder();
/* 232 */     GraphicsNode childNode = builder.build(this.ctx, childElt);
/* 233 */     if (childNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 238 */     int idx = -1;
/* 239 */     for (Node ps = childElt.getPreviousSibling(); ps != null; 
/* 240 */       ps = ps.getPreviousSibling()) {
/* 241 */       CompositeGraphicsNode compositeGraphicsNode; if (ps.getNodeType() != 1)
/*     */         continue; 
/* 243 */       Element pse = (Element)ps;
/* 244 */       GraphicsNode psgn = this.ctx.getGraphicsNode(pse);
/* 245 */       while (psgn != null && psgn.getParent() != gn)
/*     */       {
/*     */         
/* 248 */         compositeGraphicsNode = psgn.getParent();
/*     */       }
/* 250 */       if (compositeGraphicsNode == null)
/*     */         continue; 
/* 252 */       idx = gn.indexOf(compositeGraphicsNode);
/* 253 */       if (idx == -1) {
/*     */         continue;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 259 */     idx++;
/* 260 */     gn.add(idx, childNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/BindableElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */