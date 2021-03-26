/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrefixResolverDefault
/*     */   implements PrefixResolver
/*     */ {
/*     */   Node m_context;
/*     */   
/*     */   public PrefixResolverDefault(Node xpathExpressionContext) {
/*  51 */     this.m_context = xpathExpressionContext;
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
/*     */   public String getNamespaceForPrefix(String prefix) {
/*  64 */     return getNamespaceForPrefix(prefix, this.m_context);
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
/*     */   public String getNamespaceForPrefix(String prefix, Node namespaceContext) {
/*  81 */     Node parent = namespaceContext;
/*  82 */     String namespace = null;
/*     */     
/*  84 */     if (prefix.equals("xml")) {
/*     */       
/*  86 */       namespace = "http://www.w3.org/XML/1998/namespace";
/*     */     } else {
/*     */       int type;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       while (null != parent && null == namespace && ((type = parent.getNodeType()) == 1 || type == 5)) {
/*     */ 
/*     */         
/*  96 */         if (type == 1) {
/*     */           
/*  98 */           if (parent.getNodeName().indexOf(prefix + ":") == 0)
/*  99 */             return parent.getNamespaceURI(); 
/* 100 */           NamedNodeMap nnm = parent.getAttributes();
/*     */           
/* 102 */           for (int i = 0; i < nnm.getLength(); i++) {
/*     */             
/* 104 */             Node attr = nnm.item(i);
/* 105 */             String aname = attr.getNodeName();
/* 106 */             boolean isPrefix = aname.startsWith("xmlns:");
/*     */             
/* 108 */             if (isPrefix || aname.equals("xmlns")) {
/*     */               
/* 110 */               int index = aname.indexOf(':');
/* 111 */               String p = isPrefix ? aname.substring(index + 1) : "";
/*     */               
/* 113 */               if (p.equals(prefix)) {
/*     */                 
/* 115 */                 namespace = attr.getNodeValue();
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 123 */         parent = parent.getParentNode();
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     return namespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseIdentifier() {
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handlesNullPrefixes() {
/* 143 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/PrefixResolverDefault.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */