package com.github.stenoritama.chap01


class CreditCard {
  def charge(price: Int): Unit = {
    ???
  }
}

 class Coffee {
   val price: Int = ???
 }

class Payment {
  def charge(cc: CreditCard, price: Int): Unit = {
    ???
  }
}

class Cafe {

  def buyCoffee(cc: CreditCard, p: Payment): Coffee = {
    val cup = new Coffee()
    p.charge(cc, cup.price)
    cup
  }

}

case class Charge(cc: CreditCard, amount: Double) {
  def combine(other: Charge): Charge = {
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charges to different cards.")
  }
}

class FCafe {

  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee()
    (cup, Charge(cc, cup.price))
  }

  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees, charges) = purchases.unzip
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  def coalesce(charges: List[Charge]): List[Charge] = {
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
  }

}
