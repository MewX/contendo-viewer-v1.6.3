/*     */ package org.apache.batik.transcoder;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TranscoderSupport
/*     */ {
/*  32 */   static final ErrorHandler defaultErrorHandler = new DefaultErrorHandler();
/*     */ 
/*     */   
/*  35 */   protected TranscodingHints hints = new TranscodingHints();
/*     */   
/*  37 */   protected ErrorHandler handler = defaultErrorHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TranscodingHints getTranscodingHints() {
/*  48 */     return new TranscodingHints(this.hints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTranscodingHint(TranscodingHints.Key key, Object value) {
/*  58 */     this.hints.put(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTranscodingHint(TranscodingHints.Key key) {
/*  66 */     this.hints.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranscodingHints(Map hints) {
/*  75 */     this.hints.putAll(hints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranscodingHints(TranscodingHints hints) {
/*  84 */     this.hints = hints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorHandler(ErrorHandler handler) {
/*  93 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorHandler getErrorHandler() {
/* 101 */     return this.handler;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/TranscoderSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */