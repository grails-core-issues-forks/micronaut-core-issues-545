package example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated

import javax.annotation.Nullable
import javax.validation.Valid

@CompileStatic
@Validated
@Controller("/pojo")
class BookmarkWithPojoController {

    @Get("/bookmarks{?offset,max,sort,order}")
    HttpResponse list(@Valid @QueryValue @Nullable PaginationCommand cmd) {
        HttpResponse.ok()
    }
}
