/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XNodeSetForDOM
/*     */   extends XNodeSet
/*     */ {
/*     */   Object m_origObj;
/*     */   
/*     */   public XNodeSetForDOM(Node node, DTMManager dtmMgr) {
/*  39 */     this.m_dtmMgr = dtmMgr;
/*  40 */     this.m_origObj = node;
/*  41 */     int dtmHandle = dtmMgr.getDTMHandleFromNode(node);
/*  42 */     ((XObject)this).m_obj = new NodeSetDTM(dtmMgr);
/*  43 */     ((NodeSetDTM)((XObject)this).m_obj).addNode(dtmHandle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XNodeSetForDOM(XNodeSet val) {
/*  53 */     super(val);
/*  54 */     if (val instanceof XNodeSetForDOM) {
/*  55 */       this.m_origObj = ((XNodeSetForDOM)val).m_origObj;
/*     */     }
/*     */   }
/*     */   
/*     */   public XNodeSetForDOM(NodeList nodeList, XPathContext xctxt) {
/*  60 */     this.m_dtmMgr = xctxt.getDTMManager();
/*  61 */     this.m_origObj = nodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     NodeSetDTM nsdtm = new NodeSetDTM(nodeList, xctxt);
/*  68 */     this.m_last = nsdtm.getLength();
/*  69 */     ((XObject)this).m_obj = nsdtm;
/*     */   }
/*     */ 
/*     */   
/*     */   public XNodeSetForDOM(NodeIterator nodeIter, XPathContext xctxt) {
/*  74 */     this.m_dtmMgr = xctxt.getDTMManager();
/*  75 */     this.m_origObj = nodeIter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     NodeSetDTM nsdtm = new NodeSetDTM(nodeIter, xctxt);
/*  82 */     this.m_last = nsdtm.getLength();
/*  83 */     ((XObject)this).m_obj = nsdtm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object object() {
/*  94 */     return this.m_origObj;
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
/*     */   public NodeIterator nodeset() throws TransformerException {
/* 106 */     return (this.m_origObj instanceof NodeIterator) ? (NodeIterator)this.m_origObj : super.nodeset();
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
/*     */   public NodeList nodelist() throws TransformerException {
/* 119 */     return (this.m_origObj instanceof NodeList) ? (NodeList)this.m_origObj : super.nodelist();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XNodeSetForDOM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */