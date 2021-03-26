/*      */ package org.apache.batik.bridge;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.color.ICC_Profile;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InterruptedIOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*      */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*      */ import org.apache.batik.anim.dom.SVGOMAnimatedPreserveAspectRatio;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.anim.dom.SVGOMElement;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.dom.AbstractNode;
/*      */ import org.apache.batik.dom.events.DOMMouseEvent;
/*      */ import org.apache.batik.dom.events.NodeEventTarget;
/*      */ import org.apache.batik.dom.svg.LiveAttributeException;
/*      */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*      */ import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
/*      */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*      */ import org.apache.batik.ext.awt.image.spi.BrokenLinkProvider;
/*      */ import org.apache.batik.ext.awt.image.spi.ImageTagRegistry;
/*      */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*      */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.gvt.ImageNode;
/*      */ import org.apache.batik.gvt.RasterImageNode;
/*      */ import org.apache.batik.gvt.ShapeNode;
/*      */ import org.apache.batik.util.HaltingThread;
/*      */ import org.apache.batik.util.MimeTypeConstants;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
/*      */ import org.apache.xmlgraphics.java2d.color.RenderingIntent;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.DocumentEvent;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*      */ import org.w3c.dom.svg.SVGDocument;
/*      */ import org.w3c.dom.svg.SVGImageElement;
/*      */ import org.w3c.dom.svg.SVGSVGElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGImageElementBridge
/*      */   extends AbstractGraphicsNodeBridge
/*      */ {
/*      */   protected SVGDocument imgDocument;
/*   82 */   protected EventListener listener = null;
/*   83 */   protected BridgeContext subCtx = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hitCheckChildren = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName() {
/*   94 */     return "image";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bridge getInstance() {
/*  101 */     return new SVGImageElementBridge();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  113 */     ImageNode imageNode = (ImageNode)super.createGraphicsNode(ctx, e);
/*  114 */     if (imageNode == null) {
/*  115 */       return null;
/*      */     }
/*      */     
/*  118 */     associateSVGContext(ctx, e, (GraphicsNode)imageNode);
/*      */     
/*  120 */     this.hitCheckChildren = false;
/*  121 */     GraphicsNode node = buildImageGraphicsNode(ctx, e);
/*      */     
/*  123 */     if (node == null) {
/*  124 */       SVGImageElement ie = (SVGImageElement)e;
/*  125 */       String uriStr = ie.getHref().getAnimVal();
/*  126 */       throw new BridgeException(ctx, e, "uri.image.invalid", new Object[] { uriStr });
/*      */     } 
/*      */ 
/*      */     
/*  130 */     imageNode.setImage(node);
/*  131 */     imageNode.setHitCheckChildren(this.hitCheckChildren);
/*      */ 
/*      */     
/*  134 */     RenderingHints hints = null;
/*  135 */     hints = CSSUtilities.convertImageRendering(e, hints);
/*  136 */     hints = CSSUtilities.convertColorRendering(e, hints);
/*  137 */     if (hints != null) {
/*  138 */       imageNode.setRenderingHints(hints);
/*      */     }
/*  140 */     return (GraphicsNode)imageNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode buildImageGraphicsNode(BridgeContext ctx, Element e) {
/*      */     ParsedURL purl;
/*  157 */     SVGImageElement ie = (SVGImageElement)e;
/*      */ 
/*      */     
/*  160 */     String uriStr = ie.getHref().getAnimVal();
/*  161 */     if (uriStr.length() == 0) {
/*  162 */       throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "xlink:href" });
/*      */     }
/*      */     
/*  165 */     if (uriStr.indexOf('#') != -1) {
/*  166 */       throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "xlink:href", uriStr });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  171 */     String baseURI = AbstractNode.getBaseURI(e);
/*      */     
/*  173 */     if (baseURI == null) {
/*  174 */       purl = new ParsedURL(uriStr);
/*      */     } else {
/*  176 */       purl = new ParsedURL(baseURI, uriStr);
/*      */     } 
/*      */     
/*  179 */     return createImageGraphicsNode(ctx, e, purl);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode createImageGraphicsNode(BridgeContext ctx, Element e, ParsedURL purl) {
/*  185 */     Rectangle2D bounds = getImageBounds(ctx, e);
/*  186 */     if (bounds.getWidth() == 0.0D || bounds.getHeight() == 0.0D) {
/*  187 */       ShapeNode sn = new ShapeNode();
/*  188 */       sn.setShape(bounds);
/*  189 */       return (GraphicsNode)sn;
/*      */     } 
/*      */     
/*  192 */     SVGDocument svgDoc = (SVGDocument)e.getOwnerDocument();
/*  193 */     String docURL = svgDoc.getURL();
/*  194 */     ParsedURL pDocURL = null;
/*  195 */     if (docURL != null) {
/*  196 */       pDocURL = new ParsedURL(docURL);
/*      */     }
/*  198 */     UserAgent userAgent = ctx.getUserAgent();
/*      */     
/*      */     try {
/*  201 */       userAgent.checkLoadExternalResource(purl, pDocURL);
/*  202 */     } catch (SecurityException secEx) {
/*  203 */       throw new BridgeException(ctx, e, secEx, "uri.unsecure", new Object[] { purl });
/*      */     } 
/*      */ 
/*      */     
/*  207 */     DocumentLoader loader = ctx.getDocumentLoader();
/*  208 */     ImageTagRegistry reg = ImageTagRegistry.getRegistry();
/*  209 */     ICCColorSpaceWithIntent colorspace = extractColorSpace(e, ctx);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  217 */       Document doc = loader.checkCache(purl.toString());
/*  218 */       if (doc != null) {
/*  219 */         this.imgDocument = (SVGDocument)doc;
/*  220 */         return createSVGImageNode(ctx, e, this.imgDocument);
/*      */       } 
/*  222 */     } catch (BridgeException ex) {
/*  223 */       throw ex;
/*  224 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  229 */     Filter img = reg.checkCache(purl, colorspace);
/*  230 */     if (img != null) {
/*  231 */       return createRasterImageNode(ctx, e, img, purl);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  242 */     ProtectedStream reference = null;
/*      */     try {
/*  244 */       reference = openStream(e, purl);
/*  245 */     } catch (SecurityException secEx) {
/*  246 */       throw new BridgeException(ctx, e, secEx, "uri.unsecure", new Object[] { purl });
/*      */     }
/*  248 */     catch (IOException ioe) {
/*  249 */       return createBrokenImageNode(ctx, e, purl.toString(), ioe.getLocalizedMessage());
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  260 */     Filter filter1 = reg.readURL(reference, purl, colorspace, false, false);
/*      */     
/*  262 */     if (filter1 != null) {
/*      */       try {
/*  264 */         reference.tie();
/*  265 */       } catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */       
/*  269 */       return createRasterImageNode(ctx, e, filter1, purl);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  275 */       reference.retry();
/*  276 */     } catch (IOException ioe) {
/*  277 */       reference.release();
/*  278 */       reference = null;
/*      */       
/*      */       try {
/*  281 */         reference = openStream(e, purl);
/*  282 */       } catch (IOException ioe2) {
/*      */         
/*  284 */         return createBrokenImageNode(ctx, e, purl.toString(), ioe2.getLocalizedMessage());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  293 */       Document doc = loader.loadDocument(purl.toString(), reference);
/*  294 */       reference.release();
/*  295 */       this.imgDocument = (SVGDocument)doc;
/*  296 */       return createSVGImageNode(ctx, e, this.imgDocument);
/*  297 */     } catch (BridgeException ex) {
/*  298 */       reference.release();
/*  299 */       throw ex;
/*  300 */     } catch (SecurityException secEx) {
/*  301 */       reference.release();
/*  302 */       throw new BridgeException(ctx, e, secEx, "uri.unsecure", new Object[] { purl });
/*      */     }
/*  304 */     catch (InterruptedIOException iioe) {
/*  305 */       reference.release();
/*  306 */       if (HaltingThread.hasBeenHalted()) {
/*  307 */         throw new InterruptedBridgeException();
/*      */       }
/*  309 */     } catch (InterruptedBridgeException ibe) {
/*  310 */       reference.release();
/*  311 */       throw ibe;
/*  312 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  318 */       reference.retry();
/*  319 */     } catch (IOException ioe) {
/*  320 */       reference.release();
/*  321 */       reference = null;
/*      */       
/*      */       try {
/*  324 */         reference = openStream(e, purl);
/*  325 */       } catch (IOException ioe2) {
/*  326 */         return createBrokenImageNode(ctx, e, purl.toString(), ioe2.getLocalizedMessage());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  335 */       filter1 = reg.readURL(reference, purl, colorspace, true, true);
/*      */       
/*  337 */       if (filter1 != null)
/*      */       {
/*  339 */         return createRasterImageNode(ctx, e, filter1, purl);
/*      */       }
/*      */     } finally {
/*  342 */       reference.release();
/*      */     } 
/*  344 */     return null;
/*      */   }
/*      */   public static class ProtectedStream extends BufferedInputStream { static final int BUFFER_SIZE = 8192;
/*      */     boolean wasClosed;
/*      */     boolean isTied;
/*      */     
/*  350 */     ProtectedStream(InputStream is) { super(is, 8192);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  405 */       this.wasClosed = false;
/*  406 */       this.isTied = false; super.mark(8192); } public boolean markSupported() { return false; } public void mark(int sz) {} public void reset() throws IOException { throw new IOException("Reset unsupported"); } public synchronized void retry() throws IOException { super.reset(); this.wasClosed = false; this.isTied = false; } ProtectedStream(InputStream is, int size) { super(is, size); this.wasClosed = false; this.isTied = false; super.mark(size); }
/*      */     public synchronized void close() throws IOException { this.wasClosed = true; if (this.isTied)
/*      */         super.close();  }
/*      */     public synchronized void tie() throws IOException { this.isTied = true; if (this.wasClosed)
/*      */         super.close();  } public void release() { try { super.close(); } catch (IOException iOException) {} } }
/*  411 */    protected ProtectedStream openStream(Element e, ParsedURL purl) throws IOException { List mimeTypes = new ArrayList(ImageTagRegistry.getRegistry().getRegisteredMimeTypes());
/*      */     
/*  413 */     mimeTypes.addAll(MimeTypeConstants.MIME_TYPES_SVG_LIST);
/*  414 */     InputStream reference = purl.openStream(mimeTypes.iterator());
/*  415 */     return new ProtectedStream(reference); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode instantiateGraphicsNode() {
/*  422 */     return (GraphicsNode)new ImageNode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isComposite() {
/*  429 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeDynamicSupport(BridgeContext ctx, Element e, GraphicsNode node) {
/*  443 */     if (!ctx.isInteractive()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  448 */     ctx.bind(e, node);
/*      */     
/*  450 */     if (ctx.isDynamic()) {
/*      */       
/*  452 */       this.e = e;
/*  453 */       this.node = node;
/*  454 */       this.ctx = ctx;
/*  455 */       ((SVGOMElement)e).setSVGContext(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/*      */     try {
/*  467 */       String ns = alav.getNamespaceURI();
/*  468 */       String ln = alav.getLocalName();
/*  469 */       if (ns == null) {
/*  470 */         if (ln.equals("x") || ln.equals("y")) {
/*      */           
/*  472 */           updateImageBounds(); return;
/*      */         } 
/*  474 */         if (ln.equals("width") || ln.equals("height")) {
/*      */           AbstractSVGAnimatedLength _attr;
/*  476 */           SVGImageElement ie = (SVGImageElement)this.e;
/*  477 */           ImageNode imageNode = (ImageNode)this.node;
/*      */           
/*  479 */           if (ln.charAt(0) == 'w') {
/*  480 */             _attr = (AbstractSVGAnimatedLength)ie.getWidth();
/*      */           } else {
/*  482 */             _attr = (AbstractSVGAnimatedLength)ie.getHeight();
/*      */           } 
/*  484 */           float val = _attr.getCheckedValue();
/*  485 */           if (val == 0.0F || imageNode.getImage() instanceof ShapeNode) {
/*  486 */             rebuildImageNode();
/*      */           } else {
/*  488 */             updateImageBounds();
/*      */           }  return;
/*      */         } 
/*  491 */         if (ln.equals("preserveAspectRatio")) {
/*  492 */           updateImageBounds();
/*      */           return;
/*      */         } 
/*  495 */       } else if (ns.equals("http://www.w3.org/1999/xlink") && ln.equals("href")) {
/*      */         
/*  497 */         rebuildImageNode();
/*      */         return;
/*      */       } 
/*  500 */     } catch (LiveAttributeException ex) {
/*  501 */       throw new BridgeException(this.ctx, ex);
/*      */     } 
/*  503 */     super.handleAnimatedAttributeChanged(alav);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateImageBounds() {
/*  508 */     Rectangle2D bounds = getImageBounds(this.ctx, this.e);
/*  509 */     GraphicsNode imageNode = ((ImageNode)this.node).getImage();
/*  510 */     float[] vb = null;
/*  511 */     if (imageNode instanceof RasterImageNode) {
/*      */       
/*  513 */       Rectangle2D imgBounds = ((RasterImageNode)imageNode).getImageBounds();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  518 */       vb = new float[4];
/*  519 */       vb[0] = 0.0F;
/*  520 */       vb[1] = 0.0F;
/*  521 */       vb[2] = (float)imgBounds.getWidth();
/*  522 */       vb[3] = (float)imgBounds.getHeight();
/*      */     }
/*  524 */     else if (this.imgDocument != null) {
/*  525 */       SVGSVGElement sVGSVGElement = this.imgDocument.getRootElement();
/*  526 */       String viewBox = sVGSVGElement.getAttributeNS(null, "viewBox");
/*      */       
/*  528 */       vb = ViewBox.parseViewBoxAttribute(this.e, viewBox, this.ctx);
/*      */     } 
/*      */     
/*  531 */     if (imageNode != null)
/*      */     {
/*      */ 
/*      */       
/*  535 */       initializeViewport(this.ctx, this.e, imageNode, vb, bounds);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void rebuildImageNode() {
/*  542 */     if (this.imgDocument != null && this.listener != null) {
/*  543 */       NodeEventTarget tgt = (NodeEventTarget)this.imgDocument.getRootElement();
/*      */       
/*  545 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.listener, false);
/*      */ 
/*      */       
/*  548 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this.listener, false);
/*      */ 
/*      */       
/*  551 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keypress", this.listener, false);
/*      */ 
/*      */       
/*  554 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keyup", this.listener, false);
/*      */ 
/*      */       
/*  557 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousedown", this.listener, false);
/*      */ 
/*      */       
/*  560 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousemove", this.listener, false);
/*      */ 
/*      */       
/*  563 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.listener, false);
/*      */ 
/*      */       
/*  566 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.listener, false);
/*      */ 
/*      */       
/*  569 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseup", this.listener, false);
/*      */ 
/*      */       
/*  572 */       this.listener = null;
/*      */     } 
/*      */     
/*  575 */     if (this.imgDocument != null) {
/*  576 */       SVGSVGElement svgElement = this.imgDocument.getRootElement();
/*  577 */       disposeTree((Node)svgElement);
/*      */     } 
/*      */     
/*  580 */     this.imgDocument = null;
/*  581 */     this.subCtx = null;
/*      */ 
/*      */     
/*  584 */     GraphicsNode inode = buildImageGraphicsNode(this.ctx, this.e);
/*      */     
/*  586 */     ImageNode imgNode = (ImageNode)this.node;
/*  587 */     imgNode.setImage(inode);
/*      */     
/*  589 */     if (inode == null) {
/*  590 */       SVGImageElement ie = (SVGImageElement)this.e;
/*  591 */       String uriStr = ie.getHref().getAnimVal();
/*  592 */       throw new BridgeException(this.ctx, this.e, "uri.image.invalid", new Object[] { uriStr });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleCSSPropertyChanged(int property) {
/*      */     RenderingHints hints;
/*  601 */     switch (property) {
/*      */       case 6:
/*      */       case 30:
/*  604 */         hints = CSSUtilities.convertImageRendering(this.e, null);
/*  605 */         hints = CSSUtilities.convertColorRendering(this.e, hints);
/*  606 */         if (hints != null) {
/*  607 */           this.node.setRenderingHints(hints);
/*      */         }
/*      */         return;
/*      */     } 
/*  611 */     super.handleCSSPropertyChanged(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode createRasterImageNode(BridgeContext ctx, Element e, Filter img, ParsedURL purl) {
/*  629 */     Rectangle2D bounds = getImageBounds(ctx, e);
/*  630 */     if (bounds.getWidth() == 0.0D || bounds.getHeight() == 0.0D) {
/*  631 */       ShapeNode sn = new ShapeNode();
/*  632 */       sn.setShape(bounds);
/*  633 */       return (GraphicsNode)sn;
/*      */     } 
/*      */     
/*  636 */     if (BrokenLinkProvider.hasBrokenLinkProperty(img)) {
/*  637 */       Object o = img.getProperty("org.apache.batik.BrokenLinkImage");
/*  638 */       String msg = "unknown";
/*  639 */       if (o instanceof String)
/*  640 */         msg = (String)o; 
/*  641 */       SVGDocument doc = ctx.getUserAgent().getBrokenLinkDocument(e, purl.toString(), msg);
/*      */       
/*  643 */       return createSVGImageNode(ctx, e, doc);
/*      */     } 
/*      */     
/*  646 */     RasterImageNode node = new RasterImageNode();
/*  647 */     node.setImage(img);
/*  648 */     Rectangle2D imgBounds = img.getBounds2D();
/*      */ 
/*      */ 
/*      */     
/*  652 */     float[] vb = new float[4];
/*  653 */     vb[0] = 0.0F;
/*  654 */     vb[1] = 0.0F;
/*  655 */     vb[2] = (float)imgBounds.getWidth();
/*  656 */     vb[3] = (float)imgBounds.getHeight();
/*      */ 
/*      */ 
/*      */     
/*  660 */     initializeViewport(ctx, e, (GraphicsNode)node, vb, bounds);
/*      */     
/*  662 */     return (GraphicsNode)node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GraphicsNode createSVGImageNode(BridgeContext ctx, Element e, SVGDocument imgDocument) {
/*  675 */     CSSEngine eng = ((SVGOMDocument)imgDocument).getCSSEngine();
/*  676 */     this.subCtx = ctx.createSubBridgeContext((SVGOMDocument)imgDocument);
/*      */     
/*  678 */     CompositeGraphicsNode result = new CompositeGraphicsNode();
/*      */ 
/*      */     
/*  681 */     Rectangle2D bounds = getImageBounds(ctx, e);
/*      */     
/*  683 */     if (bounds.getWidth() == 0.0D || bounds.getHeight() == 0.0D) {
/*  684 */       ShapeNode sn = new ShapeNode();
/*  685 */       sn.setShape(bounds);
/*  686 */       result.getChildren().add(sn);
/*  687 */       return (GraphicsNode)result;
/*      */     } 
/*      */     
/*  690 */     Rectangle2D r = CSSUtilities.convertEnableBackground(e);
/*  691 */     if (r != null) {
/*  692 */       result.setBackgroundEnable(r);
/*      */     }
/*      */     
/*  695 */     SVGSVGElement svgElement = imgDocument.getRootElement();
/*      */     
/*  697 */     CanvasGraphicsNode node = (CanvasGraphicsNode)this.subCtx.getGVTBuilder().build(this.subCtx, (Element)svgElement);
/*      */ 
/*      */     
/*  700 */     if (eng == null && ctx.isInteractive())
/*      */     {
/*  702 */       this.subCtx.addUIEventListeners((Document)imgDocument);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  708 */     node.setClip(null);
/*      */ 
/*      */ 
/*      */     
/*  712 */     node.setViewingTransform(new AffineTransform());
/*  713 */     result.getChildren().add(node);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  718 */     String viewBox = svgElement.getAttributeNS(null, "viewBox");
/*      */     
/*  720 */     float[] vb = ViewBox.parseViewBoxAttribute(e, viewBox, ctx);
/*      */     
/*  722 */     initializeViewport(ctx, e, (GraphicsNode)result, vb, bounds);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  727 */     if (ctx.isInteractive()) {
/*  728 */       this.listener = new ForwardEventListener((Element)svgElement, e);
/*  729 */       NodeEventTarget tgt = (NodeEventTarget)svgElement;
/*      */       
/*  731 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.listener, false, null);
/*      */ 
/*      */       
/*  734 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "click", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  738 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this.listener, false, null);
/*      */ 
/*      */       
/*  741 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "keydown", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  745 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "keypress", this.listener, false, null);
/*      */ 
/*      */       
/*  748 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "keypress", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  752 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "keyup", this.listener, false, null);
/*      */ 
/*      */       
/*  755 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "keyup", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  759 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "mousedown", this.listener, false, null);
/*      */ 
/*      */       
/*  762 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "mousedown", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  766 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "mousemove", this.listener, false, null);
/*      */ 
/*      */       
/*  769 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "mousemove", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  773 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.listener, false, null);
/*      */ 
/*      */       
/*  776 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "mouseout", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  780 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.listener, false, null);
/*      */ 
/*      */       
/*  783 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "mouseover", this.listener, false);
/*      */ 
/*      */ 
/*      */       
/*  787 */       tgt.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseup", this.listener, false, null);
/*      */ 
/*      */       
/*  790 */       this.subCtx.storeEventListenerNS((EventTarget)tgt, "http://www.w3.org/2001/xml-events", "mouseup", this.listener, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  795 */     return (GraphicsNode)result;
/*      */   }
/*      */   
/*      */   public void dispose() {
/*  799 */     if (this.imgDocument != null && this.listener != null) {
/*  800 */       NodeEventTarget tgt = (NodeEventTarget)this.imgDocument.getRootElement();
/*      */       
/*  802 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.listener, false);
/*      */ 
/*      */       
/*  805 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this.listener, false);
/*      */ 
/*      */       
/*  808 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keypress", this.listener, false);
/*      */ 
/*      */       
/*  811 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keyup", this.listener, false);
/*      */ 
/*      */       
/*  814 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousedown", this.listener, false);
/*      */ 
/*      */       
/*  817 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mousemove", this.listener, false);
/*      */ 
/*      */       
/*  820 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.listener, false);
/*      */ 
/*      */       
/*  823 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.listener, false);
/*      */ 
/*      */       
/*  826 */       tgt.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseup", this.listener, false);
/*      */ 
/*      */       
/*  829 */       this.listener = null;
/*      */     } 
/*      */     
/*  832 */     if (this.imgDocument != null) {
/*  833 */       SVGSVGElement svgElement = this.imgDocument.getRootElement();
/*  834 */       disposeTree((Node)svgElement);
/*  835 */       this.imgDocument = null;
/*  836 */       this.subCtx = null;
/*      */     } 
/*  838 */     super.dispose();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ForwardEventListener
/*      */     implements EventListener
/*      */   {
/*      */     protected Element svgElement;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Element imgElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ForwardEventListener(Element svgElement, Element imgElement) {
/*  861 */       this.svgElement = svgElement;
/*  862 */       this.imgElement = imgElement;
/*      */     }
/*      */     
/*      */     public void handleEvent(Event e) {
/*  866 */       DOMMouseEvent evt = (DOMMouseEvent)e;
/*  867 */       DOMMouseEvent newMouseEvent = (DOMMouseEvent)((DocumentEvent)this.imgElement.getOwnerDocument()).createEvent("MouseEvents");
/*      */ 
/*      */ 
/*      */       
/*  871 */       newMouseEvent.initMouseEventNS("http://www.w3.org/2001/xml-events", evt.getType(), evt.getBubbles(), evt.getCancelable(), evt.getView(), evt.getDetail(), evt.getScreenX(), evt.getScreenY(), evt.getClientX(), evt.getClientY(), evt.getButton(), (EventTarget)this.imgElement, evt.getModifiersString());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  885 */       ((EventTarget)this.imgElement).dispatchEvent((Event)newMouseEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void initializeViewport(BridgeContext ctx, Element e, GraphicsNode node, float[] vb, Rectangle2D bounds) {
/*  907 */     float x = (float)bounds.getX();
/*  908 */     float y = (float)bounds.getY();
/*  909 */     float w = (float)bounds.getWidth();
/*  910 */     float h = (float)bounds.getHeight();
/*      */     
/*      */     try {
/*  913 */       SVGImageElement ie = (SVGImageElement)e;
/*  914 */       SVGOMAnimatedPreserveAspectRatio _par = (SVGOMAnimatedPreserveAspectRatio)ie.getPreserveAspectRatio();
/*      */       
/*  916 */       _par.check();
/*      */       
/*  918 */       AffineTransform at = ViewBox.getPreserveAspectRatioTransform(e, vb, w, h, (SVGAnimatedPreserveAspectRatio)_par, ctx);
/*      */       
/*  920 */       at.preConcatenate(AffineTransform.getTranslateInstance(x, y));
/*  921 */       node.setTransform(at);
/*      */ 
/*      */       
/*  924 */       Shape clip = null;
/*  925 */       if (CSSUtilities.convertOverflow(e)) {
/*  926 */         float[] offsets = CSSUtilities.convertClip(e);
/*  927 */         if (offsets == null) {
/*  928 */           clip = new Rectangle2D.Float(x, y, w, h);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  934 */           clip = new Rectangle2D.Float(x + offsets[3], y + offsets[0], w - offsets[1] - offsets[3], h - offsets[2] - offsets[0]);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  941 */       if (clip != null) {
/*      */         try {
/*  943 */           at = at.createInverse();
/*  944 */           Filter filter = node.getGraphicsNodeRable(true);
/*  945 */           clip = at.createTransformedShape(clip);
/*  946 */           node.setClip((ClipRable)new ClipRable8Bit(filter, clip));
/*  947 */         } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*      */       }
/*  949 */     } catch (LiveAttributeException ex) {
/*  950 */       throw new BridgeException(ctx, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static ICCColorSpaceWithIntent extractColorSpace(Element element, BridgeContext ctx) {
/*  964 */     String colorProfileProperty = CSSUtilities.getComputedStyle(element, 8).getStringValue();
/*      */ 
/*      */ 
/*      */     
/*  968 */     ICCColorSpaceWithIntent colorSpace = null;
/*  969 */     if ("srgb".equalsIgnoreCase(colorProfileProperty)) {
/*      */       
/*  971 */       colorSpace = new ICCColorSpaceWithIntent(ICC_Profile.getInstance(1000), RenderingIntent.AUTO, "sRGB", null);
/*      */ 
/*      */     
/*      */     }
/*  975 */     else if (!"auto".equalsIgnoreCase(colorProfileProperty) && !"".equalsIgnoreCase(colorProfileProperty)) {
/*      */ 
/*      */ 
/*      */       
/*  979 */       SVGColorProfileElementBridge profileBridge = (SVGColorProfileElementBridge)ctx.getBridge("http://www.w3.org/2000/svg", "color-profile");
/*      */ 
/*      */       
/*  982 */       if (profileBridge != null) {
/*  983 */         colorSpace = profileBridge.createICCColorSpaceWithIntent(ctx, element, colorProfileProperty);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  988 */     return colorSpace;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Rectangle2D getImageBounds(BridgeContext ctx, Element element) {
/*      */     try {
/* 1000 */       SVGImageElement ie = (SVGImageElement)element;
/*      */ 
/*      */       
/* 1003 */       AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)ie.getX();
/*      */       
/* 1005 */       float x = _x.getCheckedValue();
/*      */ 
/*      */       
/* 1008 */       AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)ie.getY();
/*      */       
/* 1010 */       float y = _y.getCheckedValue();
/*      */ 
/*      */       
/* 1013 */       AbstractSVGAnimatedLength _width = (AbstractSVGAnimatedLength)ie.getWidth();
/*      */       
/* 1015 */       float w = _width.getCheckedValue();
/*      */ 
/*      */       
/* 1018 */       AbstractSVGAnimatedLength _height = (AbstractSVGAnimatedLength)ie.getHeight();
/*      */       
/* 1020 */       float h = _height.getCheckedValue();
/*      */       
/* 1022 */       return new Rectangle2D.Float(x, y, w, h);
/* 1023 */     } catch (LiveAttributeException ex) {
/* 1024 */       throw new BridgeException(ctx, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   GraphicsNode createBrokenImageNode(BridgeContext ctx, Element e, String uri, String message) {
/* 1030 */     SVGDocument doc = ctx.getUserAgent().getBrokenLinkDocument(e, uri, Messages.formatMessage("uri.image.error", new Object[] { message }));
/*      */ 
/*      */     
/* 1033 */     return createSVGImageNode(ctx, e, doc);
/*      */   }
/*      */ 
/*      */   
/* 1037 */   static SVGBrokenLinkProvider brokenLinkProvider = new SVGBrokenLinkProvider();
/*      */   
/*      */   static {
/* 1040 */     ImageTagRegistry.setBrokenLinkProvider((BrokenLinkProvider)brokenLinkProvider);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGImageElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */