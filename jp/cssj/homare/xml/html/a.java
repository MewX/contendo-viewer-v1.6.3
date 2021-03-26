/*    */ package jp.cssj.homare.xml.html;
/*    */ 
/*    */ import org.apache.xerces.xni.Augmentations;
/*    */ import org.apache.xerces.xni.QName;
/*    */ import org.apache.xerces.xni.XMLAttributes;
/*    */ import org.apache.xerces.xni.XMLString;
/*    */ import org.apache.xerces.xni.XNIException;
/*    */ import org.cyberneko.html.filters.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class a
/*    */   extends a
/*    */ {
/*    */   private boolean a = false;
/*    */   private static final String b = "XXXXX";
/*    */   
/*    */   public void startElement(QName element, XMLAttributes atts, Augmentations augs) throws XNIException {
/* 25 */     if (jp.cssj.homare.xml.c.a.d.a(element.uri, element.localpart)) {
/* 26 */       this.a = true;
/*    */     }
/* 28 */     if (element.localpart.length() == 0) {
/* 29 */       element.localpart = "XXXXX";
/* 30 */       element.rawname += element.localpart;
/*    */     } 
/* 32 */     for (int i = 0; i < atts.getLength(); i++) {
/* 33 */       if (atts.getLocalName(i).length() == 0) {
/* 34 */         QName qName = new QName();
/* 35 */         qName.prefix = atts.getPrefix(i);
/* 36 */         qName.localpart = "XXXXX";
/* 37 */         if (qName.prefix.length() == 0) {
/* 38 */           qName.rawname = qName.localpart;
/*    */         } else {
/* 40 */           qName.rawname = qName.prefix + ":" + qName.localpart;
/*    */         } 
/* 42 */         qName.uri = atts.getURI(i);
/* 43 */         atts.setName(i, qName);
/*    */       } 
/*    */     } 
/* 46 */     super.startElement(element, atts, augs);
/*    */   }
/*    */   
/*    */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 50 */     if (this.a) {
/* 51 */       characters(text, augs);
/*    */       return;
/*    */     } 
/* 54 */     super.comment(text, augs);
/*    */   }
/*    */   
/*    */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 58 */     this.a = false;
/* 59 */     if (element.localpart.length() == 0) {
/* 60 */       element.localpart = "XXXXX";
/* 61 */       element.rawname += element.localpart;
/*    */     } 
/* 63 */     super.endElement(element, augs);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/html/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */