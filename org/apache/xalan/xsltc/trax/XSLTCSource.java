/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.dom.DOMWSFilter;
/*     */ import org.apache.xalan.xsltc.dom.SAXImpl;
/*     */ import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
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
/*     */ public final class XSLTCSource
/*     */   implements Source
/*     */ {
/*  41 */   private String _systemId = null;
/*  42 */   private Source _source = null;
/*  43 */   private ThreadLocal _dom = new ThreadLocal();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XSLTCSource(String systemId) {
/*  50 */     this._systemId = systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XSLTCSource(Source source) {
/*  58 */     this._source = source;
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
/*     */   public void setSystemId(String systemId) {
/*  70 */     this._systemId = systemId;
/*  71 */     if (this._source != null) {
/*  72 */       this._source.setSystemId(systemId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  83 */     if (this._source != null) {
/*  84 */       return this._source.getSystemId();
/*     */     }
/*     */     
/*  87 */     return this._systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOM getDOM(XSLTCDTMManager dtmManager, AbstractTranslet translet) throws SAXException {
/*  97 */     SAXImpl idom = this._dom.get();
/*     */     
/*  99 */     if (idom != null) {
/* 100 */       if (dtmManager != null) {
/* 101 */         idom.migrateTo((DTMManager)dtmManager);
/*     */       }
/*     */     } else {
/*     */       
/* 105 */       Source source = this._source;
/* 106 */       if (source == null) {
/* 107 */         if (this._systemId != null && this._systemId.length() > 0) {
/* 108 */           source = new StreamSource(this._systemId);
/*     */         } else {
/*     */           
/* 111 */           ErrorMsg err = new ErrorMsg("XSLTC_SOURCE_ERR");
/* 112 */           throw new SAXException(err.toString());
/*     */         } 
/*     */       }
/*     */       
/* 116 */       DOMWSFilter wsfilter = null;
/* 117 */       if (translet != null && translet instanceof org.apache.xalan.xsltc.StripFilter) {
/* 118 */         wsfilter = new DOMWSFilter(translet);
/*     */       }
/*     */       
/* 121 */       boolean hasIdCall = (translet != null) ? translet.hasIdCall() : false;
/*     */       
/* 123 */       if (dtmManager == null) {
/* 124 */         dtmManager = XSLTCDTMManager.newInstance();
/*     */       }
/*     */       
/* 127 */       idom = (SAXImpl)dtmManager.getDTM(source, true, (DTMWSFilter)wsfilter, false, false, hasIdCall);
/*     */       
/* 129 */       String systemId = getSystemId();
/* 130 */       if (systemId != null) {
/* 131 */         idom.setDocumentURI(systemId);
/*     */       }
/* 133 */       this._dom.set(idom);
/*     */     } 
/* 135 */     return (DOM)idom;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/XSLTCSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */