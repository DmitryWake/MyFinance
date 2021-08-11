package com.ewake.myfinance.ui.fragment.mainpage.interactor

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
class MainPageInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepository
) {

    fun getCurrentUser(): Maybe<UserModel> {
        return userRepository.loadUser()
    }

    fun updateCurrentUser(model: UserModel) {
        userRepository.updateUser(model)
    }

    fun getDailyBudget(): Single<BudgetModel> = budgetRepository.getDailyBudget()

    fun updateBudget(budgetModel: BudgetModel) {
        budgetRepository.updateBudget(budgetModel)
    }
}