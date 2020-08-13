package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // EXPRESSION

  println(2 + 3 * 4)
  // Math expressions ^^^
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // logical operators ! && ||

  var aVariable = 2 // a side effect
  aVariable += 3 // also works with -= *= /=


  // Instructions (Do) vs Expressions (Value)
  // instructions are executed (Java), expressions are evaluated (Scala)

  //  IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // if expression not an instruction, bc it returns a value
  println(aConditionedValue)
  print(if(aCondition) 5 else 3)

  var i = 85
  while (i < 88) {
    println(i)
    i += 1
  }
  // Never write this again! ^^^ avoid while loop

  val aWeirdValue = (aVariable = 3)
  println(aWeirdValue)

  // side effects are the only expressions returning unit
  // a unit can only have value ()

  // side effects: println(), whiles, reassigning (they are like instructions)

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // value of a block is the value of the last expression inside the code block

}
