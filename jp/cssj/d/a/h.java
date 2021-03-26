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
/*    */ class h
/*    */   extends DefaultHandler {
/* 13 */   final n a = new n();
/* 14 */   private StringBuffer b = null;
/* 15 */   private final List<List<i>> c = new ArrayList<>();
/*    */   private final Map<String, f> d;
/*    */   private final URI e;
/* 18 */   private int f = 0;
/*    */   
/*    */   public h(d contents) {
/* 21 */     this.d = contents.q;
/* 22 */     this.e = URI.create(contents.n.d);
/* 23 */     this.c.add(new ArrayList<>());
/*    */   }
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 27 */     if (this.f != 0) {
/* 28 */       this.f++;
/*    */     }
/* 30 */     if (uri.equals("http://www.w3.org/1999/xhtml")) {
/* 31 */       if (this.f <= 0) {
/* 32 */         if (lName.equals("nav")) {
/* 33 */           String type = atts.getValue("http://www.idpf.org/2007/ops", "type");
/* 34 */           if ("toc".equals(type)) {
/* 35 */             this.f = 1;
/*    */           }
/* 37 */         } else if (lName.equals("title")) {
/* 38 */           this.b = new StringBuffer();
/*    */         }
/*    */       
/* 41 */       } else if (lName.equals("a")) {
/* 42 */         String href = atts.getValue("href");
/* 43 */         if (href != null) {
/* 44 */           this.b = new StringBuffer();
/* 45 */           List<i> list = this.c.get(this.c.size() - 2);
/* 46 */           i navPoint = list.get(list.size() - 1);
/* 47 */           navPoint.b = this.e.resolve(href);
/* 48 */           navPoint.c = this.d.get(navPoint.b.getPath());
/*    */         } 
/* 50 */       } else if (lName.equals("li")) {
/* 51 */         i navPoint = new i();
/* 52 */         List<i> list = this.c.get(this.c.size() - 1);
/* 53 */         list.add(navPoint);
/* 54 */         this.c.add(new ArrayList<>());
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 61 */     if (this.b != null) {
/* 62 */       this.b.append(ch, off, len);
/*    */     }
/*    */   }
/*    */   
/*    */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 67 */     if (uri.equals("http://www.w3.org/1999/xhtml")) {
/* 68 */       if (this.f <= 0) {
/* 69 */         if (lName.equals("title")) {
/* 70 */           this.a.a = this.b.toString();
/* 71 */           this.b = null;
/*    */         }
/*    */       
/* 74 */       } else if (lName.equals("li")) {
/* 75 */         List<i> list = this.c.get(this.c.size() - 2);
/* 76 */         i navPoint = list.get(list.size() - 1);
/* 77 */         list = this.c.remove(this.c.size() - 1);
/* 78 */         navPoint.f = list.<i>toArray(new i[list.size()]);
/* 79 */       } else if (this.b != null) {
/*    */         
/* 81 */         if (lName.equals("a")) {
/* 82 */           List<i> list = this.c.get(this.c.size() - 2);
/* 83 */           i navPoint = list.get(list.size() - 1);
/* 84 */           navPoint.a = this.b.toString();
/* 85 */           this.b = null;
/*    */         } 
/*    */       } 
/*    */     }
/* 89 */     if (this.f != 0) {
/* 90 */       this.f--;
/*    */     }
/*    */   }
/*    */   
/*    */   public n a() {
/* 95 */     List<i> list = this.c.get(this.c.size() - 1);
/* 96 */     this.a.b = list.<i>toArray(new i[list.size()]);
/* 97 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */