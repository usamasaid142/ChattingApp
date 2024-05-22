package com.osama.chattingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.osama.chattingapp.R
import com.osama.chattingapp.databinding.ChattingfragmentBinding
import com.osama.chattingapp.databinding.HomefragmentBinding


class HomeFragment : Fragment() {

  private lateinit var binding: HomefragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= HomefragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}