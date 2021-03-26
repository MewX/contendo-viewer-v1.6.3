/*     */ package org.apache.commons.codec.language.bm;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Scanner;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
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
/*     */ 
/*     */ public class Rule
/*     */ {
/*     */   public static final class Phoneme
/*     */     implements PhonemeExpr
/*     */   {
/*  85 */     public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>()
/*     */       {
/*     */         public int compare(Rule.Phoneme o1, Rule.Phoneme o2) {
/*  88 */           for (int i = 0; i < o1.phonemeText.length(); i++) {
/*  89 */             if (i >= o2.phonemeText.length()) {
/*  90 */               return 1;
/*     */             }
/*  92 */             int c = o1.phonemeText.charAt(i) - o2.phonemeText.charAt(i);
/*  93 */             if (c != 0) {
/*  94 */               return c;
/*     */             }
/*     */           } 
/*     */           
/*  98 */           if (o1.phonemeText.length() < o2.phonemeText.length()) {
/*  99 */             return -1;
/*     */           }
/*     */           
/* 102 */           return 0;
/*     */         }
/*     */       };
/*     */     
/*     */     private final StringBuilder phonemeText;
/*     */     private final Languages.LanguageSet languages;
/*     */     
/*     */     public Phoneme(CharSequence phonemeText, Languages.LanguageSet languages) {
/* 110 */       this.phonemeText = new StringBuilder(phonemeText);
/* 111 */       this.languages = languages;
/*     */     }
/*     */     
/*     */     public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight) {
/* 115 */       this(phonemeLeft.phonemeText, phonemeLeft.languages);
/* 116 */       this.phonemeText.append(phonemeRight.phonemeText);
/*     */     }
/*     */     
/*     */     public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight, Languages.LanguageSet languages) {
/* 120 */       this(phonemeLeft.phonemeText, languages);
/* 121 */       this.phonemeText.append(phonemeRight.phonemeText);
/*     */     }
/*     */     
/*     */     public Phoneme append(CharSequence str) {
/* 125 */       this.phonemeText.append(str);
/* 126 */       return this;
/*     */     }
/*     */     
/*     */     public Languages.LanguageSet getLanguages() {
/* 130 */       return this.languages;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterable<Phoneme> getPhonemes() {
/* 135 */       return Collections.singleton(this);
/*     */     }
/*     */     
/*     */     public CharSequence getPhonemeText() {
/* 139 */       return this.phonemeText;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Phoneme join(Phoneme right) {
/* 151 */       return new Phoneme(this.phonemeText.toString() + right.phonemeText.toString(), this.languages
/* 152 */           .restrictTo(right.languages));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Phoneme mergeWithLanguage(Languages.LanguageSet lang) {
/* 163 */       return new Phoneme(this.phonemeText.toString(), this.languages.merge(lang));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 168 */       return this.phonemeText.toString() + "[" + this.languages + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class PhonemeList
/*     */     implements PhonemeExpr
/*     */   {
/*     */     private final List<Rule.Phoneme> phonemes;
/*     */ 
/*     */     
/*     */     public PhonemeList(List<Rule.Phoneme> phonemes) {
/* 180 */       this.phonemes = phonemes;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<Rule.Phoneme> getPhonemes() {
/* 185 */       return this.phonemes;
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
/* 196 */   public static final RPattern ALL_STRINGS_RMATCHER = new RPattern()
/*     */     {
/*     */       public boolean isMatch(CharSequence input) {
/* 199 */         return true;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static final String ALL = "ALL";
/*     */   
/*     */   private static final String DOUBLE_QUOTE = "\"";
/*     */   
/*     */   private static final String HASH_INCLUDE = "#include";
/* 209 */   private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap<>(NameType.class); private final RPattern lContext;
/*     */   private final String pattern;
/*     */   
/*     */   static {
/* 213 */     for (NameType s : NameType.values()) {
/* 214 */       Map<RuleType, Map<String, Map<String, List<Rule>>>> rts = new EnumMap<>(RuleType.class);
/*     */ 
/*     */       
/* 217 */       for (RuleType rt : RuleType.values()) {
/* 218 */         Map<String, Map<String, List<Rule>>> rs = new HashMap<>();
/*     */         
/* 220 */         Languages ls = Languages.getInstance(s);
/* 221 */         for (String l : ls.getLanguages()) {
/* 222 */           try (Scanner scanner = createScanner(s, rt, l)) {
/* 223 */             rs.put(l, parseRules(scanner, createResourceName(s, rt, l)));
/* 224 */           } catch (IllegalStateException e) {
/* 225 */             throw new IllegalStateException("Problem processing " + createResourceName(s, rt, l), e);
/*     */           } 
/*     */         } 
/* 228 */         if (!rt.equals(RuleType.RULES)) {
/* 229 */           try (Scanner scanner = createScanner(s, rt, "common")) {
/* 230 */             rs.put("common", parseRules(scanner, createResourceName(s, rt, "common")));
/*     */           } 
/*     */         }
/*     */         
/* 234 */         rts.put(rt, Collections.unmodifiableMap(rs));
/*     */       } 
/*     */       
/* 237 */       RULES.put(s, Collections.unmodifiableMap(rts));
/*     */     } 
/*     */   }
/*     */   private final PhonemeExpr phoneme; private final RPattern rContext;
/*     */   private static boolean contains(CharSequence chars, char input) {
/* 242 */     for (int i = 0; i < chars.length(); i++) {
/* 243 */       if (chars.charAt(i) == input) {
/* 244 */         return true;
/*     */       }
/*     */     } 
/* 247 */     return false;
/*     */   }
/*     */   
/*     */   private static String createResourceName(NameType nameType, RuleType rt, String lang) {
/* 251 */     return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", new Object[] { nameType
/* 252 */           .getName(), rt.getName(), lang });
/*     */   }
/*     */   
/*     */   private static Scanner createScanner(NameType nameType, RuleType rt, String lang) {
/* 256 */     String resName = createResourceName(nameType, rt, lang);
/* 257 */     InputStream rulesIS = Languages.class.getClassLoader().getResourceAsStream(resName);
/*     */     
/* 259 */     if (rulesIS == null) {
/* 260 */       throw new IllegalArgumentException("Unable to load resource: " + resName);
/*     */     }
/*     */     
/* 263 */     return new Scanner(rulesIS, "UTF-8");
/*     */   }
/*     */   
/*     */   private static Scanner createScanner(String lang) {
/* 267 */     String resName = String.format("org/apache/commons/codec/language/bm/%s.txt", new Object[] { lang });
/* 268 */     InputStream rulesIS = Languages.class.getClassLoader().getResourceAsStream(resName);
/*     */     
/* 270 */     if (rulesIS == null) {
/* 271 */       throw new IllegalArgumentException("Unable to load resource: " + resName);
/*     */     }
/*     */     
/* 274 */     return new Scanner(rulesIS, "UTF-8");
/*     */   }
/*     */   
/*     */   private static boolean endsWith(CharSequence input, CharSequence suffix) {
/* 278 */     if (suffix.length() > input.length()) {
/* 279 */       return false;
/*     */     }
/* 281 */     for (int i = input.length() - 1, j = suffix.length() - 1; j >= 0; i--, j--) {
/* 282 */       if (input.charAt(i) != suffix.charAt(j)) {
/* 283 */         return false;
/*     */       }
/*     */     } 
/* 286 */     return true;
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
/*     */   public static List<Rule> getInstance(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
/* 302 */     Map<String, List<Rule>> ruleMap = getInstanceMap(nameType, rt, langs);
/* 303 */     List<Rule> allRules = new ArrayList<>();
/* 304 */     for (List<Rule> rules : ruleMap.values()) {
/* 305 */       allRules.addAll(rules);
/*     */     }
/* 307 */     return allRules;
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
/*     */   public static List<Rule> getInstance(NameType nameType, RuleType rt, String lang) {
/* 322 */     return getInstance(nameType, rt, Languages.LanguageSet.from(new HashSet<>(Arrays.asList(new String[] { lang }))));
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
/*     */   public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
/* 339 */     return langs.isSingleton() ? getInstanceMap(nameType, rt, langs.getAny()) : 
/* 340 */       getInstanceMap(nameType, rt, "any");
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
/*     */   public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, String lang) {
/* 357 */     Map<String, List<Rule>> rules = (Map<String, List<Rule>>)((Map)((Map)RULES.get(nameType)).get(rt)).get(lang);
/*     */     
/* 359 */     if (rules == null) {
/* 360 */       throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", new Object[] { nameType
/* 361 */               .getName(), rt.getName(), lang }));
/*     */     }
/*     */     
/* 364 */     return rules;
/*     */   }
/*     */   
/*     */   private static Phoneme parsePhoneme(String ph) {
/* 368 */     int open = ph.indexOf("[");
/* 369 */     if (open >= 0) {
/* 370 */       if (!ph.endsWith("]")) {
/* 371 */         throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
/*     */       }
/* 373 */       String before = ph.substring(0, open);
/* 374 */       String in = ph.substring(open + 1, ph.length() - 1);
/* 375 */       Set<String> langs = new HashSet<>(Arrays.asList(in.split("[+]")));
/*     */       
/* 377 */       return new Phoneme(before, Languages.LanguageSet.from(langs));
/*     */     } 
/* 379 */     return new Phoneme(ph, Languages.ANY_LANGUAGE);
/*     */   }
/*     */   
/*     */   private static PhonemeExpr parsePhonemeExpr(String ph) {
/* 383 */     if (ph.startsWith("(")) {
/* 384 */       if (!ph.endsWith(")")) {
/* 385 */         throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
/*     */       }
/*     */       
/* 388 */       List<Phoneme> phs = new ArrayList<>();
/* 389 */       String body = ph.substring(1, ph.length() - 1);
/* 390 */       for (String part : body.split("[|]")) {
/* 391 */         phs.add(parsePhoneme(part));
/*     */       }
/* 393 */       if (body.startsWith("|") || body.endsWith("|")) {
/* 394 */         phs.add(new Phoneme("", Languages.ANY_LANGUAGE));
/*     */       }
/*     */       
/* 397 */       return new PhonemeList(phs);
/*     */     } 
/* 399 */     return parsePhoneme(ph);
/*     */   }
/*     */   
/*     */   private static Map<String, List<Rule>> parseRules(Scanner scanner, final String location) {
/* 403 */     Map<String, List<Rule>> lines = new HashMap<>();
/* 404 */     int currentLine = 0;
/*     */     
/* 406 */     boolean inMultilineComment = false;
/* 407 */     while (scanner.hasNextLine()) {
/* 408 */       currentLine++;
/* 409 */       String rawLine = scanner.nextLine();
/* 410 */       String line = rawLine;
/*     */       
/* 412 */       if (inMultilineComment) {
/* 413 */         if (line.endsWith("*/"))
/* 414 */           inMultilineComment = false; 
/*     */         continue;
/*     */       } 
/* 417 */       if (line.startsWith("/*")) {
/* 418 */         inMultilineComment = true;
/*     */         continue;
/*     */       } 
/* 421 */       int cmtI = line.indexOf("//");
/* 422 */       if (cmtI >= 0) {
/* 423 */         line = line.substring(0, cmtI);
/*     */       }
/*     */ 
/*     */       
/* 427 */       line = line.trim();
/*     */       
/* 429 */       if (line.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 433 */       if (line.startsWith("#include")) {
/*     */         
/* 435 */         String incl = line.substring("#include".length()).trim();
/* 436 */         if (incl.contains(" ")) {
/* 437 */           throw new IllegalArgumentException("Malformed import statement '" + rawLine + "' in " + location);
/*     */         }
/*     */         
/* 440 */         try (Scanner hashIncludeScanner = createScanner(incl)) {
/* 441 */           lines.putAll(parseRules(hashIncludeScanner, location + "->" + incl));
/*     */         } 
/*     */         continue;
/*     */       } 
/* 445 */       String[] parts = line.split("\\s+");
/* 446 */       if (parts.length != 4) {
/* 447 */         throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
/*     */       }
/*     */       
/*     */       try {
/* 451 */         final String pat = stripQuotes(parts[0]);
/* 452 */         final String lCon = stripQuotes(parts[1]);
/* 453 */         final String rCon = stripQuotes(parts[2]);
/* 454 */         PhonemeExpr ph = parsePhonemeExpr(stripQuotes(parts[3]));
/* 455 */         final int cLine = currentLine;
/* 456 */         Rule r = new Rule(pat, lCon, rCon, ph) {
/* 457 */             private final int myLine = cLine;
/* 458 */             private final String loc = location;
/*     */ 
/*     */             
/*     */             public String toString() {
/* 462 */               StringBuilder sb = new StringBuilder();
/* 463 */               sb.append("Rule");
/* 464 */               sb.append("{line=").append(this.myLine);
/* 465 */               sb.append(", loc='").append(this.loc).append('\'');
/* 466 */               sb.append(", pat='").append(pat).append('\'');
/* 467 */               sb.append(", lcon='").append(lCon).append('\'');
/* 468 */               sb.append(", rcon='").append(rCon).append('\'');
/* 469 */               sb.append('}');
/* 470 */               return sb.toString();
/*     */             }
/*     */           };
/* 473 */         String patternKey = r.pattern.substring(0, 1);
/* 474 */         List<Rule> rules = lines.get(patternKey);
/* 475 */         if (rules == null) {
/* 476 */           rules = new ArrayList<>();
/* 477 */           lines.put(patternKey, rules);
/*     */         } 
/* 479 */         rules.add(r);
/* 480 */       } catch (IllegalArgumentException e) {
/* 481 */         throw new IllegalStateException("Problem parsing line '" + currentLine + "' in " + location, e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 489 */     return lines;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static RPattern pattern(final String regex) {
/* 500 */     boolean startsWith = regex.startsWith("^");
/* 501 */     boolean endsWith = regex.endsWith("$");
/* 502 */     final String content = regex.substring(startsWith ? 1 : 0, endsWith ? (regex.length() - 1) : regex.length());
/* 503 */     boolean boxes = content.contains("[");
/*     */     
/* 505 */     if (!boxes) {
/* 506 */       if (startsWith && endsWith) {
/*     */         
/* 508 */         if (content.length() == 0)
/*     */         {
/* 510 */           return new RPattern()
/*     */             {
/*     */               public boolean isMatch(CharSequence input) {
/* 513 */                 return (input.length() == 0);
/*     */               }
/*     */             };
/*     */         }
/* 517 */         return new RPattern()
/*     */           {
/*     */             public boolean isMatch(CharSequence input) {
/* 520 */               return input.equals(content); }
/*     */           };
/*     */       } 
/* 523 */       if ((startsWith || endsWith) && content.length() == 0)
/*     */       {
/* 525 */         return ALL_STRINGS_RMATCHER; } 
/* 526 */       if (startsWith)
/*     */       {
/* 528 */         return new RPattern()
/*     */           {
/*     */             public boolean isMatch(CharSequence input) {
/* 531 */               return Rule.startsWith(input, content);
/*     */             }
/*     */           }; } 
/* 534 */       if (endsWith)
/*     */       {
/* 536 */         return new RPattern()
/*     */           {
/*     */             public boolean isMatch(CharSequence input) {
/* 539 */               return Rule.endsWith(input, content);
/*     */             }
/*     */           };
/*     */       }
/*     */     } else {
/* 544 */       boolean startsWithBox = content.startsWith("[");
/* 545 */       boolean endsWithBox = content.endsWith("]");
/*     */       
/* 547 */       if (startsWithBox && endsWithBox) {
/* 548 */         String boxContent = content.substring(1, content.length() - 1);
/* 549 */         if (!boxContent.contains("[")) {
/*     */           
/* 551 */           boolean negate = boxContent.startsWith("^");
/* 552 */           if (negate) {
/* 553 */             boxContent = boxContent.substring(1);
/*     */           }
/* 555 */           final String bContent = boxContent;
/* 556 */           final boolean shouldMatch = !negate;
/*     */           
/* 558 */           if (startsWith && endsWith)
/*     */           {
/* 560 */             return new RPattern()
/*     */               {
/*     */                 public boolean isMatch(CharSequence input) {
/* 563 */                   return (input.length() == 1 && Rule.contains(bContent, input.charAt(0)) == shouldMatch);
/*     */                 }
/*     */               }; } 
/* 566 */           if (startsWith)
/*     */           {
/* 568 */             return new RPattern()
/*     */               {
/*     */                 public boolean isMatch(CharSequence input) {
/* 571 */                   return (input.length() > 0 && Rule.contains(bContent, input.charAt(0)) == shouldMatch);
/*     */                 }
/*     */               }; } 
/* 574 */           if (endsWith)
/*     */           {
/* 576 */             return new RPattern()
/*     */               {
/*     */                 public boolean isMatch(CharSequence input) {
/* 579 */                   return (input.length() > 0 && Rule
/* 580 */                     .contains(bContent, input.charAt(input.length() - 1)) == shouldMatch);
/*     */                 }
/*     */               };
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 588 */     return new RPattern() {
/* 589 */         Pattern pattern = Pattern.compile(regex);
/*     */ 
/*     */         
/*     */         public boolean isMatch(CharSequence input) {
/* 593 */           Matcher matcher = this.pattern.matcher(input);
/* 594 */           return matcher.find();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static boolean startsWith(CharSequence input, CharSequence prefix) {
/* 600 */     if (prefix.length() > input.length()) {
/* 601 */       return false;
/*     */     }
/* 603 */     for (int i = 0; i < prefix.length(); i++) {
/* 604 */       if (input.charAt(i) != prefix.charAt(i)) {
/* 605 */         return false;
/*     */       }
/*     */     } 
/* 608 */     return true;
/*     */   }
/*     */   
/*     */   private static String stripQuotes(String str) {
/* 612 */     if (str.startsWith("\"")) {
/* 613 */       str = str.substring(1);
/*     */     }
/*     */     
/* 616 */     if (str.endsWith("\"")) {
/* 617 */       str = str.substring(0, str.length() - 1);
/*     */     }
/*     */     
/* 620 */     return str;
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
/*     */   public Rule(String pattern, String lContext, String rContext, PhonemeExpr phoneme) {
/* 644 */     this.pattern = pattern;
/* 645 */     this.lContext = pattern(lContext + "$");
/* 646 */     this.rContext = pattern("^" + rContext);
/* 647 */     this.phoneme = phoneme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RPattern getLContext() {
/* 656 */     return this.lContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPattern() {
/* 665 */     return this.pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhonemeExpr getPhoneme() {
/* 674 */     return this.phoneme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RPattern getRContext() {
/* 683 */     return this.rContext;
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
/*     */   public boolean patternAndContextMatches(CharSequence input, int i) {
/* 698 */     if (i < 0) {
/* 699 */       throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
/*     */     }
/*     */     
/* 702 */     int patternLength = this.pattern.length();
/* 703 */     int ipl = i + patternLength;
/*     */     
/* 705 */     if (ipl > input.length())
/*     */     {
/* 707 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 712 */     if (!input.subSequence(i, ipl).equals(this.pattern))
/* 713 */       return false; 
/* 714 */     if (!this.rContext.isMatch(input.subSequence(ipl, input.length()))) {
/* 715 */       return false;
/*     */     }
/* 717 */     return this.lContext.isMatch(input.subSequence(0, i));
/*     */   }
/*     */   
/*     */   public static interface RPattern {
/*     */     boolean isMatch(CharSequence param1CharSequence);
/*     */   }
/*     */   
/*     */   public static interface PhonemeExpr {
/*     */     Iterable<Rule.Phoneme> getPhonemes();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/bm/Rule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */