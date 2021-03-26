/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlContext;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.batik.script.ImportInfo;
/*     */ import org.apache.batik.script.Interpreter;
/*     */ import org.apache.batik.script.InterpreterException;
/*     */ import org.apache.batik.script.rhino.BatikSecurityController;
/*     */ import org.apache.batik.script.rhino.RhinoClassLoader;
/*     */ import org.apache.batik.script.rhino.RhinoClassShutter;
/*     */ import org.mozilla.javascript.ClassCache;
/*     */ import org.mozilla.javascript.ClassShutter;
/*     */ import org.mozilla.javascript.Context;
/*     */ import org.mozilla.javascript.ContextAction;
/*     */ import org.mozilla.javascript.ContextFactory;
/*     */ import org.mozilla.javascript.Function;
/*     */ import org.mozilla.javascript.JavaScriptException;
/*     */ import org.mozilla.javascript.Script;
/*     */ import org.mozilla.javascript.Scriptable;
/*     */ import org.mozilla.javascript.ScriptableObject;
/*     */ import org.mozilla.javascript.SecurityController;
/*     */ import org.mozilla.javascript.WrapFactory;
/*     */ import org.mozilla.javascript.WrappedException;
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
/*     */ public class RhinoInterpreter
/*     */   implements Interpreter
/*     */ {
/*     */   private static final int MAX_CACHED_SCRIPTS = 32;
/*     */   public static final String SOURCE_NAME_SVG = "<SVG>";
/*     */   public static final String BIND_NAME_WINDOW = "window";
/*  84 */   protected static List contexts = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Window window;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   protected ScriptableObject globalObject = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected LinkedList compiledScripts = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected WrapFactory wrapFactory = new BatikWrapFactory(this);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected ClassShutter classShutter = (ClassShutter)new RhinoClassShutter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RhinoClassLoader rhinoClassLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected SecurityController securityController = (SecurityController)new BatikSecurityController();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   protected ContextFactory contextFactory = new Factory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Context defaultContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RhinoInterpreter(URL documentURL) {
/* 146 */     init(documentURL, null);
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
/*     */   public RhinoInterpreter(URL documentURL, ImportInfo imports) {
/* 161 */     init(documentURL, imports);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(URL documentURL, final ImportInfo imports) {
/*     */     try {
/* 168 */       this.rhinoClassLoader = new RhinoClassLoader(documentURL, getClass().getClassLoader());
/*     */     }
/* 170 */     catch (SecurityException se) {
/* 171 */       this.rhinoClassLoader = null;
/*     */     } 
/* 173 */     ContextAction initAction = new ContextAction() {
/*     */         public Object run(Context cx) {
/* 175 */           ScriptableObject scriptableObject = cx.initStandardObjects(null, false);
/* 176 */           RhinoInterpreter.this.defineGlobalWrapperClass((Scriptable)scriptableObject);
/* 177 */           RhinoInterpreter.this.globalObject = RhinoInterpreter.this.createGlobalObject(cx);
/* 178 */           ClassCache cache = ClassCache.get((Scriptable)RhinoInterpreter.this.globalObject);
/* 179 */           cache.setCachingEnabled((RhinoInterpreter.this.rhinoClassLoader != null));
/*     */           
/* 181 */           ImportInfo ii = imports;
/* 182 */           if (ii == null) ii = ImportInfo.getImports();
/*     */ 
/*     */           
/* 185 */           StringBuffer sb = new StringBuffer();
/*     */           
/* 187 */           Iterator<String> iter = ii.getPackages();
/* 188 */           while (iter.hasNext()) {
/* 189 */             String pkg = iter.next();
/* 190 */             sb.append("importPackage(Packages.");
/* 191 */             sb.append(pkg);
/* 192 */             sb.append(");");
/*     */           } 
/* 194 */           iter = ii.getClasses();
/* 195 */           while (iter.hasNext()) {
/* 196 */             String cls = iter.next();
/* 197 */             sb.append("importClass(Packages.");
/* 198 */             sb.append(cls);
/* 199 */             sb.append(");");
/*     */           } 
/* 201 */           cx.evaluateString((Scriptable)RhinoInterpreter.this.globalObject, sb.toString(), null, 0, RhinoInterpreter.this.rhinoClassLoader);
/*     */           
/* 203 */           return null;
/*     */         }
/*     */       };
/* 206 */     this.contextFactory.call(initAction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getMimeTypes() {
/* 214 */     return RhinoInterpreterFactory.RHINO_MIMETYPES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Window getWindow() {
/* 221 */     return this.window;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContextFactory getContextFactory() {
/* 228 */     return this.contextFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void defineGlobalWrapperClass(Scriptable global) {
/*     */     try {
/* 236 */       ScriptableObject.defineClass(global, WindowWrapper.class);
/* 237 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScriptableObject createGlobalObject(Context ctx) {
/* 246 */     return (ScriptableObject)new WindowWrapper(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessControlContext getAccessControlContext() {
/* 254 */     if (this.rhinoClassLoader == null) return null; 
/* 255 */     return this.rhinoClassLoader.getAccessControlContext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ScriptableObject getGlobalObject() {
/* 263 */     return this.globalObject;
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
/*     */   public Object evaluate(Reader scriptreader) throws IOException {
/* 275 */     return evaluate(scriptreader, "<SVG>");
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
/*     */   public Object evaluate(final Reader scriptReader, final String description) throws IOException {
/* 289 */     ContextAction evaluateAction = new ContextAction() {
/*     */         public Object run(Context cx) {
/*     */           try {
/* 292 */             return cx.evaluateReader((Scriptable)RhinoInterpreter.this.globalObject, scriptReader, description, 1, RhinoInterpreter.this.rhinoClassLoader);
/*     */ 
/*     */           
/*     */           }
/* 296 */           catch (IOException ioe) {
/* 297 */             throw new WrappedException(ioe);
/*     */           } 
/*     */         }
/*     */       };
/*     */     try {
/* 302 */       return this.contextFactory.call(evaluateAction);
/* 303 */     } catch (JavaScriptException e) {
/*     */       
/* 305 */       Object value = e.getValue();
/* 306 */       Exception ex = (value instanceof Exception) ? (Exception)value : (Exception)e;
/* 307 */       throw new InterpreterException(ex, ex.getMessage(), -1, -1);
/* 308 */     } catch (WrappedException we) {
/* 309 */       Throwable w = we.getWrappedException();
/* 310 */       if (w instanceof Exception) {
/* 311 */         throw new InterpreterException((Exception)w, w.getMessage(), -1, -1);
/*     */       }
/*     */       
/* 314 */       throw new InterpreterException(w.getMessage(), -1, -1);
/*     */     }
/* 316 */     catch (InterruptedBridgeException ibe) {
/* 317 */       throw ibe;
/* 318 */     } catch (RuntimeException re) {
/* 319 */       throw new InterpreterException(re, re.getMessage(), -1, -1);
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
/*     */   public Object evaluate(final String scriptStr) {
/* 334 */     ContextAction evalAction = new ContextAction() {
/*     */         public Object run(final Context cx) {
/* 336 */           Script script = null;
/* 337 */           RhinoInterpreter.Entry entry = null;
/* 338 */           Iterator<RhinoInterpreter.Entry> it = RhinoInterpreter.this.compiledScripts.iterator();
/*     */ 
/*     */           
/* 341 */           while (it.hasNext()) {
/* 342 */             if ((entry = it.next()).str.equals(scriptStr)) {
/*     */ 
/*     */ 
/*     */               
/* 346 */               script = entry.script;
/* 347 */               it.remove();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 352 */           if (script == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 357 */             PrivilegedAction<Script> compile = new PrivilegedAction() {
/*     */                 public Object run() {
/*     */                   try {
/* 360 */                     return cx.compileReader(new StringReader(scriptStr), "<SVG>", 1, RhinoInterpreter.this.rhinoClassLoader);
/*     */                   
/*     */                   }
/* 363 */                   catch (IOException ioEx) {
/*     */                     
/* 365 */                     throw new RuntimeException(ioEx.getMessage());
/*     */                   } 
/*     */                 }
/*     */               };
/* 369 */             script = AccessController.<Script>doPrivileged(compile);
/*     */             
/* 371 */             if (RhinoInterpreter.this.compiledScripts.size() + 1 > 32)
/*     */             {
/*     */ 
/*     */               
/* 375 */               RhinoInterpreter.this.compiledScripts.removeFirst();
/*     */             }
/*     */             
/* 378 */             RhinoInterpreter.this.compiledScripts.addLast(new RhinoInterpreter.Entry(scriptStr, script));
/*     */           }
/*     */           else {
/*     */             
/* 382 */             RhinoInterpreter.this.compiledScripts.addLast(entry);
/*     */           } 
/*     */           
/* 385 */           return script.exec(cx, (Scriptable)RhinoInterpreter.this.globalObject);
/*     */         }
/*     */       };
/*     */     try {
/* 389 */       return this.contextFactory.call(evalAction);
/* 390 */     } catch (InterpreterException ie) {
/* 391 */       throw ie;
/* 392 */     } catch (JavaScriptException e) {
/*     */       
/* 394 */       Object value = e.getValue();
/* 395 */       Exception ex = (value instanceof Exception) ? (Exception)value : (Exception)e;
/* 396 */       throw new InterpreterException(ex, ex.getMessage(), -1, -1);
/* 397 */     } catch (WrappedException we) {
/* 398 */       Throwable w = we.getWrappedException();
/* 399 */       if (w instanceof Exception) {
/* 400 */         throw new InterpreterException((Exception)w, w.getMessage(), -1, -1);
/*     */       }
/*     */       
/* 403 */       throw new InterpreterException(w.getMessage(), -1, -1);
/*     */     }
/* 405 */     catch (RuntimeException re) {
/* 406 */       throw new InterpreterException(re, re.getMessage(), -1, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 415 */     if (this.rhinoClassLoader != null) {
/* 416 */       ClassCache cache = ClassCache.get((Scriptable)this.globalObject);
/* 417 */       cache.setCachingEnabled(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void bindObject(final String name, final Object object) {
/* 428 */     this.contextFactory.call(new ContextAction() {
/*     */           public Object run(Context cx) {
/* 430 */             Object o = object;
/* 431 */             if (name.equals("window") && object instanceof Window) {
/* 432 */               ((WindowWrapper)RhinoInterpreter.this.globalObject).window = (Window)object;
/* 433 */               RhinoInterpreter.this.window = (Window)object;
/* 434 */               o = RhinoInterpreter.this.globalObject;
/*     */             } 
/*     */             
/* 437 */             Scriptable jsObject = Context.toObject(o, (Scriptable)RhinoInterpreter.this.globalObject);
/* 438 */             RhinoInterpreter.this.globalObject.put(name, (Scriptable)RhinoInterpreter.this.globalObject, jsObject);
/* 439 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void callHandler(final Function handler, final Object arg) {
/* 448 */     this.contextFactory.call(new ContextAction() {
/*     */           public Object run(Context cx) {
/* 450 */             Object a = Context.toObject(arg, (Scriptable)RhinoInterpreter.this.globalObject);
/* 451 */             Object[] args = { a };
/* 452 */             handler.call(cx, (Scriptable)RhinoInterpreter.this.globalObject, (Scriptable)RhinoInterpreter.this.globalObject, args);
/* 453 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void callMethod(final ScriptableObject obj, final String methodName, final ArgumentsBuilder ab) {
/* 464 */     this.contextFactory.call(new ContextAction() {
/*     */           public Object run(Context cx) {
/* 466 */             ScriptableObject.callMethod((Scriptable)obj, methodName, ab.buildArguments());
/*     */             
/* 468 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void callHandler(final Function handler, final Object[] args) {
/* 477 */     this.contextFactory.call(new ContextAction() {
/*     */           public Object run(Context cx) {
/* 479 */             handler.call(cx, (Scriptable)RhinoInterpreter.this.globalObject, (Scriptable)RhinoInterpreter.this.globalObject, args);
/* 480 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void callHandler(final Function handler, final ArgumentsBuilder ab) {
/* 489 */     this.contextFactory.call(new ContextAction() {
/*     */           public Object run(Context cx) {
/* 491 */             Object[] args = ab.buildArguments();
/* 492 */             handler.call(cx, handler.getParentScope(), (Scriptable)RhinoInterpreter.this.globalObject, args);
/* 493 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object call(ContextAction action) {
/* 502 */     return this.contextFactory.call(action);
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
/*     */   Scriptable buildEventTargetWrapper(EventTarget obj) {
/* 516 */     return (Scriptable)new EventTargetWrapper((Scriptable)this.globalObject, obj, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOut(Writer out) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 536 */     return null;
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
/*     */   public void setLocale(Locale locale) {}
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
/*     */   public String formatMessage(String key, Object[] args) {
/* 560 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ArgumentsBuilder
/*     */   {
/*     */     Object[] buildArguments();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Entry
/*     */   {
/*     */     public String str;
/*     */ 
/*     */     
/*     */     public Script script;
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry(String str, Script script) {
/* 582 */       this.str = str;
/* 583 */       this.script = script;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class Factory
/*     */     extends ContextFactory
/*     */   {
/*     */     protected Context makeContext() {
/* 596 */       Context cx = super.makeContext();
/* 597 */       cx.setWrapFactory(RhinoInterpreter.this.wrapFactory);
/* 598 */       cx.setSecurityController(RhinoInterpreter.this.securityController);
/* 599 */       cx.setClassShutter(RhinoInterpreter.this.classShutter);
/* 600 */       if (RhinoInterpreter.this.rhinoClassLoader == null) {
/* 601 */         cx.setOptimizationLevel(-1);
/*     */       }
/* 603 */       return cx;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/RhinoInterpreter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */