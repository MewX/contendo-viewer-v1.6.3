/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerializationUtils
/*     */ {
/*     */   public static <T extends Serializable> T clone(T object) {
/*  78 */     if (object == null) {
/*  79 */       return null;
/*     */     }
/*  81 */     byte[] objectData = serialize((Serializable)object);
/*  82 */     ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/*     */ 
/*     */     
/*  85 */     try { ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       try { Serializable serializable1 = (Serializable)in.readObject();
/*  93 */         Serializable serializable2 = serializable1;
/*     */         
/*  95 */         in.close(); return (T)serializable2; } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (ClassNotFoundException ex)
/*  96 */     { throw new SerializationException("ClassNotFoundException while reading cloned object data", ex); }
/*  97 */     catch (IOException ex)
/*  98 */     { throw new SerializationException("IOException while reading or closing cloned object data", ex); }
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
/*     */   public static <T extends Serializable> T roundtrip(T msg) {
/* 115 */     return (T)deserialize(serialize((Serializable)msg));
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
/*     */   public static void serialize(Serializable obj, OutputStream outputStream) {
/* 136 */     Validate.isTrue((outputStream != null), "The OutputStream must not be null", new Object[0]); 
/* 137 */     try { ObjectOutputStream out = new ObjectOutputStream(outputStream); 
/* 138 */       try { out.writeObject(obj);
/* 139 */         out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ex)
/* 140 */     { throw new SerializationException(ex); }
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
/*     */   public static byte[] serialize(Serializable obj) {
/* 153 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 154 */     serialize(obj, baos);
/* 155 */     return baos.toByteArray();
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
/*     */   public static <T> T deserialize(InputStream inputStream) {
/* 191 */     Validate.isTrue((inputStream != null), "The InputStream must not be null", new Object[0]); 
/* 192 */     try { ObjectInputStream in = new ObjectInputStream(inputStream);
/*     */       
/* 194 */       try { T obj = (T)in.readObject();
/* 195 */         T t1 = obj;
/* 196 */         in.close(); return t1; } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (ClassNotFoundException|IOException ex)
/* 197 */     { throw new SerializationException(ex); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T deserialize(byte[] objectData) {
/* 222 */     Validate.isTrue((objectData != null), "The byte[] must not be null", new Object[0]);
/* 223 */     return deserialize(new ByteArrayInputStream(objectData));
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
/*     */   static class ClassLoaderAwareObjectInputStream
/*     */     extends ObjectInputStream
/*     */   {
/* 240 */     private static final Map<String, Class<?>> primitiveTypes = new HashMap<>();
/*     */     private final ClassLoader classLoader;
/*     */     
/*     */     static {
/* 244 */       primitiveTypes.put("byte", byte.class);
/* 245 */       primitiveTypes.put("short", short.class);
/* 246 */       primitiveTypes.put("int", int.class);
/* 247 */       primitiveTypes.put("long", long.class);
/* 248 */       primitiveTypes.put("float", float.class);
/* 249 */       primitiveTypes.put("double", double.class);
/* 250 */       primitiveTypes.put("boolean", boolean.class);
/* 251 */       primitiveTypes.put("char", char.class);
/* 252 */       primitiveTypes.put("void", void.class);
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
/*     */     
/*     */     ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
/* 265 */       super(in);
/* 266 */       this.classLoader = classLoader;
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
/*     */     
/*     */     protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
/* 279 */       String name = desc.getName();
/*     */       try {
/* 281 */         return Class.forName(name, false, this.classLoader);
/* 282 */       } catch (ClassNotFoundException ex) {
/*     */         try {
/* 284 */           return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
/* 285 */         } catch (ClassNotFoundException cnfe) {
/* 286 */           Class<?> cls = primitiveTypes.get(name);
/* 287 */           if (cls != null) {
/* 288 */             return cls;
/*     */           }
/* 290 */           throw cnfe;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/SerializationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */