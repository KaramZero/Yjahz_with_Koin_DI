package com.example.yjahz.ui.welcome

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.yjahz.R
import com.example.yjahz.databinding.FragmentWelcomeBinding
import com.example.yjahz.model.Status.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WelcomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getClient()

        //Observing getting Client Profile status
        viewModel.clientStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> binding.progressBar.visibility = View.VISIBLE

                ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToLogIn(view)
                }

                DONE -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToHome(viewModel, view)
                }

                else -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    navigateToLogIn(view)
                }
            }
        }

        binding.logoImage.setOnClickListener {
            animateAsFallingObject(it)
        }

    }

    private fun animateAsFallingObject(it: View?) {
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

    private fun navigateToLogIn(view: View) {
        view.findNavController()
            .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment())
    }

    private fun navigateToHome(
        viewModel: WelcomeViewModel,
        view: View
    ) {

        //preparing Client Info to be sent to The Home Fragment
        val name = viewModel.user.name ?: "no name"
        var address = "null"
        if (viewModel.user.addresses.size > 0) {
            address = viewModel.user.addresses[0].address ?: "null"
        }

        view.findNavController()
            .navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}