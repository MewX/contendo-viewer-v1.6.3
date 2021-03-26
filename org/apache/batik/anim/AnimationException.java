/*    */ package org.apache.batik.anim;
/*    */ 
/*    */ import org.apache.batik.anim.timing.TimedElement;
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
/*    */ public class AnimationException
/*    */   extends RuntimeException
/*    */ {
/*    */   protected TimedElement e;
/*    */   protected String code;
/*    */   protected Object[] params;
/*    */   protected String message;
/*    */   
/*    */   public AnimationException(TimedElement e, String code, Object[] params) {
/* 58 */     this.e = e;
/* 59 */     this.code = code;
/* 60 */     this.params = params;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TimedElement getElement() {
/* 67 */     return this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 74 */     return this.code;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object[] getParams() {
/* 81 */     return this.params;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 88 */     return TimedElement.formatMessage(this.code, this.params);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/AnimationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */