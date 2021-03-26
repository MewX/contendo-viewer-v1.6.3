/*     */ package org.apache.xalan.xsltc.runtime;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CallFunction
/*     */ {
/*     */   public static String className;
/*     */   public static String methodName;
/*     */   public static int nArgs;
/*     */   public static Class clazz;
/*     */   
/*     */   public static String invokeMethod(String _className, String _methodName, Object[] _arguments) {
/*  44 */     className = _className;
/*  45 */     methodName = _methodName;
/*  46 */     int size = _arguments.length - 1;
/*  47 */     Object[] arguments = new Object[size];
/*  48 */     Object object = _arguments[0];
/*  49 */     clazz = null;
/*     */     
/*  51 */     try { clazz = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
/*  52 */       if (clazz == null)
/*  53 */         throw new RuntimeException("Couldn't load the class");  } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/*  56 */       throw new RuntimeException("Couldn't load the class"); }
/*     */ 
/*     */     
/*  59 */     for (int i = 0, j = 1; i < size; i++, j++) {
/*  60 */       arguments[i] = _arguments[j];
/*     */     }
/*  62 */     nArgs = size;
/*  63 */     if (methodName != null) {
/*     */       Method method;
/*  65 */       if ((method = findMethods(arguments)) == null) {
/*  66 */         throw new RuntimeException("Method not found");
/*     */       }
/*     */ 
/*     */       
/*  70 */       try { Object obj = method.invoke(object, arguments);
/*  71 */         return obj.toString(); } catch (IllegalAccessException e)
/*     */       
/*     */       { 
/*  74 */         throw new RuntimeException("Error: Method is inaccessible"); } catch (IllegalArgumentException e)
/*     */       
/*     */       { 
/*  77 */         throw new RuntimeException("Error: Number of actual and formal argument differ "); } catch (InvocationTargetException e)
/*     */       
/*     */       { 
/*  80 */         throw new RuntimeException("Error: underlying constructor throws an exception "); }
/*     */     
/*     */     } 
/*     */     
/*     */     Constructor constructor;
/*  85 */     if ((constructor = findConstructor(arguments)) == null) {
/*  86 */       throw new RuntimeException("Constructor not found");
/*     */     }
/*     */     
/*  89 */     try { Object obs = constructor.newInstance(arguments);
/*  90 */       return obs.toString(); } catch (InvocationTargetException e)
/*     */     
/*  92 */     { throw new RuntimeException("Error: constructor throws an exception "); } catch (IllegalAccessException e)
/*     */     
/*     */     { 
/*  95 */       throw new RuntimeException("Error: constructor is inaccessible"); } catch (IllegalArgumentException e)
/*     */     
/*     */     { 
/*  98 */       throw new RuntimeException("Error: Number of actual and formal argument differ "); } catch (InstantiationException e)
/*     */     
/*     */     { 
/* 101 */       throw new RuntimeException("Error: Class that declares the underlying constructor represents an abstract class"); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Constructor findConstructor(Object[] arguments) {
/* 111 */     Vector constructors = null;
/*     */ 
/*     */     
/* 114 */     Constructor[] c_constructors = (Constructor[])clazz.getConstructors();
/*     */     
/* 116 */     for (int i = 0; i < c_constructors.length; i++) {
/* 117 */       int mods = c_constructors[i].getModifiers();
/*     */       
/* 119 */       if (Modifier.isPublic(mods) && (c_constructors[i].getParameterTypes()).length == nArgs) {
/* 120 */         if (constructors == null) {
/* 121 */           constructors = new Vector();
/*     */         }
/* 123 */         constructors.addElement(c_constructors[i]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 128 */     if (constructors == null)
/*     */     {
/* 130 */       throw new RuntimeException("CONSTRUCTOR_NOT_FOUND_ERR" + className + ":" + methodName);
/*     */     }
/*     */     
/* 133 */     int nConstructors = constructors.size();
/* 134 */     boolean accept = false;
/* 135 */     for (int j = 0; j < nConstructors; j++) {
/*     */       
/* 137 */       Constructor constructor = constructors.elementAt(j);
/* 138 */       Class[] paramTypes = constructor.getParameterTypes();
/*     */       
/* 140 */       for (int k = 0; k < nArgs; k++) {
/* 141 */         Class argumentClass = arguments[k].getClass();
/* 142 */         if (argumentClass == paramTypes[k]) {
/* 143 */           accept = true;
/*     */         }
/* 145 */         else if (argumentClass.isAssignableFrom(paramTypes[k])) {
/* 146 */           accept = true;
/*     */         } else {
/*     */           
/* 149 */           accept = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 153 */       if (accept)
/* 154 */         return constructor; 
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Method findMethods(Object[] arguments) {
/* 165 */     Vector methods = null;
/*     */     
/* 167 */     Method[] m_methods = clazz.getMethods();
/*     */     
/* 169 */     for (int i = 0; i < m_methods.length; i++) {
/* 170 */       int mods = m_methods[i].getModifiers();
/*     */       
/* 172 */       if (Modifier.isPublic(mods) && m_methods[i].getName().equals(methodName) && (m_methods[i].getParameterTypes()).length == nArgs) {
/*     */ 
/*     */         
/* 175 */         if (methods == null) {
/* 176 */           methods = new Vector();
/*     */         }
/* 178 */         methods.addElement(m_methods[i]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 183 */     if (methods == null)
/*     */     {
/* 185 */       throw new RuntimeException("METHOD_NOT_FOUND_ERR" + className + ":" + methodName);
/*     */     }
/* 187 */     int nMethods = methods.size();
/* 188 */     boolean accept = false;
/* 189 */     for (int j = 0; j < nMethods; j++) {
/*     */       
/* 191 */       Method method = methods.elementAt(j);
/* 192 */       Class[] paramTypes = method.getParameterTypes();
/*     */       
/* 194 */       for (int k = 0; k < nArgs; k++) {
/* 195 */         Class argumentClass = arguments[k].getClass();
/* 196 */         if (argumentClass == paramTypes[k]) {
/* 197 */           accept = true;
/*     */         }
/* 199 */         else if (argumentClass.isAssignableFrom(paramTypes[k])) {
/* 200 */           accept = true;
/*     */         }
/* 202 */         else if (paramTypes[k].isPrimitive()) {
/* 203 */           arguments[k] = isPrimitive(paramTypes[k], arguments[k]);
/* 204 */           accept = true;
/*     */         } else {
/*     */           
/* 207 */           accept = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 212 */       if (accept) {
/* 213 */         return method;
/*     */       }
/*     */     } 
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object isPrimitive(Class paramType, Object argument) {
/* 221 */     if (argument.getClass() == Integer.class)
/* 222 */       return typeCast(paramType, (Integer)argument); 
/* 223 */     if (argument.getClass() == Float.class)
/* 224 */       return typeCast(paramType, (Float)argument); 
/* 225 */     if (argument.getClass() == Double.class)
/* 226 */       return typeCast(paramType, (Double)argument); 
/* 227 */     if (argument.getClass() == Long.class)
/* 228 */       return typeCast(paramType, (Long)argument); 
/* 229 */     if (argument.getClass() == Boolean.class)
/* 230 */       return argument; 
/* 231 */     if (argument.getClass() == Byte.class) {
/* 232 */       return argument;
/*     */     }
/* 234 */     return null;
/*     */   }
/*     */   
/*     */   static Object typeCast(Class paramType, Double object) {
/* 238 */     if (paramType == long.class)
/* 239 */       return new Long(object.longValue()); 
/* 240 */     if (paramType == int.class)
/* 241 */       return new Integer(object.intValue()); 
/* 242 */     if (paramType == float.class)
/* 243 */       return new Float(object.floatValue()); 
/* 244 */     if (paramType == short.class)
/* 245 */       return new Short(object.shortValue()); 
/* 246 */     if (paramType == byte.class) {
/* 247 */       return new Byte(object.byteValue());
/*     */     }
/* 249 */     return object;
/*     */   }
/*     */   
/*     */   static Object typeCast(Class paramType, Long object) {
/* 253 */     if (paramType == int.class)
/* 254 */       return new Integer(object.intValue()); 
/* 255 */     if (paramType == float.class)
/* 256 */       return new Float(object.floatValue()); 
/* 257 */     if (paramType == short.class)
/* 258 */       return new Short(object.shortValue()); 
/* 259 */     if (paramType == byte.class) {
/* 260 */       return new Byte(object.byteValue());
/*     */     }
/* 262 */     return object;
/*     */   }
/*     */   
/*     */   static Object typeCast(Class paramType, Integer object) {
/* 266 */     if (paramType == double.class)
/* 267 */       return new Double(object.doubleValue()); 
/* 268 */     if (paramType == float.class)
/* 269 */       return new Float(object.floatValue()); 
/* 270 */     if (paramType == short.class)
/* 271 */       return new Short(object.shortValue()); 
/* 272 */     if (paramType == byte.class) {
/* 273 */       return new Byte(object.byteValue());
/*     */     }
/* 275 */     return object;
/*     */   }
/*     */   
/*     */   static Object typeCast(Class paramType, Float object) {
/* 279 */     if (paramType == double.class)
/* 280 */       return new Double(object.doubleValue()); 
/* 281 */     if (paramType == int.class)
/* 282 */       return new Float(object.intValue()); 
/* 283 */     if (paramType == short.class)
/* 284 */       return new Short(object.shortValue()); 
/* 285 */     if (paramType == byte.class) {
/* 286 */       return new Byte(object.byteValue());
/*     */     }
/* 288 */     return object;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/CallFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */