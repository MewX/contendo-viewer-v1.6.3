/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.AbstractColorManager;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorManager
/*     */   extends AbstractColorManager
/*     */ {
/*  38 */   protected static final Value DEFAULT_VALUE = SVGValueConstants.BLACK_RGB_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  45 */     values.put("aliceblue", SVGValueConstants.ALICEBLUE_VALUE);
/*     */     
/*  47 */     values.put("antiquewhite", SVGValueConstants.ANTIQUEWHITE_VALUE);
/*     */     
/*  49 */     values.put("aquamarine", SVGValueConstants.AQUAMARINE_VALUE);
/*     */     
/*  51 */     values.put("azure", SVGValueConstants.AZURE_VALUE);
/*     */     
/*  53 */     values.put("beige", SVGValueConstants.BEIGE_VALUE);
/*     */     
/*  55 */     values.put("bisque", SVGValueConstants.BISQUE_VALUE);
/*     */     
/*  57 */     values.put("blanchedalmond", SVGValueConstants.BLANCHEDALMOND_VALUE);
/*     */     
/*  59 */     values.put("blueviolet", SVGValueConstants.BLUEVIOLET_VALUE);
/*     */     
/*  61 */     values.put("brown", SVGValueConstants.BROWN_VALUE);
/*     */     
/*  63 */     values.put("burlywood", SVGValueConstants.BURLYWOOD_VALUE);
/*     */     
/*  65 */     values.put("cadetblue", SVGValueConstants.CADETBLUE_VALUE);
/*     */     
/*  67 */     values.put("chartreuse", SVGValueConstants.CHARTREUSE_VALUE);
/*     */     
/*  69 */     values.put("chocolate", SVGValueConstants.CHOCOLATE_VALUE);
/*     */     
/*  71 */     values.put("coral", SVGValueConstants.CORAL_VALUE);
/*     */     
/*  73 */     values.put("cornflowerblue", SVGValueConstants.CORNFLOWERBLUE_VALUE);
/*     */     
/*  75 */     values.put("cornsilk", SVGValueConstants.CORNSILK_VALUE);
/*     */     
/*  77 */     values.put("crimson", SVGValueConstants.CRIMSON_VALUE);
/*     */     
/*  79 */     values.put("cyan", SVGValueConstants.CYAN_VALUE);
/*     */     
/*  81 */     values.put("darkblue", SVGValueConstants.DARKBLUE_VALUE);
/*     */     
/*  83 */     values.put("darkcyan", SVGValueConstants.DARKCYAN_VALUE);
/*     */     
/*  85 */     values.put("darkgoldenrod", SVGValueConstants.DARKGOLDENROD_VALUE);
/*     */     
/*  87 */     values.put("darkgray", SVGValueConstants.DARKGRAY_VALUE);
/*     */     
/*  89 */     values.put("darkgreen", SVGValueConstants.DARKGREEN_VALUE);
/*     */     
/*  91 */     values.put("darkgrey", SVGValueConstants.DARKGREY_VALUE);
/*     */     
/*  93 */     values.put("darkkhaki", SVGValueConstants.DARKKHAKI_VALUE);
/*     */     
/*  95 */     values.put("darkmagenta", SVGValueConstants.DARKMAGENTA_VALUE);
/*     */     
/*  97 */     values.put("darkolivegreen", SVGValueConstants.DARKOLIVEGREEN_VALUE);
/*     */     
/*  99 */     values.put("darkorange", SVGValueConstants.DARKORANGE_VALUE);
/*     */     
/* 101 */     values.put("darkorchid", SVGValueConstants.DARKORCHID_VALUE);
/*     */     
/* 103 */     values.put("darkred", SVGValueConstants.DARKRED_VALUE);
/*     */     
/* 105 */     values.put("darksalmon", SVGValueConstants.DARKSALMON_VALUE);
/*     */     
/* 107 */     values.put("darkseagreen", SVGValueConstants.DARKSEAGREEN_VALUE);
/*     */     
/* 109 */     values.put("darkslateblue", SVGValueConstants.DARKSLATEBLUE_VALUE);
/*     */     
/* 111 */     values.put("darkslategray", SVGValueConstants.DARKSLATEGRAY_VALUE);
/*     */     
/* 113 */     values.put("darkslategrey", SVGValueConstants.DARKSLATEGREY_VALUE);
/*     */     
/* 115 */     values.put("darkturquoise", SVGValueConstants.DARKTURQUOISE_VALUE);
/*     */     
/* 117 */     values.put("darkviolet", SVGValueConstants.DARKVIOLET_VALUE);
/*     */     
/* 119 */     values.put("deeppink", SVGValueConstants.DEEPPINK_VALUE);
/*     */     
/* 121 */     values.put("deepskyblue", SVGValueConstants.DEEPSKYBLUE_VALUE);
/*     */     
/* 123 */     values.put("dimgray", SVGValueConstants.DIMGRAY_VALUE);
/*     */     
/* 125 */     values.put("dimgrey", SVGValueConstants.DIMGREY_VALUE);
/*     */     
/* 127 */     values.put("dodgerblue", SVGValueConstants.DODGERBLUE_VALUE);
/*     */     
/* 129 */     values.put("firebrick", SVGValueConstants.FIREBRICK_VALUE);
/*     */     
/* 131 */     values.put("floralwhite", SVGValueConstants.FLORALWHITE_VALUE);
/*     */     
/* 133 */     values.put("forestgreen", SVGValueConstants.FORESTGREEN_VALUE);
/*     */     
/* 135 */     values.put("gainsboro", SVGValueConstants.GAINSBORO_VALUE);
/*     */     
/* 137 */     values.put("ghostwhite", SVGValueConstants.GHOSTWHITE_VALUE);
/*     */     
/* 139 */     values.put("gold", SVGValueConstants.GOLD_VALUE);
/*     */     
/* 141 */     values.put("goldenrod", SVGValueConstants.GOLDENROD_VALUE);
/*     */     
/* 143 */     values.put("greenyellow", SVGValueConstants.GREENYELLOW_VALUE);
/*     */     
/* 145 */     values.put("grey", SVGValueConstants.GREY_VALUE);
/*     */     
/* 147 */     values.put("honeydew", SVGValueConstants.HONEYDEW_VALUE);
/*     */     
/* 149 */     values.put("hotpink", SVGValueConstants.HOTPINK_VALUE);
/*     */     
/* 151 */     values.put("indianred", SVGValueConstants.INDIANRED_VALUE);
/*     */     
/* 153 */     values.put("indigo", SVGValueConstants.INDIGO_VALUE);
/*     */     
/* 155 */     values.put("ivory", SVGValueConstants.IVORY_VALUE);
/*     */     
/* 157 */     values.put("khaki", SVGValueConstants.KHAKI_VALUE);
/*     */     
/* 159 */     values.put("lavender", SVGValueConstants.LAVENDER_VALUE);
/*     */     
/* 161 */     values.put("lavenderblush", SVGValueConstants.LAVENDERBLUSH_VALUE);
/*     */     
/* 163 */     values.put("lawngreen", SVGValueConstants.LAWNGREEN_VALUE);
/*     */     
/* 165 */     values.put("lemonchiffon", SVGValueConstants.LEMONCHIFFON_VALUE);
/*     */     
/* 167 */     values.put("lightblue", SVGValueConstants.LIGHTBLUE_VALUE);
/*     */     
/* 169 */     values.put("lightcoral", SVGValueConstants.LIGHTCORAL_VALUE);
/*     */     
/* 171 */     values.put("lightcyan", SVGValueConstants.LIGHTCYAN_VALUE);
/*     */     
/* 173 */     values.put("lightgoldenrodyellow", SVGValueConstants.LIGHTGOLDENRODYELLOW_VALUE);
/*     */     
/* 175 */     values.put("lightgray", SVGValueConstants.LIGHTGRAY_VALUE);
/*     */     
/* 177 */     values.put("lightgreen", SVGValueConstants.LIGHTGREEN_VALUE);
/*     */     
/* 179 */     values.put("lightgrey", SVGValueConstants.LIGHTGREY_VALUE);
/*     */     
/* 181 */     values.put("lightpink", SVGValueConstants.LIGHTPINK_VALUE);
/*     */     
/* 183 */     values.put("lightsalmon", SVGValueConstants.LIGHTSALMON_VALUE);
/*     */     
/* 185 */     values.put("lightseagreen", SVGValueConstants.LIGHTSEAGREEN_VALUE);
/*     */     
/* 187 */     values.put("lightskyblue", SVGValueConstants.LIGHTSKYBLUE_VALUE);
/*     */     
/* 189 */     values.put("lightslategray", SVGValueConstants.LIGHTSLATEGRAY_VALUE);
/*     */     
/* 191 */     values.put("lightslategrey", SVGValueConstants.LIGHTSLATEGREY_VALUE);
/*     */     
/* 193 */     values.put("lightsteelblue", SVGValueConstants.LIGHTSTEELBLUE_VALUE);
/*     */     
/* 195 */     values.put("lightyellow", SVGValueConstants.LIGHTYELLOW_VALUE);
/*     */     
/* 197 */     values.put("limegreen", SVGValueConstants.LIMEGREEN_VALUE);
/*     */     
/* 199 */     values.put("linen", SVGValueConstants.LINEN_VALUE);
/*     */     
/* 201 */     values.put("magenta", SVGValueConstants.MAGENTA_VALUE);
/*     */     
/* 203 */     values.put("mediumaquamarine", SVGValueConstants.MEDIUMAQUAMARINE_VALUE);
/*     */     
/* 205 */     values.put("mediumblue", SVGValueConstants.MEDIUMBLUE_VALUE);
/*     */     
/* 207 */     values.put("mediumorchid", SVGValueConstants.MEDIUMORCHID_VALUE);
/*     */     
/* 209 */     values.put("mediumpurple", SVGValueConstants.MEDIUMPURPLE_VALUE);
/*     */     
/* 211 */     values.put("mediumseagreen", SVGValueConstants.MEDIUMSEAGREEN_VALUE);
/*     */     
/* 213 */     values.put("mediumslateblue", SVGValueConstants.MEDIUMSLATEBLUE_VALUE);
/*     */     
/* 215 */     values.put("mediumspringgreen", SVGValueConstants.MEDIUMSPRINGGREEN_VALUE);
/*     */     
/* 217 */     values.put("mediumturquoise", SVGValueConstants.MEDIUMTURQUOISE_VALUE);
/*     */     
/* 219 */     values.put("mediumvioletred", SVGValueConstants.MEDIUMVIOLETRED_VALUE);
/*     */     
/* 221 */     values.put("midnightblue", SVGValueConstants.MIDNIGHTBLUE_VALUE);
/*     */     
/* 223 */     values.put("mintcream", SVGValueConstants.MINTCREAM_VALUE);
/*     */     
/* 225 */     values.put("mistyrose", SVGValueConstants.MISTYROSE_VALUE);
/*     */     
/* 227 */     values.put("moccasin", SVGValueConstants.MOCCASIN_VALUE);
/*     */     
/* 229 */     values.put("navajowhite", SVGValueConstants.NAVAJOWHITE_VALUE);
/*     */     
/* 231 */     values.put("oldlace", SVGValueConstants.OLDLACE_VALUE);
/*     */     
/* 233 */     values.put("olivedrab", SVGValueConstants.OLIVEDRAB_VALUE);
/*     */     
/* 235 */     values.put("orange", SVGValueConstants.ORANGE_VALUE);
/*     */     
/* 237 */     values.put("orangered", SVGValueConstants.ORANGERED_VALUE);
/*     */     
/* 239 */     values.put("orchid", SVGValueConstants.ORCHID_VALUE);
/*     */     
/* 241 */     values.put("palegoldenrod", SVGValueConstants.PALEGOLDENROD_VALUE);
/*     */     
/* 243 */     values.put("palegreen", SVGValueConstants.PALEGREEN_VALUE);
/*     */     
/* 245 */     values.put("paleturquoise", SVGValueConstants.PALETURQUOISE_VALUE);
/*     */     
/* 247 */     values.put("palevioletred", SVGValueConstants.PALEVIOLETRED_VALUE);
/*     */     
/* 249 */     values.put("papayawhip", SVGValueConstants.PAPAYAWHIP_VALUE);
/*     */     
/* 251 */     values.put("peachpuff", SVGValueConstants.PEACHPUFF_VALUE);
/*     */     
/* 253 */     values.put("peru", SVGValueConstants.PERU_VALUE);
/*     */     
/* 255 */     values.put("pink", SVGValueConstants.PINK_VALUE);
/*     */     
/* 257 */     values.put("plum", SVGValueConstants.PLUM_VALUE);
/*     */     
/* 259 */     values.put("powderblue", SVGValueConstants.POWDERBLUE_VALUE);
/*     */     
/* 261 */     values.put("purple", SVGValueConstants.PURPLE_VALUE);
/*     */     
/* 263 */     values.put("rosybrown", SVGValueConstants.ROSYBROWN_VALUE);
/*     */     
/* 265 */     values.put("royalblue", SVGValueConstants.ROYALBLUE_VALUE);
/*     */     
/* 267 */     values.put("saddlebrown", SVGValueConstants.SADDLEBROWN_VALUE);
/*     */     
/* 269 */     values.put("salmon", SVGValueConstants.SALMON_VALUE);
/*     */     
/* 271 */     values.put("sandybrown", SVGValueConstants.SANDYBROWN_VALUE);
/*     */     
/* 273 */     values.put("seagreen", SVGValueConstants.SEAGREEN_VALUE);
/*     */     
/* 275 */     values.put("seashell", SVGValueConstants.SEASHELL_VALUE);
/*     */     
/* 277 */     values.put("sienna", SVGValueConstants.SIENNA_VALUE);
/*     */     
/* 279 */     values.put("skyblue", SVGValueConstants.SKYBLUE_VALUE);
/*     */     
/* 281 */     values.put("slateblue", SVGValueConstants.SLATEBLUE_VALUE);
/*     */     
/* 283 */     values.put("slategray", SVGValueConstants.SLATEGRAY_VALUE);
/*     */     
/* 285 */     values.put("slategrey", SVGValueConstants.SLATEGREY_VALUE);
/*     */     
/* 287 */     values.put("snow", SVGValueConstants.SNOW_VALUE);
/*     */     
/* 289 */     values.put("springgreen", SVGValueConstants.SPRINGGREEN_VALUE);
/*     */     
/* 291 */     values.put("steelblue", SVGValueConstants.STEELBLUE_VALUE);
/*     */     
/* 293 */     values.put("tan", SVGValueConstants.TAN_VALUE);
/*     */     
/* 295 */     values.put("thistle", SVGValueConstants.THISTLE_VALUE);
/*     */     
/* 297 */     values.put("tomato", SVGValueConstants.TOMATO_VALUE);
/*     */     
/* 299 */     values.put("turquoise", SVGValueConstants.TURQUOISE_VALUE);
/*     */     
/* 301 */     values.put("violet", SVGValueConstants.VIOLET_VALUE);
/*     */     
/* 303 */     values.put("wheat", SVGValueConstants.WHEAT_VALUE);
/*     */     
/* 305 */     values.put("whitesmoke", SVGValueConstants.WHITESMOKE_VALUE);
/*     */     
/* 307 */     values.put("yellowgreen", SVGValueConstants.YELLOWGREEN_VALUE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     computedValues.put("black", SVGValueConstants.BLACK_RGB_VALUE);
/*     */     
/* 317 */     computedValues.put("silver", SVGValueConstants.SILVER_RGB_VALUE);
/*     */     
/* 319 */     computedValues.put("gray", SVGValueConstants.GRAY_RGB_VALUE);
/*     */     
/* 321 */     computedValues.put("white", SVGValueConstants.WHITE_RGB_VALUE);
/*     */     
/* 323 */     computedValues.put("maroon", SVGValueConstants.MAROON_RGB_VALUE);
/*     */     
/* 325 */     computedValues.put("red", SVGValueConstants.RED_RGB_VALUE);
/*     */     
/* 327 */     computedValues.put("purple", SVGValueConstants.PURPLE_RGB_VALUE);
/*     */     
/* 329 */     computedValues.put("fuchsia", SVGValueConstants.FUCHSIA_RGB_VALUE);
/*     */     
/* 331 */     computedValues.put("green", SVGValueConstants.GREEN_RGB_VALUE);
/*     */     
/* 333 */     computedValues.put("lime", SVGValueConstants.LIME_RGB_VALUE);
/*     */     
/* 335 */     computedValues.put("olive", SVGValueConstants.OLIVE_RGB_VALUE);
/*     */     
/* 337 */     computedValues.put("yellow", SVGValueConstants.YELLOW_RGB_VALUE);
/*     */     
/* 339 */     computedValues.put("navy", SVGValueConstants.NAVY_RGB_VALUE);
/*     */     
/* 341 */     computedValues.put("blue", SVGValueConstants.BLUE_RGB_VALUE);
/*     */     
/* 343 */     computedValues.put("teal", SVGValueConstants.TEAL_RGB_VALUE);
/*     */     
/* 345 */     computedValues.put("aqua", SVGValueConstants.AQUA_RGB_VALUE);
/*     */ 
/*     */     
/* 348 */     computedValues.put("aliceblue", SVGValueConstants.ALICEBLUE_RGB_VALUE);
/*     */     
/* 350 */     computedValues.put("antiquewhite", SVGValueConstants.ANTIQUEWHITE_RGB_VALUE);
/*     */     
/* 352 */     computedValues.put("aquamarine", SVGValueConstants.AQUAMARINE_RGB_VALUE);
/*     */     
/* 354 */     computedValues.put("azure", SVGValueConstants.AZURE_RGB_VALUE);
/*     */     
/* 356 */     computedValues.put("beige", SVGValueConstants.BEIGE_RGB_VALUE);
/*     */     
/* 358 */     computedValues.put("bisque", SVGValueConstants.BISQUE_RGB_VALUE);
/*     */     
/* 360 */     computedValues.put("blanchedalmond", SVGValueConstants.BLANCHEDALMOND_RGB_VALUE);
/*     */     
/* 362 */     computedValues.put("blueviolet", SVGValueConstants.BLUEVIOLET_RGB_VALUE);
/*     */     
/* 364 */     computedValues.put("brown", SVGValueConstants.BROWN_RGB_VALUE);
/*     */     
/* 366 */     computedValues.put("burlywood", SVGValueConstants.BURLYWOOD_RGB_VALUE);
/*     */     
/* 368 */     computedValues.put("cadetblue", SVGValueConstants.CADETBLUE_RGB_VALUE);
/*     */     
/* 370 */     computedValues.put("chartreuse", SVGValueConstants.CHARTREUSE_RGB_VALUE);
/*     */     
/* 372 */     computedValues.put("chocolate", SVGValueConstants.CHOCOLATE_RGB_VALUE);
/*     */     
/* 374 */     computedValues.put("coral", SVGValueConstants.CORAL_RGB_VALUE);
/*     */     
/* 376 */     computedValues.put("cornflowerblue", SVGValueConstants.CORNFLOWERBLUE_RGB_VALUE);
/*     */     
/* 378 */     computedValues.put("cornsilk", SVGValueConstants.CORNSILK_RGB_VALUE);
/*     */     
/* 380 */     computedValues.put("crimson", SVGValueConstants.CRIMSON_RGB_VALUE);
/*     */     
/* 382 */     computedValues.put("cyan", SVGValueConstants.CYAN_RGB_VALUE);
/*     */     
/* 384 */     computedValues.put("darkblue", SVGValueConstants.DARKBLUE_RGB_VALUE);
/*     */     
/* 386 */     computedValues.put("darkcyan", SVGValueConstants.DARKCYAN_RGB_VALUE);
/*     */     
/* 388 */     computedValues.put("darkgoldenrod", SVGValueConstants.DARKGOLDENROD_RGB_VALUE);
/*     */     
/* 390 */     computedValues.put("darkgray", SVGValueConstants.DARKGRAY_RGB_VALUE);
/*     */     
/* 392 */     computedValues.put("darkgreen", SVGValueConstants.DARKGREEN_RGB_VALUE);
/*     */     
/* 394 */     computedValues.put("darkgrey", SVGValueConstants.DARKGREY_RGB_VALUE);
/*     */     
/* 396 */     computedValues.put("darkkhaki", SVGValueConstants.DARKKHAKI_RGB_VALUE);
/*     */     
/* 398 */     computedValues.put("darkmagenta", SVGValueConstants.DARKMAGENTA_RGB_VALUE);
/*     */     
/* 400 */     computedValues.put("darkolivegreen", SVGValueConstants.DARKOLIVEGREEN_RGB_VALUE);
/*     */     
/* 402 */     computedValues.put("darkorange", SVGValueConstants.DARKORANGE_RGB_VALUE);
/*     */     
/* 404 */     computedValues.put("darkorchid", SVGValueConstants.DARKORCHID_RGB_VALUE);
/*     */     
/* 406 */     computedValues.put("darkred", SVGValueConstants.DARKRED_RGB_VALUE);
/*     */     
/* 408 */     computedValues.put("darksalmon", SVGValueConstants.DARKSALMON_RGB_VALUE);
/*     */     
/* 410 */     computedValues.put("darkseagreen", SVGValueConstants.DARKSEAGREEN_RGB_VALUE);
/*     */     
/* 412 */     computedValues.put("darkslateblue", SVGValueConstants.DARKSLATEBLUE_RGB_VALUE);
/*     */     
/* 414 */     computedValues.put("darkslategray", SVGValueConstants.DARKSLATEGRAY_RGB_VALUE);
/*     */     
/* 416 */     computedValues.put("darkslategrey", SVGValueConstants.DARKSLATEGREY_RGB_VALUE);
/*     */     
/* 418 */     computedValues.put("darkturquoise", SVGValueConstants.DARKTURQUOISE_RGB_VALUE);
/*     */     
/* 420 */     computedValues.put("darkviolet", SVGValueConstants.DARKVIOLET_RGB_VALUE);
/*     */     
/* 422 */     computedValues.put("deeppink", SVGValueConstants.DEEPPINK_RGB_VALUE);
/*     */     
/* 424 */     computedValues.put("deepskyblue", SVGValueConstants.DEEPSKYBLUE_RGB_VALUE);
/*     */     
/* 426 */     computedValues.put("dimgray", SVGValueConstants.DIMGRAY_RGB_VALUE);
/*     */     
/* 428 */     computedValues.put("dimgrey", SVGValueConstants.DIMGREY_RGB_VALUE);
/*     */     
/* 430 */     computedValues.put("dodgerblue", SVGValueConstants.DODGERBLUE_RGB_VALUE);
/*     */     
/* 432 */     computedValues.put("firebrick", SVGValueConstants.FIREBRICK_RGB_VALUE);
/*     */     
/* 434 */     computedValues.put("floralwhite", SVGValueConstants.FLORALWHITE_RGB_VALUE);
/*     */     
/* 436 */     computedValues.put("forestgreen", SVGValueConstants.FORESTGREEN_RGB_VALUE);
/*     */     
/* 438 */     computedValues.put("gainsboro", SVGValueConstants.GAINSBORO_RGB_VALUE);
/*     */     
/* 440 */     computedValues.put("ghostwhite", SVGValueConstants.GHOSTWHITE_RGB_VALUE);
/*     */     
/* 442 */     computedValues.put("gold", SVGValueConstants.GOLD_RGB_VALUE);
/*     */     
/* 444 */     computedValues.put("goldenrod", SVGValueConstants.GOLDENROD_RGB_VALUE);
/*     */     
/* 446 */     computedValues.put("grey", SVGValueConstants.GREY_RGB_VALUE);
/*     */     
/* 448 */     computedValues.put("greenyellow", SVGValueConstants.GREENYELLOW_RGB_VALUE);
/*     */     
/* 450 */     computedValues.put("honeydew", SVGValueConstants.HONEYDEW_RGB_VALUE);
/*     */     
/* 452 */     computedValues.put("hotpink", SVGValueConstants.HOTPINK_RGB_VALUE);
/*     */     
/* 454 */     computedValues.put("indianred", SVGValueConstants.INDIANRED_RGB_VALUE);
/*     */     
/* 456 */     computedValues.put("indigo", SVGValueConstants.INDIGO_RGB_VALUE);
/*     */     
/* 458 */     computedValues.put("ivory", SVGValueConstants.IVORY_RGB_VALUE);
/*     */     
/* 460 */     computedValues.put("khaki", SVGValueConstants.KHAKI_RGB_VALUE);
/*     */     
/* 462 */     computedValues.put("lavender", SVGValueConstants.LAVENDER_RGB_VALUE);
/*     */     
/* 464 */     computedValues.put("lavenderblush", SVGValueConstants.LAVENDERBLUSH_RGB_VALUE);
/*     */     
/* 466 */     computedValues.put("lawngreen", SVGValueConstants.LAWNGREEN_RGB_VALUE);
/*     */     
/* 468 */     computedValues.put("lemonchiffon", SVGValueConstants.LEMONCHIFFON_RGB_VALUE);
/*     */     
/* 470 */     computedValues.put("lightblue", SVGValueConstants.LIGHTBLUE_RGB_VALUE);
/*     */     
/* 472 */     computedValues.put("lightcoral", SVGValueConstants.LIGHTCORAL_RGB_VALUE);
/*     */     
/* 474 */     computedValues.put("lightcyan", SVGValueConstants.LIGHTCYAN_RGB_VALUE);
/*     */     
/* 476 */     computedValues.put("lightgoldenrodyellow", SVGValueConstants.LIGHTGOLDENRODYELLOW_RGB_VALUE);
/*     */     
/* 478 */     computedValues.put("lightgray", SVGValueConstants.LIGHTGRAY_RGB_VALUE);
/*     */     
/* 480 */     computedValues.put("lightgreen", SVGValueConstants.LIGHTGREEN_RGB_VALUE);
/*     */     
/* 482 */     computedValues.put("lightgrey", SVGValueConstants.LIGHTGREY_RGB_VALUE);
/*     */     
/* 484 */     computedValues.put("lightpink", SVGValueConstants.LIGHTPINK_RGB_VALUE);
/*     */     
/* 486 */     computedValues.put("lightsalmon", SVGValueConstants.LIGHTSALMON_RGB_VALUE);
/*     */     
/* 488 */     computedValues.put("lightseagreen", SVGValueConstants.LIGHTSEAGREEN_RGB_VALUE);
/*     */     
/* 490 */     computedValues.put("lightskyblue", SVGValueConstants.LIGHTSKYBLUE_RGB_VALUE);
/*     */     
/* 492 */     computedValues.put("lightslategray", SVGValueConstants.LIGHTSLATEGRAY_RGB_VALUE);
/*     */     
/* 494 */     computedValues.put("lightslategrey", SVGValueConstants.LIGHTSLATEGREY_RGB_VALUE);
/*     */     
/* 496 */     computedValues.put("lightsteelblue", SVGValueConstants.LIGHTSTEELBLUE_RGB_VALUE);
/*     */     
/* 498 */     computedValues.put("lightyellow", SVGValueConstants.LIGHTYELLOW_RGB_VALUE);
/*     */     
/* 500 */     computedValues.put("limegreen", SVGValueConstants.LIMEGREEN_RGB_VALUE);
/*     */     
/* 502 */     computedValues.put("linen", SVGValueConstants.LINEN_RGB_VALUE);
/*     */     
/* 504 */     computedValues.put("magenta", SVGValueConstants.MAGENTA_RGB_VALUE);
/*     */     
/* 506 */     computedValues.put("mediumaquamarine", SVGValueConstants.MEDIUMAQUAMARINE_RGB_VALUE);
/*     */     
/* 508 */     computedValues.put("mediumblue", SVGValueConstants.MEDIUMBLUE_RGB_VALUE);
/*     */     
/* 510 */     computedValues.put("mediumorchid", SVGValueConstants.MEDIUMORCHID_RGB_VALUE);
/*     */     
/* 512 */     computedValues.put("mediumpurple", SVGValueConstants.MEDIUMPURPLE_RGB_VALUE);
/*     */     
/* 514 */     computedValues.put("mediumseagreen", SVGValueConstants.MEDIUMSEAGREEN_RGB_VALUE);
/*     */     
/* 516 */     computedValues.put("mediumslateblue", SVGValueConstants.MEDIUMSLATEBLUE_RGB_VALUE);
/*     */     
/* 518 */     computedValues.put("mediumspringgreen", SVGValueConstants.MEDIUMSPRINGGREEN_RGB_VALUE);
/*     */     
/* 520 */     computedValues.put("mediumturquoise", SVGValueConstants.MEDIUMTURQUOISE_RGB_VALUE);
/*     */     
/* 522 */     computedValues.put("mediumvioletred", SVGValueConstants.MEDIUMVIOLETRED_RGB_VALUE);
/*     */     
/* 524 */     computedValues.put("midnightblue", SVGValueConstants.MIDNIGHTBLUE_RGB_VALUE);
/*     */     
/* 526 */     computedValues.put("mintcream", SVGValueConstants.MINTCREAM_RGB_VALUE);
/*     */     
/* 528 */     computedValues.put("mistyrose", SVGValueConstants.MISTYROSE_RGB_VALUE);
/*     */     
/* 530 */     computedValues.put("moccasin", SVGValueConstants.MOCCASIN_RGB_VALUE);
/*     */     
/* 532 */     computedValues.put("navajowhite", SVGValueConstants.NAVAJOWHITE_RGB_VALUE);
/*     */     
/* 534 */     computedValues.put("oldlace", SVGValueConstants.OLDLACE_RGB_VALUE);
/*     */     
/* 536 */     computedValues.put("olivedrab", SVGValueConstants.OLIVEDRAB_RGB_VALUE);
/*     */     
/* 538 */     computedValues.put("orange", SVGValueConstants.ORANGE_RGB_VALUE);
/*     */     
/* 540 */     computedValues.put("orangered", SVGValueConstants.ORANGERED_RGB_VALUE);
/*     */     
/* 542 */     computedValues.put("orchid", SVGValueConstants.ORCHID_RGB_VALUE);
/*     */     
/* 544 */     computedValues.put("palegoldenrod", SVGValueConstants.PALEGOLDENROD_RGB_VALUE);
/*     */     
/* 546 */     computedValues.put("palegreen", SVGValueConstants.PALEGREEN_RGB_VALUE);
/*     */     
/* 548 */     computedValues.put("paleturquoise", SVGValueConstants.PALETURQUOISE_RGB_VALUE);
/*     */     
/* 550 */     computedValues.put("palevioletred", SVGValueConstants.PALEVIOLETRED_RGB_VALUE);
/*     */     
/* 552 */     computedValues.put("papayawhip", SVGValueConstants.PAPAYAWHIP_RGB_VALUE);
/*     */     
/* 554 */     computedValues.put("peachpuff", SVGValueConstants.PEACHPUFF_RGB_VALUE);
/*     */     
/* 556 */     computedValues.put("peru", SVGValueConstants.PERU_RGB_VALUE);
/*     */     
/* 558 */     computedValues.put("pink", SVGValueConstants.PINK_RGB_VALUE);
/*     */     
/* 560 */     computedValues.put("plum", SVGValueConstants.PLUM_RGB_VALUE);
/*     */     
/* 562 */     computedValues.put("powderblue", SVGValueConstants.POWDERBLUE_RGB_VALUE);
/*     */     
/* 564 */     computedValues.put("purple", SVGValueConstants.PURPLE_RGB_VALUE);
/*     */     
/* 566 */     computedValues.put("rosybrown", SVGValueConstants.ROSYBROWN_RGB_VALUE);
/*     */     
/* 568 */     computedValues.put("royalblue", SVGValueConstants.ROYALBLUE_RGB_VALUE);
/*     */     
/* 570 */     computedValues.put("saddlebrown", SVGValueConstants.SADDLEBROWN_RGB_VALUE);
/*     */     
/* 572 */     computedValues.put("salmon", SVGValueConstants.SALMON_RGB_VALUE);
/*     */     
/* 574 */     computedValues.put("sandybrown", SVGValueConstants.SANDYBROWN_RGB_VALUE);
/*     */     
/* 576 */     computedValues.put("seagreen", SVGValueConstants.SEAGREEN_RGB_VALUE);
/*     */     
/* 578 */     computedValues.put("seashell", SVGValueConstants.SEASHELL_RGB_VALUE);
/*     */     
/* 580 */     computedValues.put("sienna", SVGValueConstants.SIENNA_RGB_VALUE);
/*     */     
/* 582 */     computedValues.put("skyblue", SVGValueConstants.SKYBLUE_RGB_VALUE);
/*     */     
/* 584 */     computedValues.put("slateblue", SVGValueConstants.SLATEBLUE_RGB_VALUE);
/*     */     
/* 586 */     computedValues.put("slategray", SVGValueConstants.SLATEGRAY_RGB_VALUE);
/*     */     
/* 588 */     computedValues.put("slategrey", SVGValueConstants.SLATEGREY_RGB_VALUE);
/*     */     
/* 590 */     computedValues.put("snow", SVGValueConstants.SNOW_RGB_VALUE);
/*     */     
/* 592 */     computedValues.put("springgreen", SVGValueConstants.SPRINGGREEN_RGB_VALUE);
/*     */     
/* 594 */     computedValues.put("steelblue", SVGValueConstants.STEELBLUE_RGB_VALUE);
/*     */     
/* 596 */     computedValues.put("tan", SVGValueConstants.TAN_RGB_VALUE);
/*     */     
/* 598 */     computedValues.put("thistle", SVGValueConstants.THISTLE_RGB_VALUE);
/*     */     
/* 600 */     computedValues.put("tomato", SVGValueConstants.TOMATO_RGB_VALUE);
/*     */     
/* 602 */     computedValues.put("turquoise", SVGValueConstants.TURQUOISE_RGB_VALUE);
/*     */     
/* 604 */     computedValues.put("violet", SVGValueConstants.VIOLET_RGB_VALUE);
/*     */     
/* 606 */     computedValues.put("wheat", SVGValueConstants.WHEAT_RGB_VALUE);
/*     */     
/* 608 */     computedValues.put("whitesmoke", SVGValueConstants.WHITESMOKE_RGB_VALUE);
/*     */     
/* 610 */     computedValues.put("yellowgreen", SVGValueConstants.YELLOWGREEN_RGB_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/* 620 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/* 627 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/* 634 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 641 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 649 */     return "color";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 657 */     return DEFAULT_VALUE;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/ColorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */