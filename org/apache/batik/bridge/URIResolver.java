/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class URIResolver
/*     */ {
/*     */   protected SVGOMDocument document;
/*     */   protected String documentURI;
/*     */   protected DocumentLoader documentLoader;
/*     */   
/*     */   public URIResolver(SVGDocument doc, DocumentLoader dl) {
/*  60 */     this.document = (SVGOMDocument)doc;
/*  61 */     this.documentLoader = dl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getElement(String uri, Element ref) throws MalformedURLException, IOException {
/*  75 */     Node n = getNode(uri, ref);
/*  76 */     if (n == null)
/*  77 */       return null; 
/*  78 */     if (n.getNodeType() == 9) {
/*  79 */       throw new IllegalArgumentException();
/*     */     }
/*  81 */     return (Element)n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getNode(String uri, Element ref) throws MalformedURLException, IOException, SecurityException {
/*  96 */     String baseURI = getRefererBaseURI(ref);
/*     */ 
/*     */     
/*  99 */     if (baseURI == null && uri.charAt(0) == '#') {
/* 100 */       return getNodeByFragment(uri.substring(1), ref);
/*     */     }
/*     */     
/* 103 */     ParsedURL purl = new ParsedURL(baseURI, uri);
/*     */ 
/*     */     
/* 106 */     if (this.documentURI == null) {
/* 107 */       this.documentURI = this.document.getURL();
/*     */     }
/* 109 */     String frag = purl.getRef();
/* 110 */     if (frag != null && this.documentURI != null) {
/* 111 */       ParsedURL parsedURL = new ParsedURL(this.documentURI);
/*     */ 
/*     */       
/* 114 */       if (parsedURL.sameFile(purl))
/*     */       {
/* 116 */         return this.document.getElementById(frag);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     ParsedURL pDocURL = null;
/* 124 */     if (this.documentURI != null) {
/* 125 */       pDocURL = new ParsedURL(this.documentURI);
/*     */     }
/*     */     
/* 128 */     UserAgent userAgent = this.documentLoader.getUserAgent();
/* 129 */     userAgent.checkLoadExternalResource(purl, pDocURL);
/*     */     
/* 131 */     String purlStr = purl.toString();
/* 132 */     if (frag != null) {
/* 133 */       purlStr = purlStr.substring(0, purlStr.length() - frag.length() + 1);
/*     */     }
/*     */     
/* 136 */     Document doc = this.documentLoader.loadDocument(purlStr);
/* 137 */     if (frag != null)
/* 138 */       return doc.getElementById(frag); 
/* 139 */     return doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getRefererBaseURI(Element ref) {
/* 146 */     return ((AbstractNode)ref).getBaseURI();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getNodeByFragment(String frag, Element ref) {
/* 157 */     return ref.getOwnerDocument().getElementById(frag);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/URIResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */