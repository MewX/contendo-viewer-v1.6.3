/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WalkingIteratorSorted
/*     */   extends WalkingIterator
/*     */ {
/*     */   protected boolean m_inNaturalOrderStatic = false;
/*     */   
/*     */   public WalkingIteratorSorted(PrefixResolver nscontext) {
/*  47 */     super(nscontext);
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
/*     */   WalkingIteratorSorted(Compiler compiler, int opPos, int analysis, boolean shouldLoadWalkers) throws TransformerException {
/*  69 */     super(compiler, opPos, analysis, shouldLoadWalkers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDocOrdered() {
/*  80 */     return this.m_inNaturalOrderStatic;
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
/*     */   boolean canBeWalkedInNaturalDocOrderStatic() {
/*  93 */     if (null != this.m_firstWalker) {
/*     */       
/*  95 */       AxesWalker walker = this.m_firstWalker;
/*  96 */       int prevAxis = -1;
/*  97 */       boolean prevIsSimpleDownAxis = true;
/*     */       
/*  99 */       for (int i = 0; null != walker; i++) {
/*     */         
/* 101 */         int axis = walker.getAxis();
/*     */         
/* 103 */         if (walker.isDocOrdered()) {
/*     */           
/* 105 */           boolean isSimpleDownAxis = (axis == 3 || axis == 13 || axis == 19);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 110 */           if (isSimpleDownAxis || axis == -1) {
/* 111 */             walker = walker.getNextWalker();
/*     */           } else {
/*     */             
/* 114 */             boolean isLastWalker = (null == walker.getNextWalker());
/* 115 */             if (isLastWalker)
/*     */             {
/* 117 */               if ((walker.isDocOrdered() && (axis == 4 || axis == 5 || axis == 17 || axis == 18)) || axis == 2)
/*     */               {
/*     */                 
/* 120 */                 return true; } 
/*     */             }
/* 122 */             return false;
/*     */           } 
/*     */         } else {
/*     */           
/* 126 */           return false;
/*     */         } 
/* 128 */       }  return true;
/*     */     } 
/* 130 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 195 */     super.fixupVariables(vars, globalsSize);
/*     */     
/* 197 */     int analysis = getAnalysisBits();
/* 198 */     if (WalkerFactory.isNaturalDocOrder(analysis)) {
/*     */       
/* 200 */       this.m_inNaturalOrderStatic = true;
/*     */     }
/*     */     else {
/*     */       
/* 204 */       this.m_inNaturalOrderStatic = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/WalkingIteratorSorted.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */