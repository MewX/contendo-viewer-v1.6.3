/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import jp.cssj.e.e.d;
/*    */ import org.apache.batik.util.AbstractParsedURLProtocolHandler;
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ import org.apache.batik.util.ParsedURLData;
/*    */ 
/*    */ class e
/*    */   extends AbstractParsedURLProtocolHandler
/*    */ {
/* 13 */   public static final e a = new e();
/*    */   
/*    */   private e() {
/* 16 */     super(null);
/*    */   }
/*    */   
/*    */   public ParsedURLData parseURL(String url) {
/* 20 */     ParsedURLData pURL = a();
/* 21 */     if (url == null) {
/* 22 */       return pURL;
/*    */     }
/*    */     try {
/* 25 */       URI uri = d.a("UTF-8", url);
/* 26 */       a(pURL, uri);
/* 27 */       return pURL;
/* 28 */     } catch (URISyntaxException ex) {
/* 29 */       throw new RuntimeException(ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ParsedURLData parseURL(ParsedURL base, String href) {
/* 34 */     ParsedURLData pURL = a();
/*    */     try {
/*    */       URI uri;
/* 37 */       if (base == null) {
/* 38 */         if (href == null) {
/* 39 */           return pURL;
/*    */         }
/* 41 */         uri = d.a("UTF-8", href);
/*    */       } else {
/* 43 */         uri = d.a("UTF-8", base.toString());
/* 44 */         if (href != null) {
/* 45 */           uri = uri.resolve(href);
/*    */         }
/*    */       } 
/* 48 */       a(pURL, uri);
/* 49 */       return pURL;
/* 50 */     } catch (URISyntaxException ex) {
/* 51 */       throw new RuntimeException(ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void a(ParsedURLData pURL, URI uri) {
/* 56 */     pURL.protocol = uri.getScheme();
/* 57 */     pURL.host = uri.getHost();
/* 58 */     pURL.port = uri.getPort();
/* 59 */     pURL.path = uri.getPath();
/* 60 */     pURL.ref = uri.getFragment();
/*    */   }
/*    */   
/*    */   protected ParsedURLData a() {
/* 64 */     return new ParsedURLData(this) {
/*    */         public boolean complete() {
/* 66 */           return true;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */