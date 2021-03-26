/*     */ package jp.cssj.homare.impl.ua.svg;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URI;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.homare.ua.ImageLoader;
/*     */ import jp.cssj.homare.ua.g;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.d.a;
/*     */ import jp.cssj.sakae.d.b;
/*     */ import jp.cssj.sakae.d.g;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.anim.dom.SVGOMSVGElement;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.ParsedURLProtocolHandler;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGImageLoader
/*     */   implements ImageLoader
/*     */ {
/*  38 */   private static final Logger b = Logger.getLogger(SVGImageLoader.class.getName());
/*     */   protected static final String a = "image/svg+xml";
/*     */   
/*     */   static {
/*  42 */     ParsedURL.registerHandler((ParsedURLProtocolHandler)e.a);
/*     */   }
/*     */   public boolean match(b key) {
/*     */     String mimeType;
/*  46 */     b source = key;
/*     */     
/*     */     try {
/*  49 */       mimeType = source.c();
/*  50 */     } catch (IOException e) {
/*  51 */       b.log(Level.WARNING, "MIME型を取得できませんでした。", e);
/*  52 */       return false;
/*     */     } 
/*  54 */     URI uri = source.d();
/*  55 */     String path = uri.getPath();
/*  56 */     if (!"image/svg+xml".equalsIgnoreCase(mimeType)) {
/*  57 */       if (path == null || path.length() == 0) {
/*  58 */         path = uri.getSchemeSpecificPart();
/*     */       }
/*  60 */       if (path == null) {
/*  61 */         return false;
/*     */       }
/*  63 */       path = path.toLowerCase();
/*  64 */       if (!path.endsWith(".svgz") && !path.endsWith(".svg")) {
/*  65 */         return false;
/*     */       }
/*     */     } 
/*  68 */     return true;
/*     */   }
/*     */   
/*     */   public b loadImage(m ua, b source) throws IOException {
/*  72 */     Document doc = loadDocument(source);
/*  73 */     return getImage(source.d().toString(), doc, ua);
/*     */   } public Document loadDocument(b source) throws IOException {
/*     */     boolean gzip;
/*     */     SVGOMDocument doc;
/*  77 */     URI uri = source.d();
/*  78 */     String path = uri.getPath();
/*     */     
/*  80 */     if (path != null) {
/*  81 */       path = path.toLowerCase();
/*  82 */       gzip = path.endsWith(".svgz");
/*     */     } else {
/*  84 */       gzip = false;
/*     */     } 
/*     */ 
/*     */     
/*  88 */     SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
/*  89 */     String uriStr = uri.toString();
/*     */ 
/*     */     
/*  92 */     if (!gzip && source.i()) {
/*  93 */       try (Reader in = new BufferedReader(source.j())) {
/*  94 */         doc = (SVGOMDocument)factory.createDocument(uriStr, in);
/*     */       } 
/*     */     } else {
/*  97 */       InputStream in = new BufferedInputStream(source.h());
/*     */       try {
/*  99 */         if (!gzip) {
/* 100 */           in.mark(2);
/* 101 */           if (in.read() == 31 && in.read() == 139) {
/* 102 */             gzip = true;
/*     */           }
/* 104 */           in.reset();
/*     */         } 
/* 106 */         if (gzip) {
/* 107 */           in = new GZIPInputStream(in);
/*     */         }
/* 109 */         doc = (SVGOMDocument)factory.createDocument(uriStr, in);
/*     */       } finally {
/* 111 */         in.close();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 116 */     if (path != null) {
/* 117 */       int slash = path.lastIndexOf('/');
/* 118 */       if (slash != -1) {
/* 119 */         path = path.substring(slash + 1);
/*     */       }
/*     */     } 
/* 122 */     doc.getDocumentElement().setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", path);
/* 123 */     return (Document)doc;
/*     */   }
/*     */   
/* 126 */   private static final Dimension2D c = (Dimension2D)new a(400.0D, 400.0D);
/*     */   public b getImage(String docURI, Document doc, m ua) throws IOException {
/*     */     try {
/*     */       a a;
/* 130 */       SVGOMSVGElement root = (SVGOMSVGElement)doc.getDocumentElement();
/* 131 */       Dimension2D viewport = c;
/*     */       try {
/* 133 */         SVGRect r = root.getViewBox().getBaseVal();
/* 134 */         a = new a(r.getWidth(), r.getHeight());
/* 135 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 138 */       a ctx = new a(docURI, ua, (Dimension2D)a, this);
/* 139 */       b b = new b();
/* 140 */       GraphicsNode gvtRoot = b.build(ctx, doc);
/*     */       
/* 142 */       String width = root.getAttribute("width");
/* 143 */       String height = root.getAttribute("height");
/* 144 */       if ((width == null || width.length() == 0) && height != null && height.length() > 0) {
/* 145 */         root.setAttribute("width", height);
/* 146 */       } else if ((height == null || height.length() == 0) && width != null && width.length() > 0) {
/* 147 */         root.setAttribute("height", width);
/*     */       } 
/*     */       
/* 150 */       AbstractSVGAnimatedLength _width = (AbstractSVGAnimatedLength)root.getWidth();
/* 151 */       float w = _width.getCheckedValue();
/*     */ 
/*     */       
/* 154 */       AbstractSVGAnimatedLength _height = (AbstractSVGAnimatedLength)root.getHeight();
/* 155 */       float h = _height.getCheckedValue();
/*     */       
/* 157 */       g imageMap = ctx.c;
/* 158 */       g g = new g(gvtRoot, w, h);
/* 159 */       ua.a().d().put(g, imageMap);
/* 160 */       return (b)g;
/* 161 */     } catch (BridgeException e) {
/* 162 */       IOException ioe = new IOException("SVGを読み込めませんでした。");
/* 163 */       ioe.initCause((Throwable)e);
/* 164 */       throw ioe;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/SVGImageLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */