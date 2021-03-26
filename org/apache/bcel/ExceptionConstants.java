/*     */ package org.apache.bcel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ExceptionConstants
/*     */ {
/*  66 */   public static final Class THROWABLE = (null.class$java$lang$Throwable == null) ? (null.class$java$lang$Throwable = null.class$("java.lang.Throwable")) : null.class$java$lang$Throwable;
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final Class RUNTIME_EXCEPTION = (null.class$java$lang$RuntimeException == null) ? (null.class$java$lang$RuntimeException = null.class$("java.lang.RuntimeException")) : null.class$java$lang$RuntimeException;
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static final Class LINKING_EXCEPTION = (null.class$java$lang$LinkageError == null) ? (null.class$java$lang$LinkageError = null.class$("java.lang.LinkageError")) : null.class$java$lang$LinkageError;
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final Class CLASS_CIRCULARITY_ERROR = (null.class$java$lang$ClassCircularityError == null) ? (null.class$java$lang$ClassCircularityError = null.class$("java.lang.ClassCircularityError")) : null.class$java$lang$ClassCircularityError;
/*  79 */   public static final Class CLASS_FORMAT_ERROR = (null.class$java$lang$ClassFormatError == null) ? (null.class$java$lang$ClassFormatError = null.class$("java.lang.ClassFormatError")) : null.class$java$lang$ClassFormatError;
/*  80 */   public static final Class EXCEPTION_IN_INITIALIZER_ERROR = (null.class$java$lang$ExceptionInInitializerError == null) ? (null.class$java$lang$ExceptionInInitializerError = null.class$("java.lang.ExceptionInInitializerError")) : null.class$java$lang$ExceptionInInitializerError;
/*  81 */   public static final Class INCOMPATIBLE_CLASS_CHANGE_ERROR = (null.class$java$lang$IncompatibleClassChangeError == null) ? (null.class$java$lang$IncompatibleClassChangeError = null.class$("java.lang.IncompatibleClassChangeError")) : null.class$java$lang$IncompatibleClassChangeError;
/*  82 */   public static final Class ABSTRACT_METHOD_ERROR = (null.class$java$lang$AbstractMethodError == null) ? (null.class$java$lang$AbstractMethodError = null.class$("java.lang.AbstractMethodError")) : null.class$java$lang$AbstractMethodError;
/*  83 */   public static final Class ILLEGAL_ACCESS_ERROR = (null.class$java$lang$IllegalAccessError == null) ? (null.class$java$lang$IllegalAccessError = null.class$("java.lang.IllegalAccessError")) : null.class$java$lang$IllegalAccessError;
/*  84 */   public static final Class INSTANTIATION_ERROR = (null.class$java$lang$InstantiationError == null) ? (null.class$java$lang$InstantiationError = null.class$("java.lang.InstantiationError")) : null.class$java$lang$InstantiationError;
/*  85 */   public static final Class NO_SUCH_FIELD_ERROR = (null.class$java$lang$NoSuchFieldError == null) ? (null.class$java$lang$NoSuchFieldError = null.class$("java.lang.NoSuchFieldError")) : null.class$java$lang$NoSuchFieldError;
/*  86 */   public static final Class NO_SUCH_METHOD_ERROR = (null.class$java$lang$NoSuchMethodError == null) ? (null.class$java$lang$NoSuchMethodError = null.class$("java.lang.NoSuchMethodError")) : null.class$java$lang$NoSuchMethodError;
/*  87 */   public static final Class NO_CLASS_DEF_FOUND_ERROR = (null.class$java$lang$NoClassDefFoundError == null) ? (null.class$java$lang$NoClassDefFoundError = null.class$("java.lang.NoClassDefFoundError")) : null.class$java$lang$NoClassDefFoundError;
/*  88 */   public static final Class UNSATISFIED_LINK_ERROR = (null.class$java$lang$UnsatisfiedLinkError == null) ? (null.class$java$lang$UnsatisfiedLinkError = null.class$("java.lang.UnsatisfiedLinkError")) : null.class$java$lang$UnsatisfiedLinkError;
/*  89 */   public static final Class VERIFY_ERROR = (null.class$java$lang$VerifyError == null) ? (null.class$java$lang$VerifyError = null.class$("java.lang.VerifyError")) : null.class$java$lang$VerifyError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static final Class NULL_POINTER_EXCEPTION = (null.class$java$lang$NullPointerException == null) ? (null.class$java$lang$NullPointerException = null.class$("java.lang.NullPointerException")) : null.class$java$lang$NullPointerException;
/*  97 */   public static final Class ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = (null.class$java$lang$ArrayIndexOutOfBoundsException == null) ? (null.class$java$lang$ArrayIndexOutOfBoundsException = null.class$("java.lang.ArrayIndexOutOfBoundsException")) : null.class$java$lang$ArrayIndexOutOfBoundsException;
/*  98 */   public static final Class ARITHMETIC_EXCEPTION = (null.class$java$lang$ArithmeticException == null) ? (null.class$java$lang$ArithmeticException = null.class$("java.lang.ArithmeticException")) : null.class$java$lang$ArithmeticException;
/*  99 */   public static final Class NEGATIVE_ARRAY_SIZE_EXCEPTION = (null.class$java$lang$NegativeArraySizeException == null) ? (null.class$java$lang$NegativeArraySizeException = null.class$("java.lang.NegativeArraySizeException")) : null.class$java$lang$NegativeArraySizeException;
/* 100 */   public static final Class CLASS_CAST_EXCEPTION = (null.class$java$lang$ClassCastException == null) ? (null.class$java$lang$ClassCastException = null.class$("java.lang.ClassCastException")) : null.class$java$lang$ClassCastException;
/* 101 */   public static final Class ILLEGAL_MONITOR_STATE = (null.class$java$lang$IllegalMonitorStateException == null) ? (null.class$java$lang$IllegalMonitorStateException = null.class$("java.lang.IllegalMonitorStateException")) : null.class$java$lang$IllegalMonitorStateException;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static final Class[] EXCS_CLASS_AND_INTERFACE_RESOLUTION = new Class[] { NO_CLASS_DEF_FOUND_ERROR, CLASS_FORMAT_ERROR, VERIFY_ERROR, ABSTRACT_METHOD_ERROR, EXCEPTION_IN_INITIALIZER_ERROR, ILLEGAL_ACCESS_ERROR };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static final Class[] EXCS_FIELD_AND_METHOD_RESOLUTION = new Class[] { NO_SUCH_FIELD_ERROR, ILLEGAL_ACCESS_ERROR, NO_SUCH_METHOD_ERROR };
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final Class[] EXCS_INTERFACE_METHOD_RESOLUTION = new Class[0];
/* 116 */   public static final Class[] EXCS_STRING_RESOLUTION = new Class[0];
/*     */ 
/*     */   
/* 119 */   public static final Class[] EXCS_ARRAY_EXCEPTION = new Class[] { NULL_POINTER_EXCEPTION, ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/ExceptionConstants.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */