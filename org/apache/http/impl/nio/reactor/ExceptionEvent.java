/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
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
/*    */ @Contract(threading = ThreadingBehavior.IMMUTABLE)
/*    */ public class ExceptionEvent
/*    */ {
/*    */   private final Throwable ex;
/*    */   private final long time;
/*    */   
/*    */   public ExceptionEvent(Throwable ex, Date timestamp) {
/* 48 */     this.ex = ex;
/* 49 */     if (timestamp != null) {
/* 50 */       this.time = timestamp.getTime();
/*    */     } else {
/* 52 */       this.time = 0L;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ExceptionEvent(Exception ex) {
/* 57 */     this(ex, new Date());
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 61 */     return this.ex;
/*    */   }
/*    */   
/*    */   public Date getTimestamp() {
/* 65 */     return new Date(this.time);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     StringBuilder buffer = new StringBuilder();
/* 71 */     buffer.append(new Date(this.time));
/* 72 */     buffer.append(" ");
/* 73 */     buffer.append(this.ex);
/* 74 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/ExceptionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */