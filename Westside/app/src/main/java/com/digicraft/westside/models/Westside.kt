package com.digicraft.westside.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Westside {
    open class User(@SerializedName("username") val username: String,
                    @SerializedName("password") val password: String,
                    @SerializedName("_id") val id: String?,
                    @SerializedName("imageUrl") val imageUrl: String?,
                    @SerializedName("lastActionDate") val lastActionDate: String?,
                    @SerializedName("createdDate") val createdDate: String?) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(username)
            parcel.writeString(password)
            parcel.writeString(id)
            parcel.writeString(imageUrl)
            parcel.writeString(lastActionDate)
            parcel.writeString(createdDate)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                return User(parcel)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }

    open class Event(@SerializedName("_id") val id: String,
                     @SerializedName("title") val title: String,
                     @SerializedName("description") val description: String?,
                     @SerializedName("startTime") val startTime: Date,
                     @SerializedName("endTime") val endTime: Date?,
                     @SerializedName("moreInformation") val moreInformation: String?,
                     @SerializedName("imageUrl") val imageUrl: String?,
                     @SerializedName("groups") val groups: List<Group>) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                Date(parcel.readLong()),
                Date(parcel.readLong()),
                parcel.readString(),
                parcel.readString(),
                ArrayList<Group>()) {
            parcel.readList(groups, Group::class.java.classLoader)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeLong(startTime.time)
            parcel.writeLong(if (endTime != null) endTime.time else 0)
            parcel.writeString(moreInformation)
            parcel.writeString(imageUrl)
            parcel.writeList(groups)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                return User(parcel)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }

    open class Group(@SerializedName("_id") val id: String,
                     @SerializedName("name") val name: String,
                     @SerializedName("description") val description: String?,
                     @SerializedName("chairperson") val chairperson: String?,
                     @SerializedName("email") val email: String?,
                     @SerializedName("phone") val phone: String?,
                     @SerializedName("imageUrl") val imageUrl: String?) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(chairperson)
            parcel.writeString(email)
            parcel.writeString(phone)
            parcel.writeString(imageUrl)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                return User(parcel)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }

    class Error(val error: String)
}