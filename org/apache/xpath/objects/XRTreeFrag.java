/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeList;
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.RTFIterator;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XRTreeFrag
/*     */   extends XObject
/*     */   implements Cloneable
/*     */ {
/*     */   DTM m_dtm;
/*     */   int m_dtmRoot;
/*     */   XPathContext m_xctxt;
/*     */   boolean m_allowRelease = false;
/*     */   private XMLString m_xmlStr;
/*     */   
/*     */   public XRTreeFrag(int root, XPathContext xctxt, ExpressionNode parent) {
/*  63 */     super(null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     this.m_xmlStr = null; exprSetParent(parent); this.m_dtmRoot = root; this.m_xctxt = xctxt; this.m_dtm = xctxt.getDTM(root); } public XRTreeFrag(int root, XPathContext xctxt) { super(null); this.m_xmlStr = null; this.m_dtmRoot = root; this.m_xctxt = xctxt; this.m_dtm = xctxt.getDTM(root); } public Object object() { if (this.m_xctxt != null) return new DTMNodeIterator((DTMIterator)new NodeSetDTM(this.m_dtmRoot, this.m_xctxt.getDTMManager()));  return super.object(); } public XRTreeFrag(Expression expr) { super(expr); this.m_xmlStr = null; }
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/*     */       destruct();
/*     */     } finally {
/*     */       super.finalize();
/*     */     } 
/*     */   } public void allowDetachToRelease(boolean allowRelease) {
/*     */     this.m_allowRelease = allowRelease;
/* 263 */   } public XMLString xstr() { if (null == this.m_xmlStr) {
/* 264 */       this.m_xmlStr = this.m_dtm.getStringValue(this.m_dtmRoot);
/*     */     }
/* 266 */     return this.m_xmlStr; }
/*     */   public void detach() { if (this.m_allowRelease) { int ident = this.m_xctxt.getDTMIdentity(this.m_dtm); DTM foundDTM = this.m_xctxt.getDTM(ident); if (foundDTM == this.m_dtm) { this.m_xctxt.release(this.m_dtm, true); this.m_dtm = null; this.m_xctxt = null; }
/*     */        this.m_obj = null; }
/*     */      }
/*     */   public void destruct() { if (null != this.m_dtm) { int ident = this.m_xctxt.getDTMIdentity(this.m_dtm); DTM foundDTM = this.m_xctxt.getDTM(ident); if (foundDTM == this.m_dtm) { this.m_xctxt.release(this.m_dtm, true); this.m_dtm = null; this.m_xctxt = null; }
/*     */        }
/*     */      this.m_obj = null; }
/*     */   public int getType() { return 5; }
/*     */   public String getTypeString() { return "#RTREEFRAG"; }
/*     */   public double num() throws TransformerException { XMLString s = xstr(); return s.toDouble(); }
/* 276 */   public boolean bool() { return true; } public void appendToFsb(FastStringBuffer fsb) { XString xstring = (XString)xstr();
/* 277 */     xstring.appendToFsb(fsb); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 288 */     String str = this.m_dtm.getStringValue(this.m_dtmRoot).toString();
/*     */     
/* 290 */     return (null == str) ? "" : str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int rtf() {
/* 300 */     return this.m_dtmRoot;
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
/*     */   public DTMIterator asNodeIterator() {
/* 312 */     return (DTMIterator)new RTFIterator(this.m_dtmRoot, this.m_xctxt.getDTMManager());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList convertToNodeset() {
/* 323 */     if (this.m_obj instanceof NodeList) {
/* 324 */       return (NodeList)this.m_obj;
/*     */     }
/* 326 */     return (NodeList)new DTMNodeList(asNodeIterator());
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
/*     */   public boolean equals(XObject obj2) {
/*     */     
/* 343 */     try { if (4 == obj2.getType())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 349 */         return obj2.equals(this);
/*     */       }
/* 351 */       if (1 == obj2.getType())
/*     */       {
/* 353 */         return (bool() == obj2.bool());
/*     */       }
/* 355 */       if (2 == obj2.getType())
/*     */       {
/* 357 */         return (num() == obj2.num());
/*     */       }
/* 359 */       if (4 == obj2.getType())
/*     */       {
/* 361 */         return xstr().equals(obj2.xstr());
/*     */       }
/* 363 */       if (3 == obj2.getType())
/*     */       {
/* 365 */         return xstr().equals(obj2.xstr());
/*     */       }
/* 367 */       if (5 == obj2.getType())
/*     */       {
/*     */ 
/*     */         
/* 371 */         return xstr().equals(obj2.xstr());
/*     */       }
/*     */ 
/*     */       
/* 375 */       return super.equals(obj2); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 380 */       throw new WrappedRuntimeException(te); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XRTreeFrag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */