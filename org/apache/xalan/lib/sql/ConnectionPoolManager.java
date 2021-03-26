/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnectionPoolManager
/*     */ {
/*  34 */   static Hashtable m_poolTable = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean m_isInit = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionPoolManager() {
/*  43 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void init() {
/*  53 */     if (m_isInit == true) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  59 */     m_poolTable = new Hashtable();
/*     */     
/*  61 */     m_isInit = true;
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
/*     */   public synchronized void registerPool(String name, ConnectionPool pool) {
/*  79 */     if (m_poolTable.containsKey(name))
/*     */     {
/*  81 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_POOL_EXISTS", null));
/*     */     }
/*     */     
/*  84 */     m_poolTable.put(name, pool);
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
/*     */   public synchronized void removePool(String name) {
/*  96 */     ConnectionPool pool = getPool(name);
/*     */     
/*  98 */     if (null != pool) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       pool.setPoolEnabled(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       if (!pool.hasActiveConnections()) m_poolTable.remove(name);
/*     */     
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
/*     */   public synchronized ConnectionPool getPool(String name) {
/* 127 */     return (ConnectionPool)m_poolTable.get(name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/ConnectionPoolManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */