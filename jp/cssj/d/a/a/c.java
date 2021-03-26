/*    */ package jp.cssj.d.a.a;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ public class c
/*    */   extends DefaultHandler {
/* 12 */   private Map<String, String> a = new HashMap<>();
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 15 */     if (lName.equals("meta")) {
/* 16 */       String name = atts.getValue("name");
/* 17 */       if (name != null) {
/* 18 */         String content = atts.getValue("content");
/* 19 */         if (content != null) {
/* 20 */           this.a.put(name, content);
/*    */         }
/*    */       } 
/* 23 */     } else if (lName.equals("body")) {
/* 24 */       throw new SAXException();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 29 */     if (lName.equals("head")) {
/* 30 */       throw new SAXException();
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, String> a() {
/* 35 */     return Collections.unmodifiableMap(this.a);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */