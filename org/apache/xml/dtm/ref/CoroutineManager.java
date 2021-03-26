/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CoroutineManager
/*     */ {
/* 111 */   BitSet m_activeIDs = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int m_unreasonableId = 1024;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   Object m_yield = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int NOBODY = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int ANYBODY = -1;
/*     */ 
/*     */   
/* 158 */   int m_nextCoroutine = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int co_joinCoroutineSet(int coroutineID) {
/* 184 */     if (coroutineID >= 0) {
/*     */       
/* 186 */       if (coroutineID >= 1024 || this.m_activeIDs.get(coroutineID)) {
/* 187 */         return -1;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 193 */       coroutineID = 0;
/* 194 */       while (coroutineID < 1024) {
/*     */         
/* 196 */         if (this.m_activeIDs.get(coroutineID)) {
/* 197 */           coroutineID++; continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 201 */       if (coroutineID >= 1024) {
/* 202 */         return -1;
/*     */       }
/*     */     } 
/* 205 */     this.m_activeIDs.set(coroutineID);
/* 206 */     return coroutineID;
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
/*     */   public synchronized Object co_entry_pause(int thisCoroutine) throws NoSuchMethodException {
/* 226 */     if (!this.m_activeIDs.get(thisCoroutine)) {
/* 227 */       throw new NoSuchMethodException();
/*     */     }
/* 229 */     while (this.m_nextCoroutine != thisCoroutine) {
/*     */ 
/*     */ 
/*     */       
/* 233 */       try { wait(); } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     return this.m_yield;
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
/*     */   public synchronized Object co_resume(Object arg_object, int thisCoroutine, int toCoroutine) throws NoSuchMethodException {
/* 261 */     if (!this.m_activeIDs.get(toCoroutine)) {
/* 262 */       throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_NOT_AVAIL", new Object[] { Integer.toString(toCoroutine) }));
/*     */     }
/*     */ 
/*     */     
/* 266 */     this.m_yield = arg_object;
/* 267 */     this.m_nextCoroutine = toCoroutine;
/*     */     
/* 269 */     notify();
/* 270 */     while (this.m_nextCoroutine != thisCoroutine || this.m_nextCoroutine == -1 || this.m_nextCoroutine == -1) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/* 275 */         wait(); } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     if (this.m_nextCoroutine == -1) {
/*     */ 
/*     */       
/* 287 */       co_exit(thisCoroutine);
/*     */ 
/*     */       
/* 290 */       throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_CO_EXIT", null));
/*     */     } 
/*     */     
/* 293 */     return this.m_yield;
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
/*     */   public synchronized void co_exit(int thisCoroutine) {
/* 311 */     this.m_activeIDs.clear(thisCoroutine);
/* 312 */     this.m_nextCoroutine = -1;
/* 313 */     notify();
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
/*     */   public synchronized void co_exit_to(Object arg_object, int thisCoroutine, int toCoroutine) throws NoSuchMethodException {
/* 330 */     if (!this.m_activeIDs.get(toCoroutine)) {
/* 331 */       throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_NOT_AVAIL", new Object[] { Integer.toString(toCoroutine) }));
/*     */     }
/*     */ 
/*     */     
/* 335 */     this.m_yield = arg_object;
/* 336 */     this.m_nextCoroutine = toCoroutine;
/*     */     
/* 338 */     this.m_activeIDs.clear(thisCoroutine);
/*     */     
/* 340 */     notify();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/CoroutineManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */