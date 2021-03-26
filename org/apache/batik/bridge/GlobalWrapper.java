/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.dom.svg12.SVGGlobal;
/*    */ import org.mozilla.javascript.Context;
/*    */ import org.mozilla.javascript.Function;
/*    */ import org.mozilla.javascript.NativeJavaObject;
/*    */ import org.mozilla.javascript.Scriptable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalWrapper
/*    */   extends WindowWrapper
/*    */ {
/*    */   public GlobalWrapper(Context context) {
/* 43 */     super(context);
/* 44 */     String[] names = { "startMouseCapture", "stopMouseCapture" };
/* 45 */     defineFunctionProperties(names, GlobalWrapper.class, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 50 */     return "SVGGlobal";
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     return "[object SVGGlobal]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void startMouseCapture(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 64 */     int len = args.length;
/* 65 */     GlobalWrapper gw = (GlobalWrapper)thisObj;
/* 66 */     SVGGlobal global = (SVGGlobal)gw.window;
/* 67 */     if (len >= 3) {
/* 68 */       EventTarget et = null;
/* 69 */       if (args[0] instanceof NativeJavaObject) {
/* 70 */         Object o = ((NativeJavaObject)args[0]).unwrap();
/* 71 */         if (o instanceof EventTarget) {
/* 72 */           et = (EventTarget)o;
/*    */         }
/*    */       } 
/* 75 */       if (et == null) {
/* 76 */         throw Context.reportRuntimeError("First argument to startMouseCapture must be an EventTarget");
/*    */       }
/* 78 */       boolean sendAll = Context.toBoolean(args[1]);
/* 79 */       boolean autoRelease = Context.toBoolean(args[2]);
/* 80 */       global.startMouseCapture(et, sendAll, autoRelease);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void stopMouseCapture(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 91 */     GlobalWrapper gw = (GlobalWrapper)thisObj;
/* 92 */     SVGGlobal global = (SVGGlobal)gw.window;
/* 93 */     global.stopMouseCapture();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/GlobalWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */