package com.github.stenoritama.chap04

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
  def getOrElse[B >: A](default: => B): B
  def orElse[B >: A](ob: => Option[B]): Option[B]
  def filter(f: A => Boolean): Option[A]
}

trait Helper {
  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f
}
