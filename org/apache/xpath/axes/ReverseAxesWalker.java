/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
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
/*     */ public class ReverseAxesWalker
/*     */   extends AxesWalker
/*     */ {
/*     */   protected DTMAxisIterator m_iterator;
/*     */   
/*     */   ReverseAxesWalker(LocPathIterator locPathIterator, int axis) {
/*  39 */     super(locPathIterator, axis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoot(int root) {
/*  50 */     super.setRoot(root);
/*  51 */     this.m_iterator = getDTM(root).getAxisIterator(this.m_axis);
/*  52 */     this.m_iterator.setStartNode(root);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/*  62 */     this.m_iterator = null;
/*  63 */     super.detach();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNextNode() {
/*  73 */     if (this.m_foundLast) {
/*  74 */       return -1;
/*     */     }
/*  76 */     int next = this.m_iterator.next();
/*     */     
/*  78 */     if (this.m_isFresh) {
/*  79 */       this.m_isFresh = false;
/*     */     }
/*  81 */     if (-1 == next) {
/*  82 */       this.m_foundLast = true;
/*     */     }
/*  84 */     return next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReverseAxes() {
/*  95 */     return true;
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
/*     */   protected int getProximityPosition(int predicateIndex) {
/* 124 */     if (predicateIndex < 0) {
/* 125 */       return -1;
/*     */     }
/* 127 */     int count = this.m_proximityPositions[predicateIndex];
/*     */     
/* 129 */     if (count <= 0) {
/*     */       
/* 131 */       AxesWalker savedWalker = wi().getLastUsedWalker();
/*     */ 
/*     */ 
/*     */       
/* 135 */       try { ReverseAxesWalker clone = (ReverseAxesWalker)clone();
/*     */         
/* 137 */         clone.setRoot(getRoot());
/*     */         
/* 139 */         clone.setPredicateCount(predicateIndex);
/*     */         
/* 141 */         clone.setPrevWalker(null);
/* 142 */         clone.setNextWalker(null);
/* 143 */         wi().setLastUsedWalker(clone);
/*     */ 
/*     */         
/* 146 */         count++;
/*     */         
/*     */         int next;
/* 149 */         while (-1 != (next = clone.nextNode()))
/*     */         {
/* 151 */           count++;
/*     */         }
/*     */         
/* 154 */         this.m_proximityPositions[predicateIndex] = count; } catch (CloneNotSupportedException cnse)
/*     */       
/*     */       { 
/*     */         
/*     */          }
/*     */       
/*     */       finally
/*     */       
/*     */       { 
/* 163 */         wi().setLastUsedWalker(savedWalker); }
/*     */     
/*     */     } 
/*     */     
/* 167 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void countProximityPosition(int i) {
/* 177 */     if (i < this.m_proximityPositions.length) {
/* 178 */       this.m_proximityPositions[i] = this.m_proximityPositions[i] - 1;
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
/*     */   public int getLastPos(XPathContext xctxt) {
/* 193 */     int count = 0;
/* 194 */     AxesWalker savedWalker = wi().getLastUsedWalker();
/*     */ 
/*     */     
/*     */     try {
/* 198 */       ReverseAxesWalker clone = (ReverseAxesWalker)clone();
/*     */       
/* 200 */       clone.setRoot(getRoot());
/*     */       
/* 202 */       clone.setPredicateCount(getPredicateCount() - 1);
/*     */       
/* 204 */       clone.setPrevWalker(null);
/* 205 */       clone.setNextWalker(null);
/* 206 */       wi().setLastUsedWalker(clone);
/*     */ 
/*     */       
/*     */       int next;
/*     */ 
/*     */       
/* 212 */       while (-1 != (next = clone.nextNode()))
/*     */       {
/* 214 */         count++;
/*     */       
/*     */       }
/*     */     }
/*     */     catch (CloneNotSupportedException cnse) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 224 */       wi().setLastUsedWalker(savedWalker);
/*     */     } 
/*     */     
/* 227 */     return count;
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
/*     */   public boolean isDocOrdered() {
/* 239 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/ReverseAxesWalker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */