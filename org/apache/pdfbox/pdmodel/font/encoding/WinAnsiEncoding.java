/*     */ package org.apache.pdfbox.pdmodel.font.encoding;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WinAnsiEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  36 */   private static final Object[][] WIN_ANSI_ENCODING_TABLE = new Object[][] { 
/*  37 */       { Integer.valueOf(65), "A"
/*  38 */       }, { Integer.valueOf(198), "AE"
/*  39 */       }, { Integer.valueOf(193), "Aacute"
/*  40 */       }, { Integer.valueOf(194), "Acircumflex"
/*  41 */       }, { Integer.valueOf(196), "Adieresis"
/*  42 */       }, { Integer.valueOf(192), "Agrave"
/*  43 */       }, { Integer.valueOf(197), "Aring"
/*  44 */       }, { Integer.valueOf(195), "Atilde"
/*  45 */       }, { Integer.valueOf(66), "B"
/*  46 */       }, { Integer.valueOf(67), "C" }, 
/*  47 */       { Integer.valueOf(199), "Ccedilla"
/*  48 */       }, { Integer.valueOf(68), "D"
/*  49 */       }, { Integer.valueOf(69), "E"
/*  50 */       }, { Integer.valueOf(201), "Eacute"
/*  51 */       }, { Integer.valueOf(202), "Ecircumflex"
/*  52 */       }, { Integer.valueOf(203), "Edieresis"
/*  53 */       }, { Integer.valueOf(200), "Egrave"
/*  54 */       }, { Integer.valueOf(208), "Eth"
/*  55 */       }, { Integer.valueOf(128), "Euro"
/*  56 */       }, { Integer.valueOf(70), "F" }, 
/*  57 */       { Integer.valueOf(71), "G"
/*  58 */       }, { Integer.valueOf(72), "H"
/*  59 */       }, { Integer.valueOf(73), "I"
/*  60 */       }, { Integer.valueOf(205), "Iacute"
/*  61 */       }, { Integer.valueOf(206), "Icircumflex"
/*  62 */       }, { Integer.valueOf(207), "Idieresis"
/*  63 */       }, { Integer.valueOf(204), "Igrave"
/*  64 */       }, { Integer.valueOf(74), "J"
/*  65 */       }, { Integer.valueOf(75), "K"
/*  66 */       }, { Integer.valueOf(76), "L" }, 
/*  67 */       { Integer.valueOf(77), "M"
/*  68 */       }, { Integer.valueOf(78), "N"
/*  69 */       }, { Integer.valueOf(209), "Ntilde"
/*  70 */       }, { Integer.valueOf(79), "O"
/*  71 */       }, { Integer.valueOf(140), "OE"
/*  72 */       }, { Integer.valueOf(211), "Oacute"
/*  73 */       }, { Integer.valueOf(212), "Ocircumflex"
/*  74 */       }, { Integer.valueOf(214), "Odieresis"
/*  75 */       }, { Integer.valueOf(210), "Ograve"
/*  76 */       }, { Integer.valueOf(216), "Oslash" }, 
/*  77 */       { Integer.valueOf(213), "Otilde"
/*  78 */       }, { Integer.valueOf(80), "P"
/*  79 */       }, { Integer.valueOf(81), "Q"
/*  80 */       }, { Integer.valueOf(82), "R"
/*  81 */       }, { Integer.valueOf(83), "S"
/*  82 */       }, { Integer.valueOf(138), "Scaron"
/*  83 */       }, { Integer.valueOf(84), "T"
/*  84 */       }, { Integer.valueOf(222), "Thorn"
/*  85 */       }, { Integer.valueOf(85), "U"
/*  86 */       }, { Integer.valueOf(218), "Uacute" }, 
/*  87 */       { Integer.valueOf(219), "Ucircumflex"
/*  88 */       }, { Integer.valueOf(220), "Udieresis"
/*  89 */       }, { Integer.valueOf(217), "Ugrave"
/*  90 */       }, { Integer.valueOf(86), "V"
/*  91 */       }, { Integer.valueOf(87), "W"
/*  92 */       }, { Integer.valueOf(88), "X"
/*  93 */       }, { Integer.valueOf(89), "Y"
/*  94 */       }, { Integer.valueOf(221), "Yacute"
/*  95 */       }, { Integer.valueOf(159), "Ydieresis"
/*  96 */       }, { Integer.valueOf(90), "Z" }, 
/*  97 */       { Integer.valueOf(142), "Zcaron"
/*  98 */       }, { Integer.valueOf(97), "a"
/*  99 */       }, { Integer.valueOf(225), "aacute"
/* 100 */       }, { Integer.valueOf(226), "acircumflex"
/* 101 */       }, { Integer.valueOf(180), "acute"
/* 102 */       }, { Integer.valueOf(228), "adieresis"
/* 103 */       }, { Integer.valueOf(230), "ae"
/* 104 */       }, { Integer.valueOf(224), "agrave"
/* 105 */       }, { Integer.valueOf(38), "ampersand"
/* 106 */       }, { Integer.valueOf(229), "aring" }, 
/* 107 */       { Integer.valueOf(94), "asciicircum"
/* 108 */       }, { Integer.valueOf(126), "asciitilde"
/* 109 */       }, { Integer.valueOf(42), "asterisk"
/* 110 */       }, { Integer.valueOf(64), "at"
/* 111 */       }, { Integer.valueOf(227), "atilde"
/* 112 */       }, { Integer.valueOf(98), "b"
/* 113 */       }, { Integer.valueOf(92), "backslash"
/* 114 */       }, { Integer.valueOf(124), "bar"
/* 115 */       }, { Integer.valueOf(123), "braceleft"
/* 116 */       }, { Integer.valueOf(125), "braceright" }, 
/* 117 */       { Integer.valueOf(91), "bracketleft"
/* 118 */       }, { Integer.valueOf(93), "bracketright"
/* 119 */       }, { Integer.valueOf(166), "brokenbar"
/* 120 */       }, { Integer.valueOf(149), "bullet"
/* 121 */       }, { Integer.valueOf(99), "c"
/* 122 */       }, { Integer.valueOf(231), "ccedilla"
/* 123 */       }, { Integer.valueOf(184), "cedilla"
/* 124 */       }, { Integer.valueOf(162), "cent"
/* 125 */       }, { Integer.valueOf(136), "circumflex"
/* 126 */       }, { Integer.valueOf(58), "colon" }, 
/* 127 */       { Integer.valueOf(44), "comma"
/* 128 */       }, { Integer.valueOf(169), "copyright"
/* 129 */       }, { Integer.valueOf(164), "currency"
/* 130 */       }, { Integer.valueOf(100), "d"
/* 131 */       }, { Integer.valueOf(134), "dagger"
/* 132 */       }, { Integer.valueOf(135), "daggerdbl"
/* 133 */       }, { Integer.valueOf(176), "degree"
/* 134 */       }, { Integer.valueOf(168), "dieresis"
/* 135 */       }, { Integer.valueOf(247), "divide"
/* 136 */       }, { Integer.valueOf(36), "dollar" }, 
/* 137 */       { Integer.valueOf(101), "e"
/* 138 */       }, { Integer.valueOf(233), "eacute"
/* 139 */       }, { Integer.valueOf(234), "ecircumflex"
/* 140 */       }, { Integer.valueOf(235), "edieresis"
/* 141 */       }, { Integer.valueOf(232), "egrave"
/* 142 */       }, { Integer.valueOf(56), "eight"
/* 143 */       }, { Integer.valueOf(133), "ellipsis"
/* 144 */       }, { Integer.valueOf(151), "emdash"
/* 145 */       }, { Integer.valueOf(150), "endash"
/* 146 */       }, { Integer.valueOf(61), "equal" }, 
/* 147 */       { Integer.valueOf(240), "eth"
/* 148 */       }, { Integer.valueOf(33), "exclam"
/* 149 */       }, { Integer.valueOf(161), "exclamdown"
/* 150 */       }, { Integer.valueOf(102), "f"
/* 151 */       }, { Integer.valueOf(53), "five"
/* 152 */       }, { Integer.valueOf(131), "florin"
/* 153 */       }, { Integer.valueOf(52), "four"
/* 154 */       }, { Integer.valueOf(103), "g"
/* 155 */       }, { Integer.valueOf(223), "germandbls"
/* 156 */       }, { Integer.valueOf(96), "grave" }, 
/* 157 */       { Integer.valueOf(62), "greater"
/* 158 */       }, { Integer.valueOf(171), "guillemotleft"
/* 159 */       }, { Integer.valueOf(187), "guillemotright"
/* 160 */       }, { Integer.valueOf(139), "guilsinglleft"
/* 161 */       }, { Integer.valueOf(155), "guilsinglright"
/* 162 */       }, { Integer.valueOf(104), "h"
/* 163 */       }, { Integer.valueOf(45), "hyphen"
/* 164 */       }, { Integer.valueOf(105), "i"
/* 165 */       }, { Integer.valueOf(237), "iacute"
/* 166 */       }, { Integer.valueOf(238), "icircumflex" }, 
/* 167 */       { Integer.valueOf(239), "idieresis"
/* 168 */       }, { Integer.valueOf(236), "igrave"
/* 169 */       }, { Integer.valueOf(106), "j"
/* 170 */       }, { Integer.valueOf(107), "k"
/* 171 */       }, { Integer.valueOf(108), "l"
/* 172 */       }, { Integer.valueOf(60), "less"
/* 173 */       }, { Integer.valueOf(172), "logicalnot"
/* 174 */       }, { Integer.valueOf(109), "m"
/* 175 */       }, { Integer.valueOf(175), "macron"
/* 176 */       }, { Integer.valueOf(181), "mu" }, 
/* 177 */       { Integer.valueOf(215), "multiply"
/* 178 */       }, { Integer.valueOf(110), "n"
/* 179 */       }, { Integer.valueOf(57), "nine"
/* 180 */       }, { Integer.valueOf(241), "ntilde"
/* 181 */       }, { Integer.valueOf(35), "numbersign"
/* 182 */       }, { Integer.valueOf(111), "o"
/* 183 */       }, { Integer.valueOf(243), "oacute"
/* 184 */       }, { Integer.valueOf(244), "ocircumflex"
/* 185 */       }, { Integer.valueOf(246), "odieresis"
/* 186 */       }, { Integer.valueOf(156), "oe" }, 
/* 187 */       { Integer.valueOf(242), "ograve"
/* 188 */       }, { Integer.valueOf(49), "one"
/* 189 */       }, { Integer.valueOf(189), "onehalf"
/* 190 */       }, { Integer.valueOf(188), "onequarter"
/* 191 */       }, { Integer.valueOf(185), "onesuperior"
/* 192 */       }, { Integer.valueOf(170), "ordfeminine"
/* 193 */       }, { Integer.valueOf(186), "ordmasculine"
/* 194 */       }, { Integer.valueOf(248), "oslash"
/* 195 */       }, { Integer.valueOf(245), "otilde"
/* 196 */       }, { Integer.valueOf(112), "p" }, 
/* 197 */       { Integer.valueOf(182), "paragraph"
/* 198 */       }, { Integer.valueOf(40), "parenleft"
/* 199 */       }, { Integer.valueOf(41), "parenright"
/* 200 */       }, { Integer.valueOf(37), "percent"
/* 201 */       }, { Integer.valueOf(46), "period"
/* 202 */       }, { Integer.valueOf(183), "periodcentered"
/* 203 */       }, { Integer.valueOf(137), "perthousand"
/* 204 */       }, { Integer.valueOf(43), "plus"
/* 205 */       }, { Integer.valueOf(177), "plusminus"
/* 206 */       }, { Integer.valueOf(113), "q" }, 
/* 207 */       { Integer.valueOf(63), "question"
/* 208 */       }, { Integer.valueOf(191), "questiondown"
/* 209 */       }, { Integer.valueOf(34), "quotedbl"
/* 210 */       }, { Integer.valueOf(132), "quotedblbase"
/* 211 */       }, { Integer.valueOf(147), "quotedblleft"
/* 212 */       }, { Integer.valueOf(148), "quotedblright"
/* 213 */       }, { Integer.valueOf(145), "quoteleft"
/* 214 */       }, { Integer.valueOf(146), "quoteright"
/* 215 */       }, { Integer.valueOf(130), "quotesinglbase"
/* 216 */       }, { Integer.valueOf(39), "quotesingle" }, 
/* 217 */       { Integer.valueOf(114), "r"
/* 218 */       }, { Integer.valueOf(174), "registered"
/* 219 */       }, { Integer.valueOf(115), "s"
/* 220 */       }, { Integer.valueOf(154), "scaron"
/* 221 */       }, { Integer.valueOf(167), "section"
/* 222 */       }, { Integer.valueOf(59), "semicolon"
/* 223 */       }, { Integer.valueOf(55), "seven"
/* 224 */       }, { Integer.valueOf(54), "six"
/* 225 */       }, { Integer.valueOf(47), "slash"
/* 226 */       }, { Integer.valueOf(32), "space" }, 
/* 227 */       { Integer.valueOf(163), "sterling"
/* 228 */       }, { Integer.valueOf(116), "t"
/* 229 */       }, { Integer.valueOf(254), "thorn"
/* 230 */       }, { Integer.valueOf(51), "three"
/* 231 */       }, { Integer.valueOf(190), "threequarters"
/* 232 */       }, { Integer.valueOf(179), "threesuperior"
/* 233 */       }, { Integer.valueOf(152), "tilde"
/* 234 */       }, { Integer.valueOf(153), "trademark"
/* 235 */       }, { Integer.valueOf(50), "two"
/* 236 */       }, { Integer.valueOf(178), "twosuperior" }, 
/* 237 */       { Integer.valueOf(117), "u"
/* 238 */       }, { Integer.valueOf(250), "uacute"
/* 239 */       }, { Integer.valueOf(251), "ucircumflex"
/* 240 */       }, { Integer.valueOf(252), "udieresis"
/* 241 */       }, { Integer.valueOf(249), "ugrave"
/* 242 */       }, { Integer.valueOf(95), "underscore"
/* 243 */       }, { Integer.valueOf(118), "v"
/* 244 */       }, { Integer.valueOf(119), "w"
/* 245 */       }, { Integer.valueOf(120), "x"
/* 246 */       }, { Integer.valueOf(121), "y" }, 
/* 247 */       { Integer.valueOf(253), "yacute"
/* 248 */       }, { Integer.valueOf(255), "ydieresis"
/* 249 */       }, { Integer.valueOf(165), "yen"
/* 250 */       }, { Integer.valueOf(122), "z"
/* 251 */       }, { Integer.valueOf(158), "zcaron"
/* 252 */       }, { Integer.valueOf(48), "zero"
/*     */       },
/* 254 */       { Integer.valueOf(160), "space"
/* 255 */       }, { Integer.valueOf(173), "hyphen" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public static final WinAnsiEncoding INSTANCE = new WinAnsiEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinAnsiEncoding() {
/* 270 */     for (Object[] encodingEntry : WIN_ANSI_ENCODING_TABLE)
/*     */     {
/* 272 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 277 */     for (int i = 33; i <= 255; i++) {
/*     */       
/* 279 */       if (!this.codeToName.containsKey(Integer.valueOf(i)))
/*     */       {
/* 281 */         add(i, "bullet");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 289 */     return (COSBase)COSName.WIN_ANSI_ENCODING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 295 */     return "WinAnsiEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/WinAnsiEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */