package com.ewake.myfinance.ui.fragment.mainpage.interactor

import com.ewake.myfinance.data.repository.budget.BudgetRepositoryImpl
import com.ewake.myfinance.data.repository.category.CategoryRepository
import com.ewake.myfinance.data.repository.user.UserRepository
import com.ewake.myfinance.ui.model.*
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class MainPageInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val budgetRepository: BudgetRepositoryImpl,
    private val categoryRepository: CategoryRepository
) {

    fun getCurrentUser(): Maybe<UserSettingsModel> {
        return userRepository.loadUser()
    }

    fun updateCurrentUser(model: UserSettingsModel) {
        userRepository.updateUser(model)
    }

    fun getBudget(date: Date, type: PeriodType): Single<BudgetModel> =
        budgetRepository.getBudget(date, type)

    fun saveTransaction(transactionModel: TransactionModel) {
        budgetRepository.saveTransaction(transactionModel)
    }

    fun getCategories(): Single<List<CategoryModel>> = categoryRepository.getAll()
}