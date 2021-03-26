/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.security.AccessControlContext;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import org.apache.batik.w3c.dom.Location;
/*     */ import org.mozilla.javascript.Context;
/*     */ import org.mozilla.javascript.Function;
/*     */ import org.mozilla.javascript.ImporterTopLevel;
/*     */ import org.mozilla.javascript.NativeObject;
/*     */ import org.mozilla.javascript.Scriptable;
/*     */ import org.mozilla.javascript.ScriptableObject;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class WindowWrapper
/*     */   extends ImporterTopLevel
/*     */ {
/*  48 */   private static final Object[] EMPTY_ARGUMENTS = new Object[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RhinoInterpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Window window;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WindowWrapper(Context context) {
/*  64 */     super(context);
/*  65 */     String[] names = { "setInterval", "setTimeout", "clearInterval", "clearTimeout", "parseXML", "printNode", "getURL", "postURL", "alert", "confirm", "prompt" };
/*     */ 
/*     */     
/*  68 */     defineFunctionProperties(names, WindowWrapper.class, 2);
/*     */     
/*  70 */     defineProperty("location", WindowWrapper.class, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClassName() {
/*  75 */     return "Window";
/*     */   }
/*     */   
/*     */   public String toString() {
/*  79 */     return "[object Window]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object setInterval(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/*  89 */     int len = args.length;
/*  90 */     WindowWrapper ww = (WindowWrapper)thisObj;
/*  91 */     Window window = ww.window;
/*     */     
/*  93 */     if (len < 2) {
/*  94 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/*  96 */     long to = ((Long)Context.jsToJava(args[1], long.class)).longValue();
/*  97 */     if (args[0] instanceof Function) {
/*  98 */       RhinoInterpreter interp = (RhinoInterpreter)window.getInterpreter();
/*     */ 
/*     */       
/* 101 */       FunctionWrapper fw = new FunctionWrapper(interp, (Function)args[0], EMPTY_ARGUMENTS);
/*     */       
/* 103 */       return Context.toObject(window.setInterval(fw, to), thisObj);
/*     */     } 
/* 105 */     String script = (String)Context.jsToJava(args[0], String.class);
/*     */     
/* 107 */     return Context.toObject(window.setInterval(script, to), thisObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object setTimeout(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 117 */     int len = args.length;
/* 118 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 119 */     Window window = ww.window;
/* 120 */     if (len < 2) {
/* 121 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/* 123 */     long to = ((Long)Context.jsToJava(args[1], long.class)).longValue();
/* 124 */     if (args[0] instanceof Function) {
/* 125 */       RhinoInterpreter interp = (RhinoInterpreter)window.getInterpreter();
/*     */ 
/*     */       
/* 128 */       FunctionWrapper fw = new FunctionWrapper(interp, (Function)args[0], EMPTY_ARGUMENTS);
/*     */       
/* 130 */       return Context.toObject(window.setTimeout(fw, to), thisObj);
/*     */     } 
/* 132 */     String script = (String)Context.jsToJava(args[0], String.class);
/*     */     
/* 134 */     return Context.toObject(window.setTimeout(script, to), thisObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearInterval(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 144 */     int len = args.length;
/* 145 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 146 */     Window window = ww.window;
/* 147 */     if (len >= 1) {
/* 148 */       window.clearInterval(Context.jsToJava(args[0], Object.class));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearTimeout(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 159 */     int len = args.length;
/* 160 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 161 */     Window window = ww.window;
/* 162 */     if (len >= 1) {
/* 163 */       window.clearTimeout(Context.jsToJava(args[0], Object.class));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object parseXML(Context cx, Scriptable thisObj, final Object[] args, Function funObj) {
/*     */     Object ret;
/* 174 */     int len = args.length;
/* 175 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 176 */     final Window window = ww.window;
/* 177 */     if (len < 2) {
/* 178 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/*     */     
/* 181 */     RhinoInterpreter interp = (RhinoInterpreter)window.getInterpreter();
/* 182 */     AccessControlContext acc = interp.getAccessControlContext();
/*     */     
/* 184 */     PrivilegedAction<?> pa = new PrivilegedAction() {
/*     */         public Object run() {
/* 186 */           return window.parseXML((String)Context.jsToJava(args[0], String.class), (Document)Context.jsToJava(args[1], Document.class));
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (acc != null) { ret = AccessController.doPrivileged(pa, acc); }
/* 197 */     else { ret = AccessController.doPrivileged(pa); }
/*     */     
/* 199 */     return Context.toObject(ret, thisObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object printNode(Context cx, Scriptable thisObj, final Object[] args, Function funObj) {
/* 209 */     if (args.length != 1) {
/* 210 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/*     */     
/* 213 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 214 */     final Window window = ww.window;
/*     */     
/* 216 */     AccessControlContext acc = ((RhinoInterpreter)window.getInterpreter()).getAccessControlContext();
/*     */ 
/*     */     
/* 219 */     Object ret = AccessController.doPrivileged(new PrivilegedAction() {
/*     */           public Object run() {
/* 221 */             return window.printNode((Node)Context.jsToJava(args[0], Node.class));
/*     */           }
/*     */         }acc);
/*     */     
/* 225 */     return Context.toString(ret);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getURL(Context cx, Scriptable thisObj, final Object[] args, Function funObj) {
/* 235 */     int len = args.length;
/* 236 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 237 */     final Window window = ww.window;
/* 238 */     if (len < 2) {
/* 239 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/* 241 */     RhinoInterpreter interp = (RhinoInterpreter)window.getInterpreter();
/*     */     
/* 243 */     final String uri = (String)Context.jsToJava(args[0], String.class);
/* 244 */     Window.URLResponseHandler urlHandler = null;
/* 245 */     if (args[1] instanceof Function) {
/* 246 */       urlHandler = new GetURLFunctionWrapper(interp, (Function)args[1], ww);
/*     */     } else {
/*     */       
/* 249 */       urlHandler = new GetURLObjectWrapper(interp, (ScriptableObject)args[1], ww);
/*     */     } 
/*     */     
/* 252 */     final Window.URLResponseHandler fw = urlHandler;
/*     */     
/* 254 */     AccessControlContext acc = ((RhinoInterpreter)window.getInterpreter()).getAccessControlContext();
/*     */ 
/*     */     
/* 257 */     if (len == 2) {
/* 258 */       AccessController.doPrivileged(new PrivilegedAction() {
/*     */             public Object run() {
/* 260 */               window.getURL(uri, fw);
/* 261 */               return null;
/*     */             }
/*     */           }acc);
/*     */     } else {
/* 265 */       AccessController.doPrivileged(new PrivilegedAction() {
/*     */             public Object run() {
/* 267 */               window.getURL(uri, fw, (String)Context.jsToJava(args[2], String.class));
/*     */ 
/*     */               
/* 270 */               return null;
/*     */             }
/*     */           }acc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void postURL(Context cx, Scriptable thisObj, final Object[] args, Function funObj) {
/* 283 */     int len = args.length;
/* 284 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 285 */     final Window window = ww.window;
/* 286 */     if (len < 3) {
/* 287 */       throw Context.reportRuntimeError("invalid argument count");
/*     */     }
/* 289 */     RhinoInterpreter interp = (RhinoInterpreter)window.getInterpreter();
/*     */     
/* 291 */     final String uri = (String)Context.jsToJava(args[0], String.class);
/* 292 */     final String content = (String)Context.jsToJava(args[1], String.class);
/* 293 */     Window.URLResponseHandler urlHandler = null;
/* 294 */     if (args[2] instanceof Function) {
/* 295 */       urlHandler = new GetURLFunctionWrapper(interp, (Function)args[2], ww);
/*     */     } else {
/*     */       
/* 298 */       urlHandler = new GetURLObjectWrapper(interp, (ScriptableObject)args[2], ww);
/*     */     } 
/*     */     
/* 301 */     final Window.URLResponseHandler fw = urlHandler;
/*     */ 
/*     */     
/* 304 */     AccessControlContext acc = interp.getAccessControlContext();
/*     */     
/* 306 */     switch (len) {
/*     */       case 3:
/* 308 */         AccessController.doPrivileged(new PrivilegedAction() {
/*     */               public Object run() {
/* 310 */                 window.postURL(uri, content, fw);
/* 311 */                 return null;
/*     */               }
/*     */             }acc);
/*     */         return;
/*     */       case 4:
/* 316 */         AccessController.doPrivileged(new PrivilegedAction() {
/*     */               public Object run() {
/* 318 */                 window.postURL(uri, content, fw, (String)Context.jsToJava(args[3], String.class));
/*     */ 
/*     */                 
/* 321 */                 return null;
/*     */               }
/*     */             }acc);
/*     */         return;
/*     */     } 
/* 326 */     AccessController.doPrivileged(new PrivilegedAction() {
/*     */           public Object run() {
/* 328 */             window.postURL(uri, content, fw, (String)Context.jsToJava(args[3], String.class), (String)Context.jsToJava(args[4], String.class));
/*     */ 
/*     */ 
/*     */             
/* 332 */             return null;
/*     */           }
/*     */         }acc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void alert(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 345 */     int len = args.length;
/* 346 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 347 */     Window window = ww.window;
/* 348 */     if (len >= 1) {
/* 349 */       String message = (String)Context.jsToJava(args[0], String.class);
/*     */       
/* 351 */       window.alert(message);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object confirm(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/* 362 */     int len = args.length;
/* 363 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 364 */     Window window = ww.window;
/* 365 */     if (len >= 1) {
/* 366 */       String message = (String)Context.jsToJava(args[0], String.class);
/*     */       
/* 368 */       if (window.confirm(message)) {
/* 369 */         return Context.toObject(Boolean.TRUE, thisObj);
/*     */       }
/* 371 */       return Context.toObject(Boolean.FALSE, thisObj);
/*     */     } 
/* 373 */     return Context.toObject(Boolean.FALSE, thisObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object prompt(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
/*     */     Object result;
/*     */     String message, defVal;
/* 384 */     WindowWrapper ww = (WindowWrapper)thisObj;
/* 385 */     Window window = ww.window;
/*     */     
/* 387 */     switch (args.length) {
/*     */       case 0:
/* 389 */         result = "";
/*     */         break;
/*     */       case 1:
/* 392 */         message = (String)Context.jsToJava(args[0], String.class);
/*     */         
/* 394 */         result = window.prompt(message);
/*     */         break;
/*     */       default:
/* 397 */         message = (String)Context.jsToJava(args[0], String.class);
/*     */         
/* 399 */         defVal = (String)Context.jsToJava(args[1], String.class);
/*     */         
/* 401 */         result = window.prompt(message, defVal);
/*     */         break;
/*     */     } 
/*     */     
/* 405 */     if (result == null) {
/* 406 */       return null;
/*     */     }
/* 408 */     return Context.toString(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/* 415 */     return this.window.getLocation();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(Object val) {
/* 422 */     String url = (String)Context.jsToJava(val, String.class);
/* 423 */     this.window.getLocation().assign(url);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FunctionWrapper
/*     */     implements Runnable
/*     */   {
/*     */     protected RhinoInterpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Function function;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object[] arguments;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FunctionWrapper(RhinoInterpreter ri, Function f, Object[] args) {
/* 452 */       this.interpreter = ri;
/* 453 */       this.function = f;
/* 454 */       this.arguments = args;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 461 */       this.interpreter.callHandler(this.function, this.arguments);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class GetURLFunctionWrapper
/*     */     implements Window.URLResponseHandler
/*     */   {
/*     */     protected RhinoInterpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Function function;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected WindowWrapper windowWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GetURLFunctionWrapper(RhinoInterpreter ri, Function fct, WindowWrapper ww) {
/* 491 */       this.interpreter = ri;
/* 492 */       this.function = fct;
/* 493 */       this.windowWrapper = ww;
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
/*     */     public void getURLDone(boolean success, String mime, String content) {
/* 505 */       this.interpreter.callHandler(this.function, new WindowWrapper.GetURLDoneArgBuilder(success, mime, content, this.windowWrapper));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class GetURLObjectWrapper
/*     */     implements Window.URLResponseHandler
/*     */   {
/*     */     private RhinoInterpreter interpreter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ScriptableObject object;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private WindowWrapper windowWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String COMPLETE = "operationComplete";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GetURLObjectWrapper(RhinoInterpreter ri, ScriptableObject obj, WindowWrapper ww) {
/* 541 */       this.interpreter = ri;
/* 542 */       this.object = obj;
/* 543 */       this.windowWrapper = ww;
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
/*     */     public void getURLDone(boolean success, String mime, String content) {
/* 555 */       this.interpreter.callMethod(this.object, "operationComplete", new WindowWrapper.GetURLDoneArgBuilder(success, mime, content, this.windowWrapper));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class GetURLDoneArgBuilder
/*     */     implements RhinoInterpreter.ArgumentsBuilder
/*     */   {
/*     */     boolean success;
/*     */     
/*     */     String mime;
/*     */     String content;
/*     */     WindowWrapper windowWrapper;
/*     */     
/*     */     public GetURLDoneArgBuilder(boolean success, String mime, String content, WindowWrapper ww) {
/* 570 */       this.success = success;
/* 571 */       this.mime = mime;
/* 572 */       this.content = content;
/* 573 */       this.windowWrapper = ww;
/*     */     }
/*     */     
/*     */     public Object[] buildArguments() {
/* 577 */       NativeObject nativeObject = new NativeObject();
/* 578 */       nativeObject.put("success", (Scriptable)nativeObject, this.success ? Boolean.TRUE : Boolean.FALSE);
/*     */       
/* 580 */       if (this.mime != null) {
/* 581 */         nativeObject.put("contentType", (Scriptable)nativeObject, Context.toObject(this.mime, (Scriptable)this.windowWrapper));
/*     */       }
/*     */       
/* 584 */       if (this.content != null) {
/* 585 */         nativeObject.put("content", (Scriptable)nativeObject, Context.toObject(this.content, (Scriptable)this.windowWrapper));
/*     */       }
/*     */       
/* 588 */       return new Object[] { nativeObject };
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/WindowWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */