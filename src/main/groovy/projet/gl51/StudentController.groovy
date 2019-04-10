package gl51.project

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus


@Controller("/student")
class StudentController {

   @Get("/")
   List<Student> index() {
       [new Student(firstName: "S�bastien", lastName: "Gadot")]
   }
}
