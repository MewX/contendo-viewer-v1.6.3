/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
/*     */ import org.apache.batik.dom.svg.SVGDocumentFactory;
/*     */ import org.apache.batik.dom.util.DocumentDescriptor;
/*     */ import org.apache.batik.util.CleanerThread;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class DocumentLoader
/*     */ {
/*     */   protected SVGDocumentFactory documentFactory;
/*  55 */   protected HashMap cacheMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserAgent userAgent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DocumentLoader() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentLoader(UserAgent userAgent) {
/*  72 */     this.userAgent = userAgent;
/*  73 */     this.documentFactory = (SVGDocumentFactory)new SAXSVGDocumentFactory(userAgent.getXMLParserClassName(), true);
/*     */     
/*  75 */     this.documentFactory.setValidating(userAgent.isXMLParserValidating());
/*     */   }
/*     */   public Document checkCache(String uri) {
/*     */     DocumentState state;
/*  79 */     int n = uri.lastIndexOf('/');
/*  80 */     if (n == -1)
/*  81 */       n = 0; 
/*  82 */     n = uri.indexOf('#', n);
/*  83 */     if (n != -1) {
/*  84 */       uri = uri.substring(0, n);
/*     */     }
/*     */     
/*  87 */     synchronized (this.cacheMap) {
/*  88 */       state = (DocumentState)this.cacheMap.get(uri);
/*     */     } 
/*  90 */     if (state != null)
/*  91 */       return state.getDocument(); 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document loadDocument(String uri) throws IOException {
/* 102 */     Document ret = checkCache(uri);
/* 103 */     if (ret != null) {
/* 104 */       return ret;
/*     */     }
/* 106 */     SVGDocument document = this.documentFactory.createSVGDocument(uri);
/*     */     
/* 108 */     DocumentDescriptor desc = this.documentFactory.getDocumentDescriptor();
/* 109 */     DocumentState state = new DocumentState(uri, (Document)document, desc);
/* 110 */     synchronized (this.cacheMap) {
/* 111 */       this.cacheMap.put(uri, state);
/*     */     } 
/*     */     
/* 114 */     return state.getDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document loadDocument(String uri, InputStream is) throws IOException {
/* 125 */     Document ret = checkCache(uri);
/* 126 */     if (ret != null) {
/* 127 */       return ret;
/*     */     }
/* 129 */     SVGDocument document = this.documentFactory.createSVGDocument(uri, is);
/*     */     
/* 131 */     DocumentDescriptor desc = this.documentFactory.getDocumentDescriptor();
/* 132 */     DocumentState state = new DocumentState(uri, (Document)document, desc);
/* 133 */     synchronized (this.cacheMap) {
/* 134 */       this.cacheMap.put(uri, state);
/*     */     } 
/*     */     
/* 137 */     return state.getDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserAgent getUserAgent() {
/* 144 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 152 */     synchronized (this.cacheMap) {
/* 153 */       this.cacheMap.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber(Element e) {
/*     */     DocumentState state;
/* 166 */     String uri = ((SVGDocument)e.getOwnerDocument()).getURL();
/*     */     
/* 168 */     synchronized (this.cacheMap) {
/* 169 */       state = (DocumentState)this.cacheMap.get(uri);
/*     */     } 
/* 171 */     if (state == null) {
/* 172 */       return -1;
/*     */     }
/* 174 */     return state.desc.getLocationLine(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class DocumentState
/*     */     extends CleanerThread.SoftReferenceCleared
/*     */   {
/*     */     private String uri;
/*     */ 
/*     */     
/*     */     private DocumentDescriptor desc;
/*     */ 
/*     */     
/*     */     public DocumentState(String uri, Document document, DocumentDescriptor desc) {
/* 189 */       super(document);
/* 190 */       this.uri = uri;
/* 191 */       this.desc = desc;
/*     */     }
/*     */     
/*     */     public void cleared() {
/* 195 */       synchronized (DocumentLoader.this.cacheMap) {
/* 196 */         DocumentLoader.this.cacheMap.remove(this.uri);
/*     */       } 
/*     */     }
/*     */     
/*     */     public DocumentDescriptor getDocumentDescriptor() {
/* 201 */       return this.desc;
/*     */     }
/*     */     
/*     */     public String getURI() {
/* 205 */       return this.uri;
/*     */     }
/*     */     
/*     */     public Document getDocument() {
/* 209 */       return (Document)get();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DocumentLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */