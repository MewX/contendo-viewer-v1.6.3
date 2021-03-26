/*     */ package jp.cssj.d.a;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */ {
/*  25 */   private static final Logger g = Logger.getLogger(e.class.getName());
/*     */   
/*     */   public static final String a = "http://www.idpf.org/2007/opf";
/*     */   
/*     */   public static final String b = "http://purl.org/dc/elements/1.1/";
/*     */   public static final String c = "http://www.daisy.org/z3986/2005/ncx/";
/*     */   public static final String d = "http://www.idpf.org/2007/ops";
/*     */   public static final String e = "http://www.w3.org/1999/xhtml";
/*     */   public final a f;
/*  34 */   private SAXParserFactory h = SAXParserFactory.newInstance();
/*     */   public e(a archive) {
/*  36 */     this.h.setValidating(false);
/*  37 */     a(this.h, "http://xml.org/sax/features/external-general-entities", false);
/*  38 */     a(this.h, "http://xml.org/sax/features/external-parameter-entities", false);
/*  39 */     a(this.h, "http://xml.org/sax/features/namespaces", true);
/*  40 */     a(this.h, "http://xml.org/sax/features/validation", false);
/*  41 */     a(this.h, "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
/*  42 */     a(this.h, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.f = archive;
/*     */   }
/*     */   
/*     */   private SAXParser b() {
/*     */     try {
/*  57 */       SAXParser parser = this.h.newSAXParser();
/*  58 */       return parser;
/*  59 */     } catch (ParserConfigurationException parserConfigurationException) {
/*  60 */       throw new RuntimeException(parserConfigurationException);
/*  61 */     } catch (SAXException sAXException) {
/*  62 */       throw new RuntimeException(sAXException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(SAXParserFactory pf, String key, boolean b) {
/*     */     try {
/*  68 */       if (pf.getFeature(key) == b) {
/*     */         return;
/*     */       }
/*  71 */       pf.setFeature(key, b);
/*  72 */     } catch (Exception exception) {
/*  73 */       g.log(Level.FINE, "サポートされない機能です", exception);
/*     */     } 
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
/*     */   public b a() throws FileNotFoundException, IOException, SAXException {
/*  89 */     try (InputStream in = this.f.b("META-INF/container.xml")) {
/*  90 */       SAXParser parser = b();
/*  91 */       c handler = new c();
/*  92 */       parser.parse(new InputSource(in), handler);
/*  93 */       return handler.a();
/*     */     } 
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
/*     */   public d a(b.a root) throws IOException, SAXException {
/* 109 */     try (InputStream in = new BufferedInputStream(this.f.b(root.b))) {
/* 110 */       SAXParser parser = b();
/* 111 */       k handler = new k(root.b);
/* 112 */       parser.parse(new InputSource(in), handler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       return handler.a();
/*     */     } 
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
/*     */   public n a(d contents) throws IOException, SAXException {
/* 135 */     if (contents.n == null)
/*     */     {
/*     */       
/* 138 */       for (int i = 0; i < contents.p.length; i++) {
/* 139 */         if ("application/x-dtbncx+xml".equals((contents.p[i]).b)) {
/* 140 */           contents.n = contents.p[i];
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 145 */     if (contents.n == null) {
/* 146 */       return null;
/*     */     }
/* 148 */     if ("application/xhtml+xml".equals(contents.n.b)) {
/*     */       
/* 150 */       h handler = new h(contents);
/* 151 */       try (InputStream in = this.f.b(contents.n.d)) {
/* 152 */         SAXParser parser = b();
/* 153 */         parser.parse(new InputSource(in), handler);
/*     */       } 
/* 155 */       return handler.a();
/*     */     } 
/* 157 */     if ("application/x-dtbncx+xml".equals(contents.n.b)) {
/*     */       
/* 159 */       j handler = new j(contents);
/* 160 */       try (InputStream in = this.f.b(contents.n.d)) {
/* 161 */         SAXParser parser = b();
/* 162 */         parser.parse(new InputSource(in), handler);
/*     */       } 
/* 164 */       return handler.a();
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */