/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Stack;
/*     */ import org.xml.sax.ContentHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamespaceMappings
/*     */ {
/*  65 */   private int count = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private Stack m_prefixStack = new Stack();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private Hashtable m_namespaces = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private Stack m_nodeStack = new Stack();
/*     */ 
/*     */   
/*     */   private static final String EMPTYSTRING = "";
/*     */ 
/*     */   
/*     */   private static final String XML_PREFIX = "xml";
/*     */ 
/*     */ 
/*     */   
/*     */   public NamespaceMappings() {
/* 105 */     initNamespaces();
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
/*     */   private void initNamespaces() {
/*     */     Stack stack;
/* 118 */     this.m_namespaces.put("", stack = new Stack());
/* 119 */     stack.push("");
/* 120 */     this.m_prefixStack.push("");
/*     */     
/* 122 */     this.m_namespaces.put("xml", stack = new Stack());
/* 123 */     stack.push("http://www.w3.org/XML/1998/namespace");
/* 124 */     this.m_prefixStack.push("xml");
/*     */     
/* 126 */     this.m_nodeStack.push(new Integer(-1));
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
/*     */   public String lookupNamespace(String prefix) {
/* 138 */     Stack stack = (Stack)this.m_namespaces.get(prefix);
/* 139 */     return (stack != null && !stack.isEmpty()) ? stack.peek() : null;
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
/*     */   public String lookupPrefix(String uri) {
/* 152 */     String foundPrefix = null;
/* 153 */     Enumeration prefixes = this.m_namespaces.keys();
/* 154 */     while (prefixes.hasMoreElements()) {
/*     */       
/* 156 */       String prefix = prefixes.nextElement();
/* 157 */       String uri2 = lookupNamespace(prefix);
/* 158 */       if (uri2 != null && uri2.equals(uri)) {
/*     */         
/* 160 */         foundPrefix = prefix;
/*     */         break;
/*     */       } 
/*     */     } 
/* 164 */     return foundPrefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean popNamespace(String prefix) {
/* 173 */     if (prefix.startsWith("xml"))
/*     */     {
/* 175 */       return false;
/*     */     }
/*     */     
/*     */     Stack stack;
/* 179 */     if ((stack = (Stack)this.m_namespaces.get(prefix)) != null) {
/*     */       
/* 181 */       stack.pop();
/* 182 */       return true;
/*     */     } 
/* 184 */     return false;
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
/*     */   public boolean pushNamespace(String prefix, String uri, int elemDepth) {
/* 196 */     if (prefix.startsWith("xml"))
/*     */     {
/* 198 */       return false;
/*     */     }
/*     */     
/*     */     Stack stack;
/*     */     
/* 203 */     if ((stack = (Stack)this.m_namespaces.get(prefix)) == null)
/*     */     {
/* 205 */       this.m_namespaces.put(prefix, stack = new Stack());
/*     */     }
/*     */     
/* 208 */     if (!stack.empty() && uri.equals(stack.peek()))
/*     */     {
/* 210 */       return false;
/*     */     }
/*     */     
/* 213 */     stack.push(uri);
/* 214 */     this.m_prefixStack.push(prefix);
/* 215 */     this.m_nodeStack.push(new Integer(elemDepth));
/* 216 */     return true;
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
/*     */   public void popNamespaces(int elemDepth, ContentHandler saxHandler) {
/*     */     while (true) {
/* 231 */       if (this.m_nodeStack.isEmpty())
/*     */         return; 
/* 233 */       Integer i = this.m_nodeStack.peek();
/* 234 */       if (i.intValue() < elemDepth) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 240 */       this.m_nodeStack.pop();
/* 241 */       String prefix = this.m_prefixStack.pop();
/* 242 */       popNamespace(prefix);
/* 243 */       if (saxHandler != null) {
/*     */ 
/*     */         
/*     */         try { 
/* 247 */           saxHandler.endPrefixMapping(prefix); } catch (SAXException sAXException) {}
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
/*     */   public String generateNextPrefix() {
/* 264 */     return "ns" + this.count++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 273 */     NamespaceMappings clone = new NamespaceMappings();
/* 274 */     clone.m_prefixStack = (Stack)this.m_prefixStack.clone();
/* 275 */     clone.m_nodeStack = (Stack)this.m_nodeStack.clone();
/* 276 */     clone.m_namespaces = (Hashtable)this.m_namespaces.clone();
/*     */     
/* 278 */     clone.count = this.count;
/* 279 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void reset() {
/* 285 */     this.count = 0;
/* 286 */     this.m_namespaces.clear();
/* 287 */     this.m_nodeStack.clear();
/* 288 */     this.m_prefixStack.clear();
/*     */     
/* 290 */     initNamespaces();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/NamespaceMappings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */