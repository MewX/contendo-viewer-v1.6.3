/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.Properties;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGDocumentFactory;
/*     */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*     */ import org.apache.batik.util.MimeTypeConstants;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class SAXSVGDocumentFactory
/*     */   extends SAXDocumentFactory
/*     */   implements SVGDocumentFactory
/*     */ {
/*  53 */   public static final Object LOCK = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String KEY_PUBLIC_IDS = "publicIds";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String KEY_SKIPPABLE_PUBLIC_IDS = "skippablePublicIds";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String KEY_SKIP_DTD = "skipDTD";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String KEY_SYSTEM_ID = "systemId.";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String DTDIDS = "org.apache.batik.anim.dom.resources.dtdids";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String HTTP_CHARSET = "charset";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String dtdids;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String skippable_dtdids;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String skip_dtd;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Properties dtdProps;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SAXSVGDocumentFactory(String parser) {
/* 111 */     super(SVGDOMImplementation.getDOMImplementation(), parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SAXSVGDocumentFactory(String parser, boolean dd) {
/* 120 */     super(SVGDOMImplementation.getDOMImplementation(), parser, dd);
/*     */   }
/*     */   
/*     */   public SVGDocument createSVGDocument(String uri) throws IOException {
/* 124 */     return (SVGDocument)createDocument(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGDocument createSVGDocument(String uri, InputStream inp) throws IOException {
/* 135 */     return (SVGDocument)createDocument(uri, inp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGDocument createSVGDocument(String uri, Reader r) throws IOException {
/* 146 */     return (SVGDocument)createDocument(uri, r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri) throws IOException {
/* 156 */     ParsedURL purl = new ParsedURL(uri);
/*     */     
/* 158 */     InputStream is = purl.openStream(MimeTypeConstants.MIME_TYPES_SVG_LIST.iterator());
/*     */     
/* 160 */     uri = purl.getPostConnectionURL();
/*     */     
/* 162 */     InputSource isrc = new InputSource(is);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     String contentType = purl.getContentType();
/* 169 */     int cindex = -1;
/* 170 */     if (contentType != null) {
/* 171 */       contentType = contentType.toLowerCase();
/* 172 */       cindex = contentType.indexOf("charset");
/*     */     } 
/*     */     
/* 175 */     String charset = null;
/* 176 */     if (cindex != -1) {
/* 177 */       int i = cindex + "charset".length();
/* 178 */       int eqIdx = contentType.indexOf('=', i);
/* 179 */       if (eqIdx != -1) {
/* 180 */         eqIdx++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 187 */         int idx = contentType.indexOf(',', eqIdx);
/* 188 */         int semiIdx = contentType.indexOf(';', eqIdx);
/* 189 */         if (semiIdx != -1 && (semiIdx < idx || idx == -1))
/* 190 */           idx = semiIdx; 
/* 191 */         if (idx != -1) {
/* 192 */           charset = contentType.substring(eqIdx, idx);
/*     */         } else {
/* 194 */           charset = contentType.substring(eqIdx);
/* 195 */         }  charset = charset.trim();
/* 196 */         isrc.setEncoding(charset);
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     isrc.setSystemId(uri);
/*     */     
/* 202 */     SVGOMDocument doc = (SVGOMDocument)createDocument("http://www.w3.org/2000/svg", "svg", uri, isrc);
/*     */     
/* 204 */     doc.setParsedURL(new ParsedURL(uri));
/* 205 */     doc.setDocumentInputEncoding(charset);
/* 206 */     doc.setXmlStandalone(this.isStandalone);
/* 207 */     doc.setXmlVersion(this.xmlVersion);
/*     */     
/* 209 */     return (Document)doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri, InputStream inp) throws IOException {
/*     */     Document doc;
/* 221 */     InputSource is = new InputSource(inp);
/* 222 */     is.setSystemId(uri);
/*     */     
/*     */     try {
/* 225 */       doc = createDocument("http://www.w3.org/2000/svg", "svg", uri, is);
/*     */       
/* 227 */       if (uri != null) {
/* 228 */         ((SVGOMDocument)doc).setParsedURL(new ParsedURL(uri));
/*     */       }
/*     */       
/* 231 */       AbstractDocument d = (AbstractDocument)doc;
/* 232 */       d.setDocumentURI(uri);
/* 233 */       d.setXmlStandalone(this.isStandalone);
/* 234 */       d.setXmlVersion(this.xmlVersion);
/* 235 */     } catch (MalformedURLException e) {
/* 236 */       throw new IOException(e.getMessage());
/*     */     } 
/* 238 */     return doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String uri, Reader r) throws IOException {
/*     */     Document doc;
/* 250 */     InputSource is = new InputSource(r);
/* 251 */     is.setSystemId(uri);
/*     */     
/*     */     try {
/* 254 */       doc = createDocument("http://www.w3.org/2000/svg", "svg", uri, is);
/*     */       
/* 256 */       if (uri != null) {
/* 257 */         ((SVGOMDocument)doc).setParsedURL(new ParsedURL(uri));
/*     */       }
/*     */       
/* 260 */       AbstractDocument d = (AbstractDocument)doc;
/* 261 */       d.setDocumentURI(uri);
/* 262 */       d.setXmlStandalone(this.isStandalone);
/* 263 */       d.setXmlVersion(this.xmlVersion);
/* 264 */     } catch (MalformedURLException e) {
/* 265 */       throw new IOException(e.getMessage());
/*     */     } 
/* 267 */     return doc;
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
/*     */   public Document createDocument(String ns, String root, String uri) throws IOException {
/* 279 */     if (!"http://www.w3.org/2000/svg".equals(ns) || !"svg".equals(root))
/*     */     {
/* 281 */       throw new RuntimeException("Bad root element");
/*     */     }
/* 283 */     return createDocument(uri);
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
/*     */   public Document createDocument(String ns, String root, String uri, InputStream is) throws IOException {
/* 296 */     if (!"http://www.w3.org/2000/svg".equals(ns) || !"svg".equals(root))
/*     */     {
/* 298 */       throw new RuntimeException("Bad root element");
/*     */     }
/* 300 */     return createDocument(uri, is);
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
/*     */   public Document createDocument(String ns, String root, String uri, Reader r) throws IOException {
/* 313 */     if (!"http://www.w3.org/2000/svg".equals(ns) || !"svg".equals(root))
/*     */     {
/* 315 */       throw new RuntimeException("Bad root element");
/*     */     }
/* 317 */     return createDocument(uri, r);
/*     */   }
/*     */   
/*     */   public DOMImplementation getDOMImplementation(String ver) {
/* 321 */     if (ver == null || ver.length() == 0 || ver.equals("1.0") || ver.equals("1.1"))
/*     */     {
/* 323 */       return SVGDOMImplementation.getDOMImplementation(); } 
/* 324 */     if (ver.equals("1.2")) {
/* 325 */       return SVG12DOMImplementation.getDOMImplementation();
/*     */     }
/* 327 */     throw new RuntimeException("Unsupport SVG version '" + ver + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {
/* 335 */     super.startDocument();
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
/*     */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/*     */     try {
/* 348 */       synchronized (LOCK) {
/*     */         
/* 350 */         if (dtdProps == null) {
/* 351 */           dtdProps = new Properties();
/*     */           try {
/* 353 */             Class<SAXSVGDocumentFactory> cls = SAXSVGDocumentFactory.class;
/* 354 */             InputStream is = cls.getResourceAsStream("resources/dtdids.properties");
/*     */             
/* 356 */             dtdProps.load(is);
/* 357 */           } catch (IOException ioe) {
/* 358 */             throw new SAXException(ioe);
/*     */           } 
/*     */         } 
/*     */         
/* 362 */         if (dtdids == null) {
/* 363 */           dtdids = dtdProps.getProperty("publicIds");
/*     */         }
/* 365 */         if (skippable_dtdids == null) {
/* 366 */           skippable_dtdids = dtdProps.getProperty("skippablePublicIds");
/*     */         }
/*     */         
/* 369 */         if (skip_dtd == null) {
/* 370 */           skip_dtd = dtdProps.getProperty("skipDTD");
/*     */         }
/*     */       } 
/* 373 */       if (publicId == null) {
/* 374 */         return null;
/*     */       }
/* 376 */       if (!this.isValidating && skippable_dtdids.indexOf(publicId) != -1)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 382 */         return new InputSource(new StringReader(skip_dtd));
/*     */       }
/*     */       
/* 385 */       if (dtdids.indexOf(publicId) != -1) {
/* 386 */         String localSystemId = dtdProps.getProperty("systemId." + publicId.replace(' ', '_'));
/*     */ 
/*     */ 
/*     */         
/* 390 */         if (localSystemId != null && !"".equals(localSystemId)) {
/* 391 */           return new InputSource(getClass().getResource(localSystemId).toString());
/*     */         }
/*     */       }
/*     */     
/* 395 */     } catch (MissingResourceException e) {
/* 396 */       throw new SAXException(e);
/*     */     } 
/*     */     
/* 399 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SAXSVGDocumentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */