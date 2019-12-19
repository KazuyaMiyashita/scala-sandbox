package multipleResources

import cats.Monad
import cats.data.EitherT
import cats.implicits._

case class Target(name: String)

trait Repository[F[_]] {
  def fetch(): F[Option[Target]]
}

class MultipleResources[F[_]: Monad](
    cache: Repository[F],
    database: Repository[F]
) {

  def fetch(): F[Option[Target]] = {

    val a: EitherT[F, Target, Unit] = for {
      _ <- EitherT(cache.fetch().map(_.toLeft(())))
      _ <- EitherT(database.fetch().map(_.toLeft(())))
    } yield ()
    val b: F[Either[Target, Unit]] = a.value
    b.map(_.swap.toOption)

  }

}
