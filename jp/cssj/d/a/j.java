/*    */ package jp.cssj.d.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ class j
/*    */   extends DefaultHandler {
/* 13 */   final n a = new n();
/* 14 */   private StringBuffer b = null;
/*    */   private boolean c = false;
/* 16 */   private final List<List<i>> d = new ArrayList<>();
/*    */   private final Map<String, f> e;
/*    */   private final URI f;
/*    */   
/*    */   public j(d contents) {
/* 21 */     this.e = contents.q;
/* 22 */     this.f = URI.create(contents.n.d);
/* 23 */     this.d.add(new ArrayList<>());
/*    */   }
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 27 */     if (uri.equals("http://www.daisy.org/z3986/2005/ncx/")) {
/* 28 */       if (lName.equals("text")) {
/* 29 */         this.b = new StringBuffer();
/* 30 */         this.c = true;
/* 31 */       } else if (lName.equals("navPoint")) {
/* 32 */         i navPoint = new i();
/* 33 */         navPoint.d = atts.getValue("id");
/* 34 */         navPoint.e = Integer.parseInt(atts.getValue("playOrder"));
/* 35 */         List<i> list = this.d.get(this.d.size() - 1);
/* 36 */         list.add(navPoint);
/* 37 */         this.d.add(new ArrayList<>());
/* 38 */       } else if (lName.equals("content")) {
/* 39 */         List<i> list = this.d.get(this.d.size() - 2);
/* 40 */         i navPoint = list.get(list.size() - 1);
/* 41 */         String src = atts.getValue("src");
/* 42 */         navPoint.b = this.f.resolve(src);
/* 43 */         navPoint.c = this.e.get(navPoint.b.getPath());
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 49 */     if (this.c) {
/* 50 */       this.b.append(ch, off, len);
/*    */     }
/*    */   }
/*    */   
/*    */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 55 */     if (uri.equals("http://www.daisy.org/z3986/2005/ncx/")) {
/* 56 */       if (lName.equals("navPoint")) {
/* 57 */         List<i> list = this.d.get(this.d.size() - 2);
/* 58 */         i navPoint = list.get(list.size() - 1);
/* 59 */         list = this.d.remove(this.d.size() - 1);
/* 60 */         navPoint.f = list.<i>toArray(new i[list.size()]);
/*    */         return;
/*    */       } 
/* 63 */       if (this.b == null) {
/*    */         return;
/*    */       }
/* 66 */       if (lName.equals("text")) {
/* 67 */         this.c = false; return;
/*    */       } 
/* 69 */       if (lName.equals("docTitle") && this.a != null) {
/* 70 */         this.a.a = this.b.toString();
/* 71 */       } else if (lName.equals("navLabel")) {
/* 72 */         List<i> list = this.d.get(this.d.size() - 2);
/* 73 */         i navPoint = list.get(list.size() - 1);
/* 74 */         navPoint.a = this.b.toString();
/*    */       } else {
/*    */         return;
/*    */       } 
/*    */     } else {
/*    */       return;
/*    */     } 
/* 81 */     this.b = null;
/*    */   }
/*    */   
/*    */   public n a() {
/* 85 */     List<i> list = this.d.get(this.d.size() - 1);
/* 86 */     this.a.b = list.<i>toArray(new i[list.size()]);
/* 87 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */