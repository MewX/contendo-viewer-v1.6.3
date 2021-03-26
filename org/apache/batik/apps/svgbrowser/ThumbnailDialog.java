/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ import org.apache.batik.bridge.ViewBox;
/*     */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.swing.JSVGCanvas;
/*     */ import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
/*     */ import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
/*     */ import org.apache.batik.swing.gvt.GVTTreeRendererListener;
/*     */ import org.apache.batik.swing.gvt.JGVTComponent;
/*     */ import org.apache.batik.swing.gvt.Overlay;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
/*     */ import org.apache.batik.swing.svg.SVGDocumentLoaderListener;
/*     */ import org.apache.batik.util.resources.ResourceManager;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThumbnailDialog
/*     */   extends JDialog
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.ThumbnailDialog";
/*  89 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.ThumbnailDialog", Locale.getDefault());
/*  90 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */   
/*     */   protected JSVGCanvas svgCanvas;
/*     */ 
/*     */ 
/*     */   
/*     */   protected JGVTComponent svgThumbnailCanvas;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean documentChanged;
/*     */ 
/*     */ 
/*     */   
/*     */   protected AreaOfInterestOverlay overlay;
/*     */ 
/*     */   
/*     */   protected AreaOfInterestListener aoiListener;
/*     */ 
/*     */   
/*     */   protected boolean interactionEnabled = true;
/*     */ 
/*     */ 
/*     */   
/*     */   public ThumbnailDialog(Frame owner, JSVGCanvas svgCanvas) {
/* 117 */     super(owner, resources.getString("Dialog.title"));
/*     */     
/* 119 */     addWindowListener(new ThumbnailListener());
/*     */ 
/*     */     
/* 122 */     this.svgCanvas = svgCanvas;
/* 123 */     svgCanvas.addGVTTreeRendererListener((GVTTreeRendererListener)new ThumbnailGVTListener());
/* 124 */     svgCanvas.addSVGDocumentLoaderListener((SVGDocumentLoaderListener)new ThumbnailDocumentListener());
/* 125 */     svgCanvas.addComponentListener(new ThumbnailCanvasComponentListener());
/*     */ 
/*     */     
/* 128 */     this.svgThumbnailCanvas = new JGVTComponent();
/* 129 */     this.overlay = new AreaOfInterestOverlay();
/* 130 */     this.svgThumbnailCanvas.getOverlays().add(this.overlay);
/* 131 */     this.svgThumbnailCanvas.setPreferredSize(new Dimension(150, 150));
/* 132 */     this.svgThumbnailCanvas.addComponentListener(new ThumbnailComponentListener());
/* 133 */     this.aoiListener = new AreaOfInterestListener();
/* 134 */     this.svgThumbnailCanvas.addMouseListener(this.aoiListener);
/* 135 */     this.svgThumbnailCanvas.addMouseMotionListener(this.aoiListener);
/* 136 */     getContentPane().add((Component)this.svgThumbnailCanvas, "Center");
/*     */   }
/*     */   
/*     */   public void setInteractionEnabled(boolean b) {
/* 140 */     if (b == this.interactionEnabled)
/* 141 */       return;  this.interactionEnabled = b;
/* 142 */     if (b) {
/* 143 */       this.svgThumbnailCanvas.addMouseListener(this.aoiListener);
/* 144 */       this.svgThumbnailCanvas.addMouseMotionListener(this.aoiListener);
/*     */     } else {
/* 146 */       this.svgThumbnailCanvas.removeMouseListener(this.aoiListener);
/* 147 */       this.svgThumbnailCanvas.removeMouseMotionListener(this.aoiListener);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getInteractionEnabled() {
/* 152 */     return this.interactionEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateThumbnailGraphicsNode() {
/* 159 */     this.svgThumbnailCanvas.setGraphicsNode(this.svgCanvas.getGraphicsNode());
/* 160 */     updateThumbnailRenderingTransform();
/*     */   }
/*     */   
/*     */   protected CanvasGraphicsNode getCanvasGraphicsNode(GraphicsNode gn) {
/* 164 */     if (!(gn instanceof CompositeGraphicsNode))
/* 165 */       return null; 
/* 166 */     CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 167 */     List children = cgn.getChildren();
/* 168 */     if (children.size() == 0)
/* 169 */       return null; 
/* 170 */     gn = cgn.getChildren().get(0);
/* 171 */     if (!(gn instanceof CanvasGraphicsNode))
/* 172 */       return null; 
/* 173 */     return (CanvasGraphicsNode)gn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateThumbnailRenderingTransform() {
/* 180 */     SVGDocument svgDocument = this.svgCanvas.getSVGDocument();
/* 181 */     if (svgDocument != null) {
/* 182 */       AffineTransform Tx; SVGSVGElement elt = svgDocument.getRootElement();
/* 183 */       Dimension dim = this.svgThumbnailCanvas.getSize();
/*     */ 
/*     */ 
/*     */       
/* 187 */       String viewBox = elt.getAttributeNS(null, "viewBox");
/*     */ 
/*     */ 
/*     */       
/* 191 */       if (viewBox.length() != 0) {
/* 192 */         String aspectRatio = elt.getAttributeNS(null, "preserveAspectRatio");
/*     */         
/* 194 */         Tx = ViewBox.getPreserveAspectRatioTransform((Element)elt, viewBox, aspectRatio, dim.width, dim.height, null);
/*     */       }
/*     */       else {
/*     */         
/* 198 */         Dimension2D docSize = this.svgCanvas.getSVGDocumentSize();
/* 199 */         double sx = dim.width / docSize.getWidth();
/* 200 */         double sy = dim.height / docSize.getHeight();
/* 201 */         double s = Math.min(sx, sy);
/* 202 */         Tx = AffineTransform.getScaleInstance(s, s);
/*     */       } 
/*     */       
/* 205 */       GraphicsNode gn = this.svgCanvas.getGraphicsNode();
/* 206 */       CanvasGraphicsNode cgn = getCanvasGraphicsNode(gn);
/* 207 */       if (cgn != null) {
/* 208 */         AffineTransform vTx = cgn.getViewingTransform();
/* 209 */         if (vTx != null && !vTx.isIdentity()) {
/*     */           try {
/* 211 */             AffineTransform invVTx = vTx.createInverse();
/* 212 */             Tx.concatenate(invVTx);
/* 213 */           } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 219 */       this.svgThumbnailCanvas.setRenderingTransform(Tx);
/* 220 */       this.overlay.synchronizeAreaOfInterest();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ThumbnailDocumentListener
/*     */     extends SVGDocumentLoaderAdapter
/*     */   {
/*     */     public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
/* 231 */       ThumbnailDialog.this.documentChanged = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class AreaOfInterestListener
/*     */     extends MouseInputAdapter
/*     */   {
/*     */     protected int sx;
/*     */     protected int sy;
/*     */     protected boolean in;
/*     */     
/*     */     public void mousePressed(MouseEvent evt) {
/* 244 */       this.sx = evt.getX();
/* 245 */       this.sy = evt.getY();
/* 246 */       this.in = ThumbnailDialog.this.overlay.contains(this.sx, this.sy);
/* 247 */       ThumbnailDialog.this.overlay.setPaintingTransform(new AffineTransform());
/*     */     }
/*     */     
/*     */     public void mouseDragged(MouseEvent evt) {
/* 251 */       if (this.in) {
/* 252 */         int dx = evt.getX() - this.sx;
/* 253 */         int dy = evt.getY() - this.sy;
/* 254 */         ThumbnailDialog.this.overlay.setPaintingTransform(AffineTransform.getTranslateInstance(dx, dy));
/*     */         
/* 256 */         ThumbnailDialog.this.svgThumbnailCanvas.repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseReleased(MouseEvent evt) {
/* 261 */       if (this.in) {
/* 262 */         this.in = false;
/*     */         
/* 264 */         int dx = evt.getX() - this.sx;
/* 265 */         int dy = evt.getY() - this.sy;
/* 266 */         AffineTransform at = ThumbnailDialog.this.overlay.getOverlayTransform();
/* 267 */         Point2D pt0 = new Point2D.Float(0.0F, 0.0F);
/* 268 */         Point2D pt = new Point2D.Float(dx, dy);
/*     */         
/*     */         try {
/* 271 */           at.inverseTransform(pt0, pt0);
/* 272 */           at.inverseTransform(pt, pt);
/* 273 */           double tx = pt0.getX() - pt.getX();
/* 274 */           double ty = pt0.getY() - pt.getY();
/* 275 */           at = ThumbnailDialog.this.svgCanvas.getRenderingTransform();
/* 276 */           at.preConcatenate(AffineTransform.getTranslateInstance(tx, ty));
/*     */           
/* 278 */           ThumbnailDialog.this.svgCanvas.setRenderingTransform(at);
/* 279 */         } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ThumbnailGVTListener
/*     */     extends GVTTreeRendererAdapter
/*     */   {
/*     */     public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/* 290 */       if (ThumbnailDialog.this.documentChanged) {
/* 291 */         ThumbnailDialog.this.updateThumbnailGraphicsNode();
/* 292 */         ThumbnailDialog.this.documentChanged = false;
/*     */       } else {
/* 294 */         ThumbnailDialog.this.overlay.synchronizeAreaOfInterest();
/* 295 */         ThumbnailDialog.this.svgThumbnailCanvas.repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void gvtRenderingCancelled(GVTTreeRendererEvent e) {
/* 300 */       if (ThumbnailDialog.this.documentChanged) {
/* 301 */         ThumbnailDialog.this.svgThumbnailCanvas.setGraphicsNode(null);
/* 302 */         ThumbnailDialog.this.svgThumbnailCanvas.setRenderingTransform(new AffineTransform());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void gvtRenderingFailed(GVTTreeRendererEvent e) {
/* 307 */       if (ThumbnailDialog.this.documentChanged) {
/* 308 */         ThumbnailDialog.this.svgThumbnailCanvas.setGraphicsNode(null);
/* 309 */         ThumbnailDialog.this.svgThumbnailCanvas.setRenderingTransform(new AffineTransform());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ThumbnailListener
/*     */     extends WindowAdapter
/*     */   {
/*     */     public void windowOpened(WindowEvent evt) {
/* 321 */       ThumbnailDialog.this.updateThumbnailGraphicsNode();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ThumbnailComponentListener
/*     */     extends ComponentAdapter
/*     */   {
/*     */     public void componentResized(ComponentEvent e) {
/* 332 */       ThumbnailDialog.this.updateThumbnailRenderingTransform();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ThumbnailCanvasComponentListener
/*     */     extends ComponentAdapter
/*     */   {
/*     */     public void componentResized(ComponentEvent e) {
/* 343 */       ThumbnailDialog.this.updateThumbnailRenderingTransform();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class AreaOfInterestOverlay
/*     */     implements Overlay
/*     */   {
/*     */     protected Shape s;
/*     */     
/*     */     protected AffineTransform at;
/* 354 */     protected AffineTransform paintingTransform = new AffineTransform();
/*     */     
/*     */     public boolean contains(int x, int y) {
/* 357 */       return (this.s != null) ? this.s.contains(x, y) : false;
/*     */     }
/*     */     
/*     */     public AffineTransform getOverlayTransform() {
/* 361 */       return this.at;
/*     */     }
/*     */     
/*     */     public void setPaintingTransform(AffineTransform rt) {
/* 365 */       this.paintingTransform = rt;
/*     */     }
/*     */     
/*     */     public AffineTransform getPaintingTransform() {
/* 369 */       return this.paintingTransform;
/*     */     }
/*     */     
/*     */     public void synchronizeAreaOfInterest() {
/* 373 */       this.paintingTransform = new AffineTransform();
/* 374 */       Dimension dim = ThumbnailDialog.this.svgCanvas.getSize();
/* 375 */       this.s = new Rectangle2D.Float(0.0F, 0.0F, dim.width, dim.height);
/*     */       try {
/* 377 */         this.at = ThumbnailDialog.this.svgCanvas.getRenderingTransform().createInverse();
/* 378 */         this.at.preConcatenate(ThumbnailDialog.this.svgThumbnailCanvas.getRenderingTransform());
/* 379 */         this.s = this.at.createTransformedShape(this.s);
/* 380 */       } catch (NoninvertibleTransformException ex) {
/* 381 */         dim = ThumbnailDialog.this.svgThumbnailCanvas.getSize();
/* 382 */         this.s = new Rectangle2D.Float(0.0F, 0.0F, dim.width, dim.height);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void paint(Graphics g) {
/* 387 */       if (this.s != null) {
/* 388 */         Graphics2D g2d = (Graphics2D)g;
/* 389 */         g2d.transform(this.paintingTransform);
/* 390 */         g2d.setColor(new Color(255, 255, 255, 128));
/* 391 */         g2d.fill(this.s);
/* 392 */         g2d.setColor(Color.black);
/* 393 */         g2d.setStroke(new BasicStroke());
/* 394 */         g2d.draw(this.s);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/ThumbnailDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */