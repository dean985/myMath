package Ex1;

/**
 * Ex1.ComplexFunction
 */

public class ComplexFunction implements complex_function{
                    //////////////////////////////////////////////////////////////////////////////////////////
                    ///////////////////////////////////////Fields/////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////

    Operation op ;
    function left;
    function right;
                    //////////////////////////////////////////////////////////////////////////////////////////
                    ///////////////////////////////////////Constructors///////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////

    public ComplexFunction(){
        this.left = null;
        this.right = null;
        this.op = null;
    }

    /**
     *
     * @param string
     * @param left
     * @param right
     *
     */

    public ComplexFunction(String string, function left, function right) {
        // Understanding which operation is in input
        Operation op;
        string = string.toLowerCase();

        if (string.equals("plus")){
            this.op = Operation.Plus;
        }
        else if (string.equals("times")  || string.equals("mul")){
            this.op = Operation.Times;
        }
        else if (string.equals("divide" ) || string.equals("div")){
            this.op = Operation.Divid;
        }
        else if (string.equals("max")){
            this.op = Operation.Max;
        }
        else if (string.equals("min")){
            this.op = Operation.Min;
        }
        else if (string.equals("comp")){
            this.op = Operation.Comp;
        } else if (string.equals("none") || string.equals("None")){
            this.op = Operation.None;
        }else {
            this.op = Operation.Error;
        //    throw new Exception("Wrong input for operation");
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
        this.op = Operation.None;
    }

                    //////////////////////////////////////////////////////////////////////////////////////////
                    ///////////////////////////////////////Methods////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////

    /***
     * A method to give value x to Ex1.ComplexFunction
     * @param x
     * @return double - value of the Ex1.function at point x
     */
    @Override
    public double f(double x) {
        switch (this.op){
            case Plus: return this.left.f(x) + this.right.f(x);
            case Times: return this.left.f(x) * this.right.f(x);
            case Divid:
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

    /*public boolean isOperator(String s){
        if(s.equals("plus")  || s.equals("mul") || s.equals("div") || s.equals("max") ||
                s.equals("min") || s.equals("comp") || s.equals("none")){
            return true;
        }
        return false;
    }*/

    /**
     * This Ex1.function splits the expression into two elements
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

    /*private Ex1.Operation operatorIdentifier (String s){
        Ex1.Operation op ;
        if (s.equals("plus")){
            op = Ex1.Operation.Plus;
        }
        if (s.equals("mul")){
            op = Ex1.Operation.Times;
        }
        if (s.equals("div")){
            op = Ex1.Operation.Divide;
        }
        if (s.equals("max")){
            op = Ex1.Operation.Max;
        }
        if (s.equals("min")){
            op = Ex1.Operation.Min;
        }
        if (s.equals("comp")){
            op = Ex1.Operation.Comp;
        }
        if (s.equals("none")){
            op = Ex1.Operation.None;
        }
        else{
            op = Ex1.Operation.Error;
        }
        return op;
    }*/

    /**
     * Method to transform a string from the form of "  mul(x,x+2)   " to the form of Ex1.ComplexFunction
     *
     * @param s
     * @return Ex1.function
     */
    @Override
    public function initFromString(String s)  {

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

            function f = null;

                f = new ComplexFunction(op, left, right);

            return f;
            }
        }


    /**
     * Inverse Ex1.function of initFromString
     * Transforms Ex1.ComplexFunction to String
     * @return String
     */
    @Override
    public String toString(){

        String res="";

        if (this.op != Operation.None){
            if (this.op == Operation.Plus ) res+="plus(";
            if (this.op == Operation.Times ) res+="mul(";
            if (this.op == Operation.Divid ) res+="div(";
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
            if (this.op != Operation.None && this.op !=Operation.Error){
                res+=")";
            }
        }
        return res;
    }

    /**
     * Makes deep-copy of Ex1.ComplexFunction
     * @return Ex1.function
     */
    //@Override
    public function copy() {
        function f = new ComplexFunction(this.getOp(), this.left, this.right);
        return f;
    }

    /**
     * Adds f1 to this ComplexFuntion
     * @param f1 the Ex1.function which will be added to this Ex1.ComplexFunction.
     */
    @Override
    public void plus(function f1) {
        // This method encapsulate 'this' cf inside the left Ex1.function of itself
        // Puts f1 as the right Ex1.function
        // changes the op to Plus
        // This solution will repeate with small changes throughout this class
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Operation.Plus;
    }

    /**
     * Multiplies f1 and this Ex1.ComplexFunction
     * @param f1 the Ex1.function which will be multuplied to this Ex1.ComplexFunction.
     */
    @Override
    public void mul(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op, this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Operation.Times;
    }

    /**
     * Divides this ComplexFunctino by Ex1.function f1
     * @param f1 the Ex1.function which will divide this Ex1.complex_function.
     */
    @Override
    public void div(function f1) {
        if (f1 != null && this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op, this.left, this.right);
            this.left = cf1;
            this.right = f1;
            this.op = Operation.Divid;
        }else{
            throw new RuntimeException("Can't divide by zero");
        }

    }

    /**
     * Maximum method for this complex Ex1.function and a Ex1.function
     * @param f1 the Ex1.function  which will be compared with this Ex1.ComplexFunction - to compute the maximum.
     */
    @Override
    public void max(function f1)  {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Operation.Max;
    }

    /**
     * Minimum method for this complex Ex1.function and a Ex1.function
     * @param f1 the Ex1.function  which will be compared with this Ex1.ComplexFunction - to compute the minimum.
     */
    @Override
    public void min(function f1) {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Operation.Min;

    }

    /**
     * This method wrap the f1 Ex1.complex_function with this Ex1.function: this.f(f1(x))
     * @param f1 complex Ex1.function
     */
    @Override
    public void comp(function f1)  {
        if (this.left != null){
            ComplexFunction cf1 = new ComplexFunction(this.op.toString(), this.left, this.right);
            this.left = cf1;
        }
        this.right = f1;
        this.op = Operation.Comp;

    }

    /**
     * This method checks that two Ex1.function FROM TYPE complexFunction are equal by their value
     * @param o - Must be from type Ex1.ComplexFunction
     * @return True or False
     */
    @Override
    public boolean equals (Object o){
        boolean eq = false;
        if (o instanceof ComplexFunction){
            for(int k = 0; k<10; k++){
                double diff = 100000*Math.random();
                double eps = 0.00001;
                for (double m = diff; m< diff+2; m+=eps){
                    if (Math.abs(this.f(diff) - ((ComplexFunction) o).f(diff))  <= eps) {
                        eq = true;
                    }
                    else {
                        eq = false;
                    }
                    return eq;

                }
            }
        }
        return false;
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////Getters////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
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