/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationUtils
/*     */ {
/*  50 */   private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle()
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected String getShortClassName(Class<?> cls) {
/*  72 */         Class<? extends Annotation> annotationType = null;
/*  73 */         for (Class<?> iface : ClassUtils.getAllInterfaces(cls)) {
/*  74 */           if (Annotation.class.isAssignableFrom(iface)) {
/*     */ 
/*     */             
/*  77 */             Class<? extends Annotation> found = (Class)iface;
/*  78 */             annotationType = found;
/*     */             break;
/*     */           } 
/*     */         } 
/*  82 */         return (new StringBuilder((annotationType == null) ? "" : annotationType.getName()))
/*  83 */           .insert(0, '@').toString();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
/*  91 */         if (value instanceof Annotation) {
/*  92 */           value = AnnotationUtils.toString((Annotation)value);
/*     */         }
/*  94 */         super.appendDetail(buffer, fieldName, value);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean equals(Annotation a1, Annotation a2) {
/* 122 */     if (a1 == a2) {
/* 123 */       return true;
/*     */     }
/* 125 */     if (a1 == null || a2 == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     Class<? extends Annotation> type = a1.annotationType();
/* 129 */     Class<? extends Annotation> type2 = a2.annotationType();
/* 130 */     Validate.notNull(type, "Annotation %s with null annotationType()", new Object[] { a1 });
/* 131 */     Validate.notNull(type2, "Annotation %s with null annotationType()", new Object[] { a2 });
/* 132 */     if (!type.equals(type2)) {
/* 133 */       return false;
/*     */     }
/*     */     try {
/* 136 */       for (Method m : type.getDeclaredMethods()) {
/* 137 */         if ((m.getParameterTypes()).length == 0 && 
/* 138 */           isValidAnnotationMemberType(m.getReturnType())) {
/* 139 */           Object v1 = m.invoke(a1, new Object[0]);
/* 140 */           Object v2 = m.invoke(a2, new Object[0]);
/* 141 */           if (!memberEquals(m.getReturnType(), v1, v2)) {
/* 142 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/* 146 */     } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException ex) {
/* 147 */       return false;
/*     */     } 
/* 149 */     return true;
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
/*     */   public static int hashCode(Annotation a) {
/* 165 */     int result = 0;
/* 166 */     Class<? extends Annotation> type = a.annotationType();
/* 167 */     for (Method m : type.getDeclaredMethods()) {
/*     */       try {
/* 169 */         Object value = m.invoke(a, new Object[0]);
/* 170 */         if (value == null) {
/* 171 */           throw new IllegalStateException(
/* 172 */               String.format("Annotation method %s returned null", new Object[] { m }));
/*     */         }
/* 174 */         result += hashMember(m.getName(), value);
/* 175 */       } catch (RuntimeException ex) {
/* 176 */         throw ex;
/* 177 */       } catch (Exception ex) {
/* 178 */         throw new RuntimeException(ex);
/*     */       } 
/*     */     } 
/* 181 */     return result;
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
/*     */   public static String toString(Annotation a) {
/* 193 */     ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);
/* 194 */     for (Method m : a.annotationType().getDeclaredMethods()) {
/* 195 */       if ((m.getParameterTypes()).length <= 0)
/*     */         
/*     */         try {
/*     */           
/* 199 */           builder.append(m.getName(), m.invoke(a, new Object[0]));
/* 200 */         } catch (RuntimeException ex) {
/* 201 */           throw ex;
/* 202 */         } catch (Exception ex) {
/* 203 */           throw new RuntimeException(ex);
/*     */         }  
/*     */     } 
/* 206 */     return builder.build();
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
/*     */   public static boolean isValidAnnotationMemberType(Class<?> type) {
/* 221 */     if (type == null) {
/* 222 */       return false;
/*     */     }
/* 224 */     if (type.isArray()) {
/* 225 */       type = type.getComponentType();
/*     */     }
/* 227 */     return (type.isPrimitive() || type.isEnum() || type.isAnnotation() || String.class
/* 228 */       .equals(type) || Class.class.equals(type));
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
/*     */   private static int hashMember(String name, Object value) {
/* 240 */     int part1 = name.hashCode() * 127;
/* 241 */     if (value.getClass().isArray()) {
/* 242 */       return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
/*     */     }
/* 244 */     if (value instanceof Annotation) {
/* 245 */       return part1 ^ hashCode((Annotation)value);
/*     */     }
/* 247 */     return part1 ^ value.hashCode();
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
/*     */   private static boolean memberEquals(Class<?> type, Object o1, Object o2) {
/* 261 */     if (o1 == o2) {
/* 262 */       return true;
/*     */     }
/* 264 */     if (o1 == null || o2 == null) {
/* 265 */       return false;
/*     */     }
/* 267 */     if (type.isArray()) {
/* 268 */       return arrayMemberEquals(type.getComponentType(), o1, o2);
/*     */     }
/* 270 */     if (type.isAnnotation()) {
/* 271 */       return equals((Annotation)o1, (Annotation)o2);
/*     */     }
/* 273 */     return o1.equals(o2);
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
/*     */   private static boolean arrayMemberEquals(Class<?> componentType, Object o1, Object o2) {
/* 285 */     if (componentType.isAnnotation()) {
/* 286 */       return annotationArrayMemberEquals((Annotation[])o1, (Annotation[])o2);
/*     */     }
/* 288 */     if (componentType.equals(byte.class)) {
/* 289 */       return Arrays.equals((byte[])o1, (byte[])o2);
/*     */     }
/* 291 */     if (componentType.equals(short.class)) {
/* 292 */       return Arrays.equals((short[])o1, (short[])o2);
/*     */     }
/* 294 */     if (componentType.equals(int.class)) {
/* 295 */       return Arrays.equals((int[])o1, (int[])o2);
/*     */     }
/* 297 */     if (componentType.equals(char.class)) {
/* 298 */       return Arrays.equals((char[])o1, (char[])o2);
/*     */     }
/* 300 */     if (componentType.equals(long.class)) {
/* 301 */       return Arrays.equals((long[])o1, (long[])o2);
/*     */     }
/* 303 */     if (componentType.equals(float.class)) {
/* 304 */       return Arrays.equals((float[])o1, (float[])o2);
/*     */     }
/* 306 */     if (componentType.equals(double.class)) {
/* 307 */       return Arrays.equals((double[])o1, (double[])o2);
/*     */     }
/* 309 */     if (componentType.equals(boolean.class)) {
/* 310 */       return Arrays.equals((boolean[])o1, (boolean[])o2);
/*     */     }
/* 312 */     return Arrays.equals((Object[])o1, (Object[])o2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean annotationArrayMemberEquals(Annotation[] a1, Annotation[] a2) {
/* 323 */     if (a1.length != a2.length) {
/* 324 */       return false;
/*     */     }
/* 326 */     for (int i = 0; i < a1.length; i++) {
/* 327 */       if (!equals(a1[i], a2[i])) {
/* 328 */         return false;
/*     */       }
/*     */     } 
/* 331 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int arrayMemberHash(Class<?> componentType, Object o) {
/* 342 */     if (componentType.equals(byte.class)) {
/* 343 */       return Arrays.hashCode((byte[])o);
/*     */     }
/* 345 */     if (componentType.equals(short.class)) {
/* 346 */       return Arrays.hashCode((short[])o);
/*     */     }
/* 348 */     if (componentType.equals(int.class)) {
/* 349 */       return Arrays.hashCode((int[])o);
/*     */     }
/* 351 */     if (componentType.equals(char.class)) {
/* 352 */       return Arrays.hashCode((char[])o);
/*     */     }
/* 354 */     if (componentType.equals(long.class)) {
/* 355 */       return Arrays.hashCode((long[])o);
/*     */     }
/* 357 */     if (componentType.equals(float.class)) {
/* 358 */       return Arrays.hashCode((float[])o);
/*     */     }
/* 360 */     if (componentType.equals(double.class)) {
/* 361 */       return Arrays.hashCode((double[])o);
/*     */     }
/* 363 */     if (componentType.equals(boolean.class)) {
/* 364 */       return Arrays.hashCode((boolean[])o);
/*     */     }
/* 366 */     return Arrays.hashCode((Object[])o);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/AnnotationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */