/*      */ package com.sun.jna;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NativeLibrary
/*      */ {
/*   86 */   private static final Logger LOG = Logger.getLogger(NativeLibrary.class.getName());
/*   87 */   private static final Level DEBUG_LOAD_LEVEL = Native.DEBUG_LOAD ? Level.INFO : Level.FINE;
/*      */   
/*      */   private long handle;
/*      */   private final String libraryName;
/*      */   private final String libraryPath;
/*   92 */   private final Map<String, Function> functions = new HashMap<String, Function>();
/*      */   
/*      */   final int callFlags;
/*      */   private String encoding;
/*      */   final Map<String, ?> options;
/*   97 */   private static final Map<String, Reference<NativeLibrary>> libraries = new HashMap<String, Reference<NativeLibrary>>();
/*   98 */   private static final Map<String, List<String>> searchPaths = Collections.synchronizedMap(new HashMap<String, List<String>>());
/*   99 */   private static final List<String> librarySearchPath = new ArrayList<String>();
/*      */   private static final int DEFAULT_OPEN_OPTIONS = -1;
/*      */   
/*      */   static {
/*  103 */     if (Native.POINTER_SIZE == 0)
/*  104 */       throw new Error("Native library not initialized"); 
/*      */   }
/*      */   
/*      */   private static String functionKey(String name, int flags, String encoding) {
/*  108 */     return name + "|" + flags + "|" + encoding;
/*      */   }
/*      */   
/*      */   private NativeLibrary(String libraryName, String libraryPath, long handle, Map<String, ?> options) {
/*  112 */     this.libraryName = getLibraryName(libraryName);
/*  113 */     this.libraryPath = libraryPath;
/*  114 */     this.handle = handle;
/*  115 */     Object option = options.get("calling-convention");
/*  116 */     int callingConvention = (option instanceof Number) ? ((Number)option).intValue() : 0;
/*  117 */     this.callFlags = callingConvention;
/*  118 */     this.options = options;
/*  119 */     this.encoding = (String)options.get("string-encoding");
/*  120 */     if (this.encoding == null) {
/*  121 */       this.encoding = Native.getDefaultStringEncoding();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  126 */     if (Platform.isWindows() && "kernel32".equals(this.libraryName.toLowerCase())) {
/*  127 */       synchronized (this.functions) {
/*  128 */         Function f = new Function(this, "GetLastError", 63, this.encoding)
/*      */           {
/*      */             Object invoke(Object[] args, Class<?> returnType, boolean b, int fixedArgs) {
/*  131 */               return Integer.valueOf(Native.getLastError());
/*      */             }
/*      */ 
/*      */             
/*      */             Object invoke(Method invokingMethod, Class<?>[] paramTypes, Class<?> returnType, Object[] inArgs, Map<String, ?> options) {
/*  136 */               return Integer.valueOf(Native.getLastError());
/*      */             }
/*      */           };
/*  139 */         this.functions.put(functionKey("GetLastError", this.callFlags, this.encoding), f);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static int openFlags(Map<String, ?> options) {
/*  146 */     Object opt = options.get("open-flags");
/*  147 */     if (opt instanceof Number) {
/*  148 */       return ((Number)opt).intValue();
/*      */     }
/*  150 */     return -1;
/*      */   }
/*      */   
/*      */   private static NativeLibrary loadLibrary(String libraryName, Map<String, ?> options) {
/*  154 */     LOG.log(DEBUG_LOAD_LEVEL, "Looking for library '" + libraryName + "'");
/*      */     
/*  156 */     List<Throwable> exceptions = new ArrayList<Throwable>();
/*  157 */     boolean isAbsolutePath = (new File(libraryName)).isAbsolute();
/*  158 */     List<String> searchPath = new ArrayList<String>();
/*  159 */     int openFlags = openFlags(options);
/*      */ 
/*      */ 
/*      */     
/*  163 */     String webstartPath = Native.getWebStartLibraryPath(libraryName);
/*  164 */     if (webstartPath != null) {
/*  165 */       LOG.log(DEBUG_LOAD_LEVEL, "Adding web start path " + webstartPath);
/*  166 */       searchPath.add(webstartPath);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  172 */     List<String> customPaths = searchPaths.get(libraryName);
/*  173 */     if (customPaths != null) {
/*  174 */       synchronized (customPaths) {
/*  175 */         searchPath.addAll(0, customPaths);
/*      */       } 
/*      */     }
/*      */     
/*  179 */     LOG.log(DEBUG_LOAD_LEVEL, "Adding paths from jna.library.path: " + System.getProperty("jna.library.path"));
/*      */     
/*  181 */     searchPath.addAll(initPaths("jna.library.path"));
/*  182 */     String libraryPath = findLibraryPath(libraryName, searchPath);
/*  183 */     long handle = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  190 */       LOG.log(DEBUG_LOAD_LEVEL, "Trying " + libraryPath);
/*  191 */       handle = Native.open(libraryPath, openFlags);
/*  192 */     } catch (UnsatisfiedLinkError e) {
/*      */       
/*  194 */       LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
/*  195 */       LOG.log(DEBUG_LOAD_LEVEL, "Adding system paths: " + librarySearchPath);
/*  196 */       exceptions.add(e);
/*  197 */       searchPath.addAll(librarySearchPath);
/*      */     } 
/*      */     
/*      */     try {
/*  201 */       if (handle == 0L) {
/*  202 */         libraryPath = findLibraryPath(libraryName, searchPath);
/*  203 */         LOG.log(DEBUG_LOAD_LEVEL, "Trying " + libraryPath);
/*  204 */         handle = Native.open(libraryPath, openFlags);
/*  205 */         if (handle == 0L) {
/*  206 */           throw new UnsatisfiedLinkError("Failed to load library '" + libraryName + "'");
/*      */         }
/*      */       } 
/*  209 */     } catch (UnsatisfiedLinkError ule) {
/*  210 */       LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + ule.getMessage());
/*  211 */       exceptions.add(ule);
/*      */ 
/*      */ 
/*      */       
/*  215 */       if (Platform.isAndroid()) {
/*      */         try {
/*  217 */           LOG.log(DEBUG_LOAD_LEVEL, "Preload (via System.loadLibrary) " + libraryName);
/*  218 */           System.loadLibrary(libraryName);
/*  219 */           handle = Native.open(libraryPath, openFlags);
/*      */         }
/*  221 */         catch (UnsatisfiedLinkError e2) {
/*  222 */           LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
/*  223 */           exceptions.add(e2);
/*      */         }
/*      */       
/*  226 */       } else if (Platform.isLinux() || Platform.isFreeBSD()) {
/*      */ 
/*      */ 
/*      */         
/*  230 */         LOG.log(DEBUG_LOAD_LEVEL, "Looking for version variants");
/*  231 */         libraryPath = matchLibrary(libraryName, searchPath);
/*  232 */         if (libraryPath != null) {
/*  233 */           LOG.log(DEBUG_LOAD_LEVEL, "Trying " + libraryPath);
/*      */           try {
/*  235 */             handle = Native.open(libraryPath, openFlags);
/*      */           }
/*  237 */           catch (UnsatisfiedLinkError e2) {
/*  238 */             LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
/*  239 */             exceptions.add(e2);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  244 */       } else if (Platform.isMac() && !libraryName.endsWith(".dylib")) {
/*  245 */         LOG.log(DEBUG_LOAD_LEVEL, "Looking for matching frameworks");
/*  246 */         libraryPath = matchFramework(libraryName);
/*  247 */         if (libraryPath != null) {
/*      */           try {
/*  249 */             LOG.log(DEBUG_LOAD_LEVEL, "Trying " + libraryPath);
/*  250 */             handle = Native.open(libraryPath, openFlags);
/*      */           }
/*  252 */           catch (UnsatisfiedLinkError e2) {
/*  253 */             LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
/*  254 */             exceptions.add(e2);
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*  259 */       else if (Platform.isWindows() && !isAbsolutePath) {
/*  260 */         LOG.log(DEBUG_LOAD_LEVEL, "Looking for lib- prefix");
/*  261 */         libraryPath = findLibraryPath("lib" + libraryName, searchPath);
/*  262 */         if (libraryPath != null) {
/*  263 */           LOG.log(DEBUG_LOAD_LEVEL, "Trying " + libraryPath);
/*      */           try {
/*  265 */             handle = Native.open(libraryPath, openFlags);
/*  266 */           } catch (UnsatisfiedLinkError e2) {
/*  267 */             LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
/*  268 */             exceptions.add(e2);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  274 */       if (handle == 0L) {
/*      */         try {
/*  276 */           File embedded = Native.extractFromResourcePath(libraryName, (ClassLoader)options.get("classloader"));
/*      */           try {
/*  278 */             handle = Native.open(embedded.getAbsolutePath(), openFlags);
/*  279 */             libraryPath = embedded.getAbsolutePath();
/*      */           } finally {
/*      */             
/*  282 */             if (Native.isUnpacked(embedded)) {
/*  283 */               Native.deleteLibrary(embedded);
/*      */             }
/*      */           }
/*      */         
/*  287 */         } catch (IOException e2) {
/*  288 */           LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
/*  289 */           exceptions.add(e2);
/*      */         } 
/*      */       }
/*      */       
/*  293 */       if (handle == 0L) {
/*  294 */         StringBuilder sb = new StringBuilder();
/*  295 */         sb.append("Unable to load library '");
/*  296 */         sb.append(libraryName);
/*  297 */         sb.append("':");
/*  298 */         for (Throwable t : exceptions) {
/*  299 */           sb.append("\n");
/*  300 */           sb.append(t.getMessage());
/*      */         } 
/*  302 */         UnsatisfiedLinkError res = new UnsatisfiedLinkError(sb.toString());
/*  303 */         for (Throwable t : exceptions) {
/*  304 */           addSuppressedReflected(res, t);
/*      */         }
/*  306 */         throw res;
/*      */       } 
/*      */     } 
/*      */     
/*  310 */     LOG.log(DEBUG_LOAD_LEVEL, "Found library '" + libraryName + "' at " + libraryPath);
/*  311 */     return new NativeLibrary(libraryName, libraryPath, handle, options);
/*      */   }
/*      */   
/*  314 */   private static Method addSuppressedMethod = null;
/*      */   private static void addSuppressedReflected(Throwable target, Throwable suppressed) { if (addSuppressedMethod == null) return;  try { addSuppressedMethod.invoke(target, new Object[] { suppressed }); } catch (IllegalAccessException ex) { throw new RuntimeException("Failed to call addSuppressedMethod", ex); } catch (IllegalArgumentException ex) { throw new RuntimeException("Failed to call addSuppressedMethod", ex); } catch (InvocationTargetException ex) { throw new RuntimeException("Failed to call addSuppressedMethod", ex); }  }
/*      */   static String matchFramework(String libraryName) { File framework = new File(libraryName); if (framework.isAbsolute()) { if (libraryName.indexOf(".framework") != -1 && framework.exists()) return framework.getAbsolutePath();  framework = new File(new File(framework.getParentFile(), framework.getName() + ".framework"), framework.getName()); if (framework.exists()) return framework.getAbsolutePath();  } else { String[] PREFIXES = { System.getProperty("user.home"), "", "/System" }; String suffix = (libraryName.indexOf(".framework") == -1) ? (libraryName + ".framework/" + libraryName) : libraryName; for (int i = 0; i < PREFIXES.length; i++) { String libraryPath = PREFIXES[i] + "/Library/Frameworks/" + suffix; if ((new File(libraryPath)).exists()) return libraryPath;  }  }  return null; }
/*  317 */   private String getLibraryName(String libraryName) { String simplified = libraryName; String BASE = "---"; String template = mapSharedLibraryName("---"); int prefixEnd = template.indexOf("---"); if (prefixEnd > 0 && simplified.startsWith(template.substring(0, prefixEnd))) simplified = simplified.substring(prefixEnd);  String suffix = template.substring(prefixEnd + "---".length()); int suffixStart = simplified.indexOf(suffix); if (suffixStart != -1) simplified = simplified.substring(0, suffixStart);  return simplified; } public static final NativeLibrary getInstance(String libraryName) { return getInstance(libraryName, Collections.emptyMap()); } public static final NativeLibrary getInstance(String libraryName, ClassLoader classLoader) { return getInstance(libraryName, Collections.singletonMap("classloader", classLoader)); } public static final NativeLibrary getInstance(String libraryName, Map<String, ?> libraryOptions) { Map<String, Object> options = new HashMap<String, Object>(libraryOptions); if (options.get("calling-convention") == null) options.put("calling-convention", Integer.valueOf(0));  if ((Platform.isLinux() || Platform.isFreeBSD() || Platform.isAIX()) && Platform.C_LIBRARY_NAME.equals(libraryName)) libraryName = null;  synchronized (libraries) { Reference<NativeLibrary> ref = libraries.get(libraryName + options); NativeLibrary library = (ref != null) ? ref.get() : null; if (library == null) { if (libraryName == null) { library = new NativeLibrary("<process>", null, Native.open(null, openFlags(options)), options); } else { library = loadLibrary(libraryName, options); }  ref = new WeakReference<NativeLibrary>(library); libraries.put(library.getName() + options, ref); File file = library.getFile(); if (file != null) { libraries.put(file.getAbsolutePath() + options, ref); libraries.put(file.getName() + options, ref); }  }  return library; }  } public static final synchronized NativeLibrary getProcess() { return getInstance(null); } static { try { addSuppressedMethod = Throwable.class.getMethod("addSuppressed", new Class[] { Throwable.class }); }
/*  318 */     catch (NoSuchMethodException noSuchMethodException)
/*      */     {  }
/*  320 */     catch (SecurityException ex)
/*  321 */     { Logger.getLogger(NativeLibrary.class.getName()).log(Level.SEVERE, "Failed to initialize 'addSuppressed' method", ex); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     String webstartPath = Native.getWebStartLibraryPath("jnidispatch");
/*  877 */     if (webstartPath != null) {
/*  878 */       librarySearchPath.add(webstartPath);
/*      */     }
/*  880 */     if (System.getProperty("jna.platform.library.path") == null && 
/*  881 */       !Platform.isWindows()) {
/*      */       
/*  883 */       String platformPath = "";
/*  884 */       String sep = "";
/*  885 */       String archPath = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  898 */       if (Platform.isLinux() || Platform.isSolaris() || 
/*  899 */         Platform.isFreeBSD() || Platform.iskFreeBSD())
/*      */       {
/*  901 */         archPath = (Platform.isSolaris() ? "/" : "") + (Native.POINTER_SIZE * 8);
/*      */       }
/*  903 */       String[] paths = { "/usr/lib" + archPath, "/lib" + archPath, "/usr/lib", "/lib" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  914 */       if (Platform.isLinux() || Platform.iskFreeBSD() || Platform.isGNU()) {
/*  915 */         String multiArchPath = getMultiArchPath();
/*      */ 
/*      */         
/*  918 */         paths = new String[] { "/usr/lib/" + multiArchPath, "/lib/" + multiArchPath, "/usr/lib" + archPath, "/lib" + archPath, "/usr/lib", "/lib" };
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  934 */       if (Platform.isLinux()) {
/*  935 */         ArrayList<String> ldPaths = getLinuxLdPaths();
/*      */         
/*  937 */         for (int j = paths.length - 1; 0 <= j; j--) {
/*  938 */           int found = ldPaths.indexOf(paths[j]);
/*  939 */           if (found != -1) {
/*  940 */             ldPaths.remove(found);
/*      */           }
/*  942 */           ldPaths.add(0, paths[j]);
/*      */         } 
/*  944 */         paths = ldPaths.<String>toArray(new String[0]);
/*      */       } 
/*      */       
/*  947 */       for (int i = 0; i < paths.length; i++) {
/*  948 */         File dir = new File(paths[i]);
/*  949 */         if (dir.exists() && dir.isDirectory()) {
/*  950 */           platformPath = platformPath + sep + paths[i];
/*  951 */           sep = File.pathSeparator;
/*      */         } 
/*      */       } 
/*  954 */       if (!"".equals(platformPath)) {
/*  955 */         System.setProperty("jna.platform.library.path", platformPath);
/*      */       }
/*      */     } 
/*  958 */     librarySearchPath.addAll(initPaths("jna.platform.library.path")); }
/*      */   public static final synchronized NativeLibrary getProcess(Map<String, ?> options) { return getInstance((String)null, options); }
/*      */   public static final void addSearchPath(String libraryName, String path) { synchronized (searchPaths) { List<String> customPaths = searchPaths.get(libraryName); if (customPaths == null) { customPaths = Collections.synchronizedList(new ArrayList<String>()); searchPaths.put(libraryName, customPaths); }  customPaths.add(path); }  }
/*      */   public Function getFunction(String functionName) { return getFunction(functionName, this.callFlags); }
/*  962 */   Function getFunction(String name, Method method) { FunctionMapper mapper = (FunctionMapper)this.options.get("function-mapper"); if (mapper != null) name = mapper.getFunctionName(this, method);  String prefix = System.getProperty("jna.profiler.prefix", "$$YJP$$"); if (name.startsWith(prefix)) name = name.substring(prefix.length());  int flags = this.callFlags; Class<?>[] etypes = method.getExceptionTypes(); for (int i = 0; i < etypes.length; i++) { if (LastErrorException.class.isAssignableFrom(etypes[i])) flags |= 0x40;  }  return getFunction(name, flags); } public Function getFunction(String functionName, int callFlags) { return getFunction(functionName, callFlags, this.encoding); } public Function getFunction(String functionName, int callFlags, String encoding) { if (functionName == null) throw new NullPointerException("Function name may not be null");  synchronized (this.functions) { String key = functionKey(functionName, callFlags, encoding); Function function = this.functions.get(key); if (function == null) { function = new Function(this, functionName, callFlags, encoding); this.functions.put(key, function); }  return function; }  } public Map<String, ?> getOptions() { return this.options; } private static String getMultiArchPath() { String cpu = Platform.ARCH;
/*      */ 
/*      */     
/*  965 */     String kernel = Platform.iskFreeBSD() ? "-kfreebsd" : (Platform.isGNU() ? "" : "-linux");
/*  966 */     String libc = "-gnu";
/*      */     
/*  968 */     if (Platform.isIntel()) {
/*  969 */       cpu = Platform.is64Bit() ? "x86_64" : "i386";
/*      */     }
/*  971 */     else if (Platform.isPPC()) {
/*  972 */       cpu = Platform.is64Bit() ? "powerpc64" : "powerpc";
/*      */     }
/*  974 */     else if (Platform.isARM()) {
/*  975 */       cpu = "arm";
/*  976 */       libc = "-gnueabi";
/*      */     }
/*  978 */     else if (Platform.ARCH.equals("mips64el")) {
/*  979 */       libc = "-gnuabi64";
/*      */     } 
/*      */     
/*  982 */     return cpu + kernel + libc; } public Pointer getGlobalVariableAddress(String symbolName) { try { return new Pointer(getSymbolAddress(symbolName)); } catch (UnsatisfiedLinkError e) { throw new UnsatisfiedLinkError("Error looking up '" + symbolName + "': " + e.getMessage()); }  }
/*      */   long getSymbolAddress(String name) { if (this.handle == 0L) throw new UnsatisfiedLinkError("Library has been unloaded");  return Native.findSymbol(this.handle, name); }
/*      */   public String toString() { return "Native Library <" + this.libraryPath + "@" + this.handle + ">"; }
/*      */   public String getName() { return this.libraryName; }
/*      */   public File getFile() { if (this.libraryPath == null) return null;  return new File(this.libraryPath); }
/*      */   protected void finalize() { dispose(); }
/*      */   static void disposeAll() { Set<Reference<NativeLibrary>> values; synchronized (libraries) { values = new LinkedHashSet<Reference<NativeLibrary>>(libraries.values()); }  for (Reference<NativeLibrary> ref : values) { NativeLibrary lib = ref.get(); if (lib != null) lib.dispose();  }  }
/*  989 */   private static ArrayList<String> getLinuxLdPaths() { ArrayList<String> ldPaths = new ArrayList<String>();
/*  990 */     BufferedReader reader = null;
/*      */     
/*  992 */     try { Process process = Runtime.getRuntime().exec("/sbin/ldconfig -p");
/*  993 */       reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
/*      */       String buffer;
/*  995 */       while ((buffer = reader.readLine()) != null) {
/*  996 */         int startPath = buffer.indexOf(" => ");
/*  997 */         int endPath = buffer.lastIndexOf('/');
/*  998 */         if (startPath != -1 && endPath != -1 && startPath < endPath) {
/*  999 */           String path = buffer.substring(startPath + 4, endPath);
/* 1000 */           if (!ldPaths.contains(path)) {
/* 1001 */             ldPaths.add(path);
/*      */           }
/*      */         } 
/*      */       }  }
/* 1005 */     catch (Exception exception) {  }
/*      */     finally
/* 1007 */     { if (reader != null) {
/*      */         try {
/* 1009 */           reader.close();
/* 1010 */         } catch (IOException iOException) {}
/*      */       } }
/*      */ 
/*      */     
/* 1014 */     return ldPaths; }
/*      */ 
/*      */   
/*      */   public void dispose() {
/*      */     Set<String> keys = new HashSet<String>();
/*      */     synchronized (libraries) {
/*      */       for (Map.Entry<String, Reference<NativeLibrary>> e : libraries.entrySet()) {
/*      */         Reference<NativeLibrary> ref = e.getValue();
/*      */         if (ref.get() == this)
/*      */           keys.add(e.getKey()); 
/*      */       } 
/*      */       for (String k : keys)
/*      */         libraries.remove(k); 
/*      */     } 
/*      */     synchronized (this) {
/*      */       if (this.handle != 0L) {
/*      */         Native.close(this.handle);
/*      */         this.handle = 0L;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static List<String> initPaths(String key) {
/*      */     String value = System.getProperty(key, "");
/*      */     if ("".equals(value))
/*      */       return Collections.emptyList(); 
/*      */     StringTokenizer st = new StringTokenizer(value, File.pathSeparator);
/*      */     List<String> list = new ArrayList<String>();
/*      */     while (st.hasMoreTokens()) {
/*      */       String path = st.nextToken();
/*      */       if (!"".equals(path))
/*      */         list.add(path); 
/*      */     } 
/*      */     return list;
/*      */   }
/*      */   
/*      */   private static String findLibraryPath(String libName, List<String> searchPath) {
/*      */     if ((new File(libName)).isAbsolute())
/*      */       return libName; 
/*      */     String name = mapSharedLibraryName(libName);
/*      */     for (String path : searchPath) {
/*      */       File file = new File(path, name);
/*      */       if (file.exists())
/*      */         return file.getAbsolutePath(); 
/*      */       if (Platform.isMac())
/*      */         if (name.endsWith(".dylib")) {
/*      */           file = new File(path, name.substring(0, name.lastIndexOf(".dylib")) + ".jnilib");
/*      */           if (file.exists())
/*      */             return file.getAbsolutePath(); 
/*      */         }  
/*      */     } 
/*      */     return name;
/*      */   }
/*      */   
/*      */   static String mapSharedLibraryName(String libName) {
/*      */     if (Platform.isMac()) {
/*      */       if (libName.startsWith("lib") && (libName.endsWith(".dylib") || libName.endsWith(".jnilib")))
/*      */         return libName; 
/*      */       String name = System.mapLibraryName(libName);
/*      */       if (name.endsWith(".jnilib"))
/*      */         return name.substring(0, name.lastIndexOf(".jnilib")) + ".dylib"; 
/*      */       return name;
/*      */     } 
/*      */     if (Platform.isLinux() || Platform.isFreeBSD()) {
/*      */       if (isVersionedName(libName) || libName.endsWith(".so"))
/*      */         return libName; 
/*      */     } else if (Platform.isAIX()) {
/*      */       if (libName.startsWith("lib"))
/*      */         return libName; 
/*      */     } else if (Platform.isWindows() && (libName.endsWith(".drv") || libName.endsWith(".dll"))) {
/*      */       return libName;
/*      */     } 
/*      */     return System.mapLibraryName(libName);
/*      */   }
/*      */   
/*      */   private static boolean isVersionedName(String name) {
/*      */     if (name.startsWith("lib")) {
/*      */       int so = name.lastIndexOf(".so.");
/*      */       if (so != -1 && so + 4 < name.length()) {
/*      */         for (int i = so + 4; i < name.length(); i++) {
/*      */           char ch = name.charAt(i);
/*      */           if (!Character.isDigit(ch) && ch != '.')
/*      */             return false; 
/*      */         } 
/*      */         return true;
/*      */       } 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   static String matchLibrary(final String libName, List<String> searchPath) {
/*      */     File lib = new File(libName);
/*      */     if (lib.isAbsolute())
/*      */       searchPath = Arrays.asList(new String[] { lib.getParent() }); 
/*      */     FilenameFilter filter = new FilenameFilter() {
/*      */         public boolean accept(File dir, String filename) {
/*      */           return ((filename.startsWith("lib" + libName + ".so") || (filename.startsWith(libName + ".so") && libName.startsWith("lib"))) && NativeLibrary.isVersionedName(filename));
/*      */         }
/*      */       };
/*      */     Collection<File> matches = new LinkedList<File>();
/*      */     for (String path : searchPath) {
/*      */       File[] files = (new File(path)).listFiles(filter);
/*      */       if (files != null && files.length > 0)
/*      */         matches.addAll(Arrays.asList(files)); 
/*      */     } 
/*      */     double bestVersion = -1.0D;
/*      */     String bestMatch = null;
/*      */     for (File f : matches) {
/*      */       String path = f.getAbsolutePath();
/*      */       String ver = path.substring(path.lastIndexOf(".so.") + 4);
/*      */       double version = parseVersion(ver);
/*      */       if (version > bestVersion) {
/*      */         bestVersion = version;
/*      */         bestMatch = path;
/*      */       } 
/*      */     } 
/*      */     return bestMatch;
/*      */   }
/*      */   
/*      */   static double parseVersion(String ver) {
/*      */     double v = 0.0D;
/*      */     double divisor = 1.0D;
/*      */     int dot = ver.indexOf(".");
/*      */     while (ver != null) {
/*      */       String num;
/*      */       if (dot != -1) {
/*      */         num = ver.substring(0, dot);
/*      */         ver = ver.substring(dot + 1);
/*      */         dot = ver.indexOf(".");
/*      */       } else {
/*      */         num = ver;
/*      */         ver = null;
/*      */       } 
/*      */       try {
/*      */         v += Integer.parseInt(num) / divisor;
/*      */       } catch (NumberFormatException e) {
/*      */         return 0.0D;
/*      */       } 
/*      */       divisor *= 100.0D;
/*      */     } 
/*      */     return v;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/NativeLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */