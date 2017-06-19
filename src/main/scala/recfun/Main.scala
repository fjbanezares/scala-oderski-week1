package recfun

object Main {


  /* The following pattern of numbers is called Pascal’s triangle.
       1
      1 1
     1 2 1
    1 3 3 1
   1 4 6 4 1
  ...
  The numbers at the edge of the triangle are all 1, and each number inside the triangle is the sum of the two numbers above it.
  Write a function that computes the elements of Pascal’s triangle by means of a recursive process.

  Do this exercise by implementing the pascal function in Main.scala, which takes a column c and a row r,
  counting from 0 and returns the number at that spot in the triangle.
  For example, pascal(0,2)=1,pascal(1,2)=2 and pascal(1,3)=3.

  */
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int =
    if (c == 0) 1
    else if (c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  //Alternativamente usando case en vez de if
  //faltaría lógica en caso de que se dieran puntos fuera del triángulo, aquí se ponen a 1 pero se debe tratar una excepción

  def pascalCase(c: Int, r: Int): Int = c match {
    case 0 => 1
    case c if c >= r => 1
    case _ => pascalCase(c - 1, r - 1) + pascalCase(c, r - 1)
  }


  /**
    * Exercise 2
    *
    *
    * Write a recursive function which verifies the balancing of parentheses in a string,
    * which we represent as a List[Char] not a String. For example, the function should return true for the following strings:
    * *
    * (if (zero? x) max (/ 1 x))
    * I told him (that it’s not (yet) done). (But he wasn’t listening)
    * The function should return false for the following strings:
    * *
    * :-)
    * ())(
    * *
    *
    * The last example shows that it’s not enough to verify that a string contains the same number of opening and closing parentheses.
    * *
    * Do this exercise by implementing the balance function in Main.scala. Its signature is as follows:
    * *
    * def balance(chars: List[Char]): Boolean
    * *
    * There are three methods on List[Char] that are useful for this exercise:
    * *
    *chars.isEmpty: Boolean returns whether a list is empty
    *chars.head: Char returns the first element of the list
    *chars.tail: List[Char] returns the list without the first element
    * Hint: you can define an inner function if you need to pass extra parameters to your function.
    * *
    * Testing: You can use the toList method to convert from a String to aList[Char]: e.g. "(just an) example".toList.
    *
    *
    */
  def balance(chars: List[Char]): Boolean = {

    def loopy(charsLoop: List[Char], add: Int): Boolean = {

      // val abroParentesis: Char = '('
      // val cierroParentesis: Char = ')'
      if (charsLoop.isEmpty) {
        if (add == 0) true
        else false
      }

      else {


        val cabeza: Char = charsLoop.head

        /** if (cabeza.equals(')')) {
          *
          * funciona bien con == o con .equals
          * Lo importante es poner la comilla simple
          * para que Scala no piense que nos referimos a un String
          *
          * */
        if (cabeza == ')') {
          if (add == 0) false
          else loopy(charsLoop.tail, add - 1)
        }


        else if (cabeza == '(') loopy(charsLoop.tail, add + 1)
        else loopy(charsLoop.tail, add)
      }

    }

    loopy(chars, 0)

  }

  /**
    * Exercise 3
    *
    * Write a recursive function that counts how many different ways you can make change for an amount,
    * given a list of coin denominations. For example,
    *
    * there are 3 ways to give change for 4 if you have coins with denomination 1 and 2: 1+1+1+1, 1+1+2, 2+2.
    *
    *
    * Do this exercise by implementing the countChange function inMain.scala.
    *
    * This function takes an amount to change, and a list of unique denominations for the coins. Its signature is as follows:
    *
    * def countChange(money: Int, coins: List[Int]): Int
    *
    * Once again, you can make use of functions isEmpty, head and tail on the list of integers coins.
    *
    *
    * Hint: Think of the degenerate cases. How many ways can you give change for 0 CHF(swiss money)?
    *
    * How many ways can you give change for >0 CHF, if you have no coins?
    *
    *
    */
  def countChange(money: Int, coins: List[Int]): Int = {

    /**
      *
      * Si no tenemos dinero que cambiar no hay nada que cambiar
      * Si no tenemos monedas no hay forma de cambiar
      * Para todo lo demás, si la cantidad a cambiar es múltiplo de alguna de las monedas, se habrá una combinación para esa moneda
      * En general la cantidad será una combinación lineal de los distintos tipos de monedas con:
      *
      * C = a C1 + b C2 + c C3
      *
      * Los diferentes a, b, c que resuelvan esta ecuación serán las combinaciones que nos interese
      *
      * En general podríamos iterar a,b,c y aumentar un contador por cada combinación que nos de el resultado, limitando entre
      * 0 y la función suelo de C/Ci, que en el casi de que sea múltiple será el procio C/Ci
      *
      * Sería una opción, aquí se nos pide aplicar recursividad
      *
      **/
    /*def loopCount(money: Int, coinsLoop: List[Int]): Int = {

      //if (money == 0) 0 realmente sería una que es a,b,c... igual a cero

      // else if (coinsLoop.isEmpty) 0

      //else 9


    }

    loopCount(money, coins)
  }*/

  if (money < 0 || coins.isEmpty) 0
  else if (money == 0) 1
    /**copie la idea a dwins
      *
      * básicamente va iterando de una forma ordenada, repite una moneda cuántas veces sea necesario,
      * si se llega a que nos pasamos o nos quedamos sin monedas no sumamos nada, pero por cada hit sumamos 1
      * es increiblemente elegante cuándo lo ves así
      *
      * */
  else countChange(money - coins.head, coins) + countChange(money, coins.tail)

    /** ver cómo hacerlo en paralelo
      *
      * https://github.com/TomLous/coursera-parallel-programming-scala/blob/master/src/main/scala/reductions/ParallelCountChange.scala
      * 
      */

  }
}
