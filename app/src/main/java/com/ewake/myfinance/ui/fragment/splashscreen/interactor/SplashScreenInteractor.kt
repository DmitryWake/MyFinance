package com.ewake.myfinance.ui.fragment.splashscreen.interactor

import com.ewake.myfinance.data.repository.category.CategoryRepository
import com.ewake.myfinance.data.repository.user.UserRepository
import com.ewake.myfinance.ui.model.UserSettingsModel
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) {

    fun loadUser(): Maybe<UserSettingsModel> {
        return userRepository.loadUser()
    }

    fun createUser(userModel: UserSettingsModel) {
        userRepository.createUser(userModel)
    }

    fun checkCategoriesExists(): Single<Boolean> = categoryRepository.checkCategoriesExists()

    fun initCategories() {
        categoryRepository.initCategories()
    }
}