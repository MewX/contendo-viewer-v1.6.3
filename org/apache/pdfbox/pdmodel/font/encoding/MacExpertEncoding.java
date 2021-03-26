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
/*     */ public class MacExpertEncoding
/*     */   extends Encoding
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  34 */   private static final Object[][] MAC_EXPERT_ENCODING_TABLE = new Object[][] { 
/*  35 */       { Integer.valueOf(190), "AEsmall"
/*  36 */       }, { Integer.valueOf(135), "Aacutesmall"
/*  37 */       }, { Integer.valueOf(137), "Acircumflexsmall"
/*  38 */       }, { Integer.valueOf(39), "Acutesmall"
/*  39 */       }, { Integer.valueOf(138), "Adieresissmall"
/*  40 */       }, { Integer.valueOf(136), "Agravesmall"
/*  41 */       }, { Integer.valueOf(140), "Aringsmall"
/*  42 */       }, { Integer.valueOf(97), "Asmall"
/*  43 */       }, { Integer.valueOf(139), "Atildesmall"
/*  44 */       }, { Integer.valueOf(243), "Brevesmall" }, 
/*  45 */       { Integer.valueOf(98), "Bsmall"
/*  46 */       }, { Integer.valueOf(174), "Caronsmall"
/*  47 */       }, { Integer.valueOf(141), "Ccedillasmall"
/*  48 */       }, { Integer.valueOf(201), "Cedillasmall"
/*  49 */       }, { Integer.valueOf(94), "Circumflexsmall"
/*  50 */       }, { Integer.valueOf(99), "Csmall"
/*  51 */       }, { Integer.valueOf(172), "Dieresissmall"
/*  52 */       }, { Integer.valueOf(250), "Dotaccentsmall"
/*  53 */       }, { Integer.valueOf(100), "Dsmall"
/*  54 */       }, { Integer.valueOf(142), "Eacutesmall" }, 
/*  55 */       { Integer.valueOf(144), "Ecircumflexsmall"
/*  56 */       }, { Integer.valueOf(145), "Edieresissmall"
/*  57 */       }, { Integer.valueOf(143), "Egravesmall"
/*  58 */       }, { Integer.valueOf(101), "Esmall"
/*  59 */       }, { Integer.valueOf(68), "Ethsmall"
/*  60 */       }, { Integer.valueOf(102), "Fsmall"
/*  61 */       }, { Integer.valueOf(96), "Gravesmall"
/*  62 */       }, { Integer.valueOf(103), "Gsmall"
/*  63 */       }, { Integer.valueOf(104), "Hsmall"
/*  64 */       }, { Integer.valueOf(34), "Hungarumlautsmall" }, 
/*  65 */       { Integer.valueOf(146), "Iacutesmall"
/*  66 */       }, { Integer.valueOf(148), "Icircumflexsmall"
/*  67 */       }, { Integer.valueOf(149), "Idieresissmall"
/*  68 */       }, { Integer.valueOf(147), "Igravesmall"
/*  69 */       }, { Integer.valueOf(105), "Ismall"
/*  70 */       }, { Integer.valueOf(106), "Jsmall"
/*  71 */       }, { Integer.valueOf(107), "Ksmall"
/*  72 */       }, { Integer.valueOf(194), "Lslashsmall"
/*  73 */       }, { Integer.valueOf(108), "Lsmall"
/*  74 */       }, { Integer.valueOf(244), "Macronsmall" }, 
/*  75 */       { Integer.valueOf(109), "Msmall"
/*  76 */       }, { Integer.valueOf(110), "Nsmall"
/*  77 */       }, { Integer.valueOf(150), "Ntildesmall"
/*  78 */       }, { Integer.valueOf(207), "OEsmall"
/*  79 */       }, { Integer.valueOf(151), "Oacutesmall"
/*  80 */       }, { Integer.valueOf(153), "Ocircumflexsmall"
/*  81 */       }, { Integer.valueOf(154), "Odieresissmall"
/*  82 */       }, { Integer.valueOf(242), "Ogoneksmall"
/*  83 */       }, { Integer.valueOf(152), "Ogravesmall"
/*  84 */       }, { Integer.valueOf(191), "Oslashsmall" }, 
/*  85 */       { Integer.valueOf(111), "Osmall"
/*  86 */       }, { Integer.valueOf(155), "Otildesmall"
/*  87 */       }, { Integer.valueOf(112), "Psmall"
/*  88 */       }, { Integer.valueOf(113), "Qsmall"
/*  89 */       }, { Integer.valueOf(251), "Ringsmall"
/*  90 */       }, { Integer.valueOf(114), "Rsmall"
/*  91 */       }, { Integer.valueOf(167), "Scaronsmall"
/*  92 */       }, { Integer.valueOf(115), "Ssmall"
/*  93 */       }, { Integer.valueOf(185), "Thornsmall"
/*  94 */       }, { Integer.valueOf(126), "Tildesmall" }, 
/*  95 */       { Integer.valueOf(116), "Tsmall"
/*  96 */       }, { Integer.valueOf(156), "Uacutesmall"
/*  97 */       }, { Integer.valueOf(158), "Ucircumflexsmall"
/*  98 */       }, { Integer.valueOf(159), "Udieresissmall"
/*  99 */       }, { Integer.valueOf(157), "Ugravesmall"
/* 100 */       }, { Integer.valueOf(117), "Usmall"
/* 101 */       }, { Integer.valueOf(118), "Vsmall"
/* 102 */       }, { Integer.valueOf(119), "Wsmall"
/* 103 */       }, { Integer.valueOf(120), "Xsmall"
/* 104 */       }, { Integer.valueOf(180), "Yacutesmall" }, 
/* 105 */       { Integer.valueOf(216), "Ydieresissmall"
/* 106 */       }, { Integer.valueOf(121), "Ysmall"
/* 107 */       }, { Integer.valueOf(189), "Zcaronsmall"
/* 108 */       }, { Integer.valueOf(122), "Zsmall"
/* 109 */       }, { Integer.valueOf(38), "ampersandsmall"
/* 110 */       }, { Integer.valueOf(129), "asuperior"
/* 111 */       }, { Integer.valueOf(245), "bsuperior"
/* 112 */       }, { Integer.valueOf(169), "centinferior"
/* 113 */       }, { Integer.valueOf(35), "centoldstyle"
/* 114 */       }, { Integer.valueOf(130), "centsuperior" }, 
/* 115 */       { Integer.valueOf(58), "colon"
/* 116 */       }, { Integer.valueOf(123), "colonmonetary"
/* 117 */       }, { Integer.valueOf(44), "comma"
/* 118 */       }, { Integer.valueOf(178), "commainferior"
/* 119 */       }, { Integer.valueOf(248), "commasuperior"
/* 120 */       }, { Integer.valueOf(182), "dollarinferior"
/* 121 */       }, { Integer.valueOf(36), "dollaroldstyle"
/* 122 */       }, { Integer.valueOf(37), "dollarsuperior"
/* 123 */       }, { Integer.valueOf(235), "dsuperior"
/* 124 */       }, { Integer.valueOf(165), "eightinferior" }, 
/* 125 */       { Integer.valueOf(56), "eightoldstyle"
/* 126 */       }, { Integer.valueOf(161), "eightsuperior"
/* 127 */       }, { Integer.valueOf(228), "esuperior"
/* 128 */       }, { Integer.valueOf(214), "exclamdownsmall"
/* 129 */       }, { Integer.valueOf(33), "exclamsmall"
/* 130 */       }, { Integer.valueOf(86), "ff"
/* 131 */       }, { Integer.valueOf(89), "ffi"
/* 132 */       }, { Integer.valueOf(90), "ffl"
/* 133 */       }, { Integer.valueOf(87), "fi"
/* 134 */       }, { Integer.valueOf(208), "figuredash" }, 
/* 135 */       { Integer.valueOf(76), "fiveeighths"
/* 136 */       }, { Integer.valueOf(176), "fiveinferior"
/* 137 */       }, { Integer.valueOf(53), "fiveoldstyle"
/* 138 */       }, { Integer.valueOf(222), "fivesuperior"
/* 139 */       }, { Integer.valueOf(88), "fl"
/* 140 */       }, { Integer.valueOf(162), "fourinferior"
/* 141 */       }, { Integer.valueOf(52), "fouroldstyle"
/* 142 */       }, { Integer.valueOf(221), "foursuperior"
/* 143 */       }, { Integer.valueOf(47), "fraction"
/* 144 */       }, { Integer.valueOf(45), "hyphen" }, 
/* 145 */       { Integer.valueOf(95), "hypheninferior"
/* 146 */       }, { Integer.valueOf(209), "hyphensuperior"
/* 147 */       }, { Integer.valueOf(233), "isuperior"
/* 148 */       }, { Integer.valueOf(241), "lsuperior"
/* 149 */       }, { Integer.valueOf(247), "msuperior"
/* 150 */       }, { Integer.valueOf(187), "nineinferior"
/* 151 */       }, { Integer.valueOf(57), "nineoldstyle"
/* 152 */       }, { Integer.valueOf(225), "ninesuperior"
/* 153 */       }, { Integer.valueOf(246), "nsuperior"
/* 154 */       }, { Integer.valueOf(43), "onedotenleader" }, 
/* 155 */       { Integer.valueOf(74), "oneeighth"
/* 156 */       }, { Integer.valueOf(124), "onefitted"
/* 157 */       }, { Integer.valueOf(72), "onehalf"
/* 158 */       }, { Integer.valueOf(193), "oneinferior"
/* 159 */       }, { Integer.valueOf(49), "oneoldstyle"
/* 160 */       }, { Integer.valueOf(71), "onequarter"
/* 161 */       }, { Integer.valueOf(218), "onesuperior"
/* 162 */       }, { Integer.valueOf(78), "onethird"
/* 163 */       }, { Integer.valueOf(175), "osuperior"
/* 164 */       }, { Integer.valueOf(91), "parenleftinferior" }, 
/* 165 */       { Integer.valueOf(40), "parenleftsuperior"
/* 166 */       }, { Integer.valueOf(93), "parenrightinferior"
/* 167 */       }, { Integer.valueOf(41), "parenrightsuperior"
/* 168 */       }, { Integer.valueOf(46), "period"
/* 169 */       }, { Integer.valueOf(179), "periodinferior"
/* 170 */       }, { Integer.valueOf(249), "periodsuperior"
/* 171 */       }, { Integer.valueOf(192), "questiondownsmall"
/* 172 */       }, { Integer.valueOf(63), "questionsmall"
/* 173 */       }, { Integer.valueOf(229), "rsuperior"
/* 174 */       }, { Integer.valueOf(125), "rupiah" }, 
/* 175 */       { Integer.valueOf(59), "semicolon"
/* 176 */       }, { Integer.valueOf(77), "seveneighths"
/* 177 */       }, { Integer.valueOf(166), "seveninferior"
/* 178 */       }, { Integer.valueOf(55), "sevenoldstyle"
/* 179 */       }, { Integer.valueOf(224), "sevensuperior"
/* 180 */       }, { Integer.valueOf(164), "sixinferior"
/* 181 */       }, { Integer.valueOf(54), "sixoldstyle"
/* 182 */       }, { Integer.valueOf(223), "sixsuperior"
/* 183 */       }, { Integer.valueOf(32), "space"
/* 184 */       }, { Integer.valueOf(234), "ssuperior" }, 
/* 185 */       { Integer.valueOf(75), "threeeighths"
/* 186 */       }, { Integer.valueOf(163), "threeinferior"
/* 187 */       }, { Integer.valueOf(51), "threeoldstyle"
/* 188 */       }, { Integer.valueOf(73), "threequarters"
/* 189 */       }, { Integer.valueOf(61), "threequartersemdash"
/* 190 */       }, { Integer.valueOf(220), "threesuperior"
/* 191 */       }, { Integer.valueOf(230), "tsuperior"
/* 192 */       }, { Integer.valueOf(42), "twodotenleader"
/* 193 */       }, { Integer.valueOf(170), "twoinferior"
/* 194 */       }, { Integer.valueOf(50), "twooldstyle" }, 
/* 195 */       { Integer.valueOf(219), "twosuperior"
/* 196 */       }, { Integer.valueOf(79), "twothirds"
/* 197 */       }, { Integer.valueOf(188), "zeroinferior"
/* 198 */       }, { Integer.valueOf(48), "zerooldstyle"
/* 199 */       }, { Integer.valueOf(226), "zerosuperior" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public static final MacExpertEncoding INSTANCE = new MacExpertEncoding();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MacExpertEncoding() {
/* 212 */     for (Object[] encodingEntry : MAC_EXPERT_ENCODING_TABLE)
/*     */     {
/* 214 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 221 */     return (COSBase)COSName.MAC_EXPERT_ENCODING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncodingName() {
/* 227 */     return "MacExpertEncoding";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/MacExpertEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */