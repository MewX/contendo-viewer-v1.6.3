/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.text.ParseException;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.SortedMap;
/*      */ import java.util.TreeMap;
/*      */ import org.apache.commons.collections.map.FixedSizeMap;
/*      */ import org.apache.commons.collections.map.FixedSizeSortedMap;
/*      */ import org.apache.commons.collections.map.LazyMap;
/*      */ import org.apache.commons.collections.map.LazySortedMap;
/*      */ import org.apache.commons.collections.map.ListOrderedMap;
/*      */ import org.apache.commons.collections.map.PredicatedMap;
/*      */ import org.apache.commons.collections.map.PredicatedSortedMap;
/*      */ import org.apache.commons.collections.map.TransformedMap;
/*      */ import org.apache.commons.collections.map.TransformedSortedMap;
/*      */ import org.apache.commons.collections.map.TypedMap;
/*      */ import org.apache.commons.collections.map.TypedSortedMap;
/*      */ import org.apache.commons.collections.map.UnmodifiableMap;
/*      */ import org.apache.commons.collections.map.UnmodifiableSortedMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MapUtils
/*      */ {
/*   89 */   public static final Map EMPTY_MAP = UnmodifiableMap.decorate(new HashMap(1));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   94 */   public static final SortedMap EMPTY_SORTED_MAP = UnmodifiableSortedMap.decorate(new TreeMap());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String INDENT_STRING = "    ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getObject(Map map, Object key) {
/*  116 */     if (map != null) {
/*  117 */       return map.get(key);
/*      */     }
/*  119 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getString(Map map, Object key) {
/*  132 */     if (map != null) {
/*  133 */       Object answer = map.get(key);
/*  134 */       if (answer != null) {
/*  135 */         return answer.toString();
/*      */       }
/*      */     } 
/*  138 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean getBoolean(Map map, Object key) {
/*  156 */     if (map != null) {
/*  157 */       Object answer = map.get(key);
/*  158 */       if (answer != null) {
/*  159 */         if (answer instanceof Boolean) {
/*  160 */           return (Boolean)answer;
/*      */         }
/*  162 */         if (answer instanceof String) {
/*  163 */           return new Boolean((String)answer);
/*      */         }
/*  165 */         if (answer instanceof Number) {
/*  166 */           Number n = (Number)answer;
/*  167 */           return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
/*      */         } 
/*      */       } 
/*      */     } 
/*  171 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Number getNumber(Map map, Object key) {
/*  188 */     if (map != null) {
/*  189 */       Object answer = map.get(key);
/*  190 */       if (answer != null) {
/*  191 */         if (answer instanceof Number) {
/*  192 */           return (Number)answer;
/*      */         }
/*  194 */         if (answer instanceof String) {
/*      */           try {
/*  196 */             String text = (String)answer;
/*  197 */             return NumberFormat.getInstance().parse(text);
/*      */           }
/*  199 */           catch (ParseException e) {
/*  200 */             logInfo(e);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*  205 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Byte getByte(Map map, Object key) {
/*  218 */     Number answer = getNumber(map, key);
/*  219 */     if (answer == null)
/*  220 */       return null; 
/*  221 */     if (answer instanceof Byte) {
/*  222 */       return (Byte)answer;
/*      */     }
/*  224 */     return new Byte(answer.byteValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Short getShort(Map map, Object key) {
/*  237 */     Number answer = getNumber(map, key);
/*  238 */     if (answer == null)
/*  239 */       return null; 
/*  240 */     if (answer instanceof Short) {
/*  241 */       return (Short)answer;
/*      */     }
/*  243 */     return new Short(answer.shortValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer getInteger(Map map, Object key) {
/*  256 */     Number answer = getNumber(map, key);
/*  257 */     if (answer == null)
/*  258 */       return null; 
/*  259 */     if (answer instanceof Integer) {
/*  260 */       return (Integer)answer;
/*      */     }
/*  262 */     return new Integer(answer.intValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Long getLong(Map map, Object key) {
/*  275 */     Number answer = getNumber(map, key);
/*  276 */     if (answer == null)
/*  277 */       return null; 
/*  278 */     if (answer instanceof Long) {
/*  279 */       return (Long)answer;
/*      */     }
/*  281 */     return new Long(answer.longValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Float getFloat(Map map, Object key) {
/*  294 */     Number answer = getNumber(map, key);
/*  295 */     if (answer == null)
/*  296 */       return null; 
/*  297 */     if (answer instanceof Float) {
/*  298 */       return (Float)answer;
/*      */     }
/*  300 */     return new Float(answer.floatValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Double getDouble(Map map, Object key) {
/*  313 */     Number answer = getNumber(map, key);
/*  314 */     if (answer == null)
/*  315 */       return null; 
/*  316 */     if (answer instanceof Double) {
/*  317 */       return (Double)answer;
/*      */     }
/*  319 */     return new Double(answer.doubleValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map getMap(Map map, Object key) {
/*  333 */     if (map != null) {
/*  334 */       Object answer = map.get(key);
/*  335 */       if (answer != null && answer instanceof Map) {
/*  336 */         return (Map)answer;
/*      */       }
/*      */     } 
/*  339 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getObject(Map map, Object key, Object defaultValue) {
/*  355 */     if (map != null) {
/*  356 */       Object answer = map.get(key);
/*  357 */       if (answer != null) {
/*  358 */         return answer;
/*      */       }
/*      */     } 
/*  361 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getString(Map map, Object key, String defaultValue) {
/*  377 */     String answer = getString(map, key);
/*  378 */     if (answer == null) {
/*  379 */       answer = defaultValue;
/*      */     }
/*  381 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean getBoolean(Map map, Object key, Boolean defaultValue) {
/*  397 */     Boolean answer = getBoolean(map, key);
/*  398 */     if (answer == null) {
/*  399 */       answer = defaultValue;
/*      */     }
/*  401 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Number getNumber(Map map, Object key, Number defaultValue) {
/*  417 */     Number answer = getNumber(map, key);
/*  418 */     if (answer == null) {
/*  419 */       answer = defaultValue;
/*      */     }
/*  421 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Byte getByte(Map map, Object key, Byte defaultValue) {
/*  437 */     Byte answer = getByte(map, key);
/*  438 */     if (answer == null) {
/*  439 */       answer = defaultValue;
/*      */     }
/*  441 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Short getShort(Map map, Object key, Short defaultValue) {
/*  457 */     Short answer = getShort(map, key);
/*  458 */     if (answer == null) {
/*  459 */       answer = defaultValue;
/*      */     }
/*  461 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer getInteger(Map map, Object key, Integer defaultValue) {
/*  477 */     Integer answer = getInteger(map, key);
/*  478 */     if (answer == null) {
/*  479 */       answer = defaultValue;
/*      */     }
/*  481 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Long getLong(Map map, Object key, Long defaultValue) {
/*  497 */     Long answer = getLong(map, key);
/*  498 */     if (answer == null) {
/*  499 */       answer = defaultValue;
/*      */     }
/*  501 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Float getFloat(Map map, Object key, Float defaultValue) {
/*  517 */     Float answer = getFloat(map, key);
/*  518 */     if (answer == null) {
/*  519 */       answer = defaultValue;
/*      */     }
/*  521 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Double getDouble(Map map, Object key, Double defaultValue) {
/*  537 */     Double answer = getDouble(map, key);
/*  538 */     if (answer == null) {
/*  539 */       answer = defaultValue;
/*      */     }
/*  541 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map getMap(Map map, Object key, Map defaultValue) {
/*  557 */     Map answer = getMap(map, key);
/*  558 */     if (answer == null) {
/*  559 */       answer = defaultValue;
/*      */     }
/*  561 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getBooleanValue(Map map, Object key) {
/*  582 */     Boolean booleanObject = getBoolean(map, key);
/*  583 */     if (booleanObject == null) {
/*  584 */       return false;
/*      */     }
/*  586 */     return booleanObject.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte getByteValue(Map map, Object key) {
/*  599 */     Byte byteObject = getByte(map, key);
/*  600 */     if (byteObject == null) {
/*  601 */       return 0;
/*      */     }
/*  603 */     return byteObject.byteValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short getShortValue(Map map, Object key) {
/*  616 */     Short shortObject = getShort(map, key);
/*  617 */     if (shortObject == null) {
/*  618 */       return 0;
/*      */     }
/*  620 */     return shortObject.shortValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getIntValue(Map map, Object key) {
/*  633 */     Integer integerObject = getInteger(map, key);
/*  634 */     if (integerObject == null) {
/*  635 */       return 0;
/*      */     }
/*  637 */     return integerObject.intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getLongValue(Map map, Object key) {
/*  650 */     Long longObject = getLong(map, key);
/*  651 */     if (longObject == null) {
/*  652 */       return 0L;
/*      */     }
/*  654 */     return longObject.longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getFloatValue(Map map, Object key) {
/*  667 */     Float floatObject = getFloat(map, key);
/*  668 */     if (floatObject == null) {
/*  669 */       return 0.0F;
/*      */     }
/*  671 */     return floatObject.floatValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double getDoubleValue(Map map, Object key) {
/*  684 */     Double doubleObject = getDouble(map, key);
/*  685 */     if (doubleObject == null) {
/*  686 */       return 0.0D;
/*      */     }
/*  688 */     return doubleObject.doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getBooleanValue(Map map, Object key, boolean defaultValue) {
/*  711 */     Boolean booleanObject = getBoolean(map, key);
/*  712 */     if (booleanObject == null) {
/*  713 */       return defaultValue;
/*      */     }
/*  715 */     return booleanObject.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte getByteValue(Map map, Object key, byte defaultValue) {
/*  731 */     Byte byteObject = getByte(map, key);
/*  732 */     if (byteObject == null) {
/*  733 */       return defaultValue;
/*      */     }
/*  735 */     return byteObject.byteValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short getShortValue(Map map, Object key, short defaultValue) {
/*  751 */     Short shortObject = getShort(map, key);
/*  752 */     if (shortObject == null) {
/*  753 */       return defaultValue;
/*      */     }
/*  755 */     return shortObject.shortValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getIntValue(Map map, Object key, int defaultValue) {
/*  771 */     Integer integerObject = getInteger(map, key);
/*  772 */     if (integerObject == null) {
/*  773 */       return defaultValue;
/*      */     }
/*  775 */     return integerObject.intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getLongValue(Map map, Object key, long defaultValue) {
/*  791 */     Long longObject = getLong(map, key);
/*  792 */     if (longObject == null) {
/*  793 */       return defaultValue;
/*      */     }
/*  795 */     return longObject.longValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getFloatValue(Map map, Object key, float defaultValue) {
/*  811 */     Float floatObject = getFloat(map, key);
/*  812 */     if (floatObject == null) {
/*  813 */       return defaultValue;
/*      */     }
/*  815 */     return floatObject.floatValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double getDoubleValue(Map map, Object key, double defaultValue) {
/*  831 */     Double doubleObject = getDouble(map, key);
/*  832 */     if (doubleObject == null) {
/*  833 */       return defaultValue;
/*      */     }
/*  835 */     return doubleObject.doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Properties toProperties(Map map) {
/*  848 */     Properties answer = new Properties();
/*  849 */     if (map != null) {
/*  850 */       for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
/*  851 */         Map.Entry entry = iter.next();
/*  852 */         Object key = entry.getKey();
/*  853 */         Object value = entry.getValue();
/*  854 */         answer.put(key, value);
/*      */       } 
/*      */     }
/*  857 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map toMap(ResourceBundle resourceBundle) {
/*  868 */     Enumeration enumeration = resourceBundle.getKeys();
/*  869 */     Map map = new HashMap();
/*      */     
/*  871 */     while (enumeration.hasMoreElements()) {
/*  872 */       String key = enumeration.nextElement();
/*  873 */       Object value = resourceBundle.getObject(key);
/*  874 */       map.put(key, value);
/*      */     } 
/*      */     
/*  877 */     return map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void verbosePrint(PrintStream out, Object label, Map map) {
/*  905 */     verbosePrintInternal(out, label, map, new ArrayStack(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void debugPrint(PrintStream out, Object label, Map map) {
/*  931 */     verbosePrintInternal(out, label, map, new ArrayStack(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void logInfo(Exception ex) {
/*  944 */     System.out.println("INFO: Exception: " + ex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void verbosePrintInternal(PrintStream out, Object label, Map map, ArrayStack lineage, boolean debug) {
/*  977 */     printIndent(out, lineage.size());
/*      */     
/*  979 */     if (map == null) {
/*  980 */       if (label != null) {
/*  981 */         out.print(label);
/*  982 */         out.print(" = ");
/*      */       } 
/*  984 */       out.println("null");
/*      */       return;
/*      */     } 
/*  987 */     if (label != null) {
/*  988 */       out.print(label);
/*  989 */       out.println(" = ");
/*      */     } 
/*      */     
/*  992 */     printIndent(out, lineage.size());
/*  993 */     out.println("{");
/*      */     
/*  995 */     lineage.push(map);
/*      */     
/*  997 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/*  998 */       Map.Entry entry = it.next();
/*  999 */       Object childKey = entry.getKey();
/* 1000 */       Object childValue = entry.getValue();
/* 1001 */       if (childValue instanceof Map && !lineage.contains(childValue)) {
/* 1002 */         verbosePrintInternal(out, (childKey == null) ? "null" : childKey, (Map)childValue, lineage, debug);
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */ 
/*      */       
/* 1009 */       printIndent(out, lineage.size());
/* 1010 */       out.print(childKey);
/* 1011 */       out.print(" = ");
/*      */       
/* 1013 */       int lineageIndex = lineage.indexOf(childValue);
/* 1014 */       if (lineageIndex == -1) {
/* 1015 */         out.print(childValue);
/* 1016 */       } else if (lineage.size() - 1 == lineageIndex) {
/* 1017 */         out.print("(this Map)");
/*      */       } else {
/* 1019 */         out.print("(ancestor[" + (lineage.size() - 1 - lineageIndex - 1) + "] Map)");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1025 */       if (debug && childValue != null) {
/* 1026 */         out.print(' ');
/* 1027 */         out.println(childValue.getClass().getName()); continue;
/*      */       } 
/* 1029 */       out.println();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1034 */     lineage.pop();
/*      */     
/* 1036 */     printIndent(out, lineage.size());
/* 1037 */     out.println(debug ? ("} " + map.getClass().getName()) : "}");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void printIndent(PrintStream out, int indent) {
/* 1046 */     for (int i = 0; i < indent; i++) {
/* 1047 */       out.print("    ");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map invertMap(Map map) {
/* 1068 */     Map out = new HashMap(map.size());
/* 1069 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 1070 */       Map.Entry entry = it.next();
/* 1071 */       out.put(entry.getValue(), entry.getKey());
/*      */     } 
/* 1073 */     return out;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void safeAddToMap(Map map, Object key, Object value) throws NullPointerException {
/* 1091 */     if (value == null) {
/* 1092 */       map.put(key, "");
/*      */     } else {
/* 1094 */       map.put(key, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map synchronizedMap(Map map) {
/* 1124 */     return Collections.synchronizedMap(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map unmodifiableMap(Map map) {
/* 1137 */     return UnmodifiableMap.decorate(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map predicatedMap(Map map, Predicate keyPred, Predicate valuePred) {
/* 1156 */     return PredicatedMap.decorate(map, keyPred, valuePred);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map typedMap(Map map, Class keyType, Class valueType) {
/* 1171 */     return TypedMap.decorate(map, keyType, valueType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map transformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/* 1188 */     return TransformedMap.decorate(map, keyTransformer, valueTransformer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map fixedSizeMap(Map map) {
/* 1202 */     return FixedSizeMap.decorate(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map lazyMap(Map map, Factory factory) {
/* 1234 */     return LazyMap.decorate(map, factory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map lazyMap(Map map, Transformer transformerFactory) {
/* 1273 */     return LazyMap.decorate(map, transformerFactory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map orderedMap(Map map) {
/* 1288 */     return ListOrderedMap.decorate(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map synchronizedSortedMap(SortedMap map) {
/* 1317 */     return Collections.synchronizedSortedMap(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map unmodifiableSortedMap(SortedMap map) {
/* 1330 */     return UnmodifiableSortedMap.decorate(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap predicatedSortedMap(SortedMap map, Predicate keyPred, Predicate valuePred) {
/* 1349 */     return PredicatedSortedMap.decorate(map, keyPred, valuePred);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap typedSortedMap(SortedMap map, Class keyType, Class valueType) {
/* 1363 */     return TypedSortedMap.decorate(map, keyType, valueType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap transformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/* 1380 */     return TransformedSortedMap.decorate(map, keyTransformer, valueTransformer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap fixedSizeSortedMap(SortedMap map) {
/* 1394 */     return FixedSizeSortedMap.decorate(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap lazySortedMap(SortedMap map, Factory factory) {
/* 1427 */     return LazySortedMap.decorate(map, factory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SortedMap lazySortedMap(SortedMap map, Transformer transformerFactory) {
/* 1466 */     return LazySortedMap.decorate(map, transformerFactory);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/MapUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */