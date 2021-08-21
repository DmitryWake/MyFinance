package com.ewake.myfinance.ui.fragment.splashscreen.interactor

import com.ewake.myfinance.data.repository.BudgetRepositoryImpl
import com.ewake.myfinance.data.repository.UserRepository
import com.ewake.myfinance.ui.model.UserSettingsModel
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private var budgetRepository: BudgetRepositoryImpl
) {

    fun loadUser(): Maybe<UserSettingsModel> {
        return userRepository.loadUser()
    }

    fun createUser(userModel: UserSettingsModel) {
        userRepository.createUser(userModel)
    }
}