/*     */ package jp.cssj.d.a.a;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.d.a.f;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends XMLFilterImpl
/*     */ {
/*     */   boolean a;
/*     */   final boolean b;
/*  19 */   List<Attributes> c = new ArrayList<>();
/*  20 */   final AttributesImpl d = new AttributesImpl();
/*     */   final f e;
/*     */   
/*     */   public a(ContentHandler thandler, f item, boolean vertical) {
/*  24 */     setContentHandler(thandler);
/*  25 */     this.e = item;
/*  26 */     this.b = vertical;
/*     */   }
/*     */   
/*     */   public a(ContentHandler thandler, boolean vertical) {
/*  30 */     this(thandler, (f)null, vertical);
/*     */   }
/*     */   
/*     */   public a(XMLReader reader, f item, boolean vertical) {
/*  34 */     super(reader);
/*  35 */     this.e = item;
/*  36 */     this.b = vertical;
/*     */   }
/*     */   
/*     */   public a(XMLReader reader, boolean vertical) {
/*  40 */     this(reader, (f)null, vertical);
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/*  44 */     if (this.b && !this.a) {
/*  45 */       b.a(ch, off, len, getContentHandler());
/*     */       return;
/*     */     } 
/*  48 */     super.characters(ch, off, len);
/*     */   }
/*     */   
/*     */   private static boolean a(String lName, Attributes atts) {
/*  52 */     String clazz = atts.getValue("class");
/*  53 */     if (lName.equals("style") || lName.equals("script")) {
/*  54 */       return true;
/*     */     }
/*  56 */     if (clazz == null) {
/*  57 */       return false;
/*     */     }
/*  59 */     if (clazz.indexOf("tcy") != -1 || clazz.indexOf("pre") != -1) {
/*  60 */       return true;
/*     */     }
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*  66 */     boolean inBody = lName.equals("body");
/*  67 */     if (inBody) {
/*     */       String type; StringBuffer classBuff;
/*  69 */       if (this.e != null && this.e.f != null && this.e.f.a != null) {
/*  70 */         type = this.e.f.a;
/*     */       } else {
/*  72 */         type = "text";
/*     */       } 
/*  74 */       this.d.setAttributes(atts);
/*  75 */       atts = this.d;
/*  76 */       int classIndex = this.d.getIndex("", "class");
/*     */       
/*  78 */       if (classIndex != -1) {
/*  79 */         classBuff = new StringBuffer(this.d.getValue(classIndex));
/*  80 */         this.d.removeAttribute(classIndex);
/*  81 */         classBuff.append(' ');
/*     */       } else {
/*  83 */         classBuff = new StringBuffer();
/*     */       } 
/*  85 */       classBuff.append("x-epub-").append(type);
/*  86 */       this.d.addAttribute("", "class", "class", "CDATA", classBuff.toString());
/*     */     } 
/*  88 */     this.c.add(new AttributesImpl(atts));
/*  89 */     if (a(lName, atts)) {
/*  90 */       this.a = true;
/*     */     }
/*  92 */     super.startElement(uri, lName, qName, atts);
/*  93 */     if (inBody) {
/*  94 */       this.d.clear();
/*  95 */       this.d.addAttribute("", "id", "id", "CDATA", "x-epub-left-nombre");
/*  96 */       super.startElement("http://www.w3.org/1999/xhtml", "div", "div", this.d);
/*  97 */       super.endElement("http://www.w3.org/1999/xhtml", "div", "div");
/*  98 */       this.d.clear();
/*  99 */       this.d.addAttribute("", "id", "id", "CDATA", "x-epub-right-nombre");
/* 100 */       super.startElement("http://www.w3.org/1999/xhtml", "div", "div", this.d);
/* 101 */       super.endElement("http://www.w3.org/1999/xhtml", "div", "div");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 106 */     Attributes atts = this.c.remove(this.c.size() - 1);
/* 107 */     if (a(lName, atts)) {
/* 108 */       this.a = false;
/*     */     }
/* 110 */     super.endElement(uri, lName, qName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */