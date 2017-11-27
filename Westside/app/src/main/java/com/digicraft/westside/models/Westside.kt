package com.digicraft.westside.models

import android.os.Parcel
import com.digicraft.westside.utils.KParcelable
import com.digicraft.westside.utils.parcelableCreator
import com.digicraft.westside.utils.readBoolean
import com.digicraft.westside.utils.writeBoolean
import com.google.gson.annotations.SerializedName
import java.util.*

class Westside {
    open class User(@SerializedName("username") val username: String?,
                    @SerializedName("password") val password: String?,
                    @SerializedName("email") val email: String?,
                    @SerializedName("id") val id: Int?,
                    @SerializedName("imageUrl") val imageUrl: String?,
                    @SerializedName("phone") val phone: String?,
                    @SerializedName("address") val address: String?,
                    @SerializedName("firstName") val firstName: String,
                    @SerializedName("lastName") val lastName: String,
                    @SerializedName("roles") val roles: String?) : KParcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
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
            parcel.writeInt(if (id != null) id else 0)
            parcel.writeString(imageUrl)
            parcel.writeString(phone)
            parcel.writeString(address)
            parcel.writeString(firstName)
            parcel.writeString(lastName)
            parcel.writeString(roles)
        }

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::User)
        }
    }

    class Event(@SerializedName("id") val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("description") val description: String?,
                @SerializedName("startTime") val startTime: Date,
                @SerializedName("endTime") val endTime: Date?,
                @SerializedName("moreInformation") val moreInformation: String?,
                @SerializedName("imageUrl") val imageUrl: String?,
                @SerializedName("groups") val groups: List<Group>,
                @SerializedName("users") val users: List<User>) : KParcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                Date(parcel.readLong()),
                Date(parcel.readLong()),
                parcel.readString(),
                parcel.readString(),
                ArrayList<Group>(),
                ArrayList<User>()) {
            parcel.readList(groups, Group::class.java.classLoader)
            parcel.readList(users, User::class.java.classLoader)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeLong(startTime.time)
            parcel.writeLong(if (endTime != null) endTime.time else 0)
            parcel.writeString(moreInformation)
            parcel.writeString(imageUrl)
            parcel.writeList(groups)
            parcel.writeList(users)
        }

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Event)
        }
    }

    open class Group(@SerializedName("id") val id: Int,
                     @SerializedName("name") val name: String,
                     @SerializedName("description") val description: String?,
                     @SerializedName("chairperson") val chairperson: String?,
                     @SerializedName("email") val email: String?,
                     @SerializedName("phone") val phone: String?,
                     @SerializedName("imageUrl") val imageUrl: String?,
                     @SerializedName("users") val members: List<User>) : KParcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                ArrayList<User>()) {
                    parcel.readList(members, User::class.java.classLoader)
                 }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(chairperson)
            parcel.writeString(email)
            parcel.writeString(phone)
            parcel.writeString(imageUrl)
            parcel.writeList(members)
        }

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Group)
        }
    }

    open class Announcement(@SerializedName("id") val id: Int,
                            @SerializedName("announcement") val announcement: String,
                            @SerializedName("createdDate") val createdDate: Date,
                            @SerializedName("updatedDate") val updatedDate: Date?,
                            @SerializedName("imageUrl") val imageUrl: String?,
                            @SerializedName("groupid") val groupId: Int,
                            @SerializedName("poster") val poster: User) : KParcelable {
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

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Announcement)
        }
    }

    open class Prayer(@SerializedName("id") val id: Int,
                      @SerializedName("prayer") val prayer: String,
                      @SerializedName("createdDate") val createdDate: Date,
                      @SerializedName("updatedDate") val updatedDate: Date?,
                      @SerializedName("poster") val poster: User) : KParcelable {
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

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Prayer)
        }
    }

    open class UserEvent(@SerializedName("id") val id: Int,
                         @SerializedName("isAttending") val isAttending: Boolean,
                         @SerializedName("user") val user: User,
                         @SerializedName("event") val event: Event) : KParcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readBoolean(),
                parcel.readParcelable(User::class.java.classLoader),
                parcel.readParcelable(Event::class.java.classLoader))

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeBoolean(isAttending)
            parcel.writeParcelable(user, flags)
            parcel.writeParcelable(event, flags)
        }

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Prayer)
        }
    }

    open class UserGroup(@SerializedName("id") val id: Int,
                         @SerializedName("user") val user: User,
                         @SerializedName("group") val group: Group) : KParcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readParcelable(User::class.java.classLoader),
                parcel.readParcelable(Group::class.java.classLoader))

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeParcelable(user, flags)
            parcel.writeParcelable(group, flags)
        }

        companion object {
            @JvmField
            val CREATOR = parcelableCreator(::Prayer)
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