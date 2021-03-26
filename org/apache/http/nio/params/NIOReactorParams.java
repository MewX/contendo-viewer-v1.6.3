/*     */ package org.apache.http.nio.params;
/*     */ 
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.util.Args;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public final class NIOReactorParams
/*     */   implements NIOReactorPNames
/*     */ {
/*     */   public static int getContentBufferSize(HttpParams params) {
/*  57 */     Args.notNull(params, "HTTP parameters");
/*  58 */     return params.getIntParameter("http.nio.content-buffer-size", 4096);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setContentBufferSize(HttpParams params, int size) {
/*  68 */     Args.notNull(params, "HTTP parameters");
/*  69 */     params.setIntParameter("http.nio.content-buffer-size", size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getSelectInterval(HttpParams params) {
/*  80 */     Args.notNull(params, "HTTP parameters");
/*  81 */     return params.getLongParameter("http.nio.select-interval", 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setSelectInterval(HttpParams params, long ms) {
/*  91 */     Args.notNull(params, "HTTP parameters");
/*  92 */     params.setLongParameter("http.nio.select-interval", ms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getGracePeriod(HttpParams params) {
/* 103 */     Args.notNull(params, "HTTP parameters");
/* 104 */     return params.getLongParameter("http.nio.grace-period", 500L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setGracePeriod(HttpParams params, long ms) {
/* 114 */     Args.notNull(params, "HTTP parameters");
/* 115 */     params.setLongParameter("http.nio.grace-period", ms);
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
/*     */   public static boolean getInterestOpsQueueing(HttpParams params) {
/* 128 */     Args.notNull(params, "HTTP parameters");
/* 129 */     return params.getBooleanParameter("http.nio.interest-ops-queueing", false);
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
/*     */   public static void setInterestOpsQueueing(HttpParams params, boolean interestOpsQueueing) {
/* 142 */     Args.notNull(params, "HTTP parameters");
/* 143 */     params.setBooleanParameter("http.nio.interest-ops-queueing", interestOpsQueueing);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/params/NIOReactorParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */