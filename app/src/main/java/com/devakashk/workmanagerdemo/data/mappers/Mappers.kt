package com.devakashk.workmanagerdemo.data.mappers

import com.devakashk.workmanagerdemo.data.models.QuotesDto
import com.devakashk.workmanagerdemo.domain.models.Quote

fun QuotesDto.toDomain(workType:String):Quote{
    return Quote(author,id,quote,workType)
}