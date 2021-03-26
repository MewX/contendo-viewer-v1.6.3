/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemNumber;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CountersTable
/*     */   extends Hashtable
/*     */ {
/*     */   private transient NodeSetDTM m_newFound;
/*     */   
/*     */   Vector getCounters(ElemNumber numberElem) {
/*  58 */     Vector counters = (Vector)get(numberElem);
/*     */     
/*  60 */     return (null == counters) ? putElemNumber(numberElem) : counters;
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
/*     */   Vector putElemNumber(ElemNumber numberElem) {
/*  75 */     Vector counters = new Vector();
/*     */     
/*  77 */     put((K)numberElem, (V)counters);
/*     */     
/*  79 */     return counters;
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
/*     */   void appendBtoFList(NodeSetDTM flist, NodeSetDTM blist) {
/*  98 */     int n = blist.size();
/*     */     
/* 100 */     for (int i = n - 1; i >= 0; i--)
/*     */     {
/* 102 */       flist.addElement(blist.item(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   transient int m_countersMade = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countNode(XPathContext support, ElemNumber numberElem, int node) throws TransformerException {
/* 127 */     int count = 0;
/* 128 */     Vector counters = getCounters(numberElem);
/* 129 */     int nCounters = counters.size();
/*     */ 
/*     */ 
/*     */     
/* 133 */     int target = numberElem.getTargetNode(support, node);
/*     */     
/* 135 */     if (-1 != target) {
/*     */       
/* 137 */       for (int i = 0; i < nCounters; i++) {
/*     */         
/* 139 */         Counter counter1 = counters.elementAt(i);
/*     */         
/* 141 */         count = counter1.getPreviouslyCounted(support, target);
/*     */         
/* 143 */         if (count > 0) {
/* 144 */           return count;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       count = 0;
/* 154 */       if (this.m_newFound == null) {
/* 155 */         this.m_newFound = new NodeSetDTM(support.getDTMManager());
/*     */       }
/* 157 */       for (; -1 != target; 
/* 158 */         target = numberElem.getPreviousNode(support, target)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 164 */         if (0 != count)
/*     */         {
/* 166 */           for (int j = 0; j < nCounters; j++) {
/*     */             
/* 168 */             Counter counter1 = counters.elementAt(j);
/* 169 */             int cacheLen = counter1.m_countNodes.size();
/*     */             
/* 171 */             if (cacheLen > 0 && counter1.m_countNodes.elementAt(cacheLen - 1) == target) {
/*     */ 
/*     */ 
/*     */               
/* 175 */               count += cacheLen + counter1.m_countNodesStartCount;
/*     */               
/* 177 */               if (cacheLen > 0) {
/* 178 */                 appendBtoFList(counter1.m_countNodes, this.m_newFound);
/*     */               }
/* 180 */               this.m_newFound.removeAllElements();
/*     */               
/* 182 */               return count;
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/* 187 */         this.m_newFound.addElement(target);
/*     */         
/* 189 */         count++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 194 */       Counter counter = new Counter(numberElem, new NodeSetDTM(support.getDTMManager()));
/*     */       
/* 196 */       this.m_countersMade++;
/*     */       
/* 198 */       appendBtoFList(counter.m_countNodes, this.m_newFound);
/* 199 */       this.m_newFound.removeAllElements();
/* 200 */       counters.addElement(counter);
/*     */     } 
/*     */     
/* 203 */     return count;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/CountersTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */