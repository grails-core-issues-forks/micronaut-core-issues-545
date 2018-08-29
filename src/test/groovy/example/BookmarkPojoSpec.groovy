package example

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriTemplate
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class BookmarkPojoSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    HttpClient client = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.getURL())

    void "test POJO binding allows nullable"() {
        when:
        UriTemplate template = new UriTemplate("/pojo/bookmarks{?offset,max,sort,order}")
        String uri = template.expand([:])

        then:
        uri == '/pojo/bookmarks'

        when:
        HttpRequest request = HttpRequest.GET(uri)
        HttpResponse rsp = client.toBlocking().exchange(request)

        then:
        rsp.status == HttpStatus.OK
    }

    void "test POJO binding"() {
        when:
        UriTemplate template = new UriTemplate("/pojo/bookmarks{?offset,max,sort,order}")
        String uri = template.expand([offset: -1])

        then:
        uri == '/pojo/bookmarks?offset=-1'

        when:
        HttpRequest request = HttpRequest.GET(uri)
        client.toBlocking().exchange(request)

        then:
        def e = thrown(HttpClientResponseException)
        e.response.status == HttpStatus.BAD_REQUEST
    }
}
