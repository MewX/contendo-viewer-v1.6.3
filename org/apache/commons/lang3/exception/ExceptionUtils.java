/*     */ package org.apache.commons.lang3.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExceptionUtils
/*     */ {
/*     */   static final String WRAPPED_MARKER = " [wrapped] ";
/*  54 */   private static final String[] CAUSE_METHOD_NAMES = new String[] { "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static String[] getDefaultCauseMethodNames() {
/*  91 */     return (String[])ArrayUtils.clone((Object[])CAUSE_METHOD_NAMES);
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
/*     */   @Deprecated
/*     */   public static Throwable getCause(Throwable throwable) {
/* 124 */     return getCause(throwable, null);
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
/*     */   @Deprecated
/*     */   public static Throwable getCause(Throwable throwable, String[] methodNames) {
/* 142 */     if (throwable == null) {
/* 143 */       return null;
/*     */     }
/*     */     
/* 146 */     if (methodNames == null) {
/* 147 */       Throwable cause = throwable.getCause();
/* 148 */       if (cause != null) {
/* 149 */         return cause;
/*     */       }
/*     */       
/* 152 */       methodNames = CAUSE_METHOD_NAMES;
/*     */     } 
/*     */     
/* 155 */     for (String methodName : methodNames) {
/* 156 */       if (methodName != null) {
/* 157 */         Throwable legacyCause = getCauseUsingMethodName(throwable, methodName);
/* 158 */         if (legacyCause != null) {
/* 159 */           return legacyCause;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     return null;
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
/*     */   public static Throwable getRootCause(Throwable throwable) {
/* 185 */     List<Throwable> list = getThrowableList(throwable);
/* 186 */     return list.isEmpty() ? null : list.get(list.size() - 1);
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
/*     */   private static Throwable getCauseUsingMethodName(Throwable throwable, String methodName) {
/* 198 */     Method method = null;
/*     */     try {
/* 200 */       method = throwable.getClass().getMethod(methodName, new Class[0]);
/* 201 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (method != null && Throwable.class.isAssignableFrom(method.getReturnType())) {
/*     */       try {
/* 207 */         return (Throwable)method.invoke(throwable, new Object[0]);
/* 208 */       } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException illegalAccessException) {}
/*     */     }
/*     */ 
/*     */     
/* 212 */     return null;
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
/*     */   public static int getThrowableCount(Throwable throwable) {
/* 233 */     return getThrowableList(throwable).size();
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
/*     */   public static Throwable[] getThrowables(Throwable throwable) {
/* 256 */     List<Throwable> list = getThrowableList(throwable);
/* 257 */     return list.<Throwable>toArray(new Throwable[list.size()]);
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
/*     */   public static List<Throwable> getThrowableList(Throwable throwable) {
/* 280 */     List<Throwable> list = new ArrayList<>();
/* 281 */     while (throwable != null && !list.contains(throwable)) {
/* 282 */       list.add(throwable);
/* 283 */       throwable = throwable.getCause();
/*     */     } 
/* 285 */     return list;
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
/*     */   public static int indexOfThrowable(Throwable throwable, Class<?> clazz) {
/* 304 */     return indexOf(throwable, clazz, 0, false);
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
/*     */   public static int indexOfThrowable(Throwable throwable, Class<?> clazz, int fromIndex) {
/* 327 */     return indexOf(throwable, clazz, fromIndex, false);
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
/*     */   public static int indexOfType(Throwable throwable, Class<?> type) {
/* 347 */     return indexOf(throwable, type, 0, true);
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
/*     */   public static int indexOfType(Throwable throwable, Class<?> type, int fromIndex) {
/* 371 */     return indexOf(throwable, type, fromIndex, true);
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
/*     */   private static int indexOf(Throwable throwable, Class<?> type, int fromIndex, boolean subclass) {
/* 386 */     if (throwable == null || type == null) {
/* 387 */       return -1;
/*     */     }
/* 389 */     if (fromIndex < 0) {
/* 390 */       fromIndex = 0;
/*     */     }
/* 392 */     Throwable[] throwables = getThrowables(throwable);
/* 393 */     if (fromIndex >= throwables.length) {
/* 394 */       return -1;
/*     */     }
/* 396 */     if (subclass) {
/* 397 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 398 */         if (type.isAssignableFrom(throwables[i].getClass())) {
/* 399 */           return i;
/*     */         }
/*     */       } 
/*     */     } else {
/* 403 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 404 */         if (type.equals(throwables[i].getClass())) {
/* 405 */           return i;
/*     */         }
/*     */       } 
/*     */     } 
/* 409 */     return -1;
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
/*     */   public static void printRootCauseStackTrace(Throwable throwable) {
/* 432 */     printRootCauseStackTrace(throwable, System.err);
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
/*     */   public static void printRootCauseStackTrace(Throwable throwable, PrintStream stream) {
/* 455 */     if (throwable == null) {
/*     */       return;
/*     */     }
/* 458 */     Validate.isTrue((stream != null), "The PrintStream must not be null", new Object[0]);
/* 459 */     String[] trace = getRootCauseStackTrace(throwable);
/* 460 */     for (String element : trace) {
/* 461 */       stream.println(element);
/*     */     }
/* 463 */     stream.flush();
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
/*     */   public static void printRootCauseStackTrace(Throwable throwable, PrintWriter writer) {
/* 486 */     if (throwable == null) {
/*     */       return;
/*     */     }
/* 489 */     Validate.isTrue((writer != null), "The PrintWriter must not be null", new Object[0]);
/* 490 */     String[] trace = getRootCauseStackTrace(throwable);
/* 491 */     for (String element : trace) {
/* 492 */       writer.println(element);
/*     */     }
/* 494 */     writer.flush();
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
/*     */   public static String[] getRootCauseStackTrace(Throwable throwable) {
/* 512 */     if (throwable == null) {
/* 513 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*     */     }
/* 515 */     Throwable[] throwables = getThrowables(throwable);
/* 516 */     int count = throwables.length;
/* 517 */     List<String> frames = new ArrayList<>();
/* 518 */     List<String> nextTrace = getStackFrameList(throwables[count - 1]);
/* 519 */     for (int i = count; --i >= 0; ) {
/* 520 */       List<String> trace = nextTrace;
/* 521 */       if (i != 0) {
/* 522 */         nextTrace = getStackFrameList(throwables[i - 1]);
/* 523 */         removeCommonFrames(trace, nextTrace);
/*     */       } 
/* 525 */       if (i == count - 1) {
/* 526 */         frames.add(throwables[i].toString());
/*     */       } else {
/* 528 */         frames.add(" [wrapped] " + throwables[i].toString());
/*     */       } 
/* 530 */       frames.addAll(trace);
/*     */     } 
/* 532 */     return frames.<String>toArray(new String[frames.size()]);
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
/*     */   public static void removeCommonFrames(List<String> causeFrames, List<String> wrapperFrames) {
/* 544 */     if (causeFrames == null || wrapperFrames == null) {
/* 545 */       throw new IllegalArgumentException("The List must not be null");
/*     */     }
/* 547 */     int causeFrameIndex = causeFrames.size() - 1;
/* 548 */     int wrapperFrameIndex = wrapperFrames.size() - 1;
/* 549 */     while (causeFrameIndex >= 0 && wrapperFrameIndex >= 0) {
/*     */ 
/*     */       
/* 552 */       String causeFrame = causeFrames.get(causeFrameIndex);
/* 553 */       String wrapperFrame = wrapperFrames.get(wrapperFrameIndex);
/* 554 */       if (causeFrame.equals(wrapperFrame)) {
/* 555 */         causeFrames.remove(causeFrameIndex);
/*     */       }
/* 557 */       causeFrameIndex--;
/* 558 */       wrapperFrameIndex--;
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
/*     */   public static String getStackTrace(Throwable throwable) {
/* 576 */     StringWriter sw = new StringWriter();
/* 577 */     PrintWriter pw = new PrintWriter(sw, true);
/* 578 */     throwable.printStackTrace(pw);
/* 579 */     return sw.getBuffer().toString();
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
/*     */   public static String[] getStackFrames(Throwable throwable) {
/* 596 */     if (throwable == null) {
/* 597 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*     */     }
/* 599 */     return getStackFrames(getStackTrace(throwable));
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
/*     */   static String[] getStackFrames(String stackTrace) {
/* 612 */     String linebreak = System.lineSeparator();
/* 613 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 614 */     List<String> list = new ArrayList<>();
/* 615 */     while (frames.hasMoreTokens()) {
/* 616 */       list.add(frames.nextToken());
/*     */     }
/* 618 */     return list.<String>toArray(new String[list.size()]);
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
/*     */   static List<String> getStackFrameList(Throwable t) {
/* 634 */     String stackTrace = getStackTrace(t);
/* 635 */     String linebreak = System.lineSeparator();
/* 636 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 637 */     List<String> list = new ArrayList<>();
/* 638 */     boolean traceStarted = false;
/* 639 */     while (frames.hasMoreTokens()) {
/* 640 */       String token = frames.nextToken();
/*     */       
/* 642 */       int at = token.indexOf("at");
/* 643 */       if (at != -1 && token.substring(0, at).trim().isEmpty()) {
/* 644 */         traceStarted = true;
/* 645 */         list.add(token); continue;
/* 646 */       }  if (traceStarted) {
/*     */         break;
/*     */       }
/*     */     } 
/* 650 */     return list;
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
/*     */   public static String getMessage(Throwable th) {
/* 665 */     if (th == null) {
/* 666 */       return "";
/*     */     }
/* 668 */     String clsName = ClassUtils.getShortClassName(th, null);
/* 669 */     String msg = th.getMessage();
/* 670 */     return clsName + ": " + StringUtils.defaultString(msg);
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
/*     */   public static String getRootCauseMessage(Throwable th) {
/* 685 */     Throwable root = getRootCause(th);
/* 686 */     root = (root == null) ? th : root;
/* 687 */     return getMessage(root);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <R> R rethrow(Throwable throwable) {
/* 747 */     return typeErasure(throwable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <R, T extends Throwable> R typeErasure(Throwable throwable) throws T {
/* 758 */     throw (T)throwable;
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
/*     */   public static <R> R wrapAndThrow(Throwable throwable) {
/* 783 */     if (throwable instanceof RuntimeException) {
/* 784 */       throw (RuntimeException)throwable;
/*     */     }
/* 786 */     if (throwable instanceof Error) {
/* 787 */       throw (Error)throwable;
/*     */     }
/* 789 */     throw new UndeclaredThrowableException(throwable);
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
/*     */   public static boolean hasCause(Throwable chain, Class<? extends Throwable> type) {
/* 807 */     if (chain instanceof UndeclaredThrowableException) {
/* 808 */       chain = chain.getCause();
/*     */     }
/* 810 */     return type.isInstance(chain);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/exception/ExceptionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */