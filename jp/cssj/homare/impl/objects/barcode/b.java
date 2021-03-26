/*    */ package jp.cssj.homare.impl.objects.barcode;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.homare.css.h;
/*    */ import jp.cssj.homare.impl.objects.barcode.a.a;
/*    */ import jp.cssj.homare.impl.objects.barcode.b.a;
/*    */ import jp.cssj.homare.impl.objects.barcode.c.a;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import org.apache.avalon.framework.configuration.Configuration;
/*    */ import org.apache.avalon.framework.configuration.SAXConfigurationHandler;
/*    */ import org.krysalis.barcode4j.BarcodeClassResolver;
/*    */ import org.krysalis.barcode4j.BarcodeGenerator;
/*    */ import org.krysalis.barcode4j.BarcodeUtil;
/*    */ import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends DefaultHandler
/*    */   implements h
/*    */ {
/* 29 */   private static final BarcodeClassResolver a = (BarcodeClassResolver)new DefaultBarcodeClassResolver()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */   
/*    */   private BarcodeGenerator b;
/*    */ 
/*    */   
/*    */   private String c;
/*    */ 
/*    */   
/*    */   private SAXConfigurationHandler d;
/*    */   
/* 43 */   private int e = 0;
/*    */   
/*    */   public jp.cssj.sakae.c.b.b a(m ua) throws IOException {
/* 46 */     return new a(ua, this.b, this.c);
/*    */   }
/*    */   
/*    */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 50 */     if (lName.equals("barcode")) {
/* 51 */       this.c = atts.getValue("message");
/* 52 */     } else if (this.e == 0) {
/* 53 */       this.e = 1;
/* 54 */       this.d = new SAXConfigurationHandler();
/* 55 */       this.d.startDocument();
/*    */     } 
/* 57 */     if (this.e >= 1) {
/* 58 */       this.d.startElement("", lName, lName, atts);
/* 59 */       this.e++;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 64 */     if (this.e >= 1) {
/* 65 */       this.d.characters(ch, off, len);
/*    */     }
/*    */   }
/*    */   
/*    */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 70 */     if (this.e >= 1) {
/* 71 */       this.d.endElement("", lName, lName);
/* 72 */       this.e--;
/* 73 */       if (this.e == 1) {
/* 74 */         this.e = 0;
/* 75 */         this.d.endDocument();
/* 76 */         Configuration cfg = this.d.getConfiguration();
/*    */         try {
/* 78 */           this.b = BarcodeUtil.createBarcodeGenerator(cfg, a);
/* 79 */         } catch (Exception e) {
/* 80 */           throw new SAXException(e);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */