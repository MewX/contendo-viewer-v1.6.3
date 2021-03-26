/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
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
/*     */ public class ExtensionHandlerJavaPackage
/*     */   extends ExtensionHandlerJava
/*     */ {
/*     */   public ExtensionHandlerJavaPackage(String namespaceUri, String scriptLang, String className) {
/*  77 */     super(namespaceUri, scriptLang, className);
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
/*     */   public boolean isFunctionAvailable(String function) {
/*     */     try {
/*  97 */       String fullName = this.m_className + function;
/*  98 */       int lastDot = fullName.lastIndexOf(".");
/*  99 */       if (lastDot >= 0) {
/*     */         
/* 101 */         Class myClass = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
/* 102 */         Method[] methods = myClass.getMethods();
/* 103 */         int nMethods = methods.length;
/* 104 */         function = fullName.substring(lastDot + 1);
/* 105 */         for (int i = 0; i < nMethods; i++) {
/*     */           
/* 107 */           if (methods[i].getName().equals(function)) {
/* 108 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } catch (ClassNotFoundException classNotFoundException) {}
/*     */     
/* 114 */     return false;
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
/*     */   public boolean isElementAvailable(String element) {
/*     */     try {
/* 130 */       String fullName = this.m_className + element;
/* 131 */       int lastDot = fullName.lastIndexOf(".");
/* 132 */       if (lastDot >= 0) {
/*     */         
/* 134 */         Class myClass = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
/* 135 */         Method[] methods = myClass.getMethods();
/* 136 */         int nMethods = methods.length;
/* 137 */         element = fullName.substring(lastDot + 1);
/* 138 */         for (int i = 0; i < nMethods; i++) {
/*     */           
/* 140 */           if (methods[i].getName().equals(element)) {
/*     */             
/* 142 */             Class[] paramTypes = methods[i].getParameterTypes();
/* 143 */             if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom(XSLProcessorContext.class) && paramTypes[1].isAssignableFrom(ElemExtensionCall.class))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 149 */               return true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } catch (ClassNotFoundException classNotFoundException) {}
/*     */ 
/*     */     
/* 157 */     return false;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object callFunction(String funcName, Vector args, Object methodKey, ExpressionContext exprContext) throws TransformerException {
/* 211 */     int lastDot = funcName.lastIndexOf(".");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     try { if (funcName.endsWith(".new")) {
/*     */         Class classObj;
/* 221 */         Object[] methodArgs = new Object[args.size()];
/* 222 */         Object[][] convertedArgs = new Object[1][];
/* 223 */         for (int j = 0; j < methodArgs.length; j++)
/*     */         {
/* 225 */           methodArgs[j] = args.elementAt(j);
/*     */         }
/* 227 */         Constructor c = (Constructor)getFromCache(methodKey, null, methodArgs);
/* 228 */         if (c != null) {
/*     */ 
/*     */           
/*     */           try { 
/* 232 */             Class[] paramTypes = c.getParameterTypes();
/* 233 */             MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
/* 234 */             return c.newInstance(convertedArgs[0]); } catch (InvocationTargetException ite)
/*     */           
/*     */           { 
/*     */             
/* 238 */             throw ite; } catch (Exception exception) {}
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         String className = this.m_className + funcName.substring(0, lastDot);
/*     */ 
/*     */         
/* 248 */         try { classObj = ExtensionHandler.getClassForName(className); } catch (ClassNotFoundException e)
/*     */         
/*     */         { 
/*     */           
/* 252 */           throw new TransformerException(e); }
/*     */         
/* 254 */         c = MethodResolver.getConstructor(classObj, methodArgs, convertedArgs, exprContext);
/*     */ 
/*     */ 
/*     */         
/* 258 */         putToCache(methodKey, null, methodArgs, c);
/* 259 */         if (TransformerImpl.S_DEBUG) {
/* 260 */           Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 261 */           trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
/*     */ 
/*     */           
/* 264 */           try { result = c.newInstance(convertedArgs[0]); } catch (Exception e)
/*     */           
/* 266 */           { throw e; }
/*     */           finally
/* 268 */           { trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, c, convertedArgs[0])); }
/*     */           
/* 270 */           return result;
/*     */         } 
/* 272 */         return c.newInstance(convertedArgs[0]);
/*     */       } 
/*     */       
/* 275 */       if (-1 != lastDot) {
/*     */         Class clazz1;
/* 277 */         Object[] arrayOfObject2 = new Object[args.size()];
/* 278 */         Object[][] arrayOfObject3 = new Object[1][];
/* 279 */         for (int j = 0; j < arrayOfObject2.length; j++)
/*     */         {
/* 281 */           arrayOfObject2[j] = args.elementAt(j);
/*     */         }
/* 283 */         Method method = (Method)getFromCache(methodKey, null, arrayOfObject2);
/* 284 */         if (method != null && !TransformerImpl.S_DEBUG) {
/*     */ 
/*     */           
/*     */           try { 
/* 288 */             Class[] arrayOfClass = method.getParameterTypes();
/* 289 */             MethodResolver.convertParams(arrayOfObject2, arrayOfObject3, arrayOfClass, exprContext);
/* 290 */             return method.invoke(null, arrayOfObject3[0]); } catch (InvocationTargetException ite)
/*     */           
/*     */           { 
/*     */             
/* 294 */             throw ite; } catch (Exception exception) {}
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 301 */         String str1 = this.m_className + funcName.substring(0, lastDot);
/* 302 */         String methodName = funcName.substring(lastDot + 1);
/*     */ 
/*     */         
/* 305 */         try { clazz1 = ExtensionHandler.getClassForName(str1); } catch (ClassNotFoundException e)
/*     */         
/*     */         { 
/*     */           
/* 309 */           throw new TransformerException(e); }
/*     */         
/* 311 */         method = MethodResolver.getMethod(clazz1, methodName, arrayOfObject2, arrayOfObject3, exprContext, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 317 */         putToCache(methodKey, null, arrayOfObject2, method);
/* 318 */         if (TransformerImpl.S_DEBUG) {
/* 319 */           Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 320 */           trans.getTraceManager().fireExtensionEvent(method, null, arrayOfObject3[0]);
/*     */ 
/*     */           
/* 323 */           try { result = method.invoke(null, arrayOfObject3[0]); } catch (Exception e)
/*     */           
/* 325 */           { throw e; }
/*     */           finally
/* 327 */           { trans.getTraceManager().fireExtensionEndEvent(method, null, arrayOfObject3[0]); }
/*     */           
/* 329 */           return result;
/*     */         } 
/*     */         
/* 332 */         return method.invoke(null, arrayOfObject3[0]);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 337 */       if (args.size() < 1)
/*     */       {
/* 339 */         throw new TransformerException(XSLMessages.createMessage("ER_INSTANCE_MTHD_CALL_REQUIRES", new Object[] { funcName }));
/*     */       }
/*     */       
/* 342 */       Object targetObject = args.elementAt(0);
/* 343 */       if (targetObject instanceof XObject)
/* 344 */         targetObject = ((XObject)targetObject).object(); 
/* 345 */       Object[] arrayOfObject = new Object[args.size() - 1];
/* 346 */       Object[][] arrayOfObject1 = new Object[1][];
/* 347 */       for (int i = 0; i < arrayOfObject.length; i++)
/*     */       {
/* 349 */         arrayOfObject[i] = args.elementAt(i + 1);
/*     */       }
/* 351 */       Method m = (Method)getFromCache(methodKey, targetObject, arrayOfObject);
/* 352 */       if (m != null) {
/*     */ 
/*     */         
/*     */         try { 
/* 356 */           Class[] arrayOfClass = m.getParameterTypes();
/* 357 */           MethodResolver.convertParams(arrayOfObject, arrayOfObject1, arrayOfClass, exprContext);
/* 358 */           return m.invoke(targetObject, arrayOfObject1[0]); } catch (InvocationTargetException ite)
/*     */         
/*     */         { 
/*     */           
/* 362 */           throw ite; } catch (Exception exception) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 369 */       Class clazz = targetObject.getClass();
/* 370 */       m = MethodResolver.getMethod(clazz, funcName, arrayOfObject, arrayOfObject1, exprContext, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 376 */       putToCache(methodKey, targetObject, arrayOfObject, m);
/* 377 */       if (TransformerImpl.S_DEBUG) {
/* 378 */         Object result; TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
/* 379 */         trans.getTraceManager().fireExtensionEvent(m, targetObject, arrayOfObject1[0]);
/*     */ 
/*     */         
/* 382 */         try { result = m.invoke(targetObject, arrayOfObject1[0]); } catch (Exception e)
/*     */         
/* 384 */         { throw e; }
/*     */         finally
/* 386 */         { trans.getTraceManager().fireExtensionEndEvent(m, targetObject, arrayOfObject1[0]); }
/*     */         
/* 388 */         return result;
/*     */       } 
/* 390 */       return m.invoke(targetObject, arrayOfObject1[0]); } catch (InvocationTargetException ite)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 395 */       Throwable resultException = ite;
/* 396 */       Throwable targetException = ite.getTargetException();
/*     */       
/* 398 */       if (targetException instanceof TransformerException)
/* 399 */         throw (TransformerException)targetException; 
/* 400 */       if (targetException != null) {
/* 401 */         resultException = targetException;
/*     */       }
/* 403 */       throw new TransformerException(resultException); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 408 */       throw new TransformerException(e); }
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
/* 426 */     return callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
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
/*     */   public void processElement(String localPart, ElemTemplateElement element, TransformerImpl transformer, Stylesheet stylesheetTree, Object methodKey) throws TransformerException, IOException {
/* 456 */     Object result = null;
/*     */ 
/*     */     
/* 459 */     Method m = (Method)getFromCache(methodKey, null, null);
/* 460 */     if (null == m) {
/*     */       
/*     */       try { Class classObj;
/*     */         
/* 464 */         String fullName = this.m_className + localPart;
/* 465 */         int lastDot = fullName.lastIndexOf(".");
/* 466 */         if (lastDot < 0) {
/* 467 */           throw new TransformerException(XSLMessages.createMessage("ER_INVALID_ELEMENT_NAME", new Object[] { fullName }));
/*     */         }
/*     */         
/* 470 */         try { classObj = ExtensionHandler.getClassForName(fullName.substring(0, lastDot)); } catch (ClassNotFoundException e)
/*     */         
/*     */         { 
/*     */           
/* 474 */           throw new TransformerException(e); }
/*     */         
/* 476 */         localPart = fullName.substring(lastDot + 1);
/* 477 */         m = MethodResolver.getElementMethod(classObj, localPart);
/* 478 */         if (!Modifier.isStatic(m.getModifiers()))
/* 479 */           throw new TransformerException(XSLMessages.createMessage("ER_ELEMENT_NAME_METHOD_STATIC", new Object[] { fullName }));  } catch (Exception e)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 484 */         throw new TransformerException(e); }
/*     */       
/* 486 */       putToCache(methodKey, null, null, m);
/*     */     } 
/*     */     
/* 489 */     XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 494 */     try { if (TransformerImpl.S_DEBUG)
/* 495 */       { transformer.getTraceManager().fireExtensionEvent(m, null, new Object[] { xpc, element });
/*     */         
/* 497 */         try { result = m.invoke(null, new Object[] { xpc, element }); } catch (Exception e)
/*     */         
/* 499 */         { throw e; }
/*     */         finally
/* 501 */         { transformer.getTraceManager().fireExtensionEndEvent(m, null, new Object[] { xpc, element }); }
/*     */          }
/*     */       else
/* 504 */       { result = m.invoke(null, new Object[] { xpc, element }); }  } catch (InvocationTargetException ite)
/*     */     
/*     */     { 
/*     */       
/* 508 */       Throwable resultException = ite;
/* 509 */       Throwable targetException = ite.getTargetException();
/*     */       
/* 511 */       if (targetException instanceof TransformerException)
/* 512 */         throw (TransformerException)targetException; 
/* 513 */       if (targetException != null) {
/* 514 */         resultException = targetException;
/*     */       }
/* 516 */       throw new TransformerException(resultException); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 521 */       throw new TransformerException(e); }
/*     */ 
/*     */     
/* 524 */     if (result != null)
/*     */     {
/* 526 */       xpc.outputToResultTree(stylesheetTree, result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandlerJavaPackage.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */