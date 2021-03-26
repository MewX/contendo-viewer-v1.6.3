/*    */ package jp.cssj.homare.xml.a;
/*    */ 
/*    */ import jp.cssj.homare.css.e.e;
/*    */ import jp.cssj.homare.css.f.G;
/*    */ import jp.cssj.homare.ua.a;
/*    */ import jp.cssj.homare.ua.h;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.homare.xml.c;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.ContentHandler;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends c
/*    */ {
/*    */   protected final m a;
/*    */   
/*    */   public b(m ua) {
/* 25 */     this.a = ua;
/*    */   }
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 29 */     if (uri.equals("http://www.cssj.jp/ns/cssjml")) {
/*    */       
/* 31 */       if (lName.equals(a.e.b)) {
/* 32 */         h pageRef = this.a.a().c();
/* 33 */         if (pageRef != null) {
/* 34 */           String counter = atts.getValue("counter");
/* 35 */           if (counter != null) {
/*    */             short type;
/* 37 */             String typeStr = atts.getValue("counter");
/* 38 */             if (typeStr == null) {
/* 39 */               type = 4;
/*    */             } else {
/* 41 */               G listStyle = e.a(typeStr);
/* 42 */               if (listStyle == null) {
/* 43 */                 type = 4;
/*    */               } else {
/* 45 */                 type = listStyle.b();
/*    */               } 
/*    */             } 
/* 48 */             pageRef.a((ContentHandler)this.e, counter, type);
/*    */           } 
/*    */         }  return;
/*    */       } 
/* 52 */       if (lName.equals(a.h.b)) {
/* 53 */         String type = atts.getValue("type");
/* 54 */         if (type.equals("error"))
/* 55 */           throw new AssertionError("FAIL"); 
/* 56 */         if (type.equals("graphics"))
/* 57 */           throw new c("FAIL"); 
/* 58 */         if (type.equals("runtime"))
/* 59 */           throw new NullPointerException("FAIL"); 
/* 60 */         if (type.equals("sax"))
/* 61 */           throw new SAXException("FAIL"); 
/* 62 */         if (type.equals("abort-soft")) {
/* 63 */           this.a.a((short)4097);
/* 64 */           throw new a((byte)1);
/* 65 */         }  if (type.equals("abort-hard")) {
/* 66 */           this.a.a((short)4097);
/* 67 */           throw new a((byte)2);
/*    */         } 
/*    */       } 
/*    */     } 
/* 71 */     super.startElement(uri, lName, qName, atts);
/*    */   }
/*    */   
/*    */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 75 */     if (uri.equals("http://www.cssj.jp/ns/cssjml") && 
/* 76 */       lName.equals(a.e.b)) {
/*    */       return;
/*    */     }
/*    */     
/* 80 */     super.endElement(uri, lName, qName);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */