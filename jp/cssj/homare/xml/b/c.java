/*     */ package jp.cssj.homare.xml.b;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */ {
/*     */   public static a a(String prefix, String uri) {
/*  34 */     if (!a && prefix == null) throw new AssertionError(); 
/*  35 */     if (!a && uri == null) throw new AssertionError(); 
/*  36 */     return new a(prefix, uri) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  38 */           handler.startPrefixMapping(this.a, this.b);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a(String prefix) {
/*  44 */     if (!a && prefix == null) throw new AssertionError(); 
/*  45 */     return new a(prefix) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  47 */           handler.endPrefixMapping(this.a);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a(String uri, String lName, String qName, Attributes atts) {
/*  53 */     Attributes attsc = new AttributesImpl(atts);
/*  54 */     return new a(uri, lName, qName, attsc) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  56 */           handler.startElement(this.a, this.b, this.c, this.d);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a(String uri, String lName, String qName) {
/*  62 */     return new a(uri, lName, qName) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  64 */           handler.endElement(this.a, this.b, this.c);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a(char[] ch, int off, int len) {
/*  70 */     char[] fch = new char[len];
/*     */     
/*  72 */     System.arraycopy(ch, off, fch, 0, len);
/*  73 */     return new a(fch) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  75 */           handler.characters(this.a, 0, this.a.length);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static a b(char[] ch, int off, int len) {
/*  82 */     char[] fch = new char[len];
/*  83 */     System.arraycopy(ch, off, fch, 0, len);
/*  84 */     return new a(fch) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  86 */           handler.ignorableWhitespace(this.a, 0, this.a.length);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a b(String entity) {
/*  92 */     return new a(entity) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/*  94 */           handler.skippedEntity(this.a);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a(Locator locator) {
/* 100 */     return new a(locator) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 102 */           handler.setDocumentLocator(this.a);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a b(String target, String data) {
/* 108 */     return new a(target, data) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 110 */           handler.processingInstruction(this.a, this.b);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a a() {
/* 116 */     return new a() {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 118 */           ((g)handler).startCDATA();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a b() {
/* 124 */     return new a() {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 126 */           ((g)handler).endCDATA();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a b(String name, String publicId, String systemId) {
/* 132 */     return new a(name, publicId, systemId) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 134 */           ((g)handler).startDTD(this.a, this.b, this.c);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a c() {
/* 140 */     return new a() {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 142 */           ((g)handler).endDTD();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a c(String name) {
/* 148 */     return new a(name) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 150 */           ((g)handler).startEntity(this.a);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a d(String name) {
/* 156 */     return new a(name) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 158 */           ((g)handler).endEntity(this.a);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static a c(char[] ch, int off, int len) {
/* 164 */     char[] fch = new char[len];
/*     */     
/* 166 */     System.arraycopy(ch, off, fch, 0, len);
/* 167 */     return new a(fch) {
/*     */         public void a(ContentHandler handler) throws SAXException {
/* 169 */           ((g)handler).comment(this.a, 0, this.a.length);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     void a(ContentHandler param1ContentHandler) throws SAXException;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */