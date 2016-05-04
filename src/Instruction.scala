/**
  * Created by Gabriel on 04/05/2016.
  */
trait Instruction{
  def execute(cpu: CPU)
  def actualizarAcumuladores(cpu: CPU, res : Int): Unit ={
    if(Math.abs(res) < 128){
      cpu.b = res
      cpu.a = 0
    }
    else{
      cpu.b = 127
      cpu.a = res - 127
    }
  }
}

trait MemoryAccessor extends Instruction{
  def validateAddress(addr : Int): Unit ={
    if(addr > 1023){
      throw new IllegalArgumentException
    }
  }
}

class ADD extends Instruction{
  def execute(cpu: CPU): Unit ={
    val res = cpu.a + cpu.b
    this.actualizarAcumuladores(cpu, res)
  }
}

class NOP extends Instruction{
  override def execute(cpu: CPU): Unit ={
    cpu.pc += 1
  }
}

class SUB extends Instruction{
  override def execute(cpu: CPU): Unit = {
    val res = cpu.b - cpu.a
    this.actualizarAcumuladores(cpu, res)
  }
}

class DIV extends Instruction{
  override def execute(cpu: CPU): Unit = {
    if(cpu.a == 0){
      throw new IllegalArgumentException
    }
    val res = cpu.b / cpu.a
    this.actualizarAcumuladores(cpu, res)
  }
}

class SWAP extends Instruction{
  override def execute(cpu: CPU): Unit = {
    val temp = cpu.a
    cpu.a = cpu.b
    cpu.b = temp
  }
}

class LOD(val addr : Int) extends MemoryAccessor{
  def execute(cpu: CPU): Unit ={
    this.validateAddress(addr)
    cpu.a = cpu.memory(addr)
  }
}

class STR(val addr: Int) extends MemoryAccessor{
  override def execute(cpu: CPU): Unit = {
    this.validateAddress(addr)
    cpu.a = cpu.memory(addr)
  }
}

class LODV(val value : Int) extends Instruction{
  override def execute(cpu: CPU): Unit = {
    cpu.a = value
  }
}