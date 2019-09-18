package com.tinkooladik.urbanpet.view.definition

import com.tinkooladik.urbanpet.di.scope.PerActivity
import javax.inject.Inject

@PerActivity
class TermHolder @Inject constructor() {

    var term: String? = null
}