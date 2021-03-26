/*      */ package b.a;
/*      */ 
/*      */ import java.util.Stack;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class c
/*      */ {
/*      */   protected static final int _error_sync_size = 3;
/*      */   
/*      */   public c(a parama) {
/*  129 */     this();
/*  130 */     setScanner(parama);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int error_sync_size() {
/*  147 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean _done_parsing = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int tos;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected b cur_token;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void done_parsing() {
/*  232 */     this._done_parsing = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  251 */   protected Stack stack = new Stack();
/*      */ 
/*      */ 
/*      */   
/*      */   protected short[][] production_tab;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short[][] action_tab;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short[][] reduce_tab;
/*      */ 
/*      */ 
/*      */   
/*      */   private a _scanner;
/*      */ 
/*      */   
/*      */   protected b[] lookahead;
/*      */ 
/*      */   
/*      */   protected int lookahead_pos;
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScanner(a parama) {
/*  278 */     this._scanner = parama;
/*      */   }
/*      */ 
/*      */   
/*      */   public a getScanner() {
/*  283 */     return this._scanner;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void user_init() throws Exception {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public b scan() throws Exception {
/*  335 */     b b1 = getScanner().next_token();
/*  336 */     return (b1 != null) ? b1 : new b(EOF_sym());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void report_fatal_error(String paramString, Object paramObject) throws Exception {
/*  355 */     done_parsing();
/*      */ 
/*      */     
/*  358 */     report_error(paramString, paramObject);
/*      */ 
/*      */     
/*  361 */     throw new Exception("Can't recover from previous error(s)");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void report_error(String paramString, Object paramObject) {
/*  377 */     System.err.print(paramString);
/*  378 */     if (paramObject instanceof b)
/*  379 */     { if (((b)paramObject).d != -1)
/*  380 */       { System.err.println(" at character " + ((b)paramObject).d + 
/*  381 */             " of input"); }
/*  382 */       else { System.err.println(""); }  }
/*  383 */     else { System.err.println(""); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void syntax_error(b paramb) {
/*  396 */     report_error("Syntax error", paramb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unrecovered_syntax_error(b paramb) throws Exception {
/*  409 */     report_fatal_error("Couldn't repair and continue parse", paramb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final short get_action(int paramInt1, int paramInt2) {
/*  428 */     short[] arrayOfShort = this.action_tab[paramInt1];
/*      */ 
/*      */     
/*  431 */     if (arrayOfShort.length < 20) {
/*  432 */       for (byte b1 = 0; b1 < arrayOfShort.length; b1++)
/*      */       {
/*      */         
/*  435 */         short s = arrayOfShort[b1++];
/*  436 */         if (s == paramInt2 || s == -1)
/*      */         {
/*      */           
/*  439 */           return arrayOfShort[b1];
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  445 */       int i = 0;
/*  446 */       int j = (arrayOfShort.length - 1) / 2 - 1;
/*  447 */       while (i <= j) {
/*      */         
/*  449 */         int k = (i + j) / 2;
/*  450 */         if (paramInt2 == arrayOfShort[k * 2])
/*  451 */           return arrayOfShort[k * 2 + 1]; 
/*  452 */         if (paramInt2 > arrayOfShort[k * 2]) {
/*  453 */           i = k + 1; continue;
/*      */         } 
/*  455 */         j = k - 1;
/*      */       } 
/*      */ 
/*      */       
/*  459 */       return arrayOfShort[arrayOfShort.length - 1];
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  464 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final short get_reduce(int paramInt1, int paramInt2) {
/*  482 */     short[] arrayOfShort = this.reduce_tab[paramInt1];
/*      */ 
/*      */     
/*  485 */     if (arrayOfShort == null) {
/*  486 */       return -1;
/*      */     }
/*  488 */     for (byte b1 = 0; b1 < arrayOfShort.length; b1++) {
/*      */ 
/*      */       
/*  491 */       short s = arrayOfShort[b1++];
/*  492 */       if (s == paramInt2 || s == -1)
/*      */       {
/*      */         
/*  495 */         return arrayOfShort[b1];
/*      */       }
/*      */     } 
/*      */     
/*  499 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public b parse() throws Exception {
/*  516 */     b b1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  523 */     this.production_tab = production_table();
/*  524 */     this.action_tab = action_table();
/*  525 */     this.reduce_tab = reduce_table();
/*      */ 
/*      */     
/*  528 */     init_actions();
/*      */ 
/*      */     
/*  531 */     user_init();
/*      */ 
/*      */     
/*  534 */     this.cur_token = scan();
/*      */ 
/*      */     
/*  537 */     this.stack.removeAllElements();
/*  538 */     this.stack.push(new b(0, start_state()));
/*  539 */     this.tos = 0;
/*      */ 
/*      */     
/*  542 */     for (this._done_parsing = false; !this._done_parsing; ) {
/*      */ 
/*      */       
/*  545 */       if (this.cur_token.c) {
/*  546 */         throw new Error("Symbol recycling detected (fix your scanner).");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  551 */       short s = get_action(((b)this.stack.peek()).b, this.cur_token.a);
/*      */ 
/*      */       
/*  554 */       if (s > 0) {
/*      */ 
/*      */         
/*  557 */         this.cur_token.b = s - 1;
/*  558 */         this.cur_token.c = true;
/*  559 */         this.stack.push(this.cur_token);
/*  560 */         this.tos++;
/*      */ 
/*      */         
/*  563 */         this.cur_token = scan();
/*      */         continue;
/*      */       } 
/*  566 */       if (s < 0) {
/*      */ 
/*      */         
/*  569 */         b1 = do_action(-s - 1, this, this.stack, this.tos);
/*      */ 
/*      */         
/*  572 */         short s2 = this.production_tab[-s - 1][0];
/*  573 */         short s1 = this.production_tab[-s - 1][1];
/*      */ 
/*      */         
/*  576 */         for (byte b2 = 0; b2 < s1; b2++) {
/*      */           
/*  578 */           this.stack.pop();
/*  579 */           this.tos--;
/*      */         } 
/*      */ 
/*      */         
/*  583 */         s = get_reduce(((b)this.stack.peek()).b, s2);
/*      */ 
/*      */         
/*  586 */         b1.b = s;
/*  587 */         b1.c = true;
/*  588 */         this.stack.push(b1);
/*  589 */         this.tos++;
/*      */         continue;
/*      */       } 
/*  592 */       if (s == 0) {
/*      */ 
/*      */         
/*  595 */         syntax_error(this.cur_token);
/*      */ 
/*      */         
/*  598 */         if (!error_recovery(false)) {
/*      */ 
/*      */           
/*  601 */           unrecovered_syntax_error(this.cur_token);
/*      */ 
/*      */           
/*  604 */           done_parsing(); continue;
/*      */         } 
/*  606 */         b1 = this.stack.peek();
/*      */       } 
/*      */     } 
/*      */     
/*  610 */     return b1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void debug_message(String paramString) {
/*  622 */     System.err.println(paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump_stack() {
/*  630 */     if (this.stack == null) {
/*      */       
/*  632 */       debug_message("# Stack dump requested, but stack is null");
/*      */       
/*      */       return;
/*      */     } 
/*  636 */     debug_message("============ Parse Stack Dump ============");
/*      */ 
/*      */     
/*  639 */     for (byte b1 = 0; b1 < this.stack.size(); b1++)
/*      */     {
/*  641 */       debug_message("Symbol: " + ((b)this.stack.elementAt(b1)).a + 
/*  642 */           " State: " + ((b)this.stack.elementAt(b1)).b);
/*      */     }
/*  644 */     debug_message("==========================================");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void debug_reduce(int paramInt1, int paramInt2, int paramInt3) {
/*  657 */     debug_message("# Reduce with prod #" + paramInt1 + " [NT=" + paramInt2 + 
/*  658 */         ", " + "SZ=" + paramInt3 + "]");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void debug_shift(b paramb) {
/*  669 */     debug_message("# Shift under term #" + paramb.a + 
/*  670 */         " to state #" + paramb.b);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void debug_stack() {
/*  678 */     StringBuffer stringBuffer = new StringBuffer("## STACK:");
/*  679 */     for (byte b1 = 0; b1 < this.stack.size(); b1++) {
/*  680 */       b b2 = this.stack.elementAt(b1);
/*  681 */       stringBuffer.append(" <state " + b2.b + ", sym " + b2.a + ">");
/*  682 */       if (b1 % 3 == 2 || b1 == this.stack.size() - 1) {
/*  683 */         debug_message(stringBuffer.toString());
/*  684 */         stringBuffer = new StringBuffer("         ");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public b debug_parse() throws Exception {
/*  703 */     b b1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  709 */     this.production_tab = production_table();
/*  710 */     this.action_tab = action_table();
/*  711 */     this.reduce_tab = reduce_table();
/*      */     
/*  713 */     debug_message("# Initializing parser");
/*      */ 
/*      */     
/*  716 */     init_actions();
/*      */ 
/*      */     
/*  719 */     user_init();
/*      */ 
/*      */     
/*  722 */     this.cur_token = scan();
/*      */     
/*  724 */     debug_message("# Current Symbol is #" + this.cur_token.a);
/*      */ 
/*      */     
/*  727 */     this.stack.removeAllElements();
/*  728 */     this.stack.push(new b(0, start_state()));
/*  729 */     this.tos = 0;
/*      */ 
/*      */     
/*  732 */     for (this._done_parsing = false; !this._done_parsing; ) {
/*      */ 
/*      */       
/*  735 */       if (this.cur_token.c) {
/*  736 */         throw new Error("Symbol recycling detected (fix your scanner).");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  742 */       short s = get_action(((b)this.stack.peek()).b, this.cur_token.a);
/*      */ 
/*      */       
/*  745 */       if (s > 0) {
/*      */ 
/*      */         
/*  748 */         this.cur_token.b = s - 1;
/*  749 */         this.cur_token.c = true;
/*  750 */         debug_shift(this.cur_token);
/*  751 */         this.stack.push(this.cur_token);
/*  752 */         this.tos++;
/*      */ 
/*      */         
/*  755 */         this.cur_token = scan();
/*  756 */         debug_message("# Current token is " + this.cur_token);
/*      */         continue;
/*      */       } 
/*  759 */       if (s < 0) {
/*      */ 
/*      */         
/*  762 */         b1 = do_action(-s - 1, this, this.stack, this.tos);
/*      */ 
/*      */         
/*  765 */         short s2 = this.production_tab[-s - 1][0];
/*  766 */         short s1 = this.production_tab[-s - 1][1];
/*      */         
/*  768 */         debug_reduce(-s - 1, s2, s1);
/*      */ 
/*      */         
/*  771 */         for (byte b2 = 0; b2 < s1; b2++) {
/*      */           
/*  773 */           this.stack.pop();
/*  774 */           this.tos--;
/*      */         } 
/*      */ 
/*      */         
/*  778 */         s = get_reduce(((b)this.stack.peek()).b, s2);
/*  779 */         debug_message("# Reduce rule: top state " + 
/*  780 */             ((b)this.stack.peek()).b + 
/*  781 */             ", lhs sym " + s2 + " -> state " + s);
/*      */ 
/*      */         
/*  784 */         b1.b = s;
/*  785 */         b1.c = true;
/*  786 */         this.stack.push(b1);
/*  787 */         this.tos++;
/*      */         
/*  789 */         debug_message("# Goto state #" + s);
/*      */         continue;
/*      */       } 
/*  792 */       if (s == 0) {
/*      */ 
/*      */         
/*  795 */         syntax_error(this.cur_token);
/*      */ 
/*      */         
/*  798 */         if (!error_recovery(true)) {
/*      */ 
/*      */           
/*  801 */           unrecovered_syntax_error(this.cur_token);
/*      */ 
/*      */           
/*  804 */           done_parsing(); continue;
/*      */         } 
/*  806 */         b1 = this.stack.peek();
/*      */       } 
/*      */     } 
/*      */     
/*  810 */     return b1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean error_recovery(boolean paramBoolean) throws Exception {
/*  842 */     if (paramBoolean) debug_message("# Attempting error recovery");
/*      */ 
/*      */ 
/*      */     
/*  846 */     if (!find_recovery_config(paramBoolean)) {
/*      */       
/*  848 */       if (paramBoolean) debug_message("# Error recovery fails"); 
/*  849 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  853 */     read_lookahead();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/*  859 */       if (paramBoolean) debug_message("# Trying to parse ahead"); 
/*  860 */       if (!try_parse_ahead(paramBoolean)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  866 */         if ((this.lookahead[0]).a == EOF_sym()) {
/*      */           
/*  868 */           if (paramBoolean) debug_message("# Error recovery fails at EOF"); 
/*  869 */           return false;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  878 */         if (paramBoolean)
/*  879 */           debug_message("# Consuming Symbol #" + (this.lookahead[0]).a); 
/*  880 */         restart_lookahead(); continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  884 */     if (paramBoolean) debug_message("# Parse-ahead ok, going back to normal parse");
/*      */ 
/*      */     
/*  887 */     parse_lookahead(paramBoolean);
/*      */ 
/*      */     
/*  890 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean shift_under_error() {
/*  901 */     return !(get_action(((b)this.stack.peek()).b, error_sym()) <= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean find_recovery_config(boolean paramBoolean) {
/*  918 */     if (paramBoolean) debug_message("# Finding recovery state on stack");
/*      */ 
/*      */     
/*  921 */     int i = ((b)this.stack.peek()).e;
/*  922 */     int j = ((b)this.stack.peek()).d;
/*      */ 
/*      */     
/*  925 */     while (!shift_under_error()) {
/*      */ 
/*      */       
/*  928 */       if (paramBoolean)
/*  929 */         debug_message("# Pop stack by one, state was # " + 
/*  930 */             ((b)this.stack.peek()).b); 
/*  931 */       j = ((b)this.stack.pop()).d;
/*  932 */       this.tos--;
/*      */ 
/*      */       
/*  935 */       if (this.stack.empty()) {
/*      */         
/*  937 */         if (paramBoolean) debug_message("# No recovery state found on stack"); 
/*  938 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  943 */     short s = get_action(((b)this.stack.peek()).b, error_sym());
/*  944 */     if (paramBoolean) {
/*      */       
/*  946 */       debug_message("# Recover state found (#" + 
/*  947 */           ((b)this.stack.peek()).b + ")");
/*  948 */       debug_message("# Shifting on error to state #" + (s - 1));
/*      */     } 
/*      */ 
/*      */     
/*  952 */     b b1 = new b(error_sym(), j, i);
/*  953 */     b1.b = s - 1;
/*  954 */     b1.c = true;
/*  955 */     this.stack.push(b1);
/*  956 */     this.tos++;
/*      */     
/*  958 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void read_lookahead() throws Exception {
/*  977 */     this.lookahead = new b[error_sync_size()];
/*      */ 
/*      */     
/*  980 */     for (byte b1 = 0; b1 < error_sync_size(); b1++) {
/*      */       
/*  982 */       this.lookahead[b1] = this.cur_token;
/*  983 */       this.cur_token = scan();
/*      */     } 
/*      */ 
/*      */     
/*  987 */     this.lookahead_pos = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected b cur_err_token() {
/*  993 */     return this.lookahead[this.lookahead_pos];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean advance_lookahead() {
/* 1003 */     this.lookahead_pos++;
/*      */ 
/*      */     
/* 1006 */     return !(this.lookahead_pos >= error_sync_size());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void restart_lookahead() throws Exception {
/* 1017 */     for (byte b1 = 1; b1 < error_sync_size(); b1++) {
/* 1018 */       this.lookahead[b1 - 1] = this.lookahead[b1];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1025 */     this.lookahead[error_sync_size() - 1] = this.cur_token;
/* 1026 */     this.cur_token = scan();
/*      */ 
/*      */     
/* 1029 */     this.lookahead_pos = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean try_parse_ahead(boolean paramBoolean) throws Exception {
/* 1050 */     d d = new d(this.stack);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/* 1056 */       short s1 = get_action(d.d(), (cur_err_token()).a);
/*      */ 
/*      */       
/* 1059 */       if (s1 == 0) return false;
/*      */ 
/*      */       
/* 1062 */       if (s1 > 0) {
/*      */ 
/*      */         
/* 1065 */         d.a(s1 - 1);
/*      */         
/* 1067 */         if (paramBoolean) debug_message("# Parse-ahead shifts Symbol #" + 
/* 1068 */               (cur_err_token()).a + " into state #" + (s1 - 1));
/*      */ 
/*      */         
/* 1071 */         if (!advance_lookahead()) return true;
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 1077 */       if (-s1 - 1 == start_production()) {
/*      */         
/* 1079 */         if (paramBoolean) debug_message("# Parse-ahead accepts"); 
/* 1080 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1084 */       short s2 = this.production_tab[-s1 - 1][0];
/* 1085 */       short s3 = this.production_tab[-s1 - 1][1];
/*      */ 
/*      */       
/* 1088 */       for (byte b1 = 0; b1 < s3; b1++) {
/* 1089 */         d.c();
/*      */       }
/* 1091 */       if (paramBoolean) {
/* 1092 */         debug_message("# Parse-ahead reduces: handle size = " + 
/* 1093 */             s3 + " lhs = #" + s2 + " from state #" + d.d());
/*      */       }
/*      */       
/* 1096 */       d.a(get_reduce(d.d(), s2));
/* 1097 */       if (paramBoolean) {
/* 1098 */         debug_message("# Goto state #" + d.d());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parse_lookahead(boolean paramBoolean) throws Exception {
/* 1121 */     b b1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1127 */     this.lookahead_pos = 0;
/*      */     
/* 1129 */     if (paramBoolean) {
/*      */       
/* 1131 */       debug_message("# Reparsing saved input with actions");
/* 1132 */       debug_message("# Current Symbol is #" + (cur_err_token()).a);
/* 1133 */       debug_message("# Current state is #" + 
/* 1134 */           ((b)this.stack.peek()).b);
/*      */     } 
/*      */ 
/*      */     
/* 1138 */     while (!this._done_parsing) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1143 */       short s = 
/* 1144 */         get_action(((b)this.stack.peek()).b, (cur_err_token()).a);
/*      */ 
/*      */       
/* 1147 */       if (s > 0) {
/*      */ 
/*      */         
/* 1150 */         (cur_err_token()).b = s - 1;
/* 1151 */         (cur_err_token()).c = true;
/* 1152 */         if (paramBoolean) debug_shift(cur_err_token()); 
/* 1153 */         this.stack.push(cur_err_token());
/* 1154 */         this.tos++;
/*      */ 
/*      */         
/* 1157 */         if (!advance_lookahead()) {
/*      */           
/* 1159 */           if (paramBoolean) debug_message("# Completed reparse");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1171 */         if (paramBoolean)
/* 1172 */           debug_message("# Current Symbol is #" + (cur_err_token()).a); 
/*      */         continue;
/*      */       } 
/* 1175 */       if (s < 0) {
/*      */ 
/*      */         
/* 1178 */         b1 = do_action(-s - 1, this, this.stack, this.tos);
/*      */ 
/*      */         
/* 1181 */         short s2 = this.production_tab[-s - 1][0];
/* 1182 */         short s1 = this.production_tab[-s - 1][1];
/*      */         
/* 1184 */         if (paramBoolean) debug_reduce(-s - 1, s2, s1);
/*      */ 
/*      */         
/* 1187 */         for (byte b2 = 0; b2 < s1; b2++) {
/*      */           
/* 1189 */           this.stack.pop();
/* 1190 */           this.tos--;
/*      */         } 
/*      */ 
/*      */         
/* 1194 */         s = get_reduce(((b)this.stack.peek()).b, s2);
/*      */ 
/*      */         
/* 1197 */         b1.b = s;
/* 1198 */         b1.c = true;
/* 1199 */         this.stack.push(b1);
/* 1200 */         this.tos++;
/*      */         
/* 1202 */         if (paramBoolean) debug_message("# Goto state #" + s);
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/* 1207 */       if (s == 0) {
/*      */         
/* 1209 */         report_fatal_error("Syntax error", b1);
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static short[][] unpackFromStrings(String[] paramArrayOfString) {
/* 1223 */     StringBuffer stringBuffer = new StringBuffer(paramArrayOfString[0]);
/* 1224 */     for (byte b1 = 1; b1 < paramArrayOfString.length; b1++)
/* 1225 */       stringBuffer.append(paramArrayOfString[b1]); 
/* 1226 */     byte b2 = 0;
/* 1227 */     int i = stringBuffer.charAt(b2) << 16 | stringBuffer.charAt(b2 + 1); b2 += 2;
/* 1228 */     short[][] arrayOfShort = new short[i][];
/* 1229 */     for (byte b3 = 0; b3 < i; b3++) {
/* 1230 */       int j = stringBuffer.charAt(b2) << 16 | stringBuffer.charAt(b2 + 1); b2 += 2;
/* 1231 */       arrayOfShort[b3] = new short[j];
/* 1232 */       for (byte b4 = 0; b4 < j; b4++)
/* 1233 */         arrayOfShort[b3][b4] = (short)(stringBuffer.charAt(b2++) - 2); 
/*      */     } 
/* 1235 */     return arrayOfShort;
/*      */   }
/*      */   
/*      */   public c() {}
/*      */   
/*      */   public abstract int EOF_sym();
/*      */   
/*      */   public abstract short[][] action_table();
/*      */   
/*      */   public abstract b do_action(int paramInt1, c paramc, Stack paramStack, int paramInt2) throws Exception;
/*      */   
/*      */   public abstract int error_sym();
/*      */   
/*      */   protected abstract void init_actions() throws Exception;
/*      */   
/*      */   public abstract short[][] production_table();
/*      */   
/*      */   public abstract short[][] reduce_table();
/*      */   
/*      */   public abstract int start_production();
/*      */   
/*      */   public abstract int start_state();
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/b/a/c.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */