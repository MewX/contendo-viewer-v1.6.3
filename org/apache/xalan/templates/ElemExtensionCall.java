/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.extensions.ExtensionHandler;
/*     */ import org.apache.xalan.extensions.ExtensionsTable;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.w3c.dom.Node;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemExtensionCall
/*     */   extends ElemLiteralResult
/*     */ {
/*     */   String m_extns;
/*     */   String m_lang;
/*     */   String m_srcURL;
/*     */   String m_scriptSrc;
/*  57 */   ElemExtensionDecl m_decl = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/*  67 */     return 79;
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  90 */     super.compose(sroot);
/*  91 */     this.m_extns = getNamespace();
/*  92 */     this.m_decl = getElemExtensionDecl(sroot, this.m_extns);
/*     */ 
/*     */     
/*  95 */     if (this.m_decl == null) {
/*  96 */       sroot.getExtensionNamespacesManager().registerExtension(this.m_extns);
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
/*     */   private ElemExtensionDecl getElemExtensionDecl(StylesheetRoot stylesheet, String namespace) {
/* 112 */     ElemExtensionDecl decl = null;
/* 113 */     int n = stylesheet.getGlobalImportCount();
/*     */     
/* 115 */     for (int i = 0; i < n; i++) {
/*     */       
/* 117 */       Stylesheet imported = stylesheet.getGlobalImport(i);
/*     */       
/* 119 */       ElemTemplateElement child = imported.getFirstChildElem();
/* 120 */       for (; child != null; child = child.getNextSiblingElem()) {
/*     */         
/* 122 */         if (85 == child.getXSLToken()) {
/*     */           
/* 124 */           decl = (ElemExtensionDecl)child;
/*     */           
/* 126 */           String prefix = decl.getPrefix();
/* 127 */           String declNamespace = child.getNamespaceForPrefix(prefix);
/*     */           
/* 129 */           if (namespace.equals(declNamespace))
/*     */           {
/* 131 */             return decl;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return null;
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
/*     */   private void executeFallbacks(TransformerImpl transformer) throws TransformerException {
/* 153 */     for (ElemTemplateElement child = this.m_firstChild; child != null; 
/* 154 */       child = child.m_nextSibling) {
/*     */       
/* 156 */       if (child.getXSLToken() == 57) {
/*     */         
/*     */         try {
/*     */           
/* 160 */           transformer.pushElemTemplateElement(child);
/* 161 */           ((ElemFallback)child).executeFallback(transformer);
/*     */         }
/*     */         finally {
/*     */           
/* 165 */           transformer.popElemTemplateElement();
/*     */         } 
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
/*     */   private boolean hasFallbackChildren() {
/* 179 */     for (ElemTemplateElement child = this.m_firstChild; child != null; 
/* 180 */       child = child.m_nextSibling) {
/*     */       
/* 182 */       if (child.getXSLToken() == 57) {
/* 183 */         return true;
/*     */       }
/*     */     } 
/* 186 */     return false;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 203 */     if (TransformerImpl.S_DEBUG) {
/* 204 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */     
/* 207 */     try { transformer.getResultTreeHandler().flushPending();
/*     */       
/* 209 */       ExtensionsTable etable = transformer.getExtensionsTable();
/* 210 */       ExtensionHandler nsh = etable.get(this.m_extns);
/*     */       
/* 212 */       if (null == nsh) {
/*     */         
/* 214 */         if (hasFallbackChildren()) {
/*     */           
/* 216 */           executeFallbacks(transformer);
/*     */         }
/*     */         else {
/*     */           
/* 220 */           TransformerException te = new TransformerException(XSLMessages.createMessage("ER_CALL_TO_EXT_FAILED", new Object[] { getNodeName() }));
/*     */           
/* 222 */           transformer.getErrorListener().fatalError(te);
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 230 */       try { nsh.processElement(getLocalName(), this, transformer, getStylesheet(), this); } catch (Exception e)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */         
/* 236 */         if (hasFallbackChildren())
/* 237 */         { executeFallbacks(transformer);
/*     */            }
/*     */         
/* 240 */         else if (e instanceof TransformerException)
/*     */         
/* 242 */         { TransformerException te = (TransformerException)e;
/* 243 */           if (null == te.getLocator()) {
/* 244 */             te.setLocator((SourceLocator)this);
/*     */           }
/* 246 */           transformer.getErrorListener().fatalError(te); }
/*     */         
/* 248 */         else if (e instanceof RuntimeException)
/*     */         
/* 250 */         { transformer.getErrorListener().fatalError(new TransformerException(e)); }
/*     */         
/*     */         else
/*     */         
/* 254 */         { transformer.getErrorListener().warning(new TransformerException(e)); }  }  } catch (TransformerException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 261 */       transformer.getErrorListener().fatalError(e); } catch (SAXException se)
/*     */     
/*     */     { 
/* 264 */       throw new TransformerException(se); }
/*     */     
/* 266 */     if (TransformerImpl.S_DEBUG) {
/* 267 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public String getAttribute(String rawName) {
/* 280 */     AVT avt = getLiteralResultAttribute(rawName);
/*     */     
/* 282 */     if (null != avt && avt.getRawName().equals(rawName))
/*     */     {
/* 284 */       return avt.getSimpleString();
/*     */     }
/*     */     
/* 287 */     return null;
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
/*     */   public String getAttribute(String rawName, Node sourceNode, TransformerImpl transformer) throws TransformerException {
/* 308 */     AVT avt = getLiteralResultAttribute(rawName);
/*     */     
/* 310 */     if (null != avt && avt.getRawName().equals(rawName)) {
/*     */       
/* 312 */       XPathContext xctxt = transformer.getXPathContext();
/*     */       
/* 314 */       return avt.evaluate(xctxt, xctxt.getDTMHandleFromNode(sourceNode), this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 319 */     return null;
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
/*     */   protected boolean accept(XSLTVisitor visitor) {
/* 331 */     return visitor.visitExtensionElement(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemExtensionCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */