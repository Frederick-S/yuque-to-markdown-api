package yuque2mk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Repo {
    var id: Long = 0

    var name = ""

    var slug = ""

    var namespace = ""

    var description = ""
}