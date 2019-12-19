package multipleResources

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.data.EitherT
import cats.implicits._

object MultipleResources {

  case class Target()

  def fetch(): Option[Target] = {

    def fetchFromCache(): Option[Target]    = ???
    def fetchFromDataBase(): Option[Target] = ???

    val a: Either[Target, Unit] = for {
      _ <- fetchFromCache().toLeft(())
      _ <- fetchFromDataBase().toLeft(())
    } yield ()
    a.swap.toOption

  }

  def fetchAsync(): Future[Option[Target]] = {

    def fetchFromCacheAsync(): Future[Option[Target]]    = ???
    def fetchFromDatabaseAsync(): Future[Option[Target]] = ???

    val a: EitherT[Future, Target, Unit] = for {
      _ <- EitherT(fetchFromCacheAsync().map(_.toLeft(())))
      _ <- EitherT(fetchFromDatabaseAsync().map(_.toLeft(())))
    } yield ()
    val b: Future[Either[Target, Unit]] = a.value
    b.map(_.swap.toOption)

  }

}
