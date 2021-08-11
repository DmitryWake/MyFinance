package com.ewake.myfinance.ui.fragment.splashscreen.interactor

import com.ewake.myfinance.data.repository.BudgetRepository
import com.ewake.myfinance.data.repository.UserRepository
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.UserModel
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private var budgetRepository: BudgetRepository
) {

    fun loadUser(): Maybe<UserModel> {
        return userRepository.loadUser()
    }

    fun createUser(userModel: UserModel) {
        userRepository.createUser(userModel)
    }

    fun loadBudget(): Single<List<BudgetModel>> {
        return budgetRepository.getAll()
    }

    fun saveBudget(list: List<BudgetModel>) {
        budgetRepository.saveBudget(list)
    }
}