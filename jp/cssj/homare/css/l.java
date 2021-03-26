/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.sakae.sac.css.AttributeCondition;
/*     */ import jp.cssj.sakae.sac.css.CombinatorCondition;
/*     */ import jp.cssj.sakae.sac.css.Condition;
/*     */ import jp.cssj.sakae.sac.css.ConditionalSelector;
/*     */ import jp.cssj.sakae.sac.css.DescendantSelector;
/*     */ import jp.cssj.sakae.sac.css.ElementSelector;
/*     */ import jp.cssj.sakae.sac.css.LangCondition;
/*     */ import jp.cssj.sakae.sac.css.Selector;
/*     */ import jp.cssj.sakae.sac.css.SiblingSelector;
/*     */ import jp.cssj.sakae.sac.css.SimpleSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class l
/*     */ {
/*     */   private static final boolean b = false;
/*  28 */   private final List<a> c = new ArrayList<>();
/*     */   
/*     */   public final d a;
/*     */   
/*     */   public l(d styleSheet) {
/*  33 */     this.a = styleSheet;
/*     */   }
/*     */   
/*     */   public l a(int up) {
/*  37 */     l styleContext = new l(this.a);
/*  38 */     for (int i = 0; i < this.c.size() - up; i++) {
/*  39 */       styleContext.c.add(this.c.get(i));
/*     */     }
/*  41 */     return styleContext;
/*     */   }
/*     */   
/*     */   private static String c(a ce) {
/*  45 */     StringBuffer buff = new StringBuffer();
/*  46 */     if (ce.C != null) {
/*  47 */       buff.append(ce.C);
/*     */     } else {
/*  49 */       buff.append(ce.F);
/*     */     } 
/*  51 */     buff.append('/');
/*  52 */     return buff.toString();
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
/*     */   public void a(a ce) {
/*  64 */     this.c.add(ce);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/*  71 */     a ce = this.c.remove(this.c.size() - 1);
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
/*     */   public f b(a page) {
/*  84 */     f result = new f();
/*  85 */     result.a(this.a.b);
/*  86 */     if (page.a((byte)2)) {
/*  87 */       result.a(this.a.d);
/*     */     }
/*  89 */     if (page.a((byte)3)) {
/*  90 */       result.a(this.a.e);
/*     */     }
/*  92 */     if (page.a((byte)1)) {
/*  93 */       result.a(this.a.c);
/*     */     }
/*  95 */     return result;
/*     */   }
/*     */   
/*     */   public List<d.a> b() {
/*  99 */     return this.a.f;
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
/*     */   public f a(f declaration) {
/* 117 */     List<i> result = null;
/* 118 */     for (Iterator<?> iterator = this.a.a.values().iterator(); iterator.hasNext(); ) {
/* 119 */       i rule = (i)iterator.next();
/* 120 */       Selector selector = rule.a();
/* 121 */       boolean first = true;
/* 122 */       boolean child = false;
/* 123 */       boolean sibling = false;
/* 124 */       a ce = null;
/* 125 */       for (int j = this.c.size() - 1; j >= 0; j--) {
/* 126 */         DescendantSelector descendantSelector; SiblingSelector siblingSelector; SimpleSelector simpleSelector, simpleSelector1; if (sibling) {
/* 127 */           sibling = false;
/*     */         } else {
/* 129 */           ce = this.c.get(j);
/*     */         } 
/* 131 */         switch (selector.getSelectorType()) {
/*     */           
/*     */           case 11:
/* 134 */             descendantSelector = (DescendantSelector)selector;
/* 135 */             simpleSelector1 = descendantSelector.getSimpleSelector();
/* 136 */             if (a(simpleSelector1, ce)) {
/* 137 */               selector = descendantSelector.getAncestorSelector();
/* 138 */               child = true; break;
/* 139 */             }  if (first || (!ce.a() && child)) {
/*     */               break;
/*     */             }
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 10:
/* 147 */             descendantSelector = (DescendantSelector)selector;
/* 148 */             simpleSelector1 = descendantSelector.getSimpleSelector();
/* 149 */             if (a(simpleSelector1, ce)) {
/* 150 */               selector = descendantSelector.getAncestorSelector();
/* 151 */               child = false; break;
/* 152 */             }  if (first || (!ce.a() && child)) {
/*     */               break;
/*     */             }
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 12:
/* 160 */             siblingSelector = (SiblingSelector)selector;
/* 161 */             simpleSelector1 = siblingSelector.getSiblingSelector();
/* 162 */             if (a(simpleSelector1, ce)) {
/* 163 */               selector = siblingSelector.getSelector();
/* 164 */               child = true;
/* 165 */               ce = ce.I;
/* 166 */               if (ce == null) {
/*     */                 break;
/*     */               }
/* 169 */               j++;
/* 170 */               sibling = true; break;
/* 171 */             }  if (first || (!ce.a() && child)) {
/*     */               break;
/*     */             }
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           default:
/* 179 */             simpleSelector = (SimpleSelector)selector;
/* 180 */             if (a(simpleSelector, ce)) {
/* 181 */               if (result == null) {
/* 182 */                 result = new ArrayList<>();
/*     */               }
/* 184 */               result.add(rule); break;
/*     */             } 
/* 186 */             if (first || (!ce.a() && child)) {
/*     */               break;
/*     */             }
/*     */             break;
/*     */         } 
/*     */         
/* 192 */         first = false;
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     if (result == null) {
/* 197 */       return declaration;
/*     */     }
/* 199 */     if (declaration == null) {
/* 200 */       declaration = new f();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 205 */     Collections.sort(result, j.a);
/*     */ 
/*     */     
/* 208 */     for (int i = 0; i < result.size(); i++) {
/* 209 */       i rule = result.get(i);
/* 210 */       f tempDecl = rule.b();
/* 211 */       declaration.a(tempDecl);
/*     */     } 
/* 213 */     return declaration; } private static boolean a(SimpleSelector selector, a ce) { ElementSelector elementSelector; ConditionalSelector conditionalSelector;
/*     */     String uri;
/*     */     String name;
/*     */     String str1;
/* 217 */     switch (selector.getSelectorType()) {
/*     */       
/*     */       case 4:
/* 220 */         elementSelector = (ElementSelector)selector;
/* 221 */         if (ce.a()) {
/* 222 */           return false;
/*     */         }
/*     */         
/* 225 */         uri = elementSelector.getNamespaceURI();
/* 226 */         str1 = elementSelector.getLocalName();
/*     */         
/* 228 */         if (uri == null) {
/* 229 */           if (str1 == null) {
/* 230 */             return true;
/*     */           }
/* 232 */           if (ce.B == null) {
/* 233 */             return false;
/*     */           }
/* 235 */           if (ce.B.equals("http://www.w3.org/1999/xhtml")) {
/* 236 */             str1 = str1.toLowerCase();
/*     */           }
/* 238 */           return str1.equals(ce.C);
/*     */         } 
/*     */         
/* 241 */         return ((str1 == null || str1.equals(ce.C)) && uri.equals(ce.B));
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 246 */         if (!ce.a()) {
/* 247 */           return false;
/*     */         }
/* 249 */         elementSelector = (ElementSelector)selector;
/* 250 */         name = elementSelector.getLocalName();
/* 251 */         return name.equals(ce.C);
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 256 */         conditionalSelector = (ConditionalSelector)selector;
/* 257 */         return (a(conditionalSelector.getCondition(), ce) && 
/* 258 */           a(conditionalSelector.getSimpleSelector(), ce));
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
/* 269 */     throw new IllegalStateException(String.valueOf(selector.getSelectorType())); } private static boolean a(Condition condition, a ce) { CombinatorCondition combinatorCondition; AttributeCondition classCondition; AttributeCondition attrCondition; LangCondition langCondition; String styleClass; String pseudoClass; String id; String uri; String value; byte pc; String name; String lang;
/*     */     String str1;
/*     */     String values;
/*     */     String str2;
/*     */     StringTokenizer i;
/* 274 */     switch (condition.getConditionType()) {
/*     */       
/*     */       case 0:
/* 277 */         combinatorCondition = (CombinatorCondition)condition;
/* 278 */         return (a(combinatorCondition.getFirstCondition(), ce) && 
/* 279 */           a(combinatorCondition.getSecondCondition(), ce));
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 284 */         classCondition = (AttributeCondition)condition;
/* 285 */         styleClass = classCondition.getValue();
/* 286 */         return ce.a(styleClass);
/*     */ 
/*     */ 
/*     */       
/*     */       case 10:
/* 291 */         classCondition = (AttributeCondition)condition;
/* 292 */         pseudoClass = classCondition.getValue();
/* 293 */         if (pseudoClass == null || pseudoClass.length() == 0) {
/* 294 */           return false;
/*     */         }
/* 296 */         pc = 0;
/* 297 */         switch (pseudoClass.charAt(0)) {
/*     */           case 'F':
/*     */           case 'f':
/* 300 */             if (pseudoClass.equalsIgnoreCase("first")) {
/* 301 */               pc = 1; break;
/* 302 */             }  if (pseudoClass.equalsIgnoreCase("first-child")) {
/* 303 */               pc = 6;
/*     */             }
/*     */             break;
/*     */           case 'L':
/*     */           case 'l':
/* 308 */             if (pseudoClass.equalsIgnoreCase("link")) {
/* 309 */               pc = 7; break;
/* 310 */             }  if (pseudoClass.equalsIgnoreCase("left")) {
/* 311 */               pc = 2;
/*     */             }
/*     */             break;
/*     */           case 'R':
/*     */           case 'r':
/* 316 */             if (pseudoClass.equalsIgnoreCase("right")) {
/* 317 */               pc = 3;
/*     */             }
/*     */             break;
/*     */         } 
/* 321 */         return ce.a(pc);
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 326 */         classCondition = (AttributeCondition)condition;
/* 327 */         id = classCondition.getValue();
/* 328 */         return id.equalsIgnoreCase(ce.D);
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 333 */         if (ce.H == null) {
/* 334 */           return false;
/*     */         }
/* 336 */         attrCondition = (AttributeCondition)condition;
/* 337 */         uri = attrCondition.getNamespaceURI();
/* 338 */         name = attrCondition.getLocalName();
/* 339 */         if (attrCondition.getSpecified()) {
/* 340 */           String str = attrCondition.getValue();
/* 341 */           if (uri == null) {
/* 342 */             return str.equalsIgnoreCase(ce.H.getValue(name));
/*     */           }
/* 344 */           return str.equalsIgnoreCase(ce.H.getValue(uri, name));
/*     */         } 
/* 346 */         if (uri == null) {
/* 347 */           return (ce.H.getValue(name) != null);
/*     */         }
/* 349 */         return (ce.H.getValue(uri, name) != null);
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/* 354 */         if (ce.H == null) {
/* 355 */           return false;
/*     */         }
/* 357 */         attrCondition = (AttributeCondition)condition;
/* 358 */         uri = attrCondition.getNamespaceURI();
/* 359 */         name = attrCondition.getLocalName();
/* 360 */         str1 = attrCondition.getValue();
/*     */         
/* 362 */         if (uri == null) {
/* 363 */           values = ce.H.getValue(name);
/*     */         } else {
/* 365 */           values = ce.H.getValue(uri, name);
/*     */         } 
/* 367 */         if (values == null) {
/* 368 */           return false;
/*     */         }
/* 370 */         for (i = new StringTokenizer(values, " "); i.hasMoreTokens();) {
/* 371 */           if (i.nextToken().equalsIgnoreCase(str1)) {
/* 372 */             return true;
/*     */           }
/*     */         } 
/*     */         
/* 376 */         return false;
/*     */ 
/*     */       
/*     */       case 8:
/* 380 */         if (ce.H == null) {
/* 381 */           return false;
/*     */         }
/* 383 */         attrCondition = (AttributeCondition)condition;
/* 384 */         uri = attrCondition.getNamespaceURI();
/* 385 */         name = attrCondition.getLocalName();
/* 386 */         str1 = attrCondition.getValue();
/*     */         
/* 388 */         if (uri == null) {
/* 389 */           str2 = ce.H.getValue(name);
/*     */         } else {
/* 391 */           str2 = ce.H.getValue(uri, name);
/*     */         } 
/* 393 */         if (str2 == null) {
/* 394 */           return false;
/*     */         }
/* 396 */         str2 = str2.toLowerCase();
/* 397 */         str1 = str1.toLowerCase();
/* 398 */         if (str2.startsWith(str1)) {
/* 399 */           return (str2.length() <= str1.length() || str2.charAt(str1.length()) == '-');
/*     */         }
/* 401 */         return false;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 407 */         langCondition = (LangCondition)condition;
/* 408 */         value = langCondition.getLang();
/* 409 */         if (ce.G == null) {
/* 410 */           return false;
/*     */         }
/* 412 */         lang = ce.G.getLanguage();
/* 413 */         return lang.equalsIgnoreCase(value);
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
/* 425 */     throw new IllegalStateException(String.valueOf(condition.getConditionType())); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */