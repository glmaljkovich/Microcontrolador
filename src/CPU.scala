/**
  * Created by Gabriel on 04/05/2016.
  */

class CPU(var memory: Array[Int], var a: Int, var b: Int, var pc: Int) {
  def eraseMemory(): Unit ={
    memory = new Array[Int](1024)
  }

  def load(program: Program): Unit ={
    var i = 0
    for(elem <- program.memory){
      memory(i) = elem
      i += 1
    }
  }

  def start(program: Program): Unit ={
    eraseMemory()
    load(program)
    for(instruction <- program.instructions){
      instruction.execute(this)
      pc += 1
    }
  }

  def execute(program: Program): Unit ={
    program.instructions(pc).execute(this)
    pc += 1
  }

  def stop(){}

}




