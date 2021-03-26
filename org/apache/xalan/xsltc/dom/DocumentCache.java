/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Date;
/*     */ import java.util.Hashtable;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMCache;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
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
/*     */ public final class DocumentCache
/*     */   implements DOMCache
/*     */ {
/*     */   private int _size;
/*     */   private Hashtable _references;
/*     */   private String[] _URIs;
/*     */   private int _count;
/*     */   private int _current;
/*     */   private SAXParser _parser;
/*     */   private XMLReader _reader;
/*     */   private XSLTCDTMManager _dtmManager;
/*     */   private static final int REFRESH_INTERVAL = 1000;
/*     */   
/*     */   public final class CachedDocument
/*     */   {
/*     */     private long _firstReferenced;
/*     */     private long _lastReferenced;
/*     */     private long _accessCount;
/*     */     private long _lastModified;
/*     */     private long _lastChecked;
/*     */     private long _buildTime;
/*     */     private DOMEnhancedForDTM _dom;
/*     */     private final DocumentCache this$0;
/*     */     
/*     */     public CachedDocument(DocumentCache this$0, String uri) {
/*  84 */       this.this$0 = this$0;
/*     */       this._dom = null;
/*  86 */       long stamp = System.currentTimeMillis();
/*  87 */       this._firstReferenced = stamp;
/*  88 */       this._lastReferenced = stamp;
/*  89 */       this._accessCount = 0L;
/*  90 */       loadDocument(uri);
/*     */       
/*  92 */       this._buildTime = System.currentTimeMillis() - stamp;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void loadDocument(String uri) {
/*     */       
/* 101 */       try { long stamp = System.currentTimeMillis();
/* 102 */         this._dom = (DOMEnhancedForDTM)this.this$0._dtmManager.getDTM(new SAXSource(this.this$0._reader, new InputSource(uri)), false, null, true, false);
/*     */ 
/*     */         
/* 105 */         this._dom.setDocumentURI(uri);
/*     */ 
/*     */ 
/*     */         
/* 109 */         long thisTime = System.currentTimeMillis() - stamp;
/* 110 */         if (this._buildTime > 0L)
/* 111 */         { this._buildTime = this._buildTime + thisTime >>> 1L; }
/*     */         else
/* 113 */         { this._buildTime = thisTime; }  } catch (Exception e)
/*     */       
/*     */       { 
/* 116 */         this._dom = null; }
/*     */     
/*     */     }
/*     */     public DOM getDocument() {
/* 120 */       return (DOM)this._dom;
/*     */     } public long getFirstReferenced() {
/* 122 */       return this._firstReferenced;
/*     */     } public long getLastReferenced() {
/* 124 */       return this._lastReferenced;
/*     */     } public long getAccessCount() {
/* 126 */       return this._accessCount;
/*     */     } public void incAccessCount() {
/* 128 */       this._accessCount++;
/*     */     } public long getLastModified() {
/* 130 */       return this._lastModified;
/*     */     } public void setLastModified(long t) {
/* 132 */       this._lastModified = t;
/*     */     } public long getLatency() {
/* 134 */       return this._buildTime;
/*     */     } public long getLastChecked() {
/* 136 */       return this._lastChecked;
/*     */     } public void setLastChecked(long t) {
/* 138 */       this._lastChecked = t;
/*     */     }
/*     */     public long getEstimatedSize() {
/* 141 */       if (this._dom != null) {
/* 142 */         return (this._dom.getSize() << 5);
/*     */       }
/* 144 */       return 0L;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentCache(int size) throws SAXException {
/* 153 */     this(size, null);
/*     */     
/* 155 */     try { this._dtmManager = XSLTCDTMManager.getDTMManagerClass().newInstance(); } catch (Exception e)
/*     */     
/*     */     { 
/* 158 */       throw new SAXException(e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentCache(int size, XSLTCDTMManager dtmManager) throws SAXException {
/* 166 */     this._dtmManager = dtmManager;
/* 167 */     this._count = 0;
/* 168 */     this._current = 0;
/* 169 */     this._size = size;
/* 170 */     this._references = new Hashtable(this._size + 2);
/* 171 */     this._URIs = new String[this._size];
/*     */ 
/*     */ 
/*     */     
/* 175 */     try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */       
/* 177 */       try { factory.setFeature("http://xml.org/sax/features/namespaces", true); } catch (Exception e)
/*     */       
/*     */       { 
/* 180 */         factory.setNamespaceAware(true); }
/*     */       
/* 182 */       this._parser = factory.newSAXParser();
/* 183 */       this._reader = this._parser.getXMLReader(); } catch (ParserConfigurationException e)
/*     */     
/*     */     { 
/* 186 */       BasisLibrary.runTimeError("NAMESPACES_SUPPORT_ERR");
/* 187 */       System.exit(-1); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final long getLastModified(String uri) {
/*     */     
/* 196 */     try { URL url = new URL(uri);
/* 197 */       URLConnection connection = url.openConnection();
/* 198 */       long timestamp = connection.getLastModified();
/*     */       
/* 200 */       if (timestamp == 0L && 
/* 201 */         "file".equals(url.getProtocol())) {
/* 202 */         File localfile = new File(URLDecoder.decode(url.getFile()));
/* 203 */         timestamp = localfile.lastModified();
/*     */       } 
/*     */       
/* 206 */       return timestamp; } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 210 */       return System.currentTimeMillis(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CachedDocument lookupDocument(String uri) {
/* 218 */     return (CachedDocument)this._references.get(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void insertDocument(String uri, CachedDocument doc) {
/* 225 */     if (this._count < this._size) {
/*     */       
/* 227 */       this._URIs[this._count++] = uri;
/* 228 */       this._current = 0;
/*     */     }
/*     */     else {
/*     */       
/* 232 */       this._references.remove(this._URIs[this._current]);
/*     */       
/* 234 */       this._URIs[this._current] = uri;
/* 235 */       if (++this._current >= this._size) this._current = 0; 
/*     */     } 
/* 237 */     this._references.put(uri, doc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void replaceDocument(String uri, CachedDocument doc) {
/* 244 */     CachedDocument old = (CachedDocument)this._references.get(uri);
/* 245 */     if (doc == null) {
/* 246 */       insertDocument(uri, doc);
/*     */     } else {
/* 248 */       this._references.put(uri, doc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOM retrieveDocument(String baseURI, String href, Translet trs) {
/* 258 */     String uri = href;
/* 259 */     if (baseURI != null && !baseURI.equals("")) {
/*     */       
/* 261 */       try { uri = SystemIDResolver.getAbsoluteURI(uri, baseURI); } catch (TransformerException transformerException) {}
/*     */     }
/*     */ 
/*     */     
/*     */     CachedDocument doc;
/*     */ 
/*     */     
/* 268 */     if ((doc = lookupDocument(uri)) == null) {
/* 269 */       doc = new CachedDocument(this, uri);
/* 270 */       if (doc == null) return null; 
/* 271 */       doc.setLastModified(getLastModified(uri));
/* 272 */       insertDocument(uri, doc);
/*     */     }
/*     */     else {
/*     */       
/* 276 */       long now = System.currentTimeMillis();
/* 277 */       long chk = doc.getLastChecked();
/* 278 */       doc.setLastChecked(now);
/*     */       
/* 280 */       if (now > chk + 1000L) {
/* 281 */         doc.setLastChecked(now);
/* 282 */         long last = getLastModified(uri);
/*     */         
/* 284 */         if (last > doc.getLastModified()) {
/* 285 */           doc = new CachedDocument(this, uri);
/* 286 */           if (doc == null) return null; 
/* 287 */           doc.setLastModified(getLastModified(uri));
/* 288 */           replaceDocument(uri, doc);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 295 */     DOM dom = doc.getDocument();
/*     */ 
/*     */ 
/*     */     
/* 299 */     if (dom == null) return null;
/*     */     
/* 301 */     doc.incAccessCount();
/*     */     
/* 303 */     AbstractTranslet translet = (AbstractTranslet)trs;
/*     */ 
/*     */ 
/*     */     
/* 307 */     translet.prepassDocument(dom);
/*     */     
/* 309 */     return doc.getDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getStatistics(PrintWriter out) {
/* 316 */     out.println("<h2>DOM cache statistics</h2><center><table border=\"2\"><tr><td><b>Document URI</b></td><td><center><b>Build time</b></center></td><td><center><b>Access count</b></center></td><td><center><b>Last accessed</b></center></td><td><center><b>Last modified</b></center></td></tr>");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     for (int i = 0; i < this._count; i++) {
/* 324 */       CachedDocument doc = (CachedDocument)this._references.get(this._URIs[i]);
/* 325 */       out.print("<tr><td><a href=\"" + this._URIs[i] + "\">" + "<font size=-1>" + this._URIs[i] + "</font></a></td>");
/*     */       
/* 327 */       out.print("<td><center>" + doc.getLatency() + "ms</center></td>");
/* 328 */       out.print("<td><center>" + doc.getAccessCount() + "</center></td>");
/* 329 */       out.print("<td><center>" + new Date(doc.getLastReferenced()) + "</center></td>");
/*     */       
/* 331 */       out.print("<td><center>" + new Date(doc.getLastModified()) + "</center></td>");
/*     */       
/* 333 */       out.println("</tr>");
/*     */     } 
/*     */     
/* 336 */     out.println("</table></center>");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/DocumentCache.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */