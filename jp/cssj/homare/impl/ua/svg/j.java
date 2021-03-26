/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URISyntaxException;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.e.e.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import org.apache.batik.bridge.DocumentLoader;
/*    */ import org.apache.batik.bridge.URIResolver;
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGDocument;
/*    */ 
/*    */ public class j
/*    */   extends URIResolver
/*    */ {
/*    */   protected final m a;
/*    */   protected final SVGImageLoader b;
/*    */   
/*    */   public j(SVGDocument doc, DocumentLoader dl, m ua, SVGImageLoader loader) {
/* 24 */     super(doc, dl);
/* 25 */     this.a = ua;
/* 26 */     this.b = loader;
/*    */   }
/*    */   public Node getNode(String uri, Element ref) throws MalformedURLException, IOException, SecurityException {
/*    */     try {
/*    */       ParsedURL pURL;
/* 31 */       String baseURI = getRefererBaseURI(ref);
/* 32 */       if (baseURI != null && baseURI.length() == 0) {
/* 33 */         baseURI = null;
/*    */       }
/* 35 */       if (baseURI == null && uri.charAt(0) == '#') {
/* 36 */         return getNodeByFragment(uri.substring(1), ref);
/*    */       }
/*    */ 
/*    */       
/* 40 */       if (baseURI != null && !uri.startsWith(baseURI)) {
/* 41 */         pURL = new ParsedURL(baseURI, uri);
/*    */       } else {
/* 43 */         pURL = new ParsedURL(uri);
/*    */       } 
/* 45 */       if (this.documentURI == null) {
/* 46 */         this.documentURI = this.document.getURL();
/*    */       }
/*    */       
/* 49 */       String frag = pURL.getRef();
/* 50 */       if (frag != null && this.documentURI != null) {
/* 51 */         ParsedURL pDocURL = new ParsedURL(this.documentURI);
/* 52 */         if (pDocURL.sameFile(pURL)) {
/* 53 */           return this.document.getElementById(frag);
/*    */         }
/*    */       } 
/*    */       
/* 57 */       String purlStr = pURL.toString();
/* 58 */       if (frag != null) {
/* 59 */         purlStr = purlStr.substring(0, purlStr.length() - frag.length() + 1);
/*    */       }
/*    */       
/*    */       try {
/* 63 */         b source = this.a.b(d.a("UTF-8", purlStr));
/*    */         try {
/* 65 */           Document doc = this.b.loadDocument(source);
/* 66 */           if (frag != null) {
/* 67 */             return doc.getElementById(frag);
/*    */           }
/* 69 */           return doc;
/*    */         } finally {
/* 71 */           this.a.a(source);
/*    */         } 
/* 73 */       } catch (URISyntaxException e) {
/* 74 */         throw new MalformedURLException(purlStr);
/*    */       } 
/* 76 */     } catch (IOException e) {
/*    */       
/* 78 */       e.printStackTrace();
/* 79 */       throw e;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */