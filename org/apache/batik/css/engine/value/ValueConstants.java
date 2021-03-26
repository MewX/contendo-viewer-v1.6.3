/*      */ package org.apache.batik.css.engine.value;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface ValueConstants
/*      */ {
/*   35 */   public static final Value NUMBER_0 = new FloatValue((short)1, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   40 */   public static final Value NUMBER_100 = new FloatValue((short)1, 100.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   45 */   public static final Value NUMBER_128 = new FloatValue((short)1, 128.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   50 */   public static final Value NUMBER_192 = new FloatValue((short)1, 192.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   55 */   public static final Value NUMBER_200 = new FloatValue((short)1, 200.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   60 */   public static final Value NUMBER_255 = new FloatValue((short)1, 255.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   public static final Value NUMBER_300 = new FloatValue((short)1, 300.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   70 */   public static final Value NUMBER_400 = new FloatValue((short)1, 400.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   75 */   public static final Value NUMBER_500 = new FloatValue((short)1, 500.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   public static final Value NUMBER_600 = new FloatValue((short)1, 600.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   85 */   public static final Value NUMBER_700 = new FloatValue((short)1, 700.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   public static final Value NUMBER_800 = new FloatValue((short)1, 800.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   public static final Value NUMBER_900 = new FloatValue((short)1, 900.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   public static final Value INHERIT_VALUE = InheritValue.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   public static final Value ALL_VALUE = new StringValue((short)21, "all");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   public static final Value AUTO_VALUE = new StringValue((short)21, "auto");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   public static final Value BIDI_OVERRIDE_VALUE = new StringValue((short)21, "bidi-override");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  126 */   public static final Value BLINK_VALUE = new StringValue((short)21, "blink");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   public static final Value BLOCK_VALUE = new StringValue((short)21, "block");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   public static final Value BOLD_VALUE = new StringValue((short)21, "bold");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   public static final Value BOLDER_VALUE = new StringValue((short)21, "bolder");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  154 */   public static final Value BOTTOM_VALUE = new StringValue((short)21, "bottom");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   public static final Value COLLAPSE_VALUE = new StringValue((short)21, "collapse");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  168 */   public static final Value COMPACT_VALUE = new StringValue((short)21, "compact");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  175 */   public static final Value CONDENSED_VALUE = new StringValue((short)21, "condensed");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  182 */   public static final Value CRISPEDGES_VALUE = new StringValue((short)21, "crispedges");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  189 */   public static final Value CROSSHAIR_VALUE = new StringValue((short)21, "crosshair");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   public static final Value CURSIVE_VALUE = new StringValue((short)21, "cursive");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  203 */   public static final Value DEFAULT_VALUE = new StringValue((short)21, "default");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  210 */   public static final Value E_RESIZE_VALUE = new StringValue((short)21, "e-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  217 */   public static final Value EMBED_VALUE = new StringValue((short)21, "embed");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  224 */   public static final Value EXPANDED_VALUE = new StringValue((short)21, "expanded");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  231 */   public static final Value EXTRA_CONDENSED_VALUE = new StringValue((short)21, "extra-condensed");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  238 */   public static final Value EXTRA_EXPANDED_VALUE = new StringValue((short)21, "extra-expanded");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  245 */   public static final Value FANTASY_VALUE = new StringValue((short)21, "fantasy");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  252 */   public static final Value HELP_VALUE = new StringValue((short)21, "help");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  259 */   public static final Value HIDDEN_VALUE = new StringValue((short)21, "hidden");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  266 */   public static final Value INLINE_VALUE = new StringValue((short)21, "inline");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  273 */   public static final Value INLINE_TABLE_VALUE = new StringValue((short)21, "inline-table");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  280 */   public static final Value ITALIC_VALUE = new StringValue((short)21, "italic");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   public static final Value LARGE_VALUE = new StringValue((short)21, "large");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  294 */   public static final Value LARGER_VALUE = new StringValue((short)21, "larger");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  301 */   public static final Value LIGHTER_VALUE = new StringValue((short)21, "lighter");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  308 */   public static final Value LINE_THROUGH_VALUE = new StringValue((short)21, "line-through");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  315 */   public static final Value LIST_ITEM_VALUE = new StringValue((short)21, "list-item");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  322 */   public static final Value LTR_VALUE = new StringValue((short)21, "ltr");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   public static final Value MARKER_VALUE = new StringValue((short)21, "marker");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  336 */   public static final Value MEDIUM_VALUE = new StringValue((short)21, "medium");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  343 */   public static final Value MONOSPACE_VALUE = new StringValue((short)21, "monospace");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  350 */   public static final Value MOVE_VALUE = new StringValue((short)21, "move");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  357 */   public static final Value N_RESIZE_VALUE = new StringValue((short)21, "n-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  364 */   public static final Value NARROWER_VALUE = new StringValue((short)21, "narrower");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  371 */   public static final Value NE_RESIZE_VALUE = new StringValue((short)21, "ne-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  378 */   public static final Value NW_RESIZE_VALUE = new StringValue((short)21, "nw-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  385 */   public static final Value NONE_VALUE = new StringValue((short)21, "none");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public static final Value NORMAL_VALUE = new StringValue((short)21, "normal");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  399 */   public static final Value OBLIQUE_VALUE = new StringValue((short)21, "oblique");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  406 */   public static final Value OVERLINE_VALUE = new StringValue((short)21, "overline");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  413 */   public static final Value POINTER_VALUE = new StringValue((short)21, "pointer");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  420 */   public static final Value PAINTED_VALUE = new StringValue((short)21, "painted");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  427 */   public static final Value RTL_VALUE = new StringValue((short)21, "rtl");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  434 */   public static final Value RUN_IN_VALUE = new StringValue((short)21, "run-in");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  441 */   public static final Value S_RESIZE_VALUE = new StringValue((short)21, "s-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  448 */   public static final Value SANS_SERIF_VALUE = new StringValue((short)21, "sans-serif");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  455 */   public static final Value SCROLL_VALUE = new StringValue((short)21, "scroll");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  462 */   public static final Value SE_RESIZE_VALUE = new StringValue((short)21, "se-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  469 */   public static final Value SEMI_CONDENSED_VALUE = new StringValue((short)21, "semi-condensed");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  476 */   public static final Value SEMI_EXPANDED_VALUE = new StringValue((short)21, "semi-expanded");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  483 */   public static final Value SERIF_VALUE = new StringValue((short)21, "serif");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  490 */   public static final Value SMALL_VALUE = new StringValue((short)21, "small");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  497 */   public static final Value SMALL_CAPS_VALUE = new StringValue((short)21, "small-caps");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  504 */   public static final Value SMALLER_VALUE = new StringValue((short)21, "smaller");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  511 */   public static final Value STROKE_VALUE = new StringValue((short)21, "stroke");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  518 */   public static final Value SW_RESIZE_VALUE = new StringValue((short)21, "sw-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   public static final Value TABLE_VALUE = new StringValue((short)21, "table");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  532 */   public static final Value TABLE_CAPTION_VALUE = new StringValue((short)21, "table-caption");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  539 */   public static final Value TABLE_CELL_VALUE = new StringValue((short)21, "table-cell");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  546 */   public static final Value TABLE_COLUMN_VALUE = new StringValue((short)21, "table-column");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  553 */   public static final Value TABLE_COLUMN_GROUP_VALUE = new StringValue((short)21, "table-column-group");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  560 */   public static final Value TABLE_FOOTER_GROUP_VALUE = new StringValue((short)21, "table-footer-group");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  567 */   public static final Value TABLE_HEADER_GROUP_VALUE = new StringValue((short)21, "table-header-group");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  574 */   public static final Value TABLE_ROW_VALUE = new StringValue((short)21, "table-row");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  581 */   public static final Value TABLE_ROW_GROUP_VALUE = new StringValue((short)21, "table-row-group");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  588 */   public static final Value TEXT_VALUE = new StringValue((short)21, "text");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  595 */   public static final Value ULTRA_CONDENSED_VALUE = new StringValue((short)21, "ultra-condensed");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  602 */   public static final Value ULTRA_EXPANDED_VALUE = new StringValue((short)21, "ultra-expanded");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  609 */   public static final Value TOP_VALUE = new StringValue((short)21, "top");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  616 */   public static final Value UNDERLINE_VALUE = new StringValue((short)21, "underline");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  623 */   public static final Value VISIBLE_VALUE = new StringValue((short)21, "visible");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  630 */   public static final Value W_RESIZE_VALUE = new StringValue((short)21, "w-resize");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  637 */   public static final Value WAIT_VALUE = new StringValue((short)21, "wait");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  644 */   public static final Value WIDER_VALUE = new StringValue((short)21, "wider");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  651 */   public static final Value X_LARGE_VALUE = new StringValue((short)21, "x-large");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  658 */   public static final Value X_SMALL_VALUE = new StringValue((short)21, "x-small");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  665 */   public static final Value XX_LARGE_VALUE = new StringValue((short)21, "xx-large");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  672 */   public static final Value XX_SMALL_VALUE = new StringValue((short)21, "xx-small");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  679 */   public static final Value AQUA_VALUE = new StringValue((short)21, "aqua");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  686 */   public static final Value BLACK_VALUE = new StringValue((short)21, "black");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  693 */   public static final Value BLUE_VALUE = new StringValue((short)21, "blue");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  700 */   public static final Value FUCHSIA_VALUE = new StringValue((short)21, "fuchsia");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  707 */   public static final Value GRAY_VALUE = new StringValue((short)21, "gray");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  714 */   public static final Value GREEN_VALUE = new StringValue((short)21, "green");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  721 */   public static final Value LIME_VALUE = new StringValue((short)21, "lime");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  728 */   public static final Value MAROON_VALUE = new StringValue((short)21, "maroon");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  735 */   public static final Value NAVY_VALUE = new StringValue((short)21, "navy");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  742 */   public static final Value OLIVE_VALUE = new StringValue((short)21, "olive");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  749 */   public static final Value PURPLE_VALUE = new StringValue((short)21, "purple");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  756 */   public static final Value RED_VALUE = new StringValue((short)21, "red");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  763 */   public static final Value SILVER_VALUE = new StringValue((short)21, "silver");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   public static final Value TEAL_VALUE = new StringValue((short)21, "teal");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  777 */   public static final Value WHITE_VALUE = new StringValue((short)21, "white");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  784 */   public static final Value YELLOW_VALUE = new StringValue((short)21, "yellow");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  791 */   public static final Value ACTIVEBORDER_VALUE = new StringValue((short)21, "activeborder");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  798 */   public static final Value ACTIVECAPTION_VALUE = new StringValue((short)21, "activecaption");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   public static final Value APPWORKSPACE_VALUE = new StringValue((short)21, "appworkspace");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  812 */   public static final Value BACKGROUND_VALUE = new StringValue((short)21, "background");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  819 */   public static final Value BUTTONFACE_VALUE = new StringValue((short)21, "buttonface");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  826 */   public static final Value BUTTONHIGHLIGHT_VALUE = new StringValue((short)21, "buttonhighlight");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  833 */   public static final Value BUTTONSHADOW_VALUE = new StringValue((short)21, "buttonshadow");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  840 */   public static final Value BUTTONTEXT_VALUE = new StringValue((short)21, "buttontext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  847 */   public static final Value CAPTIONTEXT_VALUE = new StringValue((short)21, "captiontext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  854 */   public static final Value GRAYTEXT_VALUE = new StringValue((short)21, "graytext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  861 */   public static final Value HIGHLIGHT_VALUE = new StringValue((short)21, "highlight");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  868 */   public static final Value HIGHLIGHTTEXT_VALUE = new StringValue((short)21, "highlighttext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  875 */   public static final Value INACTIVEBORDER_VALUE = new StringValue((short)21, "inactiveborder");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  882 */   public static final Value INACTIVECAPTION_VALUE = new StringValue((short)21, "inactivecaption");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  889 */   public static final Value INACTIVECAPTIONTEXT_VALUE = new StringValue((short)21, "inactivecaptiontext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  896 */   public static final Value INFOBACKGROUND_VALUE = new StringValue((short)21, "infobackground");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  903 */   public static final Value INFOTEXT_VALUE = new StringValue((short)21, "infotext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  910 */   public static final Value MENU_VALUE = new StringValue((short)21, "menu");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  917 */   public static final Value MENUTEXT_VALUE = new StringValue((short)21, "menutext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  924 */   public static final Value SCROLLBAR_VALUE = new StringValue((short)21, "scrollbar");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  931 */   public static final Value THREEDDARKSHADOW_VALUE = new StringValue((short)21, "threeddarkshadow");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  938 */   public static final Value THREEDFACE_VALUE = new StringValue((short)21, "threedface");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  945 */   public static final Value THREEDHIGHLIGHT_VALUE = new StringValue((short)21, "threedhighlight");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public static final Value THREEDLIGHTSHADOW_VALUE = new StringValue((short)21, "threedlightshadow");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public static final Value THREEDSHADOW_VALUE = new StringValue((short)21, "threedshadow");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  966 */   public static final Value WINDOW_VALUE = new StringValue((short)21, "window");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  973 */   public static final Value WINDOWFRAME_VALUE = new StringValue((short)21, "windowframe");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  980 */   public static final Value WINDOWTEXT_VALUE = new StringValue((short)21, "windowtext");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public static final Value BLACK_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_0, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  994 */   public static final Value SILVER_RGB_VALUE = new RGBColorValue(NUMBER_192, NUMBER_192, NUMBER_192);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1000 */   public static final Value GRAY_RGB_VALUE = new RGBColorValue(NUMBER_128, NUMBER_128, NUMBER_128);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public static final Value WHITE_RGB_VALUE = new RGBColorValue(NUMBER_255, NUMBER_255, NUMBER_255);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1012 */   public static final Value MAROON_RGB_VALUE = new RGBColorValue(NUMBER_128, NUMBER_0, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1018 */   public static final Value RED_RGB_VALUE = new RGBColorValue(NUMBER_255, NUMBER_0, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1024 */   public static final Value PURPLE_RGB_VALUE = new RGBColorValue(NUMBER_128, NUMBER_0, NUMBER_128);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   public static final Value FUCHSIA_RGB_VALUE = new RGBColorValue(NUMBER_255, NUMBER_0, NUMBER_255);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1036 */   public static final Value GREEN_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_128, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   public static final Value LIME_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_255, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1048 */   public static final Value OLIVE_RGB_VALUE = new RGBColorValue(NUMBER_128, NUMBER_128, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1054 */   public static final Value YELLOW_RGB_VALUE = new RGBColorValue(NUMBER_255, NUMBER_255, NUMBER_0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1060 */   public static final Value NAVY_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_0, NUMBER_128);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1066 */   public static final Value BLUE_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_0, NUMBER_255);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1072 */   public static final Value TEAL_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_128, NUMBER_128);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   public static final Value AQUA_RGB_VALUE = new RGBColorValue(NUMBER_0, NUMBER_255, NUMBER_255);
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/ValueConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */