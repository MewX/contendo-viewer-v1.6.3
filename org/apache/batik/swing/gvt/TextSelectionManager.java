/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.batik.bridge.ConcreteTextSelector;
/*     */ import org.apache.batik.bridge.Mark;
/*     */ import org.apache.batik.gvt.event.EventDispatcher;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseListener;
/*     */ import org.apache.batik.gvt.event.SelectionEvent;
/*     */ import org.apache.batik.gvt.event.SelectionListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextSelectionManager
/*     */ {
/*  51 */   public static final Cursor TEXT_CURSOR = new Cursor(2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ConcreteTextSelector textSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JGVTComponent component;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected Overlay selectionOverlay = new SelectionOverlay();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MouseListener mouseListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Cursor previousCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Shape selectionHighlight;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SelectionListener textSelectionListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   protected Color selectionOverlayColor = new Color(100, 100, 255, 100);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected Color selectionOverlayStrokeColor = Color.white;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean xorMode = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   Object selection = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextSelectionManager(JGVTComponent comp, EventDispatcher ed) {
/* 114 */     this.textSelector = new ConcreteTextSelector();
/* 115 */     this.textSelectionListener = new TextSelectionListener();
/* 116 */     this.textSelector.addSelectionListener(this.textSelectionListener);
/* 117 */     this.mouseListener = new MouseListener();
/*     */     
/* 119 */     this.component = comp;
/* 120 */     this.component.getOverlays().add(this.selectionOverlay);
/*     */     
/* 122 */     ed.addGraphicsNodeMouseListener(this.mouseListener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSelectionListener(SelectionListener sl) {
/* 130 */     this.textSelector.addSelectionListener(sl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSelectionListener(SelectionListener sl) {
/* 138 */     this.textSelector.removeSelectionListener(sl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionOverlayColor(Color color) {
/* 147 */     this.selectionOverlayColor = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getSelectionOverlayColor() {
/* 154 */     return this.selectionOverlayColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionOverlayStrokeColor(Color color) {
/* 164 */     this.selectionOverlayStrokeColor = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getSelectionOverlayStrokeColor() {
/* 171 */     return this.selectionOverlayStrokeColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionOverlayXORMode(boolean state) {
/* 181 */     this.xorMode = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSelectionOverlayXORMode() {
/* 189 */     return this.xorMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Overlay getSelectionOverlay() {
/* 196 */     return this.selectionOverlay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSelection() {
/* 203 */     return this.selection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelection(Mark start, Mark end) {
/* 210 */     this.textSelector.setSelection(start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearSelection() {
/* 217 */     this.textSelector.clearSelection();
/*     */   }
/*     */ 
/*     */   
/*     */   protected class MouseListener
/*     */     implements GraphicsNodeMouseListener
/*     */   {
/*     */     public void mouseClicked(GraphicsNodeMouseEvent evt) {
/* 225 */       if (evt.getSource() instanceof org.apache.batik.gvt.Selectable) {
/* 226 */         TextSelectionManager.this.textSelector.mouseClicked(evt);
/*     */       }
/*     */     }
/*     */     
/*     */     public void mousePressed(GraphicsNodeMouseEvent evt) {
/* 231 */       if (evt.getSource() instanceof org.apache.batik.gvt.Selectable) {
/* 232 */         TextSelectionManager.this.textSelector.mousePressed(evt);
/* 233 */       } else if (TextSelectionManager.this.selectionHighlight != null) {
/* 234 */         TextSelectionManager.this.textSelector.clearSelection();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseReleased(GraphicsNodeMouseEvent evt) {
/* 239 */       TextSelectionManager.this.textSelector.mouseReleased(evt);
/*     */     }
/*     */     
/*     */     public void mouseEntered(GraphicsNodeMouseEvent evt) {
/* 243 */       if (evt.getSource() instanceof org.apache.batik.gvt.Selectable) {
/* 244 */         TextSelectionManager.this.textSelector.mouseEntered(evt);
/* 245 */         TextSelectionManager.this.previousCursor = TextSelectionManager.this.component.getCursor();
/* 246 */         if (TextSelectionManager.this.previousCursor.getType() == 0) {
/* 247 */           TextSelectionManager.this.component.setCursor(TextSelectionManager.TEXT_CURSOR);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseExited(GraphicsNodeMouseEvent evt) {
/* 253 */       if (evt.getSource() instanceof org.apache.batik.gvt.Selectable) {
/* 254 */         TextSelectionManager.this.textSelector.mouseExited(evt);
/* 255 */         if (TextSelectionManager.this.component.getCursor() == TextSelectionManager.TEXT_CURSOR) {
/* 256 */           TextSelectionManager.this.component.setCursor(TextSelectionManager.this.previousCursor);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseDragged(GraphicsNodeMouseEvent evt) {
/* 262 */       if (evt.getSource() instanceof org.apache.batik.gvt.Selectable) {
/* 263 */         TextSelectionManager.this.textSelector.mouseDragged(evt);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(GraphicsNodeMouseEvent evt) {}
/*     */   }
/*     */   
/*     */   protected class TextSelectionListener
/*     */     implements SelectionListener
/*     */   {
/*     */     public void selectionDone(SelectionEvent e) {
/* 275 */       selectionChanged(e);
/* 276 */       TextSelectionManager.this.selection = e.getSelection();
/*     */     }
/*     */     public void selectionCleared(SelectionEvent e) {
/* 279 */       selectionStarted(e);
/*     */     }
/*     */     public void selectionStarted(SelectionEvent e) {
/* 282 */       if (TextSelectionManager.this.selectionHighlight != null) {
/* 283 */         Rectangle r = TextSelectionManager.this.getHighlightBounds();
/* 284 */         TextSelectionManager.this.selectionHighlight = null;
/* 285 */         TextSelectionManager.this.component.repaint(r);
/*     */       } 
/* 287 */       TextSelectionManager.this.selection = null;
/*     */     }
/*     */     public void selectionChanged(SelectionEvent e) {
/* 290 */       Rectangle r = null;
/* 291 */       AffineTransform at = TextSelectionManager.this.component.getRenderingTransform();
/* 292 */       if (TextSelectionManager.this.selectionHighlight != null) {
/* 293 */         r = at.createTransformedShape(TextSelectionManager.this.selectionHighlight).getBounds();
/* 294 */         TextSelectionManager.this.outset(r, 1);
/*     */       } 
/*     */       
/* 297 */       TextSelectionManager.this.selectionHighlight = e.getHighlightShape();
/* 298 */       if (TextSelectionManager.this.selectionHighlight != null) {
/* 299 */         if (r != null) {
/* 300 */           Rectangle r2 = TextSelectionManager.this.getHighlightBounds();
/* 301 */           r2.add(r);
/* 302 */           TextSelectionManager.this.component.repaint(r2);
/*     */         } else {
/* 304 */           TextSelectionManager.this.component.repaint(TextSelectionManager.this.getHighlightBounds());
/*     */         } 
/* 306 */       } else if (r != null) {
/* 307 */         TextSelectionManager.this.component.repaint(r);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Rectangle outset(Rectangle r, int amount) {
/* 314 */     r.x -= amount;
/* 315 */     r.y -= amount;
/* 316 */     r.width += 2 * amount;
/* 317 */     r.height += 2 * amount;
/* 318 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle getHighlightBounds() {
/* 325 */     AffineTransform at = this.component.getRenderingTransform();
/* 326 */     Shape s = at.createTransformedShape(this.selectionHighlight);
/* 327 */     return outset(s.getBounds(), 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SelectionOverlay
/*     */     implements Overlay
/*     */   {
/*     */     public void paint(Graphics g) {
/* 339 */       if (TextSelectionManager.this.selectionHighlight != null) {
/* 340 */         AffineTransform at = TextSelectionManager.this.component.getRenderingTransform();
/* 341 */         Shape s = at.createTransformedShape(TextSelectionManager.this.selectionHighlight);
/*     */         
/* 343 */         Graphics2D g2d = (Graphics2D)g;
/* 344 */         if (TextSelectionManager.this.xorMode) {
/* 345 */           g2d.setColor(Color.black);
/* 346 */           g2d.setXORMode(Color.white);
/* 347 */           g2d.fill(s);
/*     */         } else {
/* 349 */           g2d.setColor(TextSelectionManager.this.selectionOverlayColor);
/* 350 */           g2d.fill(s);
/* 351 */           if (TextSelectionManager.this.selectionOverlayStrokeColor != null) {
/* 352 */             g2d.setStroke(new BasicStroke(1.0F));
/* 353 */             g2d.setColor(TextSelectionManager.this.selectionOverlayStrokeColor);
/* 354 */             g2d.draw(s);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/TextSelectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */