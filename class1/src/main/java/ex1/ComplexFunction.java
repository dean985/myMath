

package ex1;

import java.util.ArrayList;

import static ex1.Operation.*;

/**
 * ComplexFunction
 */
public class ComplexFunction implements complex_function{
    Operation op ;
    function left;
    function right;

    public ComplexFunction(){
        ;
    }

    /**
     *
     * @param string
     * @param left
     * @param right
     *
     */

    public ComplexFunction(String string, function left, function right) throws Exception {
        // Understanding which operation is in input
        Operation op;
        string = string.toLowerCase();
       
        if (string.equals("plus")){
            this.op = Plus;
        }
        else if (string.equals("times")  || string.equals("mul")){
            this.op = Times;
        }
        else if (string.equals("divide" ) || string.equals("div")){
            this.op = Divide;
        }
        else if (string.equals("max")){
            this.op = Max;
        }
        else if (string.equals("min")){
            this.op = Min;
        }
        else if (string.equals("comp")){
            this.op = Comp;
        } else if (string.equals("none") || string.equals("None")){
            this.op = None;
        }else {
            this.op = Error;
            throw new Exception("Wrong input for operation");
        }


        if (right != null){
            this.right = right;
        }
        if (left != null){
            this.left = left;
        }


	}

	public ComplexFunction (Operation o, function left, function right){
        this.op = o;
        if (right != null){
            this.right = right;
        }
        if (left != null){
            this.left = left;
        }
    }

	public ComplexFunction(function left){
        this.left = left;
        this.op = None;
    }


	@Override
    public double f(double x) {
        switch (this.op){
            case Plus: return this.left.f(x) + this.right.f(x);
            case Times: return this.left.f(x) * this.right.f(x);
            case Divide:
                if (this.right.f(x) == 0){
                    throw new RuntimeException("Right argument is zero, aborted");
                }else{
                    return this.left.f(x) / this.right.f(x);
                }
            case Max: return Math.max(this.left.f(x), this.right.f(x));
            case Min: return Math.min(this.left.f(x), this.right.f(x));
            case Comp: return this.left.f(this.right.f(x));
            case None: return this.left.f(x);

        }
        /*if(this.op == Plus){
            return this.left.f(x) + this.right.f(x);
        }else if( this.op == Times) {
            return this.left.f(x) * this.right.f(x);
        }else if (this.op == Divide){
            if (this.right.f(x) == 0){
                throw new RuntimeException("Right argument is zero, aborted");
            }else{
                return this.left.f(x) / this.right.f(x);
            }
        }else if (this.op == Max){
            return Math.max(this.left.f(x), this.right.f(x));
        }else if (this.op == Min){
            return Math.min(this.left.f(x), this.right.f(x));
        } else if (this.op == Comp){
            return this.left.f(this.right.f(x));
        }*/


        return 0;
    }

    public boolean isOperator(String s){
        if(s.equals("plus")  || s.equals("mul") || s.equals("div") || s.equals("max") ||
                s.equals("min") || s.equals("comp") || s.equals("none")){
            return true;
        }
        return false;
    }

    /**
     * This function splits the expression into two elements
     * Example op(p1,op(p2,p3))   will return index 5 of the string
     * @param s - complexFunction string
     * @return split point
     */
    public int splitExp (String s, int t) {
        int par=0;              //parenthesis
        int sp=0;               //split point
        int com=0;              // commas ','

        while(t != s.length()) {
            if(s.charAt(t)=='(') {
                par++;
            }
            if(s.charAt(t)==',') {
                com++;
            }
            if(com==par && s.charAt(t) == ',') {
                sp=t;
                return sp;
            }
            t++;
        }
        return sp;
    }

    private Operation operatorIdentifier (String s){
        Operation op ;
        if (s.equals("plus")){
            op = Plus;
        }
        if (s.equals("mul")){
            op = Times;
        }
        if (s.equals("div")){
            op = Divide;
        }
        if (s.equals("max")){
            op = Max;
        }
        if (s.equals("min")){
            op = Min;
        }
        if (s.equals("comp")){
            op = Comp;
        }
        if (s.equals("none")){
            op = None;
        }
        else{
            op = Error;
        }
        return op;
    }

    @Override
    public function initFromString(String s) {

        s.replaceAll("\\s+","");
       // if there are no '(' or ')'
        if (s.indexOf('(') == -1 && s.indexOf(')') == -1){
            function f = new Polynom(s);
            return f;
        }
        else {


            // splitting the string
            int firstParen =s.indexOf('(');
            int sp = splitExp(s,firstParen);

            // extracting the operation from a string
            String op = s.substring(0,firstParen);

            // recursive call for each splitted string
            function left = initFromString( s.substring(firstParen+1, sp));
            function right = initFromString( s.substring(sp+1, s.length()-1));

            function f = new ComplexFunction(op, left, right);
            return f;
            }
        }



    @Override
    public String toString(){

        String res="";

        if (this.op != Operation.None){
            if (this.op == Operation.Plus ) res+="plus(";
            if (this.op == Operation.Times ) res+="mul(";
            if (this.op == Operation.Divide ) res+="div(";
            if (this.op == Operation.Max ) res+="max(";
            if (this.op == Operation.Min ) res+="min(";
            if (this.op == Operation.Comp) res+="comp(";
        }

        if(this.left!=null) {
            res+=this.left.toString();
        }
        if(this.right!=null) {
            res+=",";
            res+=this.right.toString();
            if (this.op != None && this.op !=Error){
                res+=")";
            }
        }
        return res;
    }

    //@Override
    public function copy() {
        function f = new ComplexFunction(this.getOp(), this.left, this.right);
        return f;
    }

    @Override
    public void plus(function f1) {
        // This method encapsulate 'this' cf inside the left function of itself
        // Puts f1 as the right function
        // changes the op to Plus
        // This solution will repeate with small changes throughout this class
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Plus;
    }

    @Override
    public void mul(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op, this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Times;
    }

    @Override
    public void div(function f1) {
        if (f1 != null && this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op, this.left, this.right);
            this.left = cf1;
            this.right = f1;
            this.op = Divide;
        }else{
            throw new RuntimeException("Can't divide by zero");
        }

    }

    @Override
    public void max(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Max;
    }

    @Override
    public void min(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Min;

    }

    @Override
    public void comp(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Comp;

    }

    @Override
    public function left() {
        return this.left;
    }

    @Override
    public function right() {
        return this.right;
    }

    @Override
    public Operation getOp() {
        return this.op;
    }

    
}