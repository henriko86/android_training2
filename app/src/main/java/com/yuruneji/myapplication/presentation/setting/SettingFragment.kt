package com.yuruneji.myapplication.presentation.setting

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.annotation.UiThread
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yuruneji.myapplication.R
import com.yuruneji.myapplication.common.AlphaInputFilter
import com.yuruneji.myapplication.common.CommonUtils.fullscreenToolbarFragment
import com.yuruneji.myapplication.common.LengthInputFilter
import com.yuruneji.myapplication.common.enum.ApiType
import com.yuruneji.myapplication.common.enum.AuthMethod
import com.yuruneji.myapplication.common.enum.LensFacing
import com.yuruneji.myapplication.common.enum.MinFaceSize
import com.yuruneji.myapplication.common.enum.MultiAuthType
import com.yuruneji.myapplication.common.ifLet
import com.yuruneji.myapplication.databinding.FragmentSettingBinding
import com.yuruneji.myapplication.presentation.view.DatePickerFragment
import com.yuruneji.myapplication.presentation.view.TimePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class SettingFragment : Fragment(), DatePickerFragment.OnSelectedDateListener, TimePickerFragment.OnSelectedTimeListener {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i(Throwable().stackTrace[0].methodName)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i(Throwable().stackTrace[0].methodName)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i(Throwable().stackTrace[0].methodName)
        super.onViewCreated(view, savedInstanceState)


        binding.text6.setOnClickListener {

            val inView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edittext1, null)

            val layout = inView.findViewById<TextInputLayout>(R.id.layout)
            layout.hint = "Text6"
            val editText = inView.findViewById<TextInputEditText>(R.id.text)
            editText.inputType = InputType.TYPE_CLASS_TEXT

            editText.filters = arrayOf(
                AlphaInputFilter(),
                LengthInputFilter(max = 10)
            )

            val builder = MaterialAlertDialogBuilder(requireContext())
                .setView(inView)
                // .setTitle("タイトル")
                .setPositiveButton("登録") { _, _ ->
                    binding.text6.text = editText.text
                }
                .setNeutralButton("キャンセル", null)
            builder.show()
        }


        // val toolbar: Toolbar = binding.toolbarParent.toolbar
        // toolbar.title = "設定"
        // val toolbarTitle = binding.toolbarParent.toolbarTitle
        // toolbarTitle.text = "設定"

        // val activity = requireActivity() as AppCompatActivity
        // activity.setSupportActionBar(toolbar)
        // activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        // activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // activity.supportActionBar?.show()

        // CommonUtil.fullscreenToolbarFragment(requireActivity(), true)
        // PreferenceManager.getDefaultSharedPreferences(requireContext()).
        // requireContext().deleteSharedPreferences(CameraPreferences.PREF_NAME)


        // val contextView = findViewById<View>(R.id.context_view)
        // binding.snackbarBtn.setOnClickListener {
        //     Snackbar.make(binding.root, "xxxxx", Snackbar.LENGTH_LONG)
        //         .setAction("Action") {
        //             Timber.d("action")
        //         }
        //         .show()
        // }


        // val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        // binding.listview.addItemDecoration(decoration)

        binding.slider1.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Timber.v("start:${slider.value}")
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Timber.v("stop:${slider.value}")
                binding.slider1Value.text = "${slider.value.toInt()}"
            }
        })
        // binding.slider1.addOnSliderTouchListener(object : Slider.OnChangeListener, Slider.OnSliderTouchListener {
        //     override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        //         binding.slider1Value.text = "${value.toInt()}"
        //     }
        //
        //     override fun onStartTrackingTouch(slider: Slider) {
        //     }
        //
        //     override fun onStopTrackingTouch(slider: Slider) {
        //     }
        // })


        binding.seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekbar1Value.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        val lensFacingList = LensFacing.valueList()
        val lensFacingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, lensFacingList)
        binding.lensFacing.setAdapter(lensFacingAdapter)
        // binding.lensFacing.additem
        binding.lensFacing.addTextChangedListener {
            viewModel.updateLensFacing(LensFacing.toNo(it.toString()))
        }


        // binding.imageWidth.doOnTextChanged { text, start, before, count ->  }(viewLifecycleOwner) {
        //     binding.imageWidth.setText(it, false)
        // }
        // binding.imageHeight.observe(viewLifecycleOwner) {
        //     binding.imageHeight.setText(it, false)
        // }


        // binding.imageWidth.filters = arrayOf(MinMaxInputFilter(3, 5))

        binding.imageWidth.addTextChangedListener { s ->
            ifLet(s) {
                viewModel.updateImageWidth(s.toString().toInt())
            }
        }
        viewModel.imageWidth.observe(viewLifecycleOwner) {
            binding.imageWidth.text = Editable.Factory.getInstance().newEditable(it.toString())
        }

        binding.imageHeight.addTextChangedListener { s ->
            s?.let {
                viewModel.updateImageHeight(s.toString().toInt())
            }
        }

        viewModel.imageHeight.observe(viewLifecycleOwner) {
            binding.imageHeight.text = Editable.Factory.getInstance().newEditable(it.toString())
        }


        val apiTypeList = ApiType.valueList()
        val apiTypeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, apiTypeList)
        binding.apiType.setAdapter(apiTypeAdapter)
        binding.apiType.addTextChangedListener {
            viewModel.updateApiType(ApiType.toNo(it.toString()))
        }


        val authMethodList = AuthMethod.valueList()
        val authMethodAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, authMethodList)
        binding.authMethod.setAdapter(authMethodAdapter)
        binding.authMethod.addTextChangedListener {
            viewModel.updateAuthMethod(AuthMethod.toNo(it.toString()))
            updateAuthMethod(it.toString() == AuthMethod.MULTI.value)
        }


        val multiAuthTypeList = MultiAuthType.valueList()
        val multiAuthTypeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, multiAuthTypeList)
        binding.multiAuthType.setAdapter(multiAuthTypeAdapter)
        binding.multiAuthType.addTextChangedListener {
            viewModel.updateMultiAuthType(MultiAuthType.toNo(it.toString()))
        }


        binding.faceAuthSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateFaceAuth(isChecked)
        }
        binding.cardAuthSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateCardAuth(isChecked)
        }
        binding.qrAuthSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateQrAuth(isChecked)
        }


        // binding.spinner1.adapter = apiTypeAdapter
        // binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        //     override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //         Timber.i("${parent?.getItemAtPosition(position)} ${position}")
        //     }
        //
        //     override fun onNothingSelected(parent: AdapterView<*>?) {
        //     }
        // }


        // 日付
        binding.date.setOnClickListener {
            val datePicker = DatePickerFragment()
            datePicker.show(childFragmentManager, "datePicker")
        }

        // 時間
        binding.time.setOnClickListener {
            val timePicker = TimePickerFragment()
            timePicker.show(childFragmentManager, "timePicker")
        }


        viewModel.lensFacing.observe(viewLifecycleOwner) {
            binding.lensFacing.setText(LensFacing.toValue(it), false)
        }
        viewModel.apiType.observe(viewLifecycleOwner) {
            binding.apiType.setText(ApiType.toValue(it), false)
        }
        viewModel.authMethod.observe(viewLifecycleOwner) {
            val flag = AuthMethod.toValue(it)
            binding.authMethod.setText(flag, false)
            updateAuthMethod(flag == AuthMethod.MULTI.value)
        }

        viewModel.multiAuthType.observe(viewLifecycleOwner) {
            binding.multiAuthType.setText(MultiAuthType.toValue(it), false)
        }

        viewModel.faceAuth.observe(viewLifecycleOwner) {
            binding.faceAuthSwitch.isChecked = it
        }
        viewModel.cardAuth.observe(viewLifecycleOwner) {
            binding.cardAuthSwitch.isChecked = it
        }
        viewModel.qrAuth.observe(viewLifecycleOwner) {
            binding.qrAuthSwitch.isChecked = it
        }


        val minFaceSizeList = MinFaceSize.valueList()
        val minFaceSizeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, minFaceSizeList)
        binding.minFaceSize.setAdapter(minFaceSizeAdapter)
        binding.minFaceSize.addTextChangedListener {
            viewModel.updateMinFaceSize(MinFaceSize.toSize(it.toString()))
        }
        viewModel.minFaceSize.observe(viewLifecycleOwner) {
            binding.minFaceSize.setText(MinFaceSize.toValue(it), false)
        }

        binding.saveBtn.setOnClickListener {
            Timber.d("saveBtn onClick")
        }
    }

    override fun onResume() {
        super.onResume()

        // フルスクリーン
        fullscreenToolbarFragment(requireActivity(), true)
    }

    override fun onPause() {
        super.onPause()

        // フルスクリーン
        fullscreenToolbarFragment(requireActivity(), false)
    }

    override fun onDestroyView() {
        Timber.i(Throwable().stackTrace[0].methodName)
        super.onDestroyView()
        _binding = null
    }

    override fun selectedDate(year: Int, month: Int, dayOfMonth: Int) {
        lifecycleScope.launch {
            val date = LocalDate.of(year, month + 1, dayOfMonth)
            binding.date.text = Editable.Factory.getInstance()
                .newEditable(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            // viewModel.setDate(date)
        }
    }

    override fun selectedTime(hour: Int, minute: Int) {
        lifecycleScope.launch {
            val time = LocalTime.of(hour, minute)
            binding.time.text = Editable.Factory.getInstance()
                .newEditable(time.format(DateTimeFormatter.ofPattern("HH:mm")))
            // viewModel.setTime(time)
        }
    }

    /**
     * 多要素認証のレイアウト更新
     * @param flag true: 多要素認証, false: 単要素認証
     */
    @UiThread
    private fun updateAuthMethod(flag: Boolean) {
        if (flag) { // 多要素認証
            // binding.multiAuthLayout.visibility = View.VISIBLE
            // binding.singleAuthLayout.visibility = View.GONE

            binding.multiAuthTypeLayout.isEnabled = true
            binding.faceAuthSwitch.isEnabled = false
            binding.cardAuthSwitch.isEnabled = false
            binding.qrAuthSwitch.isEnabled = false
        } else { // 単要素認証
            // binding.multiAuthLayout.visibility = View.GONE
            // binding.singleAuthLayout.visibility = View.VISIBLE

            binding.multiAuthTypeLayout.isEnabled = false
            binding.faceAuthSwitch.isEnabled = true
            binding.cardAuthSwitch.isEnabled = true
            binding.qrAuthSwitch.isEnabled = true
        }
    }
}
