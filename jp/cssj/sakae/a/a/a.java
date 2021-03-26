/*     */ package jp.cssj.sakae.a.a;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.k;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.d.b;
/*     */ import jp.cssj.sakae.d.d;
/*     */ import jp.cssj.sakae.d.h;
/*     */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.DocumentLoader;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Document;
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
/*     */ class a
/*     */   implements k
/*     */ {
/*     */   private static final long a = 2L;
/*     */   private final b b;
/*     */   private final Map<Integer, GraphicsNode> c;
/*     */   
/*     */   public a(b source) {
/*  74 */     this.c = new HashMap<>(); this.b = source;
/*  75 */   } public int a(int c) { return c; } public short b(int gid) { return 1000; } public short c(int gid) { return 1000; } public b b() { b source = this.b; return source.f(); } private static final Dimension2D d = (Dimension2D)new jp.cssj.sakae.d.a(128.0D, 128.0D);
/*     */   public g a() { return (g)this.b; }
/*     */   public short a(int sgid, int gid) { return 0; }
/*  78 */   public int b(int sgid, int gid) { return -1; } public void a(b gc, h text) throws IOException, c { jp.cssj.sakae.c.a.a.a.a(gc, (d)this, text); } public void a(b gc, int gid, AffineTransform at) { GraphicsNode gvtRoot = this.c.get(Integer.valueOf(gid));
/*  79 */     if (gvtRoot == null)
/*     */     {
/*  81 */       try (InputStream in = b.class.getResourceAsStream("emoji_u" + Integer.toHexString(gid).toLowerCase() + ".svg")) {
/*  82 */         SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
/*  83 */         SVGOMDocument doc = (SVGOMDocument)factory.createDocument(null, in);
/*  84 */         h h = new h(d);
/*  85 */         DocumentLoader loader = new DocumentLoader((UserAgent)h);
/*  86 */         BridgeContext ctx = new BridgeContext((UserAgent)h, loader);
/*  87 */         ctx.setDynamic(false);
/*  88 */         b b1 = new b();
/*  89 */         gvtRoot = b1.build(ctx, (Document)doc);
/*  90 */         this.c.put(Integer.valueOf(gid), gvtRoot);
/*  91 */       } catch (Exception e) {
/*  92 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*  95 */     if (gvtRoot == null) {
/*     */       return;
/*     */     }
/*     */     
/*  99 */     gc.d();
/* 100 */     if (at != null) {
/* 101 */       gc.a(at);
/*     */     }
/* 103 */     gc.a(AffineTransform.getTranslateInstance(0.0D, -this.b.g()));
/* 104 */     gc.a(AffineTransform.getScaleInstance(1000.0D / d.getWidth(), 1000.0D / d.getHeight()));
/* 105 */     gc.d();
/* 106 */     d d = new d(gc);
/* 107 */     gvtRoot.paint((Graphics2D)d);
/* 108 */     d.dispose();
/* 109 */     gc.e();
/* 110 */     gc.e(); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */