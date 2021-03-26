/*    */ package jp.cssj.homare.impl.formatter.document;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.b.d;
/*    */ import jp.cssj.c.b;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.homare.a.a;
/*    */ import jp.cssj.homare.formatter.Formatter;
/*    */ import jp.cssj.homare.ua.a;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.homare.xml.ParserFactory;
/*    */ import jp.cssj.homare.xml.e;
/*    */ import jp.cssj.homare.xml.g;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DocumentFormatter
/*    */   implements Formatter
/*    */ {
/* 23 */   private static final Logger a = Logger.getLogger(DocumentFormatter.class.getName());
/*    */   
/*    */   public boolean match(b key) {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   public void format(b source, m ua) throws a, d {
/*    */     try {
/* 31 */       String mimeType = source.c();
/* 32 */       ParserFactory pf = (ParserFactory)b.a().a(ParserFactory.class, mimeType);
/* 33 */       e parser = pf.createParser();
/* 34 */       b b1 = new b(ua);
/* 35 */       parser.a(ua, source, (g)b1);
/* 36 */     } catch (IOException e) {
/* 37 */       short code = 12290;
/* 38 */       String[] args = { e.getMessage() };
/* 39 */       String mes = a.b(code, args);
/* 40 */       ua.a(code, args);
/* 41 */       a.log(Level.WARNING, mes, e);
/* 42 */       throw new d(code, args, mes);
/* 43 */     } catch (SAXException e) {
/* 44 */       short code = 14339;
/* 45 */       String[] args = { e.getMessage() };
/* 46 */       String mes = a.b(code, args);
/* 47 */       ua.a(code, args);
/* 48 */       a.log(Level.WARNING, mes, e);
/* 49 */       throw new d(code, args, mes);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/formatter/document/DocumentFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */