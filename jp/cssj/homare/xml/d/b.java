/*     */ package jp.cssj.homare.xml.d;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import jp.cssj.b.a.g;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.a.a;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.homare.xml.c;
/*     */ import jp.cssj.homare.xml.f;
/*     */ import jp.cssj.homare.xml.g;
/*     */ import jp.cssj.homare.xml.i;
/*     */ import org.apache.xalan.processor.TransformerFactoryImpl;
/*     */ import org.apache.xerces.parsers.DOMParser;
/*     */ import org.w3c.dom.Document;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends c
/*     */   implements ErrorListener, URIResolver
/*     */ {
/*     */   static {
/*  53 */     f = Logger.getLogger(b.class.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  58 */   private TransformerHandler g = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private g h = null;
/*     */   
/*  65 */   protected f a = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private List<String[]> i = null;
/*     */   
/*  76 */   private int j = 0;
/*     */   
/*  78 */   private AttributesImpl k = new AttributesImpl();
/*     */   
/*  80 */   private TransformerException l = null;
/*     */   
/*     */   private boolean m = true;
/*     */   
/*     */   private static final Logger f;
/*     */   protected m b;
/*     */   protected Locator c;
/*     */   
/*     */   public void a(m ua) {
/*  89 */     this.b = ua;
/*     */   }
/*     */ 
/*     */   
/*     */   public Source resolve(String href, String base) throws TransformerException {
/*     */     try {
/*  95 */       if (base != null) {
/*     */ 
/*     */         
/*  98 */         URI baseURI = URI.create(base);
/*  99 */         if ("file".equals(baseURI.getScheme())) {
/* 100 */           String filebase = (new File(".")).getCanonicalFile().toURI().toString();
/* 101 */           base = (new File(baseURI.toURL().getFile())).getCanonicalFile().toURI().toString();
/* 102 */           if (base.startsWith(filebase)) {
/* 103 */             base = base.substring(filebase.length());
/*     */           }
/*     */         } 
/*     */       } 
/* 107 */       URI uri = d.a(this.b.c().c(), base, href);
/*     */       
/* 109 */       jp.cssj.e.b source = this.b.b(uri);
/*     */       try {
/* 111 */         DOMParser parser = new DOMParser();
/* 112 */         parser.setFeature("http://xml.org/sax/features/external-general-entities", false);
/* 113 */         parser.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
/* 114 */         parser.setFeature("http://xml.org/sax/features/namespaces", true);
/* 115 */         parser.setFeature("http://xml.org/sax/features/validation", false);
/*     */         try {
/* 117 */           parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/* 118 */         } catch (Exception e) {
/* 119 */           f.log(Level.WARNING, "Xercesではありません", e);
/*     */         } 
/*     */         
/* 122 */         parser.parse(d.a(source));
/* 123 */         Document doc = parser.getDocument();
/* 124 */         return new DOMSource(doc, uri.toString());
/*     */       } finally {
/* 126 */         this.b.a(source);
/*     */       } 
/* 128 */     } catch (Exception e) {
/* 129 */       throw new TransformerException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void warning(TransformerException te) throws TransformerException {
/* 135 */     this.b.a((short)14344, this.c.getSystemId(), te.getMessage());
/* 136 */     throw te;
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(TransformerException te) throws TransformerException {
/* 141 */     this.b.a((short)14345, this.c.getSystemId(), te.getMessage());
/* 142 */     throw te;
/*     */   }
/*     */   
/*     */   public void fatalError(TransformerException te) throws TransformerException {
/* 146 */     this.b.a((short)18433, this.c.getSystemId(), te.getMessage());
/* 147 */     f.log(Level.WARNING, "XSLTプロセッサエラー", te);
/* 148 */     this.l = te;
/* 149 */     throw te;
/*     */   }
/*     */   
/*     */   public void a(f ssh) {
/* 153 */     this.a = ssh;
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/* 157 */     super.setDocumentLocator(locator);
/* 158 */     this.c = locator;
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 162 */     if (target.equals("xml-stylesheet")) {
/*     */       
/*     */       try {
/* 165 */         d.a(data.toCharArray(), 0, data.length(), this.k);
/* 166 */         String type = this.k.getValue("type");
/*     */         
/* 168 */         if (type != null && g.a(type, "text/xsl")) {
/*     */           
/* 170 */           String href = this.k.getValue("href");
/* 171 */           if (href != null) {
/* 172 */             boolean alternate; String title = this.k.getValue("title");
/* 173 */             String mediaTypes = this.k.getValue("media");
/* 174 */             String alternateStr = this.k.getValue("alternate");
/*     */             
/* 176 */             if (alternateStr != null && alternateStr.equals("yes")) {
/* 177 */               alternate = true;
/*     */             } else {
/* 179 */               alternate = false;
/*     */             } 
/*     */             try {
/* 182 */               a(href, title, mediaTypes, alternate);
/* 183 */             } catch (URISyntaxException e) {
/* 184 */               this.b.a((short)10254, href);
/* 185 */             } catch (IOException e) {
/* 186 */               this.b.a((short)10254, href);
/* 187 */             } catch (TransformerConfigurationException e) {
/* 188 */               this.b.a((short)14337, href);
/*     */             } 
/*     */           } 
/*     */         } 
/* 192 */       } catch (ParseException e) {
/* 193 */         this.b.a((short)10245, "xml-stylesheet", data);
/*     */       } 
/* 195 */       this.k.clear();
/*     */     } 
/* 197 */     if (this.i == null) {
/* 198 */       this.i = (List)new ArrayList<>();
/*     */     }
/* 200 */     this.i.add(new String[] { target, data });
/*     */   }
/*     */   
/*     */   private void a(String href, String title, String mediaTypes, boolean alternate) throws URISyntaxException, SAXException, IOException, TransformerConfigurationException {
/*     */     boolean apply;
/* 205 */     if (!this.m) {
/*     */       return;
/*     */     }
/* 208 */     URI uri = d.a(this.b.c().c(), this.b
/* 209 */         .c().a(), href);
/*     */     
/* 211 */     if (this.a != null) {
/* 212 */       apply = this.a.a(uri, "text/xsl", title, mediaTypes, alternate);
/*     */     } else {
/* 214 */       apply = !alternate;
/*     */     } 
/* 216 */     if (apply && !this.b.b(mediaTypes)) {
/* 217 */       apply = false;
/*     */     }
/* 219 */     if (apply) {
/* 220 */       a(uri);
/* 221 */       this.m = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(URI uri) throws SAXException, IOException, TransformerConfigurationException {
/*     */     TransformerHandler th;
/* 229 */     TransformerFactoryImpl transformerFactoryImpl = new TransformerFactoryImpl();
/*     */     
/* 231 */     if (f.isLoggable(Level.FINE)) {
/* 232 */       f.fine("Using transformer: " + transformerFactoryImpl);
/*     */     }
/*     */     
/* 235 */     transformerFactoryImpl.setURIResolver(this);
/* 236 */     transformerFactoryImpl.setErrorListener(this);
/* 237 */     jp.cssj.e.b source = this.b.b(uri);
/*     */     
/*     */     try {
/* 240 */       th = transformerFactoryImpl.newTransformerHandler(c.a(source));
/*     */     } finally {
/* 242 */       this.b.a(source);
/*     */     } 
/* 244 */     Transformer t = th.getTransformer();
/* 245 */     t.setURIResolver(this);
/* 246 */     t.setErrorListener(this);
/*     */     
/* 248 */     if (this.h == null) {
/* 249 */       this.h = this.e;
/* 250 */       this.e = (g)new a(this.e);
/*     */     } 
/* 252 */     if (!d && this.e == null) throw new AssertionError(); 
/* 253 */     th.setResult(new SAXResult((ContentHandler)this.e));
/* 254 */     this.e = (g)new i(th, th);
/* 255 */     if (!d && this.c == null) throw new AssertionError(); 
/* 256 */     th.setDocumentLocator(this.c);
/* 257 */     th.startDocument();
/* 258 */     this.g = th;
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*     */     try {
/* 263 */       if (this.j == 0) {
/* 264 */         String defaultXSLT = B.f.a(this.b);
/* 265 */         if (defaultXSLT != null) {
/*     */           try {
/* 267 */             a(d.a(this.b.c().c(), defaultXSLT));
/* 268 */           } catch (URISyntaxException e) {
/* 269 */             this.b.a((short)10254, defaultXSLT);
/* 270 */           } catch (IOException e) {
/* 271 */             this.b.a((short)10254, defaultXSLT);
/* 272 */           } catch (TransformerConfigurationException e) {
/* 273 */             this.b.a((short)14337, defaultXSLT);
/*     */           } 
/*     */         }
/* 276 */         if (this.i != null) {
/* 277 */           for (int i = 0; i < this.i.size(); i++) {
/* 278 */             String[] pi = this.i.get(i);
/* 279 */             super.processingInstruction(pi[0], pi[1]);
/*     */           } 
/* 281 */           this.i = null;
/*     */         } 
/* 283 */         this.m = false;
/*     */       } 
/* 285 */       this.j++;
/* 286 */       super.startElement(uri, lName, qName, atts);
/* 287 */     } catch (RuntimeException e) {
/* 288 */       a(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/*     */     try {
/* 294 */       super.characters(ch, start, length);
/* 295 */     } catch (RuntimeException e) {
/* 296 */       a(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/*     */     try {
/* 302 */       super.endElement(uri, lName, qName);
/* 303 */       this.j--;
/* 304 */     } catch (RuntimeException e) {
/* 305 */       a(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endDocument() throws SAXException {
/*     */     try {
/* 311 */       if (this.h != null) {
/* 312 */         this.g.endDocument();
/* 313 */         this.e = this.h;
/* 314 */         this.h = null;
/* 315 */         this.g = null;
/*     */       } 
/* 317 */       super.endDocument();
/* 318 */     } catch (RuntimeException e) {
/* 319 */       a(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(RuntimeException e) throws SAXException {
/* 324 */     if (this.l == null) {
/* 325 */       throw e;
/*     */     }
/* 327 */     short code = 14337;
/* 328 */     String[] args = { this.l.getLocator().getSystemId() };
/* 329 */     String mes = a.b(code, args);
/* 330 */     this.b.a(code, args);
/* 331 */     f.log(Level.WARNING, mes, this.l);
/* 332 */     throw new SAXException(this.l);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */