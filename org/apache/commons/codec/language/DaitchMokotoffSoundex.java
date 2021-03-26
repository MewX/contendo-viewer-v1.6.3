/*     */ package org.apache.commons.codec.language;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Scanner;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.codec.EncoderException;
/*     */ import org.apache.commons.codec.StringEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DaitchMokotoffSoundex
/*     */   implements StringEncoder
/*     */ {
/*     */   private static final String COMMENT = "//";
/*     */   private static final String DOUBLE_QUOTE = "\"";
/*     */   private static final String MULTILINE_COMMENT_END = "*/";
/*     */   private static final String MULTILINE_COMMENT_START = "/*";
/*     */   private static final String RESOURCE_FILE = "org/apache/commons/codec/language/dmrules.txt";
/*     */   private static final int MAX_LENGTH = 6;
/*     */   
/*     */   private static final class Branch
/*     */   {
/*  83 */     private final StringBuilder builder = new StringBuilder();
/*  84 */     private String lastReplacement = null;
/*  85 */     private String cachedString = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Branch createBranch() {
/*  94 */       Branch branch = new Branch();
/*  95 */       branch.builder.append(toString());
/*  96 */       branch.lastReplacement = this.lastReplacement;
/*  97 */       return branch;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 102 */       if (this == other) {
/* 103 */         return true;
/*     */       }
/* 105 */       if (!(other instanceof Branch)) {
/* 106 */         return false;
/*     */       }
/*     */       
/* 109 */       return toString().equals(((Branch)other).toString());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void finish() {
/* 116 */       while (this.builder.length() < 6) {
/* 117 */         this.builder.append('0');
/* 118 */         this.cachedString = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 124 */       return toString().hashCode();
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
/*     */     public void processNextReplacement(String replacement, boolean forceAppend) {
/* 136 */       boolean append = (this.lastReplacement == null || !this.lastReplacement.endsWith(replacement) || forceAppend);
/*     */       
/* 138 */       if (append && this.builder.length() < 6) {
/* 139 */         this.builder.append(replacement);
/*     */         
/* 141 */         if (this.builder.length() > 6) {
/* 142 */           this.builder.delete(6, this.builder.length());
/*     */         }
/* 144 */         this.cachedString = null;
/*     */       } 
/*     */       
/* 147 */       this.lastReplacement = replacement;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 152 */       if (this.cachedString == null) {
/* 153 */         this.cachedString = this.builder.toString();
/*     */       }
/* 155 */       return this.cachedString;
/*     */     }
/*     */ 
/*     */     
/*     */     private Branch() {}
/*     */   }
/*     */   
/*     */   private static final class Rule
/*     */   {
/*     */     private final String pattern;
/*     */     private final String[] replacementAtStart;
/*     */     private final String[] replacementBeforeVowel;
/*     */     private final String[] replacementDefault;
/*     */     
/*     */     protected Rule(String pattern, String replacementAtStart, String replacementBeforeVowel, String replacementDefault) {
/* 170 */       this.pattern = pattern;
/* 171 */       this.replacementAtStart = replacementAtStart.split("\\|");
/* 172 */       this.replacementBeforeVowel = replacementBeforeVowel.split("\\|");
/* 173 */       this.replacementDefault = replacementDefault.split("\\|");
/*     */     }
/*     */     
/*     */     public int getPatternLength() {
/* 177 */       return this.pattern.length();
/*     */     }
/*     */     
/*     */     public String[] getReplacements(String context, boolean atStart) {
/* 181 */       if (atStart) {
/* 182 */         return this.replacementAtStart;
/*     */       }
/*     */       
/* 185 */       int nextIndex = getPatternLength();
/* 186 */       boolean nextCharIsVowel = (nextIndex < context.length()) ? isVowel(context.charAt(nextIndex)) : false;
/* 187 */       if (nextCharIsVowel) {
/* 188 */         return this.replacementBeforeVowel;
/*     */       }
/*     */       
/* 191 */       return this.replacementDefault;
/*     */     }
/*     */     
/*     */     private boolean isVowel(char ch) {
/* 195 */       return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
/*     */     }
/*     */     
/*     */     public boolean matches(String context) {
/* 199 */       return context.startsWith(this.pattern);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 204 */       return String.format("%s=(%s,%s,%s)", new Object[] { this.pattern, Arrays.asList(this.replacementAtStart), 
/* 205 */             Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault) });
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
/*     */   
/* 223 */   private static final Map<Character, List<Rule>> RULES = new HashMap<>();
/*     */ 
/*     */   
/* 226 */   private static final Map<Character, Character> FOLDINGS = new HashMap<>(); private final boolean folding;
/*     */   
/*     */   static {
/* 229 */     InputStream rulesIS = DaitchMokotoffSoundex.class.getClassLoader().getResourceAsStream("org/apache/commons/codec/language/dmrules.txt");
/* 230 */     if (rulesIS == null) {
/* 231 */       throw new IllegalArgumentException("Unable to load resource: org/apache/commons/codec/language/dmrules.txt");
/*     */     }
/*     */     
/* 234 */     try (Scanner scanner = new Scanner(rulesIS, "UTF-8")) {
/* 235 */       parseRules(scanner, "org/apache/commons/codec/language/dmrules.txt", RULES, FOLDINGS);
/*     */     } 
/*     */ 
/*     */     
/* 239 */     for (Map.Entry<Character, List<Rule>> rule : RULES.entrySet()) {
/* 240 */       List<Rule> ruleList = rule.getValue();
/* 241 */       Collections.sort(ruleList, new Comparator<Rule>()
/*     */           {
/*     */             public int compare(DaitchMokotoffSoundex.Rule rule1, DaitchMokotoffSoundex.Rule rule2) {
/* 244 */               return rule2.getPatternLength() - rule1.getPatternLength();
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void parseRules(Scanner scanner, String location, Map<Character, List<Rule>> ruleMapping, Map<Character, Character> asciiFoldings) {
/* 252 */     int currentLine = 0;
/* 253 */     boolean inMultilineComment = false;
/*     */     
/* 255 */     while (scanner.hasNextLine()) {
/* 256 */       currentLine++;
/* 257 */       String rawLine = scanner.nextLine();
/* 258 */       String line = rawLine;
/*     */       
/* 260 */       if (inMultilineComment) {
/* 261 */         if (line.endsWith("*/")) {
/* 262 */           inMultilineComment = false;
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/* 267 */       if (line.startsWith("/*")) {
/* 268 */         inMultilineComment = true;
/*     */         continue;
/*     */       } 
/* 271 */       int cmtI = line.indexOf("//");
/* 272 */       if (cmtI >= 0) {
/* 273 */         line = line.substring(0, cmtI);
/*     */       }
/*     */ 
/*     */       
/* 277 */       line = line.trim();
/*     */       
/* 279 */       if (line.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 283 */       if (line.contains("=")) {
/*     */         
/* 285 */         String[] arrayOfString = line.split("=");
/* 286 */         if (arrayOfString.length != 2) {
/* 287 */           throw new IllegalArgumentException("Malformed folding statement split into " + arrayOfString.length + " parts: " + rawLine + " in " + location);
/*     */         }
/*     */         
/* 290 */         String leftCharacter = arrayOfString[0];
/* 291 */         String rightCharacter = arrayOfString[1];
/*     */         
/* 293 */         if (leftCharacter.length() != 1 || rightCharacter.length() != 1) {
/* 294 */           throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + rawLine + " in " + location);
/*     */         }
/*     */ 
/*     */         
/* 298 */         asciiFoldings.put(Character.valueOf(leftCharacter.charAt(0)), Character.valueOf(rightCharacter.charAt(0)));
/*     */         continue;
/*     */       } 
/* 301 */       String[] parts = line.split("\\s+");
/* 302 */       if (parts.length != 4) {
/* 303 */         throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
/*     */       }
/*     */       
/*     */       try {
/* 307 */         String pattern = stripQuotes(parts[0]);
/* 308 */         String replacement1 = stripQuotes(parts[1]);
/* 309 */         String replacement2 = stripQuotes(parts[2]);
/* 310 */         String replacement3 = stripQuotes(parts[3]);
/*     */         
/* 312 */         Rule r = new Rule(pattern, replacement1, replacement2, replacement3);
/* 313 */         char patternKey = r.pattern.charAt(0);
/* 314 */         List<Rule> rules = ruleMapping.get(Character.valueOf(patternKey));
/* 315 */         if (rules == null) {
/* 316 */           rules = new ArrayList<>();
/* 317 */           ruleMapping.put(Character.valueOf(patternKey), rules);
/*     */         } 
/* 319 */         rules.add(r);
/* 320 */       } catch (IllegalArgumentException e) {
/* 321 */         throw new IllegalStateException("Problem parsing line '" + currentLine + "' in " + location, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String stripQuotes(String str) {
/* 330 */     if (str.startsWith("\"")) {
/* 331 */       str = str.substring(1);
/*     */     }
/*     */     
/* 334 */     if (str.endsWith("\"")) {
/* 335 */       str = str.substring(0, str.length() - 1);
/*     */     }
/*     */     
/* 338 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DaitchMokotoffSoundex() {
/* 348 */     this(true);
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
/*     */   public DaitchMokotoffSoundex(boolean folding) {
/* 362 */     this.folding = folding;
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
/*     */   private String cleanup(String input) {
/* 376 */     StringBuilder sb = new StringBuilder();
/* 377 */     for (char ch : input.toCharArray()) {
/* 378 */       if (!Character.isWhitespace(ch)) {
/*     */ 
/*     */ 
/*     */         
/* 382 */         ch = Character.toLowerCase(ch);
/* 383 */         if (this.folding && FOLDINGS.containsKey(Character.valueOf(ch))) {
/* 384 */           ch = ((Character)FOLDINGS.get(Character.valueOf(ch))).charValue();
/*     */         }
/* 386 */         sb.append(ch);
/*     */       } 
/* 388 */     }  return sb.toString();
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
/*     */   public Object encode(Object obj) throws EncoderException {
/* 411 */     if (!(obj instanceof String)) {
/* 412 */       throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
/*     */     }
/*     */     
/* 415 */     return encode((String)obj);
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
/*     */   public String encode(String source) {
/* 431 */     if (source == null) {
/* 432 */       return null;
/*     */     }
/* 434 */     return soundex(source, false)[0];
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
/*     */   public String soundex(String source) {
/* 461 */     String[] branches = soundex(source, true);
/* 462 */     StringBuilder sb = new StringBuilder();
/* 463 */     int index = 0;
/* 464 */     for (String branch : branches) {
/* 465 */       sb.append(branch);
/* 466 */       if (++index < branches.length) {
/* 467 */         sb.append('|');
/*     */       }
/*     */     } 
/* 470 */     return sb.toString();
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
/*     */   private String[] soundex(String source, boolean branching) {
/* 484 */     if (source == null) {
/* 485 */       return null;
/*     */     }
/*     */     
/* 488 */     String input = cleanup(source);
/*     */     
/* 490 */     Set<Branch> currentBranches = new LinkedHashSet<>();
/* 491 */     currentBranches.add(new Branch());
/*     */     
/* 493 */     char lastChar = Character.MIN_VALUE;
/* 494 */     for (int index = 0; index < input.length(); index++) {
/* 495 */       char ch = input.charAt(index);
/*     */ 
/*     */       
/* 498 */       if (!Character.isWhitespace(ch)) {
/*     */ 
/*     */ 
/*     */         
/* 502 */         String inputContext = input.substring(index);
/* 503 */         List<Rule> rules = RULES.get(Character.valueOf(ch));
/* 504 */         if (rules != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 510 */           List<Branch> nextBranches = branching ? new ArrayList<>() : Collections.EMPTY_LIST;
/*     */           
/* 512 */           for (Rule rule : rules) {
/* 513 */             if (rule.matches(inputContext)) {
/* 514 */               if (branching) {
/* 515 */                 nextBranches.clear();
/*     */               }
/* 517 */               String[] replacements = rule.getReplacements(inputContext, (lastChar == '\000'));
/* 518 */               boolean branchingRequired = (replacements.length > 1 && branching);
/*     */               
/* 520 */               for (Branch branch : currentBranches) {
/* 521 */                 String[] arrayOfString; int j; byte b; for (arrayOfString = replacements, j = arrayOfString.length, b = 0; b < j; ) { String nextReplacement = arrayOfString[b];
/*     */                   
/* 523 */                   Branch nextBranch = branchingRequired ? branch.createBranch() : branch;
/*     */ 
/*     */                   
/* 526 */                   boolean force = ((lastChar == 'm' && ch == 'n') || (lastChar == 'n' && ch == 'm'));
/*     */                   
/* 528 */                   nextBranch.processNextReplacement(nextReplacement, force);
/*     */                   
/* 530 */                   if (branching) {
/* 531 */                     nextBranches.add(nextBranch);
/*     */                     
/*     */                     b++;
/*     */                   }  }
/*     */               
/*     */               } 
/*     */               
/* 538 */               if (branching) {
/* 539 */                 currentBranches.clear();
/* 540 */                 currentBranches.addAll(nextBranches);
/*     */               } 
/* 542 */               index += rule.getPatternLength() - 1;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 547 */           lastChar = ch;
/*     */         } 
/*     */       } 
/* 550 */     }  String[] result = new String[currentBranches.size()];
/* 551 */     int i = 0;
/* 552 */     for (Branch branch : currentBranches) {
/* 553 */       branch.finish();
/* 554 */       result[i++] = branch.toString();
/*     */     } 
/*     */     
/* 557 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/DaitchMokotoffSoundex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */