/*    */ package jp.cssj.homare.xml;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import org.apache.xerces.xni.XMLLocator;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface e
/*    */ {
/* 24 */   public static final ThreadLocal<XMLLocator> a = new ThreadLocal<>();
/*    */   
/*    */   void a(m paramm, b paramb, g paramg) throws SAXException, IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */