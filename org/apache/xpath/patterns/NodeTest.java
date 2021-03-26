/*     */ package org.apache.xpath.patterns;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XNumber;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeTest
/*     */   extends Expression
/*     */ {
/*     */   public static final String WILD = "*";
/*     */   public static final String SUPPORTS_PRE_STRIPPING = "http://xml.apache.org/xpath/features/whitespace-pre-stripping";
/*     */   protected int m_whatToShow;
/*     */   public static final int SHOW_BYFUNCTION = 65536;
/*     */   String m_namespace;
/*     */   protected String m_name;
/*     */   XNumber m_score;
/*     */   
/*     */   public int getWhatToShow() {
/*  73 */     return this.m_whatToShow;
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
/*     */   public void setWhatToShow(int what) {
/*  85 */     this.m_whatToShow = what;
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
/*     */   public String getNamespace() {
/* 101 */     return this.m_namespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNamespace(String ns) {
/* 111 */     this.m_namespace = ns;
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
/*     */   public String getLocalName() {
/* 127 */     return (null == this.m_name) ? "" : this.m_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocalName(String name) {
/* 137 */     this.m_name = name;
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
/* 155 */   public static final XNumber SCORE_NODETEST = new XNumber(-0.5D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public static final XNumber SCORE_NSWILD = new XNumber(-0.25D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static final XNumber SCORE_QNAME = new XNumber(0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public static final XNumber SCORE_OTHER = new XNumber(0.5D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public static final XNumber SCORE_NONE = new XNumber(Double.NEGATIVE_INFINITY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isTotallyWild;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeTest(int whatToShow, String namespace, String name) {
/* 198 */     initNodeTest(whatToShow, namespace, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeTest(int whatToShow) {
/* 209 */     initNodeTest(whatToShow);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 217 */     if (!isSameClass(expr)) {
/* 218 */       return false;
/*     */     }
/* 220 */     NodeTest nt = (NodeTest)expr;
/*     */     
/* 222 */     if (null != nt.m_name) {
/*     */       
/* 224 */       if (null == this.m_name)
/* 225 */         return false; 
/* 226 */       if (!nt.m_name.equals(this.m_name)) {
/* 227 */         return false;
/*     */       }
/* 229 */     } else if (null != this.m_name) {
/* 230 */       return false;
/*     */     } 
/* 232 */     if (null != nt.m_namespace) {
/*     */       
/* 234 */       if (null == this.m_namespace)
/* 235 */         return false; 
/* 236 */       if (!nt.m_namespace.equals(this.m_namespace)) {
/* 237 */         return false;
/*     */       }
/* 239 */     } else if (null != this.m_namespace) {
/* 240 */       return false;
/*     */     } 
/* 242 */     if (this.m_whatToShow != nt.m_whatToShow) {
/* 243 */       return false;
/*     */     }
/* 245 */     if (this.m_isTotallyWild != nt.m_isTotallyWild) {
/* 246 */       return false;
/*     */     }
/* 248 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeTest() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initNodeTest(int whatToShow) {
/* 266 */     this.m_whatToShow = whatToShow;
/*     */     
/* 268 */     calcScore();
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
/*     */   public void initNodeTest(int whatToShow, String namespace, String name) {
/* 284 */     this.m_whatToShow = whatToShow;
/* 285 */     this.m_namespace = namespace;
/* 286 */     this.m_name = name;
/*     */     
/* 288 */     calcScore();
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
/*     */   public XNumber getStaticScore() {
/* 303 */     return this.m_score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStaticScore(XNumber score) {
/* 312 */     this.m_score = score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcScore() {
/* 321 */     if (this.m_namespace == null && this.m_name == null) {
/* 322 */       this.m_score = SCORE_NODETEST;
/* 323 */     } else if ((this.m_namespace == "*" || this.m_namespace == null) && this.m_name == "*") {
/*     */       
/* 325 */       this.m_score = SCORE_NODETEST;
/* 326 */     } else if (this.m_namespace != "*" && this.m_name == "*") {
/* 327 */       this.m_score = SCORE_NSWILD;
/*     */     } else {
/* 329 */       this.m_score = SCORE_QNAME;
/*     */     } 
/* 331 */     this.m_isTotallyWild = (this.m_namespace == null && this.m_name == "*");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDefaultScore() {
/* 342 */     return this.m_score.num();
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
/*     */   public static int getNodeTypeTest(int whatToShow) {
/* 359 */     if (0 != (whatToShow & 0x1)) {
/* 360 */       return 1;
/*     */     }
/* 362 */     if (0 != (whatToShow & 0x2)) {
/* 363 */       return 2;
/*     */     }
/* 365 */     if (0 != (whatToShow & 0x4)) {
/* 366 */       return 3;
/*     */     }
/* 368 */     if (0 != (whatToShow & 0x100)) {
/* 369 */       return 9;
/*     */     }
/* 371 */     if (0 != (whatToShow & 0x400)) {
/* 372 */       return 11;
/*     */     }
/* 374 */     if (0 != (whatToShow & 0x1000)) {
/* 375 */       return 13;
/*     */     }
/* 377 */     if (0 != (whatToShow & 0x80)) {
/* 378 */       return 8;
/*     */     }
/* 380 */     if (0 != (whatToShow & 0x40)) {
/* 381 */       return 7;
/*     */     }
/* 383 */     if (0 != (whatToShow & 0x200)) {
/* 384 */       return 10;
/*     */     }
/* 386 */     if (0 != (whatToShow & 0x20)) {
/* 387 */       return 6;
/*     */     }
/* 389 */     if (0 != (whatToShow & 0x10)) {
/* 390 */       return 5;
/*     */     }
/* 392 */     if (0 != (whatToShow & 0x800)) {
/* 393 */       return 12;
/*     */     }
/* 395 */     if (0 != (whatToShow & 0x8)) {
/* 396 */       return 4;
/*     */     }
/*     */     
/* 399 */     return 0;
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
/*     */   public static void debugWhatToShow(int whatToShow) {
/* 413 */     Vector v = new Vector();
/*     */     
/* 415 */     if (0 != (whatToShow & 0x2)) {
/* 416 */       v.addElement("SHOW_ATTRIBUTE");
/*     */     }
/* 418 */     if (0 != (whatToShow & 0x1000)) {
/* 419 */       v.addElement("SHOW_NAMESPACE");
/*     */     }
/* 421 */     if (0 != (whatToShow & 0x8)) {
/* 422 */       v.addElement("SHOW_CDATA_SECTION");
/*     */     }
/* 424 */     if (0 != (whatToShow & 0x80)) {
/* 425 */       v.addElement("SHOW_COMMENT");
/*     */     }
/* 427 */     if (0 != (whatToShow & 0x100)) {
/* 428 */       v.addElement("SHOW_DOCUMENT");
/*     */     }
/* 430 */     if (0 != (whatToShow & 0x400)) {
/* 431 */       v.addElement("SHOW_DOCUMENT_FRAGMENT");
/*     */     }
/* 433 */     if (0 != (whatToShow & 0x200)) {
/* 434 */       v.addElement("SHOW_DOCUMENT_TYPE");
/*     */     }
/* 436 */     if (0 != (whatToShow & 0x1)) {
/* 437 */       v.addElement("SHOW_ELEMENT");
/*     */     }
/* 439 */     if (0 != (whatToShow & 0x20)) {
/* 440 */       v.addElement("SHOW_ENTITY");
/*     */     }
/* 442 */     if (0 != (whatToShow & 0x10)) {
/* 443 */       v.addElement("SHOW_ENTITY_REFERENCE");
/*     */     }
/* 445 */     if (0 != (whatToShow & 0x800)) {
/* 446 */       v.addElement("SHOW_NOTATION");
/*     */     }
/* 448 */     if (0 != (whatToShow & 0x40)) {
/* 449 */       v.addElement("SHOW_PROCESSING_INSTRUCTION");
/*     */     }
/* 451 */     if (0 != (whatToShow & 0x4)) {
/* 452 */       v.addElement("SHOW_TEXT");
/*     */     }
/* 454 */     int n = v.size();
/*     */     
/* 456 */     for (int i = 0; i < n; i++) {
/*     */       
/* 458 */       if (i > 0) {
/* 459 */         System.out.print(" | ");
/*     */       }
/* 461 */       System.out.print(v.elementAt(i));
/*     */     } 
/*     */     
/* 464 */     if (0 == n) {
/* 465 */       System.out.print("empty whatToShow: " + whatToShow);
/*     */     }
/* 467 */     System.out.println();
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
/*     */   private static final boolean subPartMatch(String p, String t) {
/* 485 */     return (p == t || (null != p && (t == "*" || p.equals(t))));
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
/*     */   private static final boolean subPartMatchNS(String p, String t) {
/* 501 */     if (p != t) { if (null != p) if ((p.length() > 0) ? ((t == "*" || p.equals(t))) : ((null == t)));  return false; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt, int context) throws TransformerException {
/*     */     String ns;
/* 526 */     DTM dtm = xctxt.getDTM(context);
/* 527 */     short nodeType = dtm.getNodeType(context);
/*     */     
/* 529 */     if (this.m_whatToShow == -1) {
/* 530 */       return (XObject)this.m_score;
/*     */     }
/* 532 */     int nodeBit = this.m_whatToShow & 1 << nodeType - 1;
/*     */     
/* 534 */     switch (nodeBit) {
/*     */       
/*     */       case 256:
/*     */       case 1024:
/* 538 */         return (XObject)SCORE_OTHER;
/*     */       case 128:
/* 540 */         return (XObject)this.m_score;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 8:
/* 547 */         return (XObject)this.m_score;
/*     */       case 64:
/* 549 */         return subPartMatch(dtm.getNodeName(context), this.m_name) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4096:
/* 566 */         ns = dtm.getNodeValue(context);
/*     */         
/* 568 */         return subPartMatch(ns, this.m_name) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */ 
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 573 */         return (this.m_isTotallyWild || (subPartMatchNS(dtm.getNamespaceURI(context), this.m_namespace) && subPartMatch(dtm.getLocalName(context), this.m_name))) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */     } 
/*     */ 
/*     */     
/* 577 */     return (XObject)SCORE_NONE;
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
/*     */   public XObject execute(XPathContext xctxt, int context, DTM dtm, int expType) throws TransformerException {
/*     */     String ns;
/* 601 */     if (this.m_whatToShow == -1) {
/* 602 */       return (XObject)this.m_score;
/*     */     }
/* 604 */     int nodeBit = this.m_whatToShow & 1 << dtm.getNodeType(context) - 1;
/*     */ 
/*     */     
/* 607 */     switch (nodeBit) {
/*     */       
/*     */       case 256:
/*     */       case 1024:
/* 611 */         return (XObject)SCORE_OTHER;
/*     */       case 128:
/* 613 */         return (XObject)this.m_score;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 8:
/* 620 */         return (XObject)this.m_score;
/*     */       case 64:
/* 622 */         return subPartMatch(dtm.getNodeName(context), this.m_name) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4096:
/* 639 */         ns = dtm.getNodeValue(context);
/*     */         
/* 641 */         return subPartMatch(ns, this.m_name) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */ 
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 646 */         return (this.m_isTotallyWild || (subPartMatchNS(dtm.getNamespaceURI(context), this.m_namespace) && subPartMatch(dtm.getLocalName(context), this.m_name))) ? (XObject)this.m_score : (XObject)SCORE_NONE;
/*     */     } 
/*     */ 
/*     */     
/* 650 */     return (XObject)SCORE_NONE;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 670 */     return execute(xctxt, xctxt.getCurrentNode());
/*     */   }
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
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 686 */     assertion(false, "callVisitors should not be called for this object!!!");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/patterns/NodeTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */