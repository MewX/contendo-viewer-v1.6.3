/*     */ package jp.cssj.homare.impl.formatter.document;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.homare.impl.ua.c;
/*     */ import jp.cssj.homare.impl.ua.e;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.b.c;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.homare.xml.c;
/*     */ import jp.cssj.homare.xml.c.c;
/*     */ import jp.cssj.homare.xml.f;
/*     */ import jp.cssj.homare.xml.g;
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
/*     */ {
/*     */   protected final m a;
/*  43 */   private final AttributesImpl b = new AttributesImpl();
/*     */   
/*  45 */   private List<c.a> c = new ArrayList<>();
/*     */   
/*     */   public b(m ua) {
/*  48 */     this.a = ua;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {}
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/*  56 */     if (this.c == null) {
/*  57 */       super.setDocumentLocator(locator);
/*     */     } else {
/*  59 */       this.c.add(c.a(locator));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startCDATA() throws SAXException {
/*  64 */     if (this.c == null) {
/*  65 */       super.startCDATA();
/*     */     } else {
/*  67 */       this.c.add(c.a());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endCDATA() throws SAXException {
/*  72 */     if (this.c == null) {
/*  73 */       super.endCDATA();
/*     */     } else {
/*  75 */       this.c.add(c.b());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  80 */     if (this.c == null) {
/*  81 */       super.startDTD(name, publicId, systemId);
/*     */     } else {
/*  83 */       this.c.add(c.b(name, publicId, systemId));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endDTD() throws SAXException {
/*  88 */     if (this.c == null) {
/*  89 */       super.endDTD();
/*     */     } else {
/*  91 */       this.c.add(c.c());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/*  96 */     if (this.c == null) {
/*  97 */       super.startEntity(name);
/*     */     } else {
/*  99 */       this.c.add(c.c(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/* 104 */     if (this.c == null) {
/* 105 */       super.endEntity(name);
/*     */     } else {
/* 107 */       this.c.add(c.d(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void comment(char[] ch, int off, int len) throws SAXException {
/* 112 */     if (this.c == null) {
/* 113 */       super.comment(ch, off, len);
/*     */     } else {
/* 115 */       this.c.add(c.c(ch, off, len));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 120 */     if (this.c == null) {
/* 121 */       super.startPrefixMapping(prefix, uri);
/*     */     } else {
/* 123 */       this.c.add(c.a(prefix, uri));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 128 */     if (this.c == null) {
/* 129 */       super.endPrefixMapping(prefix);
/*     */     } else {
/* 131 */       this.c.add(c.a(prefix));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/* 136 */     if (this.c == null) {
/* 137 */       super.skippedEntity(name);
/*     */     } else {
/* 139 */       this.c.add(c.b(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 144 */     if (this.c == null) {
/* 145 */       super.ignorableWhitespace(ch, start, length);
/*     */     } else {
/* 147 */       this.c.add(c.b(ch, start, length));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 152 */     if (this.c == null) {
/* 153 */       super.characters(ch, off, len);
/*     */     } else {
/* 155 */       this.c.add(c.a(ch, off, len));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 161 */     if ("jp.cssj.property".equals(target)) {
/* 162 */       if (B.a.a(this.a)) {
/*     */         try {
/* 164 */           d.a(data.toCharArray(), 0, data.length(), this.b);
/* 165 */           String name = this.b.getValue("name");
/* 166 */           String value = this.b.getValue("value");
/* 167 */           if (name != null) {
/* 168 */             this.a.a(name, value);
/*     */           } else {
/* 170 */             this.a.a((short)10245, B.a.a, data);
/*     */           } 
/* 172 */         } catch (ParseException e) {
/* 173 */           this.a.a((short)10245, "jp.cssj.property", data);
/*     */         } 
/* 175 */         this.b.clear();
/*     */       } else {
/* 177 */         this.a.a((short)10255);
/*     */       } 
/*     */     }
/* 180 */     if (this.c == null) {
/* 181 */       super.processingInstruction(target, data);
/*     */     } else {
/* 183 */       this.c.add(c.b(target, data));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 188 */     if (this.c != null) {
/*     */       jp.cssj.homare.xml.c.b b2; f ssh; e e;
/* 190 */       this.a.c().a((byte)2);
/*     */ 
/*     */       
/* 193 */       jp.cssj.homare.xml.a.b b1 = new jp.cssj.homare.xml.a.b(this.a);
/* 194 */       a((g)b1);
/* 195 */       jp.cssj.homare.xml.a.b b3 = b1;
/*     */ 
/*     */       
/* 198 */       String stylesheets = B.c.a(this.a);
/*     */       
/* 200 */       if (stylesheets != null) {
/* 201 */         ssh = new a(stylesheets);
/*     */       } else {
/* 203 */         ssh = null;
/*     */       } 
/*     */ 
/*     */       
/* 207 */       String filters = B.b.a(this.a);
/* 208 */       for (StringTokenizer i = new StringTokenizer(filters); i.hasMoreTokens(); ) {
/* 209 */         c c1; jp.cssj.homare.xml.d.b b4; String filter = i.nextToken();
/* 210 */         if (filter.equals("loose-html")) {
/*     */           
/* 212 */           c c2 = new c(this.a);
/* 213 */           b3.a((g)c2);
/* 214 */           c1 = c2; continue;
/* 215 */         }  if (filter.equals("xslt")) {
/*     */           
/* 217 */           jp.cssj.homare.xml.d.b xsltFilter = new jp.cssj.homare.xml.d.b();
/* 218 */           xsltFilter.a(this.a);
/* 219 */           if (ssh != null) {
/* 220 */             xsltFilter.a(ssh);
/*     */           }
/* 222 */           c1.a((g)xsltFilter);
/* 223 */           b4 = xsltFilter; continue;
/* 224 */         }  if (filter.equals("default-to-xhtml")) {
/*     */           
/* 226 */           jp.cssj.homare.xml.c.b xhtmlnsFilter = new jp.cssj.homare.xml.c.b();
/* 227 */           b4.a((g)xhtmlnsFilter);
/* 228 */           b2 = xhtmlnsFilter; continue;
/*     */         } 
/* 230 */         this.a.a((short)10244, B.b.a, filters);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 236 */       if (this.a.u()) {
/* 237 */         c c1 = new c(this.a);
/*     */       } else {
/* 239 */         e = new e(this.a);
/*     */       } 
/* 241 */       jp.cssj.homare.css.b cssProcessor = new jp.cssj.homare.css.b(this.a, (jp.cssj.homare.b.d.b)e);
/* 242 */       if (ssh != null) {
/* 243 */         cssProcessor.a(ssh);
/*     */       }
/* 245 */       b2.a((g)cssProcessor);
/*     */ 
/*     */       
/* 248 */       for (int j = 0; j < this.c.size(); j++) {
/* 249 */         c.a event = this.c.get(j);
/* 250 */         event.a((ContentHandler)b1);
/*     */       } 
/* 252 */       this.c = null;
/*     */     } 
/*     */     
/* 255 */     super.startElement(uri, lName, qName, atts);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/formatter/document/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */