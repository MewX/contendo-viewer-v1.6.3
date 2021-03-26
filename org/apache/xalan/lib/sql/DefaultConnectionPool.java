/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.Driver;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ public class DefaultConnectionPool
/*     */   implements ConnectionPool
/*     */ {
/*  43 */   private Driver m_Driver = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean DEBUG = false;
/*     */ 
/*     */ 
/*     */   
/*  51 */   private String m_driver = new String("");
/*     */ 
/*     */   
/*  54 */   private String m_url = new String("");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private int m_PoolMinSize = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private Properties m_ConnectionProtocol = new Properties();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private Vector m_pool = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_IsActive = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  94 */     return this.m_IsActive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDriver(String d) {
/* 104 */     this.m_driver = d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURL(String url) {
/* 114 */     this.m_url = url;
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
/*     */   public void freeUnused() {
/* 126 */     for (int x = 0; x < this.m_pool.size(); x++) {
/*     */ 
/*     */ 
/*     */       
/* 130 */       PooledConnection pcon = this.m_pool.elementAt(x);
/*     */ 
/*     */ 
/*     */       
/* 134 */       if (!pcon.inUse())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         pcon.close();
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
/*     */   public boolean hasActiveConnections() {
/* 153 */     return (this.m_pool.size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(String p) {
/* 164 */     this.m_ConnectionProtocol.put("password", p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(String u) {
/* 174 */     this.m_ConnectionProtocol.put("user", u);
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
/*     */   public void setProtocol(Properties p) {
/* 186 */     Enumeration e = p.keys();
/* 187 */     while (e.hasMoreElements()) {
/*     */       
/* 189 */       String key = e.nextElement();
/* 190 */       this.m_ConnectionProtocol.put(key, p.getProperty(key));
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
/*     */   public void setMinConnections(int n) {
/* 204 */     this.m_PoolMinSize = n;
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
/*     */   public boolean testConnection() {
/*     */     
/* 222 */     try { Connection conn = getConnection();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       if (conn == null) return false;
/*     */       
/* 236 */       releaseConnection(conn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       return true; } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       return false; }
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
/*     */   public synchronized Connection getConnection() throws IllegalArgumentException, SQLException {
/* 268 */     PooledConnection pcon = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (this.m_pool.size() < this.m_PoolMinSize) initializePool();
/*     */ 
/*     */     
/* 277 */     for (int x = 0; x < this.m_pool.size(); x++) {
/*     */ 
/*     */       
/* 280 */       pcon = this.m_pool.elementAt(x);
/*     */ 
/*     */       
/* 283 */       if (!pcon.inUse()) {
/*     */ 
/*     */         
/* 286 */         pcon.setInUse(true);
/*     */ 
/*     */         
/* 289 */         return pcon.getConnection();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     Connection con = createConnection();
/*     */ 
/*     */ 
/*     */     
/* 301 */     pcon = new PooledConnection(con);
/*     */ 
/*     */     
/* 304 */     pcon.setInUse(true);
/*     */ 
/*     */     
/* 307 */     this.m_pool.addElement(pcon);
/*     */ 
/*     */     
/* 310 */     return pcon.getConnection();
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
/*     */   public synchronized void releaseConnection(Connection con) throws SQLException {
/* 322 */     for (int x = 0; x < this.m_pool.size(); x++) {
/*     */ 
/*     */       
/* 325 */       PooledConnection pcon = this.m_pool.elementAt(x);
/*     */ 
/*     */ 
/*     */       
/* 329 */       if (pcon.getConnection() == con) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 336 */         if (!isEnabled()) {
/*     */           
/* 338 */           con.close();
/* 339 */           this.m_pool.removeElementAt(x);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         pcon.setInUse(false);
/*     */         break;
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
/*     */   public synchronized void releaseConnectionOnError(Connection con) throws SQLException {
/* 368 */     for (int x = 0; x < this.m_pool.size(); x++) {
/*     */ 
/*     */       
/* 371 */       PooledConnection pcon = this.m_pool.elementAt(x);
/*     */ 
/*     */ 
/*     */       
/* 375 */       if (pcon.getConnection() == con) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 382 */         con.close();
/* 383 */         this.m_pool.removeElementAt(x);
/*     */         break;
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
/*     */   private Connection createConnection() throws SQLException {
/* 400 */     Connection con = null;
/*     */ 
/*     */ 
/*     */     
/* 404 */     con = this.m_Driver.connect(this.m_url, this.m_ConnectionProtocol);
/*     */     
/* 406 */     return con;
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
/*     */   public synchronized void initializePool() throws IllegalArgumentException, SQLException {
/* 419 */     if (this.m_driver == null)
/*     */     {
/* 421 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_NO_DRIVER_NAME_SPECIFIED", null));
/*     */     }
/*     */ 
/*     */     
/* 425 */     if (this.m_url == null)
/*     */     {
/* 427 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_NO_URL_SPECIFIED", null));
/*     */     }
/*     */ 
/*     */     
/* 431 */     if (this.m_PoolMinSize < 1)
/*     */     {
/* 433 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_POOLSIZE_LESS_THAN_ONE", null));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 444 */     try { this.m_Driver = (Driver)ObjectFactory.newInstance(this.m_driver, ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 450 */       DriverManager.registerDriver(this.m_Driver); } catch (ConfigurationError e)
/*     */     
/*     */     { 
/*     */       
/* 454 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_DRIVER_NAME", null)); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 459 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_DRIVER_NAME", null)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (!this.m_IsActive) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     do {
/* 470 */       Connection con = createConnection();
/*     */       
/* 472 */       if (con == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 477 */       PooledConnection pcon = new PooledConnection(con);
/*     */ 
/*     */       
/* 480 */       addConnection(pcon);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 485 */     while (this.m_pool.size() < this.m_PoolMinSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addConnection(PooledConnection value) {
/* 496 */     this.m_pool.addElement(value);
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
/*     */   protected void finalize() throws Throwable {
/* 513 */     for (int x = 0; x < this.m_pool.size(); x++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 521 */       PooledConnection pcon = this.m_pool.elementAt(x);
/*     */ 
/*     */ 
/*     */       
/* 525 */       if (!pcon.inUse()) { pcon.close(); }
/*     */       else
/*     */       
/*     */       { 
/*     */         
/*     */         try { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 537 */           Thread.sleep(30000L);
/* 538 */           pcon.close(); } catch (InterruptedException interruptedException) {} }
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 552 */     super.finalize();
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
/*     */   public void setPoolEnabled(boolean flag) {
/* 570 */     this.m_IsActive = flag;
/* 571 */     if (!flag)
/* 572 */       freeUnused(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/DefaultConnectionPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */