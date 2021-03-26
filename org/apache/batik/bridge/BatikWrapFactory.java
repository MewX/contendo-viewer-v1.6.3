/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.mozilla.javascript.Context;
/*    */ import org.mozilla.javascript.Scriptable;
/*    */ import org.mozilla.javascript.WrapFactory;
/*    */ import org.w3c.dom.events.EventTarget;
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
/*    */ class BatikWrapFactory
/*    */   extends WrapFactory
/*    */ {
/*    */   private RhinoInterpreter interpreter;
/*    */   
/*    */   public BatikWrapFactory(RhinoInterpreter interp) {
/* 39 */     this.interpreter = interp;
/* 40 */     setJavaPrimitiveWrap(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object wrap(Context ctx, Scriptable scope, Object obj, Class staticType) {
/* 45 */     if (obj instanceof EventTarget) {
/* 46 */       return this.interpreter.buildEventTargetWrapper((EventTarget)obj);
/*    */     }
/* 48 */     return super.wrap(ctx, scope, obj, staticType);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BatikWrapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */