/*    */ package jp.cssj.homare.xml;
/*    */ 
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.helpers.AttributesImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public final String a;
/*    */   public final String b;
/*    */   public final String c;
/*    */   
/*    */   public a(String uri, String prefix, String lName) {
/* 16 */     if (!d && uri == null) throw new AssertionError(); 
/* 17 */     if (!d && lName == null) throw new AssertionError(); 
/* 18 */     this.a = uri;
/* 19 */     this.b = lName;
/* 20 */     if (prefix == null) {
/* 21 */       this.c = lName;
/*    */     } else {
/* 23 */       this.c = prefix + ":" + lName;
/*    */     } 
/*    */   }
/*    */   
/*    */   public a(String lName) {
/* 28 */     this("", null, lName);
/*    */   }
/*    */   
/*    */   public String a(Attributes atts) {
/* 32 */     if (this.a.length() == 0) {
/* 33 */       return atts.getValue(this.b);
/*    */     }
/* 35 */     return atts.getValue(this.a, this.b);
/*    */   }
/*    */   
/*    */   public void a(AttributesImpl atts) {
/* 39 */     int ix = atts.getIndex(this.a, this.b);
/* 40 */     if (ix == -1) {
/*    */       return;
/*    */     }
/* 43 */     atts.removeAttribute(ix);
/*    */   }
/*    */   
/*    */   public void a(AttributesImpl atts, String value) {
/* 47 */     atts.addAttribute(this.a, this.b, this.c, "CDATA", value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */