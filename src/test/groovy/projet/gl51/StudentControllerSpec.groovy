package projet.gl51

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class StudentControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "test index"() {
		/*
        given:
        HttpResponse response = client.toBlocking().exchange("/student")

        expect:
        response.status == HttpStatus.OK
        */
		when:
		List<Student> students = client.toBlocking().retrieve(HttpRequest.GET('/student'), Argument.listOf(Student).type)
		
		then:
		students.get(0).toString() == "[firstName:naji, lastName:zakaria]"
		students.get(1).toString() == "[firstName:naji, lastName:omar]"
    }
}
