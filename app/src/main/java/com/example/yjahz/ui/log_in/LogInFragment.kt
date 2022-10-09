package com.example.yjahz.ui.log_in

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.yjahz.R
import com.example.yjahz.databinding.FragmentLogInBinding
import com.example.yjahz.model.InputStatus.*
import com.example.yjahz.model.Status.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInButton.setOnClickListener {
            viewModel.logIn(
                binding.editTextTextEmailAddress.text.toString(),
                binding.editTextTextPassword.text.toString()
            )

        }


        binding.signUpText.setOnClickListener {
            navigateToSignUpFragment(view)
        }

        viewModel.logInStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> {
                    binding.logInButton.isEnabled = false
                    binding.progressBar.visibility = View.VISIBLE
                }
                ERROR -> {
                    binding.logInButton.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
                DONE -> {

                    //preparing Client Info to be sent to The Home Fragment
                    val name = viewModel.user.name ?: "no name"
                    var address = "null"
                    if (viewModel.user.addresses.size > 0) {
                        address = viewModel.user.addresses[0].address ?: "null"
                    }

                    navigateToHomeFragment(view, name, address)

                }
                else -> {
                    binding.logInButton.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.inputStatus.observe(viewLifecycleOwner) {
            when (it) {
                EMAIL -> binding.editTextTextEmailAddress.error = getString(R.string.invalid_email)
                PASSWORD -> binding.editTextTextPassword.error =
                    getString(R.string.invalid_password)
                else -> {}
            }
        }

        binding.productImageView.setOnClickListener {
            animate(it)
        }

    }

    private fun animate(it: View?) {
        val container = it?.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = it.width.toFloat()
        var starH: Float = it.height.toFloat()

        val newStar = AppCompatImageView(requireContext())
        newStar.setImageResource(R.drawable.yjahz_logo)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)

        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX
        starW *= newStar.scaleX
        starH *= newStar.scaleY
        newStar.translationX = Math.random().toFloat() *
                containerW - starW / 2


        val mover = ObjectAnimator.ofFloat(
            newStar, View.TRANSLATION_Y,
            -starH, containerH + starH
        )
        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()

        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        set.start()


    }


    private fun navigateToHomeFragment(
        view: View,
        name: String,
        address: String
    ) {
        view.findNavController()
            .navigate(
                LogInFragmentDirections.actionLogInFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

    private fun navigateToSignUpFragment(view: View) {
        view.findNavController()
            .navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
    }

}