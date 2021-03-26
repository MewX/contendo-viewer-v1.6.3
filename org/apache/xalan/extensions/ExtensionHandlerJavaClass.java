/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemExtensionCall;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.trace.ExtensionEvent;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.functions.FuncExtFunction;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ public class ExtensionHandlerJavaClass
/*     */   extends ExtensionHandlerJava
/*     */ {
/*  58 */   private Class m_classObj = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private Object m_defaultInstance = null;
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
/*     */   public ExtensionHandlerJavaClass(String namespaceUri, String scriptLang, String className) {
/*  79 */     super(namespaceUri, scriptLang, className);
/*     */ 
/*     */     
/*  82 */     try { this.m_classObj = ExtensionHandler.getClassForName(className); } catch (ClassNotFoundException classNotFoundException) {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFunctionAvailable(String function) {
/* 103 */     Method[] methods = this.m_classObj.getMethods();
/* 104 */     int nMethods = methods.length;
/* 105 */     for (int i = 0; i < nMethods; i++) {
/*     */       
/* 107 */       if (methods[i].getName().equals(function))
/* 108 */         return true; 
/*     */     } 
/* 110 */     return false;
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
/*     */   public boolean isElementAvailable(String element) {
/* 124 */     Method[] methods = this.m_classObj.getMethods();
/* 125 */     int nMethods = methods.length;
/* 126 */     for (int i = 0; i < nMethods; i++) {
/*     */       
/* 128 */       if (methods[i].getName().equals(element)) {
/*     */         
/* 130 */         Class[] paramTypes = methods[i].getParameterTypes();
/* 131 */         if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom(XSLProcessorContext.class) && paramTypes[1].isAssignableFrom(ElemExtensionCall.class))
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 136 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 140 */     return false;
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
/*     */   public Object callFunction(String funcName, Vector args, Object methodKey, ExpressionContext exprContext) throws TransformerException {
/*     */     
/*     */     try { byte b;
/* 193 */       if (funcName.equals("new")) {
/*     */         
/* 195 */         Object[] methodArgs = new Object[args.size()];
/* 196 */         Object[][] convertedArgs = new Object[1][];
/* 197 */         for (b = 0; b < methodArgs.length; b++)
/*     */         {
/* 199 */           methodArgs[b] = args.elementAt(b);
/*     */         }
/* 201 */         Constructor c = (Constructor)getFromCache(methodKey, null, methodArgs);
/* 202 */         if (c != null && !TransformerImpl.S_DEBUG) {
/*     */ 
/*     */           
/*     */           try { 
/* 206 */             Class[] paramTypes = c.getParameterTypes();
/* 207 */             MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
/* 208 */             return c.newInstance(convertedArgs[0]); } catch (InvocationTargetException ite)
/*     */           
/*     */           { 
/*     */             
/* 212 */             throw ite; } catch (Exception exception) {}
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 219 */         c = MethodResolver.getConstructor(this.m_classObj, methodArgs, convertedArgs, exprContext);
/*     */ 
/*     */ 
/*     */         
/* 223 */         putToCache(methodKey, null, methodArgs, c);
/* 224 */         if (TransformerImpl.S_DEBUG) {
/* 225 */           Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 226 */           trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
/*     */ 
/*     */           
/* 229 */           try { result = c.newInstance(convertedArgs[0]); } catch (Exception e)
/*     */           
/* 231 */           { throw e; }
/*     */           finally
/* 233 */           { trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, c, convertedArgs[0])); }
/*     */           
/* 235 */           return result;
/*     */         } 
/* 237 */         return c.newInstance(convertedArgs[0]);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 244 */       Object targetObject = null;
/* 245 */       Object[] arrayOfObject = new Object[args.size()];
/* 246 */       Object[][] arrayOfObject1 = new Object[1][];
/* 247 */       for (int i = 0; i < arrayOfObject.length; i++)
/*     */       {
/* 249 */         arrayOfObject[i] = args.elementAt(i);
/*     */       }
/* 251 */       Method m = (Method)getFromCache(methodKey, null, arrayOfObject);
/* 252 */       if (m != null && !TransformerImpl.S_DEBUG) {
/*     */ 
/*     */         
/*     */         try { 
/* 256 */           Class[] arrayOfClass = m.getParameterTypes();
/* 257 */           MethodResolver.convertParams(arrayOfObject, arrayOfObject1, arrayOfClass, exprContext);
/* 258 */           if (Modifier.isStatic(m.getModifiers())) {
/* 259 */             return m.invoke(null, arrayOfObject1[0]);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 264 */           int nTargetArgs = (arrayOfObject1[0]).length;
/* 265 */           if (ExpressionContext.class.isAssignableFrom(arrayOfClass[0]))
/* 266 */             nTargetArgs--; 
/* 267 */           if (arrayOfObject.length <= nTargetArgs) {
/* 268 */             return m.invoke(this.m_defaultInstance, arrayOfObject1[0]);
/*     */           }
/*     */           
/* 271 */           targetObject = arrayOfObject[0];
/*     */           
/* 273 */           if (targetObject instanceof XObject) {
/* 274 */             targetObject = ((XObject)targetObject).object();
/*     */           }
/* 276 */           return m.invoke(targetObject, arrayOfObject1[0]); } catch (InvocationTargetException ite)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */           
/* 282 */           throw ite; } catch (Exception exception) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 290 */       if (args.size() > 0) {
/*     */         
/* 292 */         targetObject = arrayOfObject[0];
/*     */         
/* 294 */         if (targetObject instanceof XObject) {
/* 295 */           targetObject = ((XObject)targetObject).object();
/*     */         }
/* 297 */         if (this.m_classObj.isAssignableFrom(targetObject.getClass())) {
/* 298 */           b = 4;
/*     */         } else {
/* 300 */           b = 3;
/*     */         } 
/*     */       } else {
/*     */         
/* 304 */         targetObject = null;
/* 305 */         b = 3;
/*     */       } 
/*     */       
/* 308 */       m = MethodResolver.getMethod(this.m_classObj, funcName, arrayOfObject, arrayOfObject1, exprContext, b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       putToCache(methodKey, null, arrayOfObject, m);
/*     */       
/* 316 */       if (4 == b) {
/* 317 */         if (TransformerImpl.S_DEBUG) {
/* 318 */           Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 319 */           trans.getTraceManager().fireExtensionEvent(m, targetObject, arrayOfObject1[0]);
/*     */ 
/*     */           
/* 322 */           try { result = m.invoke(targetObject, arrayOfObject1[0]); } catch (Exception e)
/*     */           
/* 324 */           { throw e; }
/*     */           finally
/* 326 */           { trans.getTraceManager().fireExtensionEndEvent(m, targetObject, arrayOfObject1[0]); }
/*     */           
/* 328 */           return result;
/*     */         } 
/* 330 */         return m.invoke(targetObject, arrayOfObject1[0]);
/*     */       } 
/*     */ 
/*     */       
/* 334 */       if (Modifier.isStatic(m.getModifiers())) {
/* 335 */         if (TransformerImpl.S_DEBUG) {
/* 336 */           Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 337 */           trans.getTraceManager().fireExtensionEvent(m, null, arrayOfObject1[0]);
/*     */ 
/*     */           
/* 340 */           try { result = m.invoke(null, arrayOfObject1[0]); } catch (Exception e)
/*     */           
/* 342 */           { throw e; }
/*     */           finally
/* 344 */           { trans.getTraceManager().fireExtensionEndEvent(m, null, arrayOfObject1[0]); }
/*     */           
/* 346 */           return result;
/*     */         } 
/* 348 */         return m.invoke(null, arrayOfObject1[0]);
/*     */       } 
/*     */ 
/*     */       
/* 352 */       if (null == this.m_defaultInstance)
/*     */       {
/* 354 */         if (TransformerImpl.S_DEBUG) {
/* 355 */           TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 356 */           trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, this.m_classObj));
/*     */           
/* 358 */           try { this.m_defaultInstance = this.m_classObj.newInstance(); } catch (Exception e)
/*     */           
/* 360 */           { throw e; }
/*     */           finally
/* 362 */           { trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, this.m_classObj)); }
/*     */         
/*     */         } else {
/* 365 */           this.m_defaultInstance = this.m_classObj.newInstance();
/*     */         }  } 
/* 367 */       if (TransformerImpl.S_DEBUG) {
/* 368 */         Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 369 */         trans.getTraceManager().fireExtensionEvent(m, this.m_defaultInstance, arrayOfObject1[0]);
/*     */ 
/*     */         
/* 372 */         try { result = m.invoke(this.m_defaultInstance, arrayOfObject1[0]); } catch (Exception e)
/*     */         
/* 374 */         { throw e; }
/*     */         finally
/* 376 */         { trans.getTraceManager().fireExtensionEndEvent(m, this.m_defaultInstance, arrayOfObject1[0]); }
/*     */         
/* 378 */         return result;
/*     */       } 
/* 380 */       return m.invoke(this.m_defaultInstance, arrayOfObject1[0]); } catch (InvocationTargetException ite)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 388 */       Throwable resultException = ite;
/* 389 */       Throwable targetException = ite.getTargetException();
/*     */       
/* 391 */       if (targetException instanceof TransformerException)
/* 392 */         throw (TransformerException)targetException; 
/* 393 */       if (targetException != null) {
/* 394 */         resultException = targetException;
/*     */       }
/* 396 */       throw new TransformerException(resultException); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 401 */       throw new TransformerException(e); }
/*     */   
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
/*     */   public Object callFunction(FuncExtFunction extFunction, Vector args, ExpressionContext exprContext) throws TransformerException {
/* 419 */     return callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
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
/*     */   public void processElement(String localPart, ElemTemplateElement element, TransformerImpl transformer, Stylesheet stylesheetTree, Object methodKey) throws TransformerException, IOException {
/* 450 */     Object result = null;
/*     */     
/* 452 */     Method m = (Method)getFromCache(methodKey, null, null);
/* 453 */     if (null == m) {
/*     */ 
/*     */ 
/*     */       
/* 457 */       try { m = MethodResolver.getElementMethod(this.m_classObj, localPart);
/* 458 */         if (null == this.m_defaultInstance && !Modifier.isStatic(m.getModifiers()))
/* 459 */           if (TransformerImpl.S_DEBUG)
/* 460 */           { transformer.getTraceManager().fireExtensionEvent(new ExtensionEvent(transformer, this.m_classObj));
/*     */             
/* 462 */             try { this.m_defaultInstance = this.m_classObj.newInstance(); } catch (Exception e)
/*     */             
/* 464 */             { throw e; }
/*     */             finally
/* 466 */             { transformer.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(transformer, this.m_classObj)); }
/*     */              }
/*     */           else
/* 469 */           { this.m_defaultInstance = this.m_classObj.newInstance(); }   } catch (Exception e)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */         
/* 475 */         throw new TransformerException(e.getMessage(), e); }
/*     */       
/* 477 */       putToCache(methodKey, null, null, m);
/*     */     } 
/*     */     
/* 480 */     XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 485 */     try { if (TransformerImpl.S_DEBUG)
/* 486 */       { transformer.getTraceManager().fireExtensionEvent(m, this.m_defaultInstance, new Object[] { xpc, element });
/*     */         
/* 488 */         try { result = m.invoke(this.m_defaultInstance, new Object[] { xpc, element }); } catch (Exception e)
/*     */         
/* 490 */         { throw e; }
/*     */         finally
/* 492 */         { transformer.getTraceManager().fireExtensionEndEvent(m, this.m_defaultInstance, new Object[] { xpc, element }); }
/*     */          }
/*     */       else
/* 495 */       { result = m.invoke(this.m_defaultInstance, new Object[] { xpc, element }); }  } catch (InvocationTargetException e)
/*     */     
/*     */     { 
/*     */       
/* 499 */       Throwable targetException = e.getTargetException();
/*     */       
/* 501 */       if (targetException instanceof TransformerException)
/* 502 */         throw (TransformerException)targetException; 
/* 503 */       if (targetException != null) {
/* 504 */         throw new TransformerException(targetException.getMessage(), targetException);
/*     */       }
/* 506 */       throw new TransformerException(e.getMessage(), e); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 511 */       throw new TransformerException(e.getMessage(), e); }
/*     */ 
/*     */     
/* 514 */     if (result != null)
/*     */     {
/* 516 */       xpc.outputToResultTree(stylesheetTree, result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandlerJavaClass.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */