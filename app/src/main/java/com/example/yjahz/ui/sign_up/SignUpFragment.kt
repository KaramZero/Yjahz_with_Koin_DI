package com.example.yjahz.ui.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.yjahz.R
import com.example.yjahz.databinding.FragmentSignUpBinding
import com.example.yjahz.model.InputStatus.*
import com.example.yjahz.model.Status.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inputStatus.observe(viewLifecycleOwner) {
            when (it) {
                NAME -> binding.editTextTextPersonName.error = getString(R.string.not_valid_name)

                EMAIL -> binding.editTextTextEmailAddress.error =
                    getString(R.string.not_valid_email)

                PHONE -> binding.editTextPhone.error = getString(R.string.not_valid_phone)

                PASSWORD -> binding.editTextTextPassword.error =
                    getString(R.string.not_valid_password)

                CONFIRM_PASSWORD -> binding.editTextTextConfirmPassword.error =
                    getString(R.string.not_the_same_as_password)
                null -> {}
            }
        }

        viewModel.signUpStatus.observe(viewLifecycleOwner) {
            when (it) {
                LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.signUpButton.isEnabled = false
                }
                ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.signUpButton.isEnabled = true
                }
                DONE -> {
                    navigateToHome(viewModel, view)
                }
                null -> {
                    binding.progressBar.visibility = View.GONE
                    binding.signUpButton.isEnabled = true
                }
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.signUpButton.setOnClickListener {
            viewModel.signUp(
                name = binding.editTextTextPersonName.text.toString(),
                email = binding.editTextTextEmailAddress.text.toString(),
                password = binding.editTextTextPassword.text.toString(),
                confirmPassword = binding.editTextTextConfirmPassword.text.toString(),
                phone = binding.editTextPhone.text.toString()
            )
        }
        binding.logInTextView.setOnClickListener {
            view.findNavController()
                .navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
                )
        }

    }

    private fun navigateToHome(
        viewModel: SignUpViewModel,
        view: View
    ) {
        val name = viewModel.user.name ?: "no name"
        var address = "null"
        if (viewModel.user.addresses.size > 0) {
            address = viewModel.user.addresses[0].address ?: "null"
        }

        view.findNavController()
            .navigate(
                SignUpFragmentDirections.actionSignUpFragmentToHomeFragment(
                    name,
                    address
                )
            )
    }

}
