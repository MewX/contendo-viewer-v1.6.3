/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.swing.JSVGCanvas;
/*     */ import org.apache.batik.swing.gvt.Overlay;
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
/*     */ public class ElementOverlayManager
/*     */ {
/*  46 */   protected Color elementOverlayStrokeColor = Color.black;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Color elementOverlayColor = Color.white;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean xorMode = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JSVGCanvas canvas;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected Overlay elementOverlay = new ElementOverlay();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList elements;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ElementOverlayController controller;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isOverlayEnabled = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElementOverlayManager(JSVGCanvas canvas) {
/*  90 */     this.canvas = canvas;
/*  91 */     this.elements = new ArrayList();
/*  92 */     canvas.getOverlays().add(this.elementOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(Element elem) {
/* 102 */     this.elements.add(elem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeElement(Element elem) {
/* 113 */     if (this.elements.remove(elem));
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
/*     */   public void removeElements() {
/* 128 */     this.elements.clear();
/* 129 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle getAllElementsBounds() {
/* 138 */     Rectangle resultBound = null;
/* 139 */     int n = this.elements.size();
/* 140 */     for (Object element : this.elements) {
/* 141 */       Element currentElement = (Element)element;
/* 142 */       Rectangle currentBound = getElementBounds(currentElement);
/* 143 */       if (resultBound == null) {
/* 144 */         resultBound = currentBound; continue;
/*     */       } 
/* 146 */       resultBound.add(currentBound);
/*     */     } 
/*     */     
/* 149 */     return resultBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle getElementBounds(Element elem) {
/* 160 */     return getElementBounds(this.canvas.getUpdateManager().getBridgeContext().getGraphicsNode(elem));
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
/*     */   protected Rectangle getElementBounds(GraphicsNode node) {
/* 172 */     if (node == null) {
/* 173 */       return null;
/*     */     }
/* 175 */     AffineTransform at = this.canvas.getRenderingTransform();
/* 176 */     Shape s = at.createTransformedShape(node.getOutline());
/* 177 */     return outset(s.getBounds(), 1);
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
/*     */   protected Rectangle outset(Rectangle r, int amount) {
/* 191 */     r.x -= amount;
/* 192 */     r.y -= amount;
/* 193 */     r.width += 2 * amount;
/* 194 */     r.height += 2 * amount;
/* 195 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void repaint() {
/* 202 */     this.canvas.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class ElementOverlay
/*     */     implements Overlay
/*     */   {
/*     */     public void paint(Graphics g) {
/* 214 */       if (ElementOverlayManager.this.controller.isOverlayEnabled() && ElementOverlayManager.this.isOverlayEnabled()) {
/* 215 */         int n = ElementOverlayManager.this.elements.size();
/* 216 */         for (Object element : ElementOverlayManager.this.elements) {
/* 217 */           Element currentElement = (Element)element;
/* 218 */           GraphicsNode nodeToPaint = ElementOverlayManager.this.canvas.getUpdateManager().getBridgeContext().getGraphicsNode(currentElement);
/*     */           
/* 220 */           if (nodeToPaint != null) {
/* 221 */             AffineTransform elementsAt = nodeToPaint.getGlobalTransform();
/*     */             
/* 223 */             Shape selectionHighlight = nodeToPaint.getOutline();
/* 224 */             AffineTransform at = ElementOverlayManager.this.canvas.getRenderingTransform();
/* 225 */             at.concatenate(elementsAt);
/* 226 */             Shape s = at.createTransformedShape(selectionHighlight);
/* 227 */             if (s == null) {
/*     */               break;
/*     */             }
/* 230 */             Graphics2D g2d = (Graphics2D)g;
/* 231 */             if (ElementOverlayManager.this.xorMode) {
/* 232 */               g2d.setColor(Color.black);
/* 233 */               g2d.setXORMode(Color.yellow);
/* 234 */               g2d.fill(s);
/* 235 */               g2d.draw(s); continue;
/*     */             } 
/* 237 */             g2d.setColor(ElementOverlayManager.this.elementOverlayColor);
/* 238 */             g2d.setStroke(new BasicStroke(1.8F));
/* 239 */             g2d.setColor(ElementOverlayManager.this.elementOverlayStrokeColor);
/* 240 */             g2d.draw(s);
/*     */           } 
/*     */         } 
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
/*     */   public Color getElementOverlayColor() {
/* 254 */     return this.elementOverlayColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElementOverlayColor(Color selectionOverlayColor) {
/* 263 */     this.elementOverlayColor = selectionOverlayColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getElementOverlayStrokeColor() {
/* 272 */     return this.elementOverlayStrokeColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElementOverlayStrokeColor(Color selectionOverlayStrokeColor) {
/* 283 */     this.elementOverlayStrokeColor = selectionOverlayStrokeColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXorMode() {
/* 292 */     return this.xorMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXorMode(boolean xorMode) {
/* 302 */     this.xorMode = xorMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Overlay getElementOverlay() {
/* 311 */     return this.elementOverlay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeOverlay() {
/* 318 */     this.canvas.getOverlays().remove(this.elementOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setController(ElementOverlayController controller) {
/* 328 */     this.controller = controller;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOverlayEnabled() {
/* 337 */     return this.isOverlayEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverlayEnabled(boolean isOverlayEnabled) {
/* 344 */     this.isOverlayEnabled = isOverlayEnabled;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/ElementOverlayManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */