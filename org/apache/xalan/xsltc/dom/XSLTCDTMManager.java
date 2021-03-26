/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.apache.xalan.xsltc.trax.DOM2SAX;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMException;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.dtm.ref.DTMManagerDefault;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
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
/*     */ public class XSLTCDTMManager
/*     */   extends DTMManagerDefault
/*     */ {
/*     */   private static final String DEFAULT_CLASS_NAME = "org.apache.xalan.xsltc.dom.XSLTCDTMManager";
/*     */   private static final String DEFAULT_PROP_NAME = "org.apache.xalan.xsltc.dom.XSLTCDTMManager";
/*     */   private static final boolean DUMPTREE = false;
/*     */   private static final boolean DEBUG = false;
/*     */   
/*     */   public static XSLTCDTMManager newInstance() {
/*  76 */     return new XSLTCDTMManager();
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
/*     */   public static Class getDTMManagerClass() {
/*  95 */     Class mgrClass = ObjectFactory.lookUpFactoryClass("org.apache.xalan.xsltc.dom.XSLTCDTMManager", null, "org.apache.xalan.xsltc.dom.XSLTCDTMManager");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     return (mgrClass != null) ? mgrClass : XSLTCDTMManager.class;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing) {
/* 129 */     return getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, true, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing, boolean buildIdIndex) {
/* 159 */     return getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, buildIdIndex, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing, boolean buildIdIndex, boolean newNameTable) {
/* 192 */     return getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, buildIdIndex, newNameTable);
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
/*     */   public DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing, boolean hasUserReader, int size, boolean buildIdIndex) {
/* 228 */     return getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, hasUserReader, size, buildIdIndex, false);
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
/*     */   public DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing, boolean hasUserReader, int size, boolean buildIdIndex, boolean newNameTable) {
/* 273 */     int dtmPos = getFirstFreeDTMID();
/* 274 */     int documentID = dtmPos << 16;
/*     */     
/* 276 */     if (null != source && source instanceof DOMSource) {
/*     */       SAXImpl sAXImpl;
/* 278 */       DOMSource domsrc = (DOMSource)source;
/* 279 */       Node node = domsrc.getNode();
/* 280 */       DOM2SAX dom2sax = new DOM2SAX(node);
/*     */ 
/*     */ 
/*     */       
/* 284 */       if (size <= 0) {
/* 285 */         sAXImpl = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, 512, buildIdIndex, newNameTable);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 290 */         sAXImpl = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, size, buildIdIndex, newNameTable);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 295 */       sAXImpl.setDocumentURI(source.getSystemId());
/*     */       
/* 297 */       addDTM((DTM)sAXImpl, dtmPos, 0);
/*     */       
/* 299 */       dom2sax.setContentHandler(sAXImpl);
/*     */ 
/*     */       
/* 302 */       try { dom2sax.parse(); } catch (RuntimeException re)
/*     */       
/*     */       { 
/* 305 */         throw re; } catch (Exception e)
/*     */       
/*     */       { 
/* 308 */         throw new WrappedRuntimeException(e); }
/*     */ 
/*     */       
/* 311 */       return (DTM)sAXImpl;
/*     */     } 
/*     */ 
/*     */     
/* 315 */     boolean isSAXSource = (null != source) ? (source instanceof SAXSource) : true;
/*     */     
/* 317 */     boolean isStreamSource = (null != source) ? (source instanceof javax.xml.transform.stream.StreamSource) : false;
/*     */ 
/*     */     
/* 320 */     if (isSAXSource || isStreamSource) {
/*     */       XMLReader xMLReader;
/*     */       InputSource inputSource;
/*     */       SAXImpl sAXImpl;
/* 324 */       if (null == source) {
/* 325 */         inputSource = null;
/* 326 */         xMLReader = null;
/* 327 */         hasUserReader = false;
/*     */       } else {
/*     */         
/* 330 */         xMLReader = getXMLReader(source);
/* 331 */         inputSource = SAXSource.sourceToInputSource(source);
/*     */         
/* 333 */         String urlOfSource = inputSource.getSystemId();
/*     */         
/* 335 */         if (null != urlOfSource) {
/*     */           
/* 337 */           try { urlOfSource = SystemIDResolver.getAbsoluteURI(urlOfSource); } catch (Exception e)
/*     */           
/*     */           { 
/*     */             
/* 341 */             System.err.println("Can not absolutize URL: " + urlOfSource); }
/*     */ 
/*     */           
/* 344 */           inputSource.setSystemId(urlOfSource);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 350 */       if (size <= 0) {
/* 351 */         sAXImpl = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, 512, buildIdIndex, newNameTable);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 356 */         sAXImpl = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, size, buildIdIndex, newNameTable);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 363 */       addDTM((DTM)sAXImpl, dtmPos, 0);
/*     */       
/* 365 */       if (null == xMLReader)
/*     */       {
/* 367 */         return (DTM)sAXImpl;
/*     */       }
/*     */       
/* 370 */       xMLReader.setContentHandler(sAXImpl.getBuilder());
/*     */       
/* 372 */       if (!hasUserReader || null == xMLReader.getDTDHandler()) {
/* 373 */         xMLReader.setDTDHandler(sAXImpl);
/*     */       }
/*     */       
/* 376 */       if (!hasUserReader || null == xMLReader.getErrorHandler()) {
/* 377 */         xMLReader.setErrorHandler((ErrorHandler)sAXImpl);
/*     */       }
/*     */ 
/*     */       
/* 381 */       try { xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", sAXImpl); } catch (SAXNotRecognizedException e)
/*     */       {  }
/* 383 */       catch (SAXNotSupportedException sAXNotSupportedException) {}
/*     */ 
/*     */ 
/*     */       
/* 387 */       try { xMLReader.parse(inputSource); } catch (RuntimeException re)
/*     */       
/*     */       { 
/* 390 */         throw re; } catch (Exception e)
/*     */       
/*     */       { 
/* 393 */         throw new WrappedRuntimeException(e); }
/*     */       finally
/* 395 */       { if (!hasUserReader) {
/* 396 */           releaseXMLReader(xMLReader);
/*     */         } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 405 */       return (DTM)sAXImpl;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 410 */     throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[] { source }));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/XSLTCDTMManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */