package spire.optional

import spire.algebra.{Semigroup, Semigroupoid}

object optionSemigroup {
  trait OptionSemigroup[A] extends Any with Semigroup[Option[A]] {
    implicit def partial: Semigroupoid[A]
    def op(xOpt: Option[A], yOpt: Option[A]) = xOpt match {
      case Some(x) => yOpt match {
        case Some(y) => partial.partialOp(x, y)
        case None => None
      }
      case None => None
    }
  }

  implicit def optionSemigroup[A: Semigroupoid]: Semigroup[Option[A]] = new OptionSemigroup[A] {
    def partial = implicitly[Semigroupoid[A]]
  }
}
