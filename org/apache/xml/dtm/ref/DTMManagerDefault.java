/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMException;
/*     */ import org.apache.xml.dtm.DTMFilter;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.dtm.ref.dom2dtm.DOM2DTM;
/*     */ import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
/*     */ import org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.SuballocatedIntVector;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLReaderManager;
/*     */ import org.apache.xml.utils.XMLStringFactory;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMManagerDefault
/*     */   extends DTMManager
/*     */ {
/*     */   private static final boolean DUMPTREE = false;
/*     */   private static final boolean DEBUG = false;
/*  92 */   protected DTM[] m_dtms = new DTM[256];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   int[] m_dtm_offsets = new int[256];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected XMLReaderManager m_readerManager = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addDTM(DTM dtm, int id) {
/* 123 */     addDTM(dtm, id, 0);
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
/*     */   public synchronized void addDTM(DTM dtm, int id, int offset) {
/* 138 */     if (id >= 65536)
/*     */     {
/*     */       
/* 141 */       throw new DTMException(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     int oldlen = this.m_dtms.length;
/* 150 */     if (oldlen <= id) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       int newlen = Math.min(id + 256, 65536);
/*     */       
/* 159 */       DTM[] new_m_dtms = new DTM[newlen];
/* 160 */       System.arraycopy(this.m_dtms, 0, new_m_dtms, 0, oldlen);
/* 161 */       this.m_dtms = new_m_dtms;
/* 162 */       int[] new_m_dtm_offsets = new int[newlen];
/* 163 */       System.arraycopy(this.m_dtm_offsets, 0, new_m_dtm_offsets, 0, oldlen);
/* 164 */       this.m_dtm_offsets = new_m_dtm_offsets;
/*     */     } 
/*     */     
/* 167 */     this.m_dtms[id] = dtm;
/* 168 */     this.m_dtm_offsets[id] = offset;
/* 169 */     dtm.documentRegistration();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getFirstFreeDTMID() {
/* 179 */     int n = this.m_dtms.length;
/* 180 */     for (int i = 1; i < n; i++) {
/*     */       
/* 182 */       if (null == this.m_dtms[i])
/*     */       {
/* 184 */         return i;
/*     */       }
/*     */     } 
/* 187 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   private ExpandedNameTable m_expandedNameTable = new ExpandedNameTable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized DTM getDTM(Source source, boolean unique, DTMWSFilter whiteSpaceFilter, boolean incremental, boolean doIndexing) {
/* 240 */     XMLStringFactory xstringFactory = this.m_xsf;
/* 241 */     int dtmPos = getFirstFreeDTMID();
/* 242 */     int documentID = dtmPos << 16;
/*     */     
/* 244 */     if (null != source && source instanceof DOMSource) {
/*     */       
/* 246 */       DOM2DTM dtm = new DOM2DTM(this, (DOMSource)source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
/*     */ 
/*     */       
/* 249 */       addDTM((DTM)dtm, dtmPos, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       return (DTM)dtm;
/*     */     } 
/*     */ 
/*     */     
/* 260 */     boolean isSAXSource = (null != source) ? (source instanceof SAXSource) : true;
/*     */     
/* 262 */     boolean isStreamSource = (null != source) ? (source instanceof javax.xml.transform.stream.StreamSource) : false;
/*     */ 
/*     */     
/* 265 */     if (isSAXSource || isStreamSource) {
/* 266 */       XMLReader reader = null;
/*     */       
/*     */       try {
/*     */         InputSource inputSource;
/*     */         SAX2DTM sAX2DTM;
/* 271 */         if (null == source) {
/* 272 */           inputSource = null;
/*     */         } else {
/* 274 */           reader = getXMLReader(source);
/* 275 */           inputSource = SAXSource.sourceToInputSource(source);
/*     */           
/* 277 */           String urlOfSource = inputSource.getSystemId();
/*     */           
/* 279 */           if (null != urlOfSource) {
/*     */             
/* 281 */             try { urlOfSource = SystemIDResolver.getAbsoluteURI(urlOfSource); } catch (Exception e)
/*     */             
/*     */             { 
/* 284 */               System.err.println("Can not absolutize URL: " + urlOfSource); }
/*     */ 
/*     */             
/* 287 */             inputSource.setSystemId(urlOfSource);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 292 */         if (source == null && unique && !incremental && !doIndexing) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 300 */           SAX2RTFDTM sAX2RTFDTM = new SAX2RTFDTM(this, source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 312 */           sAX2DTM = new SAX2DTM(this, source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 319 */         addDTM((DTM)sAX2DTM, dtmPos, 0);
/*     */ 
/*     */         
/* 322 */         boolean haveXercesParser = (null != reader && reader.getClass().getName().equals("org.apache.xerces.parsers.SAXParser"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 328 */         if (haveXercesParser) {
/* 329 */           incremental = true;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 334 */         if (DTMManager.m_incremental && incremental) {
/*     */           
/* 336 */           IncrementalSAXSource coParser = null;
/*     */           
/* 338 */           if (haveXercesParser) {
/*     */ 
/*     */             
/* 341 */             try { coParser = (IncrementalSAXSource)Class.forName("org.apache.xml.dtm.ref.IncrementalSAXSource_Xerces").newInstance(); } catch (Exception ex)
/*     */             
/*     */             { 
/* 344 */               ex.printStackTrace();
/* 345 */               coParser = null; }
/*     */           
/*     */           }
/*     */           
/* 349 */           if (coParser == null)
/*     */           {
/* 351 */             if (null == reader) {
/* 352 */               coParser = new IncrementalSAXSource_Filter();
/*     */             } else {
/* 354 */               IncrementalSAXSource_Filter filter = new IncrementalSAXSource_Filter();
/*     */               
/* 356 */               filter.setXMLReader(reader);
/* 357 */               coParser = filter;
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 381 */           sAX2DTM.setIncrementalSAXSource(coParser);
/*     */           
/* 383 */           if (null == inputSource)
/*     */           {
/*     */             
/* 386 */             return (DTM)sAX2DTM;
/*     */           }
/*     */           
/* 389 */           if (null == reader.getErrorHandler()) {
/* 390 */             reader.setErrorHandler((ErrorHandler)sAX2DTM);
/*     */           }
/* 392 */           reader.setDTDHandler((DTDHandler)sAX2DTM);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 398 */           try { coParser.startParse(inputSource); } catch (RuntimeException re)
/*     */           
/*     */           { 
/* 401 */             sAX2DTM.clearCoRoutine();
/*     */             
/* 403 */             throw re; } catch (Exception e)
/*     */           
/*     */           { 
/* 406 */             sAX2DTM.clearCoRoutine();
/*     */             
/* 408 */             throw new WrappedRuntimeException(e); }
/*     */         
/*     */         } else {
/* 411 */           if (null == reader)
/*     */           {
/*     */             
/* 414 */             return (DTM)sAX2DTM;
/*     */           }
/*     */ 
/*     */           
/* 418 */           reader.setContentHandler((ContentHandler)sAX2DTM);
/* 419 */           reader.setDTDHandler((DTDHandler)sAX2DTM);
/* 420 */           if (null == reader.getErrorHandler()) {
/* 421 */             reader.setErrorHandler((ErrorHandler)sAX2DTM);
/*     */           }
/*     */ 
/*     */           
/* 425 */           try { reader.setProperty("http://xml.org/sax/properties/lexical-handler", sAX2DTM); } catch (SAXNotRecognizedException e)
/*     */           
/*     */           {  }
/* 428 */           catch (SAXNotSupportedException sAXNotSupportedException) {}
/*     */ 
/*     */ 
/*     */           
/* 432 */           try { reader.parse(inputSource); } catch (RuntimeException re)
/*     */           
/* 434 */           { sAX2DTM.clearCoRoutine();
/*     */             
/* 436 */             throw re; } catch (Exception e)
/*     */           
/* 438 */           { sAX2DTM.clearCoRoutine();
/*     */             
/* 440 */             throw new WrappedRuntimeException(e); }
/*     */         
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 449 */         return (DTM)sAX2DTM;
/*     */       } finally {
/* 451 */         releaseXMLReader(reader);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 457 */     throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[] { source }));
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
/*     */   public synchronized int getDTMHandleFromNode(Node node) {
/*     */     int j;
/* 473 */     if (null == node) {
/* 474 */       throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_NODE_NON_NULL", null));
/*     */     }
/* 476 */     if (node instanceof DTMNodeProxy) {
/* 477 */       return ((DTMNodeProxy)node).getDTMNodeNumber();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 502 */     int max = this.m_dtms.length;
/* 503 */     for (int i = 0; i < max; i++) {
/*     */       
/* 505 */       DTM thisDTM = this.m_dtms[i];
/* 506 */       if (null != thisDTM && thisDTM instanceof DOM2DTM) {
/*     */         
/* 508 */         int handle = ((DOM2DTM)thisDTM).getHandleOfNode(node);
/* 509 */         if (handle != -1) return handle;
/*     */       
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 532 */     Node root = node;
/* 533 */     Node p = (root.getNodeType() == 2) ? ((Attr)root).getOwnerElement() : root.getParentNode();
/* 534 */     for (; p != null; p = p.getParentNode())
/*     */     {
/* 536 */       root = p;
/*     */     }
/*     */     
/* 539 */     DOM2DTM dtm = (DOM2DTM)getDTM(new DOMSource(root), false, null, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 544 */     if (node instanceof org.apache.xml.dtm.ref.dom2dtm.DOM2DTMdefaultNamespaceDeclarationNode) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 549 */       j = dtm.getHandleOfNode(((Attr)node).getOwnerElement());
/* 550 */       j = dtm.getAttributeNode(j, node.getNamespaceURI(), node.getLocalName());
/*     */     } else {
/*     */       
/* 553 */       j = dtm.getHandleOfNode(node);
/*     */     } 
/* 555 */     if (-1 == j) {
/* 556 */       throw new RuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_RESOLVE_NODE", null));
/*     */     }
/* 558 */     return j;
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
/*     */   public synchronized XMLReader getXMLReader(Source inputSource) {
/*     */     
/* 581 */     try { XMLReader reader = (inputSource instanceof SAXSource) ? ((SAXSource)inputSource).getXMLReader() : null;
/*     */ 
/*     */ 
/*     */       
/* 585 */       if (null == reader) {
/* 586 */         if (this.m_readerManager == null) {
/* 587 */           this.m_readerManager = XMLReaderManager.getInstance();
/*     */         }
/*     */         
/* 590 */         reader = this.m_readerManager.getXMLReader();
/*     */       } 
/*     */       
/* 593 */       return reader; } catch (SAXException se)
/*     */     
/*     */     { 
/* 596 */       throw new DTMException(se.getMessage(), se); }
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
/*     */   public synchronized void releaseXMLReader(XMLReader reader) {
/* 611 */     if (this.m_readerManager != null) {
/* 612 */       this.m_readerManager.releaseXMLReader(reader);
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
/*     */   public synchronized DTM getDTM(int nodeHandle) {
/*     */     
/* 628 */     try { return this.m_dtms[nodeHandle >>> 16]; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 632 */       if (nodeHandle == -1) {
/* 633 */         return null;
/*     */       }
/* 635 */       throw e; }
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
/*     */   public synchronized int getDTMIdentity(DTM dtm) {
/* 653 */     if (dtm instanceof DTMDefaultBase) {
/*     */       
/* 655 */       DTMDefaultBase dtmdb = (DTMDefaultBase)dtm;
/* 656 */       if (dtmdb.getManager() == this) {
/* 657 */         return dtmdb.getDTMIDs().elementAt(0);
/*     */       }
/* 659 */       return -1;
/*     */     } 
/*     */     
/* 662 */     int n = this.m_dtms.length;
/*     */     
/* 664 */     for (int i = 0; i < n; i++) {
/*     */       
/* 666 */       DTM tdtm = this.m_dtms[i];
/*     */       
/* 668 */       if (tdtm == dtm && this.m_dtm_offsets[i] == 0) {
/* 669 */         return i << 16;
/*     */       }
/*     */     } 
/* 672 */     return -1;
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
/*     */   public synchronized boolean release(DTM dtm, boolean shouldHardDelete) {
/* 703 */     if (dtm instanceof SAX2DTM)
/*     */     {
/* 705 */       ((SAX2DTM)dtm).clearCoRoutine();
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
/* 716 */     if (dtm instanceof DTMDefaultBase) {
/*     */       
/* 718 */       SuballocatedIntVector ids = ((DTMDefaultBase)dtm).getDTMIDs();
/* 719 */       for (int i = ids.size() - 1; i >= 0; i--) {
/* 720 */         this.m_dtms[ids.elementAt(i) >>> 16] = null;
/*     */       }
/*     */     } else {
/*     */       
/* 724 */       int i = getDTMIdentity(dtm);
/* 725 */       if (i >= 0)
/*     */       {
/* 727 */         this.m_dtms[i >>> 16] = null;
/*     */       }
/*     */     } 
/*     */     
/* 731 */     dtm.documentRelease();
/* 732 */     return true;
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
/*     */   public synchronized DTM createDocumentFragment() {
/*     */     
/* 746 */     try { DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/*     */       
/* 748 */       dbf.setNamespaceAware(true);
/*     */       
/* 750 */       DocumentBuilder db = dbf.newDocumentBuilder();
/* 751 */       Document doc = db.newDocument();
/* 752 */       Node df = doc.createDocumentFragment();
/*     */       
/* 754 */       return getDTM(new DOMSource(df), true, null, false, false); } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 758 */       throw new DTMException(e); }
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
/*     */   public synchronized DTMIterator createDTMIterator(int whatToShow, DTMFilter filter, boolean entityReferenceExpansion) {
/* 777 */     return null;
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
/*     */   public synchronized DTMIterator createDTMIterator(String xpathString, PrefixResolver presolver) {
/* 794 */     return null;
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
/*     */   public synchronized DTMIterator createDTMIterator(int node) {
/* 809 */     return null;
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
/*     */   public synchronized DTMIterator createDTMIterator(Object xpathCompiler, int pos) {
/* 825 */     return null;
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
/*     */   public ExpandedNameTable getExpandedNameTable(DTM dtm) {
/* 837 */     return this.m_expandedNameTable;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMManagerDefault.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */