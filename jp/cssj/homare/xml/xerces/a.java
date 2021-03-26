/*     */ package jp.cssj.homare.xml.xerces;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.homare.xml.c;
/*     */ import jp.cssj.homare.xml.e;
/*     */ import jp.cssj.homare.xml.g;
/*     */ import org.apache.xerces.jaxp.SAXParserFactoryImpl;
/*     */ import org.apache.xerces.parsers.AbstractSAXParser;
/*     */ import org.apache.xerces.parsers.XML11Configuration;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLParserConfiguration;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.ext.Locator2;
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
/*     */ public class a
/*     */   implements e
/*     */ {
/*  47 */   private static final Logger b = Logger.getLogger(a.class.getName());
/*     */   
/*     */   public static XMLReader a() throws ParserConfigurationException, SAXException {
/*  50 */     SAXParserFactoryImpl pf = new SAXParserFactoryImpl();
/*  51 */     pf.setValidating(false);
/*  52 */     XMLReader reader = pf.newSAXParser().getXMLReader();
/*  53 */     a(reader);
/*  54 */     return reader;
/*     */   }
/*     */   
/*     */   public static void a(XMLReader reader) {
/*  58 */     a(reader, "http://xml.org/sax/features/external-general-entities", false);
/*  59 */     a(reader, "http://xml.org/sax/features/external-parameter-entities", false);
/*  60 */     a(reader, "http://xml.org/sax/features/namespaces", true);
/*  61 */     a(reader, "http://xml.org/sax/features/validation", false);
/*  62 */     a(reader, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/*     */   }
/*     */   
/*     */   private static void a(XMLReader pf, String key, boolean b) {
/*     */     try {
/*  67 */       if (pf.getFeature(key) == b) {
/*     */         return;
/*     */       }
/*  70 */       pf.setFeature(key, b);
/*  71 */     } catch (Exception exception) {
/*  72 */       a.b.log(Level.FINE, "サポートされない機能です", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected class a extends AbstractSAXParser {
/*     */     public a(a this$0) {
/*  78 */       super((XMLParserConfiguration)new XML11Configuration());
/*     */     }
/*     */ 
/*     */     
/*     */     public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
/*  83 */       super.startDocument(locator, encoding, namespaceContext, augs);
/*  84 */       e.a.set(locator);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(m ua, b source, g xmlHandler) throws SAXException, IOException {
/*  90 */     String encoding = source.a();
/*  91 */     if (encoding != null) {
/*  92 */       ua.c().a(encoding);
/*     */     }
/*  94 */     a a1 = new a(this);
/*  95 */     a((XMLReader)a1);
/*  96 */     InputSource inputSource = d.b(source);
/*     */     
/*  98 */     a1.setProperty("http://xml.org/sax/properties/lexical-handler", xmlHandler);
/*  99 */     a1.setContentHandler((ContentHandler)new c(this, xmlHandler, ua) {
/*     */           public void setDocumentLocator(Locator locator) {
/* 101 */             super.setDocumentLocator(locator);
/*     */             try {
/* 103 */               if (locator instanceof Locator2) {
/* 104 */                 String encoding = ((Locator2)locator).getEncoding();
/* 105 */                 if (encoding != null) {
/* 106 */                   this.a.c().a(encoding);
/*     */                 }
/*     */               } else {
/* 109 */                 this.a.c().a("UTF-8");
/*     */               } 
/* 111 */             } catch (UnsupportedEncodingException ex) {
/* 112 */               throw new RuntimeException(ex);
/*     */             } 
/*     */           }
/*     */         });
/*     */     try {
/* 117 */       a1.parse(inputSource);
/*     */     } finally {
/* 119 */       e.a.remove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/xerces/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */