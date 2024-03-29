//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";

//#line 2 "bjacc.y"
package parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

import node.DIVIDENode;
import node.Function;
import node.MINUSNode;
import node.NUMNode;
import node.Node;
import node.PLUSNode;
import node.TIMESNode;
import node.VARNode;

//#line 32 "Parser.java"

public class Parser {
	
	Node result = null;

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

	// ########## MESSAGES ##########
	// ###############################################################
	// method: debug
	// ###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

	// ########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
	// ###############################################################
	// methods: state stack push,pop,drop,peek
	// ###############################################################

	final void state_push(int state) {
		try {
			stateptr++;
			statestk[stateptr] = state;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = statestk.length;
			int newsize = oldsize * 2;
			int[] newstack = new int[newsize];
			System.arraycopy(statestk, 0, newstack, 0, oldsize);
			statestk = newstack;
			statestk[stateptr] = state;
		}
	}

	final int state_pop() {
		return statestk[stateptr--];
	}

	final void state_drop(int cnt) {
		stateptr -= cnt;
	}

	final int state_peek(int relative) {
		return statestk[stateptr - relative];
	}

	// ###############################################################
	// method: init_stacks : allocate and prepare stacks
	// ###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

	// ###############################################################
	// method: dump_stacks : show n levels of the stacks
	// ###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println("=index==state====value=     s:" + stateptr + "  v:"
				+ valptr);
		for (i = 0; i < count; i++)
			System.out.println(" " + i + "    " + statestk[i] + "      "
					+ valstk[i]);
		System.out.println("======================");
	}

	// ########## SEMANTIC VALUES ##########
	// public class ParserVal is defined in ParserVal.java

	String yytext;// user variable to return contextual strings
	ParserVal yyval; // used to return semantic vals from action routines
	public Node getYyval() {
		return result;
	}

	ParserVal yylval;// the 'lval' (result) I got from yylex()
	ParserVal valstk[];
	int valptr;

	// ###############################################################
	// methods: value stack push,pop,drop,peek.
	// ###############################################################
	void val_init() {
		valstk = new ParserVal[YYSTACKSIZE];
		yyval = new ParserVal();
		yylval = new ParserVal();
		valptr = -1;
	}

	void val_push(ParserVal val) {
		if (valptr >= YYSTACKSIZE)
			return;
		valstk[++valptr] = val;
	}

	ParserVal val_pop() {
		if (valptr < 0)
			return new ParserVal();
		return valstk[valptr--];
	}

	void val_drop(int cnt) {
		int ptr;
		ptr = valptr - cnt;
		if (ptr < 0)
			return;
		valptr = ptr;
	}

	ParserVal val_peek(int relative) {
		int ptr;
		ptr = valptr - relative;
		if (ptr < 0)
			return new ParserVal();
		return valstk[ptr];
	}

	final ParserVal dup_yyval(ParserVal val) {
		ParserVal dup = new ParserVal();
		dup.ival = val.ival;
		dup.dval = val.dval;
		dup.sval = val.sval;
		dup.nval = val.nval;
		dup.obj = val.obj;
		return dup;
	}

	// #### end semantic value section ####
	public final static short NUM = 257;
	public final static short VAR = 258;
	public final static short FUN = 259;
	public final static short NEG = 260;
	public final static short YYERRCODE = 256;
	final static short yylhs[] = { -1, 0, 0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, };
	final static short yylen[] = { 2, 0, 2, 1, 2, 1, 1, 3, 3, 3, 3, 2, 3, 4, 3, };
	final static short yydefred[] = { 1, 0, 5, 6, 0, 0, 3, 0, 0, 2, 0, 0, 0, 0,
			0, 0, 0, 0, 4, 0, 14, 0, 0, 0, 0, 0, 13, };
	final static short yydgoto[] = { 1, 8, 9, };
	final static short yysindex[] = { 0, -10, 0, 0, -38, -39, 0, -39, -6, 0,
			-39, -91, -34, -39, -39, -39, -39, -39, 0, -27, 0, -37, -37, -91,
			-91, -91, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 36, 48, 9, 21, 28, 0, };
	final static short yygindex[] = { 0, 85, 0, };
	final static int YYTABLESIZE = 249;
	static short yytable[];
	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 6, 7, 10, 17, 18, 15, 5, 20, 15, 14, 16, 13,
				11, 16, 26, 15, 14, 0, 13, 9, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7,
				10, 0, 0, 0, 5, 15, 14, 12, 13, 0, 16, 0, 11, 11, 11, 8, 11, 0,
				11, 9, 9, 9, 0, 9, 0, 9, 17, 7, 0, 17, 0, 10, 10, 10, 0, 10,
				17, 10, 12, 12, 12, 0, 12, 0, 12, 0, 8, 0, 8, 0, 8, 0, 0, 0, 0,
				0, 0, 17, 7, 11, 7, 12, 7, 0, 19, 0, 0, 21, 22, 23, 24, 25, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, };
	}

	static short yycheck[];
	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 10, 40, 40, 94, 10, 42, 45, 41, 42, 43, 47, 45,
				10, 47, 41, 42, 43, -1, 45, 10, 47, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, 40, 10, -1, -1, -1, 45, 42, 43, 10, 45, -1, 47, -1, 41,
				42, 43, 10, 45, -1, 47, 41, 42, 43, -1, 45, -1, 47, 94, 10, -1,
				94, -1, 41, 42, 43, -1, 45, 94, 47, 41, 42, 43, -1, 45, -1, 47,
				-1, 41, -1, 43, -1, 45, -1, -1, -1, -1, -1, -1, 94, 41, 5, 43,
				7, 45, -1, 10, -1, -1, 13, 14, 15, 16, 17, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 257,
				258, 259, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 257, 258,
				259, };
	}

	final static short YYFINAL = 1;
	final static short YYMAXTOKEN = 260;
	final static String yyname[] = { "end-of-file", null, null, null, null,
			null, null, null, null, null, "'\\n'", null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, "'('", "')'", "'*'", "'+'", null, "'-'", null,
			"'/'", null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, "'^'", null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, "NUM", "VAR", "FUN", "NEG", };
	final static String yyrule[] = { "$accept : input", "input :",
			"input : input line", "line : '\\n'", "line : expression '\\n'",
			"expression : NUM", "expression : VAR",
			"expression : expression '+' expression",
			"expression : expression '-' expression",
			"expression : expression '*' expression",
			"expression : expression '/' expression",
			"expression : '-' expression",
			"expression : expression '^' expression",
			"expression : FUN '(' expression ')'",
			"expression : '(' expression ')'", };

	// #line 79 "bjacc.y"

	String ins;
	StringTokenizer st;

	void yyerror(String s) {
		System.out.println("par:" + s);
	}

	boolean newline;

	int yylex() {
		String s;
		/* initialize to an error */
		int tok = -1;
		Double d;
		System.out.print("yylex ");
		if (!st.hasMoreTokens())
			if (!newline) {
				newline = true;
				return '\n'; // So we look like classic YACC example
			} else
				return 0;
		s = st.nextToken();
		System.out.println("tok:" + s);
		try {
			/* is it a number */
			d = Double.valueOf(s);/* this may fail */
			System.out.println("yyval is NUMNode");
			yylval = new ParserVal(new NUMNode(d.doubleValue())); // SEE BELOW
			tok = NUM;
		} catch (Exception e) {
			/* not a number, maybe a function */
			if (s.equalsIgnoreCase("sin") || s.equalsIgnoreCase("cos")) {
				// yyval.sval = (new ParserVal(s)).sval;
				yylval = (new ParserVal(s));
				tok = FUN;
			} else {
				/* not even a function, maybe variable */
				if (s.equalsIgnoreCase("x")) {
					yylval = new ParserVal(new VARNode("x"));
					tok = VAR;
				} else if (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("-")
						|| s.equalsIgnoreCase("*") || s.equalsIgnoreCase("/")
						|| s.equalsIgnoreCase("(") || s.equalsIgnoreCase(")")) {
					tok = s.charAt(0);
				}

				/* if not float, return char */
			}

		}
		System.out.println("tok returned: " + tok);
		return tok;
	}


	void dotest() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("BYACC/J + Math Expressions Parser");
		System.out.println("Note: Since this example uses the StringTokenizer");
		System.out.println("for simplicity, you will need to separate the items");
		System.out.println("with spaces, i.e.: '( 3 + 5 ) * 2'");
		System.out.print("expression:");
		try {
			ins = in.readLine();
		} catch (Exception e) {
			System.err.println("error :" + e);
		}
		st = new StringTokenizer(ins);
		newline = false;
		yyparse();

	}

	public static void main(String args[]) {
		Parser par = new Parser(false);
		par.dotest();
	}

	// #line 324 "Parser.java"
	// ###############################################################
	// method: yylexdebug : check lexer state
	// ###############################################################
	void yylexdebug(int state, int ch) {
		String s = null;
		if (ch < 0)
			ch = 0;
		if (ch <= YYMAXTOKEN) // check index bounds
			s = yyname[ch]; // now get it
		if (s == null)
			s = "illegal-symbol";
		debug("state " + state + ", reading " + ch + " (" + s + ")");
	}

	// The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

	// ###############################################################
	// method: yyparse : parse input and execute indicated items
	// ###############################################################
	int yyparse() {
		boolean doaction;
		init_stacks();
		yynerrs = 0;
		yyerrflag = 0;
		yychar = -1; // impossible char forces a read
		yystate = 0; // initial state
		state_push(yystate); // save it
		val_push(yylval); // save empty value
		while (true) // until parsing is done, either correctly, or w/error
		{
			doaction = true;
			if (yydebug)
				debug("loop");
			// #### NEXT ACTION (from reduction table)
			for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
				if (yydebug)
					debug("yyn:" + yyn + "  state:" + yystate + "  yychar:"
							+ yychar);
				if (yychar < 0) // we want a char?
				{
					yychar = yylex(); // get next token
					if (yydebug)
						debug(" next yychar:" + yychar);
					// #### ERROR CHECK ####
					if (yychar < 0) // it it didn't work/error
					{
						yychar = 0; // change it to default string (no -1!)
						if (yydebug)
							yylexdebug(yystate, yychar);
					}
				}// yychar<0
				yyn = yysindex[yystate]; // get amount to shift by (shift index)
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yychar) {
					if (yydebug)
						debug("state " + yystate + ", shifting to state "
								+ yytable[yyn]);
					// #### NEXT STATE ####
					yystate = yytable[yyn];// we are in a new state
					state_push(yystate); // save it
					val_push(yylval); // push our lval as the input for next
										// rule
					yychar = -1; // since we have 'eaten' a token, say we need
									// another
					if (yyerrflag > 0) // have we recovered an error?
						--yyerrflag; // give ourselves credit
					doaction = false; // but don't process yet
					break; // quit the yyn=0 loop
				}

				yyn = yyrindex[yystate]; // reduce
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yychar) { // we reduced!
					if (yydebug)
						debug("reduce");
					yyn = yytable[yyn];
					doaction = true; // get ready to execute
					break; // drop down to actions
				} else // ERROR RECOVERY
				{
					if (yyerrflag == 0) {
						yyerror("syntax error");
						yynerrs++;
					}
					if (yyerrflag < 3) // low error count?
					{
						yyerrflag = 3;
						while (true) // do until break
						{
							if (stateptr < 0) // check for under & overflow here
							{
								yyerror("stack underflow. aborting..."); // note
																			// lower
																			// case
																			// 's'
								return 1;
							}
							yyn = yysindex[state_peek(0)];
							if ((yyn != 0) && (yyn += YYERRCODE) >= 0
									&& yyn <= YYTABLESIZE
									&& yycheck[yyn] == YYERRCODE) {
								if (yydebug)
									debug("state "
											+ state_peek(0)
											+ ", error recovery shifting to state "
											+ yytable[yyn] + " ");
								yystate = yytable[yyn];
								state_push(yystate);
								val_push(yylval);
								doaction = false;
								break;
							} else {
								if (yydebug)
									debug("error recovery discarding state "
											+ state_peek(0) + " ");
								if (stateptr < 0) // check for under & overflow
													// here
								{
									yyerror("Stack underflow. aborting..."); // capital
																				// 'S'
									return 1;
								}
								state_pop();
								val_pop();
							}
						}
					} else // discard this token
					{
						if (yychar == 0)
							return 1; // yyabort
						if (yydebug) {
							yys = null;
							if (yychar <= YYMAXTOKEN)
								yys = yyname[yychar];
							if (yys == null)
								yys = "illegal-symbol";
							debug("state " + yystate
									+ ", error recovery discards token "
									+ yychar + " (" + yys + ")");
						}
						yychar = -1; // read another
					}
				}// end error recovery
			}// yyn=0 loop
			if (!doaction) // any reason not to proceed?
				continue; // skip action
			yym = yylen[yyn]; // get count of terminals on rhs
			if (yydebug)
				debug("state " + yystate + ", reducing " + yym + " by rule "
						+ yyn + " (" + yyrule[yyn] + ")");
			if (yym > 0) // if count of rhs not 'nil'
				yyval = val_peek(yym - 1); // get current semantic value
			yyval = dup_yyval(yyval); // duplicate yyval if ParserVal is used as
										// semantic value
			switch (yyn) {
			// ########## USER-SUPPLIED ACTIONS ##########
			case 4:
			// #line 36 "bjacc.y"
			{
				System.out.println(" " + val_peek(1).nval.getValue(3.0) + " ");
			}
				break;
			case 5:
			// #line 40 "bjacc.y"
			{
				yyval.nval = val_peek(0).nval;

			}
				break;
			case 6:
			// #line 41 "bjacc.y"
			{
				yyval.nval = val_peek(0).nval;
			}
				break;
			case 7:
			// #line 42 "bjacc.y"
			{
				/* Create addition binary operation node. */
				yyval.nval = (new ParserVal(new PLUSNode(val_peek(2).nval,
						val_peek(0).nval))).nval;

			}
				break;
			case 8:
			// #line 47 "bjacc.y"
			{
				yyval.nval = (new ParserVal(new MINUSNode(val_peek(2).nval,
						val_peek(0).nval))).nval;

			}
				break;
			case 9:
			// #line 51 "bjacc.y"
			{
				yyval.nval = (new ParserVal(new TIMESNode(val_peek(2).nval,
						val_peek(0).nval))).nval;

			}
				break;
			case 10:
			// #line 55 "bjacc.y"
			{
				yyval.nval = (new ParserVal(new DIVIDENode(val_peek(2).nval,
						val_peek(0).nval))).nval;

			}
				break;
			case 11:
			// #line 59 "bjacc.y"
			{
				yyval.nval = (new ParserVal(new MINUSNode(new NUMNode(2),
						val_peek(0).nval))).nval;

			}
				break;
			case 12:
			// #line 63 "bjacc.y"
			{
				/* Create exponentiation unary operation node. */

			}
				break;
			case 13:
			// #line 67 "bjacc.y"
			{
		//		yyval.nval = (new ParserVal(new Function(val_peek(3).sval,	val_peek(1).nval))).nval;
		//works:		yyval.nval = new Function(val_peek(3).sval,	val_peek(1).nval);
				yyval.nval = Function.createFunction(val_peek(3).sval,	val_peek(1).nval);
				System.out.println("here is your input: "
						+ yyval.nval.getClass());

			}
				break;
			case 14:
			// #line 71 "bjacc.y"
			{
				yyval.nval = val_peek(1).nval;
			}
				break;
			// #line 541 "Parser.java"
			// ########## END OF USER-SUPPLIED ACTIONS ##########
			}// switch
				// #### Now let's reduce... ####
			if (yydebug)
				debug("reduce");
			state_drop(yym); // we just reduced yylen states
			yystate = state_peek(0); // get new state
			val_drop(yym); // corresponding value drop
			yym = yylhs[yyn]; // select next TERMINAL(on lhs)
			if (yystate == 0 && yym == 0)// done? 'rest' state and at first
											// TERMINAL
			{
				if (yydebug)
					debug("After reduction, shifting from state 0 to state "
							+ YYFINAL + "");
				yystate = YYFINAL; // explicitly say we're done
				state_push(YYFINAL); // and save it
				//result = yyval.nval;
				val_push(yyval); // also save the semantic value of parsing
				if (yychar < 0) // we want another character?
				{
					yychar = yylex(); // get next character
					if (yychar < 0)
						yychar = 0; // clean, if necessary
					if (yydebug)
						yylexdebug(yystate, yychar);
				}
				if (yychar == 0) // Good exit (if lex returns 0 ;-)
					break; // quit the loop--all DONE
			}// if yystate
			else // else not done yet
			{ // get next state and push, for next yydefred[]
				yyn = yygindex[yym]; // find out where to go
				if ((yyn != 0) && (yyn += yystate) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yystate)
					yystate = yytable[yyn]; // get new state
				else
					yystate = yydgoto[yym]; // else go to new defred
				if (yydebug)
					debug("after reduction, shifting from state "
							+ state_peek(0) + " to state " + yystate + "");
				state_push(yystate); // going again, so push state & val...
				result = yyval.nval;
				val_push(yyval); // for next action
			}
		}// main loop
		
		
		if(result == null){
			System.out.println("result was null " + yyval);
	
		}
		return 0;// yyaccept!!
	}

	// ## end of method parse() ######################################

	// ## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}

	// ## end of method run() ########################################

	// ## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 */
	public Parser() {
		dotest();
		// nothing to do
	}
	
	public Parser(String exp) {
		BufferedReader in = new BufferedReader(new StringReader(exp));
		try {
			ins = in.readLine();
		} catch (Exception e) {
			System.err.println("error :" + e);
		}
		st = new StringTokenizer(ins);
		newline = false;
		yyparse();
	}

	/**
	 * Create a parser, setting the debug to true or false.
	 * 
	 * @param debugMe
	 *            true for debugging, false for no debug.
	 */
	public Parser(boolean debugMe) {
		yydebug = debugMe;
	}
	// ###############################################################

	public Node getResult(){
		return result;
	}
}
// ################### END OF CLASS ##############################
