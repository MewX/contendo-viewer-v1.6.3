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
/*     */ public class SymbolEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  34 */   private static final Object[][] SYMBOL_ENCODING_TABLE = new Object[][] { 
/*  35 */       { Integer.valueOf(65), "Alpha"
/*  36 */       }, { Integer.valueOf(66), "Beta"
/*  37 */       }, { Integer.valueOf(67), "Chi"
/*  38 */       }, { Integer.valueOf(68), "Delta"
/*  39 */       }, { Integer.valueOf(69), "Epsilon"
/*  40 */       }, { Integer.valueOf(72), "Eta"
/*  41 */       }, { Integer.valueOf(160), "Euro"
/*  42 */       }, { Integer.valueOf(71), "Gamma"
/*  43 */       }, { Integer.valueOf(193), "Ifraktur"
/*  44 */       }, { Integer.valueOf(73), "Iota" }, 
/*  45 */       { Integer.valueOf(75), "Kappa"
/*  46 */       }, { Integer.valueOf(76), "Lambda"
/*  47 */       }, { Integer.valueOf(77), "Mu"
/*  48 */       }, { Integer.valueOf(78), "Nu"
/*  49 */       }, { Integer.valueOf(87), "Omega"
/*  50 */       }, { Integer.valueOf(79), "Omicron"
/*  51 */       }, { Integer.valueOf(70), "Phi"
/*  52 */       }, { Integer.valueOf(80), "Pi"
/*  53 */       }, { Integer.valueOf(89), "Psi"
/*  54 */       }, { Integer.valueOf(194), "Rfraktur" }, 
/*  55 */       { Integer.valueOf(82), "Rho"
/*  56 */       }, { Integer.valueOf(83), "Sigma"
/*  57 */       }, { Integer.valueOf(84), "Tau"
/*  58 */       }, { Integer.valueOf(81), "Theta"
/*  59 */       }, { Integer.valueOf(85), "Upsilon"
/*  60 */       }, { Integer.valueOf(161), "Upsilon1"
/*  61 */       }, { Integer.valueOf(88), "Xi"
/*  62 */       }, { Integer.valueOf(90), "Zeta"
/*  63 */       }, { Integer.valueOf(192), "aleph"
/*  64 */       }, { Integer.valueOf(97), "alpha" }, 
/*  65 */       { Integer.valueOf(38), "ampersand"
/*  66 */       }, { Integer.valueOf(208), "angle"
/*  67 */       }, { Integer.valueOf(225), "angleleft"
/*  68 */       }, { Integer.valueOf(241), "angleright"
/*  69 */       }, { Integer.valueOf(187), "approxequal"
/*  70 */       }, { Integer.valueOf(171), "arrowboth"
/*  71 */       }, { Integer.valueOf(219), "arrowdblboth"
/*  72 */       }, { Integer.valueOf(223), "arrowdbldown"
/*  73 */       }, { Integer.valueOf(220), "arrowdblleft"
/*  74 */       }, { Integer.valueOf(222), "arrowdblright" }, 
/*  75 */       { Integer.valueOf(221), "arrowdblup"
/*  76 */       }, { Integer.valueOf(175), "arrowdown"
/*  77 */       }, { Integer.valueOf(190), "arrowhorizex"
/*  78 */       }, { Integer.valueOf(172), "arrowleft"
/*  79 */       }, { Integer.valueOf(174), "arrowright"
/*  80 */       }, { Integer.valueOf(173), "arrowup"
/*  81 */       }, { Integer.valueOf(189), "arrowvertex"
/*  82 */       }, { Integer.valueOf(42), "asteriskmath"
/*  83 */       }, { Integer.valueOf(124), "bar"
/*  84 */       }, { Integer.valueOf(98), "beta" }, 
/*  85 */       { Integer.valueOf(123), "braceleft"
/*  86 */       }, { Integer.valueOf(125), "braceright"
/*  87 */       }, { Integer.valueOf(236), "bracelefttp"
/*  88 */       }, { Integer.valueOf(237), "braceleftmid"
/*  89 */       }, { Integer.valueOf(238), "braceleftbt"
/*  90 */       }, { Integer.valueOf(252), "bracerighttp"
/*  91 */       }, { Integer.valueOf(253), "bracerightmid"
/*  92 */       }, { Integer.valueOf(254), "bracerightbt"
/*  93 */       }, { Integer.valueOf(239), "braceex"
/*  94 */       }, { Integer.valueOf(91), "bracketleft" }, 
/*  95 */       { Integer.valueOf(93), "bracketright"
/*  96 */       }, { Integer.valueOf(233), "bracketlefttp"
/*  97 */       }, { Integer.valueOf(234), "bracketleftex"
/*  98 */       }, { Integer.valueOf(235), "bracketleftbt"
/*  99 */       }, { Integer.valueOf(249), "bracketrighttp"
/* 100 */       }, { Integer.valueOf(250), "bracketrightex"
/* 101 */       }, { Integer.valueOf(251), "bracketrightbt"
/* 102 */       }, { Integer.valueOf(183), "bullet"
/* 103 */       }, { Integer.valueOf(191), "carriagereturn"
/* 104 */       }, { Integer.valueOf(99), "chi" }, 
/* 105 */       { Integer.valueOf(196), "circlemultiply"
/* 106 */       }, { Integer.valueOf(197), "circleplus"
/* 107 */       }, { Integer.valueOf(167), "club"
/* 108 */       }, { Integer.valueOf(58), "colon"
/* 109 */       }, { Integer.valueOf(44), "comma"
/* 110 */       }, { Integer.valueOf(64), "congruent"
/* 111 */       }, { Integer.valueOf(227), "copyrightsans"
/* 112 */       }, { Integer.valueOf(211), "copyrightserif"
/* 113 */       }, { Integer.valueOf(176), "degree"
/* 114 */       }, { Integer.valueOf(100), "delta" }, 
/* 115 */       { Integer.valueOf(168), "diamond"
/* 116 */       }, { Integer.valueOf(184), "divide"
/* 117 */       }, { Integer.valueOf(215), "dotmath"
/* 118 */       }, { Integer.valueOf(56), "eight"
/* 119 */       }, { Integer.valueOf(206), "element"
/* 120 */       }, { Integer.valueOf(188), "ellipsis"
/* 121 */       }, { Integer.valueOf(198), "emptyset"
/* 122 */       }, { Integer.valueOf(101), "epsilon"
/* 123 */       }, { Integer.valueOf(61), "equal"
/* 124 */       }, { Integer.valueOf(186), "equivalence" }, 
/* 125 */       { Integer.valueOf(104), "eta"
/* 126 */       }, { Integer.valueOf(33), "exclam"
/* 127 */       }, { Integer.valueOf(36), "existential"
/* 128 */       }, { Integer.valueOf(53), "five"
/* 129 */       }, { Integer.valueOf(166), "florin"
/* 130 */       }, { Integer.valueOf(52), "four"
/* 131 */       }, { Integer.valueOf(164), "fraction"
/* 132 */       }, { Integer.valueOf(103), "gamma"
/* 133 */       }, { Integer.valueOf(209), "gradient"
/* 134 */       }, { Integer.valueOf(62), "greater" }, 
/* 135 */       { Integer.valueOf(179), "greaterequal"
/* 136 */       }, { Integer.valueOf(169), "heart"
/* 137 */       }, { Integer.valueOf(165), "infinity"
/* 138 */       }, { Integer.valueOf(242), "integral"
/* 139 */       }, { Integer.valueOf(243), "integraltp"
/* 140 */       }, { Integer.valueOf(244), "integralex"
/* 141 */       }, { Integer.valueOf(245), "integralbt"
/* 142 */       }, { Integer.valueOf(199), "intersection"
/* 143 */       }, { Integer.valueOf(105), "iota"
/* 144 */       }, { Integer.valueOf(107), "kappa" }, 
/* 145 */       { Integer.valueOf(108), "lambda"
/* 146 */       }, { Integer.valueOf(60), "less"
/* 147 */       }, { Integer.valueOf(163), "lessequal"
/* 148 */       }, { Integer.valueOf(217), "logicaland"
/* 149 */       }, { Integer.valueOf(216), "logicalnot"
/* 150 */       }, { Integer.valueOf(218), "logicalor"
/* 151 */       }, { Integer.valueOf(224), "lozenge"
/* 152 */       }, { Integer.valueOf(45), "minus"
/* 153 */       }, { Integer.valueOf(162), "minute"
/* 154 */       }, { Integer.valueOf(109), "mu" }, 
/* 155 */       { Integer.valueOf(180), "multiply"
/* 156 */       }, { Integer.valueOf(57), "nine"
/* 157 */       }, { Integer.valueOf(207), "notelement"
/* 158 */       }, { Integer.valueOf(185), "notequal"
/* 159 */       }, { Integer.valueOf(203), "notsubset"
/* 160 */       }, { Integer.valueOf(110), "nu"
/* 161 */       }, { Integer.valueOf(35), "numbersign"
/* 162 */       }, { Integer.valueOf(119), "omega"
/* 163 */       }, { Integer.valueOf(118), "omega1"
/* 164 */       }, { Integer.valueOf(111), "omicron" }, 
/* 165 */       { Integer.valueOf(49), "one"
/* 166 */       }, { Integer.valueOf(40), "parenleft"
/* 167 */       }, { Integer.valueOf(41), "parenright"
/* 168 */       }, { Integer.valueOf(230), "parenlefttp"
/* 169 */       }, { Integer.valueOf(231), "parenleftex"
/* 170 */       }, { Integer.valueOf(232), "parenleftbt"
/* 171 */       }, { Integer.valueOf(246), "parenrighttp"
/* 172 */       }, { Integer.valueOf(247), "parenrightex"
/* 173 */       }, { Integer.valueOf(248), "parenrightbt"
/* 174 */       }, { Integer.valueOf(182), "partialdiff" }, 
/* 175 */       { Integer.valueOf(37), "percent"
/* 176 */       }, { Integer.valueOf(46), "period"
/* 177 */       }, { Integer.valueOf(94), "perpendicular"
/* 178 */       }, { Integer.valueOf(102), "phi"
/* 179 */       }, { Integer.valueOf(106), "phi1"
/* 180 */       }, { Integer.valueOf(112), "pi"
/* 181 */       }, { Integer.valueOf(43), "plus"
/* 182 */       }, { Integer.valueOf(177), "plusminus"
/* 183 */       }, { Integer.valueOf(213), "product"
/* 184 */       }, { Integer.valueOf(204), "propersubset" }, 
/* 185 */       { Integer.valueOf(201), "propersuperset"
/* 186 */       }, { Integer.valueOf(181), "proportional"
/* 187 */       }, { Integer.valueOf(121), "psi"
/* 188 */       }, { Integer.valueOf(63), "question"
/* 189 */       }, { Integer.valueOf(214), "radical"
/* 190 */       }, { Integer.valueOf(96), "radicalex"
/* 191 */       }, { Integer.valueOf(205), "reflexsubset"
/* 192 */       }, { Integer.valueOf(202), "reflexsuperset"
/* 193 */       }, { Integer.valueOf(226), "registersans"
/* 194 */       }, { Integer.valueOf(210), "registerserif" }, 
/* 195 */       { Integer.valueOf(114), "rho"
/* 196 */       }, { Integer.valueOf(178), "second"
/* 197 */       }, { Integer.valueOf(59), "semicolon"
/* 198 */       }, { Integer.valueOf(55), "seven"
/* 199 */       }, { Integer.valueOf(115), "sigma"
/* 200 */       }, { Integer.valueOf(86), "sigma1"
/* 201 */       }, { Integer.valueOf(126), "similar"
/* 202 */       }, { Integer.valueOf(54), "six"
/* 203 */       }, { Integer.valueOf(47), "slash"
/* 204 */       }, { Integer.valueOf(32), "space" }, 
/* 205 */       { Integer.valueOf(170), "spade"
/* 206 */       }, { Integer.valueOf(39), "suchthat"
/* 207 */       }, { Integer.valueOf(229), "summation"
/* 208 */       }, { Integer.valueOf(116), "tau"
/* 209 */       }, { Integer.valueOf(92), "therefore"
/* 210 */       }, { Integer.valueOf(113), "theta"
/* 211 */       }, { Integer.valueOf(74), "theta1"
/* 212 */       }, { Integer.valueOf(51), "three"
/* 213 */       }, { Integer.valueOf(228), "trademarksans"
/* 214 */       }, { Integer.valueOf(212), "trademarkserif" }, 
/* 215 */       { Integer.valueOf(50), "two"
/* 216 */       }, { Integer.valueOf(95), "underscore"
/* 217 */       }, { Integer.valueOf(200), "union"
/* 218 */       }, { Integer.valueOf(34), "universal"
/* 219 */       }, { Integer.valueOf(117), "upsilon"
/* 220 */       }, { Integer.valueOf(195), "weierstrass"
/* 221 */       }, { Integer.valueOf(120), "xi"
/* 222 */       }, { Integer.valueOf(48), "zero"
/* 223 */       }, { Integer.valueOf(122), "zeta" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public static final SymbolEncoding INSTANCE = new SymbolEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SymbolEncoding() {
/* 236 */     for (Object[] encodingEntry : SYMBOL_ENCODING_TABLE)
/*     */     {
/* 238 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 245 */     return (COSBase)COSName.getPDFName("SymbolEncoding");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 251 */     return "SymbolEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/SymbolEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */