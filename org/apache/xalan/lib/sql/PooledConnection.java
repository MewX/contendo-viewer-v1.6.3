/*    */ package org.apache.xalan.lib.sql;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PooledConnection
/*    */ {
/* 32 */   private Connection connection = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean inuse = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PooledConnection(Connection value) {
/* 45 */     if (value != null) this.connection = value;
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Connection getConnection() {
/* 55 */     return this.connection;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setInUse(boolean value) {
/* 66 */     this.inuse = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean inUse() {
/* 73 */     return this.inuse;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     
/* 83 */     try { this.connection.close(); } catch (SQLException sqle)
/*    */     
/*    */     { 
/*    */       
/* 87 */       System.err.println(sqle.getMessage()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/PooledConnection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */