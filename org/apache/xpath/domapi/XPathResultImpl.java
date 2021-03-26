/*     */ package org.apache.xpath.domapi;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ import org.w3c.dom.xpath.XPathException;
/*     */ import org.w3c.dom.xpath.XPathResult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPathResultImpl
/*     */   implements EventListener, XPathResult
/*     */ {
/*     */   private XObject m_resultObj;
/*  70 */   private short m_resultType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isInvalidIteratorState = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private Node m_contextNode;
/*     */ 
/*     */ 
/*     */   
/*  83 */   private NodeIterator m_iterator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private NodeList m_list = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   XPathResultImpl(short type, XObject result, Node contextNode) {
/*  98 */     if (!isValidType(type)) {
/*  99 */       String fmsg = XPATHMessages.createXPATHMessage("ER_INVALID_XPATH_TYPE", new Object[] { new Integer(type) });
/* 100 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */ 
/*     */     
/* 104 */     if (null == result) {
/* 105 */       String fmsg = XPATHMessages.createXPATHMessage("ER_EMPTY_XPATH_RESULT", null);
/* 106 */       throw new XPathException((short)1, fmsg);
/*     */     } 
/*     */     
/* 109 */     this.m_resultObj = result;
/* 110 */     this.m_contextNode = contextNode;
/*     */ 
/*     */     
/* 113 */     if (type == 0) {
/* 114 */       this.m_resultType = getTypeFromXObject(result);
/*     */     } else {
/* 116 */       this.m_resultType = type;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 121 */     if ((this.m_resultType == 5 || this.m_resultType == 4) && contextNode instanceof EventTarget)
/*     */     {
/*     */       
/* 124 */       ((EventTarget)contextNode).addEventListener("MutationEvents", this, true);
/*     */     }
/*     */ 
/*     */     
/* 128 */     if (this.m_resultType == 5 || this.m_resultType == 4 || this.m_resultType == 8 || this.m_resultType == 9) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */         
/* 134 */         this.m_iterator = this.m_resultObj.nodeset(); } catch (TransformerException te)
/*     */       
/*     */       { 
/* 137 */         String fmsg = XPATHMessages.createXPATHMessage("ER_INCOMPATIBLE_TYPES", new Object[] { getTypeString(getTypeFromXObject(this.m_resultObj)), getTypeString(this.m_resultType) });
/* 138 */         throw new XPathException((short)2, fmsg);
/*     */ 
/*     */ 
/*     */         
/*     */          }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 149 */     else if (this.m_resultType == 6 || this.m_resultType == 7) {
/*     */ 
/*     */       
/* 152 */       try { this.m_list = this.m_resultObj.nodelist(); } catch (TransformerException te)
/*     */       
/*     */       { 
/* 155 */         String fmsg = XPATHMessages.createXPATHMessage("ER_INCOMPATIBLE_TYPES", new Object[] { getTypeString(getTypeFromXObject(this.m_resultObj)), getTypeString(this.m_resultType) });
/* 156 */         throw new XPathException((short)2, fmsg); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getResultType() {
/* 165 */     return this.m_resultType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNumberValue() throws XPathException {
/* 176 */     if (getResultType() != 1) {
/* 177 */       String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_NUMBER", new Object[] { getTypeString(this.m_resultType) });
/* 178 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */     
/* 181 */     try { return this.m_resultObj.num(); } catch (Exception e)
/*     */     
/*     */     { 
/* 184 */       throw new XPathException((short)2, e.getMessage()); }
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
/*     */   public String getStringValue() throws XPathException {
/* 198 */     if (getResultType() != 2) {
/* 199 */       String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_STRING", new Object[] { this.m_resultObj.getTypeString() });
/* 200 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */     
/* 203 */     try { return this.m_resultObj.str(); } catch (Exception e)
/*     */     
/*     */     { 
/* 206 */       throw new XPathException((short)2, e.getMessage()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBooleanValue() throws XPathException {
/* 215 */     if (getResultType() != 3) {
/* 216 */       String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_BOOLEAN", new Object[] { getTypeString(this.m_resultType) });
/* 217 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */     
/* 220 */     try { return this.m_resultObj.bool(); } catch (TransformerException e)
/*     */     
/*     */     { 
/* 223 */       throw new XPathException((short)2, e.getMessage()); }
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
/*     */   public Node getSingleNodeValue() throws XPathException {
/* 239 */     if (this.m_resultType != 8 && this.m_resultType != 9) {
/*     */       
/* 241 */       String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_SINGLENODE", new Object[] { getTypeString(this.m_resultType) });
/* 242 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */ 
/*     */     
/* 246 */     NodeIterator result = null;
/*     */     
/* 248 */     try { result = this.m_resultObj.nodeset(); } catch (TransformerException te)
/*     */     
/* 250 */     { throw new XPathException((short)2, te.getMessage()); }
/*     */ 
/*     */     
/* 253 */     if (null == result) return null;
/*     */     
/* 255 */     Node node = result.nextNode();
/*     */ 
/*     */     
/* 258 */     if (isNamespaceNode(node)) {
/* 259 */       return new XPathNamespaceImpl(node);
/*     */     }
/* 261 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInvalidIteratorState() {
/* 269 */     return this.m_isInvalidIteratorState;
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
/*     */   public int getSnapshotLength() throws XPathException {
/* 285 */     if (this.m_resultType != 6 && this.m_resultType != 7) {
/*     */       
/* 287 */       String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_GET_SNAPSHOT_LENGTH", new Object[] { getTypeString(this.m_resultType) });
/* 288 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */ 
/*     */     
/* 292 */     return this.m_list.getLength();
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
/*     */   public Node iterateNext() throws XPathException, DOMException {
/* 309 */     if (this.m_resultType != 4 && this.m_resultType != 5) {
/*     */       
/* 311 */       String fmsg = XPATHMessages.createXPATHMessage("ER_NON_ITERATOR_TYPE", new Object[] { getTypeString(this.m_resultType) });
/* 312 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */     
/* 315 */     if (getInvalidIteratorState()) {
/* 316 */       String fmsg = XPATHMessages.createXPATHMessage("ER_DOC_MUTATED", null);
/* 317 */       throw new DOMException((short)11, fmsg);
/*     */     } 
/*     */     
/* 320 */     Node node = this.m_iterator.nextNode();
/*     */ 
/*     */     
/* 323 */     if (isNamespaceNode(node)) {
/* 324 */       return new XPathNamespaceImpl(node);
/*     */     }
/* 326 */     return node;
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
/*     */   public Node snapshotItem(int index) throws XPathException {
/* 349 */     if (this.m_resultType != 6 && this.m_resultType != 7) {
/*     */       
/* 351 */       String fmsg = XPATHMessages.createXPATHMessage("ER_NON_SNAPSHOT_TYPE", new Object[] { getTypeString(this.m_resultType) });
/* 352 */       throw new XPathException((short)2, fmsg);
/*     */     } 
/*     */ 
/*     */     
/* 356 */     Node node = this.m_list.item(index);
/*     */ 
/*     */     
/* 359 */     if (isNamespaceNode(node)) {
/* 360 */       return new XPathNamespaceImpl(node);
/*     */     }
/* 362 */     return node;
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
/*     */   public static boolean isValidType(short type) {
/* 374 */     switch (type) { case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/* 384 */         return true; }
/* 385 */      return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEvent(Event event) {
/* 394 */     if (event.getType().equals("MutationEvents")) {
/*     */       
/* 396 */       this.m_isInvalidIteratorState = true;
/*     */ 
/*     */       
/* 399 */       ((EventTarget)this.m_contextNode).removeEventListener("MutationEvents", this, true);
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
/*     */   public String getTypeString(int type) {
/* 412 */     switch (type) { case 0:
/* 413 */         return "ANY_TYPE";
/* 414 */       case 8: return "ANY_UNORDERED_NODE_TYPE";
/* 415 */       case 3: return "BOOLEAN";
/* 416 */       case 9: return "FIRST_ORDERED_NODE_TYPE";
/* 417 */       case 1: return "NUMBER_TYPE";
/* 418 */       case 5: return "ORDERED_NODE_ITERATOR_TYPE";
/* 419 */       case 7: return "ORDERED_NODE_SNAPSHOT_TYPE";
/* 420 */       case 2: return "STRING_TYPE";
/* 421 */       case 4: return "UNORDERED_NODE_ITERATOR_TYPE";
/* 422 */       case 6: return "UNORDERED_NODE_SNAPSHOT_TYPE"; }
/* 423 */      return "#UNKNOWN";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short getTypeFromXObject(XObject object) {
/* 433 */     switch (object.getType()) { case 1:
/* 434 */         return 3;
/* 435 */       case 4: return 4;
/* 436 */       case 2: return 1;
/* 437 */       case 3: return 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 450 */         return 4;
/* 451 */       case -1: return 0; }
/* 452 */      return 0;
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
/*     */   private boolean isNamespaceNode(Node node) {
/* 466 */     if (null != node && node.getNodeType() == 2 && (node.getNodeName().startsWith("xmlns:") || node.getNodeName().equals("xmlns")))
/*     */     {
/*     */       
/* 469 */       return true;
/*     */     }
/* 471 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/domapi/XPathResultImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */