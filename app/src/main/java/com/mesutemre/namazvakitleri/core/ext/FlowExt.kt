package com.mesutemre.namazvakitleri.core.ext

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent

fun <R, C> BaseResourceEvent<R>.convertRersourceEventType(
    convert: () -> C,
): BaseResourceEvent<C> {
    return if (this is BaseResourceEvent.Success) {
        BaseResourceEvent.Success(data = convert())
    } else if (this is BaseResourceEvent.Error) {
        BaseResourceEvent.Error(message = this.message)
    } else {
        BaseResourceEvent.Loading()
    }
}