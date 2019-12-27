package mycats.freee

import org.scalatest._

class MyFreeSpec extends FunSuite with Matchers {

  test("a") {

    case class User(id: Int, name: String)

    object SimpleUserRepository {
      import cats.free.Free

      sealed trait Action[A]
      final case class Get(id: Int)    extends Action[User]
      final case class Put(user: User) extends Action[Unit]

      type SimpleUserRepository[A] = Free[Action, A]

      def get(id: Int): SimpleUserRepository[User]    = Free.liftF(Get(id))
      def put(user: User): SimpleUserRepository[Unit] = Free.liftF(Put(user))
    }

    val sample1 = for {
      user <- SimpleUserRepository.get(42)
      user2 = user.copy(id = user.id + 1)
      _ <- SimpleUserRepository.put(user2)
    } yield user2.name

    object SimpleRunner {
      import cats.Id
      import cats.~>

      val action2Id: SimpleUserRepository.Action ~> Id = new (SimpleUserRepository.Action ~> Id) {
        def apply[A](fa: SimpleUserRepository.Action[A]): Id[A] = fa match {
          case SimpleUserRepository.Get(id)   => User(id, s"User $id")
          case SimpleUserRepository.Put(user) => println(user)
        }
      }

      type Error = String
      val action2Either1: SimpleUserRepository.Action ~> Either[Error, *] =
        new (SimpleUserRepository.Action ~> Either[Error, *]) {
          def apply[A](fa: SimpleUserRepository.Action[A]): Either[Error, A] = fa match {
            case SimpleUserRepository.Get(id) =>
              if (id < 42) Right(User(id, s"User $id")) else Left(s"User $id not found.")
            case SimpleUserRepository.Put(user) => Right(println(user))
          }
        }

      val action2Either2: SimpleUserRepository.Action ~> Either[Error, *] =
        new (SimpleUserRepository.Action ~> Either[Error, *]) {
          def apply[A](fa: SimpleUserRepository.Action[A]): Either[Error, A] = fa match {
            case SimpleUserRepository.Get(id)   => Right(User(id, s"User $id"))
            case SimpleUserRepository.Put(user) => Left(s"$user is not able to save.")
          }
        }
    }

    sample1.foldMap(SimpleRunner.action2Id)

  }

}
