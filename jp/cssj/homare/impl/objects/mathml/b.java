/*    */ package jp.cssj.homare.impl.objects.mathml;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import jp.cssj.homare.css.h;
/*    */ import jp.cssj.homare.impl.ua.svg.SVGImageLoader;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.homare.xml.xerces.a;
/*    */ import jp.cssj.sakae.c.b.a.a;
/*    */ import net.a.a.c.c;
/*    */ import net.a.a.d;
/*    */ import net.a.a.g.c;
/*    */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*    */ import org.apache.batik.util.XMLResourceDescriptor;
/*    */ 
/*    */ public class b
/*    */   extends SAXDocumentFactory
/*    */   implements h
/*    */ {
/* 24 */   protected SVGImageLoader a = null;
/*    */   
/*    */   public b() throws ParserConfigurationException {
/* 27 */     super(d.a().getDOMImplementation(), 
/* 28 */         XMLResourceDescriptor.getXMLParserClassName());
/*    */     try {
/* 30 */       this.parser = a.a();
/* 31 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 34 */     setValidating(false);
/*    */   }
/*    */   public jp.cssj.sakae.c.b.b a(m ua) throws IOException {
/*    */     a a;
/* 38 */     Image tempimage = new BufferedImage(1, 1, 2);
/* 39 */     Graphics2D g = (Graphics2D)tempimage.getGraphics();
/* 40 */     c view = new c(this.document, c.a(), g);
/*    */     
/* 42 */     jp.cssj.sakae.c.b.b image = new a(view);
/* 43 */     double scale = ua.k();
/* 44 */     if (scale != 1.0D) {
/* 45 */       AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
/* 46 */       a = new a(image, at);
/*    */     } 
/* 48 */     return (jp.cssj.sakae.c.b.b)a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/mathml/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */