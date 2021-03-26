/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import org.apache.batik.css.engine.CSSEngineEvent;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.apache.batik.gvt.ShapePainter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGShapeElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */ {
/*     */   protected boolean hasNewShapePainter;
/*     */   
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  52 */     ShapeNode shapeNode = (ShapeNode)super.createGraphicsNode(ctx, e);
/*  53 */     if (shapeNode == null) {
/*  54 */       return null;
/*     */     }
/*     */     
/*  57 */     associateSVGContext(ctx, e, (GraphicsNode)shapeNode);
/*     */ 
/*     */     
/*  60 */     buildShape(ctx, e, shapeNode);
/*     */ 
/*     */     
/*  63 */     RenderingHints hints = null;
/*  64 */     hints = CSSUtilities.convertColorRendering(e, hints);
/*  65 */     hints = CSSUtilities.convertShapeRendering(e, hints);
/*  66 */     if (hints != null) {
/*  67 */       shapeNode.setRenderingHints(hints);
/*     */     }
/*  69 */     return (GraphicsNode)shapeNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/*  76 */     return (GraphicsNode)new ShapeNode();
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/*  90 */     ShapeNode shapeNode = (ShapeNode)node;
/*  91 */     shapeNode.setShapePainter(createShapePainter(ctx, e, shapeNode));
/*  92 */     super.buildGraphicsNode(ctx, e, node);
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
/*     */   protected ShapePainter createShapePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/* 117 */     return PaintServer.convertFillAndStroke(e, shapeNode, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void buildShape(BridgeContext paramBridgeContext, Element paramElement, ShapeNode paramShapeNode);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleGeometryChanged() {
/* 145 */     super.handleGeometryChanged();
/* 146 */     ShapeNode shapeNode = (ShapeNode)this.node;
/* 147 */     shapeNode.setShapePainter(createShapePainter(this.ctx, this.e, shapeNode));
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
/*     */   public void handleCSSEngineEvent(CSSEngineEvent evt) {
/* 162 */     this.hasNewShapePainter = false;
/* 163 */     super.handleCSSEngineEvent(evt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleCSSPropertyChanged(int property) {
/*     */     RenderingHints hints;
/* 170 */     switch (property) {
/*     */       
/*     */       case 15:
/*     */       case 16:
/*     */       case 45:
/*     */       case 46:
/*     */       case 47:
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/*     */       case 52:
/* 182 */         if (!this.hasNewShapePainter) {
/* 183 */           this.hasNewShapePainter = true;
/* 184 */           ShapeNode shapeNode = (ShapeNode)this.node;
/* 185 */           shapeNode.setShapePainter(createShapePainter(this.ctx, this.e, shapeNode));
/*     */         } 
/*     */         return;
/*     */       
/*     */       case 42:
/* 190 */         hints = this.node.getRenderingHints();
/* 191 */         hints = CSSUtilities.convertShapeRendering(this.e, hints);
/* 192 */         if (hints != null) {
/* 193 */           this.node.setRenderingHints(hints);
/*     */         }
/*     */         return;
/*     */       
/*     */       case 9:
/* 198 */         hints = this.node.getRenderingHints();
/* 199 */         hints = CSSUtilities.convertColorRendering(this.e, hints);
/* 200 */         if (hints != null) {
/* 201 */           this.node.setRenderingHints(hints);
/*     */         }
/*     */         return;
/*     */     } 
/*     */     
/* 206 */     super.handleCSSPropertyChanged(property);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGShapeElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */