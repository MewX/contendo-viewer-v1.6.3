/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.StylesheetRoot;
/*     */ import org.apache.xpath.XPathProcessorException;
/*     */ import org.apache.xpath.functions.FuncExtFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensionsTable
/*     */ {
/*  42 */   public Hashtable m_extensionFunctionNamespaces = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private StylesheetRoot m_sroot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionsTable(StylesheetRoot sroot) throws TransformerException {
/*  58 */     this.m_sroot = sroot;
/*  59 */     Vector extensions = this.m_sroot.getExtensions();
/*  60 */     for (int i = 0; i < extensions.size(); i++) {
/*     */       
/*  62 */       ExtensionNamespaceSupport extNamespaceSpt = extensions.elementAt(i);
/*     */       
/*  64 */       ExtensionHandler extHandler = extNamespaceSpt.launch();
/*  65 */       if (extHandler != null) {
/*  66 */         addExtensionNamespace(extNamespaceSpt.getNamespace(), extHandler);
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
/*     */   public ExtensionHandler get(String extns) {
/*  80 */     return (ExtensionHandler)this.m_extensionFunctionNamespaces.get(extns);
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
/*     */   public void addExtensionNamespace(String uri, ExtensionHandler extNS) {
/*  94 */     this.m_extensionFunctionNamespaces.put(uri, extNS);
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
/*     */   public boolean functionAvailable(String ns, String funcName) throws TransformerException {
/* 109 */     boolean isAvailable = false;
/*     */     
/* 111 */     if (null != ns) {
/*     */       
/* 113 */       ExtensionHandler extNS = (ExtensionHandler)this.m_extensionFunctionNamespaces.get(ns);
/*     */       
/* 115 */       if (extNS != null)
/* 116 */         isAvailable = extNS.isFunctionAvailable(funcName); 
/*     */     } 
/* 118 */     return isAvailable;
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
/*     */   public boolean elementAvailable(String ns, String elemName) throws TransformerException {
/* 133 */     boolean isAvailable = false;
/* 134 */     if (null != ns) {
/*     */       
/* 136 */       ExtensionHandler extNS = (ExtensionHandler)this.m_extensionFunctionNamespaces.get(ns);
/*     */       
/* 138 */       if (extNS != null)
/* 139 */         isAvailable = extNS.isElementAvailable(elemName); 
/*     */     } 
/* 141 */     return isAvailable;
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
/*     */   public Object extFunction(String ns, String funcName, Vector argVec, Object methodKey, ExpressionContext exprContext) throws TransformerException {
/* 164 */     Object result = null;
/* 165 */     if (null != ns) {
/*     */       
/* 167 */       ExtensionHandler extNS = (ExtensionHandler)this.m_extensionFunctionNamespaces.get(ns);
/*     */       
/* 169 */       if (null != extNS) {
/*     */ 
/*     */ 
/*     */         
/* 173 */         try { result = extNS.callFunction(funcName, argVec, methodKey, exprContext); } catch (TransformerException e)
/*     */         
/*     */         { 
/*     */ 
/*     */           
/* 178 */           throw e; } catch (Exception e)
/*     */         
/*     */         { 
/*     */           
/* 182 */           throw new TransformerException(e); }
/*     */ 
/*     */       
/*     */       } else {
/*     */         
/* 187 */         throw new XPathProcessorException(XSLMessages.createMessage("ER_EXTENSION_FUNC_UNKNOWN", new Object[] { ns, funcName }));
/*     */       } 
/*     */     } 
/*     */     
/* 191 */     return result;
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
/*     */   public Object extFunction(FuncExtFunction extFunction, Vector argVec, ExpressionContext exprContext) throws TransformerException {
/* 210 */     Object result = null;
/* 211 */     String ns = extFunction.getNamespace();
/* 212 */     if (null != ns) {
/*     */       
/* 214 */       ExtensionHandler extNS = (ExtensionHandler)this.m_extensionFunctionNamespaces.get(ns);
/*     */       
/* 216 */       if (null != extNS) {
/*     */ 
/*     */ 
/*     */         
/* 220 */         try { result = extNS.callFunction(extFunction, argVec, exprContext); } catch (TransformerException e)
/*     */         
/*     */         { 
/*     */           
/* 224 */           throw e; } catch (Exception e)
/*     */         
/*     */         { 
/*     */           
/* 228 */           throw new TransformerException(e); }
/*     */ 
/*     */       
/*     */       } else {
/*     */         
/* 233 */         throw new XPathProcessorException(XSLMessages.createMessage("ER_EXTENSION_FUNC_UNKNOWN", new Object[] { ns, extFunction.getFunctionName() }));
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionsTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */