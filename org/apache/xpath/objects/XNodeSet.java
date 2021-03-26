/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeList;
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.axes.NodeSequence;
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
/*     */ public class XNodeSet
/*     */   extends NodeSequence
/*     */ {
/*     */   protected XNodeSet() {}
/*     */   
/*     */   public XNodeSet(DTMIterator val) {
/*  53 */     if (val instanceof XNodeSet) {
/*     */       
/*  55 */       setIter(((XNodeSet)val).m_iter);
/*  56 */       this.m_dtmMgr = ((XNodeSet)val).m_dtmMgr;
/*  57 */       this.m_last = ((XNodeSet)val).m_last;
/*  58 */       if (!((XNodeSet)val).hasCache())
/*  59 */         ((XNodeSet)val).setShouldCacheNodes(true); 
/*  60 */       ((XObject)this).m_obj = ((XObject)val).m_obj;
/*     */     } else {
/*     */       
/*  63 */       setIter(val);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XNodeSet(XNodeSet val) {
/*  74 */     setIter(val.m_iter);
/*  75 */     this.m_dtmMgr = val.m_dtmMgr;
/*  76 */     this.m_last = val.m_last;
/*  77 */     if (!val.hasCache())
/*  78 */       val.setShouldCacheNodes(true); 
/*  79 */     ((XObject)this).m_obj = ((XObject)val).m_obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XNodeSet(DTMManager dtmMgr) {
/*  89 */     this(-1, dtmMgr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XNodeSet(int n, DTMManager dtmMgr) {
/* 100 */     super(new NodeSetDTM(dtmMgr));
/* 101 */     this.m_dtmMgr = dtmMgr;
/*     */     
/* 103 */     if (-1 != n) {
/*     */       
/* 105 */       ((NodeSetDTM)((XObject)this).m_obj).addNode(n);
/* 106 */       this.m_last = 1;
/*     */     } else {
/*     */       
/* 109 */       this.m_last = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 119 */     return 4;
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
/* 130 */     return "#NODESET";
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
/*     */   public double getNumberFromNode(int n) {
/* 142 */     XMLString xstr = this.m_dtmMgr.getDTM(n).getStringValue(n);
/* 143 */     return xstr.toDouble();
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
/*     */   public double num() {
/* 155 */     int node = item(0);
/* 156 */     return (node != -1) ? getNumberFromNode(node) : Double.NaN;
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
/*     */   public double numWithSideEffects() {
/* 168 */     int node = nextNode();
/*     */     
/* 170 */     return (node != -1) ? getNumberFromNode(node) : Double.NaN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bool() {
/* 181 */     return (item(0) != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean boolWithSideEffects() {
/* 192 */     return (nextNode() != -1);
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
/*     */   public XMLString getStringFromNode(int n) {
/* 207 */     if (-1 != n)
/*     */     {
/* 209 */       return this.m_dtmMgr.getDTM(n).getStringValue(n);
/*     */     }
/*     */ 
/*     */     
/* 213 */     return XString.EMPTYSTRING;
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
/*     */   public void dispatchCharactersEvents(ContentHandler ch) throws SAXException {
/* 231 */     int node = item(0);
/*     */     
/* 233 */     if (node != -1)
/*     */     {
/* 235 */       this.m_dtmMgr.getDTM(node).dispatchCharactersEvents(node, ch, false);
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
/*     */   public XMLString xstr() {
/* 247 */     int node = item(0);
/* 248 */     return (node != -1) ? getStringFromNode(node) : XString.EMPTYSTRING;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToFsb(FastStringBuffer fsb) {
/* 258 */     XString xstring = (XString)xstr();
/* 259 */     xstring.appendToFsb(fsb);
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
/*     */   public String str() {
/* 271 */     int node = item(0);
/* 272 */     return (node != -1) ? getStringFromNode(node).toString() : "";
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
/* 283 */     if (null == ((XObject)this).m_obj) {
/* 284 */       return this;
/*     */     }
/* 286 */     return ((XObject)this).m_obj;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeIterator nodeset() throws TransformerException {
/* 326 */     return (NodeIterator)new DTMNodeIterator(iter());
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
/*     */   public NodeList nodelist() throws TransformerException {
/* 338 */     DTMNodeList nodelist = new DTMNodeList((DTMIterator)this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     XNodeSet clone = (XNodeSet)nodelist.getDTMIterator();
/* 344 */     SetVector(clone.getVector());
/* 345 */     return (NodeList)nodelist;
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
/*     */   public DTMIterator iterRaw() {
/* 365 */     return (DTMIterator)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void release(DTMIterator iter) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMIterator iter() {
/*     */     
/* 381 */     try { if (hasCache()) {
/* 382 */         return cloneWithReset();
/*     */       }
/* 384 */       return (DTMIterator)this; } catch (CloneNotSupportedException cnse)
/*     */     
/*     */     { 
/*     */       
/* 388 */       throw new RuntimeException(cnse.getMessage()); }
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
/*     */   public XObject getFresh() {
/*     */     
/* 401 */     try { if (hasCache()) {
/* 402 */         return (XObject)cloneWithReset();
/*     */       }
/* 404 */       return (XObject)this; } catch (CloneNotSupportedException cnse)
/*     */     
/*     */     { 
/*     */       
/* 408 */       throw new RuntimeException(cnse.getMessage()); }
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
/*     */   public NodeSetDTM mutableNodeset() {
/*     */     NodeSetDTM nodeSetDTM;
/* 421 */     if (((XObject)this).m_obj instanceof NodeSetDTM) {
/*     */       
/* 423 */       nodeSetDTM = (NodeSetDTM)((XObject)this).m_obj;
/*     */     }
/*     */     else {
/*     */       
/* 427 */       nodeSetDTM = new NodeSetDTM(iter());
/* 428 */       ((XObject)this).m_obj = nodeSetDTM;
/* 429 */       setCurrentPos(0);
/*     */     } 
/*     */     
/* 432 */     return nodeSetDTM;
/*     */   }
/*     */ 
/*     */   
/* 436 */   static LessThanComparator S_LT = new LessThanComparator();
/*     */ 
/*     */   
/* 439 */   static LessThanOrEqualComparator S_LTE = new LessThanOrEqualComparator();
/*     */ 
/*     */   
/* 442 */   static GreaterThanComparator S_GT = new GreaterThanComparator();
/*     */ 
/*     */   
/* 445 */   static GreaterThanOrEqualComparator S_GTE = new GreaterThanOrEqualComparator();
/*     */ 
/*     */ 
/*     */   
/* 449 */   static EqualComparator S_EQ = new EqualComparator();
/*     */ 
/*     */   
/* 452 */   static NotEqualComparator S_NEQ = new NotEqualComparator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compare(XObject obj2, Comparator comparator) throws TransformerException {
/* 468 */     boolean result = false;
/* 469 */     int type = obj2.getType();
/*     */     
/* 471 */     if (4 == type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       DTMIterator list1 = iterRaw();
/* 487 */       DTMIterator list2 = ((XNodeSet)obj2).iterRaw();
/*     */       
/* 489 */       Vector node2Strings = null;
/*     */       int node1;
/* 491 */       while (-1 != (node1 = list1.nextNode())) {
/*     */         
/* 493 */         XMLString s1 = getStringFromNode(node1);
/*     */         
/* 495 */         if (null == node2Strings) {
/*     */           int node2;
/*     */ 
/*     */           
/* 499 */           while (-1 != (node2 = list2.nextNode())) {
/*     */             
/* 501 */             XMLString s2 = getStringFromNode(node2);
/*     */             
/* 503 */             if (comparator.compareStrings(s1, s2)) {
/*     */               
/* 505 */               result = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 510 */             if (null == node2Strings) {
/* 511 */               node2Strings = new Vector();
/*     */             }
/* 513 */             node2Strings.addElement(s2);
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 518 */         int n = node2Strings.size();
/*     */         
/* 520 */         for (int i = 0; i < n; i++) {
/*     */           
/* 522 */           if (comparator.compareStrings(s1, node2Strings.elementAt(i))) {
/*     */             
/* 524 */             result = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 531 */       list1.reset();
/* 532 */       list2.reset();
/*     */     }
/* 534 */     else if (1 == type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       double num1 = bool() ? 1.0D : 0.0D;
/* 544 */       double num2 = obj2.num();
/*     */       
/* 546 */       result = comparator.compareNumbers(num1, num2);
/*     */     }
/* 548 */     else if (2 == type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 558 */       DTMIterator list1 = iterRaw();
/* 559 */       double num2 = obj2.num();
/*     */       
/*     */       int node;
/* 562 */       while (-1 != (node = list1.nextNode())) {
/*     */         
/* 564 */         double num1 = getNumberFromNode(node);
/*     */         
/* 566 */         if (comparator.compareNumbers(num1, num2)) {
/*     */           
/* 568 */           result = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 573 */       list1.reset();
/*     */     }
/* 575 */     else if (5 == type) {
/*     */       
/* 577 */       XMLString s2 = obj2.xstr();
/* 578 */       DTMIterator list1 = iterRaw();
/*     */       
/*     */       int node;
/* 581 */       while (-1 != (node = list1.nextNode())) {
/*     */         
/* 583 */         XMLString s1 = getStringFromNode(node);
/*     */         
/* 585 */         if (comparator.compareStrings(s1, s2)) {
/*     */           
/* 587 */           result = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 592 */       list1.reset();
/*     */     }
/* 594 */     else if (3 == type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 603 */       XMLString s2 = obj2.xstr();
/* 604 */       DTMIterator list1 = iterRaw();
/*     */       
/*     */       int node;
/* 607 */       while (-1 != (node = list1.nextNode())) {
/*     */         
/* 609 */         XMLString s1 = getStringFromNode(node);
/* 610 */         if (comparator.compareStrings(s1, s2)) {
/*     */           
/* 612 */           result = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 617 */       list1.reset();
/*     */     }
/*     */     else {
/*     */       
/* 621 */       result = comparator.compareNumbers(num(), obj2.num());
/*     */     } 
/*     */     
/* 624 */     return result;
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
/*     */   public boolean lessThan(XObject obj2) throws TransformerException {
/* 638 */     return compare(obj2, S_LT);
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
/*     */   public boolean lessThanOrEqual(XObject obj2) throws TransformerException {
/* 652 */     return compare(obj2, S_LTE);
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
/*     */   public boolean greaterThan(XObject obj2) throws TransformerException {
/* 666 */     return compare(obj2, S_GT);
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
/*     */   public boolean greaterThanOrEqual(XObject obj2) throws TransformerException {
/* 681 */     return compare(obj2, S_GTE);
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
/*     */   public boolean equals(XObject obj2) {
/*     */     
/* 697 */     try { return compare(obj2, S_EQ); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 701 */       throw new WrappedRuntimeException(te); }
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
/*     */   public boolean notEquals(XObject obj2) throws TransformerException {
/* 716 */     return compare(obj2, S_NEQ);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XNodeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */