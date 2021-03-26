/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.extensions.ExtensionNamespaceSupport;
/*     */ import org.apache.xalan.extensions.ExtensionNamespacesManager;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.StringVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemExtensionDecl
/*     */   extends ElemTemplateElement
/*     */ {
/*  49 */   private String m_prefix = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String v) {
/*  59 */     this.m_prefix = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/*  70 */     return this.m_prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  75 */   private StringVector m_functions = new StringVector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctions(StringVector v) {
/*  85 */     this.m_functions = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringVector getFunctions() {
/*  96 */     return this.m_functions;
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
/*     */   public String getFunction(int i) throws ArrayIndexOutOfBoundsException {
/* 112 */     if (null == this.m_functions) {
/* 113 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/* 115 */     return this.m_functions.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionCount() {
/* 126 */     return (null != this.m_functions) ? this.m_functions.size() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 131 */   private StringVector m_elements = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setElements(StringVector v) {
/* 141 */     this.m_elements = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringVector getElements() {
/* 152 */     return this.m_elements;
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
/*     */   public String getElement(int i) throws ArrayIndexOutOfBoundsException {
/* 168 */     if (null == this.m_elements) {
/* 169 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/* 171 */     return this.m_elements.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getElementCount() {
/* 182 */     return (null != this.m_elements) ? this.m_elements.size() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 193 */     return 85;
/*     */   }
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 198 */     super.compose(sroot);
/* 199 */     String prefix = getPrefix();
/* 200 */     String declNamespace = getNamespaceForPrefix(prefix);
/* 201 */     String lang = null;
/* 202 */     String srcURL = null;
/* 203 */     String scriptSrc = null;
/* 204 */     if (null == declNamespace) {
/* 205 */       throw new TransformerException(XSLMessages.createMessage("ER_NO_NAMESPACE_DECL", new Object[] { prefix }));
/*     */     }
/* 207 */     for (ElemTemplateElement child = getFirstChildElem(); child != null; 
/* 208 */       child = child.getNextSiblingElem()) {
/*     */       
/* 210 */       if (86 == child.getXSLToken()) {
/*     */         
/* 212 */         ElemExtensionScript sdecl = (ElemExtensionScript)child;
/* 213 */         lang = sdecl.getLang();
/* 214 */         srcURL = sdecl.getSrc();
/* 215 */         ElemTemplateElement childOfSDecl = sdecl.getFirstChildElem();
/* 216 */         if (null != childOfSDecl)
/*     */         {
/* 218 */           if (78 == childOfSDecl.getXSLToken()) {
/*     */ 
/*     */             
/* 221 */             ElemTextLiteral tl = (ElemTextLiteral)childOfSDecl;
/* 222 */             char[] chars = tl.getChars();
/* 223 */             scriptSrc = new String(chars);
/* 224 */             if (scriptSrc.trim().length() == 0)
/* 225 */               scriptSrc = null; 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 230 */     if (null == lang)
/* 231 */       lang = "javaclass"; 
/* 232 */     if (lang.equals("javaclass") && scriptSrc != null) {
/* 233 */       throw new TransformerException(XSLMessages.createMessage("ER_ELEM_CONTENT_NOT_ALLOWED", new Object[] { scriptSrc }));
/*     */     }
/*     */ 
/*     */     
/* 237 */     ExtensionNamespaceSupport extNsSpt = null;
/* 238 */     ExtensionNamespacesManager extNsMgr = sroot.getExtensionNamespacesManager();
/* 239 */     if (extNsMgr.namespaceIndex(declNamespace, extNsMgr.getExtensions()) == -1)
/*     */     {
/*     */       
/* 242 */       if (lang.equals("javaclass")) {
/*     */         
/* 244 */         if (null == srcURL)
/*     */         {
/* 246 */           extNsSpt = extNsMgr.defineJavaNamespace(declNamespace);
/*     */         }
/* 248 */         else if (extNsMgr.namespaceIndex(srcURL, extNsMgr.getExtensions()) == -1)
/*     */         {
/*     */           
/* 251 */           extNsSpt = extNsMgr.defineJavaNamespace(declNamespace, srcURL);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 256 */         String handler = "org.apache.xalan.extensions.ExtensionHandlerGeneral";
/* 257 */         Object[] args = { declNamespace, this.m_elements, this.m_functions, lang, srcURL, scriptSrc, getSystemId() };
/*     */         
/* 259 */         extNsSpt = new ExtensionNamespaceSupport(declNamespace, handler, args);
/*     */       } 
/*     */     }
/* 262 */     if (extNsSpt != null)
/* 263 */       extNsMgr.registerExtension(extNsSpt); 
/*     */   }
/*     */   
/*     */   public void runtimeInit(TransformerImpl transformer) throws TransformerException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemExtensionDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */