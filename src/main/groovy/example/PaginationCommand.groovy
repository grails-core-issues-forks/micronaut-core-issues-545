package example

import groovy.transform.CompileStatic

import javax.annotation.Nullable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@CompileStatic
class PaginationCommand {

    @PositiveOrZero
    @Nullable
    Integer offset

    @Positive
    @Nullable
    Integer max

    @Nullable
    @Pattern(regexp = "name|href|title")
    String sort

    @Nullable
    @Pattern(regexp = "asc|desc|ASC|DESC")
    String order
}
