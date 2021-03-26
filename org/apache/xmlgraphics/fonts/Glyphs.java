/*     */ package org.apache.xmlgraphics.fonts;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Glyphs
/*     */ {
/*     */   public static final String NOTDEF = ".notdef";
/*     */   @Deprecated
/*  54 */   public static final String[] MAC_GLYPH_NAMES = new String[] { ".notdef", ".null", "CR", "space", "exclam", "quotedbl", "numbersign", "dollar", "percent", "ampersand", "quotesingle", "parenleft", "parenright", "asterisk", "plus", "comma", "hyphen", "period", "slash", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "colon", "semicolon", "less", "equal", "greater", "question", "at", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "bracketleft", "backslash", "bracketright", "asciicircum", "underscore", "grave", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "braceleft", "bar", "braceright", "asciitilde", "Adieresis", "Aring", "Ccedilla", "Eacute", "Ntilde", "Odieresis", "Udieresis", "aacute", "agrave", "acircumflex", "adieresis", "atilde", "aring", "ccedilla", "eacute", "egrave", "ecircumflex", "edieresis", "iacute", "igrave", "icircumflex", "idieresis", "ntilde", "oacute", "ograve", "ocircumflex", "odieresis", "otilde", "uacute", "ugrave", "ucircumflex", "udieresis", "dagger", "degree", "cent", "sterling", "section", "bullet", "paragraph", "germandbls", "registered", "copyright", "trademark", "acute", "dieresis", "notequal", "AE", "Oslash", "infinity", "plusminus", "lessequal", "greaterequal", "yen", "mu", "partialdiff", "Sigma", "Pi", "pi", "integral", "ordfeminine", "ordmasculine", "Omega", "ae", "oslash", "questiondown", "exclamdown", "logicalnot", "radical", "florin", "approxequal", "Delta", "guillemotleft", "guillemotright", "ellipsis", "nbspace", "Agrave", "Atilde", "Otilde", "OE", "oe", "endash", "emdash", "quotedblleft", "quotedblright", "quoteleft", "quoteright", "divide", "lozenge", "ydieresis", "Ydieresis", "fraction", "currency", "guilsinglleft", "guilsinglright", "fi", "fl", "daggerdbl", "periodcentered", "quotesinglbase", "quotedblbase", "perthousand", "Acircumflex", "Ecircumflex", "Aacute", "Edieresis", "Egrave", "Iacute", "Icircumflex", "Idieresis", "Igrave", "Oacute", "Ocircumflex", "applelogo", "Ograve", "Uacute", "Ucircumflex", "Ugrave", "dotlessi", "circumflex", "tilde", "macron", "breve", "dotaccent", "ring", "cedilla", "hungarumlaut", "ogonek", "caron", "Lslash", "lslash", "Scaron", "scaron", "Zcaron", "zcaron", "brokenbar", "Eth", "eth", "Yacute", "yacute", "Thorn", "thorn", "minus", "multiply", "onesuperior", "twosuperior", "threesuperior", "onehalf", "onequarter", "threequarters", "franc", "Gbreve", "gbreve", "Idot", "Scedilla", "scedilla", "Cacute", "cacute", "Ccaron", "ccaron", "dmacron" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final String[] TEX8R_GLYPH_NAMES = new String[] { ".notdef", "dotaccent", "fi", "fl", "fraction", "hungarumlaut", "Lslash", "lslash", "ogonek", "ring", ".notdef", "breve", "minus", ".notdef", "Zcaron", "zcaron", "caron", "dotlessi", "dotlessj", "ff", "ffi", "ffl", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", "grave", "quotesingle", "space", "exclam", "quotedbl", "numbersign", "dollar", "percent", "ampersand", "quoteright", "parenleft", "parenright", "asterisk", "plus", "comma", "hyphen", "period", "slash", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "colon", "semicolon", "less", "equal", "greater", "question", "at", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "bracketleft", "backslash", "bracketright", "asciicircum", "underscore", "quoteleft", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "braceleft", "bar", "braceright", "asciitilde", ".notdef", "Euro", ".notdef", "quotesinglbase", "florin", "quotedblbase", "ellipsis", "dagger", "daggerdbl", "circumflex", "perthousand", "Scaron", "guilsinglleft", "OE", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", ".notdef", "quotedblleft", "quotedblright", "bullet", "endash", "emdash", "tilde", "trademark", "scaron", "guilsinglright", "oe", ".notdef", ".notdef", "Ydieresis", ".notdef", "exclamdown", "cent", "sterling", "currency", "yen", "brokenbar", "section", "dieresis", "copyright", "ordfeminine", "guillemotleft", "logicalnot", "hyphen", "registered", "macron", "degree", "plusminus", "twosuperior", "threesuperior", "acute", "mu", "paragraph", "periodcentered", "cedilla", "onesuperior", "ordmasculine", "guillemotright", "onequarter", "onehalf", "threequarters", "questiondown", "Agrave", "Aacute", "Acircumflex", "Atilde", "Adieresis", "Aring", "AE", "Ccedilla", "Egrave", "Eacute", "Ecircumflex", "Edieresis", "Igrave", "Iacute", "Icircumflex", "Idieresis", "Eth", "Ntilde", "Ograve", "Oacute", "Ocircumflex", "Otilde", "Odieresis", "multiply", "Oslash", "Ugrave", "Uacute", "Ucircumflex", "Udieresis", "Yacute", "Thorn", "germandbls", "agrave", "aacute", "acircumflex", "atilde", "adieresis", "aring", "ae", "ccedilla", "egrave", "eacute", "ecircumflex", "edieresis", "igrave", "iacute", "icircumflex", "idieresis", "eth", "ntilde", "ograve", "oacute", "ocircumflex", "otilde", "odieresis", "divide", "oslash", "ugrave", "uacute", "ucircumflex", "udieresis", "yacute", "thorn", "ydieresis" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public static final char[] WINANSI_ENCODING = new char[] { Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '‘', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '•', '€', '•', '‚', 'ƒ', '„', '…', '†', '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', '•', 'Ž', '•', '•', '‘', '’', '“', '”', '•', '–', '—', '~', '™', 'š', '›', 'œ', '•', 'ž', 'Ÿ', ' ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '­', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', '¹', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public static final char[] ADOBECYRILLIC_ENCODING = new char[] { Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', Character.MIN_VALUE, 'Ђ', 'Ѓ', '‚', 'ѓ', '„', '…', '†', '‡', '€', '‰', 'Љ', '‹', 'Њ', 'Ќ', 'Ћ', 'Џ', 'ђ', '‘', '’', '“', '”', '•', '–', '—', Character.MIN_VALUE, '™', 'љ', '›', 'њ', 'ќ', 'ћ', 'џ', ' ', 'Ў', 'ў', 'Ј', '¤', 'Ґ', '¦', '§', 'Ё', '©', 'Є', '«', '¬', '­', '®', 'Ї', '°', '±', 'І', 'і', 'ґ', 'µ', '¶', '·', 'ё', '№', 'є', '»', 'ј', 'Ѕ', 'ѕ', 'ї', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String[] UNICODE_GLYPHS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String[] DINGBATS_GLYPHS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Map CHARNAME_ALTERNATIVES;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Map CHARNAMES_TO_UNICODE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 484 */     Map<Object, Object> map = new TreeMap<Object, Object>();
/* 485 */     UNICODE_GLYPHS = loadGlyphList("glyphlist.txt", map);
/* 486 */     DINGBATS_GLYPHS = loadGlyphList("zapfdingbats.txt", map);
/* 487 */     CHARNAMES_TO_UNICODE = Collections.unmodifiableMap(map);
/*     */     
/* 489 */     map = new TreeMap<Object, Object>();
/* 490 */     addAlternatives(map, new String[] { "Omega", "Omegagreek" });
/* 491 */     addAlternatives(map, new String[] { "Delta", "Deltagreek" });
/*     */     
/* 493 */     addAlternatives(map, new String[] { "fraction", "divisionslash" });
/*     */     
/* 495 */     addAlternatives(map, new String[] { "hyphen", "sfthyphen", "softhyphen", "minus" });
/*     */     
/* 497 */     addAlternatives(map, new String[] { "macron", "overscore" });
/*     */     
/* 499 */     addAlternatives(map, new String[] { "mu", "mu1", "mugreek" });
/*     */     
/* 501 */     addAlternatives(map, new String[] { "periodcentered", "middot", "bulletoperator", "anoteleia" });
/*     */ 
/*     */     
/* 504 */     addAlternatives(map, new String[] { "space", "nonbreakingspace", "nbspace" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     addAlternatives(map, new String[] { "zero", "zerooldstyle" });
/* 514 */     addAlternatives(map, new String[] { "one", "oneoldstyle" });
/* 515 */     addAlternatives(map, new String[] { "two", "twooldstyle" });
/* 516 */     addAlternatives(map, new String[] { "three", "threeoldstyle" });
/* 517 */     addAlternatives(map, new String[] { "four", "fouroldstyle" });
/* 518 */     addAlternatives(map, new String[] { "five", "fiveoldstyle" });
/* 519 */     addAlternatives(map, new String[] { "six", "sixoldstyle" });
/* 520 */     addAlternatives(map, new String[] { "seven", "sevenoldstyle" });
/* 521 */     addAlternatives(map, new String[] { "eight", "eightoldstyle" });
/* 522 */     addAlternatives(map, new String[] { "nine", "nineoldstyle" });
/*     */ 
/*     */     
/* 525 */     addAlternatives(map, new String[] { "cent", "centoldstyle" });
/* 526 */     addAlternatives(map, new String[] { "dollar", "dollaroldstyle" });
/*     */ 
/*     */     
/* 529 */     addAlternatives(map, new String[] { "Acyrillic", "afii10017" });
/* 530 */     addAlternatives(map, new String[] { "Becyrillic", "afii10018" });
/* 531 */     addAlternatives(map, new String[] { "Vecyrillic", "afii10019" });
/* 532 */     addAlternatives(map, new String[] { "Gecyrillic", "afii10020" });
/* 533 */     addAlternatives(map, new String[] { "Decyrillic", "afii10021" });
/* 534 */     addAlternatives(map, new String[] { "Iecyrillic", "afii10022" });
/* 535 */     addAlternatives(map, new String[] { "Iocyrillic", "afii10023" });
/* 536 */     addAlternatives(map, new String[] { "Zhecyrillic", "afii10024" });
/* 537 */     addAlternatives(map, new String[] { "Zecyrillic", "afii10025" });
/* 538 */     addAlternatives(map, new String[] { "Iicyrillic", "afii10026" });
/* 539 */     addAlternatives(map, new String[] { "Iishortcyrillic", "afii10027" });
/* 540 */     addAlternatives(map, new String[] { "Kacyrillic", "afii10028" });
/* 541 */     addAlternatives(map, new String[] { "Elcyrillic", "afii10029" });
/* 542 */     addAlternatives(map, new String[] { "Emcyrillic", "afii10030" });
/* 543 */     addAlternatives(map, new String[] { "Encyrillic", "afii10031" });
/* 544 */     addAlternatives(map, new String[] { "Ocyrillic", "afii10032" });
/* 545 */     addAlternatives(map, new String[] { "Pecyrillic", "afii10033" });
/* 546 */     addAlternatives(map, new String[] { "Ercyrillic", "afii10034" });
/* 547 */     addAlternatives(map, new String[] { "Escyrillic", "afii10035" });
/* 548 */     addAlternatives(map, new String[] { "Tecyrillic", "afii10036" });
/* 549 */     addAlternatives(map, new String[] { "Ucyrillic", "afii10037" });
/* 550 */     addAlternatives(map, new String[] { "Efcyrillic", "afii10038" });
/* 551 */     addAlternatives(map, new String[] { "Khacyrillic", "afii10039" });
/* 552 */     addAlternatives(map, new String[] { "Tsecyrillic", "afii10040" });
/* 553 */     addAlternatives(map, new String[] { "Checyrillic", "afii10041" });
/* 554 */     addAlternatives(map, new String[] { "Shacyrillic", "afii10042" });
/* 555 */     addAlternatives(map, new String[] { "Shchacyrillic", "afii10043" });
/* 556 */     addAlternatives(map, new String[] { "Hardsigncyrillic", "afii10044" });
/* 557 */     addAlternatives(map, new String[] { "Yericyrillic", "afii10045" });
/* 558 */     addAlternatives(map, new String[] { "Softsigncyrillic", "afii10046" });
/* 559 */     addAlternatives(map, new String[] { "Ereversedcyrillic", "afii10047" });
/* 560 */     addAlternatives(map, new String[] { "IUcyrillic", "afii10048" });
/* 561 */     addAlternatives(map, new String[] { "IAcyrillic", "afii10049" });
/*     */     
/* 563 */     addAlternatives(map, new String[] { "acyrillic", "afii10065" });
/* 564 */     addAlternatives(map, new String[] { "becyrillic", "afii10066" });
/* 565 */     addAlternatives(map, new String[] { "vecyrillic", "afii10067" });
/* 566 */     addAlternatives(map, new String[] { "gecyrillic", "afii10068" });
/* 567 */     addAlternatives(map, new String[] { "decyrillic", "afii10069" });
/* 568 */     addAlternatives(map, new String[] { "iecyrillic", "afii10070" });
/* 569 */     addAlternatives(map, new String[] { "iocyrillic", "afii10071" });
/* 570 */     addAlternatives(map, new String[] { "zhecyrillic", "afii10072" });
/* 571 */     addAlternatives(map, new String[] { "zecyrillic", "afii10073" });
/* 572 */     addAlternatives(map, new String[] { "iicyrillic", "afii10074" });
/* 573 */     addAlternatives(map, new String[] { "iishortcyrillic", "afii10075" });
/* 574 */     addAlternatives(map, new String[] { "kacyrillic", "afii10076" });
/* 575 */     addAlternatives(map, new String[] { "elcyrillic", "afii10077" });
/* 576 */     addAlternatives(map, new String[] { "emcyrillic", "afii10078" });
/* 577 */     addAlternatives(map, new String[] { "encyrillic", "afii10079" });
/* 578 */     addAlternatives(map, new String[] { "ocyrillic", "afii10080" });
/* 579 */     addAlternatives(map, new String[] { "pecyrillic", "afii10081" });
/* 580 */     addAlternatives(map, new String[] { "ercyrillic", "afii10082" });
/* 581 */     addAlternatives(map, new String[] { "escyrillic", "afii10083" });
/* 582 */     addAlternatives(map, new String[] { "tecyrillic", "afii10084" });
/* 583 */     addAlternatives(map, new String[] { "ucyrillic", "afii10085" });
/* 584 */     addAlternatives(map, new String[] { "efcyrillic", "afii10086" });
/* 585 */     addAlternatives(map, new String[] { "khacyrillic", "afii10087" });
/* 586 */     addAlternatives(map, new String[] { "tsecyrillic", "afii10088" });
/* 587 */     addAlternatives(map, new String[] { "checyrillic", "afii10089" });
/* 588 */     addAlternatives(map, new String[] { "shacyrillic", "afii10090" });
/* 589 */     addAlternatives(map, new String[] { "shchacyrillic", "afii10091" });
/* 590 */     addAlternatives(map, new String[] { "hardsigncyrillic", "afii10092" });
/* 591 */     addAlternatives(map, new String[] { "yericyrillic", "afii10093" });
/* 592 */     addAlternatives(map, new String[] { "softsigncyrillic", "afii10094" });
/* 593 */     addAlternatives(map, new String[] { "ereversedcyrillic", "afii10095" });
/* 594 */     addAlternatives(map, new String[] { "iucyrillic", "afii10096" });
/* 595 */     addAlternatives(map, new String[] { "iacyrillic", "afii10097" });
/*     */     
/* 597 */     addAlternatives(map, new String[] { "Gheupturncyrillic", "afii10050" });
/* 598 */     addAlternatives(map, new String[] { "Djecyrillic", "afii10051" });
/* 599 */     addAlternatives(map, new String[] { "Gjecyrillic", "afii10052" });
/* 600 */     addAlternatives(map, new String[] { "Ecyrillic", "afii10053" });
/* 601 */     addAlternatives(map, new String[] { "Dzecyrillic", "afii10054" });
/* 602 */     addAlternatives(map, new String[] { "Icyrillic", "afii10055" });
/* 603 */     addAlternatives(map, new String[] { "Yicyrillic", "afii10056" });
/* 604 */     addAlternatives(map, new String[] { "Jecyrillic", "afii10057" });
/* 605 */     addAlternatives(map, new String[] { "Ljecyrillic", "afii10058" });
/* 606 */     addAlternatives(map, new String[] { "Njecyrillic", "afii10059" });
/* 607 */     addAlternatives(map, new String[] { "Tshecyrillic", "afii10060" });
/* 608 */     addAlternatives(map, new String[] { "Kjecyrillic", "afii10061" });
/* 609 */     addAlternatives(map, new String[] { "Ushortcyrillic", "afii10062" });
/*     */     
/* 611 */     addAlternatives(map, new String[] { "Dzhecyrillic", "afii10145" });
/* 612 */     addAlternatives(map, new String[] { "Yatcyrillic", "afii10146" });
/* 613 */     addAlternatives(map, new String[] { "Fitacyrillic", "afii10147" });
/* 614 */     addAlternatives(map, new String[] { "Izhitsacyrillic", "afii10148" });
/*     */     
/* 616 */     addAlternatives(map, new String[] { "gheupturncyrillic", "afii10098" });
/* 617 */     addAlternatives(map, new String[] { "djecyrillic", "afii10099" });
/* 618 */     addAlternatives(map, new String[] { "gjecyrillic", "afii10100" });
/* 619 */     addAlternatives(map, new String[] { "ecyrillic", "afii10101" });
/* 620 */     addAlternatives(map, new String[] { "dzecyrillic", "afii10102" });
/* 621 */     addAlternatives(map, new String[] { "icyrillic", "afii10103" });
/* 622 */     addAlternatives(map, new String[] { "yicyrillic", "afii10104" });
/* 623 */     addAlternatives(map, new String[] { "jecyrillic", "afii10105" });
/* 624 */     addAlternatives(map, new String[] { "ljecyrillic", "afii10106" });
/* 625 */     addAlternatives(map, new String[] { "njecyrillic", "afii10107" });
/* 626 */     addAlternatives(map, new String[] { "tshecyrillic", "afii10108" });
/* 627 */     addAlternatives(map, new String[] { "kjecyrillic", "afii10109" });
/* 628 */     addAlternatives(map, new String[] { "ushortcyrillic", "afii10110" });
/*     */     
/* 630 */     addAlternatives(map, new String[] { "dzhecyrillic", "afii10193" });
/* 631 */     addAlternatives(map, new String[] { "yatcyrillic", "afii10194" });
/* 632 */     addAlternatives(map, new String[] { "fitacyrillic", "afii10195" });
/* 633 */     addAlternatives(map, new String[] { "izhitsacyrillic", "afii10196" });
/*     */     
/* 635 */     CHARNAME_ALTERNATIVES = Collections.unmodifiableMap(map);
/*     */   }
/*     */   
/*     */   private static void addAlternatives(Map<String, String[]> map, String[] alternatives) {
/* 639 */     for (int i = 0, c = alternatives.length; i < c; i++) {
/* 640 */       String[] alt = new String[c - 1];
/* 641 */       int idx = 0;
/* 642 */       for (int j = 0; j < c; j++) {
/* 643 */         if (i != j) {
/* 644 */           alt[idx] = alternatives[j];
/* 645 */           idx++;
/*     */         } 
/*     */       } 
/* 648 */       map.put(alternatives[i], alt);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String[] loadGlyphList(String filename, Map<String, String> charNameToUnicodeMap) {
/* 653 */     List<String> lines = new ArrayList();
/* 654 */     InputStream in = Glyphs.class.getResourceAsStream(filename);
/* 655 */     if (in == null) {
/* 656 */       throw new RuntimeException("Cannot load " + filename + ". The Glyphs class cannot properly be initialized!");
/*     */     }
/*     */     
/*     */     try {
/* 660 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in, "US-ASCII"));
/*     */       try {
/*     */         String line;
/* 663 */         while ((line = reader.readLine()) != null) {
/* 664 */           if (!line.startsWith("#")) {
/* 665 */             lines.add(line);
/*     */           }
/*     */         } 
/*     */       } finally {
/* 669 */         reader.close();
/*     */       } 
/* 671 */     } catch (UnsupportedEncodingException uee) {
/* 672 */       throw new RuntimeException("Incompatible JVM! US-ASCII encoding is not supported. The Glyphs class cannot properly be initialized!");
/*     */     }
/* 674 */     catch (IOException ioe) {
/* 675 */       throw new RuntimeException("I/O error while loading " + filename + ". The Glyphs class cannot properly be initialized!");
/*     */     } finally {
/*     */       
/* 678 */       IOUtils.closeQuietly(in);
/*     */     } 
/* 680 */     String[] arr = new String[lines.size() * 2];
/* 681 */     int pos = 0;
/* 682 */     StringBuffer buf = new StringBuffer();
/* 683 */     for (int i = 0, c = lines.size(); i < c; i++) {
/* 684 */       String line = lines.get(i);
/* 685 */       int semicolon = line.indexOf(';');
/* 686 */       if (semicolon > 0) {
/*     */ 
/*     */         
/* 689 */         String charName = line.substring(0, semicolon);
/* 690 */         String rawUnicode = line.substring(semicolon + 1);
/* 691 */         buf.setLength(0);
/*     */         
/* 693 */         StringTokenizer tokenizer = new StringTokenizer(rawUnicode, " ", false);
/* 694 */         while (tokenizer.hasMoreTokens()) {
/* 695 */           String token = tokenizer.nextToken();
/* 696 */           assert token.length() == 4;
/* 697 */           buf.append(hexToChar(token));
/*     */         } 
/*     */         
/* 700 */         String unicode = buf.toString();
/* 701 */         arr[pos] = unicode;
/* 702 */         pos++;
/* 703 */         arr[pos] = charName;
/* 704 */         pos++;
/* 705 */         assert !charNameToUnicodeMap.containsKey(charName);
/* 706 */         charNameToUnicodeMap.put(charName, unicode);
/*     */       } 
/* 708 */     }  return arr;
/*     */   }
/*     */   
/*     */   private static char hexToChar(String hex) {
/* 712 */     return (char)Integer.parseInt(hex, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String charToGlyphName(char ch) {
/* 723 */     return stringToGlyph(Character.toString(ch));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getUnicodeSequenceForGlyphName(String glyphName) {
/* 734 */     int period = glyphName.indexOf('.');
/* 735 */     if (period >= 0) {
/* 736 */       glyphName = glyphName.substring(0, period);
/*     */     }
/*     */ 
/*     */     
/* 740 */     StringBuffer sb = new StringBuffer();
/* 741 */     StringTokenizer tokenizer = new StringTokenizer(glyphName, "_", false);
/* 742 */     while (tokenizer.hasMoreTokens()) {
/* 743 */       String token = tokenizer.nextToken();
/*     */       
/* 745 */       String sequence = (String)CHARNAMES_TO_UNICODE.get(token);
/* 746 */       if (sequence == null) {
/* 747 */         if (token.startsWith("uni")) {
/* 748 */           int len = token.length();
/* 749 */           int pos = 3;
/* 750 */           while (pos + 4 <= len) {
/*     */             try {
/* 752 */               sb.append(hexToChar(token.substring(pos, pos + 4)));
/* 753 */             } catch (NumberFormatException nfe) {
/* 754 */               return null;
/*     */             } 
/* 756 */             pos += 4;
/*     */           }  continue;
/* 758 */         }  if (token.startsWith("u")) {
/* 759 */           if (token.length() > 5)
/*     */           {
/* 761 */             return null;
/*     */           }
/* 763 */           if (token.length() < 5)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 769 */             return null;
/*     */           }
/*     */           try {
/* 772 */             sb.append(hexToChar(token.substring(1, 5)));
/* 773 */           } catch (NumberFormatException nfe) {
/* 774 */             return null;
/*     */           } 
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 780 */       sb.append(sequence);
/*     */     } 
/*     */ 
/*     */     
/* 784 */     if (sb.length() == 0) {
/* 785 */       return null;
/*     */     }
/* 787 */     return sb.toString();
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
/*     */   @Deprecated
/*     */   public static String glyphToString(String name) {
/* 803 */     for (int i = 0; i < UNICODE_GLYPHS.length; i += 2) {
/* 804 */       if (UNICODE_GLYPHS[i + 1].equals(name)) {
/* 805 */         return UNICODE_GLYPHS[i];
/*     */       }
/*     */     } 
/* 808 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String stringToGlyph(String name) {
/*     */     int i;
/* 819 */     for (i = 0; i < UNICODE_GLYPHS.length; i += 2) {
/* 820 */       if (UNICODE_GLYPHS[i].equals(name)) {
/* 821 */         return UNICODE_GLYPHS[i + 1];
/*     */       }
/*     */     } 
/* 824 */     for (i = 0; i < DINGBATS_GLYPHS.length; i += 2) {
/* 825 */       if (DINGBATS_GLYPHS[i].equals(name)) {
/* 826 */         return DINGBATS_GLYPHS[i + 1];
/*     */       }
/*     */     } 
/* 829 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] getCharNameAlternativesFor(String charName) {
/* 838 */     return (String[])CHARNAME_ALTERNATIVES.get(charName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/fonts/Glyphs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */