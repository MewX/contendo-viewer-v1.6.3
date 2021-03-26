/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ public class DTMNamedNodeMap
/*     */   implements NamedNodeMap
/*     */ {
/*     */   DTM dtm;
/*     */   int element;
/*  52 */   short m_count = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMNamedNodeMap(DTM dtm, int element) {
/*  62 */     this.dtm = dtm;
/*  63 */     this.element = element;
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
/*  74 */     if (this.m_count == -1) {
/*     */       
/*  76 */       short count = 0;
/*     */       
/*  78 */       for (int n = this.dtm.getFirstAttribute(this.element); n != -1; 
/*  79 */         n = this.dtm.getNextAttribute(n))
/*     */       {
/*  81 */         count = (short)(count + 1);
/*     */       }
/*     */       
/*  84 */       this.m_count = count;
/*     */     } 
/*     */     
/*  87 */     return this.m_count;
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
/*     */   public Node getNamedItem(String name) {
/* 102 */     for (int n = this.dtm.getFirstAttribute(this.element); n != -1; 
/* 103 */       n = this.dtm.getNextAttribute(n)) {
/*     */       
/* 105 */       if (this.dtm.getNodeName(n).equals(name)) {
/* 106 */         return this.dtm.getNode(n);
/*     */       }
/*     */     } 
/* 109 */     return null;
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
/*     */   public Node item(int i) {
/* 125 */     int count = 0;
/*     */     
/* 127 */     for (int n = this.dtm.getFirstAttribute(this.element); n != -1; 
/* 128 */       n = this.dtm.getNextAttribute(n)) {
/*     */       
/* 130 */       if (count == i) {
/* 131 */         return this.dtm.getNode(n);
/*     */       }
/* 133 */       count++;
/*     */     } 
/*     */     
/* 136 */     return null;
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
/*     */   public Node setNamedItem(Node newNode) {
/* 165 */     throw new DTMException(this, (short)7);
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
/*     */   public Node removeNamedItem(String name) {
/* 185 */     throw new DTMException(this, (short)7);
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
/*     */   public Node getNamedItemNS(String namespaceURI, String localName) {
/* 201 */     throw new DTMException(this, (short)9);
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
/*     */   public Node setNamedItemNS(Node arg) throws DOMException {
/* 229 */     throw new DTMException(this, (short)7);
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
/*     */   public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
/* 255 */     throw new DTMException(this, (short)7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class DTMException
/*     */     extends DOMException
/*     */   {
/*     */     private final DTMNamedNodeMap this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DTMException(DTMNamedNodeMap this$0, short code, String message) {
/* 273 */       super(code, message);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DTMException(DTMNamedNodeMap this$0, short code) {
/* 284 */       super(code, "");
/*     */       this.this$0 = this$0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMNamedNodeMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */