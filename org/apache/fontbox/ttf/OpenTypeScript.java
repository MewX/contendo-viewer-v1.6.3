/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.LineNumberReader;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class OpenTypeScript
/*     */ {
/*  45 */   private static final Log LOG = LogFactory.getLog(OpenTypeScript.class);
/*     */ 
/*     */   
/*     */   public static final String INHERITED = "Inherited";
/*     */ 
/*     */   
/*     */   public static final String UNKNOWN = "Unknown";
/*     */   
/*     */   public static final String TAG_DEFAULT = "DFLT";
/*     */   
/*     */   private static final Map<String, String[]> UNICODE_SCRIPT_TO_OPENTYPE_TAG_MAP;
/*     */   
/*     */   private static int[] unicodeRangeStarts;
/*     */   
/*     */   private static String[] unicodeRangeScripts;
/*     */ 
/*     */   
/*     */   static {
/*  63 */     Object[][] table = { { "Adlam", { "adlm" } }, { "Ahom", { "ahom" } }, { "Anatolian_Hieroglyphs", { "hluw" } }, { "Arabic", { "arab" } }, { "Armenian", { "armn" } }, { "Avestan", { "avst" } }, { "Balinese", { "bali" } }, { "Bamum", { "bamu" } }, { "Bassa_Vah", { "bass" } }, { "Batak", { "batk" } }, { "Bengali", { "bng2", "beng" } }, { "Bhaiksuki", { "bhks" } }, { "Bopomofo", { "bopo" } }, { "Brahmi", { "brah" } }, { "Braille", { "brai" } }, { "Buginese", { "bugi" } }, { "Buhid", { "buhd" } }, { "Canadian_Aboriginal", { "cans" } }, { "Carian", { "cari" } }, { "Caucasian_Albanian", { "aghb" } }, { "Chakma", { "cakm" } }, { "Cham", { "cham" } }, { "Cherokee", { "cher" } }, { "Common", { "DFLT" } }, { "Coptic", { "copt" } }, { "Cuneiform", { "xsux" } }, { "Cypriot", { "cprt" } }, { "Cyrillic", { "cyrl" } }, { "Deseret", { "dsrt" } }, { "Devanagari", { "dev2", "deva" } }, { "Duployan", { "dupl" } }, { "Egyptian_Hieroglyphs", { "egyp" } }, { "Elbasan", { "elba" } }, { "Ethiopic", { "ethi" } }, { "Georgian", { "geor" } }, { "Glagolitic", { "glag" } }, { "Gothic", { "goth" } }, { "Grantha", { "gran" } }, { "Greek", { "grek" } }, { "Gujarati", { "gjr2", "gujr" } }, { "Gurmukhi", { "gur2", "guru" } }, { "Han", { "hani" } }, { "Hangul", { "hang" } }, { "Hanunoo", { "hano" } }, { "Hatran", { "hatr" } }, { "Hebrew", { "hebr" } }, { "Hiragana", { "kana" } }, { "Imperial_Aramaic", { "armi" } }, { "Inherited", { "Inherited" } }, { "Inscriptional_Pahlavi", { "phli" } }, { "Inscriptional_Parthian", { "prti" } }, { "Javanese", { "java" } }, { "Kaithi", { "kthi" } }, { "Kannada", { "knd2", "knda" } }, { "Katakana", { "kana" } }, { "Kayah_Li", { "kali" } }, { "Kharoshthi", { "khar" } }, { "Khmer", { "khmr" } }, { "Khojki", { "khoj" } }, { "Khudawadi", { "sind" } }, { "Lao", { "lao " } }, { "Latin", { "latn" } }, { "Lepcha", { "lepc" } }, { "Limbu", { "limb" } }, { "Linear_A", { "lina" } }, { "Linear_B", { "linb" } }, { "Lisu", { "lisu" } }, { "Lycian", { "lyci" } }, { "Lydian", { "lydi" } }, { "Mahajani", { "mahj" } }, { "Malayalam", { "mlm2", "mlym" } }, { "Mandaic", { "mand" } }, { "Manichaean", { "mani" } }, { "Marchen", { "marc" } }, { "Meetei_Mayek", { "mtei" } }, { "Mende_Kikakui", { "mend" } }, { "Meroitic_Cursive", { "merc" } }, { "Meroitic_Hieroglyphs", { "mero" } }, { "Miao", { "plrd" } }, { "Modi", { "modi" } }, { "Mongolian", { "mong" } }, { "Mro", { "mroo" } }, { "Multani", { "mult" } }, { "Myanmar", { "mym2", "mymr" } }, { "Nabataean", { "nbat" } }, { "Newa", { "newa" } }, { "New_Tai_Lue", { "talu" } }, { "Nko", { "nko " } }, { "Ogham", { "ogam" } }, { "Ol_Chiki", { "olck" } }, { "Old_Italic", { "ital" } }, { "Old_Hungarian", { "hung" } }, { "Old_North_Arabian", { "narb" } }, { "Old_Permic", { "perm" } }, { "Old_Persian", { "xpeo" } }, { "Old_South_Arabian", { "sarb" } }, { "Old_Turkic", { "orkh" } }, { "Oriya", { "ory2", "orya" } }, { "Osage", { "osge" } }, { "Osmanya", { "osma" } }, { "Pahawh_Hmong", { "hmng" } }, { "Palmyrene", { "palm" } }, { "Pau_Cin_Hau", { "pauc" } }, { "Phags_Pa", { "phag" } }, { "Phoenician", { "phnx" } }, { "Psalter_Pahlavi", { "phlp" } }, { "Rejang", { "rjng" } }, { "Runic", { "runr" } }, { "Samaritan", { "samr" } }, { "Saurashtra", { "saur" } }, { "Sharada", { "shrd" } }, { "Shavian", { "shaw" } }, { "Siddham", { "sidd" } }, { "SignWriting", { "sgnw" } }, { "Sinhala", { "sinh" } }, { "Sora_Sompeng", { "sora" } }, { "Sundanese", { "sund" } }, { "Syloti_Nagri", { "sylo" } }, { "Syriac", { "syrc" } }, { "Tagalog", { "tglg" } }, { "Tagbanwa", { "tagb" } }, { "Tai_Le", { "tale" } }, { "Tai_Tham", { "lana" } }, { "Tai_Viet", { "tavt" } }, { "Takri", { "takr" } }, { "Tamil", { "tml2", "taml" } }, { "Tangut", { "tang" } }, { "Telugu", { "tel2", "telu" } }, { "Thaana", { "thaa" } }, { "Thai", { "thai" } }, { "Tibetan", { "tibt" } }, { "Tifinagh", { "tfng" } }, { "Tirhuta", { "tirh" } }, { "Ugaritic", { "ugar" } }, { "Unknown", { "DFLT" } }, { "Vai", { "vai " } }, { "Warang_Citi", { "wara" } }, { "Yi", { "yi  " } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     UNICODE_SCRIPT_TO_OPENTYPE_TAG_MAP = (Map)new HashMap<String, String>(table.length);
/* 209 */     for (Object[] obj : table) {
/*     */       
/* 211 */       Object[] array = obj;
/* 212 */       UNICODE_SCRIPT_TO_OPENTYPE_TAG_MAP.put((String)array[0], (String[])array[1]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     String path = "/org/apache/fontbox/unicode/Scripts.txt";
/* 222 */     InputStream input = null;
/*     */     
/*     */     try {
/* 225 */       input = OpenTypeScript.class.getResourceAsStream(path);
/* 226 */       if (input != null)
/*     */       {
/* 228 */         parseScriptsFile(input);
/*     */       }
/*     */       else
/*     */       {
/* 232 */         LOG.warn("Could not find '" + path + "', mirroring char map will be empty: ");
/*     */       }
/*     */     
/* 235 */     } catch (IOException e) {
/*     */       
/* 237 */       LOG.warn("Could not parse Scripts.txt, mirroring char map will be empty: " + e
/* 238 */           .getMessage());
/*     */     }
/*     */     finally {
/*     */       
/* 242 */       if (input != null) {
/*     */         
/*     */         try {
/*     */           
/* 246 */           input.close();
/*     */         }
/* 248 */         catch (IOException ex) {
/*     */           
/* 250 */           LOG.warn("Could not close Scripts.txt");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void parseScriptsFile(InputStream inputStream) throws IOException {
/* 262 */     Map<int[], String> unicodeRanges = (Map)new TreeMap<int, String>((Comparator)new Comparator<int[]>()
/*     */         {
/*     */           
/*     */           public int compare(int[] o1, int[] o2)
/*     */           {
/* 267 */             return (o1[0] < o2[0]) ? -1 : ((o1[0] == o2[0]) ? 0 : 1);
/*     */           }
/*     */         });
/* 270 */     LineNumberReader rd = new LineNumberReader(new InputStreamReader(inputStream));
/* 271 */     int[] lastRange = { Integer.MIN_VALUE, Integer.MIN_VALUE };
/* 272 */     String lastScript = null;
/*     */     
/*     */     while (true) {
/* 275 */       String s = rd.readLine();
/* 276 */       if (s == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 282 */       int comment = s.indexOf('#');
/* 283 */       if (comment != -1)
/*     */       {
/* 285 */         s = s.substring(0, comment);
/*     */       }
/*     */       
/* 288 */       if (s.length() < 2) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 293 */       StringTokenizer st = new StringTokenizer(s, ";");
/* 294 */       int nFields = st.countTokens();
/* 295 */       if (nFields < 2) {
/*     */         continue;
/*     */       }
/*     */       
/* 299 */       String characters = st.nextToken().trim();
/* 300 */       String script = st.nextToken().trim();
/* 301 */       int[] range = new int[2];
/* 302 */       int rangeDelim = characters.indexOf("..");
/* 303 */       if (rangeDelim == -1) {
/*     */         
/* 305 */         range[1] = Integer.parseInt(characters, 16); range[0] = Integer.parseInt(characters, 16);
/*     */       }
/*     */       else {
/*     */         
/* 309 */         range[0] = Integer.parseInt(characters.substring(0, rangeDelim), 16);
/* 310 */         range[1] = Integer.parseInt(characters.substring(rangeDelim + 2), 16);
/*     */       } 
/* 312 */       if (range[0] == lastRange[1] + 1 && script.equals(lastScript)) {
/*     */ 
/*     */         
/* 315 */         lastRange[1] = range[1];
/*     */         
/*     */         continue;
/*     */       } 
/* 319 */       unicodeRanges.put(range, script);
/* 320 */       lastRange = range;
/* 321 */       lastScript = script;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     rd.close();
/*     */     
/* 327 */     unicodeRangeStarts = new int[unicodeRanges.size()];
/* 328 */     unicodeRangeScripts = new String[unicodeRanges.size()];
/* 329 */     int i = 0;
/* 330 */     for (Map.Entry<int[], String> e : unicodeRanges.entrySet()) {
/*     */       
/* 332 */       unicodeRangeStarts[i] = ((int[])e.getKey())[0];
/* 333 */       unicodeRangeScripts[i] = e.getValue();
/* 334 */       i++;
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
/*     */   private static String getUnicodeScript(int codePoint) {
/* 346 */     ensureValidCodePoint(codePoint);
/* 347 */     int type = Character.getType(codePoint);
/* 348 */     if (type == 0)
/*     */     {
/* 350 */       return "Unknown";
/*     */     }
/* 352 */     int scriptIndex = Arrays.binarySearch(unicodeRangeStarts, codePoint);
/* 353 */     if (scriptIndex < 0)
/*     */     {
/* 355 */       scriptIndex = -scriptIndex - 2;
/*     */     }
/* 357 */     return unicodeRangeScripts[scriptIndex];
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
/*     */   public static String[] getScriptTags(int codePoint) {
/* 373 */     ensureValidCodePoint(codePoint);
/* 374 */     String unicode = getUnicodeScript(codePoint);
/* 375 */     return UNICODE_SCRIPT_TO_OPENTYPE_TAG_MAP.get(unicode);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void ensureValidCodePoint(int codePoint) {
/* 380 */     if (codePoint < 0 || codePoint > 1114111)
/*     */     {
/* 382 */       throw new IllegalArgumentException("Invalid codepoint: " + codePoint);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/OpenTypeScript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */