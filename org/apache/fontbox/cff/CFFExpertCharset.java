/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CFFExpertCharset
/*     */   extends CFFCharset
/*     */ {
/*     */   private static final int CHAR_CODE = 0;
/*     */   private static final int CHAR_NAME = 1;
/*  32 */   private static final Object[][] CFF_EXPERT_CHARSET_TABLE = new Object[][] { 
/*  33 */       { Integer.valueOf(0), ".notdef"
/*  34 */       }, { Integer.valueOf(1), "space"
/*  35 */       }, { Integer.valueOf(229), "exclamsmall"
/*  36 */       }, { Integer.valueOf(230), "Hungarumlautsmall"
/*  37 */       }, { Integer.valueOf(231), "dollaroldstyle"
/*  38 */       }, { Integer.valueOf(232), "dollarsuperior"
/*  39 */       }, { Integer.valueOf(233), "ampersandsmall"
/*  40 */       }, { Integer.valueOf(234), "Acutesmall"
/*  41 */       }, { Integer.valueOf(235), "parenleftsuperior"
/*  42 */       }, { Integer.valueOf(236), "parenrightsuperior" }, 
/*  43 */       { Integer.valueOf(237), "twodotenleader"
/*  44 */       }, { Integer.valueOf(238), "onedotenleader"
/*  45 */       }, { Integer.valueOf(13), "comma"
/*  46 */       }, { Integer.valueOf(14), "hyphen"
/*  47 */       }, { Integer.valueOf(15), "period"
/*  48 */       }, { Integer.valueOf(99), "fraction"
/*  49 */       }, { Integer.valueOf(239), "zerooldstyle"
/*  50 */       }, { Integer.valueOf(240), "oneoldstyle"
/*  51 */       }, { Integer.valueOf(241), "twooldstyle"
/*  52 */       }, { Integer.valueOf(242), "threeoldstyle" }, 
/*  53 */       { Integer.valueOf(243), "fouroldstyle"
/*  54 */       }, { Integer.valueOf(244), "fiveoldstyle"
/*  55 */       }, { Integer.valueOf(245), "sixoldstyle"
/*  56 */       }, { Integer.valueOf(246), "sevenoldstyle"
/*  57 */       }, { Integer.valueOf(247), "eightoldstyle"
/*  58 */       }, { Integer.valueOf(248), "nineoldstyle"
/*  59 */       }, { Integer.valueOf(27), "colon"
/*  60 */       }, { Integer.valueOf(28), "semicolon"
/*  61 */       }, { Integer.valueOf(249), "commasuperior"
/*  62 */       }, { Integer.valueOf(250), "threequartersemdash" }, 
/*  63 */       { Integer.valueOf(251), "periodsuperior"
/*  64 */       }, { Integer.valueOf(252), "questionsmall"
/*  65 */       }, { Integer.valueOf(253), "asuperior"
/*  66 */       }, { Integer.valueOf(254), "bsuperior"
/*  67 */       }, { Integer.valueOf(255), "centsuperior"
/*  68 */       }, { Integer.valueOf(256), "dsuperior"
/*  69 */       }, { Integer.valueOf(257), "esuperior"
/*  70 */       }, { Integer.valueOf(258), "isuperior"
/*  71 */       }, { Integer.valueOf(259), "lsuperior"
/*  72 */       }, { Integer.valueOf(260), "msuperior" }, 
/*  73 */       { Integer.valueOf(261), "nsuperior"
/*  74 */       }, { Integer.valueOf(262), "osuperior"
/*  75 */       }, { Integer.valueOf(263), "rsuperior"
/*  76 */       }, { Integer.valueOf(264), "ssuperior"
/*  77 */       }, { Integer.valueOf(265), "tsuperior"
/*  78 */       }, { Integer.valueOf(266), "ff"
/*  79 */       }, { Integer.valueOf(109), "fi"
/*  80 */       }, { Integer.valueOf(110), "fl"
/*  81 */       }, { Integer.valueOf(267), "ffi"
/*  82 */       }, { Integer.valueOf(268), "ffl" }, 
/*  83 */       { Integer.valueOf(269), "parenleftinferior"
/*  84 */       }, { Integer.valueOf(270), "parenrightinferior"
/*  85 */       }, { Integer.valueOf(271), "Circumflexsmall"
/*  86 */       }, { Integer.valueOf(272), "hyphensuperior"
/*  87 */       }, { Integer.valueOf(273), "Gravesmall"
/*  88 */       }, { Integer.valueOf(274), "Asmall"
/*  89 */       }, { Integer.valueOf(275), "Bsmall"
/*  90 */       }, { Integer.valueOf(276), "Csmall"
/*  91 */       }, { Integer.valueOf(277), "Dsmall"
/*  92 */       }, { Integer.valueOf(278), "Esmall" }, 
/*  93 */       { Integer.valueOf(279), "Fsmall"
/*  94 */       }, { Integer.valueOf(280), "Gsmall"
/*  95 */       }, { Integer.valueOf(281), "Hsmall"
/*  96 */       }, { Integer.valueOf(282), "Ismall"
/*  97 */       }, { Integer.valueOf(283), "Jsmall"
/*  98 */       }, { Integer.valueOf(284), "Ksmall"
/*  99 */       }, { Integer.valueOf(285), "Lsmall"
/* 100 */       }, { Integer.valueOf(286), "Msmall"
/* 101 */       }, { Integer.valueOf(287), "Nsmall"
/* 102 */       }, { Integer.valueOf(288), "Osmall" }, 
/* 103 */       { Integer.valueOf(289), "Psmall"
/* 104 */       }, { Integer.valueOf(290), "Qsmall"
/* 105 */       }, { Integer.valueOf(291), "Rsmall"
/* 106 */       }, { Integer.valueOf(292), "Ssmall"
/* 107 */       }, { Integer.valueOf(293), "Tsmall"
/* 108 */       }, { Integer.valueOf(294), "Usmall"
/* 109 */       }, { Integer.valueOf(295), "Vsmall"
/* 110 */       }, { Integer.valueOf(296), "Wsmall"
/* 111 */       }, { Integer.valueOf(297), "Xsmall"
/* 112 */       }, { Integer.valueOf(298), "Ysmall" }, 
/* 113 */       { Integer.valueOf(299), "Zsmall"
/* 114 */       }, { Integer.valueOf(300), "colonmonetary"
/* 115 */       }, { Integer.valueOf(301), "onefitted"
/* 116 */       }, { Integer.valueOf(302), "rupiah"
/* 117 */       }, { Integer.valueOf(303), "Tildesmall"
/* 118 */       }, { Integer.valueOf(304), "exclamdownsmall"
/* 119 */       }, { Integer.valueOf(305), "centoldstyle"
/* 120 */       }, { Integer.valueOf(306), "Lslashsmall"
/* 121 */       }, { Integer.valueOf(307), "Scaronsmall"
/* 122 */       }, { Integer.valueOf(308), "Zcaronsmall" }, 
/* 123 */       { Integer.valueOf(309), "Dieresissmall"
/* 124 */       }, { Integer.valueOf(310), "Brevesmall"
/* 125 */       }, { Integer.valueOf(311), "Caronsmall"
/* 126 */       }, { Integer.valueOf(312), "Dotaccentsmall"
/* 127 */       }, { Integer.valueOf(313), "Macronsmall"
/* 128 */       }, { Integer.valueOf(314), "figuredash"
/* 129 */       }, { Integer.valueOf(315), "hypheninferior"
/* 130 */       }, { Integer.valueOf(316), "Ogoneksmall"
/* 131 */       }, { Integer.valueOf(317), "Ringsmall"
/* 132 */       }, { Integer.valueOf(318), "Cedillasmall" }, 
/* 133 */       { Integer.valueOf(158), "onequarter"
/* 134 */       }, { Integer.valueOf(155), "onehalf"
/* 135 */       }, { Integer.valueOf(163), "threequarters"
/* 136 */       }, { Integer.valueOf(319), "questiondownsmall"
/* 137 */       }, { Integer.valueOf(320), "oneeighth"
/* 138 */       }, { Integer.valueOf(321), "threeeighths"
/* 139 */       }, { Integer.valueOf(322), "fiveeighths"
/* 140 */       }, { Integer.valueOf(323), "seveneighths"
/* 141 */       }, { Integer.valueOf(324), "onethird"
/* 142 */       }, { Integer.valueOf(325), "twothirds" }, 
/* 143 */       { Integer.valueOf(326), "zerosuperior"
/* 144 */       }, { Integer.valueOf(150), "onesuperior"
/* 145 */       }, { Integer.valueOf(164), "twosuperior"
/* 146 */       }, { Integer.valueOf(169), "threesuperior"
/* 147 */       }, { Integer.valueOf(327), "foursuperior"
/* 148 */       }, { Integer.valueOf(328), "fivesuperior"
/* 149 */       }, { Integer.valueOf(329), "sixsuperior"
/* 150 */       }, { Integer.valueOf(330), "sevensuperior"
/* 151 */       }, { Integer.valueOf(331), "eightsuperior"
/* 152 */       }, { Integer.valueOf(332), "ninesuperior" }, 
/* 153 */       { Integer.valueOf(333), "zeroinferior"
/* 154 */       }, { Integer.valueOf(334), "oneinferior"
/* 155 */       }, { Integer.valueOf(335), "twoinferior"
/* 156 */       }, { Integer.valueOf(336), "threeinferior"
/* 157 */       }, { Integer.valueOf(337), "fourinferior"
/* 158 */       }, { Integer.valueOf(338), "fiveinferior"
/* 159 */       }, { Integer.valueOf(339), "sixinferior"
/* 160 */       }, { Integer.valueOf(340), "seveninferior"
/* 161 */       }, { Integer.valueOf(341), "eightinferior"
/* 162 */       }, { Integer.valueOf(342), "nineinferior" }, 
/* 163 */       { Integer.valueOf(343), "centinferior"
/* 164 */       }, { Integer.valueOf(344), "dollarinferior"
/* 165 */       }, { Integer.valueOf(345), "periodinferior"
/* 166 */       }, { Integer.valueOf(346), "commainferior"
/* 167 */       }, { Integer.valueOf(347), "Agravesmall"
/* 168 */       }, { Integer.valueOf(348), "Aacutesmall"
/* 169 */       }, { Integer.valueOf(349), "Acircumflexsmall"
/* 170 */       }, { Integer.valueOf(350), "Atildesmall"
/* 171 */       }, { Integer.valueOf(351), "Adieresissmall"
/* 172 */       }, { Integer.valueOf(352), "Aringsmall" }, 
/* 173 */       { Integer.valueOf(353), "AEsmall"
/* 174 */       }, { Integer.valueOf(354), "Ccedillasmall"
/* 175 */       }, { Integer.valueOf(355), "Egravesmall"
/* 176 */       }, { Integer.valueOf(356), "Eacutesmall"
/* 177 */       }, { Integer.valueOf(357), "Ecircumflexsmall"
/* 178 */       }, { Integer.valueOf(358), "Edieresissmall"
/* 179 */       }, { Integer.valueOf(359), "Igravesmall"
/* 180 */       }, { Integer.valueOf(360), "Iacutesmall"
/* 181 */       }, { Integer.valueOf(361), "Icircumflexsmall"
/* 182 */       }, { Integer.valueOf(362), "Idieresissmall" }, 
/* 183 */       { Integer.valueOf(363), "Ethsmall"
/* 184 */       }, { Integer.valueOf(364), "Ntildesmall"
/* 185 */       }, { Integer.valueOf(365), "Ogravesmall"
/* 186 */       }, { Integer.valueOf(366), "Oacutesmall"
/* 187 */       }, { Integer.valueOf(367), "Ocircumflexsmall"
/* 188 */       }, { Integer.valueOf(368), "Otildesmall"
/* 189 */       }, { Integer.valueOf(369), "Odieresissmall"
/* 190 */       }, { Integer.valueOf(370), "OEsmall"
/* 191 */       }, { Integer.valueOf(371), "Oslashsmall"
/* 192 */       }, { Integer.valueOf(372), "Ugravesmall" }, 
/* 193 */       { Integer.valueOf(373), "Uacutesmall"
/* 194 */       }, { Integer.valueOf(374), "Ucircumflexsmall"
/* 195 */       }, { Integer.valueOf(375), "Udieresissmall"
/* 196 */       }, { Integer.valueOf(376), "Yacutesmall"
/* 197 */       }, { Integer.valueOf(377), "Thornsmall"
/* 198 */       }, { Integer.valueOf(378), "Ydieresissmall" } };
/*     */ 
/*     */ 
/*     */   
/*     */   private CFFExpertCharset() {
/* 203 */     super(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CFFExpertCharset getInstance() {
/* 212 */     return INSTANCE;
/*     */   }
/*     */   
/* 215 */   private static final CFFExpertCharset INSTANCE = new CFFExpertCharset();
/*     */ 
/*     */   
/*     */   static {
/* 219 */     int gid = 0;
/* 220 */     for (Object[] charsetEntry : CFF_EXPERT_CHARSET_TABLE)
/*     */     {
/* 222 */       INSTANCE.addSID(gid++, ((Integer)charsetEntry[0]).intValue(), charsetEntry[1].toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFExpertCharset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */