/*      */ package org.apache.xalan.xsltc.compiler;
/*      */ 
/*      */ import b.a.b;
/*      */ import b.a.c;
/*      */ import java.util.Stack;
/*      */ import java.util.Vector;
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
/*      */ class CUP$XPathParser$actions
/*      */ {
/*      */   private final XPathParser parser;
/*      */   
/*      */   CUP$XPathParser$actions(XPathParser parser) {
/* 1118 */     this.parser = parser; } public final b CUP$XPathParser$do_action(int CUP$XPathParser$act_num, c CUP$XPathParser$parser, Stack CUP$XPathParser$stack, int CUP$XPathParser$top) throws Exception { b CUP$XPathParser$result; QName qName2; Object object2; Expression expression3; QName qName1; Vector vector4; Vector vector3; Expression expression2; Integer integer2; Expression expression1; Vector vector2; Vector vector1; Integer integer1; Object object1; StepPattern stepPattern2; RelativePathPattern relativePathPattern1; StepPattern stepPattern1; IdKeyPattern idKeyPattern1; Pattern pattern1; SyntaxTreeNode syntaxTreeNode1; Object RESULT; int qnameleft; int i15; int i14; int i13; int i12; int vnameleft; int i11; int argleft; int fnameleft; int varNameleft; int fcleft; int numleft; int stringleft; int i10; int vrleft; int primaryleft; int anleft; int abbrevleft; int i9; int ntestleft; int i8; int aalpleft; int i7; int arlpleft; int i6; int stepleft; int alpleft; int rlpleft; int fexpleft; int lpleft; int peleft; int i5; int i4; int ueleft; int i3; int meleft; int i2; int i1; int eeleft; int releft; int n; int m; int oeleft; int aeleft; int exleft; int eleft; int pleft; int qnleft; int k; int axisleft; int pipleft; int ntleft; int spleft; int j; int l1left; int lleft; int i; int ikpleft; int rppleft; int lppleft; int exprleft; int patternleft; int start_valleft; int qnameright; int i35; int i34; int i33; int i32; int vnameright; int i31; int argright; int fnameright; int varNameright; int fcright; int numright; int stringright; int i30; int vrright; int primaryright; int anright; int abbrevright; int i29; int ntestright; int i28; int aalpright; int i27; int arlpright; int i26; int stepright; int alpright; int rlpright; int fexpright; int lpright; int peright; int i25; int i24; int ueright; int i23; int meright; int i22; int i21; int eeright; int reright; int i20; int i19; int oeright; int aeright; int exright; int eright; int pright; int qnright; int i18; int axisright; int pipright; int ntright; int spright; int i17; int l1right; int lright; int i16; int ikpright; int rppright; int lppright; int exprright; int patternright; int start_valright; String qname; QName qName4; String str2; Object object4; Expression expression15; QName vname; QName qName3; Expression arg; QName fname; QName varName; Expression fc; Double double_; Long num; String string; Expression expression14; Expression vr; Expression primary; Integer an; Expression abbrev; Integer integer3; Object ntest; Expression expression13; Expression aalp; Expression expression12; Expression arlp; Expression expression11; Expression step; Expression alp; Expression rlp; Expression fexp; Expression lp; Expression pe; Expression expression10; Expression expression9; Expression ue; Expression expression8; Expression me; Expression expression7; Expression expression6; Expression ee; Expression re; Expression expression5; Expression expression4; Expression oe; Expression ae; Expression ex; Expression e; Expression p; QName qn; Object object3; Integer axis; StepPattern pip; Object nt; StepPattern sp; String str1; String l1; String l; RelativePathPattern relativePathPattern2; IdKeyPattern ikp; RelativePathPattern rpp; Pattern lpp; Expression expr; Pattern pattern; SyntaxTreeNode start_val; QName name; int i53; Vector vector5; int arglleft; SyntaxTreeNode node; long value; String namespace; int i52; int i51; int i50; int nodeType; int i49; int i48; int restleft; int i47; int i46; int i45; int i44; int i43; int i42; int i41; Vector temp; int i40; int i39; int ppleft; int i38; int l2left; int i37; int i36; Expression exp; int arglright; int index; int i70; int i69; int i68; Step step1; int i67; int i66; int restright; int i65; int i64; int i63; int i62; int i61; int i60; int i59; int i58; int i57; int ppright; int i56; int l2right; int i55; int i54; Vector predicates; Vector vector9; Vector argl; Vector vector8; Object object6; Vector vector7; Expression expression23; Expression expression22; Expression rest; Expression expression21; Expression expression20; Expression expression19; Expression expression18; Expression expression17; Expression expression16; Vector vector6; StepPattern stepPattern3; Object object5; Vector pp; RelativePathPattern relativePathPattern4; String l2; RelativePathPattern relativePathPattern3;
/*      */     Pattern pattern2;
/*      */     int i73;
/*      */     Step right;
/*      */     int i72;
/*      */     int i71;
/*      */     int i76;
/*      */     int i75;
/*      */     Step step2;
/*      */     int i74;
/*      */     Vector vector11;
/*      */     int type;
/*      */     FilterParentPath fpp;
/*      */     Vector vector10;
/*      */     Vector vector12;
/* 1133 */     switch (CUP$XPathParser$act_num) {
/*      */ 
/*      */ 
/*      */       
/*      */       case 140:
/* 1138 */         qName2 = null;
/* 1139 */         qName2 = this.parser.getQNameIgnoreDefaultNs("id");
/* 1140 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1142 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 139:
/* 1147 */         qName2 = null;
/* 1148 */         qName2 = this.parser.getQNameIgnoreDefaultNs("self");
/* 1149 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1151 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 138:
/* 1156 */         qName2 = null;
/* 1157 */         qName2 = this.parser.getQNameIgnoreDefaultNs("preceding-sibling");
/* 1158 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1160 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 137:
/* 1165 */         qName2 = null;
/* 1166 */         qName2 = this.parser.getQNameIgnoreDefaultNs("preceding");
/* 1167 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1169 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 136:
/* 1174 */         qName2 = null;
/* 1175 */         qName2 = this.parser.getQNameIgnoreDefaultNs("parent");
/* 1176 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1178 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 135:
/* 1183 */         qName2 = null;
/* 1184 */         qName2 = this.parser.getQNameIgnoreDefaultNs("namespace");
/* 1185 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1187 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 134:
/* 1192 */         qName2 = null;
/* 1193 */         qName2 = this.parser.getQNameIgnoreDefaultNs("following-sibling");
/* 1194 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1196 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 133:
/* 1201 */         qName2 = null;
/* 1202 */         qName2 = this.parser.getQNameIgnoreDefaultNs("following");
/* 1203 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1205 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 132:
/* 1210 */         qName2 = null;
/* 1211 */         qName2 = this.parser.getQNameIgnoreDefaultNs("decendant-or-self");
/* 1212 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1214 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 131:
/* 1219 */         qName2 = null;
/* 1220 */         qName2 = this.parser.getQNameIgnoreDefaultNs("decendant");
/* 1221 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1223 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 130:
/* 1228 */         qName2 = null;
/* 1229 */         qName2 = this.parser.getQNameIgnoreDefaultNs("child");
/* 1230 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1232 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 129:
/* 1237 */         qName2 = null;
/* 1238 */         qName2 = this.parser.getQNameIgnoreDefaultNs("attribute");
/* 1239 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1241 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 128:
/* 1246 */         qName2 = null;
/* 1247 */         qName2 = this.parser.getQNameIgnoreDefaultNs("ancestor-or-self");
/* 1248 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1250 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 127:
/* 1255 */         qName2 = null;
/* 1256 */         qName2 = this.parser.getQNameIgnoreDefaultNs("child");
/* 1257 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1259 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 126:
/* 1264 */         qName2 = null;
/* 1265 */         qName2 = this.parser.getQNameIgnoreDefaultNs("key");
/* 1266 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1268 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 125:
/* 1273 */         qName2 = null;
/* 1274 */         qName2 = this.parser.getQNameIgnoreDefaultNs("mod");
/* 1275 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1277 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 124:
/* 1282 */         qName2 = null;
/* 1283 */         qName2 = this.parser.getQNameIgnoreDefaultNs("div");
/* 1284 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1286 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 123:
/* 1291 */         qName2 = null;
/* 1292 */         qnameleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1293 */         qnameright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1294 */         qname = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1295 */         qName2 = this.parser.getQNameIgnoreDefaultNs(qname);
/* 1296 */         CUP$XPathParser$result = new b(37, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName2);
/*      */         
/* 1298 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 122:
/* 1303 */         object2 = null;
/* 1304 */         i15 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1305 */         i35 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1306 */         qName4 = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1307 */         object2 = qName4;
/* 1308 */         CUP$XPathParser$result = new b(26, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1310 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 121:
/* 1315 */         object2 = null;
/* 1316 */         object2 = null;
/* 1317 */         CUP$XPathParser$result = new b(26, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1319 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 120:
/* 1324 */         object2 = null;
/* 1325 */         object2 = new Integer(7);
/* 1326 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1328 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 119:
/* 1333 */         object2 = null;
/* 1334 */         i14 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1335 */         i34 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1336 */         str2 = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/*      */         
/* 1338 */         name = this.parser.getQNameIgnoreDefaultNs("name");
/* 1339 */         exp = new EqualityExpr(0, new NameCall(name), new LiteralExpr(str2));
/*      */ 
/*      */         
/* 1342 */         predicates = new Vector();
/* 1343 */         predicates.addElement(new Predicate(exp));
/* 1344 */         object2 = new Step(3, 7, predicates);
/*      */         
/* 1346 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1348 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 118:
/* 1353 */         object2 = null;
/* 1354 */         object2 = new Integer(8);
/* 1355 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1357 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 117:
/* 1362 */         object2 = null;
/* 1363 */         object2 = new Integer(3);
/* 1364 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1366 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 116:
/* 1371 */         object2 = null;
/* 1372 */         object2 = new Integer(-1);
/* 1373 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1375 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 115:
/* 1380 */         object2 = null;
/* 1381 */         i13 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1382 */         i33 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1383 */         object4 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1384 */         object2 = object4;
/* 1385 */         CUP$XPathParser$result = new b(25, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object2);
/*      */         
/* 1387 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 114:
/* 1392 */         expression3 = null;
/* 1393 */         i12 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1394 */         i32 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1395 */         expression15 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1396 */         expression3 = expression15;
/* 1397 */         CUP$XPathParser$result = new b(3, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression3);
/*      */         
/* 1399 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 113:
/* 1404 */         qName1 = null;
/* 1405 */         vnameleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1406 */         vnameright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1407 */         vname = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 1409 */         qName1 = vname;
/*      */         
/* 1411 */         CUP$XPathParser$result = new b(39, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName1);
/*      */         
/* 1413 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 112:
/* 1418 */         qName1 = null;
/* 1419 */         i11 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1420 */         i31 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1421 */         qName3 = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 1423 */         qName1 = qName3;
/*      */         
/* 1425 */         CUP$XPathParser$result = new b(38, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, qName1);
/*      */         
/* 1427 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 111:
/* 1432 */         vector4 = null;
/* 1433 */         argleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 1434 */         argright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 1435 */         arg = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 1436 */         i53 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1437 */         arglright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1438 */         vector9 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1439 */         vector9.insertElementAt(arg, 0); vector4 = vector9;
/* 1440 */         CUP$XPathParser$result = new b(36, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, vector4);
/*      */         
/* 1442 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 110:
/* 1447 */         vector3 = null;
/* 1448 */         argleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1449 */         argright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1450 */         arg = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 1452 */         vector5 = new Vector();
/* 1453 */         vector5.addElement(arg);
/* 1454 */         vector3 = vector5;
/*      */         
/* 1456 */         CUP$XPathParser$result = new b(36, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, vector3);
/*      */         
/* 1458 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 109:
/* 1463 */         expression2 = null;
/* 1464 */         fnameleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d;
/* 1465 */         fnameright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).e;
/* 1466 */         fname = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).f;
/* 1467 */         arglleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1468 */         arglright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1469 */         argl = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/*      */         
/* 1471 */         if (fname == this.parser.getQNameIgnoreDefaultNs("concat")) {
/* 1472 */           expression2 = new ConcatCall(fname, argl);
/*      */         }
/* 1474 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("number")) {
/* 1475 */           expression2 = new NumberCall(fname, argl);
/*      */         }
/* 1477 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("document")) {
/* 1478 */           this.parser.setMultiDocument(true);
/* 1479 */           expression2 = new DocumentCall(fname, argl);
/*      */         }
/* 1481 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("string")) {
/* 1482 */           expression2 = new StringCall(fname, argl);
/*      */         }
/* 1484 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("boolean")) {
/* 1485 */           expression2 = new BooleanCall(fname, argl);
/*      */         }
/* 1487 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("name")) {
/* 1488 */           expression2 = new NameCall(fname, argl);
/*      */         }
/* 1490 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("generate-id")) {
/* 1491 */           expression2 = new GenerateIdCall(fname, argl);
/*      */         }
/* 1493 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("not")) {
/* 1494 */           expression2 = new NotCall(fname, argl);
/*      */         }
/* 1496 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("format-number")) {
/* 1497 */           expression2 = new FormatNumberCall(fname, argl);
/*      */         }
/* 1499 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("unparsed-entity-uri")) {
/* 1500 */           expression2 = new UnparsedEntityUriCall(fname, argl);
/*      */         }
/* 1502 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("key")) {
/* 1503 */           expression2 = new KeyCall(fname, argl);
/*      */         }
/* 1505 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("id")) {
/* 1506 */           expression2 = new KeyCall(fname, argl);
/* 1507 */           this.parser.setHasIdCall(true);
/*      */         }
/* 1509 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("ceiling")) {
/* 1510 */           expression2 = new CeilingCall(fname, argl);
/*      */         }
/* 1512 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("round")) {
/* 1513 */           expression2 = new RoundCall(fname, argl);
/*      */         }
/* 1515 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("floor")) {
/* 1516 */           expression2 = new FloorCall(fname, argl);
/*      */         }
/* 1518 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("contains")) {
/* 1519 */           expression2 = new ContainsCall(fname, argl);
/*      */         }
/* 1521 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("string-length")) {
/* 1522 */           expression2 = new StringLengthCall(fname, argl);
/*      */         }
/* 1524 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("starts-with")) {
/* 1525 */           expression2 = new StartsWithCall(fname, argl);
/*      */         }
/* 1527 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("function-available")) {
/* 1528 */           expression2 = new FunctionAvailableCall(fname, argl);
/*      */         }
/* 1530 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("element-available")) {
/* 1531 */           expression2 = new ElementAvailableCall(fname, argl);
/*      */         }
/* 1533 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("local-name")) {
/* 1534 */           expression2 = new LocalNameCall(fname, argl);
/*      */         }
/* 1536 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("lang")) {
/* 1537 */           expression2 = new LangCall(fname, argl);
/*      */         }
/* 1539 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("namespace-uri")) {
/* 1540 */           expression2 = new NamespaceUriCall(fname, argl);
/*      */         }
/* 1542 */         else if (fname == this.parser.getQName("http://xml.apache.org/xalan/xsltc", "xsltc", "cast")) {
/* 1543 */           expression2 = new CastCall(fname, argl);
/*      */         
/*      */         }
/* 1546 */         else if (fname.getLocalPart().equals("nodeset") || fname.getLocalPart().equals("node-set")) {
/* 1547 */           this.parser.setCallsNodeset(true);
/* 1548 */           expression2 = new FunctionCall(fname, argl);
/*      */         } else {
/*      */           
/* 1551 */           expression2 = new FunctionCall(fname, argl);
/*      */         } 
/*      */         
/* 1554 */         CUP$XPathParser$result = new b(16, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1556 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 108:
/* 1561 */         expression2 = null;
/* 1562 */         fnameleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 1563 */         fnameright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 1564 */         fname = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/*      */ 
/*      */         
/* 1567 */         if (fname == this.parser.getQNameIgnoreDefaultNs("current")) {
/* 1568 */           expression2 = new CurrentCall(fname);
/*      */         }
/* 1570 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("number")) {
/* 1571 */           this; expression2 = new NumberCall(fname, XPathParser.EmptyArgs);
/*      */         }
/* 1573 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("string")) {
/* 1574 */           this; expression2 = new StringCall(fname, XPathParser.EmptyArgs);
/*      */         }
/* 1576 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("concat")) {
/* 1577 */           this; expression2 = new ConcatCall(fname, XPathParser.EmptyArgs);
/*      */         }
/* 1579 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("true")) {
/* 1580 */           expression2 = new BooleanExpr(true);
/*      */         }
/* 1582 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("false")) {
/* 1583 */           expression2 = new BooleanExpr(false);
/*      */         }
/* 1585 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("name")) {
/* 1586 */           expression2 = new NameCall(fname);
/*      */         }
/* 1588 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("generate-id")) {
/* 1589 */           this; expression2 = new GenerateIdCall(fname, XPathParser.EmptyArgs);
/*      */         }
/* 1591 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("string-length")) {
/* 1592 */           this; expression2 = new StringLengthCall(fname, XPathParser.EmptyArgs);
/*      */         }
/* 1594 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("position")) {
/* 1595 */           expression2 = new PositionCall(fname);
/*      */         }
/* 1597 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("last")) {
/* 1598 */           expression2 = new LastCall(fname);
/*      */         }
/* 1600 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("local-name")) {
/* 1601 */           expression2 = new LocalNameCall(fname);
/*      */         }
/* 1603 */         else if (fname == this.parser.getQNameIgnoreDefaultNs("namespace-uri")) {
/* 1604 */           expression2 = new NamespaceUriCall(fname);
/*      */         } else {
/*      */           
/* 1607 */           this; expression2 = new FunctionCall(fname, XPathParser.EmptyArgs);
/*      */         } 
/*      */         
/* 1610 */         CUP$XPathParser$result = new b(16, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1612 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 107:
/* 1617 */         expression2 = null;
/* 1618 */         varNameleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1619 */         varNameright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1620 */         varName = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */ 
/*      */ 
/*      */         
/* 1624 */         node = this.parser.lookupName(varName);
/*      */         
/* 1626 */         if (node != null) {
/* 1627 */           if (node instanceof Variable) {
/* 1628 */             expression2 = new VariableRef((Variable)node);
/*      */           }
/* 1630 */           else if (node instanceof Param) {
/* 1631 */             expression2 = new ParameterRef((Param)node);
/*      */           } else {
/*      */             
/* 1634 */             expression2 = new UnresolvedRef(varName);
/*      */           } 
/*      */         }
/*      */         
/* 1638 */         if (node == null) {
/* 1639 */           expression2 = new UnresolvedRef(varName);
/*      */         }
/*      */         
/* 1642 */         CUP$XPathParser$result = new b(15, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1644 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 106:
/* 1649 */         expression2 = null;
/* 1650 */         fcleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1651 */         fcright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1652 */         fc = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1653 */         expression2 = fc;
/* 1654 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1656 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 105:
/* 1661 */         expression2 = null;
/* 1662 */         numleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1663 */         numright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1664 */         double_ = (Double)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1665 */         expression2 = new RealExpr(double_.doubleValue());
/* 1666 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1668 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 104:
/* 1673 */         expression2 = null;
/* 1674 */         numleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1675 */         numright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1676 */         num = (Long)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 1678 */         value = num.longValue();
/* 1679 */         if (value < -2147483648L || value > 2147483647L) {
/* 1680 */           expression2 = new RealExpr(value);
/*      */         
/*      */         }
/* 1683 */         else if (num.doubleValue() == 0.0D) {
/* 1684 */           expression2 = new RealExpr(num.doubleValue());
/* 1685 */         } else if (num.intValue() == 0) {
/* 1686 */           expression2 = new IntExpr(num.intValue());
/* 1687 */         } else if (num.doubleValue() == 0.0D) {
/* 1688 */           expression2 = new RealExpr(num.doubleValue());
/*      */         } else {
/* 1690 */           expression2 = new IntExpr(num.intValue());
/*      */         } 
/*      */         
/* 1693 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1695 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 103:
/* 1700 */         expression2 = null;
/* 1701 */         stringleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1702 */         stringright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1703 */         string = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1711 */         namespace = null;
/* 1712 */         index = string.lastIndexOf(':');
/*      */         
/* 1714 */         if (index > 0) {
/* 1715 */           String prefix = string.substring(0, index);
/* 1716 */           namespace = this.parser._symbolTable.lookupNamespace(prefix);
/*      */         } 
/* 1718 */         expression2 = (namespace == null) ? new LiteralExpr(string) : new LiteralExpr(string, namespace);
/*      */ 
/*      */         
/* 1721 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1723 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 102:
/* 1728 */         expression2 = null;
/* 1729 */         i10 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1730 */         i30 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1731 */         expression14 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1732 */         expression2 = expression14;
/* 1733 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1735 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 101:
/* 1740 */         expression2 = null;
/* 1741 */         vrleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1742 */         vrright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1743 */         vr = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1744 */         expression2 = vr;
/* 1745 */         CUP$XPathParser$result = new b(17, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1747 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 100:
/* 1752 */         expression2 = null;
/* 1753 */         primaryleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1754 */         primaryright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1755 */         primary = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1756 */         i52 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1757 */         i70 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1758 */         vector8 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1759 */         expression2 = new FilterExpr(primary, vector8);
/* 1760 */         CUP$XPathParser$result = new b(6, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1762 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 99:
/* 1767 */         expression2 = null;
/* 1768 */         primaryleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1769 */         primaryright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1770 */         primary = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1771 */         expression2 = primary;
/* 1772 */         CUP$XPathParser$result = new b(6, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1774 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 98:
/* 1779 */         expression2 = null;
/* 1780 */         expression2 = new Step(10, -1, null);
/* 1781 */         CUP$XPathParser$result = new b(20, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1783 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 97:
/* 1788 */         expression2 = null;
/* 1789 */         expression2 = new Step(13, -1, null);
/* 1790 */         CUP$XPathParser$result = new b(20, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression2);
/*      */         
/* 1792 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 96:
/* 1797 */         integer2 = null;
/* 1798 */         integer2 = new Integer(13);
/* 1799 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1801 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 95:
/* 1806 */         integer2 = null;
/* 1807 */         integer2 = new Integer(12);
/* 1808 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1810 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 94:
/* 1815 */         integer2 = null;
/* 1816 */         integer2 = new Integer(11);
/* 1817 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1819 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 93:
/* 1824 */         integer2 = null;
/* 1825 */         integer2 = new Integer(10);
/* 1826 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1828 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 92:
/* 1833 */         integer2 = null;
/* 1834 */         integer2 = new Integer(9);
/* 1835 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1837 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 91:
/* 1842 */         integer2 = null;
/* 1843 */         integer2 = new Integer(7);
/* 1844 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1846 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 90:
/* 1851 */         integer2 = null;
/* 1852 */         integer2 = new Integer(6);
/* 1853 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1855 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 89:
/* 1860 */         integer2 = null;
/* 1861 */         integer2 = new Integer(5);
/* 1862 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1864 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 88:
/* 1869 */         integer2 = null;
/* 1870 */         integer2 = new Integer(4);
/* 1871 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1873 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 87:
/* 1878 */         integer2 = null;
/* 1879 */         integer2 = new Integer(3);
/* 1880 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1882 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 86:
/* 1887 */         integer2 = null;
/* 1888 */         integer2 = new Integer(2);
/* 1889 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1891 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 85:
/* 1896 */         integer2 = null;
/* 1897 */         integer2 = new Integer(1);
/* 1898 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1900 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 84:
/* 1905 */         integer2 = null;
/* 1906 */         integer2 = new Integer(0);
/* 1907 */         CUP$XPathParser$result = new b(40, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1909 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 83:
/* 1914 */         integer2 = null;
/* 1915 */         integer2 = new Integer(2);
/* 1916 */         CUP$XPathParser$result = new b(41, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1918 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 82:
/* 1923 */         integer2 = null;
/* 1924 */         anleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1925 */         anright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1926 */         an = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1927 */         integer2 = an;
/* 1928 */         CUP$XPathParser$result = new b(41, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer2);
/*      */         
/* 1930 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 81:
/* 1935 */         expression1 = null;
/* 1936 */         abbrevleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1937 */         abbrevright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1938 */         abbrev = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1939 */         expression1 = abbrev;
/* 1940 */         CUP$XPathParser$result = new b(7, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 1942 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 80:
/* 1947 */         expression1 = null;
/* 1948 */         i9 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1949 */         i29 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1950 */         integer3 = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1951 */         i51 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1952 */         i69 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1953 */         object6 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1954 */         expression1 = new Step(integer3.intValue(), this.parser.findNodeType(integer3.intValue(), object6), null);
/*      */ 
/*      */ 
/*      */         
/* 1958 */         CUP$XPathParser$result = new b(7, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 1960 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 79:
/* 1965 */         expression1 = null;
/* 1966 */         i9 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 1967 */         i29 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 1968 */         integer3 = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 1969 */         i51 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1970 */         i69 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1971 */         object6 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1972 */         i73 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1973 */         i76 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1974 */         vector11 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 1975 */         expression1 = new Step(integer3.intValue(), this.parser.findNodeType(integer3.intValue(), object6), vector11);
/*      */ 
/*      */ 
/*      */         
/* 1979 */         CUP$XPathParser$result = new b(7, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 1981 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 78:
/* 1986 */         expression1 = null;
/* 1987 */         ntestleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 1988 */         ntestright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 1989 */         ntest = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 1990 */         i50 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 1991 */         i68 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 1992 */         vector7 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 1994 */         if (ntest instanceof Step) {
/* 1995 */           Step step3 = (Step)ntest;
/* 1996 */           step3.addPredicates(vector7);
/* 1997 */           expression1 = (Step)ntest;
/*      */         } else {
/*      */           
/* 2000 */           expression1 = new Step(3, this.parser.findNodeType(3, ntest), vector7);
/*      */         } 
/*      */ 
/*      */         
/* 2004 */         CUP$XPathParser$result = new b(7, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2006 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 77:
/* 2011 */         expression1 = null;
/* 2012 */         ntestleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2013 */         ntestright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2014 */         ntest = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2016 */         if (ntest instanceof Step) {
/* 2017 */           expression1 = (Step)ntest;
/*      */         } else {
/*      */           
/* 2020 */           expression1 = new Step(3, this.parser.findNodeType(3, ntest), null);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2025 */         CUP$XPathParser$result = new b(7, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2027 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 76:
/* 2032 */         expression1 = null;
/* 2033 */         i8 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2034 */         i28 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2035 */         expression13 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2041 */         nodeType = -1;
/* 2042 */         if (expression13 instanceof Step && this.parser.isElementAxis(((Step)expression13).getAxis()))
/*      */         {
/*      */           
/* 2045 */           nodeType = 1;
/*      */         }
/* 2047 */         step1 = new Step(5, nodeType, null);
/* 2048 */         expression1 = new AbsoluteLocationPath(this.parser.insertStep(step1, (RelativeLocationPath)expression13));
/*      */ 
/*      */         
/* 2051 */         CUP$XPathParser$result = new b(24, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2053 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 75:
/* 2058 */         expression1 = null;
/* 2059 */         i8 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2060 */         i28 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2061 */         expression13 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2062 */         i49 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2063 */         i67 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2064 */         expression23 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2066 */         right = (Step)expression23;
/* 2067 */         i75 = right.getAxis();
/* 2068 */         type = right.getNodeType();
/* 2069 */         vector12 = right.getPredicates();
/*      */         
/* 2071 */         if (i75 == 3 && type != 2) {
/*      */           
/* 2073 */           if (vector12 == null) {
/* 2074 */             right.setAxis(4);
/* 2075 */             if (expression13 instanceof Step && ((Step)expression13).isAbbreviatedDot()) {
/* 2076 */               expression1 = right;
/*      */             }
/*      */             else {
/*      */               
/* 2080 */               RelativeLocationPath left = (RelativeLocationPath)expression13;
/* 2081 */               expression1 = new ParentLocationPath(left, right);
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 2086 */           else if (expression13 instanceof Step && ((Step)expression13).isAbbreviatedDot()) {
/* 2087 */             Step left = new Step(5, 1, null);
/*      */             
/* 2089 */             expression1 = new ParentLocationPath(left, right);
/*      */           }
/*      */           else {
/*      */             
/* 2093 */             RelativeLocationPath left = (RelativeLocationPath)expression13;
/* 2094 */             Step mid = new Step(5, 1, null);
/*      */             
/* 2096 */             ParentLocationPath ppl = new ParentLocationPath(mid, right);
/* 2097 */             expression1 = new ParentLocationPath(left, ppl);
/*      */           }
/*      */         
/*      */         }
/* 2101 */         else if (i75 == 2 || type == 2) {
/*      */           
/* 2103 */           RelativeLocationPath left = (RelativeLocationPath)expression13;
/* 2104 */           Step middle = new Step(5, 1, null);
/*      */           
/* 2106 */           ParentLocationPath ppl = new ParentLocationPath(middle, right);
/* 2107 */           expression1 = new ParentLocationPath(left, ppl);
/*      */         }
/*      */         else {
/*      */           
/* 2111 */           RelativeLocationPath left = (RelativeLocationPath)expression13;
/* 2112 */           Step middle = new Step(5, -1, null);
/*      */           
/* 2114 */           ParentLocationPath ppl = new ParentLocationPath(middle, right);
/* 2115 */           expression1 = new ParentLocationPath(left, ppl);
/*      */         } 
/*      */         
/* 2118 */         CUP$XPathParser$result = new b(22, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2120 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 74:
/* 2125 */         expression1 = null;
/* 2126 */         aalpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2127 */         aalpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2128 */         aalp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2129 */         expression1 = aalp;
/* 2130 */         CUP$XPathParser$result = new b(23, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2132 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 73:
/* 2137 */         expression1 = null;
/* 2138 */         i7 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2139 */         i27 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2140 */         expression12 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2141 */         expression1 = new AbsoluteLocationPath(expression12);
/* 2142 */         CUP$XPathParser$result = new b(23, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2144 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 72:
/* 2149 */         expression1 = null;
/* 2150 */         expression1 = new AbsoluteLocationPath();
/* 2151 */         CUP$XPathParser$result = new b(23, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2153 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 71:
/* 2158 */         expression1 = null;
/* 2159 */         arlpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2160 */         arlpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2161 */         arlp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2162 */         expression1 = arlp;
/* 2163 */         CUP$XPathParser$result = new b(21, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2165 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 70:
/* 2170 */         expression1 = null;
/* 2171 */         i6 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2172 */         i26 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2173 */         expression11 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2174 */         i49 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2175 */         i67 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2176 */         expression23 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2178 */         if (expression11 instanceof Step && ((Step)expression11).isAbbreviatedDot()) {
/* 2179 */           expression1 = expression23;
/*      */         }
/* 2181 */         else if (((Step)expression23).isAbbreviatedDot()) {
/* 2182 */           expression1 = expression11;
/*      */         } else {
/*      */           
/* 2185 */           expression1 = new ParentLocationPath((RelativeLocationPath)expression11, expression23);
/*      */         } 
/*      */ 
/*      */         
/* 2189 */         CUP$XPathParser$result = new b(21, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2191 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 69:
/* 2196 */         expression1 = null;
/* 2197 */         stepleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2198 */         stepright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2199 */         step = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2200 */         expression1 = step;
/* 2201 */         CUP$XPathParser$result = new b(21, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2203 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 68:
/* 2208 */         expression1 = null;
/* 2209 */         alpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2210 */         alpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2211 */         alp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2212 */         expression1 = alp;
/* 2213 */         CUP$XPathParser$result = new b(4, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2215 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 67:
/* 2220 */         expression1 = null;
/* 2221 */         rlpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2222 */         rlpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2223 */         rlp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2224 */         expression1 = rlp;
/* 2225 */         CUP$XPathParser$result = new b(4, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2227 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 66:
/* 2232 */         expression1 = null;
/* 2233 */         fexpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2234 */         fexpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2235 */         fexp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2236 */         i48 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2237 */         i66 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2238 */         expression22 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2244 */         i72 = -1;
/* 2245 */         if (expression22 instanceof Step && this.parser.isElementAxis(((Step)expression22).getAxis()))
/*      */         {
/*      */           
/* 2248 */           i72 = 1;
/*      */         }
/* 2250 */         step2 = new Step(5, i72, null);
/* 2251 */         fpp = new FilterParentPath(fexp, step2);
/* 2252 */         fpp = new FilterParentPath(fpp, expression22);
/* 2253 */         if (!(fexp instanceof KeyCall)) {
/* 2254 */           fpp.setDescendantAxis();
/*      */         }
/* 2256 */         expression1 = fpp;
/*      */         
/* 2258 */         CUP$XPathParser$result = new b(19, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2260 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 65:
/* 2265 */         expression1 = null;
/* 2266 */         fexpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2267 */         fexpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2268 */         fexp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2269 */         i48 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2270 */         i66 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2271 */         expression22 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2272 */         expression1 = new FilterParentPath(fexp, expression22);
/* 2273 */         CUP$XPathParser$result = new b(19, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2275 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 64:
/* 2280 */         expression1 = null;
/* 2281 */         fexpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2282 */         fexpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2283 */         fexp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2284 */         expression1 = fexp;
/* 2285 */         CUP$XPathParser$result = new b(19, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2287 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 63:
/* 2292 */         expression1 = null;
/* 2293 */         lpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2294 */         lpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2295 */         lp = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2296 */         expression1 = lp;
/* 2297 */         CUP$XPathParser$result = new b(19, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2299 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 62:
/* 2304 */         expression1 = null;
/* 2305 */         peleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2306 */         peright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2307 */         pe = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2308 */         restleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2309 */         restright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2310 */         rest = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2311 */         expression1 = new UnionPathExpr(pe, rest);
/* 2312 */         CUP$XPathParser$result = new b(18, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2314 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 61:
/* 2319 */         expression1 = null;
/* 2320 */         peleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2321 */         peright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2322 */         pe = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2323 */         expression1 = pe;
/* 2324 */         CUP$XPathParser$result = new b(18, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2326 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 60:
/* 2331 */         expression1 = null;
/* 2332 */         i5 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2333 */         i25 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2334 */         expression10 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2335 */         expression1 = new UnaryOpExpr(expression10);
/* 2336 */         CUP$XPathParser$result = new b(14, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2338 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 59:
/* 2343 */         expression1 = null;
/* 2344 */         i5 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2345 */         i25 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2346 */         expression10 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2347 */         expression1 = expression10;
/* 2348 */         CUP$XPathParser$result = new b(14, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2350 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 58:
/* 2355 */         expression1 = null;
/* 2356 */         i4 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2357 */         i24 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2358 */         expression9 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2359 */         i47 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2360 */         i65 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2361 */         expression21 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2362 */         expression1 = new BinOpExpr(4, expression9, expression21);
/* 2363 */         CUP$XPathParser$result = new b(13, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2365 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 57:
/* 2370 */         expression1 = null;
/* 2371 */         i4 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2372 */         i24 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2373 */         expression9 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2374 */         i47 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2375 */         i65 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2376 */         expression21 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2377 */         expression1 = new BinOpExpr(3, expression9, expression21);
/* 2378 */         CUP$XPathParser$result = new b(13, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2380 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 56:
/* 2385 */         expression1 = null;
/* 2386 */         i4 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2387 */         i24 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2388 */         expression9 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2389 */         i47 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2390 */         i65 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2391 */         expression21 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2392 */         expression1 = new BinOpExpr(2, expression9, expression21);
/* 2393 */         CUP$XPathParser$result = new b(13, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2395 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 55:
/* 2400 */         expression1 = null;
/* 2401 */         ueleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2402 */         ueright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2403 */         ue = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2404 */         expression1 = ue;
/* 2405 */         CUP$XPathParser$result = new b(13, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2407 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 54:
/* 2412 */         expression1 = null;
/* 2413 */         i3 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2414 */         i23 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2415 */         expression8 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2416 */         i46 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2417 */         i64 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2418 */         expression20 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2419 */         expression1 = new BinOpExpr(1, expression8, expression20);
/* 2420 */         CUP$XPathParser$result = new b(12, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2422 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 53:
/* 2427 */         expression1 = null;
/* 2428 */         i3 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2429 */         i23 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2430 */         expression8 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2431 */         i46 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2432 */         i64 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2433 */         expression20 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2434 */         expression1 = new BinOpExpr(0, expression8, expression20);
/* 2435 */         CUP$XPathParser$result = new b(12, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2437 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 52:
/* 2442 */         expression1 = null;
/* 2443 */         meleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2444 */         meright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2445 */         me = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2446 */         expression1 = me;
/* 2447 */         CUP$XPathParser$result = new b(12, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2449 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 51:
/* 2454 */         expression1 = null;
/* 2455 */         i2 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2456 */         i22 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2457 */         expression7 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2458 */         i45 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2459 */         i63 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2460 */         expression19 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2461 */         expression1 = new RelationalExpr(4, expression7, expression19);
/* 2462 */         CUP$XPathParser$result = new b(11, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2464 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 50:
/* 2469 */         expression1 = null;
/* 2470 */         i2 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2471 */         i22 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2472 */         expression7 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2473 */         i45 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2474 */         i63 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2475 */         expression19 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2476 */         expression1 = new RelationalExpr(5, expression7, expression19);
/* 2477 */         CUP$XPathParser$result = new b(11, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2479 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 49:
/* 2484 */         expression1 = null;
/* 2485 */         i2 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2486 */         i22 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2487 */         expression7 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2488 */         i45 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2489 */         i63 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2490 */         expression19 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2491 */         expression1 = new RelationalExpr(2, expression7, expression19);
/* 2492 */         CUP$XPathParser$result = new b(11, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2494 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 48:
/* 2499 */         expression1 = null;
/* 2500 */         i2 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2501 */         i22 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2502 */         expression7 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2503 */         i45 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2504 */         i63 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2505 */         expression19 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2506 */         expression1 = new RelationalExpr(3, expression7, expression19);
/* 2507 */         CUP$XPathParser$result = new b(11, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2509 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 47:
/* 2514 */         expression1 = null;
/* 2515 */         i1 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2516 */         i21 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2517 */         expression6 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2518 */         expression1 = expression6;
/* 2519 */         CUP$XPathParser$result = new b(11, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2521 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 46:
/* 2526 */         expression1 = null;
/* 2527 */         eeleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2528 */         eeright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2529 */         ee = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2530 */         i44 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2531 */         i62 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2532 */         expression18 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2533 */         expression1 = new EqualityExpr(1, ee, expression18);
/* 2534 */         CUP$XPathParser$result = new b(10, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2536 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 45:
/* 2541 */         expression1 = null;
/* 2542 */         eeleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2543 */         eeright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2544 */         ee = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2545 */         i44 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2546 */         i62 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2547 */         expression18 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2548 */         expression1 = new EqualityExpr(0, ee, expression18);
/* 2549 */         CUP$XPathParser$result = new b(10, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2551 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 44:
/* 2556 */         expression1 = null;
/* 2557 */         releft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2558 */         reright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2559 */         re = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2560 */         expression1 = re;
/* 2561 */         CUP$XPathParser$result = new b(10, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2563 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 43:
/* 2568 */         expression1 = null;
/* 2569 */         n = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2570 */         i20 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2571 */         expression5 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2572 */         i43 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2573 */         i61 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2574 */         expression17 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2575 */         expression1 = new LogicalExpr(1, expression5, expression17);
/* 2576 */         CUP$XPathParser$result = new b(9, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2578 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 42:
/* 2583 */         expression1 = null;
/* 2584 */         m = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2585 */         i19 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2586 */         expression4 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2587 */         expression1 = expression4;
/* 2588 */         CUP$XPathParser$result = new b(9, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2590 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 41:
/* 2595 */         expression1 = null;
/* 2596 */         oeleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2597 */         oeright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2598 */         oe = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2599 */         i42 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2600 */         i60 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2601 */         expression16 = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2602 */         expression1 = new LogicalExpr(0, oe, expression16);
/* 2603 */         CUP$XPathParser$result = new b(8, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2605 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 40:
/* 2610 */         expression1 = null;
/* 2611 */         aeleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2612 */         aeright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2613 */         ae = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2614 */         expression1 = ae;
/* 2615 */         CUP$XPathParser$result = new b(8, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2617 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 39:
/* 2622 */         expression1 = null;
/* 2623 */         exleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2624 */         exright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2625 */         ex = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2626 */         expression1 = ex;
/* 2627 */         CUP$XPathParser$result = new b(2, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2629 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 38:
/* 2634 */         expression1 = null;
/* 2635 */         eleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2636 */         eright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2637 */         e = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/*      */         
/* 2639 */         expression1 = new Predicate(e);
/*      */         
/* 2641 */         CUP$XPathParser$result = new b(5, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, expression1);
/*      */         
/* 2643 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 37:
/* 2648 */         vector2 = null;
/* 2649 */         pleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2650 */         pright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2651 */         p = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2652 */         i41 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2653 */         i59 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2654 */         vector6 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2655 */         vector6.insertElementAt(p, 0); vector2 = vector6;
/* 2656 */         CUP$XPathParser$result = new b(35, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, vector2);
/*      */         
/* 2658 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 36:
/* 2663 */         vector1 = null;
/* 2664 */         pleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2665 */         pright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2666 */         p = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2668 */         temp = new Vector();
/* 2669 */         temp.addElement(p);
/* 2670 */         vector1 = temp;
/*      */         
/* 2672 */         CUP$XPathParser$result = new b(35, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, vector1);
/*      */         
/* 2674 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 35:
/* 2679 */         integer1 = null;
/* 2680 */         integer1 = new Integer(2);
/* 2681 */         CUP$XPathParser$result = new b(42, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer1);
/*      */         
/* 2683 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 34:
/* 2688 */         integer1 = null;
/* 2689 */         integer1 = new Integer(3);
/* 2690 */         CUP$XPathParser$result = new b(42, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer1);
/*      */         
/* 2692 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 33:
/* 2697 */         integer1 = null;
/* 2698 */         integer1 = new Integer(2);
/* 2699 */         CUP$XPathParser$result = new b(42, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, integer1);
/*      */         
/* 2701 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 32:
/* 2706 */         object1 = null;
/* 2707 */         qnleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2708 */         qnright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2709 */         qn = (QName)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2710 */         object1 = qn;
/* 2711 */         CUP$XPathParser$result = new b(34, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2713 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 31:
/* 2718 */         object1 = null;
/* 2719 */         object1 = null;
/* 2720 */         CUP$XPathParser$result = new b(34, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2722 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 30:
/* 2727 */         object1 = null;
/* 2728 */         object1 = new Integer(7);
/* 2729 */         CUP$XPathParser$result = new b(33, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2731 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 29:
/* 2736 */         object1 = null;
/* 2737 */         object1 = new Integer(8);
/* 2738 */         CUP$XPathParser$result = new b(33, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2740 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 28:
/* 2745 */         object1 = null;
/* 2746 */         object1 = new Integer(3);
/* 2747 */         CUP$XPathParser$result = new b(33, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2749 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 27:
/* 2754 */         object1 = null;
/* 2755 */         object1 = new Integer(-1);
/* 2756 */         CUP$XPathParser$result = new b(33, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2758 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 26:
/* 2763 */         object1 = null;
/* 2764 */         k = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2765 */         i18 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2766 */         object3 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2767 */         object1 = object3;
/* 2768 */         CUP$XPathParser$result = new b(33, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, object1);
/*      */         
/* 2770 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 25:
/* 2775 */         stepPattern2 = null;
/* 2776 */         axisleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2777 */         axisright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2778 */         axis = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2779 */         i40 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2780 */         i58 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2781 */         stepPattern3 = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2782 */         i71 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2783 */         i74 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2784 */         vector10 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */ 
/*      */         
/* 2787 */         stepPattern2 = stepPattern3.setPredicates(vector10);
/*      */         
/* 2789 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2791 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 24:
/* 2796 */         stepPattern2 = null;
/* 2797 */         axisleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2798 */         axisright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2799 */         axis = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2800 */         i40 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2801 */         i58 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2802 */         stepPattern3 = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2804 */         stepPattern2 = stepPattern3;
/*      */         
/* 2806 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2808 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 23:
/* 2813 */         stepPattern2 = null;
/* 2814 */         axisleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2815 */         axisright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2816 */         axis = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2817 */         i39 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2818 */         i57 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2819 */         object5 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2820 */         i71 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2821 */         i74 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2822 */         vector10 = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2824 */         stepPattern2 = this.parser.createStepPattern(axis.intValue(), object5, vector10);
/*      */         
/* 2826 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2828 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 22:
/* 2833 */         stepPattern2 = null;
/* 2834 */         axisleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2835 */         axisright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2836 */         axis = (Integer)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2837 */         i39 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2838 */         i57 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2839 */         object5 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2841 */         stepPattern2 = this.parser.createStepPattern(axis.intValue(), object5, null);
/*      */         
/* 2843 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2845 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 21:
/* 2850 */         stepPattern2 = null;
/* 2851 */         pipleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2852 */         pipright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2853 */         pip = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2854 */         ppleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2855 */         ppright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2856 */         pp = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2857 */         stepPattern2 = pip.setPredicates(pp);
/* 2858 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2860 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 20:
/* 2865 */         stepPattern2 = null;
/* 2866 */         pipleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2867 */         pipright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2868 */         pip = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2869 */         stepPattern2 = pip;
/* 2870 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2872 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 19:
/* 2877 */         stepPattern2 = null;
/* 2878 */         ntleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2879 */         ntright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2880 */         nt = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2881 */         ppleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2882 */         ppright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2883 */         pp = (Vector)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2885 */         stepPattern2 = this.parser.createStepPattern(3, nt, pp);
/*      */         
/* 2887 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2889 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 18:
/* 2894 */         stepPattern2 = null;
/* 2895 */         ntleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2896 */         ntright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2897 */         nt = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/*      */         
/* 2899 */         stepPattern2 = this.parser.createStepPattern(3, nt, null);
/*      */         
/* 2901 */         CUP$XPathParser$result = new b(32, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern2);
/*      */         
/* 2903 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 17:
/* 2908 */         relativePathPattern1 = null;
/* 2909 */         spleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2910 */         spright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2911 */         sp = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2912 */         i38 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2913 */         i56 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2914 */         relativePathPattern4 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2915 */         relativePathPattern1 = new AncestorPattern(sp, relativePathPattern4);
/* 2916 */         CUP$XPathParser$result = new b(31, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, relativePathPattern1);
/*      */         
/* 2918 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 16:
/* 2923 */         relativePathPattern1 = null;
/* 2924 */         spleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 2925 */         spright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 2926 */         sp = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 2927 */         i38 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2928 */         i56 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2929 */         relativePathPattern4 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2930 */         relativePathPattern1 = new ParentPattern(sp, relativePathPattern4);
/* 2931 */         CUP$XPathParser$result = new b(31, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, relativePathPattern1);
/*      */         
/* 2933 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/* 2938 */         relativePathPattern1 = null;
/* 2939 */         spleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2940 */         spright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2941 */         sp = (StepPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2942 */         relativePathPattern1 = sp;
/* 2943 */         CUP$XPathParser$result = new b(31, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, relativePathPattern1);
/*      */         
/* 2945 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 14:
/* 2950 */         stepPattern1 = null;
/* 2951 */         j = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2952 */         i17 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2953 */         str1 = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2954 */         stepPattern1 = new ProcessingInstructionPattern(str1);
/* 2955 */         CUP$XPathParser$result = new b(30, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, stepPattern1);
/*      */         
/* 2957 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 13:
/* 2962 */         idKeyPattern1 = null;
/* 2963 */         l1left = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d;
/* 2964 */         l1right = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).e;
/* 2965 */         l1 = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).f;
/* 2966 */         l2left = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2967 */         l2right = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2968 */         l2 = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2969 */         idKeyPattern1 = new KeyPattern(l1, l2);
/* 2970 */         CUP$XPathParser$result = new b(27, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 5)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, idKeyPattern1);
/*      */         
/* 2972 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 12:
/* 2977 */         idKeyPattern1 = null;
/* 2978 */         lleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 2979 */         lright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 2980 */         l = (String)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 2981 */         idKeyPattern1 = new IdPattern(l);
/* 2982 */         this.parser.setHasIdCall(true);
/*      */         
/* 2984 */         CUP$XPathParser$result = new b(27, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 3)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, idKeyPattern1);
/*      */         
/* 2986 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/* 2991 */         pattern1 = null;
/* 2992 */         i = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 2993 */         i16 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 2994 */         relativePathPattern2 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 2995 */         pattern1 = relativePathPattern2;
/* 2996 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 2998 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/* 3003 */         pattern1 = null;
/* 3004 */         i = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3005 */         i16 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3006 */         relativePathPattern2 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3007 */         pattern1 = new AncestorPattern(relativePathPattern2);
/* 3008 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3010 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 9:
/* 3015 */         pattern1 = null;
/* 3016 */         ikpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 3017 */         ikpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 3018 */         ikp = (IdKeyPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 3019 */         i37 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3020 */         i55 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3021 */         relativePathPattern3 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3022 */         pattern1 = new AncestorPattern(ikp, relativePathPattern3);
/* 3023 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3025 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 8:
/* 3030 */         pattern1 = null;
/* 3031 */         ikpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 3032 */         ikpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 3033 */         ikp = (IdKeyPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 3034 */         i37 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3035 */         i55 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3036 */         relativePathPattern3 = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3037 */         pattern1 = new ParentPattern(ikp, relativePathPattern3);
/* 3038 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3040 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/* 3045 */         pattern1 = null;
/* 3046 */         ikpleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3047 */         ikpright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3048 */         ikp = (IdKeyPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3049 */         pattern1 = ikp;
/* 3050 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3052 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/* 3057 */         pattern1 = null;
/* 3058 */         rppleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3059 */         rppright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3060 */         rpp = (RelativePathPattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3061 */         pattern1 = new AbsolutePathPattern(rpp);
/* 3062 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3064 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/* 3069 */         pattern1 = null;
/* 3070 */         pattern1 = new AbsolutePathPattern(null);
/* 3071 */         CUP$XPathParser$result = new b(29, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3073 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 3078 */         pattern1 = null;
/* 3079 */         lppleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d;
/* 3080 */         lppright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).e;
/* 3081 */         lpp = (Pattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).f;
/* 3082 */         i36 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3083 */         i54 = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3084 */         pattern2 = (Pattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3085 */         pattern1 = new AlternativePattern(lpp, pattern2);
/* 3086 */         CUP$XPathParser$result = new b(28, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 2)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3088 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/* 3093 */         pattern1 = null;
/* 3094 */         lppleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3095 */         lppright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3096 */         lpp = (Pattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3097 */         pattern1 = lpp;
/* 3098 */         CUP$XPathParser$result = new b(28, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, pattern1);
/*      */         
/* 3100 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 3105 */         syntaxTreeNode1 = null;
/* 3106 */         exprleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3107 */         exprright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3108 */         expr = (Expression)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3109 */         syntaxTreeNode1 = expr;
/* 3110 */         CUP$XPathParser$result = new b(1, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, syntaxTreeNode1);
/*      */         
/* 3112 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/* 3117 */         syntaxTreeNode1 = null;
/* 3118 */         patternleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).d;
/* 3119 */         patternright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e;
/* 3120 */         pattern = (Pattern)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).f;
/* 3121 */         syntaxTreeNode1 = pattern;
/* 3122 */         CUP$XPathParser$result = new b(1, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, syntaxTreeNode1);
/*      */         
/* 3124 */         return CUP$XPathParser$result;
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/* 3129 */         RESULT = null;
/* 3130 */         start_valleft = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d;
/* 3131 */         start_valright = ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).e;
/* 3132 */         start_val = (SyntaxTreeNode)((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).f;
/* 3133 */         RESULT = start_val;
/* 3134 */         CUP$XPathParser$result = new b(0, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 1)).d, ((b)CUP$XPathParser$stack.elementAt(CUP$XPathParser$top - 0)).e, RESULT);
/*      */ 
/*      */         
/* 3137 */         CUP$XPathParser$parser.done_parsing();
/* 3138 */         return CUP$XPathParser$result;
/*      */     } 
/*      */ 
/*      */     
/* 3142 */     throw new Exception("Invalid action number found in internal parse table"); }
/*      */ 
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CUP$XPathParser$actions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */