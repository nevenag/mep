%{
package com.nevenagolubovic.parser;
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import com.nevenagolubovic.node.*;

import com.nevenagolubovic.node.DIVIDENode;
import com.nevenagolubovic.node.EXPONNode;
import com.nevenagolubovic.node.MINUSNode;
import com.nevenagolubovic.node.NUMNode;
import com.nevenagolubovic.node.Node;
import com.nevenagolubovic.node.PLUSNode;
import com.nevenagolubovic.node.TIMESNode;
import com.nevenagolubovic.node.Function;
%}

/* YACC Declarations */
%token <obj> NUM
%token <obj> VAR
%token <obj> FUN
%left '-' '+'
%left '*' '/'
%left NEG /* negation--unary minus */
%right '^' /* exponentiation */

%type <node> expression

/* Grammar follows */
%%
input: /* empty string */
 | input line
 ;

line: '\n'
 | expression '\n' { System.out.println(" " + $1 + " "); }
 ;
 
 expression
: NUM {$$ = $1;}
| VAR {$$ = $1;}
| expression '+' expression {
        /* Create addition binary operation node.  */
        $$ = new ParserVal( new PLUSNode($1, $3));
        
}
| expression '-' expression {
        $$ = new ParserVal( new MINUSNode($1, $3));
        
}
| expression '*' expression {
       $$ = new ParserVal( new TIMESNode($1, $3));
        
}
| expression '/' expression {
        $$ = new ParserVal( new DIVIDENode($1, $3));
         
}
| '-' expression %prec NEG {
        $$ = new ParserVal( new MINUSNode(new NUMNode(0), $2));
         
}
| expression '^' expression {
        /* Create exponentiation unary operation node.  */
        
}
| FUN '(' expression ')' {
        $$ = new ParserVal( new Function($1, $3));
       
}
| '(' expression ')' {
         $$ = $2;	
}
;
 
 
 
%%

String ins;
StringTokenizer st;

void yyerror(String s)
{
 System.out.println("par:"+s);
}

boolean newline;
	int yylex() {
		String s;
		/*initialize to an error*/
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
		System.out.println("tok:"+s);
		try {
			/* is it a number */
			d = Double.valueOf(s);/* this may fail */
			System.out.println("yyval is NUMNode");
			yylval = new ParserVal(new NUMNode(d.doubleValue())); // SEE BELOW
			tok = NUM;
		} catch (Exception e) {
			/*not a number, maybe a function */
			if(s.equalsIgnoreCase("sin") || s.equalsIgnoreCase("cos")){
				yyval = new ParserVal(s);
				tok = FUN;
			}else{
			/* not even a function, maybe variable */
				if(s.equalsIgnoreCase("x")){
					yyval = new ParserVal(new VARNode("x"));
					tok = VAR;
				}
				else if(s.equalsIgnoreCase("+") ||
							s.equalsIgnoreCase("-") ||
							s.equalsIgnoreCase("*") ||
							s.equalsIgnoreCase("/") ||
							s.equalsIgnoreCase("(") ||
							s.equalsIgnoreCase(")")){
						tok = s.charAt(0);
				}
					
				
			/* if not float, return char */
			}
			
		}
		System.out.println("tok returned: " + tok);
		return tok;
	}
	
	  
void dotest()
{
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 	System.out.println("with spaces, i.e.: '( 3 + 5 ) * 2'");
 	while (true)
 	{
 		System.out.print("expression:");
 		try
 		{
 			ins = in.readLine();
 		}catch (Exception e){
 		 System.err.println("error :"+e);
 		}
 		st = new StringTokenizer(ins);
 		newline=false;
 		yyparse();
 	}
}

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.dotest();
}
   

