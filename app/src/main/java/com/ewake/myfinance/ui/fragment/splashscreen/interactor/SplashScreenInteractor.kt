package com.ewake.myfinance.ui.fragment.splashscreen.interactor

import com.ewake.myfinance.data.repository.UserRepository
import com.ewake.myfinance.ui.model.UserModel
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenInteractor @Inject constructor(private val userRepository: UserRepository) {

    fun loadUser(): Maybe<UserModel> {
        return userRepository.loadUser()
    }

    fun createUser(userModel: UserModel) {
        userRepository.createUser(userModel)
    }
}