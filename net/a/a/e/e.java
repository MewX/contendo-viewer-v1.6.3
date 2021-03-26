/*     */ package net.a.a.e;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.a.a.e.a.a.a;
/*     */ import net.a.a.e.a.a.b;
/*     */ import net.a.a.e.b.b;
/*     */ import net.a.a.e.b.d;
/*     */ import net.a.a.e.c.a.a;
/*     */ import net.a.a.e.c.b.b;
/*     */ import net.a.a.e.c.b.c;
/*     */ import net.a.a.e.c.b.d;
/*     */ import net.a.a.e.c.b.e;
/*     */ import net.a.a.e.c.b.f;
/*     */ import net.a.a.e.c.b.g;
/*     */ import net.a.a.e.c.b.h;
/*     */ import net.a.a.e.c.b.i;
/*     */ import net.a.a.e.c.b.j;
/*     */ import net.a.a.e.c.b.k;
/*     */ import net.a.a.e.c.c.d;
/*     */ import net.a.a.e.c.c.e;
/*     */ import net.a.a.e.c.c.f;
/*     */ import net.a.a.e.c.c.g;
/*     */ import net.a.a.e.c.c.h;
/*     */ import net.a.a.e.c.c.i;
/*     */ import net.a.a.e.c.c.j;
/*     */ import net.a.a.e.c.c.k;
/*     */ import net.a.a.e.c.c.l;
/*     */ import net.a.a.e.c.d.c;
/*     */ import net.a.a.e.c.d.d;
/*     */ import net.a.a.e.c.d.e;
/*     */ import net.a.a.e.c.d.f;
/*     */ import net.a.a.e.c.d.g;
/*     */ import net.a.a.e.c.d.h;
/*     */ import net.a.a.e.c.e.b;
/*     */ import net.a.a.e.c.e.c;
/*     */ import net.a.a.e.c.e.d;
/*     */ import net.a.a.e.c.e.e;
/*     */ import net.a.a.e.c.e.f;
/*     */ import net.a.a.e.c.e.g;
/*     */ import net.a.a.e.c.e.h;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ public final class e
/*     */ {
/*  53 */   private static final Log a = LogFactory.getLog(e.class);
/*     */   
/*  55 */   private static final Map<String, Constructor<? extends d>> b = new HashMap<String, Constructor<? extends d>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String a(String paramString) {
/*  62 */     int i = paramString.indexOf(':');
/*  63 */     if (i >= 0) {
/*  64 */       return paramString.substring(i + 1);
/*     */     }
/*  66 */     return paramString;
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
/*     */   public static Constructor<? extends d> a(String paramString1, String paramString2) {
/*  82 */     String str = a(paramString2);
/*  83 */     if (paramString1 == null || paramString1.length() == 0 || "http://www.w3.org/1998/Math/MathML"
/*  84 */       .equals(paramString1)) {
/*  85 */       return b.get(str);
/*     */     }
/*  87 */     return null;
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
/*     */   public static Element a(String paramString1, String paramString2, Document paramDocument) {
/*     */     b b;
/* 107 */     d d = null;
/*     */ 
/*     */     
/* 110 */     Constructor<? extends d> constructor = a(paramString1, paramString2);
/*     */     
/* 112 */     if (constructor != null) {
/*     */       try {
/* 114 */         d = constructor.newInstance(new Object[] { paramString2, paramDocument });
/*     */       }
/* 116 */       catch (InstantiationException instantiationException) {
/* 117 */         d = null;
/* 118 */       } catch (IllegalAccessException illegalAccessException) {
/* 119 */         d = null;
/* 120 */       } catch (InvocationTargetException invocationTargetException) {
/* 121 */         d = null;
/*     */       } 
/*     */     }
/*     */     
/* 125 */     if (d == null) {
/* 126 */       b = new b(paramString1, paramString2, (AbstractDocument)paramDocument);
/*     */     }
/*     */     
/* 129 */     return (Element)b;
/*     */   }
/*     */   
/*     */   private static void a(Class<? extends d> paramClass) {
/*     */     try {
/* 134 */       Field field = paramClass.getField("ELEMENT");
/* 135 */       String str = (String)field.get(null);
/* 136 */       b.put(str, paramClass.getConstructor(new Class[] { String.class, AbstractDocument.class }));
/*     */     }
/* 138 */     catch (NoSuchFieldException noSuchFieldException) {
/* 139 */       a.warn(paramClass.toString(), noSuchFieldException);
/* 140 */     } catch (NoSuchMethodException noSuchMethodException) {
/* 141 */       a.warn(paramClass.toString(), noSuchMethodException);
/* 142 */     } catch (IllegalAccessException illegalAccessException) {
/* 143 */       a.warn(paramClass.toString(), illegalAccessException);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 150 */     a((Class)d.class);
/* 151 */     a((Class)d.class);
/* 152 */     a((Class)e.class);
/* 153 */     a((Class)b.class);
/* 154 */     a((Class)g.class);
/* 155 */     a((Class)i.class);
/* 156 */     a((Class)g.class);
/* 157 */     a((Class)d.class);
/* 158 */     a((Class)f.class);
/* 159 */     a((Class)l.class);
/* 160 */     a((Class)h.class);
/* 161 */     a((Class)j.class);
/* 162 */     a((Class)e.class);
/* 163 */     a((Class)k.class);
/* 164 */     a((Class)g.class);
/* 165 */     a((Class)f.class);
/* 166 */     a((Class)k.class);
/* 167 */     a((Class)j.class);
/* 168 */     a((Class)h.class);
/* 169 */     a((Class)f.class);
/* 170 */     a((Class)h.class);
/* 171 */     a((Class)e.class);
/* 172 */     a((Class)g.class);
/* 173 */     a((Class)e.class);
/* 174 */     a((Class)c.class);
/* 175 */     a((Class)d.class);
/* 176 */     a((Class)h.class);
/* 177 */     a((Class)i.class);
/* 178 */     a((Class)c.class);
/* 179 */     a((Class)d.class);
/* 180 */     a((Class)b.class);
/* 181 */     a((Class)a.class);
/* 182 */     a((Class)f.class);
/* 183 */     a((Class)c.class);
/* 184 */     a((Class)a.class);
/* 185 */     a((Class)b.class);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */