/*    */ package jp.cssj.d.a;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ class c
/*    */   extends DefaultHandler
/*    */ {
/* 14 */   final List<b.a> a = new ArrayList<>();
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 17 */     if (!lName.equals("rootfile")) {
/*    */       return;
/*    */     }
/* 20 */     b.a rootfile = new b.a();
/* 21 */     rootfile.a = atts.getValue("media-type");
/* 22 */     rootfile.b = atts.getValue("full-path");
/* 23 */     this.a.add(rootfile);
/*    */   }
/*    */   
/*    */   public b a() {
/* 27 */     b container = new b();
/* 28 */     container.a = this.a.<b.a>toArray(new b.a[this.a.size()]);
/* 29 */     return container;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */