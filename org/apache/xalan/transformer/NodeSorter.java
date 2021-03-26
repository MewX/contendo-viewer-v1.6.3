/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.text.CollationKey;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
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
/*     */ public class NodeSorter
/*     */ {
/*     */   XPathContext m_execContext;
/*     */   Vector m_keys;
/*     */   
/*     */   public NodeSorter(XPathContext p) {
/*  59 */     this.m_execContext = p;
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
/*     */   public void sort(DTMIterator v, Vector keys, XPathContext support) throws TransformerException {
/*  75 */     this.m_keys = keys;
/*     */ 
/*     */     
/*  78 */     int n = v.getLength();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     Vector nodes = new Vector();
/*     */     
/*  88 */     for (int i = 0; i < n; i++) {
/*     */       
/*  90 */       NodeCompareElem elem = new NodeCompareElem(this, v.item(i));
/*     */       
/*  92 */       nodes.addElement(elem);
/*     */     } 
/*     */     
/*  95 */     Vector scratchVector = new Vector();
/*     */     
/*  97 */     mergesort(nodes, scratchVector, 0, n - 1, support);
/*     */ 
/*     */     
/* 100 */     for (int j = 0; j < n; j++)
/*     */     {
/* 102 */       v.setItem(((NodeCompareElem)nodes.elementAt(j)).m_node, j);
/*     */     }
/* 104 */     v.setCurrentPos(0);
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
/*     */   int compare(NodeCompareElem n1, NodeCompareElem n2, int kIndex, XPathContext support) throws TransformerException {
/* 129 */     int result = 0;
/* 130 */     NodeSortKey k = this.m_keys.elementAt(kIndex);
/*     */     
/* 132 */     if (k.m_treatAsNumbers) {
/*     */       double d1;
/*     */       
/*     */       double d2;
/* 136 */       if (kIndex == 0) {
/*     */         
/* 138 */         d1 = ((Double)n1.m_key1Value).doubleValue();
/* 139 */         d2 = ((Double)n2.m_key1Value).doubleValue();
/*     */       }
/* 141 */       else if (kIndex == 1) {
/*     */         
/* 143 */         d1 = ((Double)n1.m_key2Value).doubleValue();
/* 144 */         d2 = ((Double)n2.m_key2Value).doubleValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 157 */         XObject r1 = k.m_selectPat.execute(this.m_execContext, n1.m_node, k.m_namespaceContext);
/*     */         
/* 159 */         XObject r2 = k.m_selectPat.execute(this.m_execContext, n2.m_node, k.m_namespaceContext);
/*     */         
/* 161 */         d1 = r1.num();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 166 */         d2 = r2.num();
/*     */       } 
/*     */ 
/*     */       
/* 170 */       if (d1 == d2 && kIndex + 1 < this.m_keys.size()) {
/*     */         
/* 172 */         result = compare(n1, n2, kIndex + 1, support);
/*     */       } else {
/*     */         double d;
/*     */ 
/*     */         
/* 177 */         if (Double.isNaN(d1)) {
/*     */           
/* 179 */           if (Double.isNaN(d2)) {
/* 180 */             d = 0.0D;
/*     */           } else {
/* 182 */             d = -1.0D;
/*     */           } 
/* 184 */         } else if (Double.isNaN(d2)) {
/* 185 */           d = 1.0D;
/*     */         } else {
/* 187 */           d = d1 - d2;
/*     */         } 
/*     */         
/* 190 */         result = (d < 0.0D) ? (k.m_descending ? 1 : -1) : ((d > 0.0D) ? (k.m_descending ? -1 : 1) : 0);
/*     */       } 
/*     */     } else {
/*     */       CollationKey collationKey1, collationKey2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 199 */       if (kIndex == 0) {
/*     */         
/* 201 */         collationKey1 = (CollationKey)n1.m_key1Value;
/* 202 */         collationKey2 = (CollationKey)n2.m_key1Value;
/*     */       }
/* 204 */       else if (kIndex == 1) {
/*     */         
/* 206 */         collationKey1 = (CollationKey)n1.m_key2Value;
/* 207 */         collationKey2 = (CollationKey)n2.m_key2Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 220 */         XObject r1 = k.m_selectPat.execute(this.m_execContext, n1.m_node, k.m_namespaceContext);
/*     */         
/* 222 */         XObject r2 = k.m_selectPat.execute(this.m_execContext, n2.m_node, k.m_namespaceContext);
/*     */ 
/*     */         
/* 225 */         collationKey1 = k.m_col.getCollationKey(r1.str());
/* 226 */         collationKey2 = k.m_col.getCollationKey(r2.str());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 231 */       result = collationKey1.compareTo(collationKey2);
/*     */ 
/*     */       
/* 234 */       if (k.m_caseOrderUpper) {
/*     */         
/* 236 */         String tempN1 = collationKey1.getSourceString().toLowerCase();
/* 237 */         String tempN2 = collationKey2.getSourceString().toLowerCase();
/*     */         
/* 239 */         if (tempN1.equals(tempN2))
/*     */         {
/*     */ 
/*     */           
/* 243 */           result = (result == 0) ? 0 : -result;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 248 */       if (k.m_descending)
/*     */       {
/* 250 */         result = -result;
/*     */       }
/*     */     } 
/*     */     
/* 254 */     if (0 == result)
/*     */     {
/* 256 */       if (kIndex + 1 < this.m_keys.size())
/*     */       {
/* 258 */         result = compare(n1, n2, kIndex + 1, support);
/*     */       }
/*     */     }
/*     */     
/* 262 */     if (0 == result) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       DTM dtm = support.getDTM(n1.m_node);
/* 270 */       result = dtm.isNodeAfter(n1.m_node, n2.m_node) ? -1 : 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 275 */     return result;
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
/*     */   void mergesort(Vector a, Vector b, int l, int r, XPathContext support) throws TransformerException {
/* 297 */     if (r - l > 0) {
/*     */       
/* 299 */       int m = (r + l) / 2;
/*     */       
/* 301 */       mergesort(a, b, l, m, support);
/* 302 */       mergesort(a, b, m + 1, r, support);
/*     */       
/*     */       int i;
/*     */       
/* 306 */       for (i = m; i >= l; i--) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 311 */         if (i >= b.size()) {
/* 312 */           b.insertElementAt(a.elementAt(i), i);
/*     */         } else {
/* 314 */           b.setElementAt(a.elementAt(i), i);
/*     */         } 
/*     */       } 
/* 317 */       i = l;
/*     */       int j;
/* 319 */       for (j = m + 1; j <= r; j++) {
/*     */ 
/*     */ 
/*     */         
/* 323 */         if (r + m + 1 - j >= b.size()) {
/* 324 */           b.insertElementAt(a.elementAt(j), r + m + 1 - j);
/*     */         } else {
/* 326 */           b.setElementAt(a.elementAt(j), r + m + 1 - j);
/*     */         } 
/*     */       } 
/* 329 */       j = r;
/*     */ 
/*     */ 
/*     */       
/* 333 */       for (int k = l; k <= r; k++) {
/*     */         int n;
/*     */ 
/*     */         
/* 337 */         if (i == j) {
/* 338 */           n = -1;
/*     */         } else {
/* 340 */           n = compare(b.elementAt(i), b.elementAt(j), 0, support);
/*     */         } 
/*     */         
/* 343 */         if (n < 0) {
/*     */ 
/*     */ 
/*     */           
/* 347 */           a.setElementAt(b.elementAt(i), k);
/*     */           
/* 349 */           i++;
/*     */         }
/* 351 */         else if (n > 0) {
/*     */ 
/*     */ 
/*     */           
/* 355 */           a.setElementAt(b.elementAt(j), k);
/*     */           
/* 357 */           j--;
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class NodeCompareElem
/*     */   {
/*     */     int m_node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int maxkey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Object m_key1Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Object m_key2Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final NodeSorter this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     NodeCompareElem(NodeSorter this$0, int node) throws TransformerException {
/* 490 */       this.this$0 = this$0; this.maxkey = 2;
/* 491 */       this.m_node = node;
/*     */       
/* 493 */       if (!this$0.m_keys.isEmpty()) {
/*     */         
/* 495 */         NodeSortKey k1 = this$0.m_keys.elementAt(0);
/* 496 */         XObject r = k1.m_selectPat.execute(this$0.m_execContext, node, k1.m_namespaceContext);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 501 */         if (k1.m_treatAsNumbers) {
/*     */           
/* 503 */           double d = r.num();
/*     */ 
/*     */           
/* 506 */           this.m_key1Value = new Double(d);
/*     */         }
/*     */         else {
/*     */           
/* 510 */           this.m_key1Value = k1.m_col.getCollationKey(r.str());
/*     */         } 
/*     */         
/* 513 */         if (r.getType() == 4) {
/*     */ 
/*     */           
/* 516 */           DTMIterator ni = ((XNodeSet)r).iterRaw();
/* 517 */           int current = ni.getCurrentNode();
/* 518 */           if (-1 == current) {
/* 519 */             current = ni.nextNode();
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 527 */         if (this$0.m_keys.size() > 1) {
/*     */           
/* 529 */           NodeSortKey k2 = this$0.m_keys.elementAt(1);
/*     */           
/* 531 */           XObject r2 = k2.m_selectPat.execute(this$0.m_execContext, node, k2.m_namespaceContext);
/*     */ 
/*     */           
/* 534 */           if (k2.m_treatAsNumbers) {
/* 535 */             double d = r2.num();
/* 536 */             this.m_key2Value = new Double(d);
/*     */           } else {
/* 538 */             this.m_key2Value = k2.m_col.getCollationKey(r2.str());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/NodeSorter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */