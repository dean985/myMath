# polynom
## Java library working with polynomial.

  the library contain the classes `Monom`,`Polynom`.

  the interface `Polynom_able`

  and the testers `Monomtest`, `Polynomtest`.

### Monom class:

#### Constructors:

1) `Monom(double a, int b)` - Creates Monom with coefficent .

2) `Monom(String s)` - Creates polynomial from String. F.E. s = "6x^2"

#### Methods:

1) `Monom getNewZeroMonom()` - the function return a monom with zero value
2) `double get_coefficient()` - the function return a double value of the coefficient
3) `int get_power()` - the function return a int value of the power

4) `Comparator<Monom> getComp()` - comperator function, return the comperator value from the comperator class.

5) `void set_coefficient(double a)` - set the _coefficient in the monom with the given value a.

6) `void set_power(int p)` - set the _power in the monom with the given value p.

7) `Monom derivative()` - this method returns the derivative monom of this.

8) `double f(double x)` - Return the value of the monom at a specific value of x

9) `boolean isZero()` - return if the coeffcient == 0 then return true and set power to 0

10) `boolean equals(Monom m)` -   A method to determine whether two monoms are equal The coefficient comparison is being applies using EPSILON. Two coefficients are considered equal when their difference is less or equal to EPSILON

11) `void add(Monom m)` - add the recived monom to the current monom if the power is diffrent throw exaption

12) `void multiply(Monom d)` - Multiply two monoms. coefficient and power are being multiplied seperatly.

13) `String toString()` - Creates a human-readable string of a monom.




### Polynom class:

#### Constructors:

1) `Polynom()` - Creates zearo monom which defined as "0x^0".

2) `Polynom(String s)` - Creates polynomial from String. F.E. s = "x^2+2x+3"

#### Methods:

1) `double f(double x)` - return the y valu of the polynom for given x value.

2) `void add(Polynom_able p1)` - add the given polynom to the current polynom object, the answer get it to the currnt polynom object.

3) `void add(Monom m1)` - add the given Monom to the current polynom.

4) `void substract(Polynom_able p1)` - substract the given polynom_able with the current polynom the answer it put in the current polynom object.

5) `void multiply(Polynom_able p1)` - multiply the given object polynom_able with the current polynom the answer get in to the current polynom object

6) `void multiply(Monom m1)` - multiply the given object monom with the current polynom the answer get in to the current polynom object

7) `boolean isZero()` - return true when the polynom is zero which mean it contain zero Monom.

8) `double root(double x0, double x1, double eps)` - return the root value of function btween the x0 and the x1 in precision of epsilon.

9) `Polynom_able copy()` - return a deep copy of the calling polynom object

10) `Polynom_able derivative()` - return a Polynom_able derivative of the calling polynom object

11) `double area(double x0, double x1, double eps)` - return the area in the called polynom between x0 and x1 in precision of epsilon.

12) `Iterator<Monom> iteretor()` - an Iterator for the polynom terms. 

13) `boolean equals(Polynom_able p1)` - return true when the current polynom object is equle to the the given polynom_able object else return false.


14) `String toString()` - string the polynom terms.


