/*     */ package net.a.a.h;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import net.a.a.g;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSource;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
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
/*     */ @ThreadSafe
/*     */ public final class a
/*     */ {
/*     */   private static final int b = 128;
/*     */   private static final String c = "Bad StreamSource: ";
/*     */   private static final String d = "content.xml";
/*     */   private static final String e = "Cannot handle Source: ";
/*     */   private static final Log f;
/*     */   private final Map<Long, Reference<DocumentBuilder>> g;
/*     */   
/*     */   private static final class a
/*     */     implements ErrorHandler
/*     */   {
/*     */     public void error(SAXParseException param1SAXParseException) throws SAXException {
/*  75 */       a.d().warn(param1SAXParseException);
/*     */     }
/*     */ 
/*     */     
/*     */     public void fatalError(SAXParseException param1SAXParseException) throws SAXException {
/*  80 */       throw param1SAXParseException;
/*     */     }
/*     */ 
/*     */     
/*     */     public void warning(SAXParseException param1SAXParseException) throws SAXException {
/*  85 */       a.d().debug(param1SAXParseException);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class c extends FilterInputStream {
/*     */     protected c(InputStream param1InputStream) {
/*  91 */       super(param1InputStream);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() throws IOException {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class b
/*     */   {
/* 113 */     private static final a a = new a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 122 */     f = LogFactory.getLog(a.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected a() {
/* 130 */     this.g = new ConcurrentHashMap<Long, Reference<DocumentBuilder>>();
/*     */   }
/*     */   
/*     */   private DocumentBuilder e() {
/*     */     DocumentBuilder documentBuilder;
/*     */     try {
/*     */       try {
/* 137 */         documentBuilder = a(true);
/* 138 */       } catch (UnsupportedOperationException unsupportedOperationException) {
/* 139 */         f.debug("Unsupported Operation: " + unsupportedOperationException
/* 140 */             .getMessage());
/* 141 */         documentBuilder = a(false);
/* 142 */       } catch (ParserConfigurationException parserConfigurationException) {
/* 143 */         f.debug("ParserConfigurationException: " + parserConfigurationException
/* 144 */             .getMessage());
/* 145 */         documentBuilder = a(false);
/*     */       } 
/* 147 */       documentBuilder.setEntityResolver((EntityResolver)new g());
/* 148 */       documentBuilder.setErrorHandler(new a());
/* 149 */     } catch (ParserConfigurationException parserConfigurationException) {
/* 150 */       f.warn("Could not create Parser: " + parserConfigurationException.getMessage());
/* 151 */       if (!a) throw new AssertionError("Could not create Parser"); 
/* 152 */       documentBuilder = null;
/*     */     } 
/* 154 */     return documentBuilder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private DocumentBuilder a(boolean paramBoolean) throws ParserConfigurationException {
/* 160 */     DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 161 */     documentBuilderFactory.setNamespaceAware(true);
/* 162 */     if (paramBoolean) {
/* 163 */       documentBuilderFactory.setXIncludeAware(true);
/*     */     }
/*     */     
/* 166 */     return documentBuilderFactory.newDocumentBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static a a() {
/* 176 */     return b.a();
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
/*     */   @Deprecated
/*     */   public static a b() throws ParserConfigurationException {
/* 189 */     return a();
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
/*     */   public Document a(StreamSource paramStreamSource) throws SAXException, IOException {
/* 208 */     Document document = null;
/* 209 */     InputStream inputStream = paramStreamSource.getInputStream();
/* 210 */     if (inputStream != null) {
/*     */ 
/*     */       
/* 213 */       if (!inputStream.markSupported()) {
/* 214 */         inputStream = new BufferedInputStream(inputStream);
/*     */       }
/* 216 */       c c = new c(inputStream);
/*     */       
/* 218 */       c.mark(128);
/*     */       try {
/* 220 */         document = c(new StreamSource(c));
/*     */         
/* 222 */         inputStream.close();
/* 223 */       } catch (SAXParseException sAXParseException) {
/* 224 */         c.reset();
/*     */         try {
/* 226 */           document = b(new StreamSource(c));
/*     */         }
/* 228 */         catch (IOException iOException) {
/* 229 */           throw sAXParseException;
/*     */         } 
/* 231 */         inputStream.close();
/*     */       } 
/*     */     } 
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
/* 246 */     if (document == null) {
/* 247 */       document = c(paramStreamSource);
/*     */     }
/* 249 */     return document;
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
/*     */   public Document b(StreamSource paramStreamSource) throws IOException, SAXException {
/* 265 */     InputStream inputStream = paramStreamSource.getInputStream();
/* 266 */     if (inputStream == null) {
/* 267 */       throw new IllegalArgumentException("Bad StreamSource: " + paramStreamSource);
/*     */     }
/*     */     
/* 270 */     ZipInputStream zipInputStream = new ZipInputStream(inputStream);
/* 271 */     Document document = null;
/* 272 */     ZipEntry zipEntry = zipInputStream.getNextEntry();
/* 273 */     while (zipEntry != null) {
/* 274 */       if ("content.xml".equals(zipEntry.getName())) {
/* 275 */         document = c().parse(zipInputStream);
/* 276 */         zipEntry = null; continue;
/*     */       } 
/* 278 */       zipEntry = zipInputStream.getNextEntry();
/*     */     } 
/*     */     
/* 281 */     return document;
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
/*     */   public Document c(StreamSource paramStreamSource) throws SAXException, IOException {
/* 297 */     InputSource inputSource = null;
/* 298 */     String str = paramStreamSource.getSystemId();
/* 299 */     if (str != null) {
/* 300 */       inputSource = new InputSource(str);
/*     */     }
/* 302 */     InputStream inputStream = paramStreamSource.getInputStream();
/* 303 */     if (inputSource == null && inputStream != null) {
/* 304 */       inputSource = new InputSource(inputStream);
/*     */     }
/* 306 */     Reader reader = paramStreamSource.getReader();
/* 307 */     if (inputSource == null && reader != null) {
/* 308 */       inputSource = new InputSource(reader);
/*     */     }
/*     */     
/* 311 */     if (inputSource == null) {
/* 312 */       throw new IllegalArgumentException("Bad StreamSource: " + paramStreamSource);
/*     */     }
/*     */ 
/*     */     
/* 316 */     return c().parse(inputSource);
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
/*     */   public DocumentBuilder c() {
/* 335 */     long l = Thread.currentThread().getId();
/* 336 */     Reference<DocumentBuilder> reference = this.g.get(Long.valueOf(l));
/* 337 */     if (reference != null) {
/* 338 */       DocumentBuilder documentBuilder1 = reference.get();
/* 339 */       if (documentBuilder1 != null) {
/* 340 */         return documentBuilder1;
/*     */       }
/*     */     } 
/* 343 */     DocumentBuilder documentBuilder = e();
/* 344 */     this.g.put(Long.valueOf(l), new SoftReference<DocumentBuilder>(documentBuilder));
/* 345 */     return documentBuilder;
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
/*     */   public Node a(Source paramSource) throws SAXException, IOException {
/*     */     Node node;
/* 362 */     if (paramSource instanceof StreamSource) {
/* 363 */       StreamSource streamSource = (StreamSource)paramSource;
/* 364 */       node = a(streamSource);
/* 365 */     } else if (paramSource instanceof ImageSource) {
/* 366 */       ImageSource imageSource = (ImageSource)paramSource;
/*     */       
/* 368 */       StreamSource streamSource = new StreamSource(imageSource.getInputStream());
/* 369 */       node = a(streamSource);
/* 370 */     } else if (paramSource instanceof DOMSource) {
/* 371 */       DOMSource dOMSource = (DOMSource)paramSource;
/* 372 */       node = dOMSource.getNode();
/*     */     } else {
/*     */       
/*     */       try {
/* 376 */         Transformer transformer = TransformerFactory.newInstance().newTransformer();
/* 377 */         DOMResult dOMResult = new DOMResult();
/* 378 */         transformer.transform(paramSource, dOMResult);
/* 379 */         node = dOMResult.getNode();
/* 380 */       } catch (TransformerException transformerException) {
/* 381 */         f.warn(transformerException.getMessage());
/* 382 */         throw new IllegalArgumentException("Cannot handle Source: " + paramSource, transformerException);
/*     */       } 
/*     */     } 
/*     */     
/* 386 */     return node;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/h/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */