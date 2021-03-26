/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IteratorPool
/*     */   implements Serializable
/*     */ {
/*     */   private final DTMIterator m_orig;
/*     */   private final Vector m_freeStack;
/*     */   
/*     */   public IteratorPool(DTMIterator original) {
/*  48 */     this.m_orig = original;
/*  49 */     this.m_freeStack = new Vector();
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
/*     */   public synchronized DTMIterator getInstanceOrThrow() throws CloneNotSupportedException {
/*  61 */     if (this.m_freeStack.isEmpty())
/*     */     {
/*     */ 
/*     */       
/*  65 */       return (DTMIterator)this.m_orig.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  70 */     DTMIterator result = this.m_freeStack.lastElement();
/*     */     
/*  72 */     this.m_freeStack.setSize(this.m_freeStack.size() - 1);
/*     */     
/*  74 */     return result;
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
/*     */   public synchronized DTMIterator getInstance() {
/*  86 */     if (this.m_freeStack.isEmpty()) {
/*     */ 
/*     */       
/*     */       try { 
/*     */ 
/*     */         
/*  92 */         return (DTMIterator)this.m_orig.clone(); } catch (Exception ex)
/*     */       
/*     */       { 
/*     */         
/*  96 */         throw new WrappedRuntimeException(ex); }
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     DTMIterator result = this.m_freeStack.lastElement();
/*     */     
/* 104 */     this.m_freeStack.setSize(this.m_freeStack.size() - 1);
/*     */     
/* 106 */     return result;
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
/*     */   public synchronized void freeInstance(DTMIterator obj) {
/* 118 */     this.m_freeStack.addElement(obj);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/IteratorPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */