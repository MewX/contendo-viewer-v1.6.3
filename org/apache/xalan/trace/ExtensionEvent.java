/*    */ package org.apache.xalan.trace;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.Method;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
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
/*    */ public class ExtensionEvent
/*    */ {
/*    */   public static final int DEFAULT_CONSTRUCTOR = 0;
/*    */   public static final int METHOD = 1;
/*    */   public static final int CONSTRUCTOR = 2;
/*    */   public final int m_callType;
/*    */   public final TransformerImpl m_transformer;
/*    */   public final Object m_method;
/*    */   public final Object m_instance;
/*    */   public final Object[] m_arguments;
/*    */   
/*    */   public ExtensionEvent(TransformerImpl transformer, Method method, Object instance, Object[] arguments) {
/* 44 */     this.m_transformer = transformer;
/* 45 */     this.m_method = method;
/* 46 */     this.m_instance = instance;
/* 47 */     this.m_arguments = arguments;
/* 48 */     this.m_callType = 1;
/*    */   }
/*    */   
/*    */   public ExtensionEvent(TransformerImpl transformer, Constructor constructor, Object[] arguments) {
/* 52 */     this.m_transformer = transformer;
/* 53 */     this.m_instance = null;
/* 54 */     this.m_arguments = arguments;
/* 55 */     this.m_method = constructor;
/* 56 */     this.m_callType = 2;
/*    */   }
/*    */   
/*    */   public ExtensionEvent(TransformerImpl transformer, Class clazz) {
/* 60 */     this.m_transformer = transformer;
/* 61 */     this.m_instance = null;
/* 62 */     this.m_arguments = null;
/* 63 */     this.m_method = clazz;
/* 64 */     this.m_callType = 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/ExtensionEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */