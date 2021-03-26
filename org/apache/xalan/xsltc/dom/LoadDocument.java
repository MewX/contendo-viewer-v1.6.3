/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMCache;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xalan.xsltc.trax.TemplatesImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.ref.EmptyIterator;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LoadDocument
/*     */ {
/*     */   private static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";
/*     */   
/*     */   public static DTMAxisIterator documentF(Object arg1, DTMAxisIterator arg2, String xslURI, AbstractTranslet translet, DOM dom) throws TransletException {
/*  60 */     String baseURI = null;
/*  61 */     int arg2FirstNode = arg2.next();
/*  62 */     if (arg2FirstNode == -1)
/*     */     {
/*  64 */       return EmptyIterator.getInstance();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  69 */     baseURI = dom.getDocumentURI(arg2FirstNode);
/*  70 */     if (!SystemIDResolver.isAbsoluteURI(baseURI)) {
/*  71 */       baseURI = SystemIDResolver.getAbsoluteURIFromRelative(baseURI);
/*     */     }
/*     */ 
/*     */     
/*  75 */     try { if (arg1 instanceof String) {
/*  76 */         if (((String)arg1).length() == 0) {
/*  77 */           return document(xslURI, "", translet, dom);
/*     */         }
/*  79 */         return document((String)arg1, baseURI, translet, dom);
/*     */       } 
/*  81 */       if (arg1 instanceof DTMAxisIterator) {
/*  82 */         return document((DTMAxisIterator)arg1, baseURI, translet, dom);
/*     */       }
/*  84 */       String err = "document(" + arg1.toString() + ")";
/*  85 */       throw new IllegalArgumentException(err); } catch (Exception e)
/*     */     
/*     */     { 
/*  88 */       throw new TransletException(e); }
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
/*     */   public static DTMAxisIterator documentF(Object arg, String xslURI, AbstractTranslet translet, DOM dom) throws TransletException {
/*     */     
/* 102 */     try { if (arg instanceof String) {
/* 103 */         String baseURI = xslURI;
/* 104 */         if (!SystemIDResolver.isAbsoluteURI(xslURI)) {
/* 105 */           baseURI = SystemIDResolver.getAbsoluteURIFromRelative(xslURI);
/*     */         }
/* 107 */         String href = (String)arg;
/* 108 */         if (href.length() == 0) {
/* 109 */           href = "";
/*     */ 
/*     */ 
/*     */           
/* 113 */           TemplatesImpl templates = (TemplatesImpl)translet.getTemplates();
/* 114 */           DOM sdom = null;
/* 115 */           if (templates != null) {
/* 116 */             sdom = templates.getStylesheetDOM();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 122 */           if (sdom != null) {
/* 123 */             return document(sdom, translet, dom);
/*     */           }
/*     */           
/* 126 */           return document(href, baseURI, translet, dom, true);
/*     */         } 
/*     */ 
/*     */         
/* 130 */         return document(href, baseURI, translet, dom);
/*     */       } 
/* 132 */       if (arg instanceof DTMAxisIterator) {
/* 133 */         return document((DTMAxisIterator)arg, (String)null, translet, dom);
/*     */       }
/* 135 */       String err = "document(" + arg.toString() + ")";
/* 136 */       throw new IllegalArgumentException(err); } catch (Exception e)
/*     */     
/*     */     { 
/* 139 */       throw new TransletException(e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DTMAxisIterator document(String uri, String base, AbstractTranslet translet, DOM dom) throws Exception {
/* 147 */     return document(uri, base, translet, dom, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DTMAxisIterator document(String uri, String base, AbstractTranslet translet, DOM dom, boolean cacheDOM) throws Exception {
/*     */     
/*     */     try { DOMEnhancedForDTM dOMEnhancedForDTM;
/* 156 */       String originalUri = uri;
/* 157 */       MultiDOM multiplexer = (MultiDOM)dom;
/*     */ 
/*     */       
/* 160 */       if (base != null && !base.equals("")) {
/* 161 */         uri = SystemIDResolver.getAbsoluteURI(uri, base);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 166 */       if (uri == null || uri.equals("")) {
/* 167 */         return EmptyIterator.getInstance();
/*     */       }
/*     */ 
/*     */       
/* 171 */       int mask = multiplexer.getDocumentMask(uri);
/* 172 */       if (mask != -1) {
/* 173 */         DOM newDom = ((DOMAdapter)multiplexer.getDOMAdapter(uri)).getDOMImpl();
/*     */         
/* 175 */         if (newDom instanceof DOMEnhancedForDTM) {
/* 176 */           return (DTMAxisIterator)new SingletonIterator(((DOMEnhancedForDTM)newDom).getDocument(), true);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       DOMCache cache = translet.getDOMCache();
/*     */ 
/*     */       
/* 186 */       mask = multiplexer.nextMask();
/*     */       
/* 188 */       if (cache != null) {
/* 189 */         DOM newdom = cache.retrieveDocument(base, originalUri, (Translet)translet);
/* 190 */         if (newdom == null) {
/* 191 */           Exception e = new FileNotFoundException(originalUri);
/* 192 */           throw new TransletException(e);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 198 */         XSLTCDTMManager dtmManager = (XSLTCDTMManager)multiplexer.getDTMManager();
/*     */         
/* 200 */         DOMEnhancedForDTM enhancedDOM = (DOMEnhancedForDTM)dtmManager.getDTM(new StreamSource(uri), false, null, true, false, translet.hasIdCall(), cacheDOM);
/*     */ 
/*     */ 
/*     */         
/* 204 */         dOMEnhancedForDTM = enhancedDOM;
/*     */ 
/*     */         
/* 207 */         if (cacheDOM) {
/* 208 */           TemplatesImpl templates = (TemplatesImpl)translet.getTemplates();
/* 209 */           if (templates != null) {
/* 210 */             templates.setStylesheetDOM((DOM)enhancedDOM);
/*     */           }
/*     */         } 
/*     */         
/* 214 */         translet.prepassDocument((DOM)enhancedDOM);
/* 215 */         enhancedDOM.setDocumentURI(uri);
/*     */       } 
/*     */ 
/*     */       
/* 219 */       DOMAdapter domAdapter = translet.makeDOMAdapter((DOM)dOMEnhancedForDTM);
/* 220 */       multiplexer.addDOMAdapter(domAdapter);
/*     */ 
/*     */       
/* 223 */       translet.buildKeys(domAdapter, null, null, dOMEnhancedForDTM.getDocument());
/*     */ 
/*     */       
/* 226 */       return (DTMAxisIterator)new SingletonIterator(dOMEnhancedForDTM.getDocument(), true); } catch (Exception e)
/*     */     
/* 228 */     { throw e; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DTMAxisIterator document(DTMAxisIterator arg1, String baseURI, AbstractTranslet translet, DOM dom) throws Exception {
/* 238 */     UnionIterator union = new UnionIterator(dom);
/* 239 */     int node = -1;
/*     */     
/* 241 */     while ((node = arg1.next()) != -1) {
/* 242 */       String uri = dom.getStringValueX(node);
/*     */       
/* 244 */       if (baseURI == null) {
/* 245 */         baseURI = dom.getDocumentURI(node);
/* 246 */         if (!SystemIDResolver.isAbsoluteURI(baseURI))
/* 247 */           baseURI = SystemIDResolver.getAbsoluteURIFromRelative(baseURI); 
/*     */       } 
/* 249 */       union.addIterator(document(uri, baseURI, translet, dom));
/*     */     } 
/* 251 */     return (DTMAxisIterator)union;
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
/*     */   private static DTMAxisIterator document(DOM newdom, AbstractTranslet translet, DOM dom) throws Exception {
/* 268 */     DTMManager dtmManager = ((MultiDOM)dom).getDTMManager();
/*     */     
/* 270 */     if (dtmManager != null && newdom instanceof DTM) {
/* 271 */       ((DTM)newdom).migrateTo(dtmManager);
/*     */     }
/*     */     
/* 274 */     translet.prepassDocument(newdom);
/*     */ 
/*     */     
/* 277 */     DOMAdapter domAdapter = translet.makeDOMAdapter(newdom);
/* 278 */     ((MultiDOM)dom).addDOMAdapter(domAdapter);
/*     */ 
/*     */     
/* 281 */     translet.buildKeys(domAdapter, null, null, newdom.getDocument());
/*     */ 
/*     */ 
/*     */     
/* 285 */     return (DTMAxisIterator)new SingletonIterator(newdom.getDocument(), true);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/LoadDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */