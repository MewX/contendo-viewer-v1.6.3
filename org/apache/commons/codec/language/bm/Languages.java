/*     */ package org.apache.commons.codec.language.bm;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Scanner;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Languages
/*     */ {
/*     */   public static final String ANY = "any";
/*     */   
/*     */   public static abstract class LanguageSet
/*     */   {
/*     */     public static LanguageSet from(Set<String> langs) {
/*  64 */       return langs.isEmpty() ? Languages.NO_LANGUAGES : new Languages.SomeLanguages(langs);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract boolean contains(String param1String);
/*     */     
/*     */     public abstract String getAny();
/*     */     
/*     */     public abstract boolean isEmpty();
/*     */     
/*     */     public abstract boolean isSingleton();
/*     */     
/*     */     public abstract LanguageSet restrictTo(LanguageSet param1LanguageSet);
/*     */     
/*     */     abstract LanguageSet merge(LanguageSet param1LanguageSet);
/*     */   }
/*     */   
/*     */   public static final class SomeLanguages
/*     */     extends LanguageSet
/*     */   {
/*     */     private final Set<String> languages;
/*     */     
/*     */     private SomeLanguages(Set<String> languages) {
/*  87 */       this.languages = Collections.unmodifiableSet(languages);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean contains(String language) {
/*  92 */       return this.languages.contains(language);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getAny() {
/*  97 */       return this.languages.iterator().next();
/*     */     }
/*     */     
/*     */     public Set<String> getLanguages() {
/* 101 */       return this.languages;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 106 */       return this.languages.isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSingleton() {
/* 111 */       return (this.languages.size() == 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
/* 116 */       if (other == Languages.NO_LANGUAGES)
/* 117 */         return other; 
/* 118 */       if (other == Languages.ANY_LANGUAGE) {
/* 119 */         return this;
/*     */       }
/* 121 */       SomeLanguages sl = (SomeLanguages)other;
/* 122 */       Set<String> ls = new HashSet<>(Math.min(this.languages.size(), sl.languages.size()));
/* 123 */       for (String lang : this.languages) {
/* 124 */         if (sl.languages.contains(lang)) {
/* 125 */           ls.add(lang);
/*     */         }
/*     */       } 
/* 128 */       return from(ls);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Languages.LanguageSet merge(Languages.LanguageSet other) {
/* 134 */       if (other == Languages.NO_LANGUAGES)
/* 135 */         return this; 
/* 136 */       if (other == Languages.ANY_LANGUAGE) {
/* 137 */         return other;
/*     */       }
/* 139 */       SomeLanguages sl = (SomeLanguages)other;
/* 140 */       Set<String> ls = new HashSet<>(this.languages);
/* 141 */       for (String lang : sl.languages) {
/* 142 */         ls.add(lang);
/*     */       }
/* 144 */       return from(ls);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 150 */       return "Languages(" + this.languages.toString() + ")";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   private static final Map<NameType, Languages> LANGUAGES = new EnumMap<>(NameType.class); private final Set<String> languages;
/*     */   
/*     */   static {
/* 160 */     for (NameType s : NameType.values()) {
/* 161 */       LANGUAGES.put(s, getInstance(langResourceName(s)));
/*     */     }
/*     */   }
/*     */   
/*     */   public static Languages getInstance(NameType nameType) {
/* 166 */     return LANGUAGES.get(nameType);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Languages getInstance(String languagesResourceName) {
/* 171 */     Set<String> ls = new HashSet<>();
/* 172 */     InputStream langIS = Languages.class.getClassLoader().getResourceAsStream(languagesResourceName);
/*     */     
/* 174 */     if (langIS == null) {
/* 175 */       throw new IllegalArgumentException("Unable to resolve required resource: " + languagesResourceName);
/*     */     }
/*     */     
/* 178 */     try (Scanner lsScanner = new Scanner(langIS, "UTF-8")) {
/* 179 */       boolean inExtendedComment = false;
/* 180 */       while (lsScanner.hasNextLine()) {
/* 181 */         String line = lsScanner.nextLine().trim();
/* 182 */         if (inExtendedComment) {
/* 183 */           if (line.endsWith("*/"))
/* 184 */             inExtendedComment = false; 
/*     */           continue;
/*     */         } 
/* 187 */         if (line.startsWith("/*")) {
/* 188 */           inExtendedComment = true; continue;
/* 189 */         }  if (line.length() > 0) {
/* 190 */           ls.add(line);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 196 */     return new Languages(Collections.unmodifiableSet(ls));
/*     */   }
/*     */   
/*     */   private static String langResourceName(NameType nameType) {
/* 200 */     return String.format("org/apache/commons/codec/language/bm/%s_languages.txt", new Object[] { nameType.getName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public static final LanguageSet NO_LANGUAGES = new LanguageSet()
/*     */     {
/*     */       public boolean contains(String language) {
/* 211 */         return false;
/*     */       }
/*     */ 
/*     */       
/*     */       public String getAny() {
/* 216 */         throw new NoSuchElementException("Can't fetch any language from the empty language set.");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean isEmpty() {
/* 221 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean isSingleton() {
/* 226 */         return false;
/*     */       }
/*     */ 
/*     */       
/*     */       public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
/* 231 */         return this;
/*     */       }
/*     */ 
/*     */       
/*     */       public Languages.LanguageSet merge(Languages.LanguageSet other) {
/* 236 */         return other;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 241 */         return "NO_LANGUAGES";
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public static final LanguageSet ANY_LANGUAGE = new LanguageSet()
/*     */     {
/*     */       public boolean contains(String language) {
/* 251 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       public String getAny() {
/* 256 */         throw new NoSuchElementException("Can't fetch any language from the any language set.");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean isEmpty() {
/* 261 */         return false;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean isSingleton() {
/* 266 */         return false;
/*     */       }
/*     */ 
/*     */       
/*     */       public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
/* 271 */         return other;
/*     */       }
/*     */ 
/*     */       
/*     */       public Languages.LanguageSet merge(Languages.LanguageSet other) {
/* 276 */         return other;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 281 */         return "ANY_LANGUAGE";
/*     */       }
/*     */     };
/*     */   
/*     */   private Languages(Set<String> languages) {
/* 286 */     this.languages = languages;
/*     */   }
/*     */   
/*     */   public Set<String> getLanguages() {
/* 290 */     return this.languages;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/bm/Languages.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */