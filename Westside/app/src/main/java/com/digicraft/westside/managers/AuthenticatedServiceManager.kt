package com.digicraft.westside.managers

import android.content.res.Resources
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.extensions.sharedNetworkSubscription
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.AuthenticatedService
import com.digicraft.westside.service.RetryAfterTimeoutWithDelay
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException


class AuthenticatedServiceManager(private val service: WestsideServiceManager, private val authenticatedService: AuthenticatedService, private val cacheManager: WestsideCacheManager) : AuthenticatedService, Receivable.Token, Receivable.User, Receivable.UserEvent, Receivable.UserGroup, Receivable.Users {
    val isAuthenticated: Boolean
        get() = cacheManager.isAuthenticated
    private var isReauthenticating: Boolean = false
    private var currentUser: Westside.User? = null

    private val listeners: MutableList<ReauthenticationListener> = mutableListOf()

    override fun errorTextFromException(throwable: Throwable, resources: Resources): String {
        //Gotta override because of multiple implementations, so we are going to just do nothing here because the serviceManager isn't responsible for error text!
        return ""
    }

    private fun refresh(refreshToken: String) {
        if (!isReauthenticating) {
            isReauthenticating = true
            service.refresh(refreshToken)
                    .subscribe(this::onTokenReceived, this::onTokenReceivedError)
        }
    }

    override fun getUser(): Observable<Westside.User> {
        val observable = authenticatedService.getUser()
                .sharedNetworkSubscription()
        observable.subscribe(this::onUserReceived, this::onError)
        return observable
    }

    override fun joinEvent(eventId: Int): Observable<Westside.UserEvent> {
        val observable = authenticatedService.joinEvent(eventId)
                .sharedNetworkSubscription()
        observable.subscribe(this::onUserEventReceived, this::onError)
        return observable
    }

    override fun leaveEvent(eventId: Int): Observable<ResponseBody> {
        val observable = authenticatedService.leaveEvent(eventId)
                .sharedNetworkSubscription()
        observable.subscribe(this::onDeleteSuccess, this::onError)
        return observable
    }

    override fun joinMinistry(groupId: Int): Observable<Westside.UserGroup> {
        val observable = authenticatedService.joinMinistry(groupId)
                .sharedNetworkSubscription()
        observable.subscribe(this::onUserGroupReceived, this::onError)
        return observable
    }

    override fun leaveMinistry(groupId: Int): Observable<ResponseBody> {
        val observable = authenticatedService.leaveMinistry(groupId)
                .sharedNetworkSubscription()
        observable.subscribe(this::onDeleteSuccess, this::onError)
        return observable
    }

    override fun fetchUsers(): Observable<List<Westside.User>> {
        val observable = authenticatedService.fetchUsers()
                .sharedNetworkSubscription()
        observable.subscribe(this::onUsersReceived, this::onUserReceivedError)
        return observable
    }


    private fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            processHttpException(throwable)
        }
    }

    private fun processHttpException(httpException: HttpException) {
        if (httpException.code() == 401 && isAuthenticated) {
            refresh(cacheManager.refreshToken)
        }
    }

    override fun onTokenReceived(token: Westside.Token) {
        isReauthenticating = false
        listeners.forEach { it.onReauthenticated(token) }
    }

    override fun onTokenReceivedError(error: Throwable) {
        isReauthenticating = false
    }

    override fun onUserReceived(user: Westside.User) {
        currentUser = user
        // We are going to register the token now that we know the user is authed.
//        val firebaseToken = FirebaseInstanceId.getInstance().token
//        if (!firebaseToken.isNullOrEmpty()) {
//            //Message in Receivable.Device
//            //registerDevice(Fox.New.Device(firebaseToken!!))
//        }
    }

    fun addReauthenticationListener(listener: ReauthenticationListener) {
        listeners.add(listener)
    }

    fun removeReauthenticationListener(listener: ReauthenticationListener) {
        listeners.remove(listener)
    }

    fun getCurrentUser() : Westside.User? {
        return currentUser
    }

    private fun onDeleteSuccess(response: ResponseBody) {
        //Do Nothing
    }
}

interface ReauthenticationListener {
    fun onReauthenticated(token: Westside.Token)
}

