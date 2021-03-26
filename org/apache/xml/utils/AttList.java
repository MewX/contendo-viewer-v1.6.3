/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttList
/*     */   implements Attributes
/*     */ {
/*     */   NamedNodeMap m_attrs;
/*     */   int m_lastIndex;
/*     */   DOMHelper m_dh;
/*     */   
/*     */   public AttList(NamedNodeMap attrs, DOMHelper dh) {
/*  70 */     this.m_attrs = attrs;
/*  71 */     this.m_lastIndex = this.m_attrs.getLength() - 1;
/*  72 */     this.m_dh = dh;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  83 */     return this.m_attrs.getLength();
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
/*     */   public String getURI(int index) {
/*  96 */     String ns = this.m_dh.getNamespaceOfNode(this.m_attrs.item(index));
/*  97 */     if (null == ns)
/*  98 */       ns = ""; 
/*  99 */     return ns;
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
/*     */   public String getLocalName(int index) {
/* 112 */     return this.m_dh.getLocalNameOfNode(this.m_attrs.item(index));
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
/*     */   public String getQName(int i) {
/* 125 */     return ((Attr)this.m_attrs.item(i)).getName();
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
/*     */   public String getType(int i) {
/* 138 */     return "CDATA";
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
/*     */   public String getValue(int i) {
/* 151 */     return ((Attr)this.m_attrs.item(i)).getValue();
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
/*     */   public String getType(String name) {
/* 164 */     return "CDATA";
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
/*     */   public String getType(String uri, String localName) {
/* 179 */     return "CDATA";
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
/*     */   public String getValue(String name) {
/* 192 */     Attr attr = (Attr)this.m_attrs.getNamedItem(name);
/* 193 */     return (null != attr) ? attr.getValue() : null;
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
/*     */   public String getValue(String uri, String localName) {
/* 208 */     Node a = this.m_attrs.getNamedItemNS(uri, localName);
/* 209 */     return (a == null) ? null : a.getNodeValue();
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
/*     */   public int getIndex(String uri, String localPart) {
/* 223 */     for (int i = this.m_attrs.getLength() - 1; i >= 0; i--) {
/*     */       
/* 225 */       Node a = this.m_attrs.item(i);
/* 226 */       String u = a.getNamespaceURI();
/* 227 */       if (((u == null) ? ((uri == null)) : u.equals(uri)) && a.getLocalName().equals(localPart))
/*     */       {
/*     */         
/* 230 */         return i; } 
/*     */     } 
/* 232 */     return -1;
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
/*     */   public int getIndex(String qName) {
/* 244 */     for (int i = this.m_attrs.getLength() - 1; i >= 0; i--) {
/*     */       
/* 246 */       Node a = this.m_attrs.item(i);
/* 247 */       if (a.getNodeName().equals(qName))
/* 248 */         return i; 
/*     */     } 
/* 250 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/AttList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */