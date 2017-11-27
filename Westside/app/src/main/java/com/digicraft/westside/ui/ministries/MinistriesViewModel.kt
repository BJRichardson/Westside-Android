package com.digicraft.westside.ui.ministries

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside

open class MinistriesViewModel(val group: Westside.Group, val context: Context?) : BaseObservable() {

    val name: String?
        get() = group.name

    val chairperson: String?
        get() {
            return "Chairperson: " + group.chairperson
        }
}