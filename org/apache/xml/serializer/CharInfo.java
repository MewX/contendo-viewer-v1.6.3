/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.utils.CharKey;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CharInfo
/*     */ {
/*  52 */   private Hashtable m_charToEntityRef = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static String HTML_ENTITIES_RESOURCE = "org.apache.xml.serializer.HTMLEntities";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static String XML_ENTITIES_RESOURCE = "org.apache.xml.serializer.XMLEntities";
/*     */ 
/*     */   
/*     */   public static final char S_HORIZONAL_TAB = '\t';
/*     */ 
/*     */   
/*     */   public static final char S_LINEFEED = '\n';
/*     */ 
/*     */   
/*  73 */   public static char S_CARRIAGERETURN = '\r';
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final boolean onlyQuotAmpLtGt;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int ASCII_MAX = 128;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private boolean[] isSpecialAttrASCII = new boolean[128];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private boolean[] isSpecialTextASCII = new boolean[128];
/*     */   
/*  95 */   private boolean[] isCleanTextASCII = new boolean[128];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   private int[] array_of_bits = createEmptySetOfIntegers(65535);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SHIFT_PER_WORD = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int LOW_ORDER_BITMASK = 31;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int firstWordNotUsed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CharKey m_charKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CharInfo(String entitiesResource, String method) {
/* 155 */     this(entitiesResource, method, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void defineEntity(String name, char value) {
/* 356 */     CharKey character = new CharKey(value);
/*     */     
/* 358 */     this.m_charToEntityRef.put(character, name);
/* 359 */     set(value);
/*     */   }
/*     */   
/* 362 */   private CharInfo(String entitiesResource, String method, boolean internal) { this.m_charKey = new CharKey(); ResourceBundle entities = null; boolean noExtraEntities = true; if (internal)
/*     */       try { entities = ResourceBundle.getBundle(entitiesResource); } catch (Exception exception) {}  if (entities != null) { Enumeration keys = entities.getKeys(); while (keys.hasMoreElements()) { String name = keys.nextElement(); String value = entities.getString(name); int code = Integer.parseInt(value); defineEntity(name, (char)code); if (extraEntity(code))
/*     */           noExtraEntities = false;  }  set(10); set(S_CARRIAGERETURN); } else { InputStream is = null; try { BufferedReader bufferedReader; if (internal) { is = CharInfo.class.getResourceAsStream(entitiesResource); } else { ClassLoader cl = ObjectFactory.findClassLoader(); if (cl == null) { is = ClassLoader.getSystemResourceAsStream(entitiesResource); } else { is = cl.getResourceAsStream(entitiesResource); }  if (is == null)
/*     */             try { URL url = new URL(entitiesResource); is = url.openStream(); } catch (Exception exception) {}  }  if (is == null)
/*     */           throw new RuntimeException(XMLMessages.createXMLMessage("ER_RESOURCE_COULD_NOT_FIND", new Object[] { entitiesResource, entitiesResource }));  try { bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8")); } catch (UnsupportedEncodingException e) { bufferedReader = new BufferedReader(new InputStreamReader(is)); }  String line = bufferedReader.readLine(); while (line != null) { if (line.length() == 0 || line.charAt(0) == '#') { line = bufferedReader.readLine(); continue; }
/*     */            int index = line.indexOf(' '); if (index > 1) { String name = line.substring(0, index); index++; if (index < line.length()) { String value = line.substring(index); index = value.indexOf(' '); if (index > 0)
/*     */                 value = value.substring(0, index);  int code = Integer.parseInt(value); defineEntity(name, (char)code); if (extraEntity(code))
/*     */                 noExtraEntities = false;  }
/*     */              }
/*     */            line = bufferedReader.readLine(); }
/*     */          is.close(); set(10); set(S_CARRIAGERETURN); }
/*     */       catch (Exception e) { throw new RuntimeException(XMLMessages.createXMLMessage("ER_RESOURCE_COULD_NOT_LOAD", new Object[] { entitiesResource, e.toString(), entitiesResource, e.toString() })); }
/*     */       finally { if (is != null)
/*     */           try { is.close(); }
/*     */           catch (Exception exception) {}  }
/*     */        }
/*     */      for (int ch = 0; ch < 128; ch++) { if (((32 <= ch || 10 == ch || 13 == ch || 9 == ch) && !get(ch)) || 34 == ch) { this.isCleanTextASCII[ch] = true; this.isSpecialTextASCII[ch] = false; }
/*     */       else { this.isCleanTextASCII[ch] = false; this.isSpecialTextASCII[ch] = true; }
/*     */        }
/*     */      if ("xml".equals(method))
/*     */       set(9);  this.onlyQuotAmpLtGt = noExtraEntities; for (int i = 0; i < 128; i++)
/* 383 */       this.isSpecialAttrASCII[i] = get(i);  } public synchronized String getEntityNameForChar(char value) { this.m_charKey.setChar(value);
/* 384 */     return (String)this.m_charToEntityRef.get(this.m_charKey); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isSpecialAttrChar(int value) {
/* 402 */     if (value < 128) {
/* 403 */       return this.isSpecialAttrASCII[value];
/*     */     }
/*     */ 
/*     */     
/* 407 */     return get(value);
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
/*     */   public final boolean isSpecialTextChar(int value) {
/* 425 */     if (value < 128) {
/* 426 */       return this.isSpecialTextASCII[value];
/*     */     }
/*     */ 
/*     */     
/* 430 */     return get(value);
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
/*     */   public final boolean isTextASCIIClean(int value) {
/* 442 */     return this.isCleanTextASCII[value];
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
/*     */   public static CharInfo getCharInfo(String entitiesFileName, String method) {
/* 475 */     CharInfo charInfo = (CharInfo)m_getCharInfoCache.get(entitiesFileName);
/* 476 */     if (charInfo != null) {
/* 477 */       return charInfo;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 482 */     try { charInfo = new CharInfo(entitiesFileName, method, true);
/* 483 */       m_getCharInfoCache.put(entitiesFileName, charInfo);
/* 484 */       return charInfo; } catch (Exception exception)
/*     */     
/*     */     { 
/*     */       
/*     */       try { 
/* 489 */         return new CharInfo(entitiesFileName, method); } catch (Exception exception1)
/*     */       { String str;
/*     */ 
/*     */ 
/*     */         
/* 494 */         if (entitiesFileName.indexOf(':') < 0) {
/* 495 */           str = SystemIDResolver.getAbsoluteURIFromRelative(entitiesFileName);
/*     */         } else {
/*     */ 
/*     */           
/* 499 */           try { str = SystemIDResolver.getAbsoluteURI(entitiesFileName, null); } catch (TransformerException te)
/*     */           
/*     */           { 
/* 502 */             throw new WrappedRuntimeException(te); }
/*     */         
/*     */         } 
/*     */         
/* 506 */         return new CharInfo(str, method, false); }
/*     */        }
/*     */   
/*     */   }
/* 510 */   private static Hashtable m_getCharInfoCache = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int arrayIndex(int i) {
/* 519 */     return i >> 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int bit(int i) {
/* 528 */     int ret = 1 << (i & 0x1F);
/* 529 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] createEmptySetOfIntegers(int max) {
/* 537 */     this.firstWordNotUsed = 0;
/*     */     
/* 539 */     int[] arr = new int[arrayIndex(max - 1) + 1];
/* 540 */     return arr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void set(int i) {
/* 551 */     int j = i >> 5;
/* 552 */     int k = j + 1;
/*     */     
/* 554 */     if (this.firstWordNotUsed < k) {
/* 555 */       this.firstWordNotUsed = k;
/*     */     }
/* 557 */     this.array_of_bits[j] = this.array_of_bits[j] | 1 << (i & 0x1F);
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
/*     */   private final boolean get(int i) {
/* 573 */     boolean in_the_set = false;
/* 574 */     int j = i >> 5;
/*     */ 
/*     */     
/* 577 */     if (j < this.firstWordNotUsed) {
/* 578 */       in_the_set = ((this.array_of_bits[j] & 1 << (i & 0x1F)) != 0);
/*     */     }
/*     */     
/* 581 */     return in_the_set;
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
/*     */   private boolean extraEntity(int entityValue) {
/* 593 */     boolean extra = false;
/* 594 */     if (entityValue < 128) {
/*     */       
/* 596 */       switch (entityValue) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 34:
/*     */         case 38:
/*     */         case 60:
/*     */         case 62:
/* 607 */           return extra;
/*     */       } 
/*     */       extra = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/CharInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */