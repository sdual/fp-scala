package com.github.stenoritama.chap04

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
  def getOrElse[B >: A](default: => B): B
  def 
}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]
