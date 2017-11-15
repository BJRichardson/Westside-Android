package com.digicraft.westside.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Westside {
    open class User(@SerializedName("username") val username: String,
                    @SerializedName("password") val password: String,
                    @SerializedName("email") val email: String?,
                    @SerializedName("_id") val id: String?,
                    @SerializedName("imageUrl") val imageUrl: String?,
                    @SerializedName("phone") val phone: String?,
                    @SerializedName("address") val address: String?,
                    @SerializedName("firstName") val firstName: String,
                    @SerializedName("lastName") val lastName: String,
                    @SerializedName("roles") val roles: String?) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(username)
            parcel.writeString(password)
            parcel.writeString(email)
            parcel.writeString(id)
            parcel.writeString(imageUrl)
            parcel.writeString(phone)
            parcel.writeString(address)
            parcel.writeString(firstName)
            parcel.writeString(lastName)
            parcel.writeString(roles)
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

    class Event(@SerializedName("_id") val id: String,
                @SerializedName("title") val title: String,
                @SerializedName("description") val description: String?,
                @SerializedName("startTime") val startTime: Date,
                @SerializedName("endTime") val endTime: Date?,
                @SerializedName("moreInformation") val moreInformation: String?,
                @SerializedName("imageUrl") val imageUrl: String?,
                @SerializedName("groups") val groups: List<Group>) : Parcelable {
        init {

        }

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

        companion object CREATOR : Parcelable.Creator<Event> {
            override fun createFromParcel(parcel: Parcel): Event {
                return Event(parcel)
            }

            override fun newArray(size: Int): Array<Event?> {
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

        companion object CREATOR : Parcelable.Creator<Group> {
            override fun createFromParcel(parcel: Parcel): Group {
                return Group(parcel)
            }

            override fun newArray(size: Int): Array<Group?> {
                return arrayOfNulls(size)
            }
        }
    }

    open class Announcement(@SerializedName("_id") val id: Int,
                            @SerializedName("announcement") val announcement: String,
                            @SerializedName("createdDate") val createdDate: Date,
                            @SerializedName("updatedDate") val updatedDate: Date?,
                            @SerializedName("imageUrl") val imageUrl: String?,
                            @SerializedName("groupid") val groupId: Int,
                            @SerializedName("poster") val poster: User) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                Date(parcel.readLong()),
                Date(parcel.readLong()),
                parcel.readString(),
                parcel.readInt(),
                parcel.readParcelable(User::class.java.classLoader))

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(announcement)
            parcel.writeLong(createdDate.time)
            parcel.writeLong(if (updatedDate != null) updatedDate.time else 0)
            parcel.writeString(imageUrl)
            parcel.writeInt(groupId)
            parcel.writeParcelable(poster, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Announcement> {
            override fun createFromParcel(parcel: Parcel): Announcement {
                return Announcement(parcel)
            }

            override fun newArray(size: Int): Array<Announcement?> {
                return arrayOfNulls(size)
            }
        }
    }

    open class Prayer(@SerializedName("_id") val id: Int,
                      @SerializedName("prayer") val prayer: String,
                      @SerializedName("createdDate") val createdDate: Date,
                      @SerializedName("updatedDate") val updatedDate: Date?,
                      @SerializedName("poster") val poster: User) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                Date(parcel.readLong()),
                Date(parcel.readLong()),
                parcel.readParcelable(User::class.java.classLoader))

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(prayer)
            parcel.writeLong(createdDate.time)
            parcel.writeLong(if (updatedDate != null) updatedDate.time else 0)
            parcel.writeParcelable(poster, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Prayer> {
            override fun createFromParcel(parcel: Parcel): Prayer {
                return Prayer(parcel)
            }

            override fun newArray(size: Int): Array<Prayer?> {
                return arrayOfNulls(size)
            }
        }
    }

    class Token(@SerializedName("access_token") val accessToken: String,
                @SerializedName("refresh_token") val refreshToken: String,
                @SerializedName("token_type") val tokenType: String,
                @SerializedName("expires_in") val expiresIn: String)

    class Error(val error: String)

    class New {
        class User(username: String,
                   password: String,
                   firstName: String,
                   lastName: String,
                   phone: String?) : Westside.User(username, password, null, null, null, phone, null, firstName, lastName, null)
        //open class Device(val token: String, val type: String = "android")
    }
}