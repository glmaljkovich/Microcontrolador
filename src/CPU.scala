/**
  * Created by Gabriel on 04/05/2016.
  */
class CPU(var memory: Array[Instruction], var a: Int, var b: Int, var pc: Int) {

}

trait Instruction{
  def execute()
}
