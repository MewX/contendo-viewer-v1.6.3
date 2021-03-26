/*     */ package org.apache.commons.codec.language.bm;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Scanner;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Lang
/*     */ {
/*     */   private static final class LangRule
/*     */   {
/*     */     private final boolean acceptOnMatch;
/*     */     private final Set<String> languages;
/*     */     private final Pattern pattern;
/*     */     
/*     */     private LangRule(Pattern pattern, Set<String> languages, boolean acceptOnMatch) {
/*  86 */       this.pattern = pattern;
/*  87 */       this.languages = languages;
/*  88 */       this.acceptOnMatch = acceptOnMatch;
/*     */     }
/*     */     
/*     */     public boolean matches(String txt) {
/*  92 */       return this.pattern.matcher(txt).find();
/*     */     }
/*     */   }
/*     */   
/*  96 */   private static final Map<NameType, Lang> Langs = new EnumMap<>(NameType.class);
/*     */   
/*     */   private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/%s_lang.txt";
/*     */   
/*     */   static {
/* 101 */     for (NameType s : NameType.values()) {
/* 102 */       Langs.put(s, loadFromResource(String.format("org/apache/commons/codec/language/bm/%s_lang.txt", new Object[] { s.getName() }), Languages.getInstance(s)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final Languages languages;
/*     */   
/*     */   private final List<LangRule> rules;
/*     */ 
/*     */   
/*     */   public static Lang instance(NameType nameType) {
/* 114 */     return Langs.get(nameType);
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
/*     */   public static Lang loadFromResource(String languageRulesResourceName, Languages languages) {
/* 130 */     List<LangRule> rules = new ArrayList<>();
/* 131 */     InputStream lRulesIS = Lang.class.getClassLoader().getResourceAsStream(languageRulesResourceName);
/*     */     
/* 133 */     if (lRulesIS == null) {
/* 134 */       throw new IllegalStateException("Unable to resolve required resource:org/apache/commons/codec/language/bm/%s_lang.txt");
/*     */     }
/*     */     
/* 137 */     try (Scanner scanner = new Scanner(lRulesIS, "UTF-8")) {
/* 138 */       boolean inExtendedComment = false;
/* 139 */       while (scanner.hasNextLine()) {
/* 140 */         String rawLine = scanner.nextLine();
/* 141 */         String line = rawLine;
/* 142 */         if (inExtendedComment) {
/*     */           
/* 144 */           if (line.endsWith("*/"))
/* 145 */             inExtendedComment = false; 
/*     */           continue;
/*     */         } 
/* 148 */         if (line.startsWith("/*")) {
/* 149 */           inExtendedComment = true;
/*     */           continue;
/*     */         } 
/* 152 */         int cmtI = line.indexOf("//");
/* 153 */         if (cmtI >= 0) {
/* 154 */           line = line.substring(0, cmtI);
/*     */         }
/*     */ 
/*     */         
/* 158 */         line = line.trim();
/*     */         
/* 160 */         if (line.length() == 0) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 165 */         String[] parts = line.split("\\s+");
/*     */         
/* 167 */         if (parts.length != 3) {
/* 168 */           throw new IllegalArgumentException("Malformed line '" + rawLine + "' in language resource '" + languageRulesResourceName + "'");
/*     */         }
/*     */ 
/*     */         
/* 172 */         Pattern pattern = Pattern.compile(parts[0]);
/* 173 */         String[] langs = parts[1].split("\\+");
/* 174 */         boolean accept = parts[2].equals("true");
/*     */         
/* 176 */         rules.add(new LangRule(pattern, new HashSet(Arrays.asList((Object[])langs)), accept));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 181 */     return new Lang(rules, languages);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Lang(List<LangRule> rules, Languages languages) {
/* 188 */     this.rules = Collections.unmodifiableList(rules);
/* 189 */     this.languages = languages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String guessLanguage(String text) {
/* 200 */     Languages.LanguageSet ls = guessLanguages(text);
/* 201 */     return ls.isSingleton() ? ls.getAny() : "any";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Languages.LanguageSet guessLanguages(String input) {
/* 212 */     String text = input.toLowerCase(Locale.ENGLISH);
/*     */     
/* 214 */     Set<String> langs = new HashSet<>(this.languages.getLanguages());
/* 215 */     for (LangRule rule : this.rules) {
/* 216 */       if (rule.matches(text)) {
/* 217 */         if (rule.acceptOnMatch) {
/* 218 */           langs.retainAll(rule.languages); continue;
/*     */         } 
/* 220 */         langs.removeAll(rule.languages);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 225 */     Languages.LanguageSet ls = Languages.LanguageSet.from(langs);
/* 226 */     return ls.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : ls;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/bm/Lang.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */