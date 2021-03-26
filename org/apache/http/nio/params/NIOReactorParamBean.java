/*    */ package org.apache.http.nio.params;
/*    */ 
/*    */ import org.apache.http.params.HttpAbstractParamBean;
/*    */ import org.apache.http.params.HttpParams;
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
/*    */ @Deprecated
/*    */ public class NIOReactorParamBean
/*    */   extends HttpAbstractParamBean
/*    */ {
/*    */   public NIOReactorParamBean(HttpParams params) {
/* 42 */     super(params);
/*    */   }
/*    */   
/*    */   public void setContentBufferSize(int contentBufferSize) {
/* 46 */     NIOReactorParams.setContentBufferSize(this.params, contentBufferSize);
/*    */   }
/*    */   
/*    */   public void setSelectInterval(long selectInterval) {
/* 50 */     NIOReactorParams.setSelectInterval(this.params, selectInterval);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/params/NIOReactorParamBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */