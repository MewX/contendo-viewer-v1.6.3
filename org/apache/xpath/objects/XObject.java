/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathException;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
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
/*     */ public class XObject
/*     */   extends Expression
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   protected Object m_obj;
/*     */   public static final int CLASS_NULL = -1;
/*     */   public static final int CLASS_UNKNOWN = 0;
/*     */   public static final int CLASS_BOOLEAN = 1;
/*     */   public static final int CLASS_NUMBER = 2;
/*     */   public static final int CLASS_STRING = 3;
/*     */   public static final int CLASS_NODESET = 4;
/*     */   public static final int CLASS_RTREEFRAG = 5;
/*     */   public static final int CLASS_UNRESOLVEDVARIABLE = 600;
/*     */   
/*     */   public XObject() {}
/*     */   
/*     */   public XObject(Object obj) {
/*  68 */     this.m_obj = obj;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  83 */     return this;
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
/*     */   public void allowDetachToRelease(boolean allowRelease) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destruct() {
/* 114 */     if (null != this.m_obj) {
/*     */       
/* 116 */       allowDetachToRelease(true);
/* 117 */       detach();
/*     */       
/* 119 */       this.m_obj = null;
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
/*     */   public void reset() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchCharactersEvents(ContentHandler ch) throws SAXException {
/* 144 */     xstr().dispatchCharactersEvents(ch);
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
/*     */   public static XObject create(Object val) {
/* 158 */     return XObjectFactory.create(val);
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
/*     */   public static XObject create(Object val, XPathContext xctxt) {
/* 173 */     return XObjectFactory.create(val, xctxt);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 207 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/* 218 */     return "#UNKNOWN (" + object().getClass().getName() + ")";
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
/*     */   public double num() throws TransformerException {
/* 231 */     error("ER_CANT_CONVERT_TO_NUMBER", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 234 */     return 0.0D;
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
/*     */   public double numWithSideEffects() throws TransformerException {
/* 246 */     return num();
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
/*     */   public boolean bool() throws TransformerException {
/* 259 */     error("ER_CANT_CONVERT_TO_NUMBER", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 262 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean boolWithSideEffects() throws TransformerException {
/* 273 */     return bool();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLString xstr() {
/* 284 */     return XMLStringFactoryImpl.getFactory().newstr(str());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 294 */     return (this.m_obj != null) ? this.m_obj.toString() : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 305 */     return str();
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
/*     */   public int rtf(XPathContext support) {
/* 318 */     int result = rtf();
/*     */     
/* 320 */     if (-1 == result) {
/*     */       
/* 322 */       DTM frag = support.createDocumentFragment();
/*     */ 
/*     */       
/* 325 */       frag.appendTextChild(str());
/*     */       
/* 327 */       result = frag.getDocument();
/*     */     } 
/*     */     
/* 330 */     return result;
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
/*     */   public DocumentFragment rtree(XPathContext support) {
/* 342 */     DocumentFragment docFrag = null;
/* 343 */     int result = rtf();
/*     */     
/* 345 */     if (-1 == result) {
/*     */       
/* 347 */       DTM frag = support.createDocumentFragment();
/*     */ 
/*     */       
/* 350 */       frag.appendTextChild(str());
/*     */       
/* 352 */       docFrag = (DocumentFragment)frag.getNode(frag.getDocument());
/*     */     }
/*     */     else {
/*     */       
/* 356 */       DTM frag = support.getDTM(result);
/* 357 */       docFrag = (DocumentFragment)frag.getNode(frag.getDocument());
/*     */     } 
/*     */     
/* 360 */     return docFrag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentFragment rtree() {
/* 371 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int rtf() {
/* 381 */     return -1;
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
/* 392 */     return this.m_obj;
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
/*     */   public DTMIterator iter() throws TransformerException {
/* 405 */     error("ER_CANT_CONVERT_TO_NODELIST", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 408 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject getFresh() {
/* 418 */     return this;
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
/*     */   public NodeIterator nodeset() throws TransformerException {
/* 432 */     error("ER_CANT_CONVERT_TO_NODELIST", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 435 */     return null;
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
/* 448 */     error("ER_CANT_CONVERT_TO_NODELIST", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 451 */     return null;
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
/*     */   public NodeSetDTM mutableNodeset() throws TransformerException {
/* 466 */     error("ER_CANT_CONVERT_TO_MUTABLENODELIST", new Object[] { getTypeString() });
/*     */ 
/*     */     
/* 469 */     return (NodeSetDTM)this.m_obj;
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
/*     */   public Object castToType(int t, XPathContext support) throws TransformerException {
/* 488 */     switch (t)
/*     */     
/*     */     { case 3:
/* 491 */         result = str();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 518 */         return result;case 2: result = new Double(num()); return result;case 4: result = iter(); return result;case 1: result = new Boolean(bool()); return result;case 0: result = this.m_obj; return result; }  error("ER_CANT_CONVERT_TO_TYPE", new Object[] { getTypeString(), Integer.toString(t) }); Object result = null; return result;
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
/*     */   public boolean lessThan(XObject obj2) throws TransformerException {
/* 539 */     if (obj2.getType() == 4) {
/* 540 */       return obj2.greaterThan(this);
/*     */     }
/* 542 */     return (num() < obj2.num());
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
/*     */   public boolean lessThanOrEqual(XObject obj2) throws TransformerException {
/* 563 */     if (obj2.getType() == 4) {
/* 564 */       return obj2.greaterThanOrEqual(this);
/*     */     }
/* 566 */     return (num() <= obj2.num());
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
/*     */   public boolean greaterThan(XObject obj2) throws TransformerException {
/* 587 */     if (obj2.getType() == 4) {
/* 588 */       return obj2.lessThan(this);
/*     */     }
/* 590 */     return (num() > obj2.num());
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
/*     */   public boolean greaterThanOrEqual(XObject obj2) throws TransformerException {
/* 611 */     if (obj2.getType() == 4) {
/* 612 */       return obj2.lessThanOrEqual(this);
/*     */     }
/* 614 */     return (num() >= obj2.num());
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
/*     */   public boolean equals(XObject obj2) {
/* 632 */     if (obj2.getType() == 4) {
/* 633 */       return obj2.equals(this);
/*     */     }
/* 635 */     if (null != this.m_obj)
/*     */     {
/* 637 */       return this.m_obj.equals(obj2.m_obj);
/*     */     }
/*     */ 
/*     */     
/* 641 */     return (obj2.m_obj == null);
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
/*     */   public boolean notEquals(XObject obj2) throws TransformerException {
/* 661 */     if (obj2.getType() == 4) {
/* 662 */       return obj2.notEquals(this);
/*     */     }
/* 664 */     return !equals(obj2);
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
/*     */   protected void error(String msg) throws TransformerException {
/* 678 */     error(msg, null);
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
/*     */   protected void error(String msg, Object[] args) throws TransformerException {
/* 694 */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 702 */     throw new XPathException(fmsg, this);
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToFsb(FastStringBuffer fsb) {
/* 725 */     fsb.append(str());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 733 */     assertion(false, "callVisitors should not be called for this object!!!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 740 */     if (!isSameClass(expr)) {
/* 741 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 746 */     if (!equals((XObject)expr)) {
/* 747 */       return false;
/*     */     }
/* 749 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XObject.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */