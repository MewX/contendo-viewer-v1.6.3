/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.CharacterData;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ public abstract class AbstractCharacterData
/*     */   extends AbstractChildNode
/*     */   implements CharacterData
/*     */ {
/*  38 */   protected String nodeValue = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeValue() throws DOMException {
/*  45 */     return this.nodeValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  52 */     if (isReadonly()) {
/*  53 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     String val = this.nodeValue;
/*  60 */     this.nodeValue = (nodeValue == null) ? "" : nodeValue;
/*     */ 
/*     */     
/*  63 */     fireDOMCharacterDataModifiedEvent(val, this.nodeValue);
/*  64 */     if (getParentNode() != null) {
/*  65 */       ((AbstractParentNode)getParentNode()).fireDOMSubtreeModifiedEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getData() throws DOMException {
/*  75 */     return getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String data) throws DOMException {
/*  83 */     setNodeValue(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  91 */     return this.nodeValue.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String substringData(int offset, int count) throws DOMException {
/*  99 */     checkOffsetCount(offset, count);
/*     */     
/* 101 */     String v = getNodeValue();
/* 102 */     return v.substring(offset, Math.min(v.length(), offset + count));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendData(String arg) throws DOMException {
/* 110 */     if (isReadonly()) {
/* 111 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 116 */     setNodeValue(getNodeValue() + ((arg == null) ? "" : arg));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertData(int offset, String arg) throws DOMException {
/* 124 */     if (isReadonly()) {
/* 125 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (offset < 0 || offset > getLength()) {
/* 131 */       throw createDOMException((short)1, "offset", new Object[] { Integer.valueOf(offset) });
/*     */     }
/*     */ 
/*     */     
/* 135 */     String v = getNodeValue();
/* 136 */     setNodeValue(v.substring(0, offset) + arg + v.substring(offset, v.length()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteData(int offset, int count) throws DOMException {
/* 145 */     if (isReadonly()) {
/* 146 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 151 */     checkOffsetCount(offset, count);
/*     */     
/* 153 */     String v = getNodeValue();
/* 154 */     setNodeValue(v.substring(0, offset) + v.substring(Math.min(v.length(), offset + count), v.length()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceData(int offset, int count, String arg) throws DOMException {
/* 165 */     if (isReadonly()) {
/* 166 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 171 */     checkOffsetCount(offset, count);
/*     */     
/* 173 */     String v = getNodeValue();
/* 174 */     setNodeValue(v.substring(0, offset) + arg + v.substring(Math.min(v.length(), offset + count), v.length()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkOffsetCount(int offset, int count) throws DOMException {
/* 185 */     if (offset < 0 || offset >= getLength()) {
/* 186 */       throw createDOMException((short)1, "offset", new Object[] { Integer.valueOf(offset) });
/*     */     }
/*     */ 
/*     */     
/* 190 */     if (count < 0) {
/* 191 */       throw createDOMException((short)1, "negative.count", new Object[] { Integer.valueOf(count) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 201 */     super.export(n, d);
/* 202 */     AbstractCharacterData cd = (AbstractCharacterData)n;
/* 203 */     cd.nodeValue = this.nodeValue;
/* 204 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 211 */     super.deepExport(n, d);
/* 212 */     AbstractCharacterData cd = (AbstractCharacterData)n;
/* 213 */     cd.nodeValue = this.nodeValue;
/* 214 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 222 */     super.copyInto(n);
/* 223 */     AbstractCharacterData cd = (AbstractCharacterData)n;
/* 224 */     cd.nodeValue = this.nodeValue;
/* 225 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 233 */     super.deepCopyInto(n);
/* 234 */     AbstractCharacterData cd = (AbstractCharacterData)n;
/* 235 */     cd.nodeValue = this.nodeValue;
/* 236 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractCharacterData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */