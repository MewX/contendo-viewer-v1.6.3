/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.script.ScriptEventWrapper;
/*     */ import org.mozilla.javascript.Context;
/*     */ import org.mozilla.javascript.ContextAction;
/*     */ import org.mozilla.javascript.Function;
/*     */ import org.mozilla.javascript.NativeJavaObject;
/*     */ import org.mozilla.javascript.Scriptable;
/*     */ import org.mozilla.javascript.ScriptableObject;
/*     */ import org.mozilla.javascript.Undefined;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
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
/*     */ class EventTargetWrapper
/*     */   extends NativeJavaObject
/*     */ {
/*     */   protected static WeakHashMap mapOfListenerMap;
/*     */   public static final String ADD_NAME = "addEventListener";
/*     */   public static final String ADDNS_NAME = "addEventListenerNS";
/*     */   public static final String REMOVE_NAME = "removeEventListener";
/*     */   public static final String REMOVENS_NAME = "removeEventListenerNS";
/*     */   protected RhinoInterpreter interpreter;
/*     */   
/*     */   static class FunctionEventListener
/*     */     implements EventListener
/*     */   {
/*     */     protected Function function;
/*     */     protected RhinoInterpreter interpreter;
/*     */     
/*     */     FunctionEventListener(Function f, RhinoInterpreter i) {
/*  60 */       this.function = f;
/*  61 */       this.interpreter = i;
/*     */     }
/*     */     public void handleEvent(Event evt) {
/*     */       Object event;
/*  65 */       if (evt instanceof ScriptEventWrapper) {
/*  66 */         event = ((ScriptEventWrapper)evt).getEventObject();
/*     */       } else {
/*  68 */         event = evt;
/*     */       } 
/*  70 */       this.interpreter.callHandler(this.function, event);
/*     */     }
/*     */   }
/*     */   
/*     */   static class HandleEventListener
/*     */     implements EventListener {
/*     */     public static final String HANDLE_EVENT = "handleEvent";
/*     */     public Scriptable scriptable;
/*  78 */     public Object[] array = new Object[1];
/*     */     public RhinoInterpreter interpreter;
/*     */     
/*     */     HandleEventListener(Scriptable s, RhinoInterpreter interpreter) {
/*  82 */       this.scriptable = s;
/*  83 */       this.interpreter = interpreter;
/*     */     }
/*     */     public void handleEvent(Event evt) {
/*  86 */       if (evt instanceof ScriptEventWrapper) {
/*  87 */         this.array[0] = ((ScriptEventWrapper)evt).getEventObject();
/*     */       } else {
/*  89 */         this.array[0] = evt;
/*     */       } 
/*  91 */       ContextAction handleEventAction = new ContextAction() {
/*     */           public Object run(Context cx) {
/*  93 */             ScriptableObject.callMethod(EventTargetWrapper.HandleEventListener.this.scriptable, "handleEvent", EventTargetWrapper.HandleEventListener.this.array);
/*     */             
/*  95 */             return null;
/*     */           }
/*     */         };
/*  98 */       this.interpreter.call(handleEventAction);
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class FunctionProxy implements Function {
/*     */     protected Function delegate;
/*     */     
/*     */     public FunctionProxy(Function delegate) {
/* 106 */       this.delegate = delegate;
/*     */     }
/*     */ 
/*     */     
/*     */     public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
/* 111 */       return this.delegate.construct(cx, scope, args);
/*     */     }
/*     */     
/*     */     public String getClassName() {
/* 115 */       return this.delegate.getClassName();
/*     */     }
/*     */     
/*     */     public Object get(String name, Scriptable start) {
/* 119 */       return this.delegate.get(name, start);
/*     */     }
/*     */     
/*     */     public Object get(int index, Scriptable start) {
/* 123 */       return this.delegate.get(index, start);
/*     */     }
/*     */     
/*     */     public boolean has(String name, Scriptable start) {
/* 127 */       return this.delegate.has(name, start);
/*     */     }
/*     */     
/*     */     public boolean has(int index, Scriptable start) {
/* 131 */       return this.delegate.has(index, start);
/*     */     }
/*     */     
/*     */     public void put(String name, Scriptable start, Object value) {
/* 135 */       this.delegate.put(name, start, value);
/*     */     }
/*     */     
/*     */     public void put(int index, Scriptable start, Object value) {
/* 139 */       this.delegate.put(index, start, value);
/*     */     }
/*     */     
/*     */     public void delete(String name) {
/* 143 */       this.delegate.delete(name);
/*     */     }
/*     */     
/*     */     public void delete(int index) {
/* 147 */       this.delegate.delete(index);
/*     */     }
/*     */     
/*     */     public Scriptable getPrototype() {
/* 151 */       return this.delegate.getPrototype();
/*     */     }
/*     */     
/*     */     public void setPrototype(Scriptable prototype) {
/* 155 */       this.delegate.setPrototype(prototype);
/*     */     }
/*     */     
/*     */     public Scriptable getParentScope() {
/* 159 */       return this.delegate.getParentScope();
/*     */     }
/*     */     
/*     */     public void setParentScope(Scriptable parent) {
/* 163 */       this.delegate.setParentScope(parent);
/*     */     }
/*     */     
/*     */     public Object[] getIds() {
/* 167 */       return this.delegate.getIds();
/*     */     }
/*     */     
/*     */     public Object getDefaultValue(Class hint) {
/* 171 */       return this.delegate.getDefaultValue(hint);
/*     */     }
/*     */     
/*     */     public boolean hasInstance(Scriptable instance) {
/* 175 */       return this.delegate.hasInstance(instance);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class FunctionAddProxy
/*     */     extends FunctionProxy
/*     */   {
/*     */     protected Map listenerMap;
/*     */ 
/*     */     
/*     */     protected RhinoInterpreter interpreter;
/*     */ 
/*     */ 
/*     */     
/*     */     FunctionAddProxy(RhinoInterpreter interpreter, Function delegate, Map listenerMap) {
/* 193 */       super(delegate);
/* 194 */       this.listenerMap = listenerMap;
/* 195 */       this.interpreter = interpreter;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object call(Context ctx, Scriptable scope, Scriptable thisObj, Object[] args) {
/* 200 */       NativeJavaObject njo = (NativeJavaObject)thisObj;
/* 201 */       if (args[1] instanceof Function) {
/* 202 */         EventListener evtListener = null;
/* 203 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[1]);
/* 204 */         if (sr != null)
/* 205 */           evtListener = sr.get(); 
/* 206 */         if (evtListener == null) {
/* 207 */           evtListener = new EventTargetWrapper.FunctionEventListener((Function)args[1], this.interpreter);
/*     */           
/* 209 */           this.listenerMap.put(args[1], new SoftReference<EventListener>(evtListener));
/*     */         } 
/*     */         
/* 212 */         Class[] paramTypes = { String.class, Function.class, boolean.class };
/*     */         
/* 214 */         for (int i = 0; i < args.length; i++)
/* 215 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 216 */         ((EventTarget)njo.unwrap()).addEventListener((String)args[0], evtListener, ((Boolean)args[2]).booleanValue());
/*     */ 
/*     */         
/* 219 */         return Undefined.instance;
/*     */       } 
/* 221 */       if (args[1] instanceof org.mozilla.javascript.NativeObject) {
/* 222 */         EventListener evtListener = null;
/* 223 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[1]);
/* 224 */         if (sr != null)
/* 225 */           evtListener = sr.get(); 
/* 226 */         if (evtListener == null) {
/* 227 */           evtListener = new EventTargetWrapper.HandleEventListener((Scriptable)args[1], this.interpreter);
/*     */           
/* 229 */           this.listenerMap.put(args[1], new SoftReference<EventListener>(evtListener));
/*     */         } 
/*     */ 
/*     */         
/* 233 */         Class[] paramTypes = { String.class, Scriptable.class, boolean.class };
/*     */         
/* 235 */         for (int i = 0; i < args.length; i++)
/* 236 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 237 */         ((EventTarget)njo.unwrap()).addEventListener((String)args[0], evtListener, ((Boolean)args[2]).booleanValue());
/*     */ 
/*     */         
/* 240 */         return Undefined.instance;
/*     */       } 
/* 242 */       return this.delegate.call(ctx, scope, thisObj, args);
/*     */     }
/*     */   }
/*     */   
/*     */   static class FunctionRemoveProxy extends FunctionProxy {
/*     */     public Map listenerMap;
/*     */     
/*     */     FunctionRemoveProxy(Function delegate, Map listenerMap) {
/* 250 */       super(delegate);
/* 251 */       this.listenerMap = listenerMap;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object call(Context ctx, Scriptable scope, Scriptable thisObj, Object[] args) {
/* 256 */       NativeJavaObject njo = (NativeJavaObject)thisObj;
/* 257 */       if (args[1] instanceof Function) {
/* 258 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[1]);
/* 259 */         if (sr == null)
/* 260 */           return Undefined.instance; 
/* 261 */         EventListener el = sr.get();
/* 262 */         if (el == null) {
/* 263 */           return Undefined.instance;
/*     */         }
/*     */         
/* 266 */         Class[] paramTypes = { String.class, Function.class, boolean.class };
/*     */         
/* 268 */         for (int i = 0; i < args.length; i++)
/* 269 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 270 */         ((EventTarget)njo.unwrap()).removeEventListener((String)args[0], el, ((Boolean)args[2]).booleanValue());
/*     */         
/* 272 */         return Undefined.instance;
/*     */       } 
/* 274 */       if (args[1] instanceof org.mozilla.javascript.NativeObject) {
/* 275 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[1]);
/* 276 */         if (sr == null)
/* 277 */           return Undefined.instance; 
/* 278 */         EventListener el = sr.get();
/* 279 */         if (el == null) {
/* 280 */           return Undefined.instance;
/*     */         }
/* 282 */         Class[] paramTypes = { String.class, Scriptable.class, boolean.class };
/*     */         
/* 284 */         for (int i = 0; i < args.length; i++)
/* 285 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 286 */         ((EventTarget)njo.unwrap()).removeEventListener((String)args[0], el, ((Boolean)args[2]).booleanValue());
/*     */         
/* 288 */         return Undefined.instance;
/*     */       } 
/* 290 */       return this.delegate.call(ctx, scope, thisObj, args);
/*     */     }
/*     */   }
/*     */   
/*     */   static class FunctionAddNSProxy
/*     */     extends FunctionProxy {
/*     */     protected Map listenerMap;
/*     */     protected RhinoInterpreter interpreter;
/*     */     
/*     */     FunctionAddNSProxy(RhinoInterpreter interpreter, Function delegate, Map listenerMap) {
/* 300 */       super(delegate);
/* 301 */       this.listenerMap = listenerMap;
/* 302 */       this.interpreter = interpreter;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object call(Context ctx, Scriptable scope, Scriptable thisObj, Object[] args) {
/* 307 */       NativeJavaObject njo = (NativeJavaObject)thisObj;
/* 308 */       if (args[2] instanceof Function) {
/* 309 */         EventListener evtListener = new EventTargetWrapper.FunctionEventListener((Function)args[2], this.interpreter);
/*     */         
/* 311 */         this.listenerMap.put(args[2], new SoftReference<EventListener>(evtListener));
/*     */         
/* 313 */         Class[] paramTypes = { String.class, String.class, Function.class, boolean.class, Object.class };
/*     */ 
/*     */         
/* 316 */         for (int i = 0; i < args.length; i++)
/* 317 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 318 */         AbstractNode target = (AbstractNode)njo.unwrap();
/* 319 */         target.addEventListenerNS((String)args[0], (String)args[1], evtListener, ((Boolean)args[3]).booleanValue(), args[4]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 325 */         return Undefined.instance;
/*     */       } 
/* 327 */       if (args[2] instanceof org.mozilla.javascript.NativeObject) {
/* 328 */         EventListener evtListener = new EventTargetWrapper.HandleEventListener((Scriptable)args[2], this.interpreter);
/*     */         
/* 330 */         this.listenerMap.put(args[2], new SoftReference<EventListener>(evtListener));
/*     */         
/* 332 */         Class[] paramTypes = { String.class, String.class, Scriptable.class, boolean.class, Object.class };
/*     */ 
/*     */         
/* 335 */         for (int i = 0; i < args.length; i++)
/* 336 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 337 */         AbstractNode target = (AbstractNode)njo.unwrap();
/* 338 */         target.addEventListenerNS((String)args[0], (String)args[1], evtListener, ((Boolean)args[3]).booleanValue(), args[4]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 344 */         return Undefined.instance;
/*     */       } 
/* 346 */       return this.delegate.call(ctx, scope, thisObj, args);
/*     */     }
/*     */   }
/*     */   
/*     */   static class FunctionRemoveNSProxy extends FunctionProxy {
/*     */     protected Map listenerMap;
/*     */     
/*     */     FunctionRemoveNSProxy(Function delegate, Map listenerMap) {
/* 354 */       super(delegate);
/* 355 */       this.listenerMap = listenerMap;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object call(Context ctx, Scriptable scope, Scriptable thisObj, Object[] args) {
/* 360 */       NativeJavaObject njo = (NativeJavaObject)thisObj;
/* 361 */       if (args[2] instanceof Function) {
/* 362 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[2]);
/* 363 */         if (sr == null)
/* 364 */           return Undefined.instance; 
/* 365 */         EventListener el = sr.get();
/* 366 */         if (el == null) {
/* 367 */           return Undefined.instance;
/*     */         }
/* 369 */         Class[] paramTypes = { String.class, String.class, Function.class, boolean.class };
/*     */         
/* 371 */         for (int i = 0; i < args.length; i++)
/* 372 */           args[i] = Context.jsToJava(args[i], paramTypes[i]); 
/* 373 */         AbstractNode target = (AbstractNode)njo.unwrap();
/* 374 */         target.removeEventListenerNS((String)args[0], (String)args[1], el, ((Boolean)args[3]).booleanValue());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 379 */         return Undefined.instance;
/*     */       } 
/* 381 */       if (args[2] instanceof org.mozilla.javascript.NativeObject) {
/* 382 */         SoftReference<EventListener> sr = (SoftReference)this.listenerMap.get(args[2]);
/* 383 */         if (sr == null)
/* 384 */           return Undefined.instance; 
/* 385 */         EventListener el = sr.get();
/* 386 */         if (el == null) {
/* 387 */           return Undefined.instance;
/*     */         }
/* 389 */         Class[] paramTypes = { String.class, String.class, Scriptable.class, boolean.class };
/*     */         
/* 391 */         for (int i = 0; i < args.length; i++) {
/* 392 */           args[i] = Context.jsToJava(args[i], paramTypes[i]);
/*     */         }
/* 394 */         AbstractNode target = (AbstractNode)njo.unwrap();
/* 395 */         target.removeEventListenerNS((String)args[0], (String)args[1], el, ((Boolean)args[3]).booleanValue());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 400 */         return Undefined.instance;
/*     */       } 
/* 402 */       return this.delegate.call(ctx, scope, thisObj, args);
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
/*     */ 
/*     */   
/*     */   EventTargetWrapper(Scriptable scope, EventTarget object, RhinoInterpreter interpreter) {
/* 420 */     super(scope, object, null);
/* 421 */     this.interpreter = interpreter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(String name, Scriptable start) {
/* 428 */     Object method = super.get(name, start);
/* 429 */     if (name.equals("addEventListener")) {
/*     */ 
/*     */       
/* 432 */       method = new FunctionAddProxy(this.interpreter, (Function)method, initMap());
/*     */     }
/* 434 */     else if (name.equals("removeEventListener")) {
/*     */ 
/*     */       
/* 437 */       method = new FunctionRemoveProxy((Function)method, initMap());
/*     */     }
/* 439 */     else if (name.equals("addEventListenerNS")) {
/* 440 */       method = new FunctionAddNSProxy(this.interpreter, (Function)method, initMap());
/*     */     }
/* 442 */     else if (name.equals("removeEventListenerNS")) {
/* 443 */       method = new FunctionRemoveNSProxy((Function)method, initMap());
/*     */     } 
/* 445 */     return method;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map initMap() {
/* 452 */     Map<Object, Object> map = null;
/* 453 */     if (mapOfListenerMap == null)
/* 454 */       mapOfListenerMap = new WeakHashMap<Object, Object>(10); 
/* 455 */     if ((map = (Map)mapOfListenerMap.get(unwrap())) == null) {
/* 456 */       mapOfListenerMap.put(unwrap(), map = new WeakHashMap<Object, Object>(2));
/*     */     }
/* 458 */     return map;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/EventTargetWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */