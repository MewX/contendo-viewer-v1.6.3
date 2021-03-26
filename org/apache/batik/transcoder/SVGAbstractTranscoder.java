/*      */ package org.apache.batik.transcoder;
/*      */ 
/*      */ import java.awt.Dimension;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Dimension2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*      */ import org.apache.batik.anim.dom.SVGDOMImplementation;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.bridge.BaseScriptingEnvironment;
/*      */ import org.apache.batik.bridge.BridgeContext;
/*      */ import org.apache.batik.bridge.BridgeException;
/*      */ import org.apache.batik.bridge.DefaultScriptSecurity;
/*      */ import org.apache.batik.bridge.GVTBuilder;
/*      */ import org.apache.batik.bridge.NoLoadScriptSecurity;
/*      */ import org.apache.batik.bridge.RelaxedScriptSecurity;
/*      */ import org.apache.batik.bridge.SVGUtilities;
/*      */ import org.apache.batik.bridge.ScriptSecurity;
/*      */ import org.apache.batik.bridge.UserAgent;
/*      */ import org.apache.batik.bridge.UserAgentAdapter;
/*      */ import org.apache.batik.bridge.ViewBox;
/*      */ import org.apache.batik.bridge.svg12.SVG12BridgeContext;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.util.DocumentFactory;
/*      */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*      */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*      */ import org.apache.batik.gvt.GraphicsNode;
/*      */ import org.apache.batik.transcoder.keys.BooleanKey;
/*      */ import org.apache.batik.transcoder.keys.FloatKey;
/*      */ import org.apache.batik.transcoder.keys.LengthKey;
/*      */ import org.apache.batik.transcoder.keys.Rectangle2DKey;
/*      */ import org.apache.batik.transcoder.keys.StringKey;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
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
/*      */ public abstract class SVGAbstractTranscoder
/*      */   extends XMLAbstractTranscoder
/*      */ {
/*      */   public static final String DEFAULT_DEFAULT_FONT_FAMILY = "Arial, Helvetica, sans-serif";
/*      */   protected Rectangle2D curAOI;
/*      */   protected AffineTransform curTxf;
/*      */   protected GraphicsNode root;
/*      */   protected BridgeContext ctx;
/*      */   protected GVTBuilder builder;
/*  111 */   protected float width = 400.0F; protected float height = 400.0F;
/*      */   
/*      */   protected UserAgent userAgent;
/*      */ 
/*      */   
/*      */   protected SVGAbstractTranscoder() {
/*  117 */     this.userAgent = createUserAgent();
/*      */     
/*  119 */     this.hints.put(KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, "http://www.w3.org/2000/svg");
/*      */     
/*  121 */     this.hints.put(KEY_DOCUMENT_ELEMENT, "svg");
/*      */     
/*  123 */     this.hints.put(KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
/*      */     
/*  125 */     this.hints.put(KEY_MEDIA, "screen");
/*      */     
/*  127 */     this.hints.put(KEY_DEFAULT_FONT_FAMILY, "Arial, Helvetica, sans-serif");
/*      */     
/*  129 */     this.hints.put(KEY_EXECUTE_ONLOAD, Boolean.FALSE);
/*      */     
/*  131 */     this.hints.put(KEY_ALLOWED_SCRIPT_TYPES, "text/ecmascript, application/ecmascript, text/javascript, application/javascript, application/java-archive");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected UserAgent createUserAgent() {
/*  137 */     return (UserAgent)new SVGAbstractTranscoderUserAgent();
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
/*      */   protected DocumentFactory createDocumentFactory(DOMImplementation domImpl, String parserClassname) {
/*  150 */     return (DocumentFactory)new SAXSVGDocumentFactory(parserClassname);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void transcode(TranscoderInput input, TranscoderOutput output) throws TranscoderException {
/*  156 */     super.transcode(input, output);
/*      */     
/*  158 */     if (this.ctx != null) {
/*  159 */       this.ctx.dispose();
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
/*      */   protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
/*      */     GraphicsNode gvtRoot;
/*      */     AffineTransform Px;
/*  174 */     if (document != null && !(document.getImplementation() instanceof SVGDOMImplementation)) {
/*      */ 
/*      */       
/*  177 */       DOMImplementation impl = (DOMImplementation)this.hints.get(KEY_DOM_IMPLEMENTATION);
/*      */       
/*  179 */       document = DOMUtilities.deepCloneDocument(document, impl);
/*  180 */       if (uri != null) {
/*  181 */         ParsedURL url = new ParsedURL(uri);
/*  182 */         ((SVGOMDocument)document).setParsedURL(url);
/*      */       } 
/*      */     } 
/*      */     
/*  186 */     if (this.hints.containsKey(KEY_WIDTH))
/*  187 */       this.width = ((Float)this.hints.get(KEY_WIDTH)).floatValue(); 
/*  188 */     if (this.hints.containsKey(KEY_HEIGHT)) {
/*  189 */       this.height = ((Float)this.hints.get(KEY_HEIGHT)).floatValue();
/*      */     }
/*      */     
/*  192 */     SVGOMDocument svgDoc = (SVGOMDocument)document;
/*  193 */     SVGSVGElement root = svgDoc.getRootElement();
/*  194 */     this.ctx = createBridgeContext(svgDoc);
/*      */ 
/*      */     
/*  197 */     this.builder = new GVTBuilder();
/*      */     
/*  199 */     boolean isDynamic = (this.hints.containsKey(KEY_EXECUTE_ONLOAD) && ((Boolean)this.hints.get(KEY_EXECUTE_ONLOAD)).booleanValue());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  205 */       if (isDynamic) {
/*  206 */         this.ctx.setDynamicState(2);
/*      */       }
/*  208 */       gvtRoot = this.builder.build(this.ctx, (Document)svgDoc);
/*      */ 
/*      */       
/*  211 */       if (this.ctx.isDynamic()) {
/*      */         
/*  213 */         BaseScriptingEnvironment se = new BaseScriptingEnvironment(this.ctx);
/*  214 */         se.loadScripts();
/*  215 */         se.dispatchSVGLoadEvent();
/*  216 */         if (this.hints.containsKey(KEY_SNAPSHOT_TIME)) {
/*  217 */           float t = ((Float)this.hints.get(KEY_SNAPSHOT_TIME)).floatValue();
/*      */           
/*  219 */           this.ctx.getAnimationEngine().setCurrentTime(t);
/*  220 */         } else if (this.ctx.isSVG12()) {
/*  221 */           float t = SVGUtilities.convertSnapshotTime((Element)root, null);
/*  222 */           this.ctx.getAnimationEngine().setCurrentTime(t);
/*      */         } 
/*      */       } 
/*  225 */     } catch (BridgeException ex) {
/*  226 */       throw new TranscoderException(ex);
/*      */     } 
/*      */ 
/*      */     
/*  230 */     float docWidth = (float)this.ctx.getDocumentSize().getWidth();
/*  231 */     float docHeight = (float)this.ctx.getDocumentSize().getHeight();
/*      */     
/*  233 */     setImageSize(docWidth, docHeight);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  239 */     if (this.hints.containsKey(KEY_AOI)) {
/*  240 */       Rectangle2D aoi = (Rectangle2D)this.hints.get(KEY_AOI);
/*      */       
/*  242 */       Px = new AffineTransform();
/*  243 */       double sx = this.width / aoi.getWidth();
/*  244 */       double sy = this.height / aoi.getHeight();
/*  245 */       double scale = Math.min(sx, sy);
/*  246 */       Px.scale(scale, scale);
/*  247 */       double tx = -aoi.getX() + (this.width / scale - aoi.getWidth()) / 2.0D;
/*  248 */       double ty = -aoi.getY() + (this.height / scale - aoi.getHeight()) / 2.0D;
/*  249 */       Px.translate(tx, ty);
/*      */ 
/*      */       
/*  252 */       this.curAOI = aoi;
/*      */     } else {
/*  254 */       String ref = (new ParsedURL(uri)).getRef();
/*      */ 
/*      */ 
/*      */       
/*  258 */       String viewBox = root.getAttributeNS(null, "viewBox");
/*      */ 
/*      */       
/*  261 */       if (ref != null && ref.length() != 0) {
/*  262 */         Px = ViewBox.getViewTransform(ref, (Element)root, this.width, this.height, this.ctx);
/*  263 */       } else if (viewBox != null && viewBox.length() != 0) {
/*  264 */         String aspectRatio = root.getAttributeNS(null, "preserveAspectRatio");
/*      */         
/*  266 */         Px = ViewBox.getPreserveAspectRatioTransform((Element)root, viewBox, aspectRatio, this.width, this.height, this.ctx);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  271 */         float xscale = this.width / docWidth;
/*  272 */         float yscale = this.height / docHeight;
/*  273 */         float scale = Math.min(xscale, yscale);
/*  274 */         Px = AffineTransform.getScaleInstance(scale, scale);
/*      */       } 
/*      */       
/*  277 */       this.curAOI = new Rectangle2D.Float(0.0F, 0.0F, this.width, this.height);
/*      */     } 
/*      */     
/*  280 */     CanvasGraphicsNode cgn = getCanvasGraphicsNode(gvtRoot);
/*  281 */     if (cgn != null) {
/*  282 */       cgn.setViewingTransform(Px);
/*  283 */       this.curTxf = new AffineTransform();
/*      */     } else {
/*  285 */       this.curTxf = Px;
/*      */     } 
/*      */     
/*  288 */     this.root = gvtRoot;
/*      */   }
/*      */   
/*      */   protected CanvasGraphicsNode getCanvasGraphicsNode(GraphicsNode gn) {
/*  292 */     if (!(gn instanceof CompositeGraphicsNode))
/*  293 */       return null; 
/*  294 */     CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/*  295 */     List<GraphicsNode> children = cgn.getChildren();
/*  296 */     if (children.size() == 0)
/*  297 */       return null; 
/*  298 */     gn = children.get(0);
/*  299 */     if (!(gn instanceof CanvasGraphicsNode))
/*  300 */       return null; 
/*  301 */     return (CanvasGraphicsNode)gn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext createBridgeContext(SVGOMDocument doc) {
/*  312 */     return createBridgeContext(doc.isSVG12() ? "1.2" : "1.x");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext createBridgeContext() {
/*  323 */     return createBridgeContext("1.x");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BridgeContext createBridgeContext(String svgVersion) {
/*  333 */     if ("1.2".equals(svgVersion)) {
/*  334 */       return (BridgeContext)new SVG12BridgeContext(this.userAgent);
/*      */     }
/*  336 */     return new BridgeContext(this.userAgent);
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
/*      */   protected void setImageSize(float docWidth, float docHeight) {
/*  350 */     float imgWidth = -1.0F;
/*  351 */     if (this.hints.containsKey(KEY_WIDTH)) {
/*  352 */       imgWidth = ((Float)this.hints.get(KEY_WIDTH)).floatValue();
/*      */     }
/*  354 */     float imgHeight = -1.0F;
/*  355 */     if (this.hints.containsKey(KEY_HEIGHT)) {
/*  356 */       imgHeight = ((Float)this.hints.get(KEY_HEIGHT)).floatValue();
/*      */     }
/*      */     
/*  359 */     if (imgWidth > 0.0F && imgHeight > 0.0F) {
/*  360 */       this.width = imgWidth;
/*  361 */       this.height = imgHeight;
/*  362 */     } else if (imgHeight > 0.0F) {
/*  363 */       this.width = docWidth * imgHeight / docHeight;
/*  364 */       this.height = imgHeight;
/*  365 */     } else if (imgWidth > 0.0F) {
/*  366 */       this.width = imgWidth;
/*  367 */       this.height = docHeight * imgWidth / docWidth;
/*      */     } else {
/*  369 */       this.width = docWidth;
/*  370 */       this.height = docHeight;
/*      */     } 
/*      */ 
/*      */     
/*  374 */     float imgMaxWidth = -1.0F;
/*  375 */     if (this.hints.containsKey(KEY_MAX_WIDTH)) {
/*  376 */       imgMaxWidth = ((Float)this.hints.get(KEY_MAX_WIDTH)).floatValue();
/*      */     }
/*  378 */     float imgMaxHeight = -1.0F;
/*  379 */     if (this.hints.containsKey(KEY_MAX_HEIGHT)) {
/*  380 */       imgMaxHeight = ((Float)this.hints.get(KEY_MAX_HEIGHT)).floatValue();
/*      */     }
/*      */     
/*  383 */     if (imgMaxHeight > 0.0F && this.height > imgMaxHeight) {
/*  384 */       this.width = docWidth * imgMaxHeight / docHeight;
/*  385 */       this.height = imgMaxHeight;
/*      */     } 
/*  387 */     if (imgMaxWidth > 0.0F && this.width > imgMaxWidth) {
/*  388 */       this.width = imgMaxWidth;
/*  389 */       this.height = docHeight * imgMaxWidth / docWidth;
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
/*  422 */   public static final TranscodingHints.Key KEY_WIDTH = (TranscodingHints.Key)new LengthKey();
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
/*  450 */   public static final TranscodingHints.Key KEY_HEIGHT = (TranscodingHints.Key)new LengthKey();
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
/*  480 */   public static final TranscodingHints.Key KEY_MAX_WIDTH = (TranscodingHints.Key)new LengthKey();
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
/*  510 */   public static final TranscodingHints.Key KEY_MAX_HEIGHT = (TranscodingHints.Key)new LengthKey();
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
/*  540 */   public static final TranscodingHints.Key KEY_AOI = (TranscodingHints.Key)new Rectangle2DKey();
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
/*  568 */   public static final TranscodingHints.Key KEY_LANGUAGE = (TranscodingHints.Key)new StringKey();
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
/*  596 */   public static final TranscodingHints.Key KEY_MEDIA = (TranscodingHints.Key)new StringKey();
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
/*  627 */   public static final TranscodingHints.Key KEY_DEFAULT_FONT_FAMILY = (TranscodingHints.Key)new StringKey();
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
/*  655 */   public static final TranscodingHints.Key KEY_ALTERNATE_STYLESHEET = (TranscodingHints.Key)new StringKey();
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
/*  683 */   public static final TranscodingHints.Key KEY_USER_STYLESHEET_URI = (TranscodingHints.Key)new StringKey();
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
/*  711 */   public static final TranscodingHints.Key KEY_PIXEL_UNIT_TO_MILLIMETER = (TranscodingHints.Key)new FloatKey();
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
/*  742 */   public static final TranscodingHints.Key KEY_PIXEL_TO_MM = KEY_PIXEL_UNIT_TO_MILLIMETER;
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
/*  771 */   public static final TranscodingHints.Key KEY_EXECUTE_ONLOAD = (TranscodingHints.Key)new BooleanKey();
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
/*  801 */   public static final TranscodingHints.Key KEY_SNAPSHOT_TIME = (TranscodingHints.Key)new FloatKey();
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
/*  833 */   public static final TranscodingHints.Key KEY_ALLOWED_SCRIPT_TYPES = (TranscodingHints.Key)new StringKey();
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
/*      */   public static final String DEFAULT_ALLOWED_SCRIPT_TYPES = "text/ecmascript, application/ecmascript, text/javascript, application/javascript, application/java-archive";
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
/*  876 */   public static final TranscodingHints.Key KEY_CONSTRAIN_SCRIPT_ORIGIN = (TranscodingHints.Key)new BooleanKey();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class SVGAbstractTranscoderUserAgent
/*      */     extends UserAgentAdapter
/*      */   {
/*      */     protected List scripts;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SVGAbstractTranscoderUserAgent() {
/*  890 */       addStdFeatures();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AffineTransform getTransform() {
/*  897 */       return SVGAbstractTranscoder.this.curTxf;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setTransform(AffineTransform at) {
/*  904 */       SVGAbstractTranscoder.this.curTxf = at;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension2D getViewportSize() {
/*  911 */       return new Dimension((int)SVGAbstractTranscoder.this.width, (int)SVGAbstractTranscoder.this.height);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(String message) {
/*      */       try {
/*  920 */         SVGAbstractTranscoder.this.handler.error(new TranscoderException(message));
/*      */       }
/*  922 */       catch (TranscoderException ex) {
/*  923 */         throw new RuntimeException(ex.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(Exception e) {
/*      */       try {
/*  932 */         e.printStackTrace();
/*  933 */         SVGAbstractTranscoder.this.handler.error(new TranscoderException(e));
/*      */       }
/*  935 */       catch (TranscoderException ex) {
/*  936 */         throw new RuntimeException(ex.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayMessage(String message) {
/*      */       try {
/*  945 */         SVGAbstractTranscoder.this.handler.warning(new TranscoderException(message));
/*      */       }
/*  947 */       catch (TranscoderException ex) {
/*  948 */         throw new RuntimeException(ex.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelUnitToMillimeter() {
/*  957 */       Object obj = SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER);
/*      */       
/*  959 */       if (obj != null) {
/*  960 */         return ((Float)obj).floatValue();
/*      */       }
/*      */       
/*  963 */       return super.getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLanguages() {
/*  971 */       if (SVGAbstractTranscoder.this.hints.containsKey(SVGAbstractTranscoder.KEY_LANGUAGE)) {
/*  972 */         return (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_LANGUAGE);
/*      */       }
/*      */ 
/*      */       
/*  976 */       return super.getLanguages();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getMedia() {
/*  983 */       String s = (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_MEDIA);
/*  984 */       if (s != null) return s;
/*      */       
/*  986 */       return super.getMedia();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDefaultFontFamily() {
/*  993 */       String s = (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_DEFAULT_FONT_FAMILY);
/*  994 */       if (s != null) return s;
/*      */       
/*  996 */       return super.getDefaultFontFamily();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getAlternateStyleSheet() {
/* 1003 */       String s = (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_ALTERNATE_STYLESHEET);
/* 1004 */       if (s != null) {
/* 1005 */         return s;
/*      */       }
/* 1007 */       return super.getAlternateStyleSheet();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUserStyleSheetURI() {
/* 1015 */       String s = (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_USER_STYLESHEET_URI);
/*      */       
/* 1017 */       if (s != null) {
/* 1018 */         return s;
/*      */       }
/* 1020 */       return super.getUserStyleSheetURI();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getXMLParserClassName() {
/* 1027 */       String s = (String)SVGAbstractTranscoder.this.hints.get(XMLAbstractTranscoder.KEY_XML_PARSER_CLASSNAME);
/*      */       
/* 1029 */       if (s != null) {
/* 1030 */         return s;
/*      */       }
/* 1032 */       return super.getXMLParserClassName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isXMLParserValidating() {
/* 1040 */       Boolean b = (Boolean)SVGAbstractTranscoder.this.hints.get(XMLAbstractTranscoder.KEY_XML_PARSER_VALIDATING);
/*      */       
/* 1042 */       if (b != null) {
/* 1043 */         return b.booleanValue();
/*      */       }
/* 1045 */       return super.isXMLParserValidating();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptPURL, ParsedURL docPURL) {
/* 1064 */       if (this.scripts == null) {
/* 1065 */         computeAllowedScripts();
/*      */       }
/*      */       
/* 1068 */       if (!this.scripts.contains(scriptType)) {
/* 1069 */         return (ScriptSecurity)new NoLoadScriptSecurity(scriptType);
/*      */       }
/*      */ 
/*      */       
/* 1073 */       boolean constrainOrigin = true;
/*      */       
/* 1075 */       if (SVGAbstractTranscoder.this.hints.containsKey(SVGAbstractTranscoder.KEY_CONSTRAIN_SCRIPT_ORIGIN))
/*      */       {
/* 1077 */         constrainOrigin = ((Boolean)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_CONSTRAIN_SCRIPT_ORIGIN)).booleanValue();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1082 */       if (constrainOrigin) {
/* 1083 */         return (ScriptSecurity)new DefaultScriptSecurity(scriptType, scriptPURL, docPURL);
/*      */       }
/*      */       
/* 1086 */       return (ScriptSecurity)new RelaxedScriptSecurity(scriptType, scriptPURL, docPURL);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void computeAllowedScripts() {
/* 1096 */       this.scripts = new LinkedList();
/* 1097 */       if (!SVGAbstractTranscoder.this.hints.containsKey(SVGAbstractTranscoder.KEY_ALLOWED_SCRIPT_TYPES)) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1102 */       String allowedScripts = (String)SVGAbstractTranscoder.this.hints.get(SVGAbstractTranscoder.KEY_ALLOWED_SCRIPT_TYPES);
/*      */ 
/*      */ 
/*      */       
/* 1106 */       StringTokenizer st = new StringTokenizer(allowedScripts, ",");
/* 1107 */       while (st.hasMoreTokens())
/* 1108 */         this.scripts.add(st.nextToken()); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/SVGAbstractTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */