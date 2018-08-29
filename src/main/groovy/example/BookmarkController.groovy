package example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated

import javax.annotation.Nullable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@CompileStatic
@Validated
@Controller("/api")
class BookmarkController {

    @Get("/bookmarks{?offset,max,sort,order}")
    HttpResponse list(@PositiveOrZero @Nullable Integer offset,
                      @Positive @Nullable Integer max,
                      @Nullable @Pattern(regexp = "name|href|title") String sort,
                      @Nullable @Pattern(regexp = "asc|desc|ASC|DESC") String order) {
        HttpResponse.ok()
    }
}
