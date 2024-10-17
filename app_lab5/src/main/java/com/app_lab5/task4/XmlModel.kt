package com.app_lab5.task4

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
data class ValCurs(
    @field:Element(name = "Date", required = false)
    var date: String? = null,

    @field:ElementList(inline = true, required = false)
    var valutes: List<Valute>? = null
)

@Root(name = "Valute", strict = false)
data class Valute(
    @field:Element(name = "NumCode", required = false)
    var numCode: String? = null,

    @field:Element(name = "CharCode", required = false)
    var charCode: String? = null,

    @field:Element(name = "Nominal", required = false)
    var nominal: Int? = null,

    @field:Element(name = "Name", required = false)
    var name: String? = null,

    @field:Element(name = "Value", required = false)
    var value: String? = null, // consider using String for handling decimal points

    @field:Element(name = "VunitRate", required = false)
    var vunitRate: String? = null // consider using String for handling decimal points
)
