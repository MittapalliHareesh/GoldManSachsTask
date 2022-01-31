package com.goldmanscachs.task.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.goldmanscachs.task.R
import com.goldmanscachs.task.databinding.AstronomyPictureBinding
import com.goldmanscachs.task.model.ResponseData
import com.goldmanscachs.task.util.InternetConnection
import com.goldmanscachs.task.util.Resource
import com.goldmanscachs.task.util.Status
import com.goldmanscachs.task.viewModels.AstronomyPictureViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class AstronomyPictureFragment : Fragment() {

    private var _astronomyPictureBinding: AstronomyPictureBinding? = null
    private var datePickerDialog: DatePickerDialog? = null
    private val astronomyPictureViewModel: AstronomyPictureViewModel by viewModels()

    private val astronomyPictureBinding get() = _astronomyPictureBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _astronomyPictureBinding = AstronomyPictureBinding.inflate(inflater, container, false)
        return astronomyPictureBinding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDateField()
        astronomyPictureBinding.dateEditText.setOnTouchListener { _, _ ->
            datePickerDialog?.show()
            false
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDateField() {
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this.requireContext(),
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, month, dayOfMonth)
                val sd = SimpleDateFormat("yyyy-MM-dd")
                val startDate = newDate.time
                astronomyPictureBinding.dateEditText.text =
                    Editable.Factory.getInstance().newEditable(sd.format(startDate))
                getAstronomyPicture(sd.format(startDate))
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog?.datePicker?.maxDate = System.currentTimeMillis();
    }

    private fun getAstronomyPicture(selectedDate: String) {
        if (InternetConnection.checkNetworkConnection(requireContext())) {
            astronomyPictureViewModel.getPictureForSelectedDate(selectedDate)
                .observe(viewLifecycleOwner, {
                    renderUiState(it)
                })
        } else {
            Toast.makeText(requireContext(), getString(R.string.noInternet), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun renderUiState(resource: Resource<ResponseData>) {
        when (resource.status) {
            Status.SUCCESS -> {
                astronomyPictureBinding.progressBar.visibility = View.GONE
                astronomyPictureBinding.scrollView.visibility = View.VISIBLE
                resource.data?.let {
                    astronomyPictureBinding.titleTxtValue.text = it.title
                    astronomyPictureBinding.dateTxtValue.text = it.date
                    astronomyPictureBinding.explanationTxtValue.text = it.explanation
                    Glide.with(requireContext())
                        .load(it.url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontTransform()
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .into(astronomyPictureBinding.astronomyPicture)
                }
            }

            Status.LOADING -> {
                astronomyPictureBinding.progressBar.visibility = View.VISIBLE
                astronomyPictureBinding.scrollView.visibility = View.GONE
            }

            Status.ERROR -> {
                astronomyPictureBinding.progressBar.visibility = View.GONE
                astronomyPictureBinding.scrollView.visibility = View.GONE
                Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _astronomyPictureBinding = null
    }
}
