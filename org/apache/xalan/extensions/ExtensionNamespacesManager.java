/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensionNamespacesManager
/*     */ {
/*  36 */   private Vector m_extensions = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private Vector m_predefExtensions = new Vector(7);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private Vector m_unregisteredExtensions = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionNamespacesManager() {
/*  55 */     setPredefinedNamespaces();
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
/*     */   public void registerExtension(String namespace) {
/*  70 */     if (namespaceIndex(namespace, this.m_extensions) == -1) {
/*     */       
/*  72 */       int predef = namespaceIndex(namespace, this.m_predefExtensions);
/*  73 */       if (predef != -1) {
/*  74 */         this.m_extensions.addElement(this.m_predefExtensions.elementAt(predef));
/*  75 */       } else if (!this.m_unregisteredExtensions.contains(namespace)) {
/*  76 */         this.m_unregisteredExtensions.addElement(namespace);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerExtension(ExtensionNamespaceSupport extNsSpt) {
/*  87 */     String namespace = extNsSpt.getNamespace();
/*  88 */     if (namespaceIndex(namespace, this.m_extensions) == -1) {
/*     */       
/*  90 */       this.m_extensions.addElement(extNsSpt);
/*  91 */       if (this.m_unregisteredExtensions.contains(namespace)) {
/*  92 */         this.m_unregisteredExtensions.removeElement(namespace);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int namespaceIndex(String namespace, Vector extensions) {
/* 103 */     for (int i = 0; i < extensions.size(); i++) {
/*     */       
/* 105 */       if (((ExtensionNamespaceSupport)extensions.elementAt(i)).getNamespace().equals(namespace))
/* 106 */         return i; 
/*     */     } 
/* 108 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getExtensions() {
/* 119 */     return this.m_extensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerUnregisteredNamespaces() {
/* 127 */     for (int i = 0; i < this.m_unregisteredExtensions.size(); i++) {
/*     */       
/* 129 */       String ns = this.m_unregisteredExtensions.elementAt(i);
/* 130 */       ExtensionNamespaceSupport extNsSpt = defineJavaNamespace(ns);
/* 131 */       if (extNsSpt != null) {
/* 132 */         this.m_extensions.addElement(extNsSpt);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionNamespaceSupport defineJavaNamespace(String ns) {
/* 155 */     return defineJavaNamespace(ns, ns);
/*     */   }
/*     */   
/*     */   public ExtensionNamespaceSupport defineJavaNamespace(String ns, String classOrPackage) {
/* 159 */     if (null == ns || ns.trim().length() == 0) {
/* 160 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 165 */     String className = classOrPackage;
/* 166 */     if (className.startsWith("class:")) {
/* 167 */       className = className.substring(6);
/*     */     }
/* 169 */     int lastSlash = className.lastIndexOf("/");
/* 170 */     if (-1 != lastSlash) {
/* 171 */       className = className.substring(lastSlash + 1);
/*     */     }
/*     */ 
/*     */     
/* 175 */     if (null == className || className.trim().length() == 0) {
/* 176 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 180 */     try { ExtensionHandler.getClassForName(className);
/* 181 */       return new ExtensionNamespaceSupport(ns, "org.apache.xalan.extensions.ExtensionHandlerJavaClass", new Object[] { ns, "javaclass", className }); } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       return new ExtensionNamespaceSupport(ns, "org.apache.xalan.extensions.ExtensionHandlerJavaPackage", new Object[] { ns, "javapackage", className + "." }); }
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
/*     */   
/*     */   private void setPredefinedNamespaces() {
/* 208 */     String uri = "http://xml.apache.org/xalan/java";
/* 209 */     String handlerClassName = "org.apache.xalan.extensions.ExtensionHandlerJavaPackage";
/* 210 */     String lang = "javapackage";
/* 211 */     String lib = "";
/* 212 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 215 */     uri = "http://xml.apache.org/xslt/java";
/* 216 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 219 */     uri = "http://xsl.lotus.com/java";
/* 220 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 223 */     uri = "http://xml.apache.org/xalan";
/* 224 */     handlerClassName = "org.apache.xalan.extensions.ExtensionHandlerJavaClass";
/* 225 */     lang = "javaclass";
/* 226 */     lib = "org.apache.xalan.lib.Extensions";
/* 227 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 230 */     uri = "http://xml.apache.org/xslt";
/* 231 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */ 
/*     */     
/* 235 */     uri = "http://xml.apache.org/xalan/redirect";
/* 236 */     lib = "org.apache.xalan.lib.Redirect";
/* 237 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 240 */     uri = "http://xml.apache.org/xalan/PipeDocument";
/* 241 */     lib = "org.apache.xalan.lib.PipeDocument";
/* 242 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 245 */     uri = "http://xml.apache.org/xalan/sql";
/* 246 */     lib = "org.apache.xalan.lib.sql.XConnection";
/* 247 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     uri = "http://exslt.org/common";
/* 254 */     lib = "org.apache.xalan.lib.ExsltCommon";
/* 255 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 258 */     uri = "http://exslt.org/math";
/* 259 */     lib = "org.apache.xalan.lib.ExsltMath";
/* 260 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 263 */     uri = "http://exslt.org/sets";
/* 264 */     lib = "org.apache.xalan.lib.ExsltSets";
/* 265 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 268 */     uri = "http://exslt.org/dates-and-times";
/* 269 */     lib = "org.apache.xalan.lib.ExsltDatetime";
/* 270 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 273 */     uri = "http://exslt.org/dynamic";
/* 274 */     lib = "org.apache.xalan.lib.ExsltDynamic";
/* 275 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */ 
/*     */     
/* 278 */     uri = "http://exslt.org/strings";
/* 279 */     lib = "org.apache.xalan.lib.ExsltStrings";
/* 280 */     this.m_predefExtensions.addElement(new ExtensionNamespaceSupport(uri, handlerClassName, new Object[] { uri, lang, lib }));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionNamespacesManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */