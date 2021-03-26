/*    */ package jp.cssj.homare.impl.objects.svg;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.e.f;
/*    */ import jp.cssj.homare.css.h;
/*    */ import jp.cssj.homare.impl.ua.svg.SVGImageLoader;
/*    */ import jp.cssj.homare.ua.g;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*    */ import org.apache.batik.anim.dom.SVGOMDocument;
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ import org.apache.batik.util.XMLResourceDescriptor;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends SAXSVGDocumentFactory
/*    */   implements h
/*    */ {
/* 24 */   protected SVGImageLoader a = null;
/*    */   
/*    */   public a() {
/* 27 */     super(XMLResourceDescriptor.getXMLParserClassName());
/* 28 */     synchronized (this) {
/* 29 */       if (this.a == null) {
/* 30 */         this.a = new SVGImageLoader();
/*    */       }
/*    */     } 
/*    */     try {
/* 34 */       this.parser = jp.cssj.homare.xml.xerces.a.a();
/* 35 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 38 */     setValidating(false);
/*    */   }
/*    */   public b a(m ua) throws IOException {
/*    */     jp.cssj.sakae.c.b.a.a a1;
/* 42 */     SVGOMDocument doc = (SVGOMDocument)this.document;
/* 43 */     this.document = null;
/* 44 */     this.currentNode = null;
/* 45 */     this.locator = null;
/*    */     
/* 47 */     URI uri = ua.c().a();
/* 48 */     String path = uri.getPath();
/* 49 */     if (path != null) {
/* 50 */       int slash = path.lastIndexOf('/');
/* 51 */       if (slash != -1) {
/* 52 */         path = path.substring(slash + 1);
/*    */       }
/*    */     } 
/* 55 */     doc.getDocumentElement().setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", path);
/* 56 */     doc.setParsedURL(new ParsedURL(uri.toString()));
/* 57 */     b image = this.a.getImage(uri.toString(), (Document)doc, ua);
/* 58 */     double scale = f.a(ua, 1.0D, (short)17, (short)21);
/* 59 */     if (scale != 1.0D) {
/* 60 */       g map = (g)ua.a().d().remove(image);
/* 61 */       AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
/* 62 */       a1 = new jp.cssj.sakae.c.b.a.a(image, at);
/* 63 */       map = map.a(at);
/* 64 */       ua.a().d().put(a1, map);
/*    */     } 
/* 66 */     return (b)a1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/svg/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */