/*     */ package org.apache.xpath;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
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
/*     */ public class SourceTreeManager
/*     */ {
/*  46 */   private Vector m_sourceTree = new Vector();
/*     */ 
/*     */   
/*     */   URIResolver m_uriResolver;
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  54 */     this.m_sourceTree = new Vector();
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
/*     */   public void setURIResolver(URIResolver resolver) {
/*  68 */     this.m_uriResolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIResolver getURIResolver() {
/*  79 */     return this.m_uriResolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String findURIFromDoc(int owner) {
/*  90 */     int n = this.m_sourceTree.size();
/*     */     
/*  92 */     for (int i = 0; i < n; i++) {
/*     */       
/*  94 */       SourceTree sTree = this.m_sourceTree.elementAt(i);
/*     */       
/*  96 */       if (owner == sTree.m_root) {
/*  97 */         return sTree.m_url;
/*     */       }
/*     */     } 
/* 100 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Source resolveURI(String base, String urlString, SourceLocator locator) throws TransformerException, IOException {
/* 121 */     Source source = null;
/*     */     
/* 123 */     if (null != this.m_uriResolver)
/*     */     {
/* 125 */       source = this.m_uriResolver.resolve(urlString, base);
/*     */     }
/*     */     
/* 128 */     if (null == source) {
/*     */       
/* 130 */       String uri = SystemIDResolver.getAbsoluteURI(urlString, base);
/*     */       
/* 132 */       source = new StreamSource(uri);
/*     */     } 
/*     */     
/* 135 */     return source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeDocumentFromCache(int n) {
/* 145 */     if (-1 == n)
/*     */       return; 
/* 147 */     for (int i = this.m_sourceTree.size() - 1; i >= 0; i--) {
/*     */       
/* 149 */       SourceTree st = this.m_sourceTree.elementAt(i);
/* 150 */       if (st != null && st.m_root == n) {
/*     */         
/* 152 */         this.m_sourceTree.removeElementAt(i);
/*     */         return;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putDocumentInCache(int n, Source source) {
/* 170 */     int cachedNode = getNode(source);
/*     */     
/* 172 */     if (-1 != cachedNode) {
/*     */       
/* 174 */       if (cachedNode != n) {
/* 175 */         throw new RuntimeException("Programmer's Error!  putDocumentInCache found reparse of doc: " + source.getSystemId());
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 181 */     if (null != source.getSystemId())
/*     */     {
/* 183 */       this.m_sourceTree.addElement(new SourceTree(n, source.getSystemId()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNode(Source source) {
/* 201 */     String url = source.getSystemId();
/*     */     
/* 203 */     if (null == url) {
/* 204 */       return -1;
/*     */     }
/* 206 */     int n = this.m_sourceTree.size();
/*     */ 
/*     */     
/* 209 */     for (int i = 0; i < n; i++) {
/*     */       
/* 211 */       SourceTree sTree = this.m_sourceTree.elementAt(i);
/*     */ 
/*     */ 
/*     */       
/* 215 */       if (url.equals(sTree.m_url)) {
/* 216 */         return sTree.m_root;
/*     */       }
/*     */     } 
/*     */     
/* 220 */     return -1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSourceTree(String base, String urlString, SourceLocator locator, XPathContext xctxt) throws TransformerException {
/*     */     
/* 243 */     try { Source source = resolveURI(base, urlString, locator);
/*     */ 
/*     */       
/* 246 */       return getSourceTree(source, locator, xctxt); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 250 */       throw new TransformerException(ioe.getMessage(), locator, ioe); }
/*     */   
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSourceTree(Source source, SourceLocator locator, XPathContext xctxt) throws TransformerException {
/* 274 */     int n = getNode(source);
/*     */     
/* 276 */     if (-1 != n) {
/* 277 */       return n;
/*     */     }
/* 279 */     n = parseToNode(source, locator, xctxt);
/*     */     
/* 281 */     if (-1 != n) {
/* 282 */       putDocumentInCache(n, source);
/*     */     }
/* 284 */     return n;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int parseToNode(Source source, SourceLocator locator, XPathContext xctxt) throws TransformerException {
/*     */     
/*     */     try { DTM dTM;
/* 304 */       Object xowner = xctxt.getOwnerObject();
/*     */       
/* 306 */       if (null != xowner && xowner instanceof DTMWSFilter) {
/*     */         
/* 308 */         dTM = xctxt.getDTM(source, false, (DTMWSFilter)xowner, false, true);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 313 */         dTM = xctxt.getDTM(source, false, null, false, true);
/*     */       } 
/* 315 */       return dTM.getDocument(); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 320 */       throw new TransformerException(e.getMessage(), locator, e); }
/*     */   
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
/*     */   public static XMLReader getXMLReader(Source inputSource, SourceLocator locator) throws TransformerException {
/*     */     
/* 347 */     try { XMLReader reader = (inputSource instanceof SAXSource) ? ((SAXSource)inputSource).getXMLReader() : null;
/*     */ 
/*     */       
/* 350 */       if (null == reader) {
/*     */ 
/*     */         
/* 353 */         try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */           
/* 355 */           factory.setNamespaceAware(true);
/* 356 */           SAXParser jaxpParser = factory.newSAXParser();
/*     */           
/* 358 */           reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*     */         
/*     */         { 
/* 361 */           throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */         
/* 363 */         { throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2) {  }
/* 364 */         catch (AbstractMethodError abstractMethodError) {}
/*     */ 
/*     */         
/* 367 */         if (null == reader) {
/* 368 */           reader = XMLReaderFactory.createXMLReader();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 373 */       try { reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true); } catch (SAXException sAXException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 383 */       return reader; } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 387 */       throw new TransformerException(se.getMessage(), locator, se); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/SourceTreeManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */