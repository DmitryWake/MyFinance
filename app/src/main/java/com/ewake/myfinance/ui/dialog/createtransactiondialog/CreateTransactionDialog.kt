package com.ewake.myfinance.ui.dialog.createtransactiondialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.ewake.myfinance.R
import com.ewake.myfinance.databinding.DialogCreateTransactionBinding
import com.ewake.myfinance.ui.model.CategoryModel
import java.io.Serializable

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class CreateTransactionDialog : DialogFragment() {

    private var _binding: DialogCreateTransactionBinding? = null
    private val binding: DialogCreateTransactionBinding
        get() = _binding!!

    private val builder: Builder? by lazy {
        arguments?.getSerializable(BUILDER_KEY) as Builder
    }

    private lateinit var categoriesAdapter: ArrayAdapter<String>

    private val chosenCategory: CategoryModel?
        get() = if (categoriesAdapter.getPosition(binding.category.text.toString()) >= 0) {
            builder?.categories?.find { it.name == binding.category.text.toString() }
        } else {
            null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_dropdown_category,
                builder?.categories?.map { it.name } ?: listOf()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCreateTransactionBinding.inflate(layoutInflater, container, false)

        binding.apply {
            title.text = builder?.title
            category.setAdapter(categoriesAdapter)

            add.setOnClickListener {
                chosenCategory?.let {
                    builder?.onPositiveButtonClickedListener?.invoke(
                        value.text.toString().toInt(),
                        it
                    )
                }
                dialog?.dismiss()
            }

            cancel.setOnClickListener {
                dialog?.dismiss()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class Builder : Serializable {
        var title: String? = null
        var categories: List<CategoryModel>? = null
        var onPositiveButtonClickedListener: ((value: Int, category: CategoryModel) -> Unit)? = null

        fun setTitle(text: String): Builder {
            title = text
            return this
        }

        fun setCategories(list: List<CategoryModel>): Builder {
            categories = list
            return this
        }

        fun setOnPositiveButtonClickListener(listener: (value: Int, category: CategoryModel) -> Unit): Builder {
            onPositiveButtonClickedListener = listener
            return this
        }

        fun build(): CreateTransactionDialog = newInstance(this)
    }

    companion object {
        fun newInstance(
            builder: Builder
        ): CreateTransactionDialog = CreateTransactionDialog().apply {
            arguments = Bundle().apply {
                putSerializable(BUILDER_KEY, builder)
            }
        }

        private const val BUILDER_KEY = "builder"
    }
}